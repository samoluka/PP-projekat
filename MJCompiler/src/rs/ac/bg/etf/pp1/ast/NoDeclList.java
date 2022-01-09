// generated with ast extension for cup
// version 0.8
// 9/0/2022 23:53:46


package rs.ac.bg.etf.pp1.ast;

public class NoDeclList extends DeclList {

    public NoDeclList () {
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
        buffer.append("NoDeclList(\n");

        buffer.append(tab);
        buffer.append(") [NoDeclList]");
        return buffer.toString();
    }
}
