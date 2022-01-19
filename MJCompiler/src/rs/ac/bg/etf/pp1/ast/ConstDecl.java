// generated with ast extension for cup
// version 0.8
// 19/0/2022 20:34:21


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ConstDeclHeader ConstDeclHeader;
    private ConstDeclItemList ConstDeclItemList;

    public ConstDecl (ConstDeclHeader ConstDeclHeader, ConstDeclItemList ConstDeclItemList) {
        this.ConstDeclHeader=ConstDeclHeader;
        if(ConstDeclHeader!=null) ConstDeclHeader.setParent(this);
        this.ConstDeclItemList=ConstDeclItemList;
        if(ConstDeclItemList!=null) ConstDeclItemList.setParent(this);
    }

    public ConstDeclHeader getConstDeclHeader() {
        return ConstDeclHeader;
    }

    public void setConstDeclHeader(ConstDeclHeader ConstDeclHeader) {
        this.ConstDeclHeader=ConstDeclHeader;
    }

    public ConstDeclItemList getConstDeclItemList() {
        return ConstDeclItemList;
    }

    public void setConstDeclItemList(ConstDeclItemList ConstDeclItemList) {
        this.ConstDeclItemList=ConstDeclItemList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclHeader!=null) ConstDeclHeader.accept(visitor);
        if(ConstDeclItemList!=null) ConstDeclItemList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclHeader!=null) ConstDeclHeader.traverseTopDown(visitor);
        if(ConstDeclItemList!=null) ConstDeclItemList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclHeader!=null) ConstDeclHeader.traverseBottomUp(visitor);
        if(ConstDeclItemList!=null) ConstDeclItemList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(ConstDeclHeader!=null)
            buffer.append(ConstDeclHeader.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclItemList!=null)
            buffer.append(ConstDeclItemList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
