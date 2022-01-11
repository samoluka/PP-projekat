// generated with ast extension for cup
// version 0.8
// 11/0/2022 16:27:1


package rs.ac.bg.etf.pp1.ast;

public class MulMul extends Mulop {

    public MulMul () {
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
        buffer.append("MulMul(\n");

        buffer.append(tab);
        buffer.append(") [MulMul]");
        return buffer.toString();
    }
}
