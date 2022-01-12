// generated with ast extension for cup
// version 0.8
// 12/0/2022 23:3:38


package rs.ac.bg.etf.pp1.ast;

public class VarErrorClass extends VarClass {

    private VarClassErrorEndPoint VarClassErrorEndPoint;

    public VarErrorClass (VarClassErrorEndPoint VarClassErrorEndPoint) {
        this.VarClassErrorEndPoint=VarClassErrorEndPoint;
        if(VarClassErrorEndPoint!=null) VarClassErrorEndPoint.setParent(this);
    }

    public VarClassErrorEndPoint getVarClassErrorEndPoint() {
        return VarClassErrorEndPoint;
    }

    public void setVarClassErrorEndPoint(VarClassErrorEndPoint VarClassErrorEndPoint) {
        this.VarClassErrorEndPoint=VarClassErrorEndPoint;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarClassErrorEndPoint!=null) VarClassErrorEndPoint.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarClassErrorEndPoint!=null) VarClassErrorEndPoint.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarClassErrorEndPoint!=null) VarClassErrorEndPoint.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarErrorClass(\n");

        if(VarClassErrorEndPoint!=null)
            buffer.append(VarClassErrorEndPoint.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarErrorClass]");
        return buffer.toString();
    }
}
