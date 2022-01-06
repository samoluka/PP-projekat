// generated with ast extension for cup
// version 0.8
// 7/0/2022 1:18:44


package rs.ac.bg.etf.pp1.ast;

public class VarClassNonError extends VarClass {

    private Type Type;
    private VarItemListClass VarItemListClass;

    public VarClassNonError (Type Type, VarItemListClass VarItemListClass) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarItemListClass=VarItemListClass;
        if(VarItemListClass!=null) VarItemListClass.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarItemListClass getVarItemListClass() {
        return VarItemListClass;
    }

    public void setVarItemListClass(VarItemListClass VarItemListClass) {
        this.VarItemListClass=VarItemListClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarItemListClass!=null) VarItemListClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarItemListClass!=null) VarItemListClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarItemListClass!=null) VarItemListClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarClassNonError(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarItemListClass!=null)
            buffer.append(VarItemListClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarClassNonError]");
        return buffer.toString();
    }
}
