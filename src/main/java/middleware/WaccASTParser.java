package middleware;

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
import antlr.WaccParser.DoWhileContext;
import antlr.WaccParser.ExitCallContext;
import antlr.WaccParser.ExprContext;
import antlr.WaccParser.ForLoopContext;
import antlr.WaccParser.FreeCallContext;
import antlr.WaccParser.FuncCallContext;
import antlr.WaccParser.FuncDeclContext;
import antlr.WaccParser.IdentifierContext;
import antlr.WaccParser.IfThenElseContext;
import antlr.WaccParser.IntLiterContext;
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
import antlr.WaccParser.ReturnCallContext;
import antlr.WaccParser.SeperateStatContext;
import antlr.WaccParser.SkipStatContext;
import antlr.WaccParser.StrLiterContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParser.WhileDoContext;
import antlr.WaccParserBaseVisitor;
import frontend.identifier_objects.basic_types.BOOL;
import frontend.identifier_objects.basic_types.CHAR;
import frontend.identifier_objects.basic_types.INT;
import frontend.identifier_objects.basic_types.PAIR;
import frontend.identifier_objects.basic_types.STR;
import java.util.stream.Collectors;
import middleware.ast_nodes.NodeASTList;
import middleware.ast_nodes.StatementAST;
import middleware.ast_nodes.TypeAST;
import middleware.ast_nodes.arrays_ast.ArrayAST;
import middleware.ast_nodes.arrays_ast.ArrayElemAST;
import middleware.ast_nodes.expression_ast.BinOpExprAST;
import middleware.ast_nodes.expression_ast.IdentifierAST;
import middleware.ast_nodes.expression_ast.LiteralsAST;
import middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import middleware.ast_nodes.function_ast.FunctionCallAST;
import middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import middleware.ast_nodes.function_ast.ParamAST;
import middleware.ast_nodes.pair_ast.NewPairAST;
import middleware.ast_nodes.pair_ast.PairElemAST;
import middleware.ast_nodes.pointers_ast.PointerElemAST;
import middleware.ast_nodes.prog_ast.ProgAST;
import middleware.ast_nodes.statement_ast.AssignmentAST;
import middleware.ast_nodes.statement_ast.BeginAST;
import middleware.ast_nodes.statement_ast.ChainedStatementAST;
import middleware.ast_nodes.statement_ast.ExitAST;
import middleware.ast_nodes.statement_ast.ForAST;
import middleware.ast_nodes.statement_ast.FreeAST;
import middleware.ast_nodes.statement_ast.IfElseAST;
import middleware.ast_nodes.statement_ast.LHSAssignAST;
import middleware.ast_nodes.statement_ast.PrintAST;
import middleware.ast_nodes.statement_ast.RHSAssignAST;
import middleware.ast_nodes.statement_ast.ReadAST;
import middleware.ast_nodes.statement_ast.ReturnAST;
import middleware.ast_nodes.statement_ast.SkipAST;
import middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import middleware.ast_nodes.statement_ast.WhileAST;
import middleware.ast_nodes.types_ast.ArrayTypeAST;
import middleware.ast_nodes.types_ast.BaseTypeAST;
import middleware.ast_nodes.types_ast.PairElemTypeAST;
import middleware.ast_nodes.types_ast.PairTypeAST;
import middleware.ast_nodes.types_ast.PointerTypeAST;

public class WaccASTParser extends WaccParserBaseVisitor<NodeAST> {

