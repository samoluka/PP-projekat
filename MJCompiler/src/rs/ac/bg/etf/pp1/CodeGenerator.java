package rs.ac.bg.etf.pp1;

import java.lang.instrument.ClassDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.ActualParamSingleItem;
import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.AddPlus;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.BoolConst;
import rs.ac.bg.etf.pp1.ast.BreakStatement;
import rs.ac.bg.etf.pp1.ast.CharConst;
import rs.ac.bg.etf.pp1.ast.ClassConstructorDeclaration;
import rs.ac.bg.etf.pp1.ast.ClassDeclarations;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.ConditionList;
import rs.ac.bg.etf.pp1.ast.ConditionTermList;
import rs.ac.bg.etf.pp1.ast.ConstructorHeader;
import rs.ac.bg.etf.pp1.ast.ContinueStatement;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignmentStatement;
import rs.ac.bg.etf.pp1.ast.DesignatorForAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorForMethodCall;
import rs.ac.bg.etf.pp1.ast.DesignatorItemDec;
import rs.ac.bg.etf.pp1.ast.DesignatorItemFuncCallWithParam;
import rs.ac.bg.etf.pp1.ast.DesignatorItemInc;
import rs.ac.bg.etf.pp1.ast.DoPart;
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
import rs.ac.bg.etf.pp1.ast.ProgName;
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
import rs.ac.bg.etf.pp1.ast.SuperStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.ac.bg.etf.pp1.ast.DoStart;
import rs.ac.bg.etf.pp1.ast.DoStatement;
import rs.ac.bg.etf.pp1.ast.IfStart;
import rs.ac.bg.etf.pp1.ast.IfStatement;
import rs.ac.bg.etf.pp1.ast.IfHeader;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
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

	private Stack<Integer> doStartAdr = new Stack<>();
	private Stack<Boolean> doWhileIfStack = new Stack<>();
	private Stack<List<Integer>> continueStack = new Stack<>();
	private Stack<List<Integer>> breakStack = new Stack<>();
	private boolean classMethod = false;
	private int virtualTableAddr = 0;
	private int virtualTableAddrForSave = -1;
	private Stack<Boolean> isMethod = new Stack<>();
	private Stack<Integer> actualParamCnt = new Stack<>();

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

	public int getMainPc() {
		return mainPc;
	}

	public void visit(ProgName progName) {
		Code.dataSize += SemanticPass.nVars;
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
		} else {
			Code.put(Code.bread);
		}
		if (readStatement.getDesignator() instanceof ArrayDesignator) {
			Code.put(Code.astore);
		} else {
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
		int addOn = 0;
		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
			// Store vft-creation byte code before the first instruction
			// in main function.
			Object ia[] = MethodTable.toArray();
			for (int i = 0; i < ia.length; i++)
				Code.buf[Code.pc++] = ((Byte) ia[i]).byteValue();
			MethodTable.clear();
		}
		methodTypeName.obj.setAdr(Code.pc);
		if (!"main".equalsIgnoreCase(methodTypeName.getMethodName()) && classMethod) {
			if (virtualTableAddrForSave == -1)
				virtualTableAddrForSave = Code.dataSize;
			addFunctionEntry(methodTypeName.getMethodName(), methodTypeName.obj.getAdr());
		}
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();

		int varCnt = methodTypeName.obj.getLocalSymbols().size();
		int fpCnt = methodTypeName.obj.getLevel();
