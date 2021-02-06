parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

// EOF indicates that the program must consume to the end of the input.
prog:
    BEGIN (funcDecl)* stat END EOF
    | expr EOF
    | EOF
;

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
  BASE_TYPE
  | ARRAY_TYPE
  | pairType
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
  | newPair
  | pairElem
  | funcCall
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
  | op=unaryOperator expr                       #unaryOperation
  | expr op=(DIVIDE | MULTIPLY | MOD) expr      #divMulModOperation
  | expr op=(PLUS | MINUS) expr                 #plusMinusOperation
  | expr op=(GT | GTE | LT | LTE) expr          #greLseComparison
  | expr op=(EQ | NEQ) expr                     #eqNeqComparison
  | expr op=AND expr                            #logicalAndOperation
  | expr op=OR expr                             #logicalOrOperation
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES     #bracketedExpr
;

//unary operators
unaryOperator: NOT | MINUS | LENGTH | ORD | CHR ;



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
  PAIR_FIRST IDENT
  | PAIR_SECOND IDENT
;
newPair: NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES;


