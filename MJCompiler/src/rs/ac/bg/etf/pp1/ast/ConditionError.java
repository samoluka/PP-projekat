// generated with ast extension for cup
// version 0.8
// 13/0/2022 2:36:49


package rs.ac.bg.etf.pp1.ast;

public class ConditionError extends Condition {

    public ConditionError () {
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
        buffer.append("ConditionError(\n");

        buffer.append(tab);
        buffer.append(") [ConditionError]");
        return buffer.toString();
    }
}
