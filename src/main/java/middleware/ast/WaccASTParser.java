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
    NodeASTList<FunctionDeclarationAST> functionDeclASTS =
        new NodeASTList<>(
            ctx.start,
            ctx.funcDecl().stream()
                .map(this::visitFuncDecl)
                .collect(Collectors.toList())
        );
    return new ProgAST(ctx.start, functionDeclASTS, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public FunctionDeclarationAST visitFuncDecl(FuncDeclContext ctx) {
    if (ctx.paramList() != null) {
      return new FunctionDeclarationAST(
          ctx.start,
          visitType(ctx.type()),
          ctx.IDENT().getText(),
          visitParamList(ctx.paramList()),
          (StatementAST) visit(ctx.stat()));
    } else {
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
    NodeASTList<ExpressionAST> actuals;
    if (ctx.argList() != null) {
      actuals = visitArgList(ctx.argList());
    } else {
      actuals = new NodeASTList<>(ctx.start);
    }
    return new FunctionCallAST(ctx.start, ctx.IDENT().getText(), actuals);
  }

  @Override
  public ParamAST visitParam(ParamContext ctx) {
    return new ParamAST(ctx.start, visitType(ctx.type()), ctx.IDENT().getText());
  }

  @Override
  public NodeASTList<ParamAST> visitParamList(ParamListContext ctx) {
    return new NodeASTList<>(
        ctx.start, ctx.param().stream().map(this::visitParam).collect(Collectors.toList()));
  }

  @Override
  public NodeASTList<ExpressionAST> visitArgList(ArgListContext ctx) {
    if (ctx.expr() != null) {
      return new NodeASTList<>(
          ctx.start, ctx.expr().stream().map(this::visitExpr).collect(Collectors.toList()));
    } else {
      return new NodeASTList<>(
          ctx.start);
    }
  }

  @Override
  public ReturnAST visitReturnCall(ReturnCallContext ctx) {
    return new ReturnAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public IfElseAST visitIfThenElse(IfThenElseContext ctx) {
    return new IfElseAST(
        ctx.start, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitAssignIdent(AssignIdentContext ctx) {
    return new VariableDeclarationAST(ctx.start, visitType(ctx.type()), ctx.IDENT().getText(),
        visitAssignRHS(ctx.assignRHS()));
  }

  @Override
  public StatementAST visitWhileDo(WhileDoContext ctx) {
    return new WhileAST(ctx.start, visitExpr(ctx.expr()), (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitFreeCall(FreeCallContext ctx) {
    return new FreeAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitPrintlnCall(PrintlnCallContext ctx) {
    return new PrintAST(ctx.start, visitExpr(ctx.expr()), 1);
  }

  @Override
  public StatementAST visitPrintCall(PrintCallContext ctx) {
    return new PrintAST(ctx.start, visitExpr(ctx.expr()), 0);
  }

  @Override
  public StatementAST visitExitCall(ExitCallContext ctx) {
    return new ExitAST(ctx.start, visitExpr(ctx.expr()));
  }

  @Override
  public StatementAST visitSeperateStat(SeperateStatContext ctx) {
    return new ChainedStatementAST(ctx.start, (StatementAST) visit(ctx.stat(0)),
        (StatementAST) visit(ctx.stat(1)));
  }

  @Override
  public StatementAST visitBeginStat(BeginStatContext ctx) {
    return new BeginAST(ctx.start, (StatementAST) visit(ctx.stat()));
  }

  @Override
  public StatementAST visitSkipStat(SkipStatContext ctx) {
    return new SkipAST(ctx.start);
  }

  @Override
  public StatementAST visitReadCall(ReadCallContext ctx) {
    return new ReadAST(ctx.start, visitAssignLHS(ctx.assignLHS()));
  }

  @Override
  public StatementAST visitAssignVars(AssignVarsContext ctx) {
    return new AssignmentAST(
        ctx.start,
        visitAssignLHS(ctx.assignLHS()),
        visitAssignRHS(ctx.assignRHS())
    );
  }

  @Override
  public TypeAST visitType(TypeContext ctx) {
    return (TypeAST) visitChildren(ctx);
  }

  @Override
  public BaseTypeAST visitBaseType(BaseTypeContext ctx) {
    return new BaseTypeAST(ctx.start, ctx.getText());
  }

  @Override
  public ArrayTypeAST visitArrayType(ArrayTypeContext ctx) {
    if (ctx.baseType() != null) {
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(),
          visitBaseType(ctx.baseType()));
    } else {
      return new ArrayTypeAST(
          ctx.start, ctx.OPEN_SQUARE_BRACKET().size(),
          visitPairType(ctx.pairType()));
    }
  }

  @Override
  public LHSAssignAST visitAssignLHS(AssignLHSContext ctx) {
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

    Object obj = visitChildren(ctx);

    if (obj instanceof LiteralsAST || obj instanceof IdentifierAST) {
      return (ExpressionAST) obj;
    }

    return null;


  }

  //  @Override
  //  public NodeAST visitUnaryOperator(UnaryOperatorContext ctx) {
  //    return null;
  //  }

  @Override
  public ArrayElemAST visitArrayElem(ArrayElemContext ctx) {
    NodeASTList<ExpressionAST> exprs =
        new NodeASTList<>(
            ctx.start, ctx.expr().stream()
            .map(this::visitExpr)
            .collect(Collectors.toList())
        );
    return new ArrayElemAST(ctx.start, ctx.IDENT().getText(), exprs);
  }

  @Override
  public ArrayAST visitArray(ArrayContext ctx) {
    NodeASTList<ExpressionAST> exprs = new NodeASTList<>(
        ctx.start, ctx.expr().stream()
        .map(this::visitExpr)
        .collect(Collectors.toList())
    );
    return new ArrayAST(ctx.start, exprs);
  }

  @Override
  public PairTypeAST visitPairType(PairTypeContext ctx) {

    return new PairTypeAST(ctx.start, visitPairElemType(ctx.pairElemType(0)),
        visitPairElemType(ctx.pairElemType(1)));
  }

  @Override
  public PairElemTypeAST visitPairElemType(PairElemTypeContext ctx) {

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
    return new PairElemAST(ctx.start, visitExpr(ctx.expr()),
        ctx.PAIR_FIRST() != null ? 0 : 1);
  }

  @Override
  public NewPairAST visitNewPair(NewPairContext ctx) {
    return new NewPairAST(ctx.start, visitExpr(ctx.expr(0)),
        visitExpr(ctx.expr(1)));
  }

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

}
