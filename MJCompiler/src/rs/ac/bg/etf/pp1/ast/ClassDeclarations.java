// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:28:29


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclarations extends ClassDecl {

    private String className;
    private ClassExtends ClassExtends;
    private VarDeclList VarDeclList;
    private ClassMethodDeclItemList ClassMethodDeclItemList;

    public ClassDeclarations (String className, ClassExtends ClassExtends, VarDeclList VarDeclList, ClassMethodDeclItemList ClassMethodDeclItemList) {
        this.className=className;
        this.ClassExtends=ClassExtends;
        if(ClassExtends!=null) ClassExtends.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
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

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
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
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExtends!=null) ClassExtends.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(ClassMethodDeclItemList!=null) ClassMethodDeclItemList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExtends!=null) ClassExtends.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
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

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
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
