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
  | '\''
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

DOT : '.' ;


// types
STRING_TYPE: 'string';
CHAR_TYPE: 'char';
BOOL_TYPE: 'bool' ;
INT_TYPE: 'int' ;
PAIR_TYPE: 'pair' ;
FLOAT_TYPE: 'float' ;

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
DONE : 'done' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
END_IF: 'fi' ;

//extension
//for loop
FOR : 'for' ;
END_FOR: 'rof';

//extension
//loop breaks
CONTINUE : 'continue' ;
BREAK : 'break' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_SQUARE_BRACKET: '[' ;
CLOSE_SQUARE_BRACKET: ']' ;

//binary operators
PLUS: '+' ;
MINUS: '-' ;
STAR: '*' ;
DIVIDE: '/' ;
MOD: '%' ;
GT: '>' ;
GTE: '>=' ;
LT: '<' ;
LTE: '<=' ;
EQ: '==' ;
NEQ: '!=' ;
AND: '&&' ;
OR: '||' ;

//extension
BITWISE_AND : '&' ;
BITWISE_OR : '|' ;
INVERT : '~';

//unary operators
NOT: '!';
LENGTH: 'len';
ORD: 'ord' ;
CHR: 'chr' ;

//assignment operator
EQUALS: '=' ;

//integers
INTEGER: DIGIT+ ;
FLOAT: DIGIT+ DOT DIGIT+ ;

//boolean values
TRUE: 'true';
FALSE: 'false';

//pairs
NEWPAIR: 'newpair' ;
PAIR_FIRST: 'fst' ;
PAIR_SECOND: 'snd' ;


//extension
//class
CLASS : 'class' ;
EXTENDS : 'extends' ;
PUBLIC : 'public' ;
PRIVATE : 'private' ;
VISIBILITY : PUBLIC | PRIVATE ;

NULL: 'null' ;

//character/string set
fragment CHAR:  ~[\\'"] | ('\\' ESCAPED_CHAR);
STRING: '"' CHAR* '"' ;
CHARACTER: '\'' CHAR? '\'' ;

//identifier
IDENT: [_a-zA-Z][_a-zA-Z0-9]* ;

