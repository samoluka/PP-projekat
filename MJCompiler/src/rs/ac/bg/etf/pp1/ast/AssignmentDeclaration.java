// generated with ast extension for cup
// version 0.8
// 20/0/2022 0:43:31


package rs.ac.bg.etf.pp1.ast;

public class AssignmentDeclaration extends AssignmentDecl {

    private String varName;
    private FactorForConst FactorForConst;

    public AssignmentDeclaration (String varName, FactorForConst FactorForConst) {
        this.varName=varName;
        this.FactorForConst=FactorForConst;
        if(FactorForConst!=null) FactorForConst.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public FactorForConst getFactorForConst() {
        return FactorForConst;
    }

    public void setFactorForConst(FactorForConst FactorForConst) {
        this.FactorForConst=FactorForConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorForConst!=null) FactorForConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorForConst!=null) FactorForConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorForConst!=null) FactorForConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignmentDeclaration(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(FactorForConst!=null)
            buffer.append(FactorForConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssignmentDeclaration]");
        return buffer.toString();
    }
}
