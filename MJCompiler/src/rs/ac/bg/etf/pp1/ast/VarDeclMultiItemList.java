// generated with ast extension for cup
// version 0.8
// 4/0/2022 23:6:39


package rs.ac.bg.etf.pp1.ast;

public class VarDeclMultiItemList extends VarDeclItemList {

    private VarDeclItemList VarDeclItemList;
    private VarDeclDefinition VarDeclDefinition;

    public VarDeclMultiItemList (VarDeclItemList VarDeclItemList, VarDeclDefinition VarDeclDefinition) {
        this.VarDeclItemList=VarDeclItemList;
        if(VarDeclItemList!=null) VarDeclItemList.setParent(this);
        this.VarDeclDefinition=VarDeclDefinition;
        if(VarDeclDefinition!=null) VarDeclDefinition.setParent(this);
    }

    public VarDeclItemList getVarDeclItemList() {
        return VarDeclItemList;
    }

    public void setVarDeclItemList(VarDeclItemList VarDeclItemList) {
        this.VarDeclItemList=VarDeclItemList;
    }

    public VarDeclDefinition getVarDeclDefinition() {
        return VarDeclDefinition;
    }

    public void setVarDeclDefinition(VarDeclDefinition VarDeclDefinition) {
        this.VarDeclDefinition=VarDeclDefinition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclItemList!=null) VarDeclItemList.accept(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclItemList!=null) VarDeclItemList.traverseTopDown(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclItemList!=null) VarDeclItemList.traverseBottomUp(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclMultiItemList(\n");

        if(VarDeclItemList!=null)
            buffer.append(VarDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclDefinition!=null)
            buffer.append(VarDeclDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclMultiItemList]");
        return buffer.toString();
    }
}
