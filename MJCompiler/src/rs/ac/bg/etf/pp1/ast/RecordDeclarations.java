// generated with ast extension for cup
// version 0.8
// 4/0/2022 23:6:39


package rs.ac.bg.etf.pp1.ast;

public class RecordDeclarations extends RecordDecl {

    private String name;
    private RecordDeclItemList RecordDeclItemList;

    public RecordDeclarations (String name, RecordDeclItemList RecordDeclItemList) {
        this.name=name;
        this.RecordDeclItemList=RecordDeclItemList;
        if(RecordDeclItemList!=null) RecordDeclItemList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public RecordDeclItemList getRecordDeclItemList() {
        return RecordDeclItemList;
    }

    public void setRecordDeclItemList(RecordDeclItemList RecordDeclItemList) {
        this.RecordDeclItemList=RecordDeclItemList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(RecordDeclItemList!=null) RecordDeclItemList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordDeclItemList!=null) RecordDeclItemList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordDeclItemList!=null) RecordDeclItemList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDeclarations(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(RecordDeclItemList!=null)
            buffer.append(RecordDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDeclarations]");
        return buffer.toString();
    }
}
