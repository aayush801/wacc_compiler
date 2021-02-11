package middleware.ast.statement_ast;

import identifier_objects.TYPE;
import identifier_objects.VARIABLE;
import middleware.ast.expression_ast.ExpressionAST;
import errors.semantic_errors.MismatchedTypes;
import errors.semantic_errors.Undefined;
import identifier_objects.IDENTIFIER;
import identifier_objects.VARIABLE;
import identifier_objects.polymorhpic_types.EXPR;
import middleware.ast.expression_ast.ExpressionAST;
import org.antlr.v4.runtime.Token;

import javax.swing.text.StyledEditorKit;

public class AssignmentAST extends StatementAST {

    private final LHSAssignAST LHS;
    private final RHSAssignAST RHS;

    public AssignmentAST(Token token, LHSAssignAST LHS, RHSAssignAST RHS) {
        super(token);
        this.LHS = LHS;
        this.RHS = RHS;
    }

    @Override
    public void check() {
        LHS.check();
        RHS.check();
        IDENTIFIER leftType = LHS.getType();
        TYPE rightType = RHS.getType();
        if (leftType == null) addError(new Undefined(LHS.token));
        else if (rightType == null) addError(new Undefined(RHS.token));
        else if (!isCompatible(leftType, rightType)) addError(new MismatchedTypes(token, rightType, leftType));
    }
}
