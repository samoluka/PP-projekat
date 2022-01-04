// generated with ast extension for cup
// version 0.8
// 4/0/2022 22:48:8


package rs.ac.bg.etf.pp1.ast;

public class AssignmentDeclaration extends AssignmentDecl {

    private String varName;
    private Const Const;

    public AssignmentDeclaration (String varName, Const Const) {
        this.varName=varName;
        this.Const=Const;
        if(Const!=null) Const.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public Const getConst() {
        return Const;
    }

    public void setConst(Const Const) {
        this.Const=Const;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Const!=null) Const.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Const!=null) Const.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Const!=null) Const.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignmentDeclaration(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(Const!=null)
            buffer.append(Const.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignmentDeclaration]");
        return buffer.toString();
    }
}
