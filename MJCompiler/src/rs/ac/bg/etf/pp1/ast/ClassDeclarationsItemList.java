// generated with ast extension for cup
// version 0.8
// 4/0/2022 23:6:39


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarationsItemList extends ClassVarDeclItemList {

    private ClassVarDeclItemList ClassVarDeclItemList;
    private ClassDeclItem ClassDeclItem;

    public ClassDeclarationsItemList (ClassVarDeclItemList ClassVarDeclItemList, ClassDeclItem ClassDeclItem) {
        this.ClassVarDeclItemList=ClassVarDeclItemList;
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.setParent(this);
        this.ClassDeclItem=ClassDeclItem;
        if(ClassDeclItem!=null) ClassDeclItem.setParent(this);
    }

    public ClassVarDeclItemList getClassVarDeclItemList() {
        return ClassVarDeclItemList;
    }

    public void setClassVarDeclItemList(ClassVarDeclItemList ClassVarDeclItemList) {
        this.ClassVarDeclItemList=ClassVarDeclItemList;
    }

    public ClassDeclItem getClassDeclItem() {
        return ClassDeclItem;
    }

    public void setClassDeclItem(ClassDeclItem ClassDeclItem) {
        this.ClassDeclItem=ClassDeclItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.accept(visitor);
        if(ClassDeclItem!=null) ClassDeclItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.traverseTopDown(visitor);
        if(ClassDeclItem!=null) ClassDeclItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.traverseBottomUp(visitor);
        if(ClassDeclItem!=null) ClassDeclItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarationsItemList(\n");

        if(ClassVarDeclItemList!=null)
            buffer.append(ClassVarDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclItem!=null)
            buffer.append(ClassDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarationsItemList]");
        return buffer.toString();
    }
}
