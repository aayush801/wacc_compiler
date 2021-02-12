package middleware.ast;

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
import antlr.WaccParser.ExitCallContext;
import antlr.WaccParser.ExprContext;
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
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.basic_types.PAIR;
import identifier_objects.basic_types.STR;
import java.util.stream.Collectors;
import middleware.ast.arrays_ast.ArrayAST;
import middleware.ast.arrays_ast.ArrayElemAST;
import middleware.ast.arrays_ast.ArrayTypeAST;
import middleware.ast.arrays_ast.BaseTypeAST;
import middleware.ast.arrays_ast.PairElemTypeAST;
import middleware.ast.arrays_ast.PairTypeAST;
import middleware.ast.arrays_ast.TypeAST;
import middleware.ast.expression_ast.BinOpExprAST;
import middleware.ast.expression_ast.ExpressionAST;
import middleware.ast.expression_ast.IdentifierAST;
import middleware.ast.expression_ast.LiteralsAST;
import middleware.ast.expression_ast.UnaryOpExprAST;
import middleware.ast.function_ast.FunctionCallAST;
import middleware.ast.function_ast.FunctionDeclarationAST;
import middleware.ast.function_ast.ParamAST;
import middleware.ast.pair_ast.NewPairAST;
import middleware.ast.pair_ast.PairElemAST;
import middleware.ast.statement_ast.AssignmentAST;
import middleware.ast.statement_ast.BeginAST;
import middleware.ast.statement_ast.ChainedStatementAST;
import middleware.ast.statement_ast.ExitAST;
import middleware.ast.statement_ast.FreeAST;
import middleware.ast.statement_ast.IfElseAST;
import middleware.ast.statement_ast.LHSAssignAST;
import middleware.ast.statement_ast.PrintAST;
import middleware.ast.statement_ast.RHSAssignAST;
import middleware.ast.statement_ast.ReadAST;
import middleware.ast.statement_ast.ReturnAST;
import middleware.ast.statement_ast.SkipAST;
import middleware.ast.statement_ast.StatementAST;
import middleware.ast.statement_ast.VariableDeclarationAST;
import middleware.ast.statement_ast.WhileAST;

public class WaccASTParser extends WaccParserBaseVisitor<NodeAST> {

