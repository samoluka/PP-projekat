package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.AssignmentDeclaration;
import rs.ac.bg.etf.pp1.ast.ClassDeclarations;
import rs.ac.bg.etf.pp1.ast.Definition;
import rs.ac.bg.etf.pp1.ast.DefinitionArray;
import rs.ac.bg.etf.pp1.ast.RecordDeclarations;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

    private int constCounter = 0;
    private int variableCounter = 0;
    private int arrayCounter = 0;
    private int classDeclarationCounter = 0;
    private int recordDeclarationCounter = 0;

    public int getConstCounter() {
	return constCounter;
    }

    public int getVariableCounter() {
	return variableCounter;
    }

    public int getArrayCounter() {
	return arrayCounter;
    }

    public int getClassDeclarationCounter() {
	return classDeclarationCounter;
    }

    public int getRecordDeclarationCounter() {
	return recordDeclarationCounter;
    }

    public void visit(AssignmentDeclaration assignmentDeclaration) {
	constCounter++;
    }

    public void visit(Definition definition) {
	variableCounter++;
    }

    public void visit(DefinitionArray definitionArray) {
	arrayCounter++;
    }

    public void visit(ClassDeclarations classDeclarations) {
	classDeclarationCounter++;
    }

    public void visit(RecordDeclarations recordDeclarations) {
	recordDeclarationCounter++;
    }

}
