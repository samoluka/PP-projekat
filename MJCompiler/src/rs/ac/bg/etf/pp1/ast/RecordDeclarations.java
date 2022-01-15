// generated with ast extension for cup
// version 0.8
// 15/0/2022 14:43:3


package rs.ac.bg.etf.pp1.ast;

public class RecordDeclarations extends RecordDecl {

    private RecordName RecordName;
    private VarListClass VarListClass;

    public RecordDeclarations (RecordName RecordName, VarListClass VarListClass) {
        this.RecordName=RecordName;
        if(RecordName!=null) RecordName.setParent(this);
        this.VarListClass=VarListClass;
        if(VarListClass!=null) VarListClass.setParent(this);
    }

    public RecordName getRecordName() {
        return RecordName;
    }

    public void setRecordName(RecordName RecordName) {
        this.RecordName=RecordName;
    }

    public VarListClass getVarListClass() {
        return VarListClass;
    }

    public void setVarListClass(VarListClass VarListClass) {
        this.VarListClass=VarListClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordName!=null) RecordName.accept(visitor);
        if(VarListClass!=null) VarListClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordName!=null) RecordName.traverseTopDown(visitor);
        if(VarListClass!=null) VarListClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordName!=null) RecordName.traverseBottomUp(visitor);
        if(VarListClass!=null) VarListClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDeclarations(\n");

        if(RecordName!=null)
            buffer.append(RecordName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarListClass!=null)
            buffer.append(VarListClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDeclarations]");
        return buffer.toString();
    }
}
