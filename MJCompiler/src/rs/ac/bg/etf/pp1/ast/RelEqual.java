// generated with ast extension for cup
// version 0.8
// 15/0/2022 3:15:20


package rs.ac.bg.etf.pp1.ast;

public class RelEqual extends Relop {

    public RelEqual () {
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
        buffer.append("RelEqual(\n");

        buffer.append(tab);
        buffer.append(") [RelEqual]");
        return buffer.toString();
    }
}
