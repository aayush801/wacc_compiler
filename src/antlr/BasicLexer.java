// Generated from ./BasicLexer.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, PLUS=2, MINUS=3, MULTIPLY=4, DIVIDE=5, MOD=6, GR=7, GRE=8, LS=9, 
		LSE=10, EQ=11, NEQ=12, AND=13, OR=14, EQUALS=15, NOT=16, LENGTH=17, ORD=18, 
		CHR=19, OPEN_PARENTHESES=20, CLOSE_PARENTHESES=21, OPEN_SQUARE_BRACKET=22, 
		CLOSE_SQUARE_BRACKET=23, BEGIN=24, END=25, IS=26, SKIP_STATEMENT=27, READ=28, 
		FREE=29, RETURN=30, EXIT=31, PRINT=32, PRINT_LINE=33, SEPERATOR=34, CALL=35, 
		TYPE=36, PAIR_TYPE=37, BASE_TYPE=38, ARRAY_TYPE=39, WHILE=40, DO=41, END_WHILE=42, 
		IF=43, THEN=44, ELSE=45, END_IF=46, INTEGER=47, BOOLEAN=48, STRING=49, 
		CHARACTER=50, CREATE_PAIR=51, PAIR=52, PAIR_FIRST=53, PAIR_SECOND=54, 
		IDENT=55, COMMENT=56, WS=57;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "ESCAPED_CHAR", "COMMA", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
			"MOD", "GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", "EQUALS", 
			"NOT", "LENGTH", "ORD", "CHR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "BEGIN", "END", "IS", 
			"SKIP_STATEMENT", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINT_LINE", 
			"SEPERATOR", "CALL", "TYPE", "PAIR_TYPE", "BASE_TYPE", "ARRAY_TYPE", 
			"WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", "END_IF", "DIGIT", 
			"INTSIGN", "INTEGER", "BOOLEAN", "CHAR", "STRING", "CHARACTER", "CREATE_PAIR", 
			"PAIR", "PAIR_FIRST", "PAIR_SECOND", "IDENT", "COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", "'<'", 
			"'<='", "'=='", "'!='", "'&&'", "'||'", "'='", "'!'", "'len'", "'ord'", 
			"'chr'", "'('", "')'", "'['", "']'", "'begin'", "'end'", "'is'", "'skip'", 
			"'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", "';'", 
			"'call'", null, "'pair'", null, null, "'while'", "'do'", "'done'", "'if'", 
			"'then'", "'else'", "'fi'", null, null, null, null, "'newpair'", "'null'", 
			"'fst'", "'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", "GR", "GRE", 
			"LS", "LSE", "EQ", "NEQ", "AND", "OR", "EQUALS", "NOT", "LENGTH", "ORD", 
			"CHR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "BEGIN", "END", "IS", "SKIP_STATEMENT", "READ", 
			"FREE", "RETURN", "EXIT", "PRINT", "PRINT_LINE", "SEPERATOR", "CALL", 
			"TYPE", "PAIR_TYPE", "BASE_TYPE", "ARRAY_TYPE", "WHILE", "DO", "END_WHILE", 
			"IF", "THEN", "ELSE", "END_IF", "INTEGER", "BOOLEAN", "STRING", "CHARACTER", 
			"CREATE_PAIR", "PAIR", "PAIR_FIRST", "PAIR_SECOND", "IDENT", "COMMENT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2;\u019b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\5\'\u00fe\n"+
		"\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5"+
		")\u0116\n)\3*\3*\5*\u011a\n*\3*\3*\6*\u011e\n*\r*\16*\u011f\3+\3+\3+\3"+
		"+\3+\3+\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\63\3\63\5\63\u0144\n\63\3\64\5\64"+
		"\u0147\n\64\3\64\6\64\u014a\n\64\r\64\16\64\u014b\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\5\65\u0157\n\65\3\66\3\66\3\66\5\66\u015c\n"+
		"\66\3\67\3\67\7\67\u0160\n\67\f\67\16\67\u0163\13\67\3\67\3\67\38\38\7"+
		"8\u0169\n8\f8\168\u016c\138\38\38\39\39\39\39\39\39\39\39\3:\3:\3:\3:"+
		"\3:\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\7=\u0187\n=\f=\16=\u018a\13=\3>\3>\7"+
		">\u018e\n>\f>\16>\u0191\13>\3>\3>\3?\6?\u0196\n?\r?\16?\u0197\3?\3?\2"+
		"\2@\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16"+
		"\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35"+
		"=\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\2e\2g\61i\62k\2m\63o\64"+
		"q\65s\66u\67w8y9{:};\3\2\t\13\2$$\62\62^^ddhhppttvv\u201b\u201b\3\2\62"+
		";\5\2$$))^^\4\2aac|\6\2\62;C\\aac|\3\2\f\f\5\2\13\13\"\"~~\2\u01a6\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y"+
		"\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\3\177\3\2\2\2\5\u0081\3\2\2\2\7\u0083\3"+
		"\2\2\2\t\u0085\3\2\2\2\13\u0087\3\2\2\2\r\u0089\3\2\2\2\17\u008b\3\2\2"+
		"\2\21\u008d\3\2\2\2\23\u008f\3\2\2\2\25\u0091\3\2\2\2\27\u0094\3\2\2\2"+
		"\31\u0096\3\2\2\2\33\u0099\3\2\2\2\35\u009c\3\2\2\2\37\u009f\3\2\2\2!"+
		"\u00a2\3\2\2\2#\u00a5\3\2\2\2%\u00a7\3\2\2\2\'\u00a9\3\2\2\2)\u00ad\3"+
		"\2\2\2+\u00b1\3\2\2\2-\u00b5\3\2\2\2/\u00b7\3\2\2\2\61\u00b9\3\2\2\2\63"+
		"\u00bb\3\2\2\2\65\u00bd\3\2\2\2\67\u00c3\3\2\2\29\u00c7\3\2\2\2;\u00ca"+
		"\3\2\2\2=\u00cf\3\2\2\2?\u00d4\3\2\2\2A\u00d9\3\2\2\2C\u00e0\3\2\2\2E"+
		"\u00e5\3\2\2\2G\u00eb\3\2\2\2I\u00f3\3\2\2\2K\u00f5\3\2\2\2M\u00fd\3\2"+
		"\2\2O\u00ff\3\2\2\2Q\u0115\3\2\2\2S\u0119\3\2\2\2U\u0121\3\2\2\2W\u0127"+
		"\3\2\2\2Y\u012a\3\2\2\2[\u012f\3\2\2\2]\u0132\3\2\2\2_\u0137\3\2\2\2a"+
		"\u013c\3\2\2\2c\u013f\3\2\2\2e\u0143\3\2\2\2g\u0146\3\2\2\2i\u0156\3\2"+
		"\2\2k\u015b\3\2\2\2m\u015d\3\2\2\2o\u0166\3\2\2\2q\u016f\3\2\2\2s\u0177"+
		"\3\2\2\2u\u017c\3\2\2\2w\u0180\3\2\2\2y\u0184\3\2\2\2{\u018b\3\2\2\2}"+
		"\u0195\3\2\2\2\177\u0080\7\f\2\2\u0080\4\3\2\2\2\u0081\u0082\t\2\2\2\u0082"+
		"\6\3\2\2\2\u0083\u0084\7.\2\2\u0084\b\3\2\2\2\u0085\u0086\7-\2\2\u0086"+
		"\n\3\2\2\2\u0087\u0088\7/\2\2\u0088\f\3\2\2\2\u0089\u008a\7,\2\2\u008a"+
		"\16\3\2\2\2\u008b\u008c\7\61\2\2\u008c\20\3\2\2\2\u008d\u008e\7\'\2\2"+
		"\u008e\22\3\2\2\2\u008f\u0090\7@\2\2\u0090\24\3\2\2\2\u0091\u0092\7@\2"+
		"\2\u0092\u0093\7?\2\2\u0093\26\3\2\2\2\u0094\u0095\7>\2\2\u0095\30\3\2"+
		"\2\2\u0096\u0097\7>\2\2\u0097\u0098\7?\2\2\u0098\32\3\2\2\2\u0099\u009a"+
		"\7?\2\2\u009a\u009b\7?\2\2\u009b\34\3\2\2\2\u009c\u009d\7#\2\2\u009d\u009e"+
		"\7?\2\2\u009e\36\3\2\2\2\u009f\u00a0\7(\2\2\u00a0\u00a1\7(\2\2\u00a1 "+
		"\3\2\2\2\u00a2\u00a3\7~\2\2\u00a3\u00a4\7~\2\2\u00a4\"\3\2\2\2\u00a5\u00a6"+
		"\7?\2\2\u00a6$\3\2\2\2\u00a7\u00a8\7#\2\2\u00a8&\3\2\2\2\u00a9\u00aa\7"+
		"n\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7p\2\2\u00ac(\3\2\2\2\u00ad\u00ae"+
		"\7q\2\2\u00ae\u00af\7t\2\2\u00af\u00b0\7f\2\2\u00b0*\3\2\2\2\u00b1\u00b2"+
		"\7e\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4\7t\2\2\u00b4,\3\2\2\2\u00b5\u00b6"+
		"\7*\2\2\u00b6.\3\2\2\2\u00b7\u00b8\7+\2\2\u00b8\60\3\2\2\2\u00b9\u00ba"+
		"\7]\2\2\u00ba\62\3\2\2\2\u00bb\u00bc\7_\2\2\u00bc\64\3\2\2\2\u00bd\u00be"+
		"\7d\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7i\2\2\u00c0\u00c1\7k\2\2\u00c1"+
		"\u00c2\7p\2\2\u00c2\66\3\2\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7p\2\2\u00c5"+
		"\u00c6\7f\2\2\u00c68\3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7u\2\2\u00c9"+
		":\3\2\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7m\2\2\u00cc\u00cd\7k\2\2\u00cd"+
		"\u00ce\7r\2\2\u00ce<\3\2\2\2\u00cf\u00d0\7t\2\2\u00d0\u00d1\7g\2\2\u00d1"+
		"\u00d2\7c\2\2\u00d2\u00d3\7f\2\2\u00d3>\3\2\2\2\u00d4\u00d5\7h\2\2\u00d5"+
		"\u00d6\7t\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7g\2\2\u00d8@\3\2\2\2\u00d9"+
		"\u00da\7t\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd\7w\2\2"+
		"\u00dd\u00de\7t\2\2\u00de\u00df\7p\2\2\u00dfB\3\2\2\2\u00e0\u00e1\7g\2"+
		"\2\u00e1\u00e2\7z\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7v\2\2\u00e4D\3\2"+
		"\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9"+
		"\7p\2\2\u00e9\u00ea\7v\2\2\u00eaF\3\2\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed"+
		"\7t\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0"+
		"\u00f1\7n\2\2\u00f1\u00f2\7p\2\2\u00f2H\3\2\2\2\u00f3\u00f4\7=\2\2\u00f4"+
		"J\3\2\2\2\u00f5\u00f6\7e\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8"+
		"\u00f9\7n\2\2\u00f9L\3\2\2\2\u00fa\u00fe\5Q)\2\u00fb\u00fe\5S*\2\u00fc"+
		"\u00fe\5O(\2\u00fd\u00fa\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2"+
		"\2\u00feN\3\2\2\2\u00ff\u0100\7r\2\2\u0100\u0101\7c\2\2\u0101\u0102\7"+
		"k\2\2\u0102\u0103\7t\2\2\u0103P\3\2\2\2\u0104\u0105\7k\2\2\u0105\u0106"+
		"\7p\2\2\u0106\u0116\7v\2\2\u0107\u0108\7d\2\2\u0108\u0109\7q\2\2\u0109"+
		"\u010a\7q\2\2\u010a\u0116\7n\2\2\u010b\u010c\7e\2\2\u010c\u010d\7j\2\2"+
		"\u010d\u010e\7c\2\2\u010e\u0116\7t\2\2\u010f\u0110\7u\2\2\u0110\u0111"+
		"\7v\2\2\u0111\u0112\7t\2\2\u0112\u0113\7k\2\2\u0113\u0114\7p\2\2\u0114"+
		"\u0116\7i\2\2\u0115\u0104\3\2\2\2\u0115\u0107\3\2\2\2\u0115\u010b\3\2"+
		"\2\2\u0115\u010f\3\2\2\2\u0116R\3\2\2\2\u0117\u011a\5Q)\2\u0118\u011a"+
		"\5O(\2\u0119\u0117\3\2\2\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u011c\7]\2\2\u011c\u011e\7_\2\2\u011d\u011b\3\2\2\2\u011e\u011f\3\2\2"+
		"\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120T\3\2\2\2\u0121\u0122"+
		"\7y\2\2\u0122\u0123\7j\2\2\u0123\u0124\7k\2\2\u0124\u0125\7n\2\2\u0125"+
		"\u0126\7g\2\2\u0126V\3\2\2\2\u0127\u0128\7f\2\2\u0128\u0129\7q\2\2\u0129"+
		"X\3\2\2\2\u012a\u012b\7f\2\2\u012b\u012c\7q\2\2\u012c\u012d\7p\2\2\u012d"+
		"\u012e\7g\2\2\u012eZ\3\2\2\2\u012f\u0130\7k\2\2\u0130\u0131\7h\2\2\u0131"+
		"\\\3\2\2\2\u0132\u0133\7v\2\2\u0133\u0134\7j\2\2\u0134\u0135\7g\2\2\u0135"+
		"\u0136\7p\2\2\u0136^\3\2\2\2\u0137\u0138\7g\2\2\u0138\u0139\7n\2\2\u0139"+
		"\u013a\7u\2\2\u013a\u013b\7g\2\2\u013b`\3\2\2\2\u013c\u013d\7h\2\2\u013d"+
		"\u013e\7k\2\2\u013eb\3\2\2\2\u013f\u0140\t\3\2\2\u0140d\3\2\2\2\u0141"+
		"\u0144\5\t\5\2\u0142\u0144\5\13\6\2\u0143\u0141\3\2\2\2\u0143\u0142\3"+
		"\2\2\2\u0144f\3\2\2\2\u0145\u0147\5e\63\2\u0146\u0145\3\2\2\2\u0146\u0147"+
		"\3\2\2\2\u0147\u0149\3\2\2\2\u0148\u014a\5c\62\2\u0149\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014ch\3\2\2\2"+
		"\u014d\u014e\7v\2\2\u014e\u014f\7t\2\2\u014f\u0150\7w\2\2\u0150\u0157"+
		"\7g\2\2\u0151\u0152\7h\2\2\u0152\u0153\7c\2\2\u0153\u0154\7n\2\2\u0154"+
		"\u0155\7u\2\2\u0155\u0157\7g\2\2\u0156\u014d\3\2\2\2\u0156\u0151\3\2\2"+
		"\2\u0157j\3\2\2\2\u0158\u015c\n\4\2\2\u0159\u015a\7^\2\2\u015a\u015c\5"+
		"\5\3\2\u015b\u0158\3\2\2\2\u015b\u0159\3\2\2\2\u015cl\3\2\2\2\u015d\u0161"+
		"\7$\2\2\u015e\u0160\5k\66\2\u015f\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u0161\3\2"+
		"\2\2\u0164\u0165\7$\2\2\u0165n\3\2\2\2\u0166\u016a\7)\2\2\u0167\u0169"+
		"\5k\66\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a"+
		"\u016b\3\2\2\2\u016b\u016d\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016e\7)"+
		"\2\2\u016ep\3\2\2\2\u016f\u0170\7p\2\2\u0170\u0171\7g\2\2\u0171\u0172"+
		"\7y\2\2\u0172\u0173\7r\2\2\u0173\u0174\7c\2\2\u0174\u0175\7k\2\2\u0175"+
		"\u0176\7t\2\2\u0176r\3\2\2\2\u0177\u0178\7p\2\2\u0178\u0179\7w\2\2\u0179"+
		"\u017a\7n\2\2\u017a\u017b\7n\2\2\u017bt\3\2\2\2\u017c\u017d\7h\2\2\u017d"+
		"\u017e\7u\2\2\u017e\u017f\7v\2\2\u017fv\3\2\2\2\u0180\u0181\7u\2\2\u0181"+
		"\u0182\7p\2\2\u0182\u0183\7f\2\2\u0183x\3\2\2\2\u0184\u0188\t\5\2\2\u0185"+
		"\u0187\t\6\2\2\u0186\u0185\3\2\2\2\u0187\u018a\3\2\2\2\u0188\u0186\3\2"+
		"\2\2\u0188\u0189\3\2\2\2\u0189z\3\2\2\2\u018a\u0188\3\2\2\2\u018b\u018f"+
		"\7%\2\2\u018c\u018e\n\7\2\2\u018d\u018c\3\2\2\2\u018e\u0191\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0192\3\2\2\2\u0191\u018f\3\2"+
		"\2\2\u0192\u0193\5\3\2\2\u0193|\3\2\2\2\u0194\u0196\t\b\2\2\u0195\u0194"+
		"\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u0199\3\2\2\2\u0199\u019a\b?\2\2\u019a~\3\2\2\2\21\2\u00fd\u0115\u0119"+
		"\u011f\u0143\u0146\u014b\u0156\u015b\u0161\u016a\u0188\u018f\u0197\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}