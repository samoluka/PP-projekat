// generated with ast extension for cup
// version 0.8
// 20/0/2022 22:38:55


package rs.ac.bg.etf.pp1.ast;

public class SuperStatement extends SingleStatement {

    private SuperStart SuperStart;

    public SuperStatement (SuperStart SuperStart) {
        this.SuperStart=SuperStart;
        if(SuperStart!=null) SuperStart.setParent(this);
    }

    public SuperStart getSuperStart() {
        return SuperStart;
    }

    public void setSuperStart(SuperStart SuperStart) {
        this.SuperStart=SuperStart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SuperStart!=null) SuperStart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SuperStart!=null) SuperStart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SuperStart!=null) SuperStart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SuperStatement(\n");

        if(SuperStart!=null)
            buffer.append(SuperStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SuperStatement]");
        return buffer.toString();
    }
}
