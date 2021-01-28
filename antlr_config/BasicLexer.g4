lexer grammar BasicLexer;
// escape characters
fragment EOL: '\n';
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

//base types
BASE_TYPE:
  'int'
  | 'bool'
  | 'char'
  | 'string'
;

//prog descriptors
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

//unary ops
NOT: '!';
LENGTH: 'len';
ORD: 'ord' ;
CHR: 'chr' ;

//operators
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

//assignment
EQUALS: '=' ;
COMMA : ',' ;
SEPERATOR: ';' ;



// whitespace
WS : [ \r\t\n]+ -> skip ;


ARRAY_TYPE: (BASE_TYPE|PAIR_TYPE) ('[]')+ ;


//booleans
BOOLEAN: 'true' | 'false' ;

//character
fragment CHAR: ~[\\'"] | '\\' ESCAPED_CHAR;
STRING: '"' CHAR* '"' ;
CHARACTER: '\'' CHAR* '\'' ;

//pair
CREATE_PAIR: 'newpair' ;
PAIR: 'null' ;
PAIR_FIRST: 'fst' ;
PAIR_SECOND: 'snd' ;
PAIR_TYPE: 'pair';

//numbers
fragment DIGIT: [0-9] ;
INTEGER:  DIGIT+ ;

//comment
COMMENT : '#' ~[\r\n]* EOL -> skip;

//identifier
IDENT: ([_a-z]) ([_a-zA-Z0-9])* ;

