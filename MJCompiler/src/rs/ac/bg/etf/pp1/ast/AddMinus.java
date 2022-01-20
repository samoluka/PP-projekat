// generated with ast extension for cup
// version 0.8
// 20/0/2022 18:23:52


package rs.ac.bg.etf.pp1.ast;

public class AddMinus extends Addop {

    public AddMinus () {
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
        buffer.append("AddMinus(\n");

        buffer.append(tab);
        buffer.append(") [AddMinus]");
        return buffer.toString();
    }
}
