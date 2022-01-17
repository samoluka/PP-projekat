package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.AddMinus;
import rs.ac.bg.etf.pp1.ast.AddPlus;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignmentStatement;
import rs.ac.bg.etf.pp1.ast.DesignatorForAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorItemDec;
import rs.ac.bg.etf.pp1.ast.DesignatorItemInc;
import rs.ac.bg.etf.pp1.ast.DotDesignator;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameVoid;
import rs.ac.bg.etf.pp1.ast.MulDiv;
import rs.ac.bg.etf.pp1.ast.MulMod;
import rs.ac.bg.etf.pp1.ast.MulMul;
import rs.ac.bg.etf.pp1.ast.Mulop;
import rs.ac.bg.etf.pp1.ast.MulopTerm;
import rs.ac.bg.etf.pp1.ast.NegativeTermExpr;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NewFactorWithBrackets;
import rs.ac.bg.etf.pp1.ast.NumberConst;
import rs.ac.bg.etf.pp1.ast.PrintStatementWithoutNumConst;
import rs.ac.bg.etf.pp1.ast.ReturnStatementWithoutExpresion;
import rs.ac.bg.etf.pp1.ast.SingleDesignator;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	Logger log = Logger.getLogger(getClass());
	private boolean forced = false;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(PrintStatementWithoutNumConst printStmt) {
		Code.loadConst(5);
		Code.put(Code.print);
//		if (printStmt.getExpr().struct == Tab.intType) {
//			Code.loadConst(5);
//			Code.put(Code.print);
//		} else {
//			Code.loadConst(1);
//			Code.put(Code.bprint);
//		}
	}

	public void visit(NumberConst cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.obj.getType());
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

	@Override
	public void visit(DesignatorItemInc desInc) {
		Designator d = desInc.getDesignator();
		forced = true;
		d.traverseBottomUp(this);
		forced = false;
		Code.put(Code.const_1);
		Code.put(Code.add);
		if (d instanceof ArrayDesignator) {
			Code.put(Code.astore);
		} else {
			Code.store(d.obj);
		}
	}

	@Override
	public void visit(DesignatorItemDec desDec) {
		Designator d = desDec.getDesignator();
		forced = true;
		d.traverseBottomUp(this);
		forced = false;
		Code.put(Code.const_1);
		Code.put(Code.sub);
		if (d instanceof ArrayDesignator) {
			Code.put(Code.astore);
		} else {
			Code.store(d.obj);
		}
	}

	@Override
	public void visit(DesignatorAssignmentStatement designatorAssignmentStatement) {
		Designator d = designatorAssignmentStatement.getDesignatorForAssign().getDesignator();
		if (d instanceof ArrayDesignator) {
			Code.put(Code.astore);
		} else {
			Code.store(d.obj);
		}
	}

	@Override
	public void visit(SingleDesignator singleDesignator) {
		if ((singleDesignator.getParent() instanceof DesignatorItemInc
				|| singleDesignator.getParent() instanceof DesignatorItemDec) && !forced) {
			return;
		}
		if (!(singleDesignator.getParent() instanceof DesignatorForAssign)) {
			Code.load(singleDesignator.obj);
		}
	}

	@Override
	public void visit(DotDesignator dotDesignator) {
		if ((dotDesignator.getParent() instanceof DesignatorItemInc
				|| dotDesignator.getParent() instanceof DesignatorItemDec) && !forced) {
			return;
		}
		if (!(dotDesignator.getParent() instanceof DesignatorForAssign)) {
			Code.load(dotDesignator.obj);
		}
	}

	@Override
	public void visit(ArrayDesignator arrayDesignator) {
		if ((arrayDesignator.getParent() instanceof DesignatorItemInc
				|| arrayDesignator.getParent() instanceof DesignatorItemDec) && !forced) {
			return;
		}
		if (!(arrayDesignator.getParent() instanceof DesignatorForAssign)) {
			Code.put(Code.aload);
		}
	}

	@Override
	public void visit(NewFactor nFactor) {
		log.debug("new called with level = " + nFactor.obj.getType().getNumberOfFields());
		log.debug("class is " + nFactor.obj.getType().getKind());

		String className = nFactor.getType().getTypeName();
		log.debug("class name is " + nFactor.getType().getTypeName());
		int vTableAddress = nFactor.obj.getAdr();

		Code.put(Code.new_);
		Code.put2((nFactor.obj.getType().getNumberOfFields() + 1) * 4);
		// Code.put(Code.dup);
		// Code.loadConst(vTableAddress); // v_table value
		// Code.put(Code.putfield);
		// Code.put2(0);
	}

	@Override
	public void visit(NewFactorWithBrackets nArray) {
		Code.put(Code.newarray);
		Code.put(1);
	}
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
//)		Code.put(Code.pop);
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

	@Override
	public void visit(NegativeTermExpr negativeTermExpr) {
		Code.loadConst(-1);
		Code.put(Code.mul);
	}

	@Override
	public void visit(AddExpr exprOperand) {
		if (exprOperand.getAddop() instanceof AddPlus)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}

	@Override
	public void visit(MulopTerm exprOperand) {
		if (exprOperand.getMulop() instanceof MulMul)
			Code.put(Code.mul);
		if (exprOperand.getMulop() instanceof MulDiv)
			Code.put(Code.div);
		if (exprOperand.getMulop() instanceof MulMod)
			Code.put(Code.rem);
	}

}
