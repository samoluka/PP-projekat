// generated with ast extension for cup
// version 0.8
// 17/0/2022 20:44:38


package rs.ac.bg.etf.pp1.ast;

public class MethodCall extends Factor {

    private DesignatorForMethodCall DesignatorForMethodCall;
    private ActualPars ActualPars;

    public MethodCall (DesignatorForMethodCall DesignatorForMethodCall, ActualPars ActualPars) {
        this.DesignatorForMethodCall=DesignatorForMethodCall;
        if(DesignatorForMethodCall!=null) DesignatorForMethodCall.setParent(this);
        this.ActualPars=ActualPars;
        if(ActualPars!=null) ActualPars.setParent(this);
    }

    public DesignatorForMethodCall getDesignatorForMethodCall() {
        return DesignatorForMethodCall;
    }

    public void setDesignatorForMethodCall(DesignatorForMethodCall DesignatorForMethodCall) {
        this.DesignatorForMethodCall=DesignatorForMethodCall;
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
        if(DesignatorForMethodCall!=null) DesignatorForMethodCall.accept(visitor);
        if(ActualPars!=null) ActualPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorForMethodCall!=null) DesignatorForMethodCall.traverseTopDown(visitor);
        if(ActualPars!=null) ActualPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorForMethodCall!=null) DesignatorForMethodCall.traverseBottomUp(visitor);
        if(ActualPars!=null) ActualPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodCall(\n");

        if(DesignatorForMethodCall!=null)
            buffer.append(DesignatorForMethodCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualPars!=null)
            buffer.append(ActualPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodCall]");
        return buffer.toString();
    }
}
