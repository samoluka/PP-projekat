// generated with ast extension for cup
// version 0.8
// 20/0/2022 17:53:33


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatementWithoutExpresion extends SingleStatement {

    public ReturnStatementWithoutExpresion () {
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
        buffer.append("ReturnStatementWithoutExpresion(\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatementWithoutExpresion]");
        return buffer.toString();
    }
}
