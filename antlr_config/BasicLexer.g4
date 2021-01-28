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

COMMA : ',' ;

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

NOT: '!';
LENGTH: 'len';
ORD: 'ord' ;
CHR: 'chr' ;

//brackets
OPEN_PARENTHESES: '(' ;
CLOSE_PARENTHESES: ')' ;
OPEN_SQUARE_BRACKET: '[' ;
CLOSE_SQUARE_BRACKET: ']' ;

//prog descriptors
BEGIN: 'begin';
END: 'end' ;

//stat descriptors
IS: 'is';
SKIP_STATEMENT : 'skip' ;
READ: 'read' ;
FREE: 'free' ;
RETURN: 'return' ;
EXIT: 'exit' ;
PRINT: 'print' ;
PRINT_LINE: 'println' ;
SEPERATOR: ';' ;
CALL: 'call';


//typings
TYPE:
  BASE_TYPE
  | ARRAY_TYPE
  | PAIR_TYPE
;


PAIR_TYPE: 'pair';

//base types
BASE_TYPE:
  'int'
  | 'bool'
  | 'char'
  | 'string'
;

ARRAY_TYPE: (BASE_TYPE|PAIR_TYPE) ('[]')+ ;

//conditional keywords
WHILE: 'while' ;
DO: 'do' ;
END_WHILE: 'done' ;
IF: 'if' ;
THEN: 'then' ;
ELSE: 'else' ;
END_IF: 'fi' ;



//numbers
fragment DIGIT: [0-9] ;
fragment INTSIGN: PLUS | MINUS ;

INTEGER: INTSIGN? DIGIT+ ;

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

//identifier
IDENT: ([_a-z]) ([_a-zA-Z0-9])* ;

//comment
COMMENT: '#' (~[\n])* EOL ;

WS: [ \t]+ -> skip ;