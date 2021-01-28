parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

//binary operators
binaryOper: PLUS | MINUS | DIVIDE | MULTIPLY | MOD | GRE | GR | LSE | LS | EQ | NEQ | AND | OR;

//unary operators
unaryOper: NOT | MINUS | LENGTH | ORD | CHR ;

//statements
stat:
  SKIP_STATEMENT
  | TYPE IDENT EQUALS assignRHS
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
  | CALL IDENT OPEN_PARENTHESES argList CLOSE_PARENTHESES
;

//expressions
expr:
  INTEGER
  | BOOLEAN
  | CHARACTER
  | STRING
  | PAIR
  | IDENT
  | arrayElem
  | unaryOper expr
  | expr binaryOper expr
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

//arrays
arrayElem: IDENT (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+ ;
array: OPEN_SQUARE_BRACKET expr (COMMA expr*)? CLOSE_SQUARE_BRACKET ;

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

// EOF indicates that the program must consume to the end of the input.
prog:
    BEGIN (func)* stat END
    | expr
;

//function
func: TYPE IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;

//parameters
param: TYPE IDENT;
paramList: param (COMMA param)*;

//argument list
argList: expr (COMMA expr)* ;

