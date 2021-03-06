package wacc.middleware.ast_nodes.prog_ast;

import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.IMPORT;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.import_ast.ImportAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.symbol_table.SymbolTable;

// AST Node for the entire program.

public class ProgAST extends NodeAST {

  private final NodeASTList<ImportAST> importASTS;
  private final NodeASTList<FunctionDeclarationAST> functionDeclarationASTS;
  private final NodeASTList<ClassDefinitionAST> classDefinitionASTS;
  private StatementAST statementAST;
  private String filename;
  private SymbolTable scopeST;


  public ProgAST(List<WaccError> errors, ParserRuleContext ctx,
      String filename, NodeASTList<ImportAST> importASTS,
      NodeASTList<ClassDefinitionAST> classDefinitionASTS,
      NodeASTList<FunctionDeclarationAST> functionDeclarationASTS,
      StatementAST statementAST) {
    super(errors, ctx);
    this.functionDeclarationASTS = functionDeclarationASTS;
    this.classDefinitionASTS = classDefinitionASTS;
    this.statementAST = statementAST;
    this.importASTS = importASTS;
    this.filename = filename.replace(".wacc", "");
  }

  public NodeASTList<ImportAST> getImportASTS() {
    return importASTS;
  }

  public String getFilename() {
    return filename;
  }

  @Override
  public void check() {
    SymbolTable.reset();
    scopeST = ST = SymbolTable.TopSymbolTable();

    // add current file as the global import
    ST.add(filename, new IMPORT("MAIN"));

    // Go through all imported statements and record them in the top symbol table
    for (ImportAST importAST : importASTS) {
      // do semantic checks to make sure import is valid
      importAST.check();

      ProgAST importedProg = importAST.getProgAST();

      if (importedProg != null) {
        // add the imported classes to beginning of the classDefnList
        classDefinitionASTS.addAll(0,
            importedProg.getClassDefinitionASTS());

        // add the imported functions to beginning of the funcDeclrList
        functionDeclarationASTS.addAll(0,
            importedProg.getFunctionDeclarationASTS());

        // append the statement bodies together using a chained statement
        statementAST = new ChainedStatementAST(errors, statementAST.ctx,
            importedProg.getStatementAST(), statementAST);
      }
    }

    //go through all declared classes and record them in the top symbol table
    for (ClassDefinitionAST classDefinitionAST : classDefinitionASTS) {
      classDefinitionAST.check();
    }

    // Go through any functions declared, and record them in the top symbol table.
    // This is done in a separate pass because a function body may call other functions
    // declared later on.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.check();
    }

    // Now go through the actual function bodies.
    for (FunctionDeclarationAST func : functionDeclarationASTS) {
      func.checkStatement();
    }

    // Now check all the statements.
    statementAST.check();
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return visitor.visit(this);
  }

  public NodeASTList<FunctionDeclarationAST> getFunctionDeclarationASTS() {
    return functionDeclarationASTS;
  }


  public NodeASTList<ClassDefinitionAST> getClassDefinitionASTS() {
    return classDefinitionASTS;
  }

  public StatementAST getStatementAST() {
    return statementAST;
  }

  public SymbolTable getScopeST() {
    return scopeST;
  }
}
