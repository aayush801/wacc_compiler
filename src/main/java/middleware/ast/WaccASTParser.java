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
import antlr.WaccParser.StatContext;
import antlr.WaccParser.StrLiterContext;
import antlr.WaccParser.TypeContext;
import antlr.WaccParser.WhileDoContext;
import antlr.WaccParserBaseVisitor;
import identifier_objects.basic_types.BOOL;
import identifier_objects.basic_types.CHAR;
import identifier_objects.basic_types.INT;
import identifier_objects.basic_types.PAIR;
import identifier_objects.basic_types.STR;
import identifier_objects.unary_operator_functions.FREE;
import identifier_objects.unary_operator_functions.PRINT;
import identifier_objects.unary_operator_functions.PRINT_LINE;
import java.util.Collections;
import java.util.stream.Collectors;
import middleware.ast.arrays_ast.ArrayAST;
import middleware.ast.arrays_ast.ArrayElemAST;
import middleware.ast.arrays_ast.ArrayTypeAST;
import middleware.ast.arrays_ast.BaseTypeAST;
import middleware.ast.arrays_ast.PairTypeAST;
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
import middleware.ast.statement_ast.IfElseAST;
import middleware.ast.statement_ast.LHSAssignAST;
import middleware.ast.statement_ast.RHSAssignAST;
import middleware.ast.statement_ast.ReturnAST;
import middleware.ast.statement_ast.StatementAST;
import middleware.ast.statement_ast.VariableDeclarationAST;
import middleware.ast.statement_ast.WhileAST;

public class WaccASTParser extends WaccParserBaseVisitor<NodeAST> {

  @Override
  public ProgAST visitProg(ProgContext ctx) {
    NodeASTList<FunctionDeclarationAST> functionDeclASTS =
        new NodeASTList<>(
            ctx.start,
            ctx.funcDecl().stream().map(this::visitFuncDecl).collect(Collectors.toList()));
    return new ProgAST(ctx.start, functionDeclASTS, visitStat(ctx.stat()));
  }

  @Override
  public FunctionDeclarationAST visitFuncDecl(FuncDeclContext ctx) {
    if(ctx.paramList() != null)
    return new FunctionDeclarationAST(
        ctx.start,
        ctx.type().getText(),
        ctx.IDENT().getText(),
        visitParamList(ctx.paramList()),
        visitStat(ctx.stat()));
    else
      return new FunctionDeclarationAST(
          ctx.start,
          ctx.type().getText(),
          ctx.IDENT().getText(),
          new NodeASTList<>(ctx.start),
          visitStat(ctx.stat()));
  }

  @Override
  public FunctionCallAST visitFuncCall(FuncCallContext ctx) {
    NodeASTList<ExpressionAST> actuals = visitArgList(ctx.argList());
    return new FunctionCallAST(ctx.start, ctx.IDENT().getText(), actuals);
  }

  @Override
  public ParamAST visitParam(ParamContext ctx) {
    return new ParamAST(ctx.start, ctx.type().getText(), ctx.IDENT().getText());
  }

  @Override
  public NodeASTList<ParamAST> visitParamList(ParamListContext ctx) {
    return new NodeASTList<>(
        ctx.start, ctx.param().stream().map(this::visitParam).collect(Collectors.toList()));
  }

