// generated with ast extension for cup
// version 0.8
// 17/0/2022 19:15:9


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssignmentStatement extends DesignatorStatement {

    private DesignatorForAssign DesignatorForAssign;
    private Assignop Assignop;
    private Expr Expr;

    public DesignatorAssignmentStatement (DesignatorForAssign DesignatorForAssign, Assignop Assignop, Expr Expr) {
        this.DesignatorForAssign=DesignatorForAssign;
        if(DesignatorForAssign!=null) DesignatorForAssign.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorForAssign getDesignatorForAssign() {
        return DesignatorForAssign;
    }

    public void setDesignatorForAssign(DesignatorForAssign DesignatorForAssign) {
        this.DesignatorForAssign=DesignatorForAssign;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorForAssign!=null) DesignatorForAssign.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorForAssign!=null) DesignatorForAssign.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorForAssign!=null) DesignatorForAssign.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssignmentStatement(\n");

        if(DesignatorForAssign!=null)
            buffer.append(DesignatorForAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssignmentStatement]");
        return buffer.toString();
    }
}
