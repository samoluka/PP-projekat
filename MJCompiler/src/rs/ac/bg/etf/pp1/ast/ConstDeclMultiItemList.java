// generated with ast extension for cup
// version 0.8
// 20/0/2022 20:45:17


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclMultiItemList extends ConstDeclItemList {

    private ConstDeclItemList ConstDeclItemList;
    private AssignmentDecl AssignmentDecl;

    public ConstDeclMultiItemList (ConstDeclItemList ConstDeclItemList, AssignmentDecl AssignmentDecl) {
        this.ConstDeclItemList=ConstDeclItemList;
        if(ConstDeclItemList!=null) ConstDeclItemList.setParent(this);
        this.AssignmentDecl=AssignmentDecl;
        if(AssignmentDecl!=null) AssignmentDecl.setParent(this);
    }

    public ConstDeclItemList getConstDeclItemList() {
        return ConstDeclItemList;
    }

    public void setConstDeclItemList(ConstDeclItemList ConstDeclItemList) {
        this.ConstDeclItemList=ConstDeclItemList;
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
        if(ConstDeclItemList!=null) ConstDeclItemList.accept(visitor);
        if(AssignmentDecl!=null) AssignmentDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclItemList!=null) ConstDeclItemList.traverseTopDown(visitor);
        if(AssignmentDecl!=null) AssignmentDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclItemList!=null) ConstDeclItemList.traverseBottomUp(visitor);
        if(AssignmentDecl!=null) AssignmentDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclMultiItemList(\n");

        if(ConstDeclItemList!=null)
            buffer.append(ConstDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignmentDecl!=null)
            buffer.append(AssignmentDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclMultiItemList]");
        return buffer.toString();
    }
}
