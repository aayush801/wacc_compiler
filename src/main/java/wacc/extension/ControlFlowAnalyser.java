package wacc.extension;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.class_ast.FieldAST;
import wacc.middleware.ast_nodes.class_ast.MethodCallAST;
import wacc.middleware.ast_nodes.class_ast.MethodDeclarationAST;
import wacc.middleware.ast_nodes.class_ast.NewObjectAST;
import wacc.middleware.ast_nodes.expression_ast.BinOpExprAST;
import wacc.middleware.ast_nodes.expression_ast.IdentifierAST;
import wacc.middleware.ast_nodes.expression_ast.LiteralsAST;
import wacc.middleware.ast_nodes.expression_ast.SizeOfAST;
import wacc.middleware.ast_nodes.expression_ast.UnaryOpExprAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallAST;
import wacc.middleware.ast_nodes.function_ast.FunctionCallInterface;
import wacc.middleware.ast_nodes.function_ast.FunctionDeclarationAST;
import wacc.middleware.ast_nodes.function_ast.ParamAST;
import wacc.middleware.ast_nodes.pair_ast.NewPairAST;
import wacc.middleware.ast_nodes.pair_ast.PairElemAST;
import wacc.middleware.ast_nodes.pointers_ast.PointerElemAST;
import wacc.middleware.ast_nodes.prog_ast.ProgAST;
import wacc.middleware.ast_nodes.statement_ast.AssignmentAST;
import wacc.middleware.ast_nodes.statement_ast.BeginAST;
import wacc.middleware.ast_nodes.statement_ast.ChainedStatementAST;
import wacc.middleware.ast_nodes.statement_ast.ExitAST;
import wacc.middleware.ast_nodes.statement_ast.FreeAST;
import wacc.middleware.ast_nodes.statement_ast.IfElseAST;
import wacc.middleware.ast_nodes.statement_ast.LHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.PrintAST;
import wacc.middleware.ast_nodes.statement_ast.RHSAssignAST;
import wacc.middleware.ast_nodes.statement_ast.ReadAST;
import wacc.middleware.ast_nodes.statement_ast.ReturnAST;
import wacc.middleware.ast_nodes.statement_ast.SkipAST;
import wacc.middleware.ast_nodes.statement_ast.VariableDeclarationAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.BreakAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ContinueAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.ForAST;
import wacc.middleware.ast_nodes.statement_ast.loop_ast.WhileAST;
import wacc.middleware.ast_nodes.types_ast.ArrayTypeAST;
import wacc.middleware.ast_nodes.types_ast.BaseTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairElemTypeAST;
import wacc.middleware.ast_nodes.types_ast.PairTypeAST;
import wacc.middleware.ast_nodes.types_ast.PointerTypeAST;

// Tries to simplify the AST as much as possible by keeping track of
// values of variables to reduce the number of instructions
public class ControlFlowAnalyser extends NodeASTVisitor<NodeAST> {

  private ValueTable values = new ValueTable();

  private final int MAX_VALUE = 2147483647;
  private final int MIN_VALUE = -2147483648;


  @Override
  public ProgAST visit(ProgAST prog) {
    ProgAST newProg = new ProgAST(prog.getErrors(), prog.getCtx(),
        prog.getFilename(), prog.getImportASTS(),
        visit(prog.getFunctionDeclarationASTS()),
        (StatementAST) visit(prog.getStatementAST()));
    newProg.check();

    return newProg;
  }

  @Override
  public NodeAST visit(ArrayAST array) {
    return new ArrayAST(array.getErrors(), array.getCtx(),
        visit(array.getExpressionASTList()));
  }

  @Override
  public NodeAST visit(ArrayElemAST arrayElem) {
    return new ArrayAST(arrayElem.getErrors(), arrayElem.getCtx(),
        visit(arrayElem.getExpressionASTs()));
  }

