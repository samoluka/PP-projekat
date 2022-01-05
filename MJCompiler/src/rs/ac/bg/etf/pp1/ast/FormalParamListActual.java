// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:0:35


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListActual extends FormalParamList {

    private FormalParamList FormalParamList;
    private Type Type;
    private VarDeclDefinition VarDeclDefinition;

    public FormalParamListActual (FormalParamList FormalParamList, Type Type, VarDeclDefinition VarDeclDefinition) {
        this.FormalParamList=FormalParamList;
        if(FormalParamList!=null) FormalParamList.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclDefinition=VarDeclDefinition;
        if(VarDeclDefinition!=null) VarDeclDefinition.setParent(this);
    }

    public FormalParamList getFormalParamList() {
        return FormalParamList;
    }

    public void setFormalParamList(FormalParamList FormalParamList) {
        this.FormalParamList=FormalParamList;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(FormalParamList!=null) FormalParamList.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamList!=null) FormalParamList.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamList!=null) FormalParamList.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclDefinition!=null) VarDeclDefinition.traverseBottomUp(visitor);
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

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclDefinition!=null)
            buffer.append(VarDeclDefinition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListActual]");
        return buffer.toString();
    }
}