package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameVoid;
import rs.ac.bg.etf.pp1.ast.NumberConst;
import rs.ac.bg.etf.pp1.ast.PrintStatementWithoutNumConst;
import rs.ac.bg.etf.pp1.ast.ReturnStatementWithoutExpresion;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStatementWithoutNumConst printStmt) {
		if (printStmt.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	public void visit(NumberConst cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getVal());

		Code.load(con);
	}

	public void visit(MethodTypeNameVoid methodTypeName) {

		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}

	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

//	public void visit(Assignment assignment) {
//		Code.store(assignment.getDesignator().obj);
//	}
//
//	public void visit(Designator designator) {
//		SyntaxNode parent = designator.getParent();
//
//		if (Assignment.class != parent.getClass() && FuncCall.class != parent.getClass()
//				&& ProcCall.class != parent.getClass()) {
//			Code.load(designator.obj);
//		}
//	}

//	public void visit(FuncCall funcCall) {
//		Obj functionObj = funcCall.getDesignator().obj;
//		int offset = functionObj.getAdr() - Code.pc;
//		Code.put(Code.call);
//
//		Code.put2(offset);
//	}
//
//	public void visit(ProcCall procCall) {
//		Obj functionObj = procCall.getDesignator().obj;
//		int offset = functionObj.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
//		if (procCall.getDesignator().obj.getType() != Tab.noType) {
//			Code.put(Code.pop);
//		}
//	}

//	public void visit(ReturnExpr returnExpr) {
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//	}

	public void visit(ReturnStatementWithoutExpresion returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

//	public void visit(AddExpr addExpr) {
//		Code.put(Code.add);
//	}
}