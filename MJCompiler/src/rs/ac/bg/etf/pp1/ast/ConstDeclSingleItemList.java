// generated with ast extension for cup
// version 0.8
// 11/0/2022 18:59:32


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclSingleItemList extends ConstDeclItemList {

    private AssignmentDecl AssignmentDecl;

    public ConstDeclSingleItemList (AssignmentDecl AssignmentDecl) {
        this.AssignmentDecl=AssignmentDecl;
        if(AssignmentDecl!=null) AssignmentDecl.setParent(this);
    }

    public AssignmentDecl getAssignmentDecl() {
        return AssignmentDecl;
    }

    public void setAssignmentDecl(AssignmentDecl AssignmentDecl) {
        this.AssignmentDecl=AssignmentDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignmentDecl!=null) AssignmentDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignmentDecl!=null) AssignmentDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignmentDecl!=null) AssignmentDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclSingleItemList(\n");

        if(AssignmentDecl!=null)
            buffer.append(AssignmentDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclSingleItemList]");
        return buffer.toString();
    }
}
