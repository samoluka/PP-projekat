package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	Type currentType;
	int nVars;

	Logger log = Logger.getLogger(getClass());
	private List<String> currDeclVar = new LinkedList<>();

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
		currDeclVar.add(varDefinition.getName());
	}

	public void visit(VarDeclItemList varDeclItemList) {
		int x = 2;
	}

	public void visit(VarDeclarations varDeclarations) {
		currentType = varDeclarations.getType();
		for (String varName : currDeclVar) {
			if (Tab.currentScope.findSymbol(varName) != null) {
				log.error("Greska na liniji " + varDeclarations.getLine() + ". Varijabla " + varName
						+ " je vec definisana");
				continue;
			}
			report_info("Deklarisana promenljiva " + varName, varDeclarations);
			Tab.insert(Obj.Var, varName, currentType.struct);
		}
		currDeclVar.clear();
	}

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
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
			} else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				type.struct = Tab.noType;
			}
		}
	}

	public void visit(MethodTypeNameWithType methodTypeNameWithType) {
		currentMethod = Tab.insert(Obj.Meth, methodTypeNameWithType.getMethodName(),
				methodTypeNameWithType.getType().struct);
		methodTypeNameWithType.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeNameWithType.getMethodName(), methodTypeNameWithType);
	}

	public void visit(MethodDeclaration MethodDeclaration) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + MethodDeclaration.getLine() + ": funkcija " + currentMethod.getName()
					+ " nema return iskaz!", null);
		}
		log.info("Zatvoren skoup funkcije");
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();

		returnFound = false;
		currentMethod = null;
	}

//	public void visit(Designator designator) {
//		Obj obj = Tab.find(designator.get());
//		if (obj == Tab.noObj) {
//			report_error("Greska na liniji " + designator.getLine() + " : ime " + designator.getName()
//					+ " nije deklarisano! ", null);
//		}
//		designator.obj = obj;
//	}

	public void visit(MethodCall funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
		} else {
			report_error("Greska na liniji " + funcCall.getLine() + " : ime " + func.getName() + " nije funkcija!",
					null);
			funcCall.struct = Tab.noType;
		}
	}

	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getFactor().struct;
	}

//	public void visit(TermExpr termExpr) {
//		termExpr.struct = termExpr.getTerm().struct;
//	}

//	public void visit(AddExpr addExpr) {
//		Struct te = addExpr.getExpr().struct;
//		Struct t = addExpr.getTerm().struct;
//		if (te.equals(t) && te == Tab.intType) {
//			addExpr.struct = te;
//		} else {
//			report_error("Greska na liniji " + addExpr.getLine() + " : nekompatibilni tipovi u izrazu za sabiranje.",
//					null);
//			addExpr.struct = Tab.noType;
//		}
//	}

	public void visit(NumberConst cnst) {
		cnst.struct = Tab.intType;
	}

	public void visit(Variable var) {
		var.struct = var.getDesignator().obj.getType();
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

//    public void visit(Assignment assignment){
//    	if(!assignment.getExpr().struct.assignableTo(assignment.getDesignator().obj.getType()))
//    		report_error("Greska na liniji " + assignment.getLine() + " : " + "nekompatibilni tipovi u dodeli vrednosti! ", null);
//    }

	public boolean passed() {
		return !errorDetected;
	}

}
