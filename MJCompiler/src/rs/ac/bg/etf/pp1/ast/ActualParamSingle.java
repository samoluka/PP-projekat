// generated with ast extension for cup
// version 0.8
// 12/0/2022 2:24:10


package rs.ac.bg.etf.pp1.ast;

public class ActualParamSingle extends ActualPars {

    private ActualParamSingleItem ActualParamSingleItem;

    public ActualParamSingle (ActualParamSingleItem ActualParamSingleItem) {
        this.ActualParamSingleItem=ActualParamSingleItem;
        if(ActualParamSingleItem!=null) ActualParamSingleItem.setParent(this);
    }

    public ActualParamSingleItem getActualParamSingleItem() {
        return ActualParamSingleItem;
    }

    public void setActualParamSingleItem(ActualParamSingleItem ActualParamSingleItem) {
        this.ActualParamSingleItem=ActualParamSingleItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParamSingleItem!=null) ActualParamSingleItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParamSingleItem!=null) ActualParamSingleItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParamSingleItem!=null) ActualParamSingleItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParamSingle(\n");

        if(ActualParamSingleItem!=null)
            buffer.append(ActualParamSingleItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParamSingle]");
        return buffer.toString();
    }
}