  @Override
  public NodeASTList<ExpressionAST> visitArgList(ArgListContext ctx) {
    return new NodeASTList<>(
        ctx.start, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
  }

  @Override
  public ReturnAST visitReturnCall(ReturnCallContext ctx) {
    return new ReturnAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public IfElseAST visitIfThenElse(IfThenElseContext ctx) {
    return new IfElseAST(
        ctx.start, visitExpr(ctx.expr()), visitStat(ctx.stat(0)), visitStat(ctx.stat(1)));
  }

  public StatementAST visitStat(StatContext ctx) {
    Object obj = visit(ctx);
    if (obj == null) {
      System.out.println("Made an error in visitStat");
      return null;
    }

    if (!(obj instanceof StatementAST)) {
      System.out.println("Erron in visitStat, not returning a statement ast node");
      return null;
    }

    return (StatementAST) obj;
  }

  @Override
  public NodeAST visitAssignIdent(AssignIdentContext ctx) {
    return new VariableDeclarationAST(ctx.start, ctx.type().getText(), ctx.IDENT().getText());
  }

  @Override
  public NodeAST visitWhileDo(WhileDoContext ctx) {
    return new WhileAST(ctx.start, visitExpr(ctx.expr()), visitStat(ctx.stat()));
  }

  @Override
  public NodeAST visitFreeCall(FreeCallContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(ctx.start, Collections.singletonList(visitExpr(ctx.expr())));
    return new FunctionCallAST(ctx.start, FREE.name, exprs);
  }

  @Override
  public NodeAST visitPrintlnCall(PrintlnCallContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(ctx.start, Collections.singletonList(visitExpr(ctx.expr())));
    return new FunctionCallAST(ctx.start, PRINT_LINE.name, exprs);
  }

  @Override
  public NodeAST visitExitCall(ExitCallContext ctx) {
    return new ExitAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public NodeAST visitSeperateStat(SeperateStatContext ctx) {
    return new ChainedStatementAST(ctx.start, visitStat(ctx.stat(0)), visitStat(ctx.stat(1)));
  }

  @Override
  public NodeAST visitBeginStat(BeginStatContext ctx) {
    return new BeginAST(ctx.start, visitStat(ctx.stat()));
  }

  @Override
  public NodeAST visitSkipStat(SkipStatContext ctx) {
    return null;
  }

  @Override
  public NodeAST visitReadCall(ReadCallContext ctx) {
    //    NodeASTList<ExpressionAST> exprs =
    //        new NodeASTList<>(ctx.start,
    // Collections.singletonList(visitAssignLHS(ctx.assignLHS())));
    //    return new FunctionCallAST(ctx.start, READ.name, exprs);
    return null;
  }

  @Override
  public NodeAST visitPrintCall(PrintCallContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(ctx.start, Collections.singletonList(visitExpr(ctx.expr())));
    return new FunctionCallAST(ctx.start, PRINT.name, exprs);
  }

  @Override
  public NodeAST visitAssignVars(AssignVarsContext ctx) {
    return new AssignmentAST(
        ctx.start, visitAssignLHS(ctx.assignLHS()), visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public NodeAST visitType(TypeContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public BaseTypeAST visitBaseType(BaseTypeContext ctx) {
    return new BaseTypeAST(ctx.start);
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    if (ctx.baseType() != null)
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(), visitBaseType(ctx.baseType()), null);
    else
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(), null, visitPairType(ctx.pairType()));
  }

  @Override
  public LHSAssignAST visitAssignLHS(AssignLHSContext ctx) {
    return new LHSAssignAST(ctx.start);
  }

  @Override
  public RHSAssignAST visitAssignRHS(AssignRHSContext ctx) {
    return new RHSAssignAST(ctx.start);
  }

  @Override
  public ExpressionAST visitExpr(ExprContext ctx) {
    if (ctx.binaryOperator != null) {
      return new BinOpExprAST(
          ctx.start, visitExpr(ctx.expr(0)), ctx.binaryOperator.getText(), visitExpr(ctx.expr(1)));
    }

    if (ctx.unaryOperator() != null) {
      return new UnaryOpExprAST(ctx.start, visitExpr(ctx.expr(0)), ctx.unaryOperator().getText());
    }

    if (ctx.OPEN_PARENTHESES() != null) {
      return visitExpr(ctx.expr(0));
    }

    if (ctx.arrayElem() != null) {
      return visitArrayElem(ctx.arrayElem());
    }
    Object obj = visitChildren(ctx);

    if (!(obj instanceof LiteralsAST)) {
      System.out.println("something wnet wrong in expression");
      return null;
    }

    return (ExpressionAST) obj;
  }

  //  @Override
  //  public NodeAST visitUnaryOperator(UnaryOperatorContext ctx) {
  //    return null;
  //  }

  @Override
  public ArrayElemAST visitArrayElem(ArrayElemContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(
            ctx.start, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
    return new ArrayElemAST(ctx.start, ctx.IDENT().getText(), exprs);
  }

  @Override
  public NodeAST visitArray(ArrayContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(
            ctx.start, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
    return new ArrayAST(ctx.start, exprs);
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {
    return new PairTypeAST(ctx.start);
  }

  @Override
  public NodeAST visitPairElemType(PairElemTypeContext ctx) {
    if (ctx.baseType() != null) {
      return visitBaseType(ctx.baseType());
    }

    if (ctx.arrayType() != null) {
      return visitArrayType(ctx.arrayType());
    }

    if (ctx.PAIR_TYPE() != null) {
      return new LiteralsAST(ctx.start, new PAIR());
    }

    return null;
  }

  @Override
  public NodeAST visitPairElem(PairElemContext ctx) {
    return new PairElemAST(ctx.start, visitExpr(ctx.expr()), ctx.PAIR_FIRST() != null ? 0 : 1);
  }

  @Override
  public NodeAST visitNewPair(NewPairContext ctx) {
    return new NewPairAST(ctx.start, visitExpr(ctx.expr(0)), visitExpr(ctx.expr(1)));
  }

  @Override
  public NodeAST visitIntLiter(IntLiterContext ctx) {
    return new LiteralsAST(ctx.start, new INT());
  }

  @Override
  public NodeAST visitBoolLiter(BoolLiterContext ctx) {
    return new LiteralsAST(ctx.start, new BOOL());
  }

  @Override
  public NodeAST visitPairLiter(PairLiterContext ctx) {
    return new LiteralsAST(ctx.start, null);
  }

  @Override
  public NodeAST visitStrLiter(StrLiterContext ctx) {
    return new LiteralsAST(ctx.start, new STR());
  }

  @Override
  public NodeAST visitCharLiter(CharLiterContext ctx) {
    return new LiteralsAST(ctx.start, new CHAR());
  }

  @Override
  public NodeAST visitIdentifier(IdentifierContext ctx) {
    return new IdentifierAST(ctx.start, ctx.IDENT().getText());
  }

}
