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
		WS=1, COMMENT=2, BASE_TYPE=3, ARRAY_TYPE=4, BEGIN=5, END=6, RETURN=7, 
		CALL=8, EXIT=9, SKIP_STATEMENT=10, IS=11, READ=12, FREE=13, PRINT=14, 
		PRINT_LINE=15, WHILE=16, DO=17, END_WHILE=18, IF=19, THEN=20, ELSE=21, 
		END_IF=22, OPEN_PARENTHESES=23, CLOSE_PARENTHESES=24, OPEN_SQUARE_BRACKET=25, 
		CLOSE_SQUARE_BRACKET=26, PLUS=27, MINUS=28, MULTIPLY=29, DIVIDE=30, MOD=31, 
		GR=32, GRE=33, LS=34, LSE=35, EQ=36, NEQ=37, AND=38, OR=39, NOT=40, LENGTH=41, 
		ORD=42, CHR=43, EQUALS=44, COMMA=45, SEPERATOR=46, INTEGER=47, BOOLEAN=48, 
		CREATE_PAIR=49, PAIR=50, PAIR_FIRST=51, PAIR_SECOND=52, PAIR_TYPE=53, 
		STRING=54, CHARACTER=55, IDENT=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "ESCAPED_CHAR", "DIGIT", "WS", "COMMENT", "BASE_TYPE", "ARRAY_TYPE", 
			"BEGIN", "END", "RETURN", "CALL", "EXIT", "SKIP_STATEMENT", "IS", "READ", 
			"FREE", "PRINT", "PRINT_LINE", "WHILE", "DO", "END_WHILE", "IF", "THEN", 
			"ELSE", "END_IF", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", 
			"GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", "NOT", "LENGTH", 
			"ORD", "CHR", "EQUALS", "COMMA", "SEPERATOR", "INTEGER", "BOOLEAN", "CREATE_PAIR", 
			"PAIR", "PAIR_FIRST", "PAIR_SECOND", "PAIR_TYPE", "CHAR", "STRING", "CHARACTER", 
			"IDENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'begin'", "'end'", "'return'", "'call'", 
			"'exit'", "'skip'", "'is'", "'read'", "'free'", "'print'", "'println'", 
			"'while'", "'do'", "'done'", "'if'", "'then'", "'else'", "'fi'", "'('", 
			"')'", "'['", "']'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", 
			"'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", 
			"'chr'", "'='", "','", "';'", null, null, "'newpair'", "'null'", "'fst'", 
			"'snd'", "'pair'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "COMMENT", "BASE_TYPE", "ARRAY_TYPE", "BEGIN", "END", "RETURN", 
			"CALL", "EXIT", "SKIP_STATEMENT", "IS", "READ", "FREE", "PRINT", "PRINT_LINE", 
			"WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", "END_IF", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "MOD", "GR", "GRE", "LS", "LSE", "EQ", 
			"NEQ", "AND", "OR", "NOT", "LENGTH", "ORD", "CHR", "EQUALS", "COMMA", 
			"SEPERATOR", "INTEGER", "BOOLEAN", "CREATE_PAIR", "PAIR", "PAIR_FIRST", 
			"PAIR_SECOND", "PAIR_TYPE", "STRING", "CHARACTER", "IDENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0190\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\5\2}\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\6\5\u0086\n\5\r\5\16\5\u0087"+
		"\3\5\3\5\3\6\3\6\7\6\u008e\n\6\f\6\16\6\u0091\13\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00a8"+
		"\n\7\3\b\3\b\5\b\u00ac\n\b\3\b\3\b\6\b\u00b0\n\b\r\b\16\b\u00b1\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+"+
		"\3+\3+\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\61\3\61\3"+
		"\62\3\62\3\63\6\63\u014a\n\63\r\63\16\63\u014b\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\5\64\u0157\n\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\38\38\38\39\39\3"+
		"9\39\39\3:\3:\3:\5:\u0176\n:\3;\3;\7;\u017a\n;\f;\16;\u017d\13;\3;\3;"+
		"\3<\3<\7<\u0183\n<\f<\16<\u0186\13<\3<\3<\3=\3=\7=\u018c\n=\f=\16=\u018f"+
		"\13=\2\2>\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21\7\23\b\25\t\27\n\31\13\33\f"+
		"\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31\67\329"+
		"\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62i\63"+
		"k\64m\65o\66q\67s\2u8w9y:\3\2\t\13\2$$\62\62^^ddhhppttvv\u201b\u201b\3"+
		"\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\5\2$$))^^\4\2aac|\6\2\62;C\\a"+
		"ac|\2\u0199\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2"+
		"\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2"+
		"M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3"+
		"\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2"+
		"\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2"+
		"u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3|\3\2\2\2\5\u0080\3\2\2\2\7\u0082\3\2"+
		"\2\2\t\u0085\3\2\2\2\13\u008b\3\2\2\2\r\u00a7\3\2\2\2\17\u00ab\3\2\2\2"+
		"\21\u00b3\3\2\2\2\23\u00b9\3\2\2\2\25\u00bd\3\2\2\2\27\u00c4\3\2\2\2\31"+
		"\u00c9\3\2\2\2\33\u00ce\3\2\2\2\35\u00d3\3\2\2\2\37\u00d6\3\2\2\2!\u00db"+
		"\3\2\2\2#\u00e0\3\2\2\2%\u00e6\3\2\2\2\'\u00ee\3\2\2\2)\u00f4\3\2\2\2"+
		"+\u00f7\3\2\2\2-\u00fc\3\2\2\2/\u00ff\3\2\2\2\61\u0104\3\2\2\2\63\u0109"+
		"\3\2\2\2\65\u010c\3\2\2\2\67\u010e\3\2\2\29\u0110\3\2\2\2;\u0112\3\2\2"+
		"\2=\u0114\3\2\2\2?\u0116\3\2\2\2A\u0118\3\2\2\2C\u011a\3\2\2\2E\u011c"+
		"\3\2\2\2G\u011e\3\2\2\2I\u0120\3\2\2\2K\u0123\3\2\2\2M\u0125\3\2\2\2O"+
		"\u0128\3\2\2\2Q\u012b\3\2\2\2S\u012e\3\2\2\2U\u0131\3\2\2\2W\u0134\3\2"+
		"\2\2Y\u0136\3\2\2\2[\u013a\3\2\2\2]\u013e\3\2\2\2_\u0142\3\2\2\2a\u0144"+
		"\3\2\2\2c\u0146\3\2\2\2e\u0149\3\2\2\2g\u0156\3\2\2\2i\u0158\3\2\2\2k"+
		"\u0160\3\2\2\2m\u0165\3\2\2\2o\u0169\3\2\2\2q\u016d\3\2\2\2s\u0175\3\2"+
		"\2\2u\u0177\3\2\2\2w\u0180\3\2\2\2y\u0189\3\2\2\2{}\7\17\2\2|{\3\2\2\2"+
		"|}\3\2\2\2}~\3\2\2\2~\177\7\f\2\2\177\4\3\2\2\2\u0080\u0081\t\2\2\2\u0081"+
		"\6\3\2\2\2\u0082\u0083\t\3\2\2\u0083\b\3\2\2\2\u0084\u0086\t\4\2\2\u0085"+
		"\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\5\2\2\u008a\n\3\2\2\2\u008b\u008f"+
		"\7%\2\2\u008c\u008e\n\5\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0092\u0093\5\3\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\6\2\2\u0095"+
		"\f\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7p\2\2\u0098\u00a8\7v\2\2\u0099"+
		"\u009a\7d\2\2\u009a\u009b\7q\2\2\u009b\u009c\7q\2\2\u009c\u00a8\7n\2\2"+
		"\u009d\u009e\7e\2\2\u009e\u009f\7j\2\2\u009f\u00a0\7c\2\2\u00a0\u00a8"+
		"\7t\2\2\u00a1\u00a2\7u\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7t\2\2\u00a4"+
		"\u00a5\7k\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a8\7i\2\2\u00a7\u0096\3\2\2"+
		"\2\u00a7\u0099\3\2\2\2\u00a7\u009d\3\2\2\2\u00a7\u00a1\3\2\2\2\u00a8\16"+
		"\3\2\2\2\u00a9\u00ac\5\r\7\2\u00aa\u00ac\5q9\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ae\7]\2\2\u00ae\u00b0\7_\2"+
		"\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2"+
		"\3\2\2\2\u00b2\20\3\2\2\2\u00b3\u00b4\7d\2\2\u00b4\u00b5\7g\2\2\u00b5"+
		"\u00b6\7i\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7p\2\2\u00b8\22\3\2\2\2\u00b9"+
		"\u00ba\7g\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7f\2\2\u00bc\24\3\2\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7v\2\2\u00c0\u00c1\7w\2\2"+
		"\u00c1\u00c2\7t\2\2\u00c2\u00c3\7p\2\2\u00c3\26\3\2\2\2\u00c4\u00c5\7"+
		"e\2\2\u00c5\u00c6\7c\2\2\u00c6\u00c7\7n\2\2\u00c7\u00c8\7n\2\2\u00c8\30"+
		"\3\2\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7z\2\2\u00cb\u00cc\7k\2\2\u00cc"+
		"\u00cd\7v\2\2\u00cd\32\3\2\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7m\2\2\u00d0"+
		"\u00d1\7k\2\2\u00d1\u00d2\7r\2\2\u00d2\34\3\2\2\2\u00d3\u00d4\7k\2\2\u00d4"+
		"\u00d5\7u\2\2\u00d5\36\3\2\2\2\u00d6\u00d7\7t\2\2\u00d7\u00d8\7g\2\2\u00d8"+
		"\u00d9\7c\2\2\u00d9\u00da\7f\2\2\u00da \3\2\2\2\u00db\u00dc\7h\2\2\u00dc"+
		"\u00dd\7t\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7g\2\2\u00df\"\3\2\2\2\u00e0"+
		"\u00e1\7r\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7p\2\2"+
		"\u00e4\u00e5\7v\2\2\u00e5$\3\2\2\2\u00e6\u00e7\7r\2\2\u00e7\u00e8\7t\2"+
		"\2\u00e8\u00e9\7k\2\2\u00e9\u00ea\7p\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec"+
		"\7n\2\2\u00ec\u00ed\7p\2\2\u00ed&\3\2\2\2\u00ee\u00ef\7y\2\2\u00ef\u00f0"+
		"\7j\2\2\u00f0\u00f1\7k\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7g\2\2\u00f3"+
		"(\3\2\2\2\u00f4\u00f5\7f\2\2\u00f5\u00f6\7q\2\2\u00f6*\3\2\2\2\u00f7\u00f8"+
		"\7f\2\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7p\2\2\u00fa\u00fb\7g\2\2\u00fb"+
		",\3\2\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7h\2\2\u00fe.\3\2\2\2\u00ff\u0100"+
		"\7v\2\2\u0100\u0101\7j\2\2\u0101\u0102\7g\2\2\u0102\u0103\7p\2\2\u0103"+
		"\60\3\2\2\2\u0104\u0105\7g\2\2\u0105\u0106\7n\2\2\u0106\u0107\7u\2\2\u0107"+
		"\u0108\7g\2\2\u0108\62\3\2\2\2\u0109\u010a\7h\2\2\u010a\u010b\7k\2\2\u010b"+
		"\64\3\2\2\2\u010c\u010d\7*\2\2\u010d\66\3\2\2\2\u010e\u010f\7+\2\2\u010f"+
		"8\3\2\2\2\u0110\u0111\7]\2\2\u0111:\3\2\2\2\u0112\u0113\7_\2\2\u0113<"+
		"\3\2\2\2\u0114\u0115\7-\2\2\u0115>\3\2\2\2\u0116\u0117\7/\2\2\u0117@\3"+
		"\2\2\2\u0118\u0119\7,\2\2\u0119B\3\2\2\2\u011a\u011b\7\61\2\2\u011bD\3"+
		"\2\2\2\u011c\u011d\7\'\2\2\u011dF\3\2\2\2\u011e\u011f\7@\2\2\u011fH\3"+
		"\2\2\2\u0120\u0121\7@\2\2\u0121\u0122\7?\2\2\u0122J\3\2\2\2\u0123\u0124"+
		"\7>\2\2\u0124L\3\2\2\2\u0125\u0126\7>\2\2\u0126\u0127\7?\2\2\u0127N\3"+
		"\2\2\2\u0128\u0129\7?\2\2\u0129\u012a\7?\2\2\u012aP\3\2\2\2\u012b\u012c"+
		"\7#\2\2\u012c\u012d\7?\2\2\u012dR\3\2\2\2\u012e\u012f\7(\2\2\u012f\u0130"+
		"\7(\2\2\u0130T\3\2\2\2\u0131\u0132\7~\2\2\u0132\u0133\7~\2\2\u0133V\3"+
		"\2\2\2\u0134\u0135\7#\2\2\u0135X\3\2\2\2\u0136\u0137\7n\2\2\u0137\u0138"+
		"\7g\2\2\u0138\u0139\7p\2\2\u0139Z\3\2\2\2\u013a\u013b\7q\2\2\u013b\u013c"+
		"\7t\2\2\u013c\u013d\7f\2\2\u013d\\\3\2\2\2\u013e\u013f\7e\2\2\u013f\u0140"+
		"\7j\2\2\u0140\u0141\7t\2\2\u0141^\3\2\2\2\u0142\u0143\7?\2\2\u0143`\3"+
		"\2\2\2\u0144\u0145\7.\2\2\u0145b\3\2\2\2\u0146\u0147\7=\2\2\u0147d\3\2"+
		"\2\2\u0148\u014a\5\7\4\2\u0149\u0148\3\2\2\2\u014a\u014b\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014cf\3\2\2\2\u014d\u014e\7v\2\2\u014e"+
		"\u014f\7t\2\2\u014f\u0150\7w\2\2\u0150\u0157\7g\2\2\u0151\u0152\7h\2\2"+
		"\u0152\u0153\7c\2\2\u0153\u0154\7n\2\2\u0154\u0155\7u\2\2\u0155\u0157"+
		"\7g\2\2\u0156\u014d\3\2\2\2\u0156\u0151\3\2\2\2\u0157h\3\2\2\2\u0158\u0159"+
		"\7p\2\2\u0159\u015a\7g\2\2\u015a\u015b\7y\2\2\u015b\u015c\7r\2\2\u015c"+
		"\u015d\7c\2\2\u015d\u015e\7k\2\2\u015e\u015f\7t\2\2\u015fj\3\2\2\2\u0160"+
		"\u0161\7p\2\2\u0161\u0162\7w\2\2\u0162\u0163\7n\2\2\u0163\u0164\7n\2\2"+
		"\u0164l\3\2\2\2\u0165\u0166\7h\2\2\u0166\u0167\7u\2\2\u0167\u0168\7v\2"+
		"\2\u0168n\3\2\2\2\u0169\u016a\7u\2\2\u016a\u016b\7p\2\2\u016b\u016c\7"+
		"f\2\2\u016cp\3\2\2\2\u016d\u016e\7r\2\2\u016e\u016f\7c\2\2\u016f\u0170"+
		"\7k\2\2\u0170\u0171\7t\2\2\u0171r\3\2\2\2\u0172\u0176\n\6\2\2\u0173\u0174"+
		"\7^\2\2\u0174\u0176\5\5\3\2\u0175\u0172\3\2\2\2\u0175\u0173\3\2\2\2\u0176"+
		"t\3\2\2\2\u0177\u017b\7$\2\2\u0178\u017a\5s:\2\u0179\u0178\3\2\2\2\u017a"+
		"\u017d\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2"+
		"\2\2\u017d\u017b\3\2\2\2\u017e\u017f\7$\2\2\u017fv\3\2\2\2\u0180\u0184"+
		"\7)\2\2\u0181\u0183\5s:\2\u0182\u0181\3\2\2\2\u0183\u0186\3\2\2\2\u0184"+
		"\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0187\3\2\2\2\u0186\u0184\3\2"+
		"\2\2\u0187\u0188\7)\2\2\u0188x\3\2\2\2\u0189\u018d\t\7\2\2\u018a\u018c"+
		"\t\b\2\2\u018b\u018a\3\2\2\2\u018c\u018f\3\2\2\2\u018d\u018b\3\2\2\2\u018d"+
		"\u018e\3\2\2\2\u018ez\3\2\2\2\u018f\u018d\3\2\2\2\17\2|\u0087\u008f\u00a7"+
		"\u00ab\u00b1\u014b\u0156\u0175\u017b\u0184\u018d\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}