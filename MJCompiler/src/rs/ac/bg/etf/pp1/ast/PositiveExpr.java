// generated with ast extension for cup
// version 0.8
// 5/0/2022 16:21:26


package rs.ac.bg.etf.pp1.ast;

public class PositiveExpr extends Expr {

    private ExprPositive ExprPositive;

    public PositiveExpr (ExprPositive ExprPositive) {
        this.ExprPositive=ExprPositive;
        if(ExprPositive!=null) ExprPositive.setParent(this);
    }

    public ExprPositive getExprPositive() {
        return ExprPositive;
    }

    public void setExprPositive(ExprPositive ExprPositive) {
        this.ExprPositive=ExprPositive;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprPositive!=null) ExprPositive.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprPositive!=null) ExprPositive.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprPositive!=null) ExprPositive.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PositiveExpr(\n");

        if(ExprPositive!=null)
            buffer.append(ExprPositive.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PositiveExpr]");
        return buffer.toString();
    }
}
