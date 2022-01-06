// generated with ast extension for cup
// version 0.8
// 6/0/2022 2:19:23


package rs.ac.bg.etf.pp1.ast;

public class MultiDesign extends DesignatorMultiList {

    private DesignatorMultiList DesignatorMultiList;
    private DesignatorMulti DesignatorMulti;

    public MultiDesign (DesignatorMultiList DesignatorMultiList, DesignatorMulti DesignatorMulti) {
        this.DesignatorMultiList=DesignatorMultiList;
        if(DesignatorMultiList!=null) DesignatorMultiList.setParent(this);
        this.DesignatorMulti=DesignatorMulti;
        if(DesignatorMulti!=null) DesignatorMulti.setParent(this);
    }

    public DesignatorMultiList getDesignatorMultiList() {
        return DesignatorMultiList;
    }

    public void setDesignatorMultiList(DesignatorMultiList DesignatorMultiList) {
        this.DesignatorMultiList=DesignatorMultiList;
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
        if(DesignatorMultiList!=null) DesignatorMultiList.accept(visitor);
        if(DesignatorMulti!=null) DesignatorMulti.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorMultiList!=null) DesignatorMultiList.traverseTopDown(visitor);
        if(DesignatorMulti!=null) DesignatorMulti.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorMultiList!=null) DesignatorMultiList.traverseBottomUp(visitor);
        if(DesignatorMulti!=null) DesignatorMulti.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiDesign(\n");

        if(DesignatorMultiList!=null)
            buffer.append(DesignatorMultiList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorMulti!=null)
            buffer.append(DesignatorMulti.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiDesign]");
        return buffer.toString();
    }
}
