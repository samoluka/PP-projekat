// generated with ast extension for cup
// version 0.8
// 15/0/2022 17:38:23


package rs.ac.bg.etf.pp1.ast;

public class FormalParamVar extends FormalParamList {

    private FormalParamListItem FormalParamListItem;

    public FormalParamVar (FormalParamListItem FormalParamListItem) {
        this.FormalParamListItem=FormalParamListItem;
        if(FormalParamListItem!=null) FormalParamListItem.setParent(this);
    }

    public FormalParamListItem getFormalParamListItem() {
        return FormalParamListItem;
    }

    public void setFormalParamListItem(FormalParamListItem FormalParamListItem) {
        this.FormalParamListItem=FormalParamListItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamListItem!=null) FormalParamListItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamListItem!=null) FormalParamListItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamListItem!=null) FormalParamListItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamVar(\n");

        if(FormalParamListItem!=null)
            buffer.append(FormalParamListItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamVar]");
        return buffer.toString();
    }
}
