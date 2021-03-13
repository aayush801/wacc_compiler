parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

// EOF indicates that the program must consume to the end of the input.
prog:
  imports? BEGIN (funcDecl)* stat END EOF ;

//wacc.extension
//imported files
imports:
  IMPORT identifier
  | imports SEPERATOR imports
;

//function
funcDecl: type identifier OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END ;
funcCall: CALL identifier OPEN_PARENTHESES argList? CLOSE_PARENTHESES;

//parameters
param: type identifier;
paramList: param (COMMA param)*;

//argument list
argList: expr (COMMA expr)* ;

//statements
stat:
    SKIP_STATEMENT                        #skipStat
  | VISIBILITY? type identifier
                    EQUALS assignRHS      #assignIdent
  | assignLHS EQUALS assignRHS            #assignVars
  | READ assignLHS                        #readCall
  | MALLOC expr                           #mallocCall
  | FREE expr                             #freeCall
  | PRINT expr                            #printCall
  | PRINT_LINE expr                       #printlnCall
  | EXIT expr                             #exitStat
  | RETURN expr                           #returnStat
  | IF expr THEN stat ELSE stat END_IF    #ifThenElse
  | IF expr THEN stat END_IF              #ifThen
  | WHILE expr DO stat DONE               #whileDo
  | DO stat WHILE expr DONE               #doWhile
  | FOR OPEN_PARENTHESES stat?
      SEPERATOR expr?
      SEPERATOR stat?
    CLOSE_PARENTHESES stat END_FOR        #forLoop
  | BEGIN stat END                        #beginStat
  | stat SEPERATOR stat                   #seperateStat
  | CLASS IDENT
      stat
      funcDecl*
    DONE                                  #classDef
  | SWITCH expr
      (CASE expr COLON stat)*
      (DEFAULT COLON stat)?
    DONE                                  #switchStat
;

//typings
type:
  baseType
  | pairType
  | arrayType
  | pointerType
;

//base types
baseType:
    INT_TYPE
  | BOOL_TYPE
  | CHAR_TYPE
  | STRING_TYPE
  | VOID_TYPE
;

// array initialisation type
arrayType : (baseType | pairType) (OPEN_SQUARE_BRACKET CLOSE_SQUARE_BRACKET)+;

// pointer
pointerType : baseType STAR+ ;
pointerElem : STAR+ identifier ;

//left hand side assignment
assignLHS:
  identifier
  | arrayElem
  | pairElem
  | pointerElem
;

//right hand side assignment
assignRHS:
    expr
  | array
  | newPair
  | pairElem
  | funcCall
  | newObject
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
  | sizeOfCall
  | pointerElem
  | expr binaryOperator=(DIVIDE | STAR | MOD) expr
  | expr binaryOperator=(PLUS | MINUS) expr
  | expr binaryOperator=(GT | GTE | LT | LTE) expr
  | expr binaryOperator=(EQ | NEQ) expr
  | expr binaryOperator=(AND | BITWISE_AND) expr
  | expr binaryOperator=(OR | BITWISE_OR) expr
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES
  | identifier DOT identifier
;

//classes
newObject: NEW identifier OPEN_PARENTHESES argList? CLOSE_PARENTHESES ;
methodCall: identifier DOT identifier OPEN_PARENTHESES argList? CLOSE_PARENTHESES ;

//unary operators
unaryOperator: NOT | MINUS | LENGTH | ORD | CHR | INVERT | BITWISE_AND | MALLOC;

//arrays
arrayElem: identifier (OPEN_SQUARE_BRACKET expr CLOSE_SQUARE_BRACKET)+ ;
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

//literals
intLiter: (PLUS | MINUS)? INTEGER;
boolLiter: TRUE | FALSE ;
pairLiter: NULL;
strLiter: STRING;
floatLiter : (PLUS | MINUS)? FLOAT ;
charLiter: CHARACTER;
identifier: IDENT;


//sizeofcall
sizeOfCall: SIZE_OF (expr | type | OPEN_PARENTHESES type CLOSE_PARENTHESES);