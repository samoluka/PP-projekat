// generated with ast extension for cup
// version 0.8
// 17/0/2022 16:31:23


package rs.ac.bg.etf.pp1.ast;

public class MulDiv extends Mulop {

    public MulDiv () {
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
        buffer.append("MulDiv(\n");

        buffer.append(tab);
        buffer.append(") [MulDiv]");
        return buffer.toString();
    }
}
