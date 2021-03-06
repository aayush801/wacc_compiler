package wacc.middleware.ast_nodes.import_ast;

import antlr.WaccParser.ProgContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import wacc.WaccCompiler;
import wacc.errors.WaccError;
import wacc.errors.semantic_errors.DuplicateIdentifier;
import wacc.errors.semantic_errors.ImportBroken;
import wacc.errors.semantic_errors.ImportNotFound;
import wacc.errors.semantic_errors.InvalidImport;
import wacc.frontend.identifier_objects.IDENTIFIER;
import wacc.frontend.identifier_objects.IMPORT;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.WaccASTParser;
import wacc.middleware.ast_nodes.prog_ast.ProgAST;

public class ImportAST extends NodeAST {

  private final String filename;
  private final Path relativePath;
  private IMPORT importObj;
  private ProgAST progAST;

  public ImportAST(List<WaccError> errors, ParserRuleContext ctx,
      String filename, Path relativePath) {
    super(errors, ctx);
    // removes .wacc ext from filename
    this.filename = filename.replace(".wacc", "");
    this.relativePath = relativePath;
  }

  @Override
  public void check() {

    IDENTIFIER obj = ST.lookupAll(filename);

    if (obj != null) {
      // if trying to import current file

      if (obj instanceof IMPORT) {
        if (((IMPORT) obj).getFilename().equals("MAIN")) {
          addError(new ImportNotFound(ctx, filename));
          return;
        }
        // if the file has already been imported then ignore it
        return;
      }
      // if the import name is clashing with another object
      addError(new DuplicateIdentifier(ctx));
      return;
    }

    // check if the imported file is a wacc file
    String filepath = (relativePath == null ? "" : relativePath + "/") + filename + ".wacc";

    File importedFile = new File(filepath);

    if (!importedFile.isFile()) {
      addError(new InvalidImport(ctx, filepath));
      return;
    }

    // create a new instance of the compiler to parse the imported code
    WaccCompiler compiler = null;
    try {
      compiler = new WaccCompiler(new FileInputStream(importedFile));
    } catch (IOException e) {
      addError(new ImportNotFound(ctx, filename));
      return;
    }

    // try to parse the syntax of the file
    ProgContext parseTree = compiler.parseSyntactics();

    if (compiler.hasErrors()) {
      //if the imported file is broken
      addError(new ImportBroken(ctx, compiler.getErrors()));
      return;
    }

    // build up ast tree for imported prog
    WaccASTParser semanticParser = new WaccASTParser(filename, relativePath, errors);
    progAST = (ProgAST) semanticParser.visit(parseTree);

    importObj = new IMPORT(filename);

    // add imported filename to keywords
    ST.add(filename, importObj);
  }

  public ProgAST getProgAST() {
    return progAST;
  }

  @Override
  public <T> T accept(NodeASTVisitor<? extends T> visitor) {
    return null;
  }
}
