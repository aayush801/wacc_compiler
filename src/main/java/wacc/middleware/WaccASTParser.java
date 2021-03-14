package wacc.middleware;

import antlr.WaccParser;
import antlr.WaccParser.ArgListContext;
import antlr.WaccParser.ArrayContext;
import antlr.WaccParser.ArrayElemContext;
import antlr.WaccParser.ArrayTypeContext;
import antlr.WaccParser.AssignIdentContext;
import antlr.WaccParser.AssignLHSContext;
import antlr.WaccParser.AssignRHSContext;
import antlr.WaccParser.AssignVarsContext;
import antlr.WaccParser.BaseTypeContext;
import antlr.WaccParser.BeginStatContext;
import antlr.WaccParser.BoolLiterContext;
import antlr.WaccParser.CharLiterContext;
import antlr.WaccParser.ClassDefContext;
import antlr.WaccParser.DoWhileContext;
import antlr.WaccParser.ExitStatContext;
import antlr.WaccParser.ExprContext;
import antlr.WaccParser.ForLoopContext;
import antlr.WaccParser.FreeCallContext;
import antlr.WaccParser.FuncCallContext;
import antlr.WaccParser.FuncDeclContext;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParser.IfThenElseContext;
import antlr.WaccParser.ImportsContext;
import antlr.WaccParser.IntLiterContext;
import antlr.WaccParser.NewObjectContext;
import antlr.WaccParser.NewPairContext;
import antlr.WaccParser.PairElemContext;
import antlr.WaccParser.PairElemTypeContext;
import antlr.WaccParser.PairLiterContext;
import antlr.WaccParser.PairTypeContext;
import antlr.WaccParser.ParamContext;
import antlr.WaccParser.ParamListContext;
import antlr.WaccParser.PointerElemContext;
import antlr.WaccParser.PointerTypeContext;
import antlr.WaccParser.PrintCallContext;
import antlr.WaccParser.PrintlnCallContext;
import antlr.WaccParser.ProgContext;
import antlr.WaccParser.ReadCallContext;
import antlr.WaccParser.ReturnStatContext;
import antlr.WaccParser.SeperateStatContext;
import antlr.WaccParser.SizeOfCallContext;
import antlr.WaccParser.SkipStatContext;
import antlr.WaccParser.StrLiterContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParser.WhileDoContext;
import antlr.WaccParserBaseVisitor;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import wacc.errors.WaccError;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.frontend.identifier_objects.basic_types.PAIR;
import wacc.frontend.identifier_objects.basic_types.STR;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.TypeAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.MethodCallAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.MethodDeclarationAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.NewObjectAST;
import wacc.middleware.ast_nodes.expression_ast.BinOpExprAST;
import wacc.middleware.ast_nodes.expression_ast.IdentifierAST;
import wacc.middleware.ast_nodes.expression_ast.LiteralsAST;
import wacc.middleware.ast_nodes.expression_ast.SizeOfAST;
import wacc.middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;
import wacc.middleware.ast_nodes.import_ast.ImportAST;
import wacc.middleware.ast_nodes.pair_ast.NewPairAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.pointers_ast.PointerElemAST;
import wacc.middleware.ast_nodes.prog_ast.ProgAST;
import wacc.middleware.ast_nodes.statement_ast.AssignmentAST;
import wacc.middleware.ast_nodes.statement_ast.BeginAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.ast_nodes.statement_ast.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.statement_ast.ExitAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ForAST;
import wacc.middleware.ast_nodes.statement_ast.FreeAST;
import wacc.middleware.ast_nodes.statement_ast.IfElseAST;
import wacc.middleware.ast_nodes.statement_ast.LHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.PrintAST;
import wacc.middleware.ast_nodes.statement_ast.RHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.ReadAST;
import wacc.middleware.ast_nodes.statement_ast.ReturnAST;
import wacc.middleware.ast_nodes.statement_ast.SkipAST;
import wacc.middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.WhileAST;
import wacc.middleware.ast_nodes.types_ast.ArrayTypeAST;
import wacc.middleware.ast_nodes.types_ast.BaseTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairElemTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairTypeAST;
import wacc.middleware.ast_nodes.types_ast.PointerTypeAST;

