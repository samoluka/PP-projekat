// generated with ast extension for cup
// version 0.8
// 15/0/2022 2:8:43


package rs.ac.bg.etf.pp1.ast;

public class OnlyMethodClassMethodDeclaration extends ClassMethodDeclItemList {

    private MethodDeclListNonEmpty MethodDeclListNonEmpty;

    public OnlyMethodClassMethodDeclaration (MethodDeclListNonEmpty MethodDeclListNonEmpty) {
        this.MethodDeclListNonEmpty=MethodDeclListNonEmpty;
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.setParent(this);
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
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclListNonEmpty!=null) MethodDeclListNonEmpty.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OnlyMethodClassMethodDeclaration(\n");

        if(MethodDeclListNonEmpty!=null)
            buffer.append(MethodDeclListNonEmpty.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OnlyMethodClassMethodDeclaration]");
        return buffer.toString();
    }
}
