// generated with ast extension for cup
// version 0.8
// 16/0/2022 22:50:43


package rs.ac.bg.etf.pp1.ast;

public class ActualParamMulti extends ActualPars {

    private ActualPars ActualPars;
    private ActualParamSingleItem ActualParamSingleItem;

    public ActualParamMulti (ActualPars ActualPars, ActualParamSingleItem ActualParamSingleItem) {
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
        this.ActualParamSingleItem=ActualParamSingleItem;
        if(ActualParamSingleItem!=null) ActualParamSingleItem.setParent(this);
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
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
        if(ActualPars!=null) ActualPars.accept(visitor);
        if(ActualParamSingleItem!=null) ActualParamSingleItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
        if(ActualParamSingleItem!=null) ActualParamSingleItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        if(ActualParamSingleItem!=null) ActualParamSingleItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParamMulti(\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParamSingleItem!=null)
            buffer.append(ActualParamSingleItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParamMulti]");
        return buffer.toString();
    }
}
