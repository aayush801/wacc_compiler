parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

// EOF indicates that the program must consume to the end of the input.
prog: BEGIN (funcDecl)* stat END EOF ;

//function
funcDecl: type IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;
funcCall: CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES;

//parameters
param: type IDENT;
paramList: param (COMMA param)*;

//argument list
argList: expr (COMMA expr)* ;

//statements
stat:
    SKIP_STATEMENT                        #skipStat
  | type IDENT EQUALS assignRHS           #assignIdent
  | assignLHS EQUALS assignRHS            #assignVars
  | READ assignLHS                        #readCall
  | FREE expr                             #freeCall
  | RETURN expr                           #returnCall
  | EXIT expr                             #exitCall
  | PRINT expr                            #printCall
  | PRINT_LINE expr                       #printlnCall
  | IF expr THEN stat ELSE stat END_IF    #ifThenElse
  | WHILE expr DO stat END_WHILE          #whileDo
  | BEGIN stat END                        #beginStat
  | stat SEPERATOR stat                   #seperateStat
;

//typings
type:
  baseType
  | pairType
  | arrayType
;

//base types
baseType:
    INT_TYPE
  | BOOL_TYPE
  | CHAR_TYPE
  | STRING_TYPE
;

// array initialisation type
arrayType : (baseType | pairType) (OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET)+;

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
  | newPair
  | pairElem
  | funcCall
;

//expressions
expr:
    intLiter
  | boolLiter
  | pairLiter
  | strLiter
  | charLiter
  | identifier
  | arrayElem
  | unaryOperator expr
  | expr binaryOperator=(DIVIDE | MULTIPLY | MOD) expr
  | expr binaryOperator=(PLUS | MINUS) expr
  | expr binaryOperator=(GT | GTE | LT | LTE) expr
  | expr binaryOperator=(EQ | NEQ) expr
  | expr binaryOperator=AND expr
  | expr binaryOperator=OR expr
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES
;

//unary operators
unaryOperator: NOT | MINUS | LENGTH | ORD | CHR ;

//arrays
arrayElem: IDENT (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+ ;
array: OPEN_SQUARE_BRACKET (expr (COMMA expr)*)? CLOSE_SQUARE_BRACKET ;

//pairs
pairType: PAIR_TYPE OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES ;
pairElemType:
  baseType
  | arrayType
  | PAIR_TYPE
;
pairElem:
  PAIR_FIRST expr
  | PAIR_SECOND expr
;
newPair: NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES;

intLiter: (PLUS | MINUS)? INTEGER;
boolLiter: TRUE | FALSE ;
pairLiter: NULL;
strLiter: STRING;
charLiter: CHARACTER;
identifier: IDENT;