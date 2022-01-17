// generated with ast extension for cup
// version 0.8
// 17/0/2022 2:3:6


package rs.ac.bg.etf.pp1.ast;

public class FullClassMethodDeclarationsItemList extends ClassMethodDeclItemList {

    private ConstructorDecl ConstructorDecl;
    private MethodDeclListNonEmpty MethodDeclListNonEmpty;

    public FullClassMethodDeclarationsItemList (ConstructorDecl ConstructorDecl, MethodDeclListNonEmpty MethodDeclListNonEmpty) {
        this.ConstructorDecl=ConstructorDecl;
        if(ConstructorDecl!=null) ConstructorDecl.setParent(this);
        this.MethodDeclListNonEmpty=MethodDeclListNonEmpty;
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.setParent(this);
    }

    public ConstructorDecl getConstructorDecl() {
        return ConstructorDecl;
    }

    public void setConstructorDecl(ConstructorDecl ConstructorDecl) {
        this.ConstructorDecl=ConstructorDecl;
    }

    public MethodDeclListNonEmpty getMethodDeclListNonEmpty() {
        return MethodDeclListNonEmpty;
    }

    public void setMethodDeclListNonEmpty(MethodDeclListNonEmpty MethodDeclListNonEmpty) {
        this.MethodDeclListNonEmpty=MethodDeclListNonEmpty;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorDecl!=null) ConstructorDecl.accept(visitor);
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorDecl!=null) ConstructorDecl.traverseTopDown(visitor);
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorDecl!=null) ConstructorDecl.traverseBottomUp(visitor);
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FullClassMethodDeclarationsItemList(\n");

        if(ConstructorDecl!=null)
            buffer.append(ConstructorDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclListNonEmpty!=null)
            buffer.append(MethodDeclListNonEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FullClassMethodDeclarationsItemList]");
        return buffer.toString();
    }
}