  @Override
  public ProgAST visitProg(ProgContext ctx) {

    // Make a list of functionASTs for all functions declared in the program.
    // Do this by calling visitFuncDecl on all function declarations in ctx.
    NodeASTList<FunctionDeclarationAST> functionDeclASTS =
        new NodeASTList<>(
            ctx.start,
            ctx.funcDecl().stream()
                .map(this::visitFuncDecl)
                .collect(Collectors.toList())
        );

    // Return a new progAST node.
    return new ProgAST(ctx.start, functionDeclASTS, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public FunctionDeclarationAST visitFuncDecl(FuncDeclContext ctx) {
    // Check whether the function has any parameters.
    if (ctx.paramList() != null) {
      // There are parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(
          ctx.start,
          visitType(ctx.type()),
          ctx.IDENT().getText(),
          visitParamList(ctx.paramList()),
          (StatementAST) visit(ctx.stat()));

    } else {
      // There are no parameters for this function.

      // Return a new FunctionDeclarationAST.
      return new FunctionDeclarationAST(
          ctx.start,
          visitType(ctx.type()),
          ctx.IDENT().getText(),
          new NodeASTList<>(ctx.start),
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
      actuals = new NodeASTList<>(ctx.start);
    }

    // return a new FunctionCallAST.
    return new FunctionCallAST(ctx.start, ctx.IDENT().getText(), actuals);
  }

  @Override
  public ParamAST visitParam(ParamContext ctx) {
    // return a new ParamAST.
    return new ParamAST(ctx.start, visitType(ctx.type()), ctx.IDENT().getText());
  }

  @Override
  public NodeASTList<ParamAST> visitParamList(ParamListContext ctx) {
    // return a list of ParamAST, done by calling visitParam on all
    // parameters in ctx.
    return new NodeASTList<>(
        ctx.start, ctx.param().stream().map(this::visitParam).collect(Collectors.toList()));
  }

  @Override
  public NodeASTList<ExpressionAST> visitArgList(ArgListContext ctx) {
    // similar to visitParamList, but arguments are expressions so call
    // visitExpr. Also need an explicit check here for the number of arguments.
    // in either case, return a new NodeASTList.
    if (ctx.expr() != null) {
      return new NodeASTList<>(
          ctx.start, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
    } else {
      return new NodeASTList<>(ctx.start);
    }
  }

  @Override
  public ReturnAST visitReturnCall(ReturnCallContext ctx) {
    // return a new ReturnAST.
    return new ReturnAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public IfElseAST visitIfThenElse(IfThenElseContext ctx) {
    // return a new IfElseAST.
    return new IfElseAST(
        ctx.start, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitAssignIdent(AssignIdentContext ctx) {
    // return a new VariableDeclarationAST.
    return new VariableDeclarationAST(ctx.start, visitType(ctx.type()), ctx.IDENT().getText(),
        visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public StatementAST visitWhileDo(WhileDoContext ctx) {
    // return a new WhileAST.
    return new WhileAST(ctx.start, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitFreeCall(FreeCallContext ctx) {
    // return a new FreeAST.
    return new FreeAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitPrintlnCall(PrintlnCallContext ctx) {
    // return a new PrintAST with newLine flag set to 1.
    return new PrintAST(ctx.start, visitExpr(ctx.expr()), 1);
  }

  @Override
  public StatementAST visitPrintCall(PrintCallContext ctx) {
    // return a new PrintAST with newLine flag set to 0.
    return new PrintAST(ctx.start, visitExpr(ctx.expr()), 0);
  }

  @Override
  public StatementAST visitExitCall(ExitCallContext ctx) {
    // return a new ExitAST.
    return new ExitAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitSeperateStat(SeperateStatContext ctx) {
    // return a new ChainedStatementAST.
    return new ChainedStatementAST(ctx.start, (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitBeginStat(BeginStatContext ctx) {
    // return a new BeginAST.
    return new BeginAST(ctx.start, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitSkipStat(SkipStatContext ctx) {
    // return a new SkipAST.
    return new SkipAST(ctx.start);
  }

  @Override
  public StatementAST visitReadCall(ReadCallContext ctx) {
    // return a new ReadAST.
    return new ReadAST(ctx.start, visitAssignLHS(ctx.assignLHS()));
  }

  @Override
  public StatementAST visitAssignVars(AssignVarsContext ctx) {
    // return a new AssignmentAST.
    return new AssignmentAST(
        ctx.start,
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
    return new BaseTypeAST(ctx.start, ctx.getText());
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    // ArrayType can start with either a baseType or an arrayType.

    if (ctx.baseType() != null) {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(),
          visitBaseType(ctx.baseType()));

    } else {
      // case for base type.

      // return new ArrayTypeAST.
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(),
          visitPairType(ctx.pairType()));
    }
  }

  @Override
  public LHSAssignAST visitAssignLHS(AssignLHSContext ctx) {
    // Check what the type of AssignLHS is, and create new LHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.IDENT() != null) {
      return new LHSAssignAST(ctx.start, ctx.IDENT().getText());
    }
    if (ctx.arrayElem() != null) {
      return new LHSAssignAST(ctx.start, visitArrayElem(ctx.arrayElem()));
    }
    if (ctx.pairElem() != null) {
      return new LHSAssignAST(ctx.start, visitPairElem(ctx.pairElem()));
    }
    return null;
  }

  @Override
  public RHSAssignAST visitAssignRHS(AssignRHSContext ctx) {
    // Check what the type of AssignRHS is, and create new RHSAssignAST
    // by calling the appropriate constructor.

    if (ctx.expr() != null) {
      return new RHSAssignAST(ctx.start, visitExpr(ctx.expr()));
    }
    if (ctx.array() != null) {
      return new RHSAssignAST(ctx.start, visitArray(ctx.array()));

    }
    if (ctx.newPair() != null) {
      return new RHSAssignAST(ctx.start, visitNewPair(ctx.newPair()));

    }
    if (ctx.pairElem() != null) {
      return new RHSAssignAST(ctx.start, visitPairElem(ctx.pairElem()));

    }
    if (ctx.funcCall() != null) {
      return new RHSAssignAST(ctx.start, visitFuncCall(ctx.funcCall()));

    }
    return null;
  }

  @Override
  public ExpressionAST visitExpr(ExprContext ctx) {
    // Check what kind of expression Expr is, and create the corresponding
    // ExpressionAST.

    if (ctx.binaryOperator != null) {
      return new BinOpExprAST(
          ctx.start, visitExpr(ctx.expr(0)),
          ctx.binaryOperator.getText(),
          visitExpr(ctx.expr(1))
      );
    }

    if (ctx.unaryOperator() != null) {
      return new UnaryOpExprAST(ctx.start,
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
            ctx.start, ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList())
        );

    // return a new ArrayElemAST.
    return new ArrayElemAST(ctx.start, ctx.IDENT().getText(), exprs);
  }

  @Override
  public ArrayAST visitArray(ArrayContext ctx) {
    // Make a list of ExpressionAST for each element of the array.
    NodeASTList<ExpressionAST> exprs = new NodeASTList<>(
        ctx.start, ctx.expr().stream()
        .map(this::visitExpr)
        .collect(Collectors.toList())
    );

    // return a new ArrayAST.
    return new ArrayAST(ctx.start, exprs);
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {

    // return a new PairTypeAST.
    return new PairTypeAST(ctx.start, visitPairElemType(ctx.pairElemType(0)),
        visitPairElemType(ctx.pairElemType(1)));
  }

  @Override
  public PairElemTypeAST visitPairElemType(PairElemTypeContext ctx) {

    // Check what the type of PairElemType is, and create new PairElemType
    // by calling the appropriate constructor.

    if (ctx.baseType() != null) {
      return new PairElemTypeAST(ctx.start, visitBaseType(ctx.baseType()));
    }

    if (ctx.arrayType() != null) {
      return new PairElemTypeAST(ctx.start, visitArrayType(ctx.arrayType()));
    }

    if (ctx.PAIR_TYPE() != null) {
      return new PairElemTypeAST(ctx.start, ctx.PAIR_TYPE().getText());
    }

    return null;
  }

  @Override
  public PairElemAST visitPairElem(PairElemContext ctx) {
    // return a new PairElemAST.
    // If operation is a first, pass index of 0, otherwise 1.
    return new PairElemAST(ctx.start, visitExpr(ctx.expr()),
        ctx.PAIR_FIRST() != null ? 0 : 1);
  }

  @Override
  public NewPairAST visitNewPair(NewPairContext ctx) {
    // return a new NewPairAST.
    return new NewPairAST(ctx.start, visitExpr(ctx.expr(0)),
        visitExpr(ctx.expr(1)));
  }

 // ================= visiting Literals and Identifier ==========================

  @Override
  public NodeAST visitIntLiter(IntLiterContext ctx) {
    return new LiteralsAST(ctx.start, ctx.getText(), new INT());
  }

  @Override
  public NodeAST visitBoolLiter(BoolLiterContext ctx) {
    return new LiteralsAST(ctx.start, ctx.getText(), new BOOL());
  }

  @Override
  public NodeAST visitPairLiter(PairLiterContext ctx) {
    return new LiteralsAST(ctx.start, ctx.getText(), new PAIR());
  }

  @Override
  public NodeAST visitStrLiter(StrLiterContext ctx) {
    return new LiteralsAST(ctx.start, ctx.getText(), new STR());
  }

  @Override
  public NodeAST visitCharLiter(CharLiterContext ctx) {
    return new LiteralsAST(ctx.start, ctx.getText(), new CHAR());
  }

  @Override
  public NodeAST visitIdentifier(IdentifierContext ctx) {
    return new IdentifierAST(ctx.start, ctx.IDENT().getText());
  }

  // ==========================================================================


}
