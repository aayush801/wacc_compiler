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
		BASE_TYPE=1, BEGIN=2, END=3, RETURN=4, CALL=5, EXIT=6, SKIP_STATEMENT=7, 
		IS=8, READ=9, FREE=10, PRINT=11, PRINT_LINE=12, WHILE=13, DO=14, END_WHILE=15, 
		IF=16, THEN=17, ELSE=18, END_IF=19, OPEN_PARENTHESES=20, CLOSE_PARENTHESES=21, 
		OPEN_SQUARE_BRACKET=22, CLOSE_SQUARE_BRACKET=23, NOT=24, LENGTH=25, ORD=26, 
		CHR=27, PLUS=28, MINUS=29, MULTIPLY=30, DIVIDE=31, MOD=32, GR=33, GRE=34, 
		LS=35, LSE=36, EQ=37, NEQ=38, AND=39, OR=40, EQUALS=41, COMMA=42, SEPERATOR=43, 
		WS=44, ARRAY_TYPE=45, BOOLEAN=46, STRING=47, CHARACTER=48, CREATE_PAIR=49, 
		PAIR=50, PAIR_FIRST=51, PAIR_SECOND=52, PAIR_TYPE=53, INTEGER=54, COMMENT=55, 
		IDENT=56;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "ESCAPED_CHAR", "BASE_TYPE", "BEGIN", "END", "RETURN", "CALL", 
			"EXIT", "SKIP_STATEMENT", "IS", "READ", "FREE", "PRINT", "PRINT_LINE", 
			"WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", "END_IF", "OPEN_PARENTHESES", 
			"CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "NOT", 
			"LENGTH", "ORD", "CHR", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", 
			"GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", "EQUALS", "COMMA", 
			"SEPERATOR", "WS", "ARRAY_TYPE", "BOOLEAN", "CHAR", "STRING", "CHARACTER", 
			"CREATE_PAIR", "PAIR", "PAIR_FIRST", "PAIR_SECOND", "PAIR_TYPE", "DIGIT", 
			"INTEGER", "COMMENT", "IDENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'begin'", "'end'", "'return'", "'call'", "'exit'", "'skip'", 
			"'is'", "'read'", "'free'", "'print'", "'println'", "'while'", "'do'", 
			"'done'", "'if'", "'then'", "'else'", "'fi'", "'('", "')'", "'['", "']'", 
			"'!'", "'len'", "'ord'", "'chr'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'&&'", "'||'", "'='", 
			"','", "';'", null, null, null, null, null, "'newpair'", "'null'", "'fst'", 
			"'snd'", "'pair'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BASE_TYPE", "BEGIN", "END", "RETURN", "CALL", "EXIT", "SKIP_STATEMENT", 
			"IS", "READ", "FREE", "PRINT", "PRINT_LINE", "WHILE", "DO", "END_WHILE", 
			"IF", "THEN", "ELSE", "END_IF", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", 
			"OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", "NOT", "LENGTH", "ORD", 
			"CHR", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", "GR", "GRE", "LS", 
			"LSE", "EQ", "NEQ", "AND", "OR", "EQUALS", "COMMA", "SEPERATOR", "WS", 
			"ARRAY_TYPE", "BOOLEAN", "STRING", "CHARACTER", "CREATE_PAIR", "PAIR", 
			"PAIR_FIRST", "PAIR_SECOND", "PAIR_TYPE", "INTEGER", "COMMENT", "IDENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u018d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4\u0091\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3"+
		"\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 "+
		"\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)\3"+
		")\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\6/\u0129\n/\r/\16/\u012a\3/\3"+
		"/\3\60\3\60\5\60\u0131\n\60\3\60\3\60\6\60\u0135\n\60\r\60\16\60\u0136"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0142\n\61\3\62\3\62"+
		"\3\62\5\62\u0147\n\62\3\63\3\63\7\63\u014b\n\63\f\63\16\63\u014e\13\63"+
		"\3\63\3\63\3\64\3\64\7\64\u0154\n\64\f\64\16\64\u0157\13\64\3\64\3\64"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67"+
		"\3\67\3\67\3\67\38\38\38\38\39\39\39\39\39\3:\3:\3;\6;\u0178\n;\r;\16"+
		";\u0179\3<\3<\7<\u017e\n<\f<\16<\u0181\13<\3<\3<\3<\3<\3=\3=\7=\u0189"+
		"\n=\f=\16=\u018c\13=\2\2>\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n"+
		"\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63"+
		"\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60"+
		"c\2e\61g\62i\63k\64m\65o\66q\67s\2u8w9y:\3\2\t\13\2$$\62\62^^ddhhpptt"+
		"vv\u201b\u201b\5\2\13\f\17\17\"\"\5\2$$))^^\3\2\62;\4\2\f\f\17\17\4\2"+
		"aac|\6\2\62;C\\aac|\2\u0195\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3"+
		"\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2"+
		"\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3"+
		"\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2"+
		"\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2"+
		";\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3"+
		"\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2"+
		"\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2"+
		"a\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3"+
		"\2\2\2\2q\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3{\3\2\2\2\5}\3\2\2"+
		"\2\7\u0090\3\2\2\2\t\u0092\3\2\2\2\13\u0098\3\2\2\2\r\u009c\3\2\2\2\17"+
		"\u00a3\3\2\2\2\21\u00a8\3\2\2\2\23\u00ad\3\2\2\2\25\u00b2\3\2\2\2\27\u00b5"+
		"\3\2\2\2\31\u00ba\3\2\2\2\33\u00bf\3\2\2\2\35\u00c5\3\2\2\2\37\u00cd\3"+
		"\2\2\2!\u00d3\3\2\2\2#\u00d6\3\2\2\2%\u00db\3\2\2\2\'\u00de\3\2\2\2)\u00e3"+
		"\3\2\2\2+\u00e8\3\2\2\2-\u00eb\3\2\2\2/\u00ed\3\2\2\2\61\u00ef\3\2\2\2"+
		"\63\u00f1\3\2\2\2\65\u00f3\3\2\2\2\67\u00f5\3\2\2\29\u00f9\3\2\2\2;\u00fd"+
		"\3\2\2\2=\u0101\3\2\2\2?\u0103\3\2\2\2A\u0105\3\2\2\2C\u0107\3\2\2\2E"+
		"\u0109\3\2\2\2G\u010b\3\2\2\2I\u010d\3\2\2\2K\u0110\3\2\2\2M\u0112\3\2"+
		"\2\2O\u0115\3\2\2\2Q\u0118\3\2\2\2S\u011b\3\2\2\2U\u011e\3\2\2\2W\u0121"+
		"\3\2\2\2Y\u0123\3\2\2\2[\u0125\3\2\2\2]\u0128\3\2\2\2_\u0130\3\2\2\2a"+
		"\u0141\3\2\2\2c\u0146\3\2\2\2e\u0148\3\2\2\2g\u0151\3\2\2\2i\u015a\3\2"+
		"\2\2k\u0162\3\2\2\2m\u0167\3\2\2\2o\u016b\3\2\2\2q\u016f\3\2\2\2s\u0174"+
		"\3\2\2\2u\u0177\3\2\2\2w\u017b\3\2\2\2y\u0186\3\2\2\2{|\7\f\2\2|\4\3\2"+
		"\2\2}~\t\2\2\2~\6\3\2\2\2\177\u0080\7k\2\2\u0080\u0081\7p\2\2\u0081\u0091"+
		"\7v\2\2\u0082\u0083\7d\2\2\u0083\u0084\7q\2\2\u0084\u0085\7q\2\2\u0085"+
		"\u0091\7n\2\2\u0086\u0087\7e\2\2\u0087\u0088\7j\2\2\u0088\u0089\7c\2\2"+
		"\u0089\u0091\7t\2\2\u008a\u008b\7u\2\2\u008b\u008c\7v\2\2\u008c\u008d"+
		"\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2\u008f\u0091\7i\2\2\u0090"+
		"\177\3\2\2\2\u0090\u0082\3\2\2\2\u0090\u0086\3\2\2\2\u0090\u008a\3\2\2"+
		"\2\u0091\b\3\2\2\2\u0092\u0093\7d\2\2\u0093\u0094\7g\2\2\u0094\u0095\7"+
		"i\2\2\u0095\u0096\7k\2\2\u0096\u0097\7p\2\2\u0097\n\3\2\2\2\u0098\u0099"+
		"\7g\2\2\u0099\u009a\7p\2\2\u009a\u009b\7f\2\2\u009b\f\3\2\2\2\u009c\u009d"+
		"\7t\2\2\u009d\u009e\7g\2\2\u009e\u009f\7v\2\2\u009f\u00a0\7w\2\2\u00a0"+
		"\u00a1\7t\2\2\u00a1\u00a2\7p\2\2\u00a2\16\3\2\2\2\u00a3\u00a4\7e\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7n\2\2\u00a6\u00a7\7n\2\2\u00a7\20\3\2\2\2\u00a8"+
		"\u00a9\7g\2\2\u00a9\u00aa\7z\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7v\2\2"+
		"\u00ac\22\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7m\2\2\u00af\u00b0\7"+
		"k\2\2\u00b0\u00b1\7r\2\2\u00b1\24\3\2\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4"+
		"\7u\2\2\u00b4\26\3\2\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8"+
		"\7c\2\2\u00b8\u00b9\7f\2\2\u00b9\30\3\2\2\2\u00ba\u00bb\7h\2\2\u00bb\u00bc"+
		"\7t\2\2\u00bc\u00bd\7g\2\2\u00bd\u00be\7g\2\2\u00be\32\3\2\2\2\u00bf\u00c0"+
		"\7r\2\2\u00c0\u00c1\7t\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7p\2\2\u00c3"+
		"\u00c4\7v\2\2\u00c4\34\3\2\2\2\u00c5\u00c6\7r\2\2\u00c6\u00c7\7t\2\2\u00c7"+
		"\u00c8\7k\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7n\2\2"+
		"\u00cb\u00cc\7p\2\2\u00cc\36\3\2\2\2\u00cd\u00ce\7y\2\2\u00ce\u00cf\7"+
		"j\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7n\2\2\u00d1\u00d2\7g\2\2\u00d2 "+
		"\3\2\2\2\u00d3\u00d4\7f\2\2\u00d4\u00d5\7q\2\2\u00d5\"\3\2\2\2\u00d6\u00d7"+
		"\7f\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7g\2\2\u00da"+
		"$\3\2\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7h\2\2\u00dd&\3\2\2\2\u00de\u00df"+
		"\7v\2\2\u00df\u00e0\7j\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7p\2\2\u00e2"+
		"(\3\2\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7u\2\2\u00e6"+
		"\u00e7\7g\2\2\u00e7*\3\2\2\2\u00e8\u00e9\7h\2\2\u00e9\u00ea\7k\2\2\u00ea"+
		",\3\2\2\2\u00eb\u00ec\7*\2\2\u00ec.\3\2\2\2\u00ed\u00ee\7+\2\2\u00ee\60"+
		"\3\2\2\2\u00ef\u00f0\7]\2\2\u00f0\62\3\2\2\2\u00f1\u00f2\7_\2\2\u00f2"+
		"\64\3\2\2\2\u00f3\u00f4\7#\2\2\u00f4\66\3\2\2\2\u00f5\u00f6\7n\2\2\u00f6"+
		"\u00f7\7g\2\2\u00f7\u00f8\7p\2\2\u00f88\3\2\2\2\u00f9\u00fa\7q\2\2\u00fa"+
		"\u00fb\7t\2\2\u00fb\u00fc\7f\2\2\u00fc:\3\2\2\2\u00fd\u00fe\7e\2\2\u00fe"+
		"\u00ff\7j\2\2\u00ff\u0100\7t\2\2\u0100<\3\2\2\2\u0101\u0102\7-\2\2\u0102"+
		">\3\2\2\2\u0103\u0104\7/\2\2\u0104@\3\2\2\2\u0105\u0106\7,\2\2\u0106B"+
		"\3\2\2\2\u0107\u0108\7\61\2\2\u0108D\3\2\2\2\u0109\u010a\7\'\2\2\u010a"+
		"F\3\2\2\2\u010b\u010c\7@\2\2\u010cH\3\2\2\2\u010d\u010e\7@\2\2\u010e\u010f"+
		"\7?\2\2\u010fJ\3\2\2\2\u0110\u0111\7>\2\2\u0111L\3\2\2\2\u0112\u0113\7"+
		">\2\2\u0113\u0114\7?\2\2\u0114N\3\2\2\2\u0115\u0116\7?\2\2\u0116\u0117"+
		"\7?\2\2\u0117P\3\2\2\2\u0118\u0119\7#\2\2\u0119\u011a\7?\2\2\u011aR\3"+
		"\2\2\2\u011b\u011c\7(\2\2\u011c\u011d\7(\2\2\u011dT\3\2\2\2\u011e\u011f"+
		"\7~\2\2\u011f\u0120\7~\2\2\u0120V\3\2\2\2\u0121\u0122\7?\2\2\u0122X\3"+
		"\2\2\2\u0123\u0124\7.\2\2\u0124Z\3\2\2\2\u0125\u0126\7=\2\2\u0126\\\3"+
		"\2\2\2\u0127\u0129\t\3\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\b/"+
		"\2\2\u012d^\3\2\2\2\u012e\u0131\5\7\4\2\u012f\u0131\5q9\2\u0130\u012e"+
		"\3\2\2\2\u0130\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0133\7]\2\2\u0133"+
		"\u0135\7_\2\2\u0134\u0132\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3\2"+
		"\2\2\u0136\u0137\3\2\2\2\u0137`\3\2\2\2\u0138\u0139\7v\2\2\u0139\u013a"+
		"\7t\2\2\u013a\u013b\7w\2\2\u013b\u0142\7g\2\2\u013c\u013d\7h\2\2\u013d"+
		"\u013e\7c\2\2\u013e\u013f\7n\2\2\u013f\u0140\7u\2\2\u0140\u0142\7g\2\2"+
		"\u0141\u0138\3\2\2\2\u0141\u013c\3\2\2\2\u0142b\3\2\2\2\u0143\u0147\n"+
		"\4\2\2\u0144\u0145\7^\2\2\u0145\u0147\5\5\3\2\u0146\u0143\3\2\2\2\u0146"+
		"\u0144\3\2\2\2\u0147d\3\2\2\2\u0148\u014c\7$\2\2\u0149\u014b\5c\62\2\u014a"+
		"\u0149\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2"+
		"\2\2\u014d\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\7$\2\2\u0150"+
		"f\3\2\2\2\u0151\u0155\7)\2\2\u0152\u0154\5c\62\2\u0153\u0152\3\2\2\2\u0154"+
		"\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2"+
		"\2\2\u0157\u0155\3\2\2\2\u0158\u0159\7)\2\2\u0159h\3\2\2\2\u015a\u015b"+
		"\7p\2\2\u015b\u015c\7g\2\2\u015c\u015d\7y\2\2\u015d\u015e\7r\2\2\u015e"+
		"\u015f\7c\2\2\u015f\u0160\7k\2\2\u0160\u0161\7t\2\2\u0161j\3\2\2\2\u0162"+
		"\u0163\7p\2\2\u0163\u0164\7w\2\2\u0164\u0165\7n\2\2\u0165\u0166\7n\2\2"+
		"\u0166l\3\2\2\2\u0167\u0168\7h\2\2\u0168\u0169\7u\2\2\u0169\u016a\7v\2"+
		"\2\u016an\3\2\2\2\u016b\u016c\7u\2\2\u016c\u016d\7p\2\2\u016d\u016e\7"+
		"f\2\2\u016ep\3\2\2\2\u016f\u0170\7r\2\2\u0170\u0171\7c\2\2\u0171\u0172"+
		"\7k\2\2\u0172\u0173\7t\2\2\u0173r\3\2\2\2\u0174\u0175\t\5\2\2\u0175t\3"+
		"\2\2\2\u0176\u0178\5s:\2\u0177\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
		"\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017av\3\2\2\2\u017b\u017f\7%\2\2\u017c"+
		"\u017e\n\6\2\2\u017d\u017c\3\2\2\2\u017e\u0181\3\2\2\2\u017f\u017d\3\2"+
		"\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u017f\3\2\2\2\u0182"+
		"\u0183\5\3\2\2\u0183\u0184\3\2\2\2\u0184\u0185\b<\2\2\u0185x\3\2\2\2\u0186"+
		"\u018a\t\7\2\2\u0187\u0189\t\b\2\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2"+
		"\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018bz\3\2\2\2\u018c\u018a"+
		"\3\2\2\2\16\2\u0090\u012a\u0130\u0136\u0141\u0146\u014c\u0155\u0179\u017f"+
		"\u018a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}