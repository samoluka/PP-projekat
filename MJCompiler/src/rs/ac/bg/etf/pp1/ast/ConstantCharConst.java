// generated with ast extension for cup
// version 0.8
// 20/0/2022 20:45:17


package rs.ac.bg.etf.pp1.ast;

public class ConstantCharConst extends FactorForConst {

    private Character val;

    public ConstantCharConst (Character val) {
        this.val=val;
    }

    public Character getVal() {
        return val;
    }

    public void setVal(Character val) {
        this.val=val;
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
        buffer.append("ConstantCharConst(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantCharConst]");
        return buffer.toString();
    }
}
