// generated with ast extension for cup
// version 0.8
// 12/0/2022 2:24:10


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementNoAssigment extends DesignatorStatement {

    private Designator Designator;
    private DesignatorItem DesignatorItem;

    public DesignatorStatementNoAssigment (Designator Designator, DesignatorItem DesignatorItem) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorItem=DesignatorItem;
        if(DesignatorItem!=null) DesignatorItem.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorItem getDesignatorItem() {
        return DesignatorItem;
    }

    public void setDesignatorItem(DesignatorItem DesignatorItem) {
        this.DesignatorItem=DesignatorItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorItem!=null) DesignatorItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorItem!=null) DesignatorItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorItem!=null) DesignatorItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementNoAssigment(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorItem!=null)
            buffer.append(DesignatorItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementNoAssigment]");
        return buffer.toString();
    }
}
