// Generated from C:/Users/Raul/Desktop/wacc_40/src/main/antlr4/antlr\WaccLexer.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class WaccLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, COMMENT=2, COMMA=3, SEPERATOR=4, STRING_TYPE=5, CHAR_TYPE=6, BOOL_TYPE=7, 
		INT_TYPE=8, BASE_TYPE=9, PAIR_TYPE=10, ARRAY_TYPE=11, BEGIN=12, END=13, 
		RETURN=14, CALL=15, EXIT=16, SKIP_STATEMENT=17, IS=18, READ=19, FREE=20, 
		PRINT=21, PRINT_LINE=22, WHILE=23, DO=24, END_WHILE=25, IF=26, THEN=27, 
		ELSE=28, END_IF=29, OPEN_PARENTHESES=30, CLOSE_PARENTHESES=31, OPEN_SQUARE_BRACKET=32, 
		CLOSE_SQUARE_BRACKET=33, PLUS=34, MINUS=35, MULTIPLY=36, DIVIDE=37, MOD=38, 
		GR=39, GRE=40, LS=41, LSE=42, EQ=43, NEQ=44, AND=45, OR=46, NOT=47, LENGTH=48, 
		ORD=49, CHR=50, EQUALS=51, INTEGER=52, TRUE=53, FALSE=54, BOOLEAN=55, 
		NEWPAIR=56, PAIR=57, PAIR_FIRST=58, PAIR_SECOND=59, STRING=60, CHARACTER=61, 
		IDENT=62;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "ESCAPED_CHAR", "DIGIT", "WS", "COMMENT", "COMMA", "SEPERATOR", 
			"STRING_TYPE", "CHAR_TYPE", "BOOL_TYPE", "INT_TYPE", "BASE_TYPE", "PAIR_TYPE", 
			"ARRAY_TYPE", "BEGIN", "END", "RETURN", "CALL", "EXIT", "SKIP_STATEMENT", 
			"IS", "READ", "FREE", "PRINT", "PRINT_LINE", "WHILE", "DO", "END_WHILE", 
			"IF", "THEN", "ELSE", "END_IF", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "PLUS", "MINUS", "MULTIPLY", 
			"DIVIDE", "MOD", "GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", 
			"NOT", "LENGTH", "ORD", "CHR", "EQUALS", "INTEGER", "TRUE", "FALSE", 
			"BOOLEAN", "NEWPAIR", "PAIR", "PAIR_FIRST", "PAIR_SECOND", "CHAR", "STRING", 
			"CHARACTER", "IDENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "','", "';'", "'string'", "'char'", "'bool'", "'int'", 
			null, "'pair'", null, "'begin'", "'end'", "'return'", "'call'", "'exit'", 
			"'skip'", "'is'", "'read'", "'free'", "'print'", "'println'", "'while'", 
			"'do'", "'done'", "'if'", "'then'", "'else'", "'fi'", "'('", "')'", "'['", 
			"']'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", "'<'", "'<='", 
			"'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", "'chr'", "'='", 
			null, "'true'", "'false'", null, "'newpair'", "'null'", "'fst'", "'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "COMMA", "SEPERATOR", "STRING_TYPE", "CHAR_TYPE", 
			"BOOL_TYPE", "INT_TYPE", "BASE_TYPE", "PAIR_TYPE", "ARRAY_TYPE", "BEGIN", 
			"END", "RETURN", "CALL", "EXIT", "SKIP_STATEMENT", "IS", "READ", "FREE", 
			"PRINT", "PRINT_LINE", "WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", 
			"END_IF", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", 
			"GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", "NOT", "LENGTH", 
			"ORD", "CHR", "EQUALS", "INTEGER", "TRUE", "FALSE", "BOOLEAN", "NEWPAIR", 
			"PAIR", "PAIR_FIRST", "PAIR_SECOND", "STRING", "CHARACTER", "IDENT"
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


	public WaccLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "WaccLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2@\u01ac\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\5\2\u0089\n\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\3\3\5\3\u0092\n\3\3\4\3\4\3\5\6\5\u0097\n\5\r\5\16\5\u0098\3"+
		"\5\3\5\3\6\3\6\7\6\u009f\n\6\f\6\16\6\u00a2\13\6\3\6\3\6\5\6\u00a6\n\6"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00c7"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\5\17\u00d0\n\17\3\17\3\17\6\17"+
		"\u00d4\n\17\r\17\16\17\u00d5\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&"+
		"\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64"+
		"\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\38\68\u016a\n8\r8\16"+
		"8\u016b\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3;\3;\5;\u017b\n;\3<\3<\3<\3"+
		"<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3>\3>\3>\3>\3?\3?\3?\3?\3@\3@\3@\5@\u0195"+
		"\n@\3A\3A\7A\u0199\nA\fA\16A\u019c\13A\3A\3A\3B\3B\5B\u01a2\nB\3B\3B\3"+
		"C\3C\7C\u01a8\nC\fC\16C\u01ab\13C\2\2D\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21"+
		"\7\23\b\25\t\27\n\31\13\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/"+
		"\26\61\27\63\30\65\31\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)"+
		"W*Y+[,]-_.a/c\60e\61g\62i\63k\64m\65o\66q\67s8u9w:y;{<}=\177\2\u0081>"+
		"\u0083?\u0085@\3\2\t\t\2$$\62\62ddhhppttvv\3\2\62;\5\2\13\f\17\17\"\""+
		"\4\2\f\f\17\17\5\2$$))^^\4\2aac|\6\2\62;C\\aac|\2\u01b8\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2"+
		"\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q"+
		"\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2"+
		"\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2"+
		"\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w"+
		"\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2"+
		"\2\2\2\u0085\3\2\2\2\3\u0088\3\2\2\2\5\u0091\3\2\2\2\7\u0093\3\2\2\2\t"+
		"\u0096\3\2\2\2\13\u009c\3\2\2\2\r\u00a9\3\2\2\2\17\u00ab\3\2\2\2\21\u00ad"+
		"\3\2\2\2\23\u00b4\3\2\2\2\25\u00b9\3\2\2\2\27\u00be\3\2\2\2\31\u00c6\3"+
		"\2\2\2\33\u00c8\3\2\2\2\35\u00cf\3\2\2\2\37\u00d7\3\2\2\2!\u00dd\3\2\2"+
		"\2#\u00e1\3\2\2\2%\u00e8\3\2\2\2\'\u00ed\3\2\2\2)\u00f2\3\2\2\2+\u00f7"+
		"\3\2\2\2-\u00fa\3\2\2\2/\u00ff\3\2\2\2\61\u0104\3\2\2\2\63\u010a\3\2\2"+
		"\2\65\u0112\3\2\2\2\67\u0118\3\2\2\29\u011b\3\2\2\2;\u0120\3\2\2\2=\u0123"+
		"\3\2\2\2?\u0128\3\2\2\2A\u012d\3\2\2\2C\u0130\3\2\2\2E\u0132\3\2\2\2G"+
		"\u0134\3\2\2\2I\u0136\3\2\2\2K\u0138\3\2\2\2M\u013a\3\2\2\2O\u013c\3\2"+
		"\2\2Q\u013e\3\2\2\2S\u0140\3\2\2\2U\u0142\3\2\2\2W\u0144\3\2\2\2Y\u0147"+
		"\3\2\2\2[\u0149\3\2\2\2]\u014c\3\2\2\2_\u014f\3\2\2\2a\u0152\3\2\2\2c"+
		"\u0155\3\2\2\2e\u0158\3\2\2\2g\u015a\3\2\2\2i\u015e\3\2\2\2k\u0162\3\2"+
		"\2\2m\u0166\3\2\2\2o\u0169\3\2\2\2q\u016d\3\2\2\2s\u0172\3\2\2\2u\u017a"+
		"\3\2\2\2w\u017c\3\2\2\2y\u0184\3\2\2\2{\u0189\3\2\2\2}\u018d\3\2\2\2\177"+
		"\u0194\3\2\2\2\u0081\u0196\3\2\2\2\u0083\u019f\3\2\2\2\u0085\u01a5\3\2"+
		"\2\2\u0087\u0089\7\17\2\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008b\7\f\2\2\u008b\4\3\2\2\2\u008c\u0092\t\2\2\2"+
		"\u008d\u008e\7\u00e4\2\2\u008e\u008f\7\u20ae\2\2\u008f\u0092\7\u2124\2"+
		"\2\u0090\u0092\7^\2\2\u0091\u008c\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u0090"+
		"\3\2\2\2\u0092\6\3\2\2\2\u0093\u0094\t\3\2\2\u0094\b\3\2\2\2\u0095\u0097"+
		"\t\4\2\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\b\5\2\2\u009b\n\3\2\2\2"+
		"\u009c\u00a0\7%\2\2\u009d\u009f\n\5\2\2\u009e\u009d\3\2\2\2\u009f\u00a2"+
		"\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a5\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a6\5\3\2\2\u00a4\u00a6\7\2\2\3\u00a5\u00a3\3\2"+
		"\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\6\2\2\u00a8"+
		"\f\3\2\2\2\u00a9\u00aa\7.\2\2\u00aa\16\3\2\2\2\u00ab\u00ac\7=\2\2\u00ac"+
		"\20\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7t\2\2\u00b0"+
		"\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7i\2\2\u00b3\22\3\2\2\2\u00b4"+
		"\u00b5\7e\2\2\u00b5\u00b6\7j\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7t\2\2"+
		"\u00b8\24\3\2\2\2\u00b9\u00ba\7d\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7"+
		"q\2\2\u00bc\u00bd\7n\2\2\u00bd\26\3\2\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0"+
		"\7p\2\2\u00c0\u00c1\7v\2\2\u00c1\30\3\2\2\2\u00c2\u00c7\5\27\f\2\u00c3"+
		"\u00c7\5\25\13\2\u00c4\u00c7\5\23\n\2\u00c5\u00c7\5\21\t\2\u00c6\u00c2"+
		"\3\2\2\2\u00c6\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7"+
		"\32\3\2\2\2\u00c8\u00c9\7r\2\2\u00c9\u00ca\7c\2\2\u00ca\u00cb\7k\2\2\u00cb"+
		"\u00cc\7t\2\2\u00cc\34\3\2\2\2\u00cd\u00d0\5\31\r\2\u00ce\u00d0\5\33\16"+
		"\2\u00cf\u00cd\3\2\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00d2"+
		"\7]\2\2\u00d2\u00d4\7_\2\2\u00d3\u00d1\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\36\3\2\2\2\u00d7\u00d8\7d\2\2"+
		"\u00d8\u00d9\7g\2\2\u00d9\u00da\7i\2\2\u00da\u00db\7k\2\2\u00db\u00dc"+
		"\7p\2\2\u00dc \3\2\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7p\2\2\u00df\u00e0"+
		"\7f\2\2\u00e0\"\3\2\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4"+
		"\7v\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6\7t\2\2\u00e6\u00e7\7p\2\2\u00e7"+
		"$\3\2\2\2\u00e8\u00e9\7e\2\2\u00e9\u00ea\7c\2\2\u00ea\u00eb\7n\2\2\u00eb"+
		"\u00ec\7n\2\2\u00ec&\3\2\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7z\2\2\u00ef"+
		"\u00f0\7k\2\2\u00f0\u00f1\7v\2\2\u00f1(\3\2\2\2\u00f2\u00f3\7u\2\2\u00f3"+
		"\u00f4\7m\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6\7r\2\2\u00f6*\3\2\2\2\u00f7"+
		"\u00f8\7k\2\2\u00f8\u00f9\7u\2\2\u00f9,\3\2\2\2\u00fa\u00fb\7t\2\2\u00fb"+
		"\u00fc\7g\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7f\2\2\u00fe.\3\2\2\2\u00ff"+
		"\u0100\7h\2\2\u0100\u0101\7t\2\2\u0101\u0102\7g\2\2\u0102\u0103\7g\2\2"+
		"\u0103\60\3\2\2\2\u0104\u0105\7r\2\2\u0105\u0106\7t\2\2\u0106\u0107\7"+
		"k\2\2\u0107\u0108\7p\2\2\u0108\u0109\7v\2\2\u0109\62\3\2\2\2\u010a\u010b"+
		"\7r\2\2\u010b\u010c\7t\2\2\u010c\u010d\7k\2\2\u010d\u010e\7p\2\2\u010e"+
		"\u010f\7v\2\2\u010f\u0110\7n\2\2\u0110\u0111\7p\2\2\u0111\64\3\2\2\2\u0112"+
		"\u0113\7y\2\2\u0113\u0114\7j\2\2\u0114\u0115\7k\2\2\u0115\u0116\7n\2\2"+
		"\u0116\u0117\7g\2\2\u0117\66\3\2\2\2\u0118\u0119\7f\2\2\u0119\u011a\7"+
		"q\2\2\u011a8\3\2\2\2\u011b\u011c\7f\2\2\u011c\u011d\7q\2\2\u011d\u011e"+
		"\7p\2\2\u011e\u011f\7g\2\2\u011f:\3\2\2\2\u0120\u0121\7k\2\2\u0121\u0122"+
		"\7h\2\2\u0122<\3\2\2\2\u0123\u0124\7v\2\2\u0124\u0125\7j\2\2\u0125\u0126"+
		"\7g\2\2\u0126\u0127\7p\2\2\u0127>\3\2\2\2\u0128\u0129\7g\2\2\u0129\u012a"+
		"\7n\2\2\u012a\u012b\7u\2\2\u012b\u012c\7g\2\2\u012c@\3\2\2\2\u012d\u012e"+
		"\7h\2\2\u012e\u012f\7k\2\2\u012fB\3\2\2\2\u0130\u0131\7*\2\2\u0131D\3"+
		"\2\2\2\u0132\u0133\7+\2\2\u0133F\3\2\2\2\u0134\u0135\7]\2\2\u0135H\3\2"+
		"\2\2\u0136\u0137\7_\2\2\u0137J\3\2\2\2\u0138\u0139\7-\2\2\u0139L\3\2\2"+
		"\2\u013a\u013b\7/\2\2\u013bN\3\2\2\2\u013c\u013d\7,\2\2\u013dP\3\2\2\2"+
		"\u013e\u013f\7\61\2\2\u013fR\3\2\2\2\u0140\u0141\7\'\2\2\u0141T\3\2\2"+
		"\2\u0142\u0143\7@\2\2\u0143V\3\2\2\2\u0144\u0145\7@\2\2\u0145\u0146\7"+
		"?\2\2\u0146X\3\2\2\2\u0147\u0148\7>\2\2\u0148Z\3\2\2\2\u0149\u014a\7>"+
		"\2\2\u014a\u014b\7?\2\2\u014b\\\3\2\2\2\u014c\u014d\7?\2\2\u014d\u014e"+
		"\7?\2\2\u014e^\3\2\2\2\u014f\u0150\7#\2\2\u0150\u0151\7?\2\2\u0151`\3"+
		"\2\2\2\u0152\u0153\7(\2\2\u0153\u0154\7(\2\2\u0154b\3\2\2\2\u0155\u0156"+
		"\7~\2\2\u0156\u0157\7~\2\2\u0157d\3\2\2\2\u0158\u0159\7#\2\2\u0159f\3"+
		"\2\2\2\u015a\u015b\7n\2\2\u015b\u015c\7g\2\2\u015c\u015d\7p\2\2\u015d"+
		"h\3\2\2\2\u015e\u015f\7q\2\2\u015f\u0160\7t\2\2\u0160\u0161\7f\2\2\u0161"+
		"j\3\2\2\2\u0162\u0163\7e\2\2\u0163\u0164\7j\2\2\u0164\u0165\7t\2\2\u0165"+
		"l\3\2\2\2\u0166\u0167\7?\2\2\u0167n\3\2\2\2\u0168\u016a\5\7\4\2\u0169"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2"+
		"\2\2\u016cp\3\2\2\2\u016d\u016e\7v\2\2\u016e\u016f\7t\2\2\u016f\u0170"+
		"\7w\2\2\u0170\u0171\7g\2\2\u0171r\3\2\2\2\u0172\u0173\7h\2\2\u0173\u0174"+
		"\7c\2\2\u0174\u0175\7n\2\2\u0175\u0176\7u\2\2\u0176\u0177\7g\2\2\u0177"+
		"t\3\2\2\2\u0178\u017b\5q9\2\u0179\u017b\5s:\2\u017a\u0178\3\2\2\2\u017a"+
		"\u0179\3\2\2\2\u017bv\3\2\2\2\u017c\u017d\7p\2\2\u017d\u017e\7g\2\2\u017e"+
		"\u017f\7y\2\2\u017f\u0180\7r\2\2\u0180\u0181\7c\2\2\u0181\u0182\7k\2\2"+
		"\u0182\u0183\7t\2\2\u0183x\3\2\2\2\u0184\u0185\7p\2\2\u0185\u0186\7w\2"+
		"\2\u0186\u0187\7n\2\2\u0187\u0188\7n\2\2\u0188z\3\2\2\2\u0189\u018a\7"+
		"h\2\2\u018a\u018b\7u\2\2\u018b\u018c\7v\2\2\u018c|\3\2\2\2\u018d\u018e"+
		"\7u\2\2\u018e\u018f\7p\2\2\u018f\u0190\7f\2\2\u0190~\3\2\2\2\u0191\u0195"+
		"\n\6\2\2\u0192\u0193\7^\2\2\u0193\u0195\5\5\3\2\u0194\u0191\3\2\2\2\u0194"+
		"\u0192\3\2\2\2\u0195\u0080\3\2\2\2\u0196\u019a\7$\2\2\u0197\u0199\5\177"+
		"@\2\u0198\u0197\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u019a\3\2\2\2\u019d\u019e\7$"+
		"\2\2\u019e\u0082\3\2\2\2\u019f\u01a1\7)\2\2\u01a0\u01a2\5\177@\2\u01a1"+
		"\u01a0\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4\7)"+
		"\2\2\u01a4\u0084\3\2\2\2\u01a5\u01a9\t\7\2\2\u01a6\u01a8\t\b\2\2\u01a7"+
		"\u01a6\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2"+
		"\2\2\u01aa\u0086\3\2\2\2\u01ab\u01a9\3\2\2\2\21\2\u0088\u0091\u0098\u00a0"+
		"\u00a5\u00c6\u00cf\u00d5\u016b\u017a\u0194\u019a\u01a1\u01a9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}