  @Override
  public ProgAST visitProg(ProgContext ctx) {

    // Make a list of functionASTs for all functions declared in the program.
    // Do this by calling visitFuncDecl on all function declarations in ctx.
    NodeASTList<FunctionDeclarationAST> functionDeclASTS =
        new NodeASTList<>(
            ctx,
            ctx.funcDecl().stream()
                .map(this::visitFuncDecl)
                .collect(Collectors.toList())
        );

    // Return a new progAST node.
    return new ProgAST(ctx, functionDeclASTS, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public FunctionDeclarationAST visitFuncDecl(FuncDeclContext ctx) {
    // Check whether the function has any parameters.
    if (ctx.paramList() != null) {
      // There are parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(
          ctx,
          visitType(ctx.type()),
          ctx.identifier().getText(),
          visitParamList(ctx.paramList()),
          (StatementAST) visit(ctx.stat()));

    } else {
      // There are no parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(
          ctx,
          visitType(ctx.type()),
          ctx.identifier().getText(),
          new NodeASTList<>(ctx),
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
      actuals = new NodeASTList<>(ctx);
    }

    // return a new FunctionCallAST.
    return new FunctionCallAST(ctx, ctx.identifier().getText(), actuals);
  }

  @Override
  public ParamAST visitParam(ParamContext ctx) {
    // return a new ParamAST.
    return new ParamAST(ctx, visitType(ctx.type()), ctx.identifier().getText());
  }

  @Override
  public NodeASTList<ParamAST> visitParamList(ParamListContext ctx) {
    // return a list of ParamAST, done by calling visitParam on all
    // parameters in ctx.
    return new NodeASTList<>(
        ctx, ctx.param().stream().map(this::visitParam)
        .collect(Collectors.toList()));
  }

  @Override
  public NodeASTList<ExpressionAST> visitArgList(ArgListContext ctx) {
    // similar to visitParamList, but arguments are expressions so call
    // visitExpr. Also need an explicit check here for the number of arguments.
    // in either case, return a new NodeASTList.
    if (ctx.expr() != null) {
      return new NodeASTList<>(
          ctx, ctx.expr().stream().map(this::visitExpr)
          .collect(Collectors.toList()));
    } else {
      return new NodeASTList<>(ctx);
    }
  }

  @Override
  public ReturnAST visitReturnCall(ReturnCallContext ctx) {
    // return a new ReturnAST.
    return new ReturnAST(ctx, visitExpr(ctx.expr()));
  }

  @Override
  public IfElseAST visitIfThenElse(IfThenElseContext ctx) {
    // return a new IfElseAST.
    return new IfElseAST(
        ctx, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitAssignIdent(AssignIdentContext ctx) {
    // return a new VariableDeclarationAST.
    return new VariableDeclarationAST(ctx, visitType(ctx.type()),
        ctx.identifier().getText(), visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public StatementAST visitWhileDo(WhileDoContext ctx) {
    // return a new WhileAST.
    return new WhileAST(ctx, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat()), false);
  }

  @Override
  public StatementAST visitDoWhile(DoWhileContext ctx) {
    // return a new WhileAST.
    return new WhileAST(ctx, visitExpr(ctx.expr()),
        (StatementAST) visit(ctx.stat()), true);
  }

  @Override
  public ForAST visitForLoop(ForLoopContext ctx) {
    return new ForAST(ctx, (StatementAST) visit(ctx.stat(0)),
        visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat(1)),
        (StatementAST) visit(ctx.stat(2)));
  }

  @Override
  public StatementAST visitFreeCall(FreeCallContext ctx) {
    // return a new FreeAST.
    return new FreeAST(ctx, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitPrintlnCall(PrintlnCallContext ctx) {
    // return a new PrintAST with newLine flag set to 1.
    return new PrintAST(ctx, visitExpr(ctx.expr()), true);
  }

  @Override
  public StatementAST visitPrintCall(PrintCallContext ctx) {
    // return a new PrintAST with newLine flag set to 0.
    return new PrintAST(ctx, visitExpr(ctx.expr()), false);
  }

  @Override
  public StatementAST visitExitCall(ExitCallContext ctx) {
    // return a new ExitAST.
    return new ExitAST(ctx, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitSeperateStat(SeperateStatContext ctx) {
    // return a new ChainedStatementAST.
    return new ChainedStatementAST(ctx, (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitBeginStat(BeginStatContext ctx) {
    // return a new BeginAST.
    return new BeginAST(ctx, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitSkipStat(SkipStatContext ctx) {
    // return a new SkipAST.
    return new SkipAST(ctx);
  }

  @Override
  public StatementAST visitReadCall(ReadCallContext ctx) {
    // return a new ReadAST.
    return new ReadAST(ctx, visitAssignLHS(ctx.assignLHS()));
  }

  @Override
  public StatementAST visitAssignVars(AssignVarsContext ctx) {
    // return a new AssignmentAST.
    return new AssignmentAST(
        ctx,
        visitAssignLHS(ctx.assignLHS()),
        visitAssignRHS(ctx.assignRHS())
    );
  }

  @Override
  public TypeAST visitType(TypeContext ctx) {
    // return the TypeAST obtained from visiting the children.
    return (TypeAST) visitChildren(ctx);
  }

  @Override
  public BaseTypeAST visitBaseType(BaseTypeContext ctx) {
    // return a new BaseTypeAST.
    return new BaseTypeAST(ctx, ctx.getText());
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    // ArrayType can start with either a baseType or an arrayType.

    if (ctx.baseType() != null) {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(
          ctx, ctx.OPEN_SQUARE_BRACKET().size(),
          visitBaseType(ctx.baseType()));

    } else {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(
          ctx, ctx.OPEN_SQUARE_BRACKET().size(),
          visitPairType(ctx.pairType()));
    }
  }

  @Override
  public PointerTypeAST visitPointerType(PointerTypeContext ctx) {
      // case for base type.
      return new PointerTypeAST(ctx, ctx.STAR().size(),
          visitBaseType(ctx.baseType()));
  }

  @Override
  public PointerElemAST visitPointerElem(PointerElemContext ctx) {
    // case for base type.
    return new PointerElemAST(ctx, ctx.STAR().size(), ctx.identifier().getText());
  }


  @Override
  public LHSAssignAST visitAssignLHS(AssignLHSContext ctx) {
    // Check what the type of AssignLHS is, and create new LHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.identifier() != null) {
      return new LHSAssignAST(ctx, ctx.identifier().getText());
    }

    if (ctx.arrayElem() != null) {
      ArrayElemAST arrayElemAST = visitArrayElem(ctx.arrayElem());
      arrayElemAST.setLHS();
      return new LHSAssignAST(ctx, arrayElemAST);
    }

    if (ctx.pairElem() != null) {
      return new LHSAssignAST(ctx, visitPairElem(ctx.pairElem()));
    }

    if(ctx.pointerElem() != null){
      PointerElemAST pointerElemAST = visitPointerElem(ctx.pointerElem());
      pointerElemAST.setLHS();
      return new LHSAssignAST(ctx, pointerElemAST);
    }

    return null;
  }

  @Override
  public RHSAssignAST visitAssignRHS(AssignRHSContext ctx) {
    // Check what the type of AssignRHS is, and create new RHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.expr() != null) {
      return new RHSAssignAST(ctx, visitExpr(ctx.expr()));
    }

    if (ctx.array() != null) {
      return new RHSAssignAST(ctx, visitArray(ctx.array()));

    }

    if (ctx.newPair() != null) {
      return new RHSAssignAST(ctx, visitNewPair(ctx.newPair()));

    }

    if (ctx.pairElem() != null) {
      return new RHSAssignAST(ctx, visitPairElem(ctx.pairElem()));

    }

    if (ctx.funcCall() != null) {
      return new RHSAssignAST(ctx, visitFuncCall(ctx.funcCall()));
    }

    return null;
  }

  @Override
  public ExpressionAST visitExpr(ExprContext ctx) {
    // Check what kind of expression Expr is, and create the corresponding
    // ExpressionAST.

    if (ctx.binaryOperator != null) {
      return new BinOpExprAST(
          ctx, visitExpr(ctx.expr(0)),
          ctx.binaryOperator.getText(),
          visitExpr(ctx.expr(1))
      );
    }

    if (ctx.unaryOperator() != null) {
      return new UnaryOpExprAST(ctx,
          visitExpr(ctx.expr(0)),
          ctx.unaryOperator().getText()
      );
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

    // in this case, could be a literal, an ident, or null.
    Object obj = visitChildren(ctx);

    if (obj instanceof LiteralsAST || obj instanceof IdentifierAST) {
      return (ExpressionAST) obj;
    }

    return null;
  }

  @Override
  public ArrayElemAST visitArrayElem(ArrayElemContext ctx) {
    // Make a list of ExpressionAST for each element of the array.
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(
            ctx, ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList())
        );

    // return a new ArrayElemAST.
    return new ArrayElemAST(ctx, ctx.identifier().getText(), exprs);
  }

  @Override
  public ArrayAST visitArray(ArrayContext ctx) {
    // Make a list of ExpressionAST for each element of the array.
    NodeASTList<ExpressionAST> exprs = new NodeASTList<>(
        ctx, ctx.expr().stream()
        .map(this::visitExpr)
        .collect(Collectors.toList())
    );

    // return a new ArrayAST.
    return new ArrayAST(ctx, exprs);
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {

    // return a new PairTypeAST.
    return new PairTypeAST(ctx, visitPairElemType(ctx.pairElemType(0)),
        visitPairElemType(ctx.pairElemType(1)));
  }

  @Override
  public PairElemTypeAST visitPairElemType(PairElemTypeContext ctx) {

    // Check what the type of PairElemType is, and create new PairElemType
    // by calling the appropriate constructor.

    if (ctx.baseType() != null) {
      return new PairElemTypeAST(ctx, visitBaseType(ctx.baseType()));
    }

    if (ctx.arrayType() != null) {
      return new PairElemTypeAST(ctx, visitArrayType(ctx.arrayType()));
    }

    if (ctx.PAIR_TYPE() != null) {
      return new PairElemTypeAST(ctx, ctx.PAIR_TYPE().getText());
    }

    return null;
  }

  @Override
  public PairElemAST visitPairElem(PairElemContext ctx) {
    // return a new PairElemAST.
    // If operation is a first, pass index of 0, otherwise 1.
    return new PairElemAST(ctx, visitExpr(ctx.expr()), ctx.PAIR_FIRST() != null);
  }

  @Override
  public NewPairAST visitNewPair(NewPairContext ctx) {
    // return a new NewPairAST.
    return new NewPairAST(ctx, visitExpr(ctx.expr(0)),
        visitExpr(ctx.expr(1)));
  }

  // ================= visiting Literals and Identifier ==========================

  @Override
  public NodeAST visitIntLiter(IntLiterContext ctx) {
    return new LiteralsAST(ctx, new INT());
  }

  @Override
  public NodeAST visitBoolLiter(BoolLiterContext ctx) {
    return new LiteralsAST(ctx, new BOOL());
  }

  @Override
  public NodeAST visitPairLiter(PairLiterContext ctx) {
    return new LiteralsAST(ctx, new PAIR());
  }

  @Override
  public NodeAST visitStrLiter(StrLiterContext ctx) {
    return new LiteralsAST(ctx, new STR());
  }

  @Override
  public NodeAST visitCharLiter(CharLiterContext ctx) {
    return new LiteralsAST(ctx, new CHAR());
  }

  @Override
  public NodeAST visitIdentifier(IdentifierContext ctx) {
    return new IdentifierAST(ctx, ctx.IDENT().getText());
  }

  // ==========================================================================


}
