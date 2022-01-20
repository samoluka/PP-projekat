package rs.ac.bg.etf.pp1;

import java.awt.Component;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.ActualParamSingleItem;
import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.AssignmentDeclaration;
import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.ClassConstructorDeclaration;
import rs.ac.bg.etf.pp1.ast.ClassDeclarations;
import rs.ac.bg.etf.pp1.ast.ClassDeclarationsExtends;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.ConstantBoolConst;
import rs.ac.bg.etf.pp1.ast.ConstantCharConst;
import rs.ac.bg.etf.pp1.ast.ConstantNumberConst;
import rs.ac.bg.etf.pp1.ast.ConstructorHeader;
import rs.ac.bg.etf.pp1.ast.Definition;
import rs.ac.bg.etf.pp1.ast.DefinitionArray;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignmentStatement;
import rs.ac.bg.etf.pp1.ast.DesignatorForAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorForMethodCall;
import rs.ac.bg.etf.pp1.ast.DesignatorItemDec;
import rs.ac.bg.etf.pp1.ast.DesignatorItemFuncCallWithParam;
import rs.ac.bg.etf.pp1.ast.DesignatorItemInc;
import rs.ac.bg.etf.pp1.ast.DesignatorMulti;
import rs.ac.bg.etf.pp1.ast.DotDesignator;
import rs.ac.bg.etf.pp1.ast.Expr;
import rs.ac.bg.etf.pp1.ast.ExprFactor;
import rs.ac.bg.etf.pp1.ast.FormalParametherListItem;
import rs.ac.bg.etf.pp1.ast.FormalParamsList;
import rs.ac.bg.etf.pp1.ast.GotoStatement;
import rs.ac.bg.etf.pp1.ast.MethodCall;
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodNameDesignator;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameVoid;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameWithType;
import rs.ac.bg.etf.pp1.ast.MulopTerm;
import rs.ac.bg.etf.pp1.ast.MultiCondFact;
import rs.ac.bg.etf.pp1.ast.NegativeTermExpr;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NewFactorWithBrackets;
import rs.ac.bg.etf.pp1.ast.NoActualParam;
import rs.ac.bg.etf.pp1.ast.NumberConst;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.RecordDeclarations;
import rs.ac.bg.etf.pp1.ast.RecordName;
import rs.ac.bg.etf.pp1.ast.RelEqual;
import rs.ac.bg.etf.pp1.ast.RelNEqual;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.ReturnStatementWithExpresion;
import rs.ac.bg.etf.pp1.ast.SingleCondFact;
import rs.ac.bg.etf.pp1.ast.SingleDesignator;
import rs.ac.bg.etf.pp1.ast.SingleTerm;
import rs.ac.bg.etf.pp1.ast.StatementLabel;
import rs.ac.bg.etf.pp1.ast.SuperStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermExpr;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VarDeclarations;
import rs.ac.bg.etf.pp1.ast.VarListClassNonEmpty;
import rs.ac.bg.etf.pp1.ast.Variable;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

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
	static int nVars;

	Logger log = Logger.getLogger(getClass());
	private List<Definition> currDeclVar = new LinkedList<>();
	private List<DefinitionArray> currDeclVarArray = new LinkedList<>();
	private List<Obj> allClasses = new LinkedList<>();
	private Queue<DesignatorMulti> designatorQueue = new LinkedList<>();
	private boolean changed = false;
	private Type extendClassType = null;
	private List<String> allLabels = new LinkedList<>();
	private boolean foundClassMethodCall = false;
	private Obj currClass = null;
	private boolean constructorFound = false;
	private Stack<Obj> currClassInsideDesignatorStack = new Stack<>();
	private List<GotoStatement> allGotoStatements = new LinkedList<>();

	static List<Byte> MethodTable = new ArrayList<>();

	void addWordToStaticData(int value, int address) {
		MethodTable.add(Byte.valueOf((byte) Code.const_));
		MethodTable.add(Byte.valueOf((byte) ((value >> 16) >> 8)));
		MethodTable.add(Byte.valueOf((byte) (value >> 16)));
		MethodTable.add(Byte.valueOf((byte) (value >> 8)));
		MethodTable.add(Byte.valueOf((byte) value));
		MethodTable.add(Byte.valueOf((byte) Code.putstatic));
		MethodTable.add(Byte.valueOf((byte) (address >> 8)));
		MethodTable.add(Byte.valueOf((byte) address));
	}

	void addNameTerminator() {
		addWordToStaticData(-1, Code.dataSize++);
	}

	void addTableTerminator() {
		addWordToStaticData(-2, Code.dataSize++);
	}

	void addFunctionAddress(int functionAddress) {
		addWordToStaticData(functionAddress, Code.dataSize++);
	}

	void addFunctionEntry(String name, int functionAddressInCodeBuffer) {
		for (int j = 0; j < name.length(); j++) {
			addWordToStaticData((int) (name.charAt(j)), Code.dataSize++);
		}
		addNameTerminator();
		addFunctionAddress(functionAddressInCodeBuffer);
	}

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
		Tab.insert(Obj.Fld, "VTP", new Struct(Struct.Int));
		report_info("Pronadjena deklaracija klase: " + className.getClassName(), className);
		allClasses.add(className.obj);
		currClass = className.obj;
	}

	@Override
	public void visit(ClassDeclarations classDeclarations) {
		if (!constructorFound) {
			report_info("Za klasu: " + classDeclarations.getClassName().getClassName()
					+ " nije pronadjen konstruktor, dodaje se prazan podrazumevani", null);
			Obj constructor = Tab.insert(Obj.Meth, classDeclarations.getClassName().getClassName(), Tab.noType);
			Tab.openScope();
			Tab.insert(Obj.Var, "this", classDeclarations.getClassName().obj.getType());
			Tab.chainLocalSymbols(constructor);
			Tab.closeScope();
		}
		constructorFound = false;
//		for (Obj o : Tab.currentScope.getLocals().symbols()) {
//			if (o.getKind() == Obj.Meth) {
//				addFunctionEntry(o.getName(), o.getAdr());
//				Tab.insert(Obj.Meth, o.getName(), o.getType());
//			}
//		}
		Tab.chainLocalSymbols(classDeclarations.getClassName().obj.getType());
		Tab.closeScope();
		if (extendClassType != null) {
			classDeclarations.getClassName().obj.getType().setElementType(extendClassType.struct);
			extendClassType = null;
		}
		currClass = null;
		report_info("Zavrsena obrada klase: " + classDeclarations.getClassName().getClassName(), classDeclarations);
	}

	@Override
	public void visit(ClassDeclarationsExtends classExtends) {
		extendClassType = classExtends.getType();
		Obj extendClassObj = Tab.find(extendClassType.getTypeName());
		if (!allClasses.contains(extendClassObj)) {
			report_error("Nije moguce izvodjenje iz tipa: " + extendClassObj.getName(), extendClassType);
		}
		if (Tab.noObj != extendClassObj) {
			Collection<Obj> listOfSimbols = extendClassObj.getType().getMembers();
			for (Obj simbol : listOfSimbols) {
				if (!simbol.getName().equals("VTP"))
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
			Tab.insert(Obj.Fld, var.getName(), var.struct);
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
		Obj foundMethod = Tab.find(methodTypeNameWithType.getMethodName());
		if (foundMethod != Tab.noObj) {
			Collection<Obj> cObj = null;
			if (extendClassType != null)
				cObj = extendClassType.struct.getMembers();
			boolean found = false;
			for (Obj c : cObj) {
				if (c.getName().equals(methodTypeNameWithType.getMethodName())) {
					report_info("Redefinisana metoda: " + c.getName(), methodTypeNameWithType);
					found = true;
				}
			}
			if (!found || foundMethod.getLevel() == foundMethod.getLocalSymbols().size())
//			if (!found)
				report_error("Greska na liniji " + methodTypeNameWithType.getLine() + ". Metoda "
						+ methodTypeNameWithType.getMethodName() + " je vec definisana", null);
		}
		currentMethod = Tab.insert(Obj.Meth, methodTypeNameWithType.getMethodName(),
				methodTypeNameWithType.getType().struct);
		methodTypeNameWithType.obj = currentMethod;
		Tab.openScope();
		if (currClass != null) {
			Tab.insert(Obj.Var, "this", currClass.getType());
		}
		report_info("Obradjuje se funkcija " + methodTypeNameWithType.getMethodName(), methodTypeNameWithType);
	}

	public void visit(MethodTypeNameVoid MethodTypeNameVoid) {
		Obj foundMethod = Tab.find(MethodTypeNameVoid.getMethodName());
		if (foundMethod != Tab.noObj) {
			Collection<Obj> cObj = null;
			if (extendClassType != null)
				cObj = extendClassType.struct.getMembers();
			boolean found = false;
			for (Obj c : cObj) {
				if (c.getName().equals(MethodTypeNameVoid.getMethodName())) {
					report_info("Redefinisana metoda: " + c.getName(), MethodTypeNameVoid);
					found = true;
				}
			}
			if (!found || foundMethod.getLevel() == foundMethod.getLocalSymbols().size())
//			if (!found)
				report_error("Greska na liniji " + MethodTypeNameVoid.getLine() + ". Metoda "
						+ MethodTypeNameVoid.getMethodName() + " je vec definisana", null);
		}
		currentMethod = Tab.insert(Obj.Meth, MethodTypeNameVoid.getMethodName(), Tab.noType);
		currentMethod.setFpPos(-1);
		currentMethodParamNumStack.push(0);
		MethodTypeNameVoid.obj = currentMethod;
		Tab.openScope();
		if (currClass != null) {
			Tab.insert(Obj.Var, "this", currClass.getType());
		}
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

		MethodDeclaration.obj = MethodDeclaration.getMethodTypeName().obj;

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
	public void visit(SingleDesignator singleDesignator) {
		Obj obj = Tab.find(singleDesignator.getName());
		if (obj.getKind() == Obj.Meth && currClass != null && extendClassType != null
				&& obj.getLevel() != obj.getLocalSymbols().size()) {
			Collection<Obj> cObj = extendClassType.struct.getMembers();
			for (Obj o : cObj) {
				if (o.getName().equals(singleDesignator.getName())) {
					obj = o;
					break;
				}
			}
		}
		if (obj == Tab.noObj) {
			report_error("Greska na liniji " + singleDesignator.getLine() + " : ime " + singleDesignator.getName()
					+ " nije deklarisano! ", null);
		}
		if (obj.getType().getKind() == Struct.Class)
			currClassInsideDesignatorStack.push(obj);
		if (!(singleDesignator.getParent() instanceof Designator))
			if (!currClassInsideDesignatorStack.empty())
				currClassInsideDesignatorStack.pop();
		singleDesignator.obj = obj;
	}

	@Override
	public void visit(DotDesignator dotDesignator) {
		dotDesignator.obj = Tab.noObj;
		Collection<Obj> cObj = null;
		Obj currClassInsideDesignator = null;
		if (!currClassInsideDesignatorStack.empty())
			currClassInsideDesignator = currClassInsideDesignatorStack.peek();
		if (currClassInsideDesignator.getName().equals("this")) {
			cObj = Tab.currentScope().getOuter().getLocals().symbols();
		} else {
			cObj = currClassInsideDesignator.getType().getMembers();
		}
		for (Obj o : cObj) {
			if (o.getName().equals(dotDesignator.getName())) {
				dotDesignator.obj = o;
				if (dotDesignator.obj.getType().getKind() == Struct.Class) {
					if (!currClassInsideDesignatorStack.empty())
						currClassInsideDesignatorStack.pop();
					currClassInsideDesignatorStack.push(dotDesignator.obj);
				}
				if (!(dotDesignator.getParent() instanceof Designator))
					if (!currClassInsideDesignatorStack.empty())
						currClassInsideDesignatorStack.pop();
				break;
			}
		}
	}

	@Override
	public void visit(ArrayDesignator arrayDesignator) {
		arrayDesignator.obj = Tab.noObj;
		String name = "";
		Obj currClassInsideDesignator = null;
		if (!currClassInsideDesignatorStack.empty())
			currClassInsideDesignator = currClassInsideDesignatorStack.peek();
		if (arrayDesignator.getDesignator() instanceof DotDesignator) {
			name = ((DotDesignator) arrayDesignator.getDesignator()).getName();
		} else {
			name = ((SingleDesignator) arrayDesignator.getDesignator()).getName();
		}
		if (currClassInsideDesignator != null) {
			Collection<Obj> cObj = currClassInsideDesignator.getType().getMembers();
			for (Obj o : cObj) {
				if (o.getName().equals(name)) {
					arrayDesignator.obj = new Obj(o.getType().getElemType().getKind(), "", o.getType().getElemType());
					break;
				}
			}
		} else {
			arrayDesignator.obj = Tab.find(name);
			arrayDesignator.obj = new Obj(arrayDesignator.obj.getType().getElemType().getKind(), "",
					arrayDesignator.obj.getType().getElemType());
		}
		if (arrayDesignator.obj.getType().getKind() == Struct.Class) {
			if (!currClassInsideDesignatorStack.empty())
				currClassInsideDesignatorStack.pop();
			currClassInsideDesignatorStack.push(arrayDesignator.obj);
		}
		if (!(arrayDesignator.getParent() instanceof Designator))
			if (!currClassInsideDesignatorStack.empty())
				currClassInsideDesignatorStack.pop();
	}

	@Override
	public void visit(DesignatorAssignmentStatement designatorAssignmentStatement) {
		Designator leftDesign = designatorAssignmentStatement.getDesignatorForAssign().getDesignator();
		Struct left = designatorAssignmentStatement.getDesignatorForAssign().struct;
		Struct right = designatorAssignmentStatement.getExpr().struct;
		if (leftDesign != null && leftDesign.obj != null && leftDesign.obj.getKind() == Obj.Con) {
			report_error("Leva strana operatora dodele ne sme da bude konsanta", designatorAssignmentStatement);
		}
		if (!right.assignableTo(left) && !isExtended(right, left))
			report_error("Greska na liniji " + designatorAssignmentStatement.getLine() + " : "
					+ "nekompatibilni tipovi u dodeli vrednosti! ", null);
	}

	private boolean isExtended(Struct right, Struct left) {
		while (right.getElemType() != null) {
			if (right.getElemType().assignableTo(left))
				return true;
			right = right.getElemType();
		}
		return false;
	}

	public void visit(DesignatorForMethodCall dsForMethodCall) {
		dsForMethodCall.obj = dsForMethodCall.getDesignator().obj;
		methodCalledStack.push(dsForMethodCall.getDesignator().obj);
		// currentMethodParamNumStack.push(foundClassMethodCall ? -1 : 0);
		currentMethodParamNumStack.push(0);
		changed = false;
	}

	@Override
	public void visit(MethodNameDesignator methodNameDesignator) {
		methodNameDesignator.obj = methodNameDesignator.getDesignator().obj;
		methodCalledStack.push(methodNameDesignator.getDesignator().obj);
		// currentMethodParamNumStack.push(foundClassMethodCall ? -1 : 0);
		currentMethodParamNumStack.push(0);
		changed = false;
	}

	@Override
	public void visit(DesignatorItemFuncCallWithParam dfParam) {
		currentMethodCalled = methodCalledStack.pop();
		int currentMethodParamNum = Math.abs(currentMethodParamNumStack.pop());
		int isClassMethod = 0;
		if (currentMethodCalled.getLevel() > 0)
			isClassMethod = ((Obj) currentMethodCalled.getLocalSymbols().toArray()[0]).getName().equals("this") ? 1 : 0;
		if ((dfParam.getActualPars() instanceof NoActualParam && currentMethodCalled.getLevel() - isClassMethod > 0)
				|| (!(dfParam.getActualPars() instanceof NoActualParam)
						&& currentMethodCalled.getLevel() - isClassMethod == 0)
				|| (currentMethodParamNum != currentMethodCalled.getLevel() - isClassMethod)) {
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
		int currentMethodParamNum = Math.abs(currentMethodParamNumStack.pop());
		int isClassMethod = 0;
		if (currentMethodCalled.getLevel() > 0)
			isClassMethod = ((Obj) currentMethodCalled.getLocalSymbols().toArray()[0]).getName().equals("this") ? 1 : 0;
		if ((funcCall.getActualPars() instanceof NoActualParam && currentMethodCalled.getLevel() - isClassMethod > 0)
				|| (!(funcCall.getActualPars() instanceof NoActualParam)
						&& currentMethodCalled.getLevel() - isClassMethod == 0)
				|| (currentMethodParamNum != currentMethodCalled.getLevel() - isClassMethod)) {
			report_error("Broj prosledjenih parametara se ne slaze sa brojem definisanih parametara za metodu: "
					+ currentMethodCalled.getName(), funcCall);
		}
		if (Obj.Meth == currentMethodCalled.getKind()) {
			report_info(
					"Pronadjen poziv funkcije " + currentMethodCalled.getName() + " na liniji " + funcCall.getLine(),
					null);
			funcCall.obj = currentMethodCalled;
		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + currentMethodCalled.getName()
					+ " nije funkcija!", null);
			funcCall.obj = Tab.noObj;
		}
		// currentMethodCalled = methodCalledStack.empty() ? null :
		// methodCalledStack.pop();
		currentMethodParamNum = 0;
	}

	@Override
	public void visit(MulopTerm mulopTerm) {
		Struct rightT = mulopTerm.getFactor().obj.getType();
		Struct leftT = mulopTerm.getTerm().struct;
		if (leftT.equals(rightT) && leftT == Tab.intType) {
			mulopTerm.struct = leftT;
		} else {
			report_error("Greska na liniji " + mulopTerm.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje.",
					null);
			mulopTerm.struct = Tab.noType;
		}
	}

	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getFactor().obj.getType();
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
		int currentMethodParamNum = Math.abs(currentMethodParamNumStack.peek());
		Object[] localSymbols = currentMethodCalled.getLocalSymbols().toArray();
		int isClassMethod = 0;
		if (currentMethodCalled.getLevel() > 0)
			isClassMethod = ((Obj) currentMethodCalled.getLocalSymbols().toArray()[0]).getName().equals("this") ? 1 : 0;
		if (currentMethodCalled.getLevel() - isClassMethod <= currentMethodParamNum) {
			report_error("Neispravan broj parametara pri pozivu metode " + currentMethodCalled.getName() + " na liniji "
					+ acSingleItem.getLine(), null);
		} else {
			if (!((Obj) localSymbols[currentMethodParamNum + isClassMethod]).getType().equals(paramType))
				if (currentMethodCalled.getName().equals("len")) {
					if (paramType.getKind() != Struct.Array) {
						report_error("Prosledjeni parametar broj " + currentMethodParamNum
								+ " nije odgovarajuceg tipa na liniji " + acSingleItem.getLine(), null);
					}
				} else
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

	@Override
	public void visit(NewFactor factor) {
		Struct s = factor.getType().struct;
		if (s.getKind() != Struct.Class) {
			report_error("Tip operatora new mora da bude klasa", factor);
		}
		// factor.struct = s;
		// ovu liniju sam izmenio
		factor.obj = Tab.find(factor.getType().getTypeName());
	}

	@Override
	public void visit(NewFactorWithBrackets factorBrackets) {
		Struct s = factorBrackets.getType().struct;
		if (factorBrackets.getExpr().struct != Tab.intType) {
			report_error("Tip izraza unutar [] mora biti int", factorBrackets.getExpr());
		}
		// factorBrackets.struct = new Struct(Struct.Array, s);
		factorBrackets.obj = new Obj(Obj.Var, "", new Struct(Struct.Array, s));
	}

	public void visit(NumberConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.intType);
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(BoolConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.find("bool").getType());
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(CharConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.charType);
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(ConstantNumberConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.intType);
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(ConstantBoolConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.find("bool").getType());
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(ConstantCharConst cnst) {
		cnst.obj = new Obj(Obj.Con, "", Tab.charType);
		cnst.obj.setAdr(cnst.getVal());
	}

	public void visit(ExprFactor exprFactor) {
		exprFactor.obj = new Obj(Obj.NO_VALUE, "", exprFactor.getExpr().struct);
//		exprFactor.struct = exprFactor.getExpr().struct;
	}

	public void visit(Variable var) {
//		if (var.getDesignator() instanceof MultiDesignator) {
//			var.obj = ((MultiDesignator) var.getDesignator()).getDesignatorMultiList().obj;
//		} else {
//			var.obj = var.getDesignator().obj;
//		}
		var.obj = var.getDesignator().obj;
	}

	public void visit(DesignatorForAssign deForAssign) {
		deForAssign.struct = deForAssign.getDesignator().obj.getType();
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

	public void visit(AssignmentDeclaration assignmentDeclaration) {
		Struct left = currentType.struct;
		Struct right = assignmentDeclaration.getFactorForConst().obj.getType();
		if (Tab.find(assignmentDeclaration.getVarName()) != Tab.noObj) {
			report_error("Ime konstante: " + assignmentDeclaration.getVarName() + " se vec koristi",
					assignmentDeclaration);
		}
		if (!right.assignableTo(left)) {
			report_error("Nekompatibilni tipovi pri izrazu dodele", assignmentDeclaration);
		} else {
			Obj con = Tab.insert(Obj.Con, assignmentDeclaration.getVarName(), right);
			con.setAdr(assignmentDeclaration.getFactorForConst().obj.getAdr());
			report_info("Deklarisana konsanta: " + assignmentDeclaration.getVarName(), assignmentDeclaration);
		}
	}

	@Override
	public void visit(MultiCondFact mcFact) {
		mcFact.obj = Tab.noObj;
		Expr left = mcFact.getExpr();
		Expr right = mcFact.getExpr1();
		Relop op = mcFact.getRelop();
		Boolean error = false;
		if (!left.struct.compatibleWith(right.struct)) {
			report_error("Tipovi uslova moraju da budu kompatibilni", mcFact);
			error = true;
		}
		if (!(op instanceof RelEqual || op instanceof RelNEqual)
				&& (left.struct.getKind() == Struct.Class || left.struct.getKind() == Struct.Array
						|| right.struct.getKind() == Struct.Class || right.struct.getKind() == Struct.Array)) {
			report_error("Tip Class i tip Array mogu da se uporedjuju samo operatorima == i !=", op);
			error = true;
		}
		if (!error)
			mcFact.obj = new Obj(Obj.NO_VALUE, "", Tab.find("bool").getType());
	}

	@Override
	public void visit(SingleCondFact scFact) {
		Expr expr = scFact.getExpr();
		if (expr.struct != Tab.find("bool").getType()) {
			report_error("Tip izraza unutar uslovnog iskaza mora da bude boolean", expr);
			scFact.obj = Tab.noObj;
		} else {
			scFact.obj = new Obj(Obj.NO_VALUE, "", Tab.find("bool").getType());
		}
	}

	@Override
	public void visit(rs.ac.bg.etf.pp1.ast.Label label) {
		if (label.getParent() instanceof StatementLabel) {
			if (allLabels.contains(label.getName())) {
				report_error("Labela sa nazivom: " + label.getName() + " je vec definisana", label);
			} else {
				label.obj = Tab.insert(Obj.Elem, label.getName(), Tab.noType);
				allLabels.add(label.getName());
				for (GotoStatement g : allGotoStatements) {
					if (g.getLabel().getName().equals(label.getName())) {
						g.getLabel().obj = label.obj;
					}
				}
				report_info("Pronadjena definicija lebele sa nazivom: " + label.getName(), label);
			}
		}
	}

	@Override
	public void visit(GotoStatement gotoStatement) {
		gotoStatement.getLabel().obj = Tab.find(gotoStatement.getLabel().getName());
		allGotoStatements.add(gotoStatement);
	}

	@Override
	public void visit(SuperStatement superStatement) {
		if (currClass == null || extendClassType == null) {
			report_error("Ne postoji roditeljska metoda za poziv", superStatement);
		} else {
			if (currentMethod.getName().equals(currClass.getName())) {
				report_info("Pronadjen poziv konstustruktora roditeljske klase: " + extendClassType.getTypeName(),
						superStatement);
				for (Obj o : extendClassType.struct.getMembers()) {
					if (o.getName().equals(extendClassType.getTypeName())) {
						superStatement.getSuperStart().obj = o;
						return;
					}
				}
				return;
			}
			Collection<Obj> cObj = extendClassType.struct.getMembers();
			for (Obj o : cObj) {
				if (o.getName().equals(currentMethod.getName())) {
					report_info("Pronadjen poziv roditeljske metode: " + currentMethod.getName(), superStatement);
					superStatement.getSuperStart().obj = o;
					return;
				}
			}
			report_error("Roditeljska klasa ne sadrzi metodu: " + currentMethod.getName(), superStatement);

		}

	}

	@Override
	public void visit(DesignatorItemInc desInc) {
		if (desInc.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Operacija inkrementiranja je primenljiva samo na tipu int", desInc);
		}
	}

	@Override
	public void visit(DesignatorItemDec desDec) {
		if (desDec.getDesignator().obj.getType().getKind() != Struct.Int) {
			report_error("Operacija dekrementiranja je primenljiva samo na tipu int", desDec);
		}
	}

	public void visit(ConstructorHeader cHeader) {
		if (!currClass.getName().equals(cHeader.getI1())) {
			report_error("Naziv konstruktora mora da se poklopi sa nazivom klase", cHeader);
		}
		Obj foundMethod = Tab.find(cHeader.getI1());
		if (foundMethod != Tab.noObj && foundMethod.getKind() == Obj.Meth) {
			report_error(
					"Greska na liniji " + cHeader.getLine() + ". Konstruktor " + cHeader.getI1() + " je vec definisana",
					cHeader);
		}
		currentMethod = Tab.insert(Obj.Meth, cHeader.getI1(), Tab.noType);
		cHeader.obj = currentMethod;
		Tab.openScope();
		if (currClass != null) {
			Tab.insert(Obj.Var, "this", currClass.getType());
			constructorFound = true;
		}
		report_info("Obradjuje se konstruktor " + cHeader.getI1(), cHeader);
	}

	public void visit(ClassConstructorDeclaration ccDeclaration) {
		log.info("Zatvoren skoup funkcije");
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		returnFound = false;
		currentMethod = null;
	}

	public boolean passed() {
		return !errorDetected;
	}

}
