// generated with ast extension for cup
// version 0.8
// 12/0/2022 23:3:38


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListError extends FormalParams {

    public FormalParamListError () {
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
        buffer.append("FormalParamListError(\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListError]");
        return buffer.toString();
    }
}
