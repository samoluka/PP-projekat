// generated with ast extension for cup
// version 0.8
// 17/0/2022 20:44:38


package rs.ac.bg.etf.pp1.ast;

public class AssignmentDeclError extends AssignmentDecl {

    public AssignmentDeclError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssignmentDeclError(\n");

        buffer.append(tab);
        buffer.append(") [AssignmentDeclError]");
        return buffer.toString();
    }
}