//		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
//			varCnt = Code.dataSize + fpCnt;
//		}
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt);
		Code.put(varCnt);

	}

	public void visit(MethodTypeNameWithType methodTypeName) {

		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
			// Store vft-creation byte code before the first instruction
			// in main function.
			Object ia[] = MethodTable.toArray();
			for (int i = 0; i < ia.length; i++)
				Code.buf[Code.pc++] = ((Byte) ia[i]).byteValue();
			MethodTable.clear();
		}
		methodTypeName.obj.setAdr(Code.pc);
		if (!"main".equalsIgnoreCase(methodTypeName.getMethodName()) && classMethod) {
			if (virtualTableAddrForSave == -1)
				virtualTableAddrForSave = Code.dataSize;
			addFunctionEntry(methodTypeName.getMethodName(), methodTypeName.obj.getAdr());
		}
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
		if (singleDesignator.obj.getKind() == Obj.Meth) {
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
		if (arrayDesignator.getParent() instanceof ReadStatement) {
			return;
		}
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
		Code.put(Code.dup);
		Code.loadConst(vTableAddress); // v_table value
		Code.put(Code.putfield);
		Code.put2(0);
		Code.put(Code.dup);
		for (Obj o : nFactor.obj.getType().getMembers()) {
			if (o.getName().equals(className)) {
				int offset = o.getAdr() - Code.pc;
				Code.put(Code.call);
				Code.put2(offset);
			}
		}
	}

	@Override
	public void visit(NewFactorWithBrackets nArray) {
		Code.put(Code.newarray);
		Code.put(1);
	}

	public void visit(MethodCall methodCall) {
		Obj functionObj = methodCall.getDesignatorForMethodCall().obj;
		if (functionObj.getName().equals("ord")) {
			return;
		}
		if (functionObj.getName().equals("chr")) {
			return;
		}
		if (functionObj.getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		if (isMethod.peek()) {
//			Obj classCalled = ((DotDesignator) methodCall.getDesignatorForMethodCall().getDesignator())
//					.getDesignator().obj;
			String name = methodCall.getDesignatorForMethodCall().obj.getName();
//			Code.put(Code.getstatic);
//			Code.put2(0);
			if (methodCall.getDesignatorForMethodCall().obj.getLevel() == 1)
				Code.put(Code.dup);
			Code.put(Code.getfield);
			Code.put2(0);
			Code.put(Code.invokevirtual);
			for (int i = 0; i < name.length(); i++)
				Code.put4(name.charAt(i));
			Code.put4(-1);
		} else {
			int offset = functionObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
		isMethod.pop();
//		if (functionObj.getType() != Tab.noType) {
//			Code.put(Code.pop);
//		}
//		int offset = functionObj.getAdr() - Code.pc;
//		Code.put(Code.call);
//		Code.put2(offset);
////		if (functionObj.getType() != Tab.noType) {
////			Code.put(Code.pop);
////		}
	}

	public void visit(DesignatorItemFuncCallWithParam diFunCall) {
		thisObj = lastClassObj;
		Obj functionObj = diFunCall.getMethodNameDesignator().obj;
		if (isMethod.peek()) {
//			Obj classCalled = ((DotDesignator) diFunCall.getMethodNameDesignator().getDesignator()).getDesignator().obj;
			String name = diFunCall.getMethodNameDesignator().obj.getName();
//			Code.put(Code.getstatic);
//			Code.put2(0);
//			for (int i = 0; i < diFunCall.getMethodNameDesignator().obj.getLevel() - 1; i++) {
//				Code.put(Code.dup_x1);
//				Code.put(Code.pop);
//			}
			if (diFunCall.getMethodNameDesignator().obj.getLevel() == 1)
				Code.put(Code.dup);
			Code.put(Code.getfield);
			Code.put2(0);
			Code.put(Code.invokevirtual);
			for (int i = 0; i < name.length(); i++)
				Code.put4(name.charAt(i));
			Code.put4(-1);
		} else {
			int offset = functionObj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
		isMethod.pop();
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
		if (doWhileIfStack.peek()) {
			Code.putJump(doStartAdr.peek());
		} else {
			Code.loadConst(0);
			Code.loadConst(0);
			Code.putFalseJump(Code.ne, 0);
			conditionFixupListForTrue.add(Code.pc - 2);
		}
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
		if (doWhileIfStack.peek()) {
			Code.put2(6);
			Code.putJump(doStartAdr.peek());
		} else {
			Code.put2(8);
			Code.loadConst(0);
			Code.loadConst(0);
			Code.putFalseJump(Code.ne, 0);
			conditionFixupListForTrue.add(Code.pc - 2);
		}
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
		else
			conditionFixupListForFalse = new LinkedList<>();

		if (!stackConditionFixupListForTrue.empty())
			conditionFixupListForTrue = stackConditionFixupListForTrue.peek();
		else
			conditionFixupListForTrue = new LinkedList<>();

		if (!stackConditionFixupForOr.empty())
			conditionFixupForOr = stackConditionFixupForOr.peek();
		else
			conditionFixupForOr = new LinkedList<>();

		if (!stackCondtionFixupListForAnd.empty())
			condtionFixupListForAnd = stackCondtionFixupListForAnd.peek();
		else
			condtionFixupListForAnd = new LinkedList<>();

		if (!stackConditionFixupListForElse.empty())
			conditionFixupListForElse = stackConditionFixupListForElse.peek();
		else
			conditionFixupListForElse = new LinkedList<>();

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
		else
			conditionFixupListForFalse = new LinkedList<>();

		if (!stackConditionFixupListForTrue.empty())
			conditionFixupListForTrue = stackConditionFixupListForTrue.peek();
		else
			conditionFixupListForTrue = new LinkedList<>();

		if (!stackConditionFixupForOr.empty())
			conditionFixupForOr = stackConditionFixupForOr.peek();
		else
			conditionFixupForOr = new LinkedList<>();

		if (!stackCondtionFixupListForAnd.empty())
			condtionFixupListForAnd = stackCondtionFixupListForAnd.peek();
		else
			condtionFixupListForAnd = new LinkedList<>();

		if (!stackConditionFixupListForElse.empty())
			conditionFixupListForElse = stackConditionFixupListForElse.peek();
		else
			conditionFixupListForElse = new LinkedList<>();
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

		doWhileIfStack.push(false);

	};

	public void visit(ContinueStatement continueStatement) {
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		if (!continueStack.empty()) {
			continueStack.peek().add(Code.pc - 2);
		} else {
			report_error("Continue iskaz mora biti uparen sa doWhile naredbom", continueStatement);
		}
	}

	public void visit(BreakStatement breakStatement) {
		Code.loadConst(0);
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		if (!breakStack.empty()) {
			breakStack.peek().add(Code.pc - 2);
		} else {
			report_error("Break iskaz mora biti uparen sa doWhile naredbom", breakStatement);
		}
	}

	public void visit(DoStart doStart) {
		doWhileIfStack.push(true);
		doStartAdr.push(Code.pc);
		continueStack.push(new LinkedList<>());
		breakStack.push(new LinkedList<>());
	}

	public void visit(DoStatement doStatement) {
		doWhileIfStack.pop();
		doStartAdr.pop();
		List<Integer> fixupList = breakStack.pop();
		for (Integer adr : fixupList) {
			Code.fixup(adr);
		}
	}

	public void visit(IfStatement ifStatement) {
		doWhileIfStack.pop();
	}

	public void visit(DoPart doPart) {
		List<Integer> fixupList = continueStack.pop();
		for (Integer adr : fixupList) {
			Code.fixup(adr);
		}
	}

	public void visit(ClassName className) {
		classMethod = true;
	}

	public void visit(ClassDeclarations classDeclarations) {
		classMethod = false;
		classDeclarations.getClassName().obj.setAdr(virtualTableAddrForSave);
		virtualTableAddrForSave = -1;
		if (classDeclarations.getClassName().obj.getType().getElemType() != null) {
			for (Obj o : classDeclarations.getClassName().obj.getType().getMembers()) {
				if (o.getKind() == Obj.Meth && o.getAdr() == 0) {
					for (Obj oo : classDeclarations.getClassName().obj.getType().getElemType().getMembers()) {
						if (o.getName().equals(oo.getName()))
							addFunctionEntry(o.getName(), oo.getAdr());
					}

				}
			}
		}
	}

	public void visit(SuperStatement superStatement) {
		for (int i = 0; i < superStatement.getSuperStart().obj.getLevel(); i++) {
			Code.put(Code.load_n + i);
		}
		int offset = superStatement.getSuperStart().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if (superStatement.getSuperStart().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}

	public void visit(ConstructorHeader cHeader) {
		cHeader.obj.setAdr(Code.pc);
		if (virtualTableAddrForSave == -1)
			virtualTableAddrForSave = Code.dataSize;
		addFunctionEntry(cHeader.obj.getName(), cHeader.obj.getAdr());
		// Collect arguments and local variables
		int varCnt = cHeader.obj.getLocalSymbols().size();

		int fpCnt = cHeader.obj.getLevel();

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt);
		Code.put(varCnt);
		returnFound = false;
	}

	public void visit(ClassConstructorDeclaration ccDeclaration) {
		if (returnFound == false) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		returnFound = false;
	}

	@Override
	public void visit(MethodNameDesignator mnDesignator) {
		if (mnDesignator.getDesignator() instanceof DotDesignator || classMethod) {
			isMethod.push(true);
			actualParamCnt.push(0);
		} else {
			isMethod.push(false);
			actualParamCnt.push(0);
		}
	}

	@Override
	public void visit(DesignatorForMethodCall mnDesignator) {
		if (mnDesignator.getDesignator() instanceof DotDesignator || classMethod) {
			isMethod.push(true);
			actualParamCnt.push(0);
		} else {
			isMethod.push(false);
			actualParamCnt.push(0);
		}
	}

	@Override
	public void visit(ActualParamSingleItem apSingle) {
		actualParamCnt.push(actualParamCnt.pop() + 1);
		if (isMethod.peek()) {
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			if (actualParamCnt.peek() == 1)
				Code.put(Code.dup_x1);
		}
	}

}
