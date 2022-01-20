// generated with ast extension for cup
// version 0.8
// 20/0/2022 18:23:52


package rs.ac.bg.etf.pp1.ast;

public class DoStatement extends SingleStatement {

    private DoPart DoPart;
    private Condition Condition;

    public DoStatement (DoPart DoPart, Condition Condition) {
        this.DoPart=DoPart;
        if(DoPart!=null) DoPart.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DoPart getDoPart() {
        return DoPart;
    }

    public void setDoPart(DoPart DoPart) {
        this.DoPart=DoPart;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoPart!=null) DoPart.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoPart!=null) DoPart.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoPart!=null) DoPart.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoStatement(\n");

        if(DoPart!=null)
            buffer.append(DoPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoStatement]");
        return buffer.toString();
    }
}
