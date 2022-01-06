// generated with ast extension for cup
// version 0.8
// 6/0/2022 2:36:45


package rs.ac.bg.etf.pp1.ast;

public class MultiDesignator extends Designator {

    private String name;
    private DesignatorMultiList DesignatorMultiList;

    public MultiDesignator (String name, DesignatorMultiList DesignatorMultiList) {
        this.name=name;
        this.DesignatorMultiList=DesignatorMultiList;
        if(DesignatorMultiList!=null) DesignatorMultiList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public DesignatorMultiList getDesignatorMultiList() {
        return DesignatorMultiList;
    }

    public void setDesignatorMultiList(DesignatorMultiList DesignatorMultiList) {
        this.DesignatorMultiList=DesignatorMultiList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorMultiList!=null) DesignatorMultiList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorMultiList!=null) DesignatorMultiList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorMultiList!=null) DesignatorMultiList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultiDesignator(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(DesignatorMultiList!=null)
            buffer.append(DesignatorMultiList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultiDesignator]");
        return buffer.toString();
    }
}
