package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}

	public static void main(String[] args) throws Exception {

		Logger log = Logger.getLogger(MJParserTest.class);

		Reader br = null;
		try {
			File sourceCode = new File("test/program.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);

			MJParser p = new MJParser(lexer);
			Symbol s = p.parse(); // pocetak parsiranja
			Program prog = (Program) (s.value);
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			// ispis prepoznatih programskih konstrukcija
			RuleVisitor v = new RuleVisitor();
			prog.traverseBottomUp(v);

			log.info(" Print count calls = " + v.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + v.varDeclCount);

		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
		}

	}

}




//
//VarDeclForClass ::= (VarDeclForClassarations) Type VarDeclForClassItemList SEMI
//		|  
//		(VarDeclForClassError) error:e SEMI
//        {:
//        	parser.report_error("Uspesan oporavak od greske na liniji " + eleft + " pri definisanju globalne promenljive do ;", null);
//        :}
//        |
//        (VarDeclForClassLbraceError) Type IDENT error:e LBRACE
//        {:
//        	parser.report_error("Uspesan oporavak od greske na liniji " + eleft + " pri definisanju globalne promenljive do ;", null);
//        :}
//        ;
//VarDeclForClassItemList ::= (VarDeclForClassMultiItemList) VarDeclForClassItemList COMMA VarDeclDefinition
//                    |    
//                    (VarDeclForClassSinglItemList) VarDeclDefinition
//                    |    
//                    (VarDeclForClassItemListLBRACEError) Type error:e LBRACE
//                    {:
//                    	parser.report_error("Uspesan oporavak od greske na liniji " + eleft + " pri definisanju globalne promenljive do { ili ;", null);
//                    :}
//                    ;
//
//VarDeclForClassList ::= (VarDeclForClassarationsList) VarDeclForClassList VarDeclForClassListItem
//                    |    
//                    (NoVarDeclForClassList) /* epsilon */
//                    ;
//VarDeclForClassListItem ::= (VarDeclForClassListItem) VarDeclForClass;
//
/*
Unmatched ::= (UnmatchedIf) IF Expr Statement
		 	|
		 	(UnmatchedIfElse) IF Expr Matched ELSE Unmatched
		 	;
		 

Matched ::= (Assignment) Designator:dest EQUAL Expr:e SEMI
		   |
		   (ErrorStmt) error SEMI:l
		   {: parser.report_error("Izvrsen oporavak do ; u liniji " + lleft, null);  :}
		   |
		   (PrintStmt) PRINT LPAREN Expr RPAREN SEMI
		   |
		   (ReturnExpr) RETURN Expr:t SEMI
		   |
		   (ReturnNoExpr) RETURN SEMI
		   |
		   (MatchedStatement) IF Expr Matched ELSE Matched
		   ;
*/		
