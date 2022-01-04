// generated with ast extension for cup
// version 0.8
// 4/0/2022 23:6:39


package rs.ac.bg.etf.pp1.ast;

public class RecordDeclarationsItemList extends RecordDeclItemList {

    private RecordDeclItemList RecordDeclItemList;
    private RecordDeclItem RecordDeclItem;

    public RecordDeclarationsItemList (RecordDeclItemList RecordDeclItemList, RecordDeclItem RecordDeclItem) {
        this.RecordDeclItemList=RecordDeclItemList;
        if(RecordDeclItemList!=null) RecordDeclItemList.setParent(this);
        this.RecordDeclItem=RecordDeclItem;
        if(RecordDeclItem!=null) RecordDeclItem.setParent(this);
    }

    public RecordDeclItemList getRecordDeclItemList() {
        return RecordDeclItemList;
    }

    public void setRecordDeclItemList(RecordDeclItemList RecordDeclItemList) {
        this.RecordDeclItemList=RecordDeclItemList;
    }

    public RecordDeclItem getRecordDeclItem() {
        return RecordDeclItem;
    }

    public void setRecordDeclItem(RecordDeclItem RecordDeclItem) {
        this.RecordDeclItem=RecordDeclItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordDeclItemList!=null) RecordDeclItemList.accept(visitor);
        if(RecordDeclItem!=null) RecordDeclItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordDeclItemList!=null) RecordDeclItemList.traverseTopDown(visitor);
        if(RecordDeclItem!=null) RecordDeclItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordDeclItemList!=null) RecordDeclItemList.traverseBottomUp(visitor);
        if(RecordDeclItem!=null) RecordDeclItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDeclarationsItemList(\n");

        if(RecordDeclItemList!=null)
            buffer.append(RecordDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RecordDeclItem!=null)
            buffer.append(RecordDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDeclarationsItemList]");
        return buffer.toString();
    }
}
