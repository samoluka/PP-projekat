// generated with ast extension for cup
// version 0.8
// 20/0/2022 16:37:39


package rs.ac.bg.etf.pp1.ast;

public class AddPlus extends Addop {

    public AddPlus () {
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
        buffer.append("AddPlus(\n");

        buffer.append(tab);
        buffer.append(") [AddPlus]");
        return buffer.toString();
    }
}