public class WaccASTParser extends WaccParserBaseVisitor<NodeAST> {

  private final Path relativePath;
  private final List<WaccError> semanticErrors;
  private final String filename;

  public WaccASTParser(String filename, Path relativePath,
      List<WaccError> semanticErrors) {
    this.relativePath = relativePath;
    this.semanticErrors = semanticErrors;
    this.filename = filename;
  }

  @Override
  public ProgAST visitProg(ProgContext ctx) {

    // Make a list of functionASTs for all functions declared in the program.
    // Do this by calling visitFuncDecl on all function declarations in ctx.
    NodeASTList<FunctionDeclarationAST> functionDeclASTS =
        new NodeASTList<>(semanticErrors,
            ctx, ctx.funcDecl().stream()
            .map(this::visitFuncDecl)
            .collect(Collectors.toList()));

    NodeASTList<ImportAST> importASTS = new NodeASTList<>(semanticErrors, ctx);
    if (ctx.imports() != null) {
      importASTS = visitImports(ctx.imports());
    }

    // Return a new progAST node.
    return new ProgAST(semanticErrors, ctx, filename, importASTS, functionDeclASTS,
        (StatementAST) visit(ctx.stat()));
  }

  @Override
  public NodeASTList<ImportAST> visitImports(ImportsContext ctx) {
    NodeASTList<ImportAST> importASTS = new NodeASTList<>(semanticErrors, ctx);

    if (ctx.identifier() != null) {
      importASTS.add(
          new ImportAST(semanticErrors, ctx.identifier(),
              ctx.identifier().getText(), relativePath));
    } else {
      importASTS.addAll(visitImports(ctx.imports(0)));
      importASTS.addAll(visitImports(ctx.imports(1)));
    }

    return importASTS;
  }

