// generated with ast extension for cup
// version 0.8
// 11/0/2022 17:10:6


package rs.ac.bg.etf.pp1.ast;

public class VarListClassNonEmpty extends VarListClass {

    private VarListClass VarListClass;
    private VarClass VarClass;

    public VarListClassNonEmpty (VarListClass VarListClass, VarClass VarClass) {
        this.VarListClass=VarListClass;
        if(VarListClass!=null) VarListClass.setParent(this);
        this.VarClass=VarClass;
        if(VarClass!=null) VarClass.setParent(this);
    }

    public VarListClass getVarListClass() {
        return VarListClass;
    }

    public void setVarListClass(VarListClass VarListClass) {
        this.VarListClass=VarListClass;
    }

    public VarClass getVarClass() {
        return VarClass;
    }

    public void setVarClass(VarClass VarClass) {
        this.VarClass=VarClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarListClass!=null) VarListClass.accept(visitor);
        if(VarClass!=null) VarClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarListClass!=null) VarListClass.traverseTopDown(visitor);
        if(VarClass!=null) VarClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarListClass!=null) VarListClass.traverseBottomUp(visitor);
        if(VarClass!=null) VarClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListClassNonEmpty(\n");

        if(VarListClass!=null)
            buffer.append(VarListClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarClass!=null)
            buffer.append(VarClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListClassNonEmpty]");
        return buffer.toString();
    }
}
