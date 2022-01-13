// generated with ast extension for cup
// version 0.8
// 13/0/2022 16:56:9


package rs.ac.bg.etf.pp1.ast;

public class SingleDesign extends DesignatorMultiList {

    private DesignatorMulti DesignatorMulti;

    public SingleDesign (DesignatorMulti DesignatorMulti) {
        this.DesignatorMulti=DesignatorMulti;
        if(DesignatorMulti!=null) DesignatorMulti.setParent(this);
    }

    public DesignatorMulti getDesignatorMulti() {
        return DesignatorMulti;
    }

    public void setDesignatorMulti(DesignatorMulti DesignatorMulti) {
        this.DesignatorMulti=DesignatorMulti;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorMulti!=null) DesignatorMulti.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorMulti!=null) DesignatorMulti.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorMulti!=null) DesignatorMulti.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleDesign(\n");

        if(DesignatorMulti!=null)
            buffer.append(DesignatorMulti.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleDesign]");
        return buffer.toString();
    }
}