  @Override
  public FunctionDeclarationAST visitFuncDecl(FuncDeclContext ctx) {
    // Check whether the function has any parameters.
    if (ctx.paramList() != null) {
      // There are parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(semanticErrors,
          ctx,
          visitType(ctx.type()),
          ctx.identifier().getText(),
          visitParamList(ctx.paramList()),
          (StatementAST) visit(ctx.stat()));

    } else {
      // There are no parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(semanticErrors,
          ctx,
          visitType(ctx.type()),
          ctx.identifier().getText(),
          new NodeASTList<>(semanticErrors, ctx),
          (StatementAST) visit(ctx.stat()));
    }
  }

  @Override
  public FunctionCallAST visitFuncCall(FuncCallContext ctx) {

    // check whether the function call has any arguments.
    NodeASTList<ExpressionAST> actuals;
    if (ctx.argList() != null) {
      // arguments present, so check them.
      actuals = visitArgList(ctx.argList());
    } else {
      // no arguments, just set actuals to an empty list.
      actuals = new NodeASTList<>(semanticErrors, ctx);
    }

    // return a new FunctionCallAST.
    return new FunctionCallAST(semanticErrors, ctx, ctx.identifier().getText(), actuals);
  }

  @Override
  public ParamAST visitParam(ParamContext ctx) {
    // return a new ParamAST.
    return new ParamAST(semanticErrors, ctx, visitType(ctx.type()), ctx.identifier().getText());
  }

  @Override
  public NodeASTList<ParamAST> visitParamList(ParamListContext ctx) {
    // return a list of ParamAST, done by calling visitParam on all
    // parameters in ctx.
    return new NodeASTList<>(semanticErrors,
        ctx, ctx.param().stream().map(this::visitParam).collect(Collectors.toList()));
  }

  @Override
  public NodeASTList<ExpressionAST> visitArgList(ArgListContext ctx) {
    // similar to visitParamList, but arguments are expressions so call
    // visitExpr. Also need an explicit check here for the number of arguments.
    // in either case, return a new NodeASTList.
    if (ctx.expr() != null) {
      return new NodeASTList<>(semanticErrors,
          ctx, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
    } else {
      return new NodeASTList<>(semanticErrors, ctx);
    }
  }

  @Override
  public ReturnAST visitReturnStat(ReturnStatContext ctx) {
    // return a new ReturnAST.
    return new ReturnAST(semanticErrors, ctx, visitExpr(ctx.expr()));
  }

  @Override
  public IfElseAST visitIfThenElse(IfThenElseContext ctx) {
    // return a new IfElseAST.
    return new IfElseAST(semanticErrors,
        ctx,
        visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public IfElseAST visitIfThen(WaccParser.IfThenContext ctx) {
    return new IfElseAST(semanticErrors, ctx, visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat()), new SkipAST(semanticErrors, ctx));
  }


  @Override
  public StatementAST visitAssignIdent(AssignIdentContext ctx) {
    // return a new VariableDeclarationAST.
    return new VariableDeclarationAST(semanticErrors,
        ctx, visitType(ctx.type()), ctx.identifier().getText(), visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public AssignmentAST visitBinOpAssign(WaccParser.BinOpAssignContext ctx) {
    String op = ctx.op.getText();
    String operator = op.substring(0, op.length() - 1);
    String var = ctx.identifier().getText();
    AssignmentAST assignmentAST =
        new AssignmentAST(semanticErrors, ctx,
            new LHSAssignAST(semanticErrors, ctx, var),
            new RHSAssignAST(semanticErrors, ctx,
                new BinOpExprAST(semanticErrors, ctx,
                    new IdentifierAST(semanticErrors, ctx, var), operator,
                    visitExpr(ctx.expr()))));

    return assignmentAST;
  }


  @Override
  public StatementAST visitWhileDo(WhileDoContext ctx) {
    // return a new WhileAST.
    return new WhileAST(semanticErrors, ctx, visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat()), false);
  }

  @Override
  public StatementAST visitDoWhile(DoWhileContext ctx) {
    // return a new WhileAST.
    return new WhileAST(semanticErrors, ctx, visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat()), true);
  }

  @Override
  public ForAST visitForLoop(ForLoopContext ctx) {
    return new ForAST(semanticErrors,
        ctx,
        (StatementAST) visit(ctx.stat(0)),
        visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat(1)),
        (StatementAST) visit(ctx.stat(2)));
  }

  @Override
  public IfElseAST visitForEachLoop(WaccParser.ForEachLoopContext ctx) {
    // type
    TypeAST typeAST = visitType(ctx.type());
    // var
    String variableName = ctx.identifier(0).getText();
    // array
    String arrayName = ctx.identifier(1).getText();
    // Loop variable
    String loopVariable = "i";
    if (variableName.equals(loopVariable)) {
      // To avoid clash with variable
      loopVariable = "j";
    }
    // int i = 0
    VariableDeclarationAST indexDeclaration =
        new VariableDeclarationAST(semanticErrors, ctx,
            new BaseTypeAST(semanticErrors, ctx, "int"),
            loopVariable,
            new RHSAssignAST(semanticErrors, ctx,
                new LiteralsAST(semanticErrors, ctx, "0", new INT())));
    // type var = array[0]
    VariableDeclarationAST elementDeclaration =
        new VariableDeclarationAST(semanticErrors, ctx, typeAST, variableName,
            new RHSAssignAST(semanticErrors, ctx,
                new ArrayElemAST(semanticErrors, ctx, arrayName,
                    new NodeASTList<>(semanticErrors, ctx,
                        new ArrayList<>(
                            Arrays.asList((new LiteralsAST(semanticErrors, ctx,
                                "0", new INT()))))))));
    // i = i + 1
    AssignmentAST indexIncrement =
        new AssignmentAST(semanticErrors, ctx,
            new LHSAssignAST(semanticErrors, ctx, loopVariable),
            new RHSAssignAST(semanticErrors, ctx,
                new BinOpExprAST(semanticErrors, ctx,
                    new IdentifierAST(semanticErrors, ctx, loopVariable),
                    "+", new LiteralsAST(semanticErrors, ctx, "1",
                    new INT()))));
    // var = array[i]
    AssignmentAST elementUpdate =
        new AssignmentAST(semanticErrors, ctx,
            new LHSAssignAST(semanticErrors, ctx, variableName),
            new RHSAssignAST(semanticErrors, ctx,
                new ArrayElemAST(semanticErrors, ctx, arrayName,
                    new NodeASTList<>(semanticErrors, ctx,
                        new ArrayList<>(Arrays.asList(
                            new IdentifierAST(semanticErrors, ctx, loopVariable
                            )))))));
    // (int i = 0; type var = array[0])
    ChainedStatementAST initialisation =
        new ChainedStatementAST(semanticErrors, ctx, indexDeclaration,
            elementDeclaration);
    // i < len array
    BinOpExprAST indexBoundCheck =
        new BinOpExprAST(semanticErrors, ctx,
            new IdentifierAST(semanticErrors, ctx, loopVariable),
            "<",
            new UnaryOpExprAST(semanticErrors, ctx,
                new IdentifierAST(semanticErrors, ctx, arrayName), "len"));
    // (i = i + 1; var = array[i])
    ChainedStatementAST afterthought =
        new ChainedStatementAST(semanticErrors, ctx, indexIncrement,
            elementUpdate);
    // if len array > 0
    BinOpExprAST entryCheck =
        new BinOpExprAST(semanticErrors, ctx,
            new LiteralsAST(semanticErrors, ctx, "0", new INT()),
            "<",
            new UnaryOpExprAST(semanticErrors, ctx,
                new IdentifierAST(semanticErrors, ctx, arrayName), "len"));

    // Only enter loop if array is non-empty
    return new IfElseAST(semanticErrors, ctx, entryCheck,
        new ForAST(semanticErrors, ctx, initialisation, indexBoundCheck,
            afterthought, (StatementAST) visit(ctx.stat())),
        new SkipAST(semanticErrors, ctx));
  }

  @Override
  public SizeOfAST visitSizeOfCall(SizeOfCallContext ctx) {
    if(ctx.type() != null) {
      return new SizeOfAST(semanticErrors, ctx, visitType(ctx.type()));
    } else {
      return new SizeOfAST(semanticErrors, ctx, visitExpr(ctx.expr()));
    }
  }

  @Override
  public StatementAST visitFreeCall(FreeCallContext ctx) {
    // return a new FreeAST.
    return new FreeAST(semanticErrors, ctx, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitPrintlnCall(PrintlnCallContext ctx) {
    // return a new PrintAST with newLine flag set to 1.
    return new PrintAST(semanticErrors, ctx, visitExpr(ctx.expr()), true);
  }

  @Override
  public StatementAST visitPrintCall(PrintCallContext ctx) {
    // return a new PrintAST with newLine flag set to 0.
    return new PrintAST(semanticErrors, ctx, visitExpr(ctx.expr()), false);
  }

  @Override
  public StatementAST visitExitStat(ExitStatContext ctx) {
    // return a new ExitAST.
    return new ExitAST(semanticErrors, ctx, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitSeperateStat(SeperateStatContext ctx) {
    // return a new ChainedStatementAST.
    return new ChainedStatementAST(semanticErrors,
        ctx, (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitBeginStat(BeginStatContext ctx) {
    // return a new BeginAST.
    return new BeginAST(semanticErrors, ctx, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitSkipStat(SkipStatContext ctx) {
    // return a new SkipAST.
    return new SkipAST(semanticErrors, ctx);
  }

  @Override
  public StatementAST visitReadCall(ReadCallContext ctx) {
    // return a new ReadAST.
    return new ReadAST(semanticErrors, ctx, visitAssignLHS(ctx.assignLHS()));
  }

  @Override
  public StatementAST visitAssignVars(AssignVarsContext ctx) {
    // return a new AssignmentAST.
    return new AssignmentAST(semanticErrors, ctx,
        visitAssignLHS(ctx.assignLHS()), visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public TypeAST visitType(TypeContext ctx) {
    // return the TypeAST obtained from visiting the children.
    return (TypeAST) visitChildren(ctx);
  }

  @Override
  public BaseTypeAST visitBaseType(BaseTypeContext ctx) {
    // return a new BaseTypeAST.
    return new BaseTypeAST(semanticErrors, ctx, ctx.getText());
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    // ArrayType can start with either a baseType or an arrayType.

    if (ctx.baseType() != null) {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(semanticErrors, ctx,
          ctx.OPEN_SQUARE_BRACKET().size(), visitBaseType(ctx.baseType()));

    } else {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(semanticErrors, ctx,
          ctx.OPEN_SQUARE_BRACKET().size(), visitPairType(ctx.pairType()));
    }
  }

  @Override
  public PointerTypeAST visitPointerType(PointerTypeContext ctx) {
    // case for base type.
    return new PointerTypeAST(semanticErrors, ctx, ctx.STAR().size(),
        visitBaseType(ctx.baseType()));
  }

  @Override
  public PointerElemAST visitPointerElem(PointerElemContext ctx) {
    // case for base type.
    return new PointerElemAST(semanticErrors, ctx, ctx.STAR().size(),
        ctx.identifier().getText());
  }

  @Override
  public LHSAssignAST visitAssignLHS(AssignLHSContext ctx) {
    // Check what the type of AssignLHS is, and create new LHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.identifier() != null) {
      return new LHSAssignAST(semanticErrors, ctx,
          ctx.identifier().getText());
    }

    if (ctx.arrayElem() != null) {
      ArrayElemAST arrayElemAST = visitArrayElem(ctx.arrayElem());
      arrayElemAST.setLHS();
      return new LHSAssignAST(semanticErrors, ctx, arrayElemAST);
    }

    if (ctx.pairElem() != null) {
      return new LHSAssignAST(semanticErrors, ctx,
          visitPairElem(ctx.pairElem()));
    }

    if (ctx.pointerElem() != null) {
      PointerElemAST pointerElemAST = visitPointerElem(ctx.pointerElem());
      pointerElemAST.setLHS();
      return new LHSAssignAST(semanticErrors, ctx, pointerElemAST);
    }

    return null;
  }

  @Override
  public RHSAssignAST visitAssignRHS(AssignRHSContext ctx) {
    // Check what the type of AssignRHS is, and create new RHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.expr() != null) {
      return new RHSAssignAST(semanticErrors, ctx, visitExpr(ctx.expr()));
    }

    if (ctx.array() != null) {
      return new RHSAssignAST(semanticErrors, ctx, visitArray(ctx.array()));
    }

    if (ctx.newPair() != null) {
      return new RHSAssignAST(semanticErrors, ctx,
          visitNewPair(ctx.newPair()));
    }

    if (ctx.pairElem() != null) {
      return new RHSAssignAST(semanticErrors, ctx,
          visitPairElem(ctx.pairElem()));
    }

    if (ctx.funcCall() != null) {
      return new RHSAssignAST(semanticErrors, ctx,
          visitFuncCall(ctx.funcCall()));
    }

    return null;
  }

  @Override
  public ExpressionAST visitExpr(ExprContext ctx) {
    // Check what kind of expression Expr is, and create the corresponding
    // ExpressionAST.

    if (ctx.binaryOperator != null) {
      return new BinOpExprAST(semanticErrors,
          ctx, visitExpr(ctx.expr(0)), ctx.binaryOperator.getText(),
          visitExpr(ctx.expr(1)));
    }

    if (ctx.unaryOperator() != null) {
      return new UnaryOpExprAST(semanticErrors, ctx, visitExpr(ctx.expr(0)),
          ctx.unaryOperator().getText());
    }

    if (ctx.OPEN_PARENTHESES() != null) {
      return visitExpr(ctx.expr(0));
    }

    if (ctx.arrayElem() != null) {
      return visitArrayElem(ctx.arrayElem());
    }

    if (ctx.pointerElem() != null) {
      return visitPointerElem(ctx.pointerElem());
    }

    if(ctx.sizeOfCall() != null){
      return visitSizeOfCall(ctx.sizeOfCall());
    }

    // in this case, could be a literal, an ident,or null.
    Object obj = visitChildren(ctx);

    if (obj instanceof LiteralsAST || obj instanceof IdentifierAST) {
      return (ExpressionAST) obj;
    }

    return null;
  }

  @Override
  public NewObjectAST visitNewObject(NewObjectContext ctx) {
    IdentifierContext identifier = ctx.identifier();
    ArgListContext argList = ctx.argList();

    List<ExpressionAST> actuals = new ArrayList<>();
    return new NewObjectAST(semanticErrors, ctx, identifier.getText(),
        new NodeASTList<>(semanticErrors, ctx, actuals));
  }

  @Override
  public ArrayElemAST visitArrayElem(ArrayElemContext ctx) {
    // Make a list of ExpressionAST for each element of the array.
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(semanticErrors,
            ctx, ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList()));

    // return a new ArrayElemAST.
    return new ArrayElemAST(semanticErrors, ctx, ctx.identifier().getText(), exprs);
  }

  @Override
  public ArrayAST visitArray(ArrayContext ctx) {
    // Make a list of ExpressionAST for each element of the array.
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(semanticErrors,
            ctx, ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList()));

    // return a new ArrayAST.
    return new ArrayAST(semanticErrors, ctx, exprs);
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {

    // return a new PairTypeAST.
    return new PairTypeAST(semanticErrors, ctx,
        visitPairElemType(ctx.pairElemType(0)),
        visitPairElemType(ctx.pairElemType(1)));
  }

  @Override
  public PairElemTypeAST visitPairElemType(PairElemTypeContext ctx) {

    // Check what the type of PairElemType is, and create new PairElemType
    // by calling the appropriate constructor.

    if (ctx.baseType() != null) {
      return new PairElemTypeAST(semanticErrors, ctx, visitBaseType(ctx.baseType()));
    }

    if (ctx.arrayType() != null) {
      return new PairElemTypeAST(semanticErrors, ctx, visitArrayType(ctx.arrayType()));
    }

    if (ctx.PAIR_TYPE() != null) {
      return new PairElemTypeAST(semanticErrors, ctx, ctx.PAIR_TYPE().getText());
    }

    return null;
  }

  @Override
  public PairElemAST visitPairElem(PairElemContext ctx) {
    // return a new PairElemAST.
    // If operation is a first, pass index of 0, otherwise 1.
    return new PairElemAST(semanticErrors, ctx, visitExpr(ctx.expr()),
        ctx.PAIR_FIRST() != null);
  }

  @Override
  public NewPairAST visitNewPair(NewPairContext ctx) {
    // return a new NewPairAST.
    return new NewPairAST(semanticErrors, ctx, visitExpr(ctx.expr(0)),
        visitExpr(ctx.expr(1)));
  }

  // ================= visiting Literals and Identifier ==========================

  @Override
  public NodeAST visitIntLiter(IntLiterContext ctx) {
    return new LiteralsAST(semanticErrors, ctx, new INT());
  }

  @Override
  public NodeAST visitBoolLiter(BoolLiterContext ctx) {
    return new LiteralsAST(semanticErrors, ctx, new BOOL());
  }

  @Override
  public NodeAST visitPairLiter(PairLiterContext ctx) {
    return new LiteralsAST(semanticErrors, ctx, new PAIR());
  }

  @Override
  public NodeAST visitStrLiter(StrLiterContext ctx) {
    return new LiteralsAST(semanticErrors, ctx, new STR());
  }

  @Override
  public NodeAST visitCharLiter(CharLiterContext ctx) {
    return new LiteralsAST(semanticErrors, ctx, new CHAR());
  }

  @Override
  public NodeAST visitIdentifier(IdentifierContext ctx) {
    return new IdentifierAST(semanticErrors, ctx, ctx.IDENT().getText());
  }

  // ==========================================================================
  @Override
  public MethodCallAST visitMethodCall(WaccParser.MethodCallContext ctx) {

    // check whether the function call has any arguments.
    NodeASTList<ExpressionAST> actuals;
    if (ctx.argList() != null) {
      // arguments present, so check them.
      actuals = visitArgList(ctx.argList());
    } else {
      // no arguments, just set actuals to an empty list.
      actuals = new NodeASTList<>(semanticErrors, ctx);
    }

    // return a new FunctionCallAST.
    return new MethodCallAST(semanticErrors, ctx, ctx.identifier(0).getText(),
        new FunctionCallAST(semanticErrors, ctx, ctx.identifier(1).getText(), actuals));
  }

  @Override
  public MethodDeclarationAST visitMethodDecl(WaccParser.MethodDeclContext ctx) {
    FunctionDeclarationAST func = visitFuncDecl(ctx.funcDecl());
    Visibility visibility = Visibility.PUBLIC;
    if (ctx.VISIBILITY().getText().equals("private")) {
      visibility = Visibility.PRIVATE;
    }

    return new MethodDeclarationAST(semanticErrors, ctx, func, visibility);
  }

  @Override
  public ClassDefinitionAST visitClassDef(ClassDefContext ctx) {
    // Make a list of functionASTs for all functions declared in the program.
    // Do this by calling visitFuncDecl on all function declarations in ctx.
    NodeASTList<MethodDeclarationAST> methodDeclASTS =
        new NodeASTList<>(semanticErrors, ctx,
            ctx.methodDecl().stream()
            .map(this::visitMethodDecl).collect(Collectors.toList()));

    // Return a new progAST node.
    return new ClassDefinitionAST(semanticErrors,
        ctx,
        ctx.identifier().getText(),
        (StatementAST) visit(ctx.fields()),
        methodDeclASTS);
  }

  @Override
  public IfElseAST visitSwitchStat(WaccParser.SwitchStatContext ctx) {
    List<ExpressionAST> cases =
        ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList());

    ExpressionAST expression = cases.remove(0);

    List<StatementAST> statements =
        ctx.stat().stream()
            .map(s -> (StatementAST) visit(s))
            .collect(Collectors.toList());

    boolean hasDefault = ctx.DEFAULT() != null;

    StatementAST statementAST = new SkipAST(semanticErrors, ctx);
    // If the switch statement has a default case then the final else will be the final statement
    if (hasDefault) {
      statementAST = statements.remove(statements.size() - 1);
    }

    // Going through each case bottom-up
    for (int i = statements.size() - 1; i >= 0; i--) {
      // Checks if the expression is equal to current case
      // If they match then the corresponding statement is executed
      // Otherwise all the other cases will get checked in the else block
      statementAST = new IfElseAST(semanticErrors, ctx,
          new BinOpExprAST(null, ctx, expression,
              "==", cases.get(i)),
          statements.get(i), statementAST);
    }
    return (IfElseAST) statementAST;
  }
}
