// generated with ast extension for cup
// version 0.8
// 12/0/2022 1:1:12


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListActual extends FormalParamList {

    private FormalParamList FormalParamList;
    private FormalParamListItem FormalParamListItem;

    public FormalParamListActual (FormalParamList FormalParamList, FormalParamListItem FormalParamListItem) {
        this.FormalParamList=FormalParamList;
        if(FormalParamList!=null) FormalParamList.setParent(this);
        this.FormalParamListItem=FormalParamListItem;
        if(FormalParamListItem!=null) FormalParamListItem.setParent(this);
    }

    public FormalParamList getFormalParamList() {
        return FormalParamList;
    }

    public void setFormalParamList(FormalParamList FormalParamList) {
        this.FormalParamList=FormalParamList;
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
        if(FormalParamList!=null) FormalParamList.accept(visitor);
        if(FormalParamListItem!=null) FormalParamListItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamList!=null) FormalParamList.traverseTopDown(visitor);
        if(FormalParamListItem!=null) FormalParamListItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamList!=null) FormalParamList.traverseBottomUp(visitor);
        if(FormalParamListItem!=null) FormalParamListItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamListActual(\n");

        if(FormalParamList!=null)
            buffer.append(FormalParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParamListItem!=null)
            buffer.append(FormalParamListItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListActual]");
        return buffer.toString();
    }
}
