// generated with ast extension for cup
// version 0.8
// 13/0/2022 2:8:51


package rs.ac.bg.etf.pp1.ast;

public class AssignmentDeclaration extends AssignmentDecl {

    private String varName;
    private Factor Factor;

    public AssignmentDeclaration (String varName, Factor Factor) {
        this.varName=varName;
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignmentDeclaration(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignmentDeclaration]");
        return buffer.toString();
    }
}
