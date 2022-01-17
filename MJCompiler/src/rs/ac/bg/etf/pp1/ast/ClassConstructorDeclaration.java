// generated with ast extension for cup
// version 0.8
// 17/0/2022 19:15:9


package rs.ac.bg.etf.pp1.ast;

public class ClassConstructorDeclaration extends ConstructorDecl {

    private ConstructorHeader ConstructorHeader;
    private VarDeclList VarDeclList;
    private StatementList StatementList;

    public ClassConstructorDeclaration (ConstructorHeader ConstructorHeader, VarDeclList VarDeclList, StatementList StatementList) {
        this.ConstructorHeader=ConstructorHeader;
        if(ConstructorHeader!=null) ConstructorHeader.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public ConstructorHeader getConstructorHeader() {
        return ConstructorHeader;
    }

    public void setConstructorHeader(ConstructorHeader ConstructorHeader) {
        this.ConstructorHeader=ConstructorHeader;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorHeader!=null) ConstructorHeader.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorHeader!=null) ConstructorHeader.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorHeader!=null) ConstructorHeader.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassConstructorDeclaration(\n");

        if(ConstructorHeader!=null)
            buffer.append(ConstructorHeader.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassConstructorDeclaration]");
        return buffer.toString();
    }
}