  @Override
  public NodeAST visit(BinOpExprAST binOpExpr) {
    NodeAST left = visit(binOpExpr.getLeftExprAST());
    NodeAST right = visit(binOpExpr.getRightExprAST());
    if (left instanceof LiteralsAST && right instanceof LiteralsAST) {
      int a = 0, b = 0;
      if (((LiteralsAST) left).getType() instanceof INT) {
        a = Integer.parseInt(((LiteralsAST) left).getText());
        b = Integer.parseInt(((LiteralsAST) right).getText());
        if (b == 0 && binOpExpr.getOperator().equals("/")) {
          return binOpExpr;
        }
        if (b == 0 && binOpExpr.getOperator().equals("%")) {
          return binOpExpr;
        }
      } else if (((LiteralsAST) left).getType() instanceof CHAR) {
        a = ((LiteralsAST) left).getText().charAt(0);
        b = ((LiteralsAST) right).getText().charAt(0);
      } else if (((LiteralsAST) left).getType() instanceof BOOL) {
        a = ((LiteralsAST) left).getText().equals("true") ? 1 : 0;
        b = ((LiteralsAST) right).getText().equals("true") ? 1 : 0;
      }

      long value = 0;
      switch (binOpExpr.getOperator()) {
        case "+":
          value = (long) a + b;
          if (value > MAX_VALUE || value < MIN_VALUE) {
            // Overflow error detected, 'undo' optimization
            return binOpExpr;
          }
          break;
        case "-":
          value = (long) a - b;
          if (value > MAX_VALUE || value < MIN_VALUE) {
            // Overflow error detected, 'undo' optimization
            return binOpExpr;
          }
          break;
        case "*":
          value = (long) a * b;
          if (value > MAX_VALUE || value < MIN_VALUE) {
            // Overflow error detected, 'undo' optimization
            return binOpExpr;
          }
          break;
        case "/":
          value = a / b;
          break;
        case "&":
          value = a & b;
          break;
        case "|":
          value = a | b;
          break;
        case "%":
          value = a % b;
          break;
        case "^":
          value = (int) Math.pow((double) a, (double) b);
          break;
        case "<":
          value = a < b ? 1 : 0;
          break;
        case "<=":
          value = a <= b ? 1 : 0;
          break;
        case ">":
          value = a < b ? 1 : 0;
          break;
        case ">=":
          value = a <= b ? 1 : 0;
          break;
        case "&&":
          System.out.println("&&");
          value = (a == 1 && b == 1) ? 1 : 0;
          break;
        case "||":
          value = (a == 1 || b == 1) ? 1 : 0;
          break;
        case "==":
          value = (a == b) ? 1 : 0;
          break;
        }

        if (binOpExpr.getType() instanceof INT) {
          return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(), (int) value);
        } else if (binOpExpr.getType() instanceof BOOL) {
          return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(), value == 1);
        } else if (binOpExpr.getType() instanceof CHAR) {
          return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(), (int) value);
        }
    }

    return binOpExpr;
  }

  @Override
  public NodeAST visit(IdentifierAST identifier) {
    NodeAST value = values.lookupAll(identifier.getIdentifier());
    System.out.println("here " + value);
    if (value == null) {
      return identifier;
    }
    return value;
  }

  @Override
  public NodeAST visit(LiteralsAST literal) {
    return literal;
  }

  @Override
  public NodeAST visit(UnaryOpExprAST unaryOpExpr) {
    ExpressionAST expr =  visit(unaryOpExpr.getExpr());
    String operator = unaryOpExpr.getOperator();
    if (expr instanceof LiteralsAST) {
      String str = ((LiteralsAST) expr).getText();
      switch (operator) {
        case "!":
          boolean value = false;
          if (str.equals("true")) {
            value = false;
          } else if (str.equals("false")) {
            value = true;
          }
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
              value);
        case "ord":
          int ascii = str.charAt(0);
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
              ascii);
        case "chr":
          char c = (char) Integer.parseInt(str);
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(), "'" + c + "'");
      }
    }

    if (expr instanceof ArrayAST && operator.equals("len")) {
      return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
          ((ArrayAST) expr).getExpressionASTList().size());
    }


    return new UnaryOpExprAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
        expr, unaryOpExpr.getOperator());
  }

  @Override
  public FunctionDeclarationAST visit(FunctionDeclarationAST functionDeclaration) {
    values = new ValueTable(values);
    for (ParamAST param : functionDeclaration.getParamASTList()) {
      values.add(param.getParamName(),
          new IdentifierAST(functionDeclaration.getErrors(),
              functionDeclaration.getCtx(), param.getParamName()));
    }
    StatementAST stat = (StatementAST) visit(functionDeclaration.getStatementAST());
    values = values.getEncValueTable();

    return new FunctionDeclarationAST(functionDeclaration.getErrors(),
        functionDeclaration.getCtx(), functionDeclaration.getTypeAST(),
        functionDeclaration.getFuncName(), functionDeclaration.getParamASTList(),
        stat);
  }

  @Override
  public NodeAST visit(FunctionCallAST functionCall) {
    return new FunctionCallAST(functionCall.getErrors(), functionCall.getCtx(),
        functionCall.getFuncName(), visit(functionCall.getActuals()));
  }

  @Override
  public ParamAST visit(ParamAST param) {
    return param;
  }

  @Override
  public NewPairAST visit(NewPairAST newPair) {
    return new NewPairAST(newPair.getErrors(), newPair.getCtx(),
        (ExpressionAST) visit(newPair.getFstExpr()),
        (ExpressionAST) visit(newPair.getSndExpr()));
  }

  @Override
  public NodeAST visit(PairElemAST pairElem) {
    return null;
  }

  @Override
  public NodeAST visit(AssignmentAST assignment) {
    LHSAssignAST lhs = assignment.getLHS();
    RHSAssignAST rhs = (RHSAssignAST) visit(assignment.getRHS());
    if (lhs.getIdentifier() != null) {
      values.add(lhs.getIdentifier(), rhs.getExpressionAST());
    }
    return new AssignmentAST(assignment.getErrors(), assignment.getCtx(),
        assignment.getLHS(), rhs);
  }

  @Override
  public NodeAST visit(BeginAST begin) {
    values = new ValueTable(values);
    StatementAST stat = (StatementAST) visit(begin.getStatementAST());
    values = values.getEncValueTable();
    return new BeginAST(begin.getErrors(), begin.getCtx(), stat);
  }

  @Override
  public StatementAST visit(ChainedStatementAST chainedStatement) {
    return new ChainedStatementAST(chainedStatement.getErrors(),
        chainedStatement.getCtx(),
        (StatementAST) visit(chainedStatement.getStatementAST1()),
        (StatementAST) visit(chainedStatement.getStatementAST2()));
  }

  @Override
  public NodeAST visit(ExitAST exit) {
    return new ExitAST(exit.getErrors(), exit.getCtx(),
        visit(exit.getExpressionAST()));
  }

  @Override
  public NodeAST visit(FreeAST free) {
    return new FreeAST(free.getErrors(), free.getCtx(),
        visit(free.getExpr()));
  }

  @Override
  public NodeAST visit(IfElseAST ifElse) {
    ExpressionAST condition = visit(ifElse.getExpressionAST());
    StatementAST first = (StatementAST) visit(ifElse.getFirstStatAST());
    StatementAST second = (StatementAST) visit(ifElse.getSecondStatAST());

    if (condition instanceof LiteralsAST) {
      String bool = ((LiteralsAST) condition).getText();
      if (bool.equals("true")) {
        return first;
      } else if (bool.equals("false")) {
        return second;
      }
    }
    return new IfElseAST(ifElse.getErrors(), ifElse.getCtx(), condition,
        first, second);
  }

  @Override
  public NodeAST visit(PrintAST print) {
    ExpressionAST expr = visit(print.getExpr());
    return new PrintAST(print.getErrors(), print.getCtx(), expr,
        print.isNewLine());
  }

  @Override
  public NodeAST visit(ReadAST read) {
    return read;
  }

  @Override
  public NodeAST visit(ReturnAST returnStatement) {
    return new ReturnAST(returnStatement.getErrors(), returnStatement.getCtx(),
            visit(returnStatement.getExpr()));
  }

  @Override
  public NodeAST visit(LHSAssignAST lhs) {
    return lhs;
  }

  public ExpressionAST visit(ExpressionAST expr) {
    if (expr instanceof BinOpExprAST) {
      return (ExpressionAST) visit((BinOpExprAST) expr);
    } else if (expr instanceof IdentifierAST) {
      return (ExpressionAST) visit((IdentifierAST) expr);
    } else if (expr instanceof UnaryOpExprAST) {
      return (ExpressionAST) visit((UnaryOpExprAST) expr);
    } else if (expr instanceof LiteralsAST) {
      return (ExpressionAST) visit((LiteralsAST) expr);
    }
    return expr;
  }

  @Override
  public NodeAST visit(RHSAssignAST rhs) {
    if (rhs.getExpressionAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(),
          visit(rhs.getExpressionAST()));

    } else if (rhs.getFunctionCallAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(),
          (FunctionCallInterface) visit((FunctionCallAST) rhs.getFunctionCallAST()));

    } else if (rhs.getNewPairAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(),
          visit(rhs.getNewPairAST()));

    } else if (rhs.getArrayAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(),
          (ArrayAST) visit(rhs.getArrayAST()));
    }

    return rhs;
  }

  @Override
  public NodeAST visit(SkipAST skip) {
    return skip;
  }

  @Override
  public NodeAST visit(SizeOfAST sizeOf) {
    return sizeOf;
  }

  @Override
  public NodeAST visit(VariableDeclarationAST variableDeclaration) {
    RHSAssignAST rhs = (RHSAssignAST) visit(variableDeclaration.getRhsAssignAST());
    if (rhs.getExpressionAST() != null) {
      values.add(variableDeclaration.getVarName(), rhs.getExpressionAST());
    }
//    if (rhs.getNewPairAST() != null) {
//      values.add(variableDeclaration.getVarName(), rhs.getExpressionAST());
//    }
    return new VariableDeclarationAST(variableDeclaration.getErrors(),
        variableDeclaration.getCtx(),
        variableDeclaration.getTypeAST(), variableDeclaration.getVarName(),
        rhs);
  }

  @Override
  public NodeAST visit(ForAST forLoop) {
    return forLoop;
  }

  @Override
  public NodeAST visit(WhileAST whileLoop) {
    return whileLoop;
  }

  @Override
  public NodeAST visit(ContinueAST continueAST) {
    return continueAST;
  }

  @Override
  public NodeAST visit(BreakAST breakAST) {
    return breakAST;
  }

  public NodeASTList visit(NodeASTList nodeList) {
    return new NodeASTList(nodeList.getErrors(), nodeList.getCtx(),
        (List) nodeList.getASTList().stream().
            map(e -> visit((NodeAST) e)).collect(Collectors.toList()));
  }

  @Override
  public NodeAST visit(BaseTypeAST baseType) {
    return baseType;
  }

  @Override
  public NodeAST visit(ArrayTypeAST arrayType) {
    return arrayType;
  }

  @Override
  public NodeAST visit(PairTypeAST pairType) {
    return pairType;
  }

  @Override
  public NodeAST visit(PairElemTypeAST pairElemType) {
    return pairElemType;
  }

  @Override
  public NodeAST visit(PointerTypeAST pointerType) {
    return pointerType;
  }

  @Override
  public NodeAST visit(PointerElemAST pointerElem) {
    return pointerElem;
  }



  @Override
  public NodeAST visit(MethodDeclarationAST classDef) {
    return null;
  }

  @Override
  public NodeAST visit(FieldAST fields) {
    return null;
  }

  @Override
  public NodeAST visit(ClassDefinitionAST classDef) {
    return null;
  }

  @Override
  public NodeAST visit(NewObjectAST newObjectAST) {
    return null;
  }

  @Override
  public NodeAST visit(MethodCallAST classDef) {
    return null;
  }

  public class ValueTable {

    private final ValueTable encValueTable;
    private final LinkedHashMap<String, NodeAST> dict;
    protected TYPE scopeReturnType = null;

    public ValueTable() {
      this(null);
    }

    public ValueTable(ValueTable st, TYPE scopeReturnType) {
      this(st);
      this.scopeReturnType = scopeReturnType;
    }

    public ValueTable(ValueTable st) {
      encValueTable = st;
      if (st != null) {
        scopeReturnType = st.getScopeReturnType();
      }
      dict = new LinkedHashMap<>();

    }

    public ValueTable getEncValueTable() {
      return encValueTable;
    }

    public TYPE getScopeReturnType() {
      return scopeReturnType;
    }


    public void add(String name, NodeAST obj) {
      dict.put(name, obj);
    }

    // lookup identifier in the local scope
    public NodeAST lookup(String name) {
      return dict.get(name);
    }

    // backtracking algorithm to lookup identifier if it is in scope
    public NodeAST lookupAll(String name) {
      NodeAST obj = null;
      ValueTable st;
      for (st = this; obj == null && st != null; st = st.encValueTable) {
        obj = st.lookup(name);
      }
      return obj;
    }
  }
}
