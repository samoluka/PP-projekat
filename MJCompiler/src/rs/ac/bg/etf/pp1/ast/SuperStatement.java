// generated with ast extension for cup
// version 0.8
// 16/0/2022 20:13:9


package rs.ac.bg.etf.pp1.ast;

public class SuperStatement extends SingleStatement {

    public SuperStatement () {
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
        buffer.append("SuperStatement(\n");

        buffer.append(tab);
        buffer.append(") [SuperStatement]");
        return buffer.toString();
    }
}