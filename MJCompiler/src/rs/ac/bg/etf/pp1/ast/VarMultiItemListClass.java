// generated with ast extension for cup
// version 0.8
// 22/0/2022 23:29:10


package rs.ac.bg.etf.pp1.ast;

public class VarMultiItemListClass extends VarItemListClass {

    private VarItemListClass VarItemListClass;
    private VarDeclDefinition VarDeclDefinition;

    public VarMultiItemListClass (VarItemListClass VarItemListClass, VarDeclDefinition VarDeclDefinition) {
        this.VarItemListClass=VarItemListClass;
        if(VarItemListClass!=null) VarItemListClass.setParent(this);
        this.VarDeclDefinition=VarDeclDefinition;
        if(VarDeclDefinition!=null) VarDeclDefinition.setParent(this);
    }

    public VarItemListClass getVarItemListClass() {
        return VarItemListClass;
    }

    public void setVarItemListClass(VarItemListClass VarItemListClass) {
        this.VarItemListClass=VarItemListClass;
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
        if(VarItemListClass!=null) VarItemListClass.accept(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarItemListClass!=null) VarItemListClass.traverseTopDown(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarItemListClass!=null) VarItemListClass.traverseBottomUp(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarMultiItemListClass(\n");

        if(VarItemListClass!=null)
            buffer.append(VarItemListClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclDefinition!=null)
            buffer.append(VarDeclDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarMultiItemListClass]");
        return buffer.toString();
    }
}
