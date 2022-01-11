// generated with ast extension for cup
// version 0.8
// 11/0/2022 16:27:1


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationNonEmptyList extends MethodDeclListNonEmpty {

    private MethodDeclListNonEmpty MethodDeclListNonEmpty;
    private MethodDecl MethodDecl;

    public MethodDeclarationNonEmptyList (MethodDeclListNonEmpty MethodDeclListNonEmpty, MethodDecl MethodDecl) {
        this.MethodDeclListNonEmpty=MethodDeclListNonEmpty;
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.setParent(this);
        this.MethodDecl=MethodDecl;
        if(MethodDecl!=null) MethodDecl.setParent(this);
    }

    public MethodDeclListNonEmpty getMethodDeclListNonEmpty() {
        return MethodDeclListNonEmpty;
    }

    public void setMethodDeclListNonEmpty(MethodDeclListNonEmpty MethodDeclListNonEmpty) {
        this.MethodDeclListNonEmpty=MethodDeclListNonEmpty;
    }

    public MethodDecl getMethodDecl() {
        return MethodDecl;
    }

    public void setMethodDecl(MethodDecl MethodDecl) {
        this.MethodDecl=MethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.accept(visitor);
        if(MethodDecl!=null) MethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseTopDown(visitor);
        if(MethodDecl!=null) MethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseBottomUp(visitor);
        if(MethodDecl!=null) MethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationNonEmptyList(\n");

        if(MethodDeclListNonEmpty!=null)
            buffer.append(MethodDeclListNonEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecl!=null)
            buffer.append(MethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationNonEmptyList]");
        return buffer.toString();
    }
}
