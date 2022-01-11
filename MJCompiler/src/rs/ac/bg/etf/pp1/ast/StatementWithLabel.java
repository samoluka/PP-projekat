// generated with ast extension for cup
// version 0.8
// 11/0/2022 15:44:31


package rs.ac.bg.etf.pp1.ast;

public class StatementWithLabel extends Statement {

    private StatementLabel StatementLabel;
    private SingleStatement SingleStatement;

    public StatementWithLabel (StatementLabel StatementLabel, SingleStatement SingleStatement) {
        this.StatementLabel=StatementLabel;
        if(StatementLabel!=null) StatementLabel.setParent(this);
        this.SingleStatement=SingleStatement;
        if(SingleStatement!=null) SingleStatement.setParent(this);
    }

    public StatementLabel getStatementLabel() {
        return StatementLabel;
    }

    public void setStatementLabel(StatementLabel StatementLabel) {
        this.StatementLabel=StatementLabel;
    }

    public SingleStatement getSingleStatement() {
        return SingleStatement;
    }

    public void setSingleStatement(SingleStatement SingleStatement) {
        this.SingleStatement=SingleStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementLabel!=null) StatementLabel.accept(visitor);
        if(SingleStatement!=null) SingleStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementLabel!=null) StatementLabel.traverseTopDown(visitor);
        if(SingleStatement!=null) SingleStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementLabel!=null) StatementLabel.traverseBottomUp(visitor);
        if(SingleStatement!=null) SingleStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementWithLabel(\n");

        if(StatementLabel!=null)
            buffer.append(StatementLabel.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleStatement!=null)
            buffer.append(SingleStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementWithLabel]");
        return buffer.toString();
    }
}
