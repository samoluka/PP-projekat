// generated with ast extension for cup
// version 0.8
// 20/0/2022 0:27:35


package rs.ac.bg.etf.pp1.ast;

public class StatementError extends SingleStatement {

    public StatementError () {
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
        buffer.append("StatementError(\n");

        buffer.append(tab);
        buffer.append(") [StatementError]");
        return buffer.toString();
    }
}
