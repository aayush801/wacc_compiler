lexer grammar WaccLexer;

// end of line
fragment EOL: '\r'? '\n';

// escape characters
fragment ESCAPED_CHAR:
  '0'
  | 'b'
  | 't'
  | 'n'
  | 'f'
  | 'r'
  | '"'
  | '’'
  | '\\'
;

// digits
fragment DIGIT: [0-9] ;

// whitespace
WS : [ \r\t\n]+ -> skip ;
//comment
COMMENT : '#' (~[\r\n])* (EOL | EOF) -> skip;
// comma
COMMA : ',' ;
// statement seperator
SEPERATOR: ';' ;

//base types
STRING_TYPE: 'string';
CHAR_TYPE: 'char';
BOOL_TYPE: 'bool' ;
INT_TYPE: 'int' ;

BASE_TYPE:
  INT_TYPE
  | BOOL_TYPE
  | CHAR_TYPE
  | STRING_TYPE
;

// other types
PAIR_TYPE: 'pair' ;
ARRAY_TYPE: (BASE_TYPE | PAIR_TYPE) ('[]')+ ;


//prog keywords
BEGIN: 'begin';
END: 'end' ;
RETURN: 'return' ;
CALL: 'call';
EXIT: 'exit' ;

//stat descriptors
SKIP_STATEMENT : 'skip' ;
IS: 'is';
READ: 'read' ;
FREE: 'free' ;
PRINT: 'print' ;
PRINT_LINE: 'println' ;

//conditional keywords
WHILE: 'while' ;
DO: 'do' ;
END_WHILE: 'done' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
END_IF: 'fi' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_SQUARE_BRACKET: '[' ;
CLOSE_SQUARE_BRACKET: ']' ;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
MULTIPLY: '*' ;
DIVIDE: '/' ;
MOD: '%' ;
GR: '>' ;
GRE: '>=' ;
LS: '<' ;
LSE: '<=' ;
EQ: '==' ;
NEQ: '!=' ;
AND: '&&' ;
OR: '||' ;

//unary operators
NOT: '!';
LENGTH: 'len';
ORD: 'ord' ;
CHR: 'chr' ;

//assignment operator
EQUALS: '=' ;

//integers
INTEGER: DIGIT+ ;

//boolean values
TRUE: 'true' ;
FALSE: 'false';
BOOLEAN: TRUE | FALSE ;

//pairs
NEWPAIR: 'newpair' ;
PAIR: 'null' ;
PAIR_FIRST: 'fst' ;
PAIR_SECOND: 'snd' ;

//character/string set
fragment CHAR: ~[\\'"] | ('\\' ESCAPED_CHAR);
STRING: '"' CHAR* '"' ;
CHARACTER: '\'' CHAR? '\'' ;

//identifier
IDENT: [_a-z][_a-zA-Z0-9]* ;
