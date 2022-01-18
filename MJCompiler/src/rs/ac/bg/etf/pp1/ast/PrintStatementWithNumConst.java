// generated with ast extension for cup
// version 0.8
// 18/0/2022 16:29:27


package rs.ac.bg.etf.pp1.ast;

public class PrintStatementWithNumConst extends SingleStatement {

    private Expr Expr;
    private FactorForConst FactorForConst;

    public PrintStatementWithNumConst (Expr Expr, FactorForConst FactorForConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.FactorForConst=FactorForConst;
        if(FactorForConst!=null) FactorForConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public FactorForConst getFactorForConst() {
        return FactorForConst;
    }

    public void setFactorForConst(FactorForConst FactorForConst) {
        this.FactorForConst=FactorForConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(FactorForConst!=null) FactorForConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(FactorForConst!=null) FactorForConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(FactorForConst!=null) FactorForConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStatementWithNumConst(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorForConst!=null)
            buffer.append(FactorForConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStatementWithNumConst]");
        return buffer.toString();
    }
}
