// generated with ast extension for cup
// version 0.8
// 12/0/2022 2:24:10


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(MethodDecl MethodDecl);
    public void visit(Mulop Mulop);
    public void visit(MethodDeclItem MethodDeclItem);
    public void visit(ConstructorDecl ConstructorDecl);
    public void visit(VarClassErrorEndPoint VarClassErrorEndPoint);
    public void visit(Relop Relop);
    public void visit(VarListClass VarListClass);
    public void visit(VarDeclDefinition VarDeclDefinition);
    public void visit(VarDeclItemList VarDeclItemList);
    public void visit(MethodDeclListNonEmpty MethodDeclListNonEmpty);
    public void visit(StatementList StatementList);
    public void visit(DesignatorMulti DesignatorMulti);
    public void visit(Addop Addop);
    public void visit(VarItemListClass VarItemListClass);
    public void visit(RecordDecl RecordDecl);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(DeclItem DeclItem);
    public void visit(DesignatorMultiList DesignatorMultiList);
    public void visit(ElseStatement ElseStatement);
    public void visit(ClassExtends ClassExtends);
    public void visit(AssignmentDecl AssignmentDecl);
    public void visit(VarDeclList VarDeclList);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(FormalParams FormalParams);
    public void visit(FormalParamListItem FormalParamListItem);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualPars ActualPars);
    public void visit(Statement Statement);
    public void visit(VarClass VarClass);
    public void visit(VarDecl VarDecl);
    public void visit(ClassMethodDeclItemList ClassMethodDeclItemList);
    public void visit(ConstDeclItemList ConstDeclItemList);
    public void visit(DesignatorItem DesignatorItem);
    public void visit(ClassDecl ClassDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SingleStatement SingleStatement);
    public void visit(RelLess RelLess);
    public void visit(RelGREATER RelGREATER);
    public void visit(RelLEqual RelLEqual);
    public void visit(RelNEqual RelNEqual);
    public void visit(RelGEqual RelGEqual);
    public void visit(RelEqual RelEqual);
    public void visit(Label Label);
    public void visit(Assignop Assignop);
    public void visit(MulMod MulMod);
    public void visit(MulDiv MulDiv);
    public void visit(MulMul MulMul);
    public void visit(AddMinus AddMinus);
    public void visit(AddPlus AddPlus);
    public void visit(ParenDesignator ParenDesignator);
    public void visit(DotDesignator DotDesignator);
    public void visit(SingleDesign SingleDesign);
    public void visit(MultiDesign MultiDesign);
    public void visit(SingleDesignator SingleDesignator);
    public void visit(MultiDesignator MultiDesignator);
    public void visit(DesignatorForMethodCall DesignatorForMethodCall);
    public void visit(ActualParamSingleItem ActualParamSingleItem);
    public void visit(NoActualParam NoActualParam);
    public void visit(ActualParamSingle ActualParamSingle);
    public void visit(ActualParamMulti ActualParamMulti);
    public void visit(ExprFactor ExprFactor);
    public void visit(NewFactorWithBrackets NewFactorWithBrackets);
    public void visit(NewFactor NewFactor);
    public void visit(MethodCall MethodCall);
    public void visit(Variable Variable);
    public void visit(CharConst CharConst);
    public void visit(BoolConst BoolConst);
    public void visit(NumberConst NumberConst);
    public void visit(SingleTerm SingleTerm);
    public void visit(MulopTerm MulopTerm);
    public void visit(NegativeTermExpr NegativeTermExpr);
    public void visit(TermExpr TermExpr);
    public void visit(AddExpr AddExpr);
    public void visit(DesignatorItemDec DesignatorItemDec);
    public void visit(DesignatorItemInc DesignatorItemInc);
    public void visit(DesignatorItemFuncCallWithParam DesignatorItemFuncCallWithParam);
    public void visit(DesignatorAssignmentStatement DesignatorAssignmentStatement);
    public void visit(DesignatorStatementNoAssigment DesignatorStatementNoAssigment);
    public void visit(MultiCondFact MultiCondFact);
    public void visit(SingleCondFact SingleCondFact);
    public void visit(SingleConditionTermList SingleConditionTermList);
    public void visit(ConditionTermList ConditionTermList);
    public void visit(SingleConditionList SingleConditionList);
    public void visit(ConditionError ConditionError);
    public void visit(ConditionList ConditionList);
    public void visit(NoElseStatement NoElseStatement);
    public void visit(ElseStatementStatement ElseStatementStatement);
    public void visit(StatementError StatementError);
    public void visit(SingleMultiStatement SingleMultiStatement);
    public void visit(GotoStatement GotoStatement);
    public void visit(PrintStatementWithNumConst PrintStatementWithNumConst);
    public void visit(PrintStatementWithoutNumConst PrintStatementWithoutNumConst);
    public void visit(ReadStatement ReadStatement);
    public void visit(ReturnStatementWithoutExpresion ReturnStatementWithoutExpresion);
    public void visit(ReturnStatementWithExpresion ReturnStatementWithExpresion);
    public void visit(ContinueStatement ContinueStatement);
    public void visit(BreakStatement BreakStatement);
    public void visit(DoStatement DoStatement);
    public void visit(IfStatement IfStatement);
    public void visit(DesignatorSingleStatement DesignatorSingleStatement);
    public void visit(ListOfStatement ListOfStatement);
    public void visit(ListOfStatements ListOfStatements);
    public void visit(StatementLabel StatementLabel);
    public void visit(StatementWithoutLabel StatementWithoutLabel);
    public void visit(StatementWithLabel StatementWithLabel);
    public void visit(Type Type);
    public void visit(FormalParamCommaError FormalParamCommaError);
    public void visit(FormalParametherListItem FormalParametherListItem);
    public void visit(FormalParamVar FormalParamVar);
    public void visit(FormalParamListActual FormalParamListActual);
    public void visit(NoFormalParamsList NoFormalParamsList);
    public void visit(FormalParamListError FormalParamListError);
    public void visit(FormalParamsList FormalParamsList);
    public void visit(MethodDeclItemNoStatements MethodDeclItemNoStatements);
    public void visit(MethodDeclItemWithStatements MethodDeclItemWithStatements);
    public void visit(MethodTypeNameVoid MethodTypeNameVoid);
    public void visit(MethodTypeNameWithType MethodTypeNameWithType);
    public void visit(MethodDeclaration MethodDeclaration);
    public void visit(NoMethodDeclList NoMethodDeclList);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(MethodDeclarationNonEmptySingleList MethodDeclarationNonEmptySingleList);
    public void visit(MethodDeclarationNonEmptyList MethodDeclarationNonEmptyList);
    public void visit(ClassConstructorDeclaration ClassConstructorDeclaration);
    public void visit(NoClassMethodDeclItemList NoClassMethodDeclItemList);
    public void visit(OnlyMethodClassMethodDeclaration OnlyMethodClassMethodDeclaration);
    public void visit(OnlyConstructorClassMethodDeclaration OnlyConstructorClassMethodDeclaration);
    public void visit(FullClassMethodDeclarationsItemList FullClassMethodDeclarationsItemList);
    public void visit(NoClassExtends NoClassExtends);
    public void visit(ClassNoExtedsError ClassNoExtedsError);
    public void visit(ClassExtendsError ClassExtendsError);
    public void visit(ClassDeclarationsExtends ClassDeclarationsExtends);
    public void visit(ClassName ClassName);
    public void visit(ClassDeclarations ClassDeclarations);
    public void visit(VarSinglItemListClass VarSinglItemListClass);
    public void visit(VarMultiItemListClass VarMultiItemListClass);
    public void visit(NoVarDeclListClLass NoVarDeclListClLass);
    public void visit(VarListClassNonEmpty VarListClassNonEmpty);
    public void visit(VarClassErrorEndPointDerived2 VarClassErrorEndPointDerived2);
    public void visit(VarClassErrorEndPointDerived1 VarClassErrorEndPointDerived1);
    public void visit(VarErrorClass VarErrorClass);
    public void visit(VarClassNonError VarClassNonError);
    public void visit(RecordName RecordName);
    public void visit(RecordDeclarations RecordDeclarations);
    public void visit(VarDeclListItem VarDeclListItem);
    public void visit(NoVarDeclList NoVarDeclList);
    public void visit(VarDeclarationsList VarDeclarationsList);
    public void visit(DefinitionArray DefinitionArray);
    public void visit(Definition Definition);
    public void visit(VarDeclItemListError VarDeclItemListError);
    public void visit(VarDeclSinglItemList VarDeclSinglItemList);
    public void visit(VarDeclMultiItemList VarDeclMultiItemList);
    public void visit(VarDeclError VarDeclError);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(AssignmentDeclError AssignmentDeclError);
    public void visit(AssignmentDeclaration AssignmentDeclaration);
    public void visit(ConstDeclSingleItemList ConstDeclSingleItemList);
    public void visit(ConstDeclMultiItemList ConstDeclMultiItemList);
    public void visit(ConstDecl ConstDecl);
    public void visit(ClassDeclarationItem ClassDeclarationItem);
    public void visit(RecordDeclarationItem RecordDeclarationItem);
    public void visit(VarDeclarationItem VarDeclarationItem);
    public void visit(ConstDeclartionItem ConstDeclartionItem);
    public void visit(NoDeclList NoDeclList);
    public void visit(DeclarationsList DeclarationsList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
