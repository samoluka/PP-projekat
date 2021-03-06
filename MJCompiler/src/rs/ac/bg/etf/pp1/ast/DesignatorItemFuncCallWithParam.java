// generated with ast extension for cup
// version 0.8
// 25/0/2022 16:20:46


package rs.ac.bg.etf.pp1.ast;

public class DesignatorItemFuncCallWithParam extends DesignatorStatement {

    private MethodNameDesignator MethodNameDesignator;
    private ActualPars ActualPars;

    public DesignatorItemFuncCallWithParam (MethodNameDesignator MethodNameDesignator, ActualPars ActualPars) {
        this.MethodNameDesignator=MethodNameDesignator;
        if(MethodNameDesignator!=null) MethodNameDesignator.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public MethodNameDesignator getMethodNameDesignator() {
        return MethodNameDesignator;
    }

    public void setMethodNameDesignator(MethodNameDesignator MethodNameDesignator) {
        this.MethodNameDesignator=MethodNameDesignator;
    }

    public ActualPars getActualPars() {
        return ActualPars;
    }

    public void setActualPars(ActualPars ActualPars) {
        this.ActualPars=ActualPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodNameDesignator!=null) MethodNameDesignator.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodNameDesignator!=null) MethodNameDesignator.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodNameDesignator!=null) MethodNameDesignator.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorItemFuncCallWithParam(\n");

        if(MethodNameDesignator!=null)
            buffer.append(MethodNameDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorItemFuncCallWithParam]");
        return buffer.toString();
    }
}
