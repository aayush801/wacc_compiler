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
public class BasicLexer extends Lexer {
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
		ORD=49, CHR=50, EQUALS=51, INTEGER=52, BOOLEAN=53, CREATE_PAIR=54, PAIR=55, 
		PAIR_FIRST=56, PAIR_SECOND=57, STRING=58, CHARACTER=59, IDENT=60;
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
			"NOT", "LENGTH", "ORD", "CHR", "EQUALS", "INTEGER", "BOOLEAN", "CREATE_PAIR", 
			"PAIR", "PAIR_FIRST", "PAIR_SECOND", "CHAR", "STRING", "CHARACTER", "IDENT"
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
			null, null, "'newpair'", "'null'", "'fst'", "'snd'"
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
			"ORD", "CHR", "EQUALS", "INTEGER", "BOOLEAN", "CREATE_PAIR", "PAIR", 
			"PAIR_FIRST", "PAIR_SECOND", "STRING", "CHARACTER", "IDENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2>\u01a4\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\3\2\5\2\u0085\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\5\3\u008e\n\3\3\4\3\4\3\5\6\5\u0093\n\5\r\5\16\5\u0094\3\5\3\5\3\6\3"+
		"\6\7\6\u009b\n\6\f\6\16\6\u009e\13\6\3\6\3\6\5\6\u00a2\n\6\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00c3\n\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\5\17\u00cc\n\17\3\17\3\17\6\17\u00d0\n\17\r"+
		"\17\16\17\u00d1\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3"+
		"\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\38\68\u0166\n8\r8\168\u0167\39"+
		"\39\39\39\39\39\39\39\39\59\u0173\n9\3:\3:\3:\3:\3:\3:\3:\3:\3;\3;\3;"+
		"\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\5>\u018d\n>\3?\3?\7?\u0191\n?"+
		"\f?\16?\u0194\13?\3?\3?\3@\3@\5@\u019a\n@\3@\3@\3A\3A\7A\u01a0\nA\fA\16"+
		"A\u01a3\13A\2\2B\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21\7\23\b\25\t\27\n\31"+
		"\13\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30\65\31"+
		"\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g"+
		"\62i\63k\64m\65o\66q\67s8u9w:y;{\2}<\177=\u0081>\3\2\t\t\2$$\62\62ddh"+
		"hppttvv\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\5\2$$))^^\4\2aac|\6\2"+
		"\62;C\\aac|\2\u01b0\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2}\3\2\2\2\2\177"+
		"\3\2\2\2\2\u0081\3\2\2\2\3\u0084\3\2\2\2\5\u008d\3\2\2\2\7\u008f\3\2\2"+
		"\2\t\u0092\3\2\2\2\13\u0098\3\2\2\2\r\u00a5\3\2\2\2\17\u00a7\3\2\2\2\21"+
		"\u00a9\3\2\2\2\23\u00b0\3\2\2\2\25\u00b5\3\2\2\2\27\u00ba\3\2\2\2\31\u00c2"+
		"\3\2\2\2\33\u00c4\3\2\2\2\35\u00cb\3\2\2\2\37\u00d3\3\2\2\2!\u00d9\3\2"+
		"\2\2#\u00dd\3\2\2\2%\u00e4\3\2\2\2\'\u00e9\3\2\2\2)\u00ee\3\2\2\2+\u00f3"+
		"\3\2\2\2-\u00f6\3\2\2\2/\u00fb\3\2\2\2\61\u0100\3\2\2\2\63\u0106\3\2\2"+
		"\2\65\u010e\3\2\2\2\67\u0114\3\2\2\29\u0117\3\2\2\2;\u011c\3\2\2\2=\u011f"+
		"\3\2\2\2?\u0124\3\2\2\2A\u0129\3\2\2\2C\u012c\3\2\2\2E\u012e\3\2\2\2G"+
		"\u0130\3\2\2\2I\u0132\3\2\2\2K\u0134\3\2\2\2M\u0136\3\2\2\2O\u0138\3\2"+
		"\2\2Q\u013a\3\2\2\2S\u013c\3\2\2\2U\u013e\3\2\2\2W\u0140\3\2\2\2Y\u0143"+
		"\3\2\2\2[\u0145\3\2\2\2]\u0148\3\2\2\2_\u014b\3\2\2\2a\u014e\3\2\2\2c"+
		"\u0151\3\2\2\2e\u0154\3\2\2\2g\u0156\3\2\2\2i\u015a\3\2\2\2k\u015e\3\2"+
		"\2\2m\u0162\3\2\2\2o\u0165\3\2\2\2q\u0172\3\2\2\2s\u0174\3\2\2\2u\u017c"+
		"\3\2\2\2w\u0181\3\2\2\2y\u0185\3\2\2\2{\u018c\3\2\2\2}\u018e\3\2\2\2\177"+
		"\u0197\3\2\2\2\u0081\u019d\3\2\2\2\u0083\u0085\7\17\2\2\u0084\u0083\3"+
		"\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\f\2\2\u0087"+
		"\4\3\2\2\2\u0088\u008e\t\2\2\2\u0089\u008a\7\u00e4\2\2\u008a\u008b\7\u20ae"+
		"\2\2\u008b\u008e\7\u2124\2\2\u008c\u008e\7^\2\2\u008d\u0088\3\2\2\2\u008d"+
		"\u0089\3\2\2\2\u008d\u008c\3\2\2\2\u008e\6\3\2\2\2\u008f\u0090\t\3\2\2"+
		"\u0090\b\3\2\2\2\u0091\u0093\t\4\2\2\u0092\u0091\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\b\5\2\2\u0097\n\3\2\2\2\u0098\u009c\7%\2\2\u0099\u009b\n\5\2\2"+
		"\u009a\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d"+
		"\3\2\2\2\u009d\u00a1\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a2\5\3\2\2\u00a0"+
		"\u00a2\7\2\2\3\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a4\b\6\2\2\u00a4\f\3\2\2\2\u00a5\u00a6\7.\2\2\u00a6\16\3"+
		"\2\2\2\u00a7\u00a8\7=\2\2\u00a8\20\3\2\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab"+
		"\7v\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7p\2\2\u00ae"+
		"\u00af\7i\2\2\u00af\22\3\2\2\2\u00b0\u00b1\7e\2\2\u00b1\u00b2\7j\2\2\u00b2"+
		"\u00b3\7c\2\2\u00b3\u00b4\7t\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7d\2\2\u00b6"+
		"\u00b7\7q\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7n\2\2\u00b9\26\3\2\2\2\u00ba"+
		"\u00bb\7k\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7v\2\2\u00bd\30\3\2\2\2\u00be"+
		"\u00c3\5\27\f\2\u00bf\u00c3\5\25\13\2\u00c0\u00c3\5\23\n\2\u00c1\u00c3"+
		"\5\21\t\2\u00c2\u00be\3\2\2\2\u00c2\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2"+
		"\u00c2\u00c1\3\2\2\2\u00c3\32\3\2\2\2\u00c4\u00c5\7r\2\2\u00c5\u00c6\7"+
		"c\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7t\2\2\u00c8\34\3\2\2\2\u00c9\u00cc"+
		"\5\31\r\2\u00ca\u00cc\5\33\16\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2"+
		"\2\u00cc\u00cf\3\2\2\2\u00cd\u00ce\7]\2\2\u00ce\u00d0\7_\2\2\u00cf\u00cd"+
		"\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\36\3\2\2\2\u00d3\u00d4\7d\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7i\2\2\u00d6"+
		"\u00d7\7k\2\2\u00d7\u00d8\7p\2\2\u00d8 \3\2\2\2\u00d9\u00da\7g\2\2\u00da"+
		"\u00db\7p\2\2\u00db\u00dc\7f\2\2\u00dc\"\3\2\2\2\u00dd\u00de\7t\2\2\u00de"+
		"\u00df\7g\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7w\2\2\u00e1\u00e2\7t\2\2"+
		"\u00e2\u00e3\7p\2\2\u00e3$\3\2\2\2\u00e4\u00e5\7e\2\2\u00e5\u00e6\7c\2"+
		"\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7n\2\2\u00e8&\3\2\2\2\u00e9\u00ea\7"+
		"g\2\2\u00ea\u00eb\7z\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7v\2\2\u00ed("+
		"\3\2\2\2\u00ee\u00ef\7u\2\2\u00ef\u00f0\7m\2\2\u00f0\u00f1\7k\2\2\u00f1"+
		"\u00f2\7r\2\2\u00f2*\3\2\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7u\2\2\u00f5"+
		",\3\2\2\2\u00f6\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7c\2\2\u00f9"+
		"\u00fa\7f\2\2\u00fa.\3\2\2\2\u00fb\u00fc\7h\2\2\u00fc\u00fd\7t\2\2\u00fd"+
		"\u00fe\7g\2\2\u00fe\u00ff\7g\2\2\u00ff\60\3\2\2\2\u0100\u0101\7r\2\2\u0101"+
		"\u0102\7t\2\2\u0102\u0103\7k\2\2\u0103\u0104\7p\2\2\u0104\u0105\7v\2\2"+
		"\u0105\62\3\2\2\2\u0106\u0107\7r\2\2\u0107\u0108\7t\2\2\u0108\u0109\7"+
		"k\2\2\u0109\u010a\7p\2\2\u010a\u010b\7v\2\2\u010b\u010c\7n\2\2\u010c\u010d"+
		"\7p\2\2\u010d\64\3\2\2\2\u010e\u010f\7y\2\2\u010f\u0110\7j\2\2\u0110\u0111"+
		"\7k\2\2\u0111\u0112\7n\2\2\u0112\u0113\7g\2\2\u0113\66\3\2\2\2\u0114\u0115"+
		"\7f\2\2\u0115\u0116\7q\2\2\u01168\3\2\2\2\u0117\u0118\7f\2\2\u0118\u0119"+
		"\7q\2\2\u0119\u011a\7p\2\2\u011a\u011b\7g\2\2\u011b:\3\2\2\2\u011c\u011d"+
		"\7k\2\2\u011d\u011e\7h\2\2\u011e<\3\2\2\2\u011f\u0120\7v\2\2\u0120\u0121"+
		"\7j\2\2\u0121\u0122\7g\2\2\u0122\u0123\7p\2\2\u0123>\3\2\2\2\u0124\u0125"+
		"\7g\2\2\u0125\u0126\7n\2\2\u0126\u0127\7u\2\2\u0127\u0128\7g\2\2\u0128"+
		"@\3\2\2\2\u0129\u012a\7h\2\2\u012a\u012b\7k\2\2\u012bB\3\2\2\2\u012c\u012d"+
		"\7*\2\2\u012dD\3\2\2\2\u012e\u012f\7+\2\2\u012fF\3\2\2\2\u0130\u0131\7"+
		"]\2\2\u0131H\3\2\2\2\u0132\u0133\7_\2\2\u0133J\3\2\2\2\u0134\u0135\7-"+
		"\2\2\u0135L\3\2\2\2\u0136\u0137\7/\2\2\u0137N\3\2\2\2\u0138\u0139\7,\2"+
		"\2\u0139P\3\2\2\2\u013a\u013b\7\61\2\2\u013bR\3\2\2\2\u013c\u013d\7\'"+
		"\2\2\u013dT\3\2\2\2\u013e\u013f\7@\2\2\u013fV\3\2\2\2\u0140\u0141\7@\2"+
		"\2\u0141\u0142\7?\2\2\u0142X\3\2\2\2\u0143\u0144\7>\2\2\u0144Z\3\2\2\2"+
		"\u0145\u0146\7>\2\2\u0146\u0147\7?\2\2\u0147\\\3\2\2\2\u0148\u0149\7?"+
		"\2\2\u0149\u014a\7?\2\2\u014a^\3\2\2\2\u014b\u014c\7#\2\2\u014c\u014d"+
		"\7?\2\2\u014d`\3\2\2\2\u014e\u014f\7(\2\2\u014f\u0150\7(\2\2\u0150b\3"+
		"\2\2\2\u0151\u0152\7~\2\2\u0152\u0153\7~\2\2\u0153d\3\2\2\2\u0154\u0155"+
		"\7#\2\2\u0155f\3\2\2\2\u0156\u0157\7n\2\2\u0157\u0158\7g\2\2\u0158\u0159"+
		"\7p\2\2\u0159h\3\2\2\2\u015a\u015b\7q\2\2\u015b\u015c\7t\2\2\u015c\u015d"+
		"\7f\2\2\u015dj\3\2\2\2\u015e\u015f\7e\2\2\u015f\u0160\7j\2\2\u0160\u0161"+
		"\7t\2\2\u0161l\3\2\2\2\u0162\u0163\7?\2\2\u0163n\3\2\2\2\u0164\u0166\5"+
		"\7\4\2\u0165\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0165\3\2\2\2\u0167"+
		"\u0168\3\2\2\2\u0168p\3\2\2\2\u0169\u016a\7v\2\2\u016a\u016b\7t\2\2\u016b"+
		"\u016c\7w\2\2\u016c\u0173\7g\2\2\u016d\u016e\7h\2\2\u016e\u016f\7c\2\2"+
		"\u016f\u0170\7n\2\2\u0170\u0171\7u\2\2\u0171\u0173\7g\2\2\u0172\u0169"+
		"\3\2\2\2\u0172\u016d\3\2\2\2\u0173r\3\2\2\2\u0174\u0175\7p\2\2\u0175\u0176"+
		"\7g\2\2\u0176\u0177\7y\2\2\u0177\u0178\7r\2\2\u0178\u0179\7c\2\2\u0179"+
		"\u017a\7k\2\2\u017a\u017b\7t\2\2\u017bt\3\2\2\2\u017c\u017d\7p\2\2\u017d"+
		"\u017e\7w\2\2\u017e\u017f\7n\2\2\u017f\u0180\7n\2\2\u0180v\3\2\2\2\u0181"+
		"\u0182\7h\2\2\u0182\u0183\7u\2\2\u0183\u0184\7v\2\2\u0184x\3\2\2\2\u0185"+
		"\u0186\7u\2\2\u0186\u0187\7p\2\2\u0187\u0188\7f\2\2\u0188z\3\2\2\2\u0189"+
		"\u018d\n\6\2\2\u018a\u018b\7^\2\2\u018b\u018d\5\5\3\2\u018c\u0189\3\2"+
		"\2\2\u018c\u018a\3\2\2\2\u018d|\3\2\2\2\u018e\u0192\7$\2\2\u018f\u0191"+
		"\5{>\2\u0190\u018f\3\2\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\u0195\3\2\2\2\u0194\u0192\3\2\2\2\u0195\u0196\7$"+
		"\2\2\u0196~\3\2\2\2\u0197\u0199\7)\2\2\u0198\u019a\5{>\2\u0199\u0198\3"+
		"\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019c\7)\2\2\u019c"+
		"\u0080\3\2\2\2\u019d\u01a1\t\7\2\2\u019e\u01a0\t\b\2\2\u019f\u019e\3\2"+
		"\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2"+
		"\u0082\3\2\2\2\u01a3\u01a1\3\2\2\2\21\2\u0084\u008d\u0094\u009c\u00a1"+
		"\u00c2\u00cb\u00d1\u0167\u0172\u018c\u0192\u0199\u01a1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}