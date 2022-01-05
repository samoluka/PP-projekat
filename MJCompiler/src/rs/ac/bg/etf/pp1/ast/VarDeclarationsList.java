// generated with ast extension for cup
// version 0.8
// 5/0/2022 16:21:26


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationsList extends VarDeclList {

    private VarDeclList VarDeclList;
    private VarDeclListItem VarDeclListItem;

    public VarDeclarationsList (VarDeclList VarDeclList, VarDeclListItem VarDeclListItem) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.VarDeclListItem=VarDeclListItem;
        if(VarDeclListItem!=null) VarDeclListItem.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public VarDeclListItem getVarDeclListItem() {
        return VarDeclListItem;
    }

    public void setVarDeclListItem(VarDeclListItem VarDeclListItem) {
        this.VarDeclListItem=VarDeclListItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(VarDeclListItem!=null) VarDeclListItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(VarDeclListItem!=null) VarDeclListItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(VarDeclListItem!=null) VarDeclListItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationsList(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListItem!=null)
            buffer.append(VarDeclListItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationsList]");
        return buffer.toString();
    }
}
