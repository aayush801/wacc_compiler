parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

// EOF indicates that the program must consume to the end of the input.
prog:
    BEGIN (func)* stat END EOF
    | expr EOF
    | EOF
;

//function
func: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;


//parameters
param: type IDENT;
paramList: param (COMMA param)*;

//argument list
argList: expr (COMMA expr)* ;

//statements
stat:
  SKIP_STATEMENT
  | type IDENT EQUALS assignRHS
  | assignLHS EQUALS assignRHS
  | READ assignLHS
  | FREE expr
  | RETURN expr
  | EXIT expr
  | PRINT expr
  | PRINT_LINE expr
  | IF expr THEN stat ELSE stat END_IF
  | WHILE expr DO stat END_WHILE
  | BEGIN stat END
  | stat SEPERATOR stat
;

//left hand side assignment
assignLHS:
  IDENT
  | arrayElem
  | pairElem
;

//right hand side assignment
assignRHS:
  expr
  | array
  | CREATE_PAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
  | pairElem
  | CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES
;

//expressions
expr:
  | BOOLEAN
  | PAIR
  | IDENT
  | STRING
  | CHARACTER
  | arrayElem
  | unaryOper expr
  | term5
;

term5:
  term5 binOp5 term4
  | term4
;

term4:
  term4 binOp4 term3
  | term3
;

term3:
  term3 binOp3 term2
  | term2
;

term2:
  term2 binOp2 term1
  | term1
;

term1:
  term1 binOp1 factor
  | factor
;


factor:
  (PLUS | MINUS)? INTEGER                       #num
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES     #otherExpr
;


//binary operators
binaryOper :
    (DIVIDE | MULTIPLY | MOD)
  | (PLUS | MINUS)
  | (GRE | GR | LSE | LS | EQ | NEQ)
  | AND
  | OR
;

binOp5 : OR ;
binOp4 : AND ;
binOp3 : GRE | GR | LSE | LS | EQ | NEQ ;
binOp2 : PLUS | MINUS ;
binOp1 : DIVIDE | MULTIPLY | MOD ;

//unary operators
unaryOper: NOT | MINUS | LENGTH | ORD | CHR ;

//typings
type:
  BASE_TYPE
  | ARRAY_TYPE
  | pairType
;

//arrays
arrayElem: IDENT (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+ ;
array: OPEN_SQUARE_BRACKET (expr (COMMA expr)*)? CLOSE_SQUARE_BRACKET ;

//pairs
pairType: PAIR_TYPE OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;
pairElemType:
  BASE_TYPE
  | ARRAY_TYPE
  | PAIR_TYPE
;
pairElem:
  PAIR_FIRST expr
  | PAIR_SECOND expr
;


