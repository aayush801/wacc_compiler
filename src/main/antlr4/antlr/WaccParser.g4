parser grammar WaccParser;

options {
  tokenVocab=WaccLexer;
}

// EOF indicates that the program must consume to the end of the input.
prog:
  imports? BEGIN (classDef)* (funcDecl)* stat END EOF ;

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

assignment: '=' | INCREMENT ;

//statements
stat:
    SKIP_STATEMENT                        #skipStat
  | type identifier EQUALS assignRHS      #assignIdent
  | identifier
    op=(INCREMENT_BY | MULTIPLY_BY |
        DIVIDE_BY | DECREMENT_BY | AND_BY
        | OR_BY | BITWISE_AND_BY |
        BITWISE_OR_BY | MOD_BY)  expr    #binOpAssign

  | assignLHS EQUALS assignRHS            #assignVars
  | READ assignLHS                        #readCall
  | MALLOC expr                           #mallocCall
  | FREE expr                             #freeCall
  | PRINT expr                            #printCall
  | PRINT_LINE expr                       #printlnCall
  | EXIT expr                             #exitStat
  | RETURN expr                           #returnStat
  | IF expr THEN stat ELSE stat END_IF    #ifThenElse
  | IF expr stat END_IF                   #ifThen
  | WHILE expr DO stat DONE               #whileDo

  | DO stat WHILE expr DONE               #doWhile
  | FOR OPEN_PARENTHESES stat?
      SEPERATOR expr?
      SEPERATOR stat?
    CLOSE_PARENTHESES stat END_FOR        #forLoop

  | FOR_EACH type identifier IN identifier
      stat END_FOR                        #forEachLoop
  | BEGIN stat END                        #beginStat
  | stat SEPERATOR stat                   #seperateStat
  | SWITCH expr
      (CASE expr COLON stat)*
      (DEFAULT COLON stat)?
    DONE                                  #switchStat
  | CONTINUE                              #continueStat
  | BREAK                                 #breakStat
;


//typings
type:
  baseType
  | pairType
  | arrayType
  | pointerType
  | classType
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
  | objectField
;

//right hand side assignment
assignRHS:
    expr
  | array
  | newPair
  | pairElem
  | funcCall
  | newObject
  | methodCall
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
  | objectField
  | pointerElem
  | expr binaryOperator=(POWER | DIVIDE | STAR | MOD) expr
  | expr binaryOperator=(PLUS | MINUS) expr
  | expr binaryOperator=(GT | GTE | LT | LTE) expr
  | expr binaryOperator=(EQ | NEQ) expr
  | expr binaryOperator=(AND | BITWISE_AND) expr
  | expr binaryOperator=(OR | BITWISE_OR) expr
  | OPEN_PARENTHESES expr CLOSE_PARENTHESES
  | identifier DOT identifier
;

//classes
classDef: classType
      fields?
      constructor?
      methodDecl*
    DONE
;
newObject: NEW identifier OPEN_PARENTHESES argList? CLOSE_PARENTHESES;
fields:
    VISIBILITY (type identifier EQUALS assignRHS)
  | fields SEPERATOR fields
;
classType: CLASS identifier;
constructor: identifier OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END;

objectField: identifier DOT identifier;
methodDecl: VISIBILITY funcDecl;
methodCall: CALL objectField OPEN_PARENTHESES argList? CLOSE_PARENTHESES ;

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
charLiter: CHARACTER;
identifier: IDENT;


//sizeofcall
sizeOfCall: SIZE_OF (expr | type | OPEN_PARENTHESES type CLOSE_PARENTHESES);