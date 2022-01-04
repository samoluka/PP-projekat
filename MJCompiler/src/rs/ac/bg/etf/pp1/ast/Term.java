// generated with ast extension for cup
// version 0.8
// 4/0/2022 22:48:8


package rs.ac.bg.etf.pp1.ast;

public class Term implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Const Const;

    public Term (Const Const) {
        this.Const=Const;
        if(Const!=null) Const.setParent(this);
    }

    public Const getConst() {
        return Const;
    }

    public void setConst(Const Const) {
        this.Const=Const;
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
        if(Const!=null) Const.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Const!=null) Const.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Const!=null) Const.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term(\n");

        if(Const!=null)
            buffer.append(Const.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term]");
        return buffer.toString();
    }
}
