// generated with ast extension for cup
// version 0.8
// 25/0/2022 16:20:46


package rs.ac.bg.etf.pp1.ast;

public class ElseStatementStatement extends ElseStatement {

    private Statement Statement;

    public ElseStatementStatement (Statement Statement) {
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ElseStatementStatement(\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ElseStatementStatement]");
        return buffer.toString();
    }
}
