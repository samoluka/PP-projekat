// generated with ast extension for cup
// version 0.8
// 20/0/2022 9:42:56


package rs.ac.bg.etf.pp1.ast;

public class NoVarDeclListClLass extends VarListClass {

    public NoVarDeclListClLass () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoVarDeclListClLass(\n");

        buffer.append(tab);
        buffer.append(") [NoVarDeclListClLass]");
        return buffer.toString();
    }
}
