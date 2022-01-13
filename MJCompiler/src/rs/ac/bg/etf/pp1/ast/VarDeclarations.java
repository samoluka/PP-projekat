// generated with ast extension for cup
// version 0.8
// 13/0/2022 19:39:59


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarations extends VarDecl {

    private Type Type;
    private VarDeclItemList VarDeclItemList;

    public VarDeclarations (Type Type, VarDeclItemList VarDeclItemList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclItemList=VarDeclItemList;
        if(VarDeclItemList!=null) VarDeclItemList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclItemList getVarDeclItemList() {
        return VarDeclItemList;
    }

    public void setVarDeclItemList(VarDeclItemList VarDeclItemList) {
        this.VarDeclItemList=VarDeclItemList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclItemList!=null) VarDeclItemList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclItemList!=null) VarDeclItemList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclItemList!=null) VarDeclItemList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarations(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclItemList!=null)
            buffer.append(VarDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarations]");
        return buffer.toString();
    }
}
