// generated with ast extension for cup
// version 0.8
// 7/0/2022 1:18:44


package rs.ac.bg.etf.pp1.ast;

public class VarClassErrorEndPointDerived1 extends VarClassErrorEndPoint {

    public VarClassErrorEndPointDerived1 () {
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
        buffer.append("VarClassErrorEndPointDerived1(\n");

        buffer.append(tab);
        buffer.append(") [VarClassErrorEndPointDerived1]");
        return buffer.toString();
    }
}
