package rs.ac.bg.etf.pp1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.AddPlus;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.ConditionList;
import rs.ac.bg.etf.pp1.ast.ConditionTermList;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignmentStatement;
import rs.ac.bg.etf.pp1.ast.DesignatorForAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorForMethodCall;
import rs.ac.bg.etf.pp1.ast.DesignatorItemDec;
import rs.ac.bg.etf.pp1.ast.DesignatorItemFuncCallWithParam;
import rs.ac.bg.etf.pp1.ast.DesignatorItemInc;
import rs.ac.bg.etf.pp1.ast.DotDesignator;
import rs.ac.bg.etf.pp1.ast.ElseStatementStatement;
import rs.ac.bg.etf.pp1.ast.GotoStatement;
import rs.ac.bg.etf.pp1.ast.IfPart;
import rs.ac.bg.etf.pp1.ast.Label;
import rs.ac.bg.etf.pp1.ast.MethodCall;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodDeclaration;
import rs.ac.bg.etf.pp1.ast.MethodNameDesignator;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameVoid;
import rs.ac.bg.etf.pp1.ast.MethodTypeNameWithType;
import rs.ac.bg.etf.pp1.ast.MulDiv;
import rs.ac.bg.etf.pp1.ast.MulMod;
import rs.ac.bg.etf.pp1.ast.MulMul;
import rs.ac.bg.etf.pp1.ast.MulopTerm;
import rs.ac.bg.etf.pp1.ast.MultiCondFact;
import rs.ac.bg.etf.pp1.ast.NegativeTermExpr;
import rs.ac.bg.etf.pp1.ast.NewFactor;
import rs.ac.bg.etf.pp1.ast.NewFactorWithBrackets;
import rs.ac.bg.etf.pp1.ast.NoElseStatement;
import rs.ac.bg.etf.pp1.ast.NumberConst;
import rs.ac.bg.etf.pp1.ast.PrintStatementWithNumConst;
import rs.ac.bg.etf.pp1.ast.PrintStatementWithoutNumConst;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.RelEqual;
import rs.ac.bg.etf.pp1.ast.RelGEqual;
import rs.ac.bg.etf.pp1.ast.RelGREATER;
import rs.ac.bg.etf.pp1.ast.RelLEqual;
import rs.ac.bg.etf.pp1.ast.RelLess;
import rs.ac.bg.etf.pp1.ast.RelNEqual;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.ReturnStatementWithExpresion;
import rs.ac.bg.etf.pp1.ast.ReturnStatementWithoutExpresion;
import rs.ac.bg.etf.pp1.ast.SingleConditionList;
import rs.ac.bg.etf.pp1.ast.SingleConditionTermList;
import rs.ac.bg.etf.pp1.ast.SingleDesignator;
import rs.ac.bg.etf.pp1.ast.StatementLabel;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.ast.IfStart;
import rs.ac.bg.etf.pp1.ast.IfHeader;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	Logger log = Logger.getLogger(getClass());
	private boolean forced = false;
	private Obj lastClassObj;
	private Obj thisObj;
	private String imeLabele = "";
	private int fixuUpPc = 0;
	private HashMap<String, Integer> labelPcMap = new HashMap<>();
	private HashMap<String, LinkedList<Integer>> labelFixUpMap = new HashMap<>();
	private boolean returnFound = false;
	private List<Integer> conditionFixupListForFalse = new LinkedList<>();
	private List<Integer> conditionFixupListForElse = new LinkedList<>();
	private List<Integer> conditionFixupListForTrue = new LinkedList<>();
	private List<Integer> condtionFixupListForAnd = new LinkedList<>();
	private List<Integer> conditionFixupForOr = new LinkedList<>();

	private Stack<List<Integer>> stackConditionFixupListForFalse = new Stack<>();
	private Stack<List<Integer>> stackConditionFixupListForElse = new Stack<>();
	private Stack<List<Integer>> stackConditionFixupListForTrue = new Stack<>();
	private Stack<List<Integer>> stackCondtionFixupListForAnd = new Stack<>();
	private Stack<List<Integer>> stackConditionFixupForOr = new Stack<>();

	public int getMainPc() {
		return mainPc;
	}

	@Override
	public void visit(PrintStatementWithNumConst printStmt) {
		switch (printStmt.getExpr().struct.getKind()) {
		case Struct.Int:
		case Struct.Bool:
			Code.load(printStmt.getFactorForConst().obj);
			Code.put(Code.print);
			break;
		default:
			Code.load(printStmt.getFactorForConst().obj);
			Code.put(Code.bprint);
			break;
		}
	}

	@Override
	public void visit(PrintStatementWithoutNumConst printStmt) {
		switch (printStmt.getExpr().struct.getKind()) {
		case Struct.Int:
		case Struct.Bool:
			Code.put(Code.const_5);
			Code.put(Code.print);
			break;
		default:
			Code.put(Code.const_1);
			Code.put(Code.bprint);
			break;
		}
	}

	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignator().obj.getType() == Tab.intType) {
			Code.put(Code.read);
			Code.store(readStatement.getDesignator().obj);
		} else {
			Code.put(Code.bread);
			Code.store(readStatement.getDesignator().obj);
		}
	}

	public void visit(NumberConst cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.obj.getType());
		con.setLevel(0);
		con.setAdr(cnst.getVal());
		Code.load(con);
	}

	public void visit(CharConst cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.obj.getType());
		con.setLevel(0);
		con.setAdr(cnst.getVal());
		Code.load(con);
	}

	public void visit(BoolConst cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.obj.getType());
		con.setLevel(0);
		con.setAdr(cnst.getVal());

		Code.load(con);
	}

	public void visit(MethodDeclaration methodDeclaration) {
		if (!returnFound && methodDeclaration.obj.getType() == Tab.noType) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		returnFound = false;
	}

	public void visit(MethodTypeNameVoid methodTypeName) {

		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		int varCnt = methodTypeName.obj.getLocalSymbols().size();

		int fpCnt = methodTypeName.obj.getLevel();

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt);
		Code.put(varCnt);

	}

	public void visit(MethodTypeNameWithType methodTypeName) {

		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		int varCnt = methodTypeName.obj.getLocalSymbols().size();

		int fpCnt = methodTypeName.obj.getLevel();

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt);
		Code.put(varCnt);

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
		if (singleDesignator.obj.getKind() == Obj.Fld) {
			Code.put(Code.load_n + 0);
		}
		if (singleDesignator.getParent() instanceof ReadStatement) {
			return;
		}
		if (singleDesignator.getParent() instanceof MethodNameDesignator) {
			return;
		}
		if (singleDesignator.getParent() instanceof DesignatorForMethodCall) {
			return;
		}
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
		if (dotDesignator.getParent() instanceof ReadStatement) {
			return;
		}
		if (dotDesignator.getParent() instanceof MethodNameDesignator) {
			return;
		}
		if (dotDesignator.getParent() instanceof DesignatorForMethodCall) {
			return;
		}
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
//		ovo verovatno nikad ne moze da se desi
//		if (arrayDesignator.getParent() instanceof MethodNameDesignator) {
//			return;
//		}
//		if (arrayDesignator.getParent() instanceof DesignatorForMethodCall) {
//			return;
//		}
//		if (arrayDesignator.getParent() instanceof ReadStatement) {
//			return;
//		}
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

	public void visit(MethodCall methodCall) {
		Obj functionObj = methodCall.getDesignatorForMethodCall().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
//		if (functionObj.getType() != Tab.noType) {
//			Code.put(Code.pop);
//		}
	}

	public void visit(DesignatorItemFuncCallWithParam diFunCall) {
		thisObj = lastClassObj;
		Obj functionObj = diFunCall.getMethodNameDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if (functionObj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}

	public void visit(ReturnStatementWithExpresion returnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		thisObj = null;
		returnFound = true;
	}

	public void visit(ReturnStatementWithoutExpresion returnNoExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		returnFound = true;
		thisObj = null;
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

	@Override
	public void visit(GotoStatement gotoStatement) {
		String name = gotoStatement.getLabel().getName();
		if (labelPcMap.containsKey(name)) {
			Code.putJump(labelPcMap.get(name));
		} else {
			LinkedList<Integer> l = null;
			if (labelFixUpMap.containsKey(name)) {
				l = labelFixUpMap.get(name);
			} else {
				l = new LinkedList<>();
			}
			Code.loadConst(0);
			Code.loadConst(0);
			Code.putFalseJump(Code.ne, 0);
			l.add(Code.pc - 2);
			labelFixUpMap.put(name, l);
		}
	}

	@Override
	public void visit(Label label) {
		if (label.getParent() instanceof StatementLabel) {
			labelPcMap.put(label.getName(), Code.pc);
			LinkedList<Integer> l = null;
			if (labelFixUpMap.containsKey(label.getName())) {
				l = labelFixUpMap.get(label.getName());
			} else {
				l = new LinkedList<>();
			}
			for (Integer fixUpAdr : l) {
				Code.fixup(fixUpAdr);
			}
		}
	}

	@Override
	public void visit(SingleConditionTermList singleConditionTermList) {
		Code.loadConst(0);
		Code.put(Code.jcc + Code.ne);
		Code.put2(9);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		condtionFixupListForAnd.add(Code.pc - 2);
		if (singleConditionTermList.getParent() instanceof SingleConditionList) {
			Code.loadConst(1);
		}
	}

	@Override
	public void visit(ConditionTermList conditionTermList) {
		for (Integer adr : condtionFixupListForAnd) {
			Code.fixup(adr);
		}
		condtionFixupListForAnd.clear();
		Code.loadConst(0);
		Code.put(Code.jcc + Code.ne);
		Code.put2(9);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		conditionFixupForOr.add(Code.pc - 2);
	}

	@Override
	public void visit(ConditionList conditionList) {
//		Code.put(Code.add);
//		Code.loadConst(0);
//		Code.putFalseJump(Code.eq, 0);
//		conditionFixupListForTrue.add(Code.pc - 2);
//		Code.loadConst(0);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		conditionFixupListForTrue.add(Code.pc - 2);
	}

	@Override
	public void visit(IfHeader ifHeader) {
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		conditionFixupListForFalse.add(Code.pc - 2);
		for (Integer adr : conditionFixupListForTrue) {
			Code.fixup(adr);
		}
		conditionFixupListForTrue.clear();
	}

	public void visit(SingleConditionList singleConditionList) {
		for (Integer adr : conditionFixupForOr) {
			Code.fixup(adr);
		}
		conditionFixupForOr.clear();
		for (Integer adr : condtionFixupListForAnd) {
			Code.fixup(adr);
		}
		condtionFixupListForAnd.clear();
		Code.loadConst(1);
		Code.put(Code.jcc + Code.ne);
		Code.put2(8);
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		conditionFixupListForTrue.add(Code.pc - 2);
	}

	private void putRelOp(int opCode) {
		// test and jmp if yes
		Code.put(Code.jcc + opCode);
		Code.put2(7);

		// no: put 0, jmp next
		Code.loadConst(0);
		Code.put(Code.jmp);
		Code.put2(4);

		// yes: put 1
		Code.loadConst(1);

	}

	@Override
	public void visit(MultiCondFact multiCondFact) {
		int opCode = -1;
		Relop relop = multiCondFact.getRelop();

		if (relop instanceof RelEqual) {
			opCode = Code.eq;
		}

		if (relop instanceof RelNEqual) {
			opCode = Code.ne;
		}

		if (relop instanceof RelGREATER) {
			opCode = Code.gt;
		}

		if (relop instanceof RelLess) {
			opCode = Code.lt;
		}

		if (relop instanceof RelGEqual) {
			opCode = Code.ge;
		}

		if (relop instanceof RelLEqual) {
			opCode = Code.le;
		}
		putRelOp(opCode);
	}

	// kraj if dela
	public void visit(IfPart ifPart) {
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		conditionFixupListForElse.add(Code.pc - 2);
		for (Integer adr : conditionFixupListForFalse) {
			Code.fixup(adr);
		}
		for (Integer adr : condtionFixupListForAnd) {
			Code.fixup(adr);
		}
		for (Integer adr : conditionFixupForOr) {
			Code.fixup(adr);
		}
		conditionFixupForOr.clear();
		condtionFixupListForAnd.clear();
		conditionFixupListForFalse.clear();
	}

	public void visit(ElseStatementStatement elseStatement) {
		for (Integer adr : conditionFixupListForElse) {
			Code.fixup(adr);
		}
		conditionFixupListForElse.clear();

		stackConditionFixupListForFalse.pop();
		stackConditionFixupListForTrue.pop();
		stackConditionFixupForOr.pop();
		stackCondtionFixupListForAnd.pop();
		stackConditionFixupListForElse.pop();

		if (!stackConditionFixupListForFalse.empty())
			conditionFixupListForFalse = stackConditionFixupListForFalse.peek();
		if (!stackConditionFixupListForTrue.empty())
			conditionFixupListForTrue = stackConditionFixupListForTrue.peek();
		if (!stackConditionFixupForOr.empty())
			conditionFixupForOr = stackConditionFixupForOr.peek();
		if (!stackCondtionFixupListForAnd.empty())
			condtionFixupListForAnd = stackCondtionFixupListForAnd.peek();
		if (!stackConditionFixupListForElse.empty())
			conditionFixupListForElse = stackConditionFixupListForElse.peek();

	}

	public void visit(NoElseStatement noElseStatement) {
		for (Integer adr : conditionFixupListForElse) {
			Code.fixup(adr);
		}
		conditionFixupListForElse.clear();
		
		stackConditionFixupListForFalse.pop();
		stackConditionFixupListForTrue.pop();
		stackConditionFixupForOr.pop();
		stackCondtionFixupListForAnd.pop();
		stackConditionFixupListForElse.pop();

		if (!stackConditionFixupListForFalse.empty())
			conditionFixupListForFalse = stackConditionFixupListForFalse.peek();
		if (!stackConditionFixupListForTrue.empty())
			conditionFixupListForTrue = stackConditionFixupListForTrue.peek();
		if (!stackConditionFixupForOr.empty())
			conditionFixupForOr = stackConditionFixupForOr.peek();
		if (!stackCondtionFixupListForAnd.empty())
			condtionFixupListForAnd = stackCondtionFixupListForAnd.peek();
		if (!stackConditionFixupListForElse.empty())
			conditionFixupListForElse = stackConditionFixupListForElse.peek();
		
		
	}

	public void visit(IfStart ifStart) {
		stackConditionFixupListForFalse.push(new LinkedList<>());
		stackConditionFixupListForTrue.push(new LinkedList<>());
		stackConditionFixupForOr.push(new LinkedList<>());
		stackCondtionFixupListForAnd.push(new LinkedList<>());
		stackConditionFixupListForElse.push(new LinkedList<>());

		conditionFixupListForFalse = stackConditionFixupListForFalse.peek();
		conditionFixupListForTrue = stackConditionFixupListForTrue.peek();
		conditionFixupForOr = stackConditionFixupForOr.peek();
		condtionFixupListForAnd = stackCondtionFixupListForAnd.peek();
		conditionFixupListForElse = stackConditionFixupListForElse.peek();
	};

}
