package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.text.html.parser.DTD;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;

	Stack<Obj> methodCalledStack = new Stack<>();
	Obj currentMethodCalled = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	Type currentType;
	Stack<Integer> currentMethodParamNumStack = new Stack<>();
	int nVars;

	Logger log = Logger.getLogger(getClass());
	private List<Definition> currDeclVar = new LinkedList<>();
	private List<DefinitionArray> currDeclVarArray = new LinkedList<>();
	// private Struct currExprType = Tab.noType;
	private List<Obj> allClasses = new LinkedList<>();
	// private Designator lastDesignator;
	// private boolean foundArray = false;
	private Queue<DesignatorMulti> designatorQueue = new LinkedList<>();
	private boolean changed = false;

	public SemanticPass() {
		super();
		Struct struct = new Struct(Struct.Int);
		Tab.insert(Obj.Type, "bool", struct);
	}

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public void visit(Definition varDefinition) {
		varDeclCount++;
		varDefinition.struct = currentType.struct;
		currDeclVar.add(varDefinition);
	}

	@Override
	public void visit(DefinitionArray definitionArray) {
		definitionArray.struct = new Struct(Struct.Array, currentType.struct);
		currDeclVarArray.add(definitionArray);
	}

	public void visit(VarDeclarations varDeclarations) {
//		currentType = varDeclarations.getType();
		for (Definition var : currDeclVar) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error("Greska na liniji " + varDeclarations.getLine() + ". Varijabla " + var.getName()
						+ " je vec definisana");
				continue;
			}
			report_info("Deklarisana promenljiva " + var.getName(), varDeclarations);
			Tab.insert(Obj.Var, var.getName(), var.struct);
		}
		for (DefinitionArray var : currDeclVarArray) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error("Greska na liniji " + varDeclarations.getLine() + ". Varijabla " + var.getName()
						+ " je vec definisana");
				continue;
			}
			report_info("Deklarisan niz " + var.getName(), varDeclarations);
			Tab.insert(Obj.Var, var.getName(), var.struct);
		}
		currDeclVarArray.clear();
		currDeclVar.clear();
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(ClassName className) {
		if (Tab.find(className.getClassName()) != Tab.noObj) {
			report_error("Greska na liniji " + className.getLine() + ". Klasa " + className.getClassName()
					+ " je vec definisana", null);
		}
		className.obj = Tab.insert(Obj.Type, className.getClassName(), new Struct(Struct.Class));
		Tab.openScope();
		report_info("Pronadjena deklaracija klase: " + className.getClassName(), className);
		allClasses.add(className.obj);
	}

	@Override
	public void visit(ClassDeclarations classDeclarations) {
		Tab.chainLocalSymbols(classDeclarations.getClassName().obj.getType());
		Tab.closeScope();
		report_info("Zavrsena obrada klase: " + classDeclarations.getClassName().getClassName(), classDeclarations);
	}

	@Override
	public void visit(ClassDeclarationsExtends classExtends) {
		Type extendClassType = classExtends.getType();
		Obj extendClassObj = Tab.find(extendClassType.getTypeName());
		if (!allClasses.contains(extendClassObj)) {
			report_error("Nije moguce izvodjenje iz tipa: " + extendClassObj.getName(), extendClassType);
		}
		if (Tab.noObj != extendClassObj) {
			Collection<Obj> listOfSimbols = extendClassObj.getLocalSymbols();
			for (Obj simbol : listOfSimbols) {
				Tab.insert(simbol.getKind(), simbol.getName(), simbol.getType());
			}
		}
	}

	@Override
	public void visit(VarListClassNonEmpty VarListClassNonEmpty) {
		for (Definition var : currDeclVar) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error("Greska na liniji " + VarListClassNonEmpty.getLine() + ". Varijabla " + var.getName()
						+ " je vec definisana");
				continue;
			}
			report_info("Deklarisano polje klase: promenljiva " + var.getName(), VarListClassNonEmpty);
			Tab.insert(Obj.Fld, var.getName(), var.struct);
		}
		for (DefinitionArray var : currDeclVarArray) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error("Greska na liniji " + VarListClassNonEmpty.getLine() + ". Varijabla " + var.getName()
						+ " je vec definisana");
				continue;
			}
			report_info("Deklarisano polje klase: niz " + var.getName(), VarListClassNonEmpty);
			Tab.insert(Obj.Var, var.getName(), var.struct);
		}
		currDeclVarArray.clear();
		currDeclVar.clear();
	}

	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
		log.info("Zatvoren skoup programa");
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			type.struct = Tab.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
				currentType = type;
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}

	@Override
	public void visit(MethodTypeNameWithType methodTypeNameWithType) {
		if (Tab.find(methodTypeNameWithType.getMethodName()) != Tab.noObj) {
			report_error("Greska na liniji " + methodTypeNameWithType.getLine() + ". Metoda "
					+ methodTypeNameWithType.getMethodName() + " je vec definisana", null);
		}
		currentMethod = Tab.insert(Obj.Meth, methodTypeNameWithType.getMethodName(),
				methodTypeNameWithType.getType().struct);
		methodTypeNameWithType.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeNameWithType.getMethodName(), methodTypeNameWithType);
	}

	public void visit(MethodTypeNameVoid MethodTypeNameVoid) {
		currentMethod = Tab.insert(Obj.Meth, MethodTypeNameVoid.getMethodName(), Tab.noType);
		currentMethod.setFpPos(-1);
		currentMethodParamNumStack.push(0);
		MethodTypeNameVoid.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + MethodTypeNameVoid.getMethodName(), MethodTypeNameVoid);
	}

	public void visit(MethodDeclaration MethodDeclaration) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + MethodDeclaration.getLine() + ": funkcija "
					+ currentMethod.getName() + " nema return iskaz!", null);
		}
		log.info("Zatvoren skoup funkcije");
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		returnFound = false;
		currentMethod = null;
	}

	public void visit(FormalParametherListItem fItem) {
		Struct paramType = fItem.getVarDeclDefinition().struct;
//		if (fItem.getVarDeclDefinition() instanceof DefinitionArray)
//			paramType = fItem

		fItem.getVarDeclDefinition().struct = paramType;
		currentMethod.setLevel(currentMethod.getLevel() + 1);
		currentMethod.setFpPos(currentMethod.getFpPos() + 1);
	}

	public void visit(FormalParamsList fList) {
		for (Definition var : currDeclVar) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error(
						"Greska na liniji " + fList.getLine() + ". Varijabla " + var.getName() + " je vec definisana");
				continue;
			}
			report_info("Deklarisan formalni parametar funkcije: promenljiva " + var.getName(), fList);
			Tab.insert(Obj.Var, var.getName(), var.struct);
		}

		for (DefinitionArray var : currDeclVarArray) {
			if (Tab.currentScope.findSymbol(var.getName()) != null) {
				log.error(
						"Greska na liniji " + fList.getLine() + ". Varijabla " + var.getName() + " je vec definisana");
				continue;
			}
			report_info("Deklarisan formalni parametar funkcije: niz " + var.getName(), fList);
			Tab.insert(Obj.Var, var.getName(), var.struct);
		}
		currDeclVarArray.clear();

		currDeclVar.clear();
	}

	@Override
	public void visit(ParenDesignator parenDesignator) {
		if (parenDesignator.getExpr().struct != Tab.intType) {
			report_error("Izraz za dohvatanje elemnta niza mora da bude tipa int ", parenDesignator);
		}
		designatorQueue.add(parenDesignator);
	}

	@Override
	public void visit(DotDesignator dotDesignator) {
		designatorQueue.add(dotDesignator);
	}

	@Override
	public void visit(MultiDesignator multiDesignator) {
		Obj obj = Tab.find(multiDesignator.getName());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + multiDesignator.getLine() + " : ime " + multiDesignator.getName()
					+ " nije deklarisano! ", null);
		}
		multiDesignator.obj = obj;
	}

	@Override
	public void visit(SingleDesignator singleDesignator) {
		Obj obj = Tab.find(singleDesignator.getName());
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + singleDesignator.getLine() + " : ime " + singleDesignator.getName()
					+ " nije deklarisano! ", null);
		}
		singleDesignator.obj = obj;
	}

	@Override
	public void visit(DesignatorAssignmentStatement designatorAssignmentStatement) {
		Struct left = designatorAssignmentStatement.getDesignatorForAssign().struct;
		Struct right = designatorAssignmentStatement.getExpr().struct;
		if (!right.assignableTo(left))
			report_error("Greska na liniji " + designatorAssignmentStatement.getLine() + " : "
					+ "nekompatibilni tipovi u dodeli vrednosti! ", null);
	}

	public void visit(DesignatorForMethodCall dsForMethodCall) {
		methodCalledStack.push(dsForMethodCall.getDesignator().obj);
		currentMethodParamNumStack.push(0);
		changed = false;
	}

	@Override
	public void visit(MethodNameDesignator methodNameDesignator) {
		methodCalledStack.push(methodNameDesignator.getDesignator().obj);
		currentMethodParamNumStack.push(0);;
		changed = false;
	}

	@Override
	public void visit(DesignatorItemFuncCallWithParam dfParam) {
		currentMethodCalled = methodCalledStack.pop();
		int currentMethodParamNum = currentMethodParamNumStack.pop();
		if ((dfParam.getActualPars() instanceof NoActualParam && currentMethodCalled.getLevel() > 0)
				|| (!(dfParam.getActualPars() instanceof NoActualParam) && currentMethodCalled.getLevel() == 0)
				|| (currentMethodParamNum != currentMethodCalled.getLevel())) {
			report_error("Broj prosledjenih parametara se ne slaze sa brojem definisanih parametara za metodu: "
					+ currentMethodCalled.getName(), dfParam);
		}
		if (Obj.Meth == currentMethodCalled.getKind()) {
			report_info("Pronadjen poziv funkcije " + currentMethodCalled.getName() + " na liniji " + dfParam.getLine(),
					null);
		} else {
			report_error("Greska na liniji " + dfParam.getLine() + " : ime " + currentMethodCalled.getName()
					+ " nije funkcija!", null);
		}
		changed = false;
		currentMethodParamNum = 0;
	}

	public void visit(MethodCall funcCall) {
		currentMethodCalled = methodCalledStack.pop();
		int currentMethodParamNum = currentMethodParamNumStack.pop();
		if ((funcCall.getActualPars() instanceof NoActualParam && currentMethodCalled.getLevel() > 0)
				|| (!(funcCall.getActualPars() instanceof NoActualParam) && currentMethodCalled.getLevel() == 0)
				|| (currentMethodParamNum != currentMethodCalled.getLevel())) {
			report_error("Broj prosledjenih parametara se ne slaze sa brojem definisanih parametara za metodu: "
					+ currentMethodCalled.getName(), funcCall);
		}
		if (Obj.Meth == currentMethodCalled.getKind()) {
			report_info(
					"Pronadjen poziv funkcije " + currentMethodCalled.getName() + " na liniji " + funcCall.getLine(),
					null);
			funcCall.struct = currentMethodCalled.getType();
		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + currentMethodCalled.getName()
					+ " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
		// currentMethodCalled = methodCalledStack.empty() ? null :
		// methodCalledStack.pop();
		currentMethodParamNum = 0;
	}

	@Override
	public void visit(MulopTerm mulopTerm) {
		Struct leftT = mulopTerm.getFactor().struct;
		Struct rightT = mulopTerm.getFactor1().struct;
		if (leftT.equals(rightT) && leftT == Tab.intType) {
			mulopTerm.struct = leftT;
		} else {
			report_error("Greska na liniji " + mulopTerm.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje.",
					null);
			mulopTerm.struct = Tab.noType;
		}
	}

	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getFactor().struct;
	}

	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}

	@Override
	public void visit(NegativeTermExpr NegativeTermExpr) {
		NegativeTermExpr.struct = NegativeTermExpr.getTerm().struct;
	}

	public void visit(ActualParamSingleItem acSingleItem) {
		Struct paramType = acSingleItem.getExpr().struct;
		Obj currentMethodCalled = methodCalledStack.peek();
		int currentMethodParamNum = currentMethodParamNumStack.peek();
		Object[] localSymbols = currentMethodCalled.getLocalSymbols().toArray();
		if (currentMethodCalled.getLevel() <= currentMethodParamNum) {
			report_error("Neispravan broj parametara pri pozivu metode " + currentMethodCalled.getName() + " na liniji "
					+ acSingleItem.getLine(), null);
		} else {
			if (!((Obj) localSymbols[currentMethodParamNum]).getType().equals(paramType))
				report_error("Prosledjeni parametar broj " + currentMethodParamNum
						+ " nije odgovarajuceg tipa na liniji " + acSingleItem.getLine(), null);
		}
		currentMethodParamNumStack.push(currentMethodParamNumStack.pop() + 1);
//		if (currentMethodCalled.getLevel() <= currentMethodParamNum) {
//			changed = true;
//			currentMethodCalled = methodCalledStack.empty() ? null : methodCalledStack.pop();
//		}
	}

	public void visit(NoActualParam noActualParam) {
		// currentMethodCalled = methodCalledStack.empty() ? null :
		// methodCalledStack.pop();
	}

	public void visit(AddExpr addExpr) {
		Struct te = addExpr.getExpr().struct;
		Struct t = addExpr.getTerm().struct;
		if (te.equals(t) && te == Tab.intType) {
			addExpr.struct = te;
		} else {
			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje.",
					null);
			addExpr.struct = Tab.noType;
		}
	}

	public void visit(NumberConst cnst) {
		cnst.struct = Tab.intType;
	}

	public void visit(BoolConst cnst) {
		cnst.struct = Tab.find("bool").getType();
	}

	public void visit(CharConst cnst) {
		cnst.struct = Tab.charType;
	}

	public void visit(ExprFactor exprFactor) {
		exprFactor.struct = exprFactor.getExpr().struct;
	}

	public void visit(Variable var) {
		if (!designatorQueue.isEmpty()) {
			Obj obj = var.getDesignator().obj;
			Struct currStruct = Tab.noType;
			while (!designatorQueue.isEmpty()) {
				DesignatorMulti top = designatorQueue.poll();
				if (top instanceof ParenDesignator) {
					currStruct = obj.getType().getElemType();
					obj = Tab.find(obj.getName());
				} else {
					DotDesignator dTop = (DotDesignator) top;
					Obj c = Tab.find(obj.getName());
					Collection<Obj> cObj;
					if (c.getType().getKind() == Struct.Array) {
						if (currStruct.getKind() != Struct.Class) {
							report_error("Mora da bude []", dTop);
						}
						cObj = c.getType().getElemType().getMembers();
					} else {
						cObj = c.getType().getMembers();
					}
					boolean found = false;
					for (Obj o : cObj) {
						if (o.getName().equals(dTop.getI1())) {
							found = true;
							currStruct = o.getType();
							obj = o;
//							if (!designatorQueue.isEmpty())
//								top = designatorQueue.poll();
							break;
						}
					}
					if (!found) {
						report_error("Nepostojece polje " + dTop.getI1(), dTop);
						break;
					}
				}
			}
			var.struct = currStruct;
		} else {
			var.struct = var.getDesignator().obj.getType();
		}
		designatorQueue.clear();

	}

	public void visit(DesignatorForAssign deForAssign) {
		if (!designatorQueue.isEmpty()) {
			Obj obj = deForAssign.getDesignator().obj;
			Struct currStruct = Tab.noType;
			while (!designatorQueue.isEmpty()) {
				DesignatorMulti top = designatorQueue.poll();
				if (top instanceof ParenDesignator) {
					currStruct = obj.getType().getElemType();
					obj = Tab.find(obj.getName());
				} else {
					DotDesignator dTop = (DotDesignator) top;
					Obj c = Tab.find(obj.getName());
					Collection<Obj> cObj;
					if (c.getType().getKind() == Struct.Array) {
						if (currStruct.getKind() != Struct.Class) {
							report_error("Mora da bude []", dTop);
						}
						cObj = c.getType().getElemType().getMembers();
					} else {
						cObj = c.getType().getMembers();
					}
					boolean found = false;
					for (Obj o : cObj) {
						if (o.getName().equals(dTop.getI1())) {
							found = true;
							currStruct = o.getType();
							obj = o;
//							if (!designatorQueue.isEmpty())
//								top = designatorQueue.poll();
							break;
						}
					}
					if (!found) {
						report_error("Nepostojece polje " + dTop.getI1(), dTop);
						break;
					}
				}
			}
			deForAssign.struct = currStruct;
		} else {
			deForAssign.struct = deForAssign.getDesignator().obj.getType();
		}
		designatorQueue.clear();

	}

	public void visit(ReturnStatementWithExpresion returnExpr) {
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : "
					+ "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije "
					+ currentMethod.getName(), null);
		}
	}

	@Override
	public void visit(RecordName recordName) {
		recordName.obj = Tab.insert(Obj.Type, recordName.getName(), new Struct(Struct.Class));
		Tab.openScope();
		report_info("Pronadjena deklaracija rekorda: " + recordName.getName(), recordName);
	}

	@Override
	public void visit(RecordDeclarations recordDeclarations) {
		Tab.chainLocalSymbols(recordDeclarations.getRecordName().obj.getType());
		Tab.closeScope();
		report_info("Zavrsena obrada klase: " + recordDeclarations.getRecordName().getName(), recordDeclarations);
	}

	public boolean passed() {
		return !errorDetected;
	}

}
