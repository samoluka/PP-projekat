// generated with ast extension for cup
// version 0.8
// 4/0/2022 23:6:39


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarations extends ClassDecl {

    private String className;
    private ClassExtends ClassExtends;
    private ClassVarDeclItemList ClassVarDeclItemList;
    private ClassMethodDeclItemList ClassMethodDeclItemList;

    public ClassDeclarations (String className, ClassExtends ClassExtends, ClassVarDeclItemList ClassVarDeclItemList, ClassMethodDeclItemList ClassMethodDeclItemList) {
        this.className=className;
        this.ClassExtends=ClassExtends;
        if(ClassExtends!=null) ClassExtends.setParent(this);
        this.ClassVarDeclItemList=ClassVarDeclItemList;
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.setParent(this);
        this.ClassMethodDeclItemList=ClassMethodDeclItemList;
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ClassExtends getClassExtends() {
        return ClassExtends;
    }

    public void setClassExtends(ClassExtends ClassExtends) {
        this.ClassExtends=ClassExtends;
    }

    public ClassVarDeclItemList getClassVarDeclItemList() {
        return ClassVarDeclItemList;
    }

    public void setClassVarDeclItemList(ClassVarDeclItemList ClassVarDeclItemList) {
        this.ClassVarDeclItemList=ClassVarDeclItemList;
    }

    public ClassMethodDeclItemList getClassMethodDeclItemList() {
        return ClassMethodDeclItemList;
    }

    public void setClassMethodDeclItemList(ClassMethodDeclItemList ClassMethodDeclItemList) {
        this.ClassMethodDeclItemList=ClassMethodDeclItemList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassExtends!=null) ClassExtends.accept(visitor);
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.accept(visitor);
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExtends!=null) ClassExtends.traverseTopDown(visitor);
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.traverseTopDown(visitor);
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExtends!=null) ClassExtends.traverseBottomUp(visitor);
        if(ClassVarDeclItemList!=null) ClassVarDeclItemList.traverseBottomUp(visitor);
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclarations(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ClassExtends!=null)
            buffer.append(ClassExtends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclItemList!=null)
            buffer.append(ClassVarDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethodDeclItemList!=null)
            buffer.append(ClassMethodDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclarations]");
        return buffer.toString();
    }
}
