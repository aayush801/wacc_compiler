package wacc.extension;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import wacc.frontend.identifier_objects.TYPE;
import wacc.frontend.identifier_objects.basic_types.BOOL;
import wacc.frontend.identifier_objects.basic_types.CHAR;
import wacc.frontend.identifier_objects.basic_types.INT;
import wacc.frontend.identifier_objects.basic_types.STR;
import wacc.middleware.ExpressionAST;
import wacc.middleware.NodeAST;
import wacc.middleware.NodeASTVisitor;
import wacc.middleware.ast_nodes.NodeASTList;
import wacc.middleware.ast_nodes.StatementAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayAST;
import wacc.middleware.ast_nodes.arrays_ast.ArrayElemAST;
import wacc.middleware.ast_nodes.class_ast.ClassDefinitionAST;
import wacc.middleware.ast_nodes.class_ast.ConstructorAST;
import wacc.middleware.ast_nodes.class_ast.FieldAST;
import wacc.middleware.ast_nodes.class_ast.MethodCallAST;
import wacc.middleware.ast_nodes.class_ast.MethodDeclarationAST;
import wacc.middleware.ast_nodes.class_ast.NewObjectAST;
import wacc.middleware.ast_nodes.class_ast.ObjectFieldAST;
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
  private int insideScope = 0;
  private final int MAX_VALUE = 2147483647;
  private final int MIN_VALUE = -2147483648;

  @Override
  public ProgAST visit(ProgAST prog) {
    ProgAST newProg =
        new ProgAST(
            prog.getErrors(),
            prog.getCtx(),
            prog.getFilename(),
            new NodeASTList<>(prog.getErrors(), prog.getCtx()),
            visit(prog.getClassDefinitionASTS()),
            visit(prog.getFunctionDeclarationASTS()),
            (StatementAST) visit(prog.getStatementAST()));
    return newProg;
  }

  @Override
  public NodeAST visit(ArrayAST array) {
    return new ArrayAST(array.getErrors(), array.getCtx(),
        visit(array.getExpressionASTList()));
  }

  @Override
  public NodeAST visit(ArrayElemAST arrayElem) {
    return new ArrayElemAST(
        arrayElem.getErrors(),
        arrayElem.getCtx(),
        arrayElem.getArrayName(),
        visit(arrayElem.getExpressionASTs()));
  }

  @Override
  public NodeAST visit(BinOpExprAST binOpExpr) {
    ExpressionAST left = visit(binOpExpr.getLeftExprAST());
    ExpressionAST right = visit(binOpExpr.getRightExprAST());

    // If both operands are literal values then they can be simplfied at compile time
    if (left instanceof LiteralsAST && right instanceof LiteralsAST) {
      int a, b;
      if (left.getType() instanceof INT) {
        a = Integer.parseInt(((LiteralsAST) left).getText());
        b = Integer.parseInt(((LiteralsAST) right).getText());

        // avoid div and mod overflow error
        if (b == 0
            && (binOpExpr.getOperator().equals("/") ||
            binOpExpr.getOperator().equals("%"))) {
          return binOpExpr;
        }
      } else if (left.getType() instanceof CHAR) {
        a = ((LiteralsAST) left).getText()
            .charAt(((LiteralsAST) left).getText().length() - 2);
        b = ((LiteralsAST) right).getText()
            .charAt(((LiteralsAST) right).getText().length() - 2);

      } else if (left.getType() instanceof BOOL) {
        a = ((LiteralsAST) left).getText().equals("true") ? 1 : 0;
        b = ((LiteralsAST) right).getText().equals("true") ? 1 : 0;

      } else if (left.getType() instanceof STR) {
        boolean stringsMatch =
            ((LiteralsAST) left).getText()
                .equals(((LiteralsAST) right).getText());
        return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(),
            stringsMatch);

      } else {
        // If the literal is not a primitive type it is returned unchanged
        return binOpExpr;
      }

      long value = 0;
      switch (binOpExpr.getOperator()) {
        case "+":
          value = (long) a + b;
          if (value >= MAX_VALUE || value <= MIN_VALUE) {
            // Overflow error detected, 'undo' optimization
            return binOpExpr;
          }
          break;
        case "-":
          value = (long) a - b;
          if (value >= MAX_VALUE || value <= MIN_VALUE) {
            // Overflow error detected, 'undo' optimization
            return binOpExpr;
          }
          break;
        case "*":
          value = (long) a * b;
          if (value >= MAX_VALUE || value <= MIN_VALUE) {
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
          value = (int) Math.pow(a, b);
          break;
        case "<":
          value = a < b ? 1 : 0;
          break;
        case "<=":
          value = a <= b ? 1 : 0;
          break;
        case ">":
          value = a > b ? 1 : 0;
          break;
        case ">=":
          value = a >= b ? 1 : 0;
          break;
        case "&&":
          value = (a == 1 && b == 1) ? 1 : 0;
          break;
        case "||":
          value = (a == 1 || b == 1) ? 1 : 0;
          break;
        case "==":
          value = (a == b) ? 1 : 0;
          break;
        case "!=":
          value = (a != b) ? 1 : 0;
          break;
      }

      if (binOpExpr.getType() instanceof INT) {
        return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(),
            (int) value);
      } else if (binOpExpr.getType() instanceof BOOL) {
        return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(),
            value == 1);
      } else if (binOpExpr.getType() instanceof CHAR) {
        return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(),
            "'" + (char) value + "'");
      }
    }

    // If one of the operands has a literal value then the expression can be
    // simplified for particular values
    if (left instanceof LiteralsAST || right instanceof LiteralsAST) {
      switch (binOpExpr.getOperator()) {
        case "|":
        case "+":
          if (left instanceof LiteralsAST && left.getType() instanceof INT) {
            // Adding 0 or bitwise OR with 0 has no effect
            if (Integer.parseInt(((LiteralsAST) left).getText()) == 0) {
              return right;
            }
          } else if (right instanceof LiteralsAST &&
              right.getType() instanceof INT) {
            // Adding 0 or bitwise OR with 0 has no effect
            if (Integer.parseInt(((LiteralsAST) right).getText()) == 0) {
              return left;
            }
          }
          break;
        case "-":
          // Subtracting 0 has no effect
          if (right instanceof LiteralsAST && right.getType() instanceof INT) {
            if (Integer.parseInt(((LiteralsAST) right).getText()) == 0) {
              return left;
            }
          }
          break;
        case "&":
        case "*":
          // Multiplying by 0 will always result in 0
          if (left instanceof LiteralsAST && left.getType() instanceof INT) {
            if (Integer.parseInt(((LiteralsAST) left).getText()) == 0) {
              return new LiteralsAST(binOpExpr.getErrors(),
                  binOpExpr.getCtx(), 0);
            }
          } else if (right instanceof LiteralsAST &&
              right.getType() instanceof INT) {
            if (Integer.parseInt(((LiteralsAST) right).getText()) == 0) {
              return new LiteralsAST(binOpExpr.getErrors(),
                  binOpExpr.getCtx(), 0);
            }
          }
          break;
        case "^":
          if (left instanceof LiteralsAST && left.getType() instanceof INT) {
            if (Integer.parseInt(((LiteralsAST) left).getText()) == 1) {
              return left;
            }
          } else if (right instanceof LiteralsAST &&
              right.getType() instanceof INT) {
            if (Integer.parseInt(((LiteralsAST) right).getText()) == 0) {
              return new LiteralsAST(binOpExpr.getErrors(), binOpExpr.getCtx(), 1);
            }
          }
          break;
        case "&&":
          // Logical AND with false will always be false
          if (left instanceof LiteralsAST && left.getType() instanceof BOOL) {
            if (((LiteralsAST) left).getText().equals("false")) {
              return left;
            }
          } else if (right instanceof LiteralsAST &&
              right.getType() instanceof BOOL) {
              if (((LiteralsAST) right).getText().equals("false")) {
                return right;
              }
          }
          break;
        case "||":
          // Logical OR with true will always be true
          if (left instanceof LiteralsAST && left.getType() instanceof BOOL) {
            if (((LiteralsAST) left).getText().equals("true")) {
              return left;
            }
          } else if (right instanceof LiteralsAST &&
              right.getType() instanceof BOOL) {
            if (((LiteralsAST) right).getText().equals("true")) {
              return right;
            }
          }
          break;
      }
    }

    return binOpExpr;
  }

  @Override
  public NodeAST visit(IdentifierAST identifier) {
    if (insideScope > 0)
      return identifier;

    NodeAST value = values.lookupAll(identifier.getIdentifier());
    if (!(value instanceof LiteralsAST)) {
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
    ExpressionAST expr = visit(unaryOpExpr.getExpr());
    String operator = unaryOpExpr.getOperator();

    // If the argument is a literal value the unary operators
    // can be applied at compile time
    if (expr instanceof LiteralsAST) {
      String str = ((LiteralsAST) expr).getText();

      switch (operator) {
        case "!":
          // Negate value
          boolean value = false;
          if (str.equals("true")) {
            value = false;
          } else if (str.equals("false")) {
            value = true;
          }
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
              value);

        case "ord":
          // ASCII code for character
          int ascii = str.charAt(str.length() - 2);
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
              ascii);

        case "chr":
          // Character with given ASCII code
          char c = (char) Integer.parseInt(str);
          return new LiteralsAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
              "'" + c + "'");
      }
    }

    // The length of an array can be found
    if (expr instanceof ArrayAST && operator.equals("len")) {
      return new LiteralsAST(
          unaryOpExpr.getErrors(),
          unaryOpExpr.getCtx(),
          ((ArrayAST) expr).getExpressionASTList().size());
    }

    return new UnaryOpExprAST(unaryOpExpr.getErrors(), unaryOpExpr.getCtx(),
        expr, unaryOpExpr.getOperator());
  }

  @Override
  public FunctionDeclarationAST visit(FunctionDeclarationAST functionDeclaration) {
    // Value table for new scope
    values = new ValueTable(values);
    // The parameters are added to current scope just as identifiers
    for (ParamAST param : functionDeclaration.getParamASTList()) {
      values.add(
          param.getParamName(),
          new IdentifierAST(
              functionDeclaration.getErrors(), functionDeclaration.getCtx(),
              param.getParamName()));
    }

    // The body of the function is optimised
    StatementAST stat = (StatementAST) visit(functionDeclaration.getStatementAST());
    insideScope = 0;

    // Original value table
    values = values.getEncValueTable();

    // Function with optimised body
    return new FunctionDeclarationAST(
        functionDeclaration.getErrors(),
        functionDeclaration.getCtx(),
        functionDeclaration.getTypeAST(),
        functionDeclaration.getBaseName(),
        functionDeclaration.getParamASTList(),
        stat);
  }

  @Override
  public NodeAST visit(FunctionCallAST functionCall) {
    // The expressions passed to the function are simplified
    return new FunctionCallAST(
        functionCall.getErrors(),
        functionCall.getCtx(),
        functionCall.getBaseName(),
        visit(functionCall.getActuals()));
  }

  @Override
  public ParamAST visit(ParamAST param) {
    return param;
  }

  @Override
  public NewPairAST visit(NewPairAST newPair) {
    // Each expression in pair is simplified
    return new NewPairAST(
        newPair.getErrors(),
        newPair.getCtx(),
        visit(newPair.getFstExpr()),
        visit(newPair.getSndExpr()));
  }

  @Override
  public NodeAST visit(PairElemAST pairElem) {
    return pairElem;
  }

  @Override
  public NodeAST visit(AssignmentAST assignment) {
    LHSAssignAST lhs = assignment.getLHS();
    RHSAssignAST rhs = (RHSAssignAST) visit(assignment.getRHS());

    if (lhs.getIdentifier() != null) {
        values.addAll(lhs.getIdentifier(),
            new IdentifierAST(assignment.getErrors(), assignment.getCtx(),
                lhs.getIdentifier()));
        values.add(lhs.getIdentifier(), rhs.getExpressionAST());

    }

    return new AssignmentAST(assignment.getErrors(), assignment.getCtx(),
        assignment.getLHS(), rhs);
  }

  @Override
  public NodeAST visit(BeginAST begin) {

    insideScope++;
    values = new ValueTable(values);
    StatementAST stat = (StatementAST) visit(begin.getStatementAST());
    values = values.getEncValueTable();
    insideScope--;

    return new BeginAST(begin.getErrors(), begin.getCtx(), stat);
  }

  @Override
  public StatementAST visit(ChainedStatementAST chainedStatement) {
    StatementAST first, second;
    first = (StatementAST) visit(chainedStatement.getStatementAST1());
    second = (StatementAST) visit(chainedStatement.getStatementAST2());

    return new ChainedStatementAST(
        chainedStatement.getErrors(), chainedStatement.getCtx(), first, second);
  }

  @Override
  public NodeAST visit(ExitAST exit) {
    return new ExitAST(exit.getErrors(), exit.getCtx(),
        visit(exit.getExpressionAST()));
  }

  @Override
  public NodeAST visit(FreeAST free) {
    return new FreeAST(free.getErrors(), free.getCtx(), visit(free.getExpr()));
  }

  @Override
  public NodeAST visit(IfElseAST ifElse) {
    ExpressionAST condition = visit(ifElse.getExpressionAST());
    if (condition instanceof LiteralsAST) {
      String bool = ((LiteralsAST) condition).getText();
      if (bool.equals("true")) {
        return visit(ifElse.getFirstStatAST());
      } else if (bool.equals("false")) {
        return visit(ifElse.getSecondStatAST());
      }
    }

    insideScope++;
    return ifElse;
    /*
    values = new ValueTable(values);
    insideScope++;
    StatementAST first = (StatementAST) visit(ifElse.getFirstStatAST());
    values = values.getEncValueTable();
    values = new ValueTable(values);
    StatementAST second = (StatementAST) visit(ifElse.getSecondStatAST());
    insideScope--;
    values = values.getEncValueTable();

    return new IfElseAST(ifElse.getErrors(), ifElse.getCtx(), condition,
        first, second);

     */
  }

  @Override
  public NodeAST visit(PrintAST print) {
    ExpressionAST expr = visit(print.getExpr());
    return new PrintAST(print.getErrors(), print.getCtx(), expr,
        print.isNewLine());
  }

  @Override
  public NodeAST visit(ReadAST read) {
    values.add(read.getLHS().getIdentifier(),
        new IdentifierAST(read.getErrors(), read.getCtx(),
            read.getLHS().getIdentifier()));
    return read;
  }

  @Override
  public NodeAST visit(ReturnAST returnStatement) {
    return new ReturnAST(
        returnStatement.getErrors(), returnStatement.getCtx(),
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
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(), visit(rhs.getExpressionAST()));

    } else if (rhs.getFunctionCallAST() != null) {
      return new RHSAssignAST(
          rhs.getErrors(),
          rhs.getCtx(),
          (FunctionCallInterface) rhs.getFunctionCallAST().accept(this));

    } else if (rhs.getNewPairAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(), visit(rhs.getNewPairAST()));

    } else if (rhs.getArrayAST() != null) {
      return new RHSAssignAST(rhs.getErrors(), rhs.getCtx(), (ArrayAST) visit(rhs.getArrayAST()));
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
    } else {
      values.add(variableDeclaration.getVarName(),
          new IdentifierAST(variableDeclaration.getErrors(),
              variableDeclaration.getCtx(),
              variableDeclaration.getVarName()));
    }

    return new VariableDeclarationAST(
        variableDeclaration.getErrors(),
        variableDeclaration.getCtx(),
        variableDeclaration.getTypeAST(),
        variableDeclaration.getVarName(),
        rhs);
  }

  @Override
  public NodeAST visit(ForAST forLoop) {
    insideScope++;
    return forLoop;
    /*
    values = new ValueTable(values);
    StatementAST body = (StatementAST) visit(forLoop.getStatementAST());
    values = values.getEncValueTable();
    insideScope--;

    return new ForAST(
        forLoop.getErrors(),
        forLoop.getCtx(),
        forLoop.getInitialisation(),
        forLoop.getConditionAST(),
        body);

     */
  }

  @Override
  public NodeAST visit(WhileAST whileLoop) {
    insideScope++;
    return whileLoop;
    /*
    ExpressionAST condition = visit(whileLoop.getConditionAST());
    if (condition instanceof LiteralsAST &&
        ((LiteralsAST) condition).getText().equals("false")) {
      return new SkipAST(whileLoop.getErrors(), whileLoop.getCtx());
    }

    insideScope++;
    values = new ValueTable(values);
    StatementAST body = (StatementAST) visit(whileLoop.getStatementAST());
    values = values.getEncValueTable();
    insideScope--;


    if (body instanceof SkipAST) {
      return body;
    }
    return new WhileAST(whileLoop.getErrors(), whileLoop.getCtx(), condition,
        body);

     */
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
    return new NodeASTList(
        nodeList.getErrors(),
        nodeList.getCtx(),
        (List)
            nodeList.getASTList().stream()
                .map(e -> visit((NodeAST) e))
                .collect(Collectors.toList()));
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
  public NodeAST visit(MethodDeclarationAST methodDecl) {
    return methodDecl;
  }

  @Override
  public NodeAST visit(FieldAST fields) {
    return fields;
  }

  @Override
  public NodeAST visit(ObjectFieldAST objField) {
    return objField;
  }

  @Override
  public NodeAST visit(ConstructorAST constructor) {
    return constructor;
  }

  @Override
  public NodeAST visit(ClassDefinitionAST classDef) {
    return classDef;
  }

  @Override
  public NodeAST visit(NewObjectAST newObjectAST) {
    return newObjectAST;
  }

  @Override
  public NodeAST visit(MethodCallAST methodCall) {
    return methodCall;
  }

  public static class ValueTable {

    private final ValueTable encValueTable;
    private final LinkedHashMap<String, NodeAST> dict;


    private ValueTable() {
      this(null);
    }

    public ValueTable(ValueTable vt) {
      encValueTable = vt;
      dict = new LinkedHashMap<>();
    }

    private ValueTable getEncValueTable() {
      return encValueTable;
    }

    public void add(String name, NodeAST obj) {
      dict.put(name, obj);
    }

    private void addAll(String name, NodeAST obj) {
      ValueTable curr = this;
      while (curr.getEncValueTable() != null && curr.lookup(name) == null) {
        curr = curr.getEncValueTable();
      }
      curr.add(name, obj);
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
