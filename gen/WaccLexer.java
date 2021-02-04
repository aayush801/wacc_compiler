// Generated from /home/raul/Desktop/wacc_40/src/main/antlr4/antlr/WaccLexer.g4 by ANTLR 4.9.1
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
		GT=39, GTE=40, LT=41, LTE=42, EQ=43, NEQ=44, AND=45, OR=46, NOT=47, LENGTH=48, 
		ORD=49, CHR=50, EQUALS=51, INTEGER=52, BOOLEAN=53, TRUE=54, FALSE=55, 
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
			"DIVIDE", "MOD", "GT", "GTE", "LT", "LTE", "EQ", "NEQ", "AND", "OR", 
			"NOT", "LENGTH", "ORD", "CHR", "EQUALS", "INTEGER", "BOOLEAN", "TRUE", 
			"FALSE", "NEWPAIR", "PAIR", "PAIR_FIRST", "PAIR_SECOND", "CHAR", "STRING", 
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
			null, null, "'true'", "'false'", "'newpair'", "'null'", "'fst'", "'snd'"
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
			"GT", "GTE", "LT", "LTE", "EQ", "NEQ", "AND", "OR", "NOT", "LENGTH", 
			"ORD", "CHR", "EQUALS", "INTEGER", "BOOLEAN", "TRUE", "FALSE", "NEWPAIR", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2@\u01a7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\5\2\u0089\n\2\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\6\5\u0092\n\5\r\5\16\5\u0093\3\5\3\5\3\6\3\6\7\6\u009a\n"+
		"\6\f\6\16\6\u009d\13\6\3\6\3\6\5\6\u00a1\n\6\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00c2\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\5\17\u00cb\n\17\3\17\3\17\6\17\u00cf\n\17\r\17\16\17\u00d0"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3"+
		"+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3"+
		"\66\3\66\3\66\3\67\3\67\38\68\u0165\n8\r8\168\u0166\39\39\59\u016b\n9"+
		"\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<\3=\3=\3=\3="+
		"\3=\3>\3>\3>\3>\3?\3?\3?\3?\3@\3@\3@\5@\u0190\n@\3A\3A\7A\u0194\nA\fA"+
		"\16A\u0197\13A\3A\3A\3B\3B\5B\u019d\nB\3B\3B\3C\3C\7C\u01a3\nC\fC\16C"+
		"\u01a6\13C\2\2D\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21\7\23\b\25\t\27\n\31\13"+
		"\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67"+
		"\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62"+
		"i\63k\64m\65o\66q\67s8u9w:y;{<}=\177\2\u0081>\u0083?\u0085@\3\2\t\13\2"+
		"$$\62\62^^ddhhppttvv\u201b\u201b\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\5\2$$))^^\4\2aac|\6\2\62;C\\aac|\2\u01b1\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2"+
		"\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2"+
		"\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y"+
		"\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085"+
		"\3\2\2\2\3\u0088\3\2\2\2\5\u008c\3\2\2\2\7\u008e\3\2\2\2\t\u0091\3\2\2"+
		"\2\13\u0097\3\2\2\2\r\u00a4\3\2\2\2\17\u00a6\3\2\2\2\21\u00a8\3\2\2\2"+
		"\23\u00af\3\2\2\2\25\u00b4\3\2\2\2\27\u00b9\3\2\2\2\31\u00c1\3\2\2\2\33"+
		"\u00c3\3\2\2\2\35\u00ca\3\2\2\2\37\u00d2\3\2\2\2!\u00d8\3\2\2\2#\u00dc"+
		"\3\2\2\2%\u00e3\3\2\2\2\'\u00e8\3\2\2\2)\u00ed\3\2\2\2+\u00f2\3\2\2\2"+
		"-\u00f5\3\2\2\2/\u00fa\3\2\2\2\61\u00ff\3\2\2\2\63\u0105\3\2\2\2\65\u010d"+
		"\3\2\2\2\67\u0113\3\2\2\29\u0116\3\2\2\2;\u011b\3\2\2\2=\u011e\3\2\2\2"+
		"?\u0123\3\2\2\2A\u0128\3\2\2\2C\u012b\3\2\2\2E\u012d\3\2\2\2G\u012f\3"+
		"\2\2\2I\u0131\3\2\2\2K\u0133\3\2\2\2M\u0135\3\2\2\2O\u0137\3\2\2\2Q\u0139"+
		"\3\2\2\2S\u013b\3\2\2\2U\u013d\3\2\2\2W\u013f\3\2\2\2Y\u0142\3\2\2\2["+
		"\u0144\3\2\2\2]\u0147\3\2\2\2_\u014a\3\2\2\2a\u014d\3\2\2\2c\u0150\3\2"+
		"\2\2e\u0153\3\2\2\2g\u0155\3\2\2\2i\u0159\3\2\2\2k\u015d\3\2\2\2m\u0161"+
		"\3\2\2\2o\u0164\3\2\2\2q\u016a\3\2\2\2s\u016c\3\2\2\2u\u0171\3\2\2\2w"+
		"\u0177\3\2\2\2y\u017f\3\2\2\2{\u0184\3\2\2\2}\u0188\3\2\2\2\177\u018f"+
		"\3\2\2\2\u0081\u0191\3\2\2\2\u0083\u019a\3\2\2\2\u0085\u01a0\3\2\2\2\u0087"+
		"\u0089\7\17\2\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3"+
		"\2\2\2\u008a\u008b\7\f\2\2\u008b\4\3\2\2\2\u008c\u008d\t\2\2\2\u008d\6"+
		"\3\2\2\2\u008e\u008f\t\3\2\2\u008f\b\3\2\2\2\u0090\u0092\t\4\2\2\u0091"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\5\2\2\u0096\n\3\2\2\2\u0097\u009b"+
		"\7%\2\2\u0098\u009a\n\5\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u00a0\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u00a1\5\3\2\2\u009f\u00a1\7\2\2\3\u00a0\u009e\3\2\2\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\6\2\2\u00a3\f\3\2\2\2"+
		"\u00a4\u00a5\7.\2\2\u00a5\16\3\2\2\2\u00a6\u00a7\7=\2\2\u00a7\20\3\2\2"+
		"\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac"+
		"\7k\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7i\2\2\u00ae\22\3\2\2\2\u00af\u00b0"+
		"\7e\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7t\2\2\u00b3"+
		"\24\3\2\2\2\u00b4\u00b5\7d\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7q\2\2\u00b7"+
		"\u00b8\7n\2\2\u00b8\26\3\2\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7p\2\2\u00bb"+
		"\u00bc\7v\2\2\u00bc\30\3\2\2\2\u00bd\u00c2\5\27\f\2\u00be\u00c2\5\25\13"+
		"\2\u00bf\u00c2\5\23\n\2\u00c0\u00c2\5\21\t\2\u00c1\u00bd\3\2\2\2\u00c1"+
		"\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\32\3\2\2"+
		"\2\u00c3\u00c4\7r\2\2\u00c4\u00c5\7c\2\2\u00c5\u00c6\7k\2\2\u00c6\u00c7"+
		"\7t\2\2\u00c7\34\3\2\2\2\u00c8\u00cb\5\31\r\2\u00c9\u00cb\5\33\16\2\u00ca"+
		"\u00c8\3\2\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00cd\7]"+
		"\2\2\u00cd\u00cf\7_\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\36\3\2\2\2\u00d2\u00d3\7d\2\2"+
		"\u00d3\u00d4\7g\2\2\u00d4\u00d5\7i\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7"+
		"\7p\2\2\u00d7 \3\2\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7p\2\2\u00da\u00db"+
		"\7f\2\2\u00db\"\3\2\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df"+
		"\7v\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"$\3\2\2\2\u00e3\u00e4\7e\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6"+
		"\u00e7\7n\2\2\u00e7&\3\2\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7z\2\2\u00ea"+
		"\u00eb\7k\2\2\u00eb\u00ec\7v\2\2\u00ec(\3\2\2\2\u00ed\u00ee\7u\2\2\u00ee"+
		"\u00ef\7m\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7r\2\2\u00f1*\3\2\2\2\u00f2"+
		"\u00f3\7k\2\2\u00f3\u00f4\7u\2\2\u00f4,\3\2\2\2\u00f5\u00f6\7t\2\2\u00f6"+
		"\u00f7\7g\2\2\u00f7\u00f8\7c\2\2\u00f8\u00f9\7f\2\2\u00f9.\3\2\2\2\u00fa"+
		"\u00fb\7h\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\7g\2\2\u00fd\u00fe\7g\2\2"+
		"\u00fe\60\3\2\2\2\u00ff\u0100\7r\2\2\u0100\u0101\7t\2\2\u0101\u0102\7"+
		"k\2\2\u0102\u0103\7p\2\2\u0103\u0104\7v\2\2\u0104\62\3\2\2\2\u0105\u0106"+
		"\7r\2\2\u0106\u0107\7t\2\2\u0107\u0108\7k\2\2\u0108\u0109\7p\2\2\u0109"+
		"\u010a\7v\2\2\u010a\u010b\7n\2\2\u010b\u010c\7p\2\2\u010c\64\3\2\2\2\u010d"+
		"\u010e\7y\2\2\u010e\u010f\7j\2\2\u010f\u0110\7k\2\2\u0110\u0111\7n\2\2"+
		"\u0111\u0112\7g\2\2\u0112\66\3\2\2\2\u0113\u0114\7f\2\2\u0114\u0115\7"+
		"q\2\2\u01158\3\2\2\2\u0116\u0117\7f\2\2\u0117\u0118\7q\2\2\u0118\u0119"+
		"\7p\2\2\u0119\u011a\7g\2\2\u011a:\3\2\2\2\u011b\u011c\7k\2\2\u011c\u011d"+
		"\7h\2\2\u011d<\3\2\2\2\u011e\u011f\7v\2\2\u011f\u0120\7j\2\2\u0120\u0121"+
		"\7g\2\2\u0121\u0122\7p\2\2\u0122>\3\2\2\2\u0123\u0124\7g\2\2\u0124\u0125"+
		"\7n\2\2\u0125\u0126\7u\2\2\u0126\u0127\7g\2\2\u0127@\3\2\2\2\u0128\u0129"+
		"\7h\2\2\u0129\u012a\7k\2\2\u012aB\3\2\2\2\u012b\u012c\7*\2\2\u012cD\3"+
		"\2\2\2\u012d\u012e\7+\2\2\u012eF\3\2\2\2\u012f\u0130\7]\2\2\u0130H\3\2"+
		"\2\2\u0131\u0132\7_\2\2\u0132J\3\2\2\2\u0133\u0134\7-\2\2\u0134L\3\2\2"+
		"\2\u0135\u0136\7/\2\2\u0136N\3\2\2\2\u0137\u0138\7,\2\2\u0138P\3\2\2\2"+
		"\u0139\u013a\7\61\2\2\u013aR\3\2\2\2\u013b\u013c\7\'\2\2\u013cT\3\2\2"+
		"\2\u013d\u013e\7@\2\2\u013eV\3\2\2\2\u013f\u0140\7@\2\2\u0140\u0141\7"+
		"?\2\2\u0141X\3\2\2\2\u0142\u0143\7>\2\2\u0143Z\3\2\2\2\u0144\u0145\7>"+
		"\2\2\u0145\u0146\7?\2\2\u0146\\\3\2\2\2\u0147\u0148\7?\2\2\u0148\u0149"+
		"\7?\2\2\u0149^\3\2\2\2\u014a\u014b\7#\2\2\u014b\u014c\7?\2\2\u014c`\3"+
		"\2\2\2\u014d\u014e\7(\2\2\u014e\u014f\7(\2\2\u014fb\3\2\2\2\u0150\u0151"+
		"\7~\2\2\u0151\u0152\7~\2\2\u0152d\3\2\2\2\u0153\u0154\7#\2\2\u0154f\3"+
		"\2\2\2\u0155\u0156\7n\2\2\u0156\u0157\7g\2\2\u0157\u0158\7p\2\2\u0158"+
		"h\3\2\2\2\u0159\u015a\7q\2\2\u015a\u015b\7t\2\2\u015b\u015c\7f\2\2\u015c"+
		"j\3\2\2\2\u015d\u015e\7e\2\2\u015e\u015f\7j\2\2\u015f\u0160\7t\2\2\u0160"+
		"l\3\2\2\2\u0161\u0162\7?\2\2\u0162n\3\2\2\2\u0163\u0165\5\7\4\2\u0164"+
		"\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2"+
		"\2\2\u0167p\3\2\2\2\u0168\u016b\5s:\2\u0169\u016b\5u;\2\u016a\u0168\3"+
		"\2\2\2\u016a\u0169\3\2\2\2\u016br\3\2\2\2\u016c\u016d\7v\2\2\u016d\u016e"+
		"\7t\2\2\u016e\u016f\7w\2\2\u016f\u0170\7g\2\2\u0170t\3\2\2\2\u0171\u0172"+
		"\7h\2\2\u0172\u0173\7c\2\2\u0173\u0174\7n\2\2\u0174\u0175\7u\2\2\u0175"+
		"\u0176\7g\2\2\u0176v\3\2\2\2\u0177\u0178\7p\2\2\u0178\u0179\7g\2\2\u0179"+
		"\u017a\7y\2\2\u017a\u017b\7r\2\2\u017b\u017c\7c\2\2\u017c\u017d\7k\2\2"+
		"\u017d\u017e\7t\2\2\u017ex\3\2\2\2\u017f\u0180\7p\2\2\u0180\u0181\7w\2"+
		"\2\u0181\u0182\7n\2\2\u0182\u0183\7n\2\2\u0183z\3\2\2\2\u0184\u0185\7"+
		"h\2\2\u0185\u0186\7u\2\2\u0186\u0187\7v\2\2\u0187|\3\2\2\2\u0188\u0189"+
		"\7u\2\2\u0189\u018a\7p\2\2\u018a\u018b\7f\2\2\u018b~\3\2\2\2\u018c\u0190"+
		"\n\6\2\2\u018d\u018e\7^\2\2\u018e\u0190\5\5\3\2\u018f\u018c\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u0190\u0080\3\2\2\2\u0191\u0195\7$\2\2\u0192\u0194\5\177"+
		"@\2\u0193\u0192\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193\3\2\2\2\u0195"+
		"\u0196\3\2\2\2\u0196\u0198\3\2\2\2\u0197\u0195\3\2\2\2\u0198\u0199\7$"+
		"\2\2\u0199\u0082\3\2\2\2\u019a\u019c\7)\2\2\u019b\u019d\5\177@\2\u019c"+
		"\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\7)"+
		"\2\2\u019f\u0084\3\2\2\2\u01a0\u01a4\t\7\2\2\u01a1\u01a3\t\b\2\2\u01a2"+
		"\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5\3\2"+
		"\2\2\u01a5\u0086\3\2\2\2\u01a6\u01a4\3\2\2\2\20\2\u0088\u0093\u009b\u00a0"+
		"\u00c1\u00ca\u00d0\u0166\u016a\u018f\u0195\u019c\u01a4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}