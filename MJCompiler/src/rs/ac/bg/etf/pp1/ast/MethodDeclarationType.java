// generated with ast extension for cup
// version 0.8
// 5/0/2022 1:0:35


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationType extends MethodDecl {

    private Type Type;
    private String methodName;
    private MethodDeclItem MethodDeclItem;

    public MethodDeclarationType (Type Type, String methodName, MethodDeclItem MethodDeclItem) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.methodName=methodName;
        this.MethodDeclItem=MethodDeclItem;
        if(MethodDeclItem!=null) MethodDeclItem.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName=methodName;
    }

    public MethodDeclItem getMethodDeclItem() {
        return MethodDeclItem;
    }

    public void setMethodDeclItem(MethodDeclItem MethodDeclItem) {
        this.MethodDeclItem=MethodDeclItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(MethodDeclItem!=null) MethodDeclItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(MethodDeclItem!=null) MethodDeclItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(MethodDeclItem!=null) MethodDeclItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationType(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methodName);
        buffer.append("\n");

        if(MethodDeclItem!=null)
            buffer.append(MethodDeclItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationType]");
        return buffer.toString();
    }
}
