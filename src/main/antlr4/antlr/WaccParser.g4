parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
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
  | NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
  | pairElem
  | CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES
;

//expressions
expr:
    (PLUS | MINUS)? INTEGER                     #integer
  | BOOLEAN                                     #boolean
  | PAIR                                        #pair
  | STRING                                      #string
  | CHARACTER                                   #character
  | IDENT                                       #identifier
  | arrayElem                                   #arrayExpr
  | unaryOperator expr                          #unaryOperation
  | expr (DIVIDE | MULTIPLY | MOD) expr         #divMulModOperation
  | expr (PLUS | MINUS) expr                    #plusMinusOperation
  | expr (GT | GTE | LT | LTE) expr             #greLseComparison
  | expr (EQ | NEQ) expr                        #eqNeqComparison
  | expr AND expr                               #logicalAndOperation
  | expr OR expr                                #logicalOrOperation
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES     #bracketedExpr
;

//unary operators
unaryOperator: NOT | MINUS | LENGTH | ORD | CHR ;

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


