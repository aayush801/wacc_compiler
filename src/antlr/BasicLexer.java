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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2:\u0192\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\5\2}\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\6\5\u0086\n\5\r\5\16\5\u0087"+
		"\3\5\3\5\3\6\3\6\7\6\u008e\n\6\f\6\16\6\u0091\13\6\3\6\3\6\5\6\u0095\n"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7\u00aa\n\7\3\b\3\b\5\b\u00ae\n\b\3\b\3\b\6\b\u00b2\n\b\r\b"+
		"\16\b\u00b3\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3)\3)"+
		"\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3"+
		"\60\3\61\3\61\3\62\3\62\3\63\6\63\u014c\n\63\r\63\16\63\u014d\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\5\64\u0159\n\64\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\38\3"+
		"8\38\38\39\39\39\39\39\3:\3:\3:\5:\u0178\n:\3;\3;\7;\u017c\n;\f;\16;\u017f"+
		"\13;\3;\3;\3<\3<\7<\u0185\n<\f<\16<\u0188\13<\3<\3<\3=\3=\7=\u018e\n="+
		"\f=\16=\u0191\13=\2\2>\3\2\5\2\7\2\t\3\13\4\r\5\17\6\21\7\23\b\25\t\27"+
		"\n\31\13\33\f\35\r\37\16!\17#\20%\21\'\22)\23+\24-\25/\26\61\27\63\30"+
		"\65\31\67\329\33;\34=\35?\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60"+
		"e\61g\62i\63k\64m\65o\66q\67s\2u8w9y:\3\2\t\13\2$$\62\62^^ddhhppttvv\u201b"+
		"\u201b\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\5\2$$))^^\4\2aac|\6\2"+
		"\62;C\\aac|\2\u019c\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q"+
		"\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3|\3\2\2\2\5\u0080\3\2\2\2\7"+
		"\u0082\3\2\2\2\t\u0085\3\2\2\2\13\u008b\3\2\2\2\r\u00a9\3\2\2\2\17\u00ad"+
		"\3\2\2\2\21\u00b5\3\2\2\2\23\u00bb\3\2\2\2\25\u00bf\3\2\2\2\27\u00c6\3"+
		"\2\2\2\31\u00cb\3\2\2\2\33\u00d0\3\2\2\2\35\u00d5\3\2\2\2\37\u00d8\3\2"+
		"\2\2!\u00dd\3\2\2\2#\u00e2\3\2\2\2%\u00e8\3\2\2\2\'\u00f0\3\2\2\2)\u00f6"+
		"\3\2\2\2+\u00f9\3\2\2\2-\u00fe\3\2\2\2/\u0101\3\2\2\2\61\u0106\3\2\2\2"+
		"\63\u010b\3\2\2\2\65\u010e\3\2\2\2\67\u0110\3\2\2\29\u0112\3\2\2\2;\u0114"+
		"\3\2\2\2=\u0116\3\2\2\2?\u0118\3\2\2\2A\u011a\3\2\2\2C\u011c\3\2\2\2E"+
		"\u011e\3\2\2\2G\u0120\3\2\2\2I\u0122\3\2\2\2K\u0125\3\2\2\2M\u0127\3\2"+
		"\2\2O\u012a\3\2\2\2Q\u012d\3\2\2\2S\u0130\3\2\2\2U\u0133\3\2\2\2W\u0136"+
		"\3\2\2\2Y\u0138\3\2\2\2[\u013c\3\2\2\2]\u0140\3\2\2\2_\u0144\3\2\2\2a"+
		"\u0146\3\2\2\2c\u0148\3\2\2\2e\u014b\3\2\2\2g\u0158\3\2\2\2i\u015a\3\2"+
		"\2\2k\u0162\3\2\2\2m\u0167\3\2\2\2o\u016b\3\2\2\2q\u016f\3\2\2\2s\u0177"+
		"\3\2\2\2u\u0179\3\2\2\2w\u0182\3\2\2\2y\u018b\3\2\2\2{}\7\17\2\2|{\3\2"+
		"\2\2|}\3\2\2\2}~\3\2\2\2~\177\7\f\2\2\177\4\3\2\2\2\u0080\u0081\t\2\2"+
		"\2\u0081\6\3\2\2\2\u0082\u0083\t\3\2\2\u0083\b\3\2\2\2\u0084\u0086\t\4"+
		"\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\5\2\2\u008a\n\3\2\2\2"+
		"\u008b\u008f\7%\2\2\u008c\u008e\n\5\2\2\u008d\u008c\3\2\2\2\u008e\u0091"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0094\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0092\u0095\5\3\2\2\u0093\u0095\7\2\2\3\u0094\u0092\3\2"+
		"\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\b\6\2\2\u0097"+
		"\f\3\2\2\2\u0098\u0099\7k\2\2\u0099\u009a\7p\2\2\u009a\u00aa\7v\2\2\u009b"+
		"\u009c\7d\2\2\u009c\u009d\7q\2\2\u009d\u009e\7q\2\2\u009e\u00aa\7n\2\2"+
		"\u009f\u00a0\7e\2\2\u00a0\u00a1\7j\2\2\u00a1\u00a2\7c\2\2\u00a2\u00aa"+
		"\7t\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7t\2\2\u00a6"+
		"\u00a7\7k\2\2\u00a7\u00a8\7p\2\2\u00a8\u00aa\7i\2\2\u00a9\u0098\3\2\2"+
		"\2\u00a9\u009b\3\2\2\2\u00a9\u009f\3\2\2\2\u00a9\u00a3\3\2\2\2\u00aa\16"+
		"\3\2\2\2\u00ab\u00ae\5\r\7\2\u00ac\u00ae\5q9\2\u00ad\u00ab\3\2\2\2\u00ad"+
		"\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00b0\7]\2\2\u00b0\u00b2\7_\2"+
		"\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\20\3\2\2\2\u00b5\u00b6\7d\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7i\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7p\2\2\u00ba\22\3\2\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\u00bd\7p\2\2\u00bd\u00be\7f\2\2\u00be\24\3\2\2\2\u00bf"+
		"\u00c0\7t\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7v\2\2\u00c2\u00c3\7w\2\2"+
		"\u00c3\u00c4\7t\2\2\u00c4\u00c5\7p\2\2\u00c5\26\3\2\2\2\u00c6\u00c7\7"+
		"e\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7n\2\2\u00c9\u00ca\7n\2\2\u00ca\30"+
		"\3\2\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7z\2\2\u00cd\u00ce\7k\2\2\u00ce"+
		"\u00cf\7v\2\2\u00cf\32\3\2\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7m\2\2\u00d2"+
		"\u00d3\7k\2\2\u00d3\u00d4\7r\2\2\u00d4\34\3\2\2\2\u00d5\u00d6\7k\2\2\u00d6"+
		"\u00d7\7u\2\2\u00d7\36\3\2\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7g\2\2\u00da"+
		"\u00db\7c\2\2\u00db\u00dc\7f\2\2\u00dc \3\2\2\2\u00dd\u00de\7h\2\2\u00de"+
		"\u00df\7t\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7g\2\2\u00e1\"\3\2\2\2\u00e2"+
		"\u00e3\7r\2\2\u00e3\u00e4\7t\2\2\u00e4\u00e5\7k\2\2\u00e5\u00e6\7p\2\2"+
		"\u00e6\u00e7\7v\2\2\u00e7$\3\2\2\2\u00e8\u00e9\7r\2\2\u00e9\u00ea\7t\2"+
		"\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee"+
		"\7n\2\2\u00ee\u00ef\7p\2\2\u00ef&\3\2\2\2\u00f0\u00f1\7y\2\2\u00f1\u00f2"+
		"\7j\2\2\u00f2\u00f3\7k\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7g\2\2\u00f5"+
		"(\3\2\2\2\u00f6\u00f7\7f\2\2\u00f7\u00f8\7q\2\2\u00f8*\3\2\2\2\u00f9\u00fa"+
		"\7f\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7g\2\2\u00fd"+
		",\3\2\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7h\2\2\u0100.\3\2\2\2\u0101\u0102"+
		"\7v\2\2\u0102\u0103\7j\2\2\u0103\u0104\7g\2\2\u0104\u0105\7p\2\2\u0105"+
		"\60\3\2\2\2\u0106\u0107\7g\2\2\u0107\u0108\7n\2\2\u0108\u0109\7u\2\2\u0109"+
		"\u010a\7g\2\2\u010a\62\3\2\2\2\u010b\u010c\7h\2\2\u010c\u010d\7k\2\2\u010d"+
		"\64\3\2\2\2\u010e\u010f\7*\2\2\u010f\66\3\2\2\2\u0110\u0111\7+\2\2\u0111"+
		"8\3\2\2\2\u0112\u0113\7]\2\2\u0113:\3\2\2\2\u0114\u0115\7_\2\2\u0115<"+
		"\3\2\2\2\u0116\u0117\7-\2\2\u0117>\3\2\2\2\u0118\u0119\7/\2\2\u0119@\3"+
		"\2\2\2\u011a\u011b\7,\2\2\u011bB\3\2\2\2\u011c\u011d\7\61\2\2\u011dD\3"+
		"\2\2\2\u011e\u011f\7\'\2\2\u011fF\3\2\2\2\u0120\u0121\7@\2\2\u0121H\3"+
		"\2\2\2\u0122\u0123\7@\2\2\u0123\u0124\7?\2\2\u0124J\3\2\2\2\u0125\u0126"+
		"\7>\2\2\u0126L\3\2\2\2\u0127\u0128\7>\2\2\u0128\u0129\7?\2\2\u0129N\3"+
		"\2\2\2\u012a\u012b\7?\2\2\u012b\u012c\7?\2\2\u012cP\3\2\2\2\u012d\u012e"+
		"\7#\2\2\u012e\u012f\7?\2\2\u012fR\3\2\2\2\u0130\u0131\7(\2\2\u0131\u0132"+
		"\7(\2\2\u0132T\3\2\2\2\u0133\u0134\7~\2\2\u0134\u0135\7~\2\2\u0135V\3"+
		"\2\2\2\u0136\u0137\7#\2\2\u0137X\3\2\2\2\u0138\u0139\7n\2\2\u0139\u013a"+
		"\7g\2\2\u013a\u013b\7p\2\2\u013bZ\3\2\2\2\u013c\u013d\7q\2\2\u013d\u013e"+
		"\7t\2\2\u013e\u013f\7f\2\2\u013f\\\3\2\2\2\u0140\u0141\7e\2\2\u0141\u0142"+
		"\7j\2\2\u0142\u0143\7t\2\2\u0143^\3\2\2\2\u0144\u0145\7?\2\2\u0145`\3"+
		"\2\2\2\u0146\u0147\7.\2\2\u0147b\3\2\2\2\u0148\u0149\7=\2\2\u0149d\3\2"+
		"\2\2\u014a\u014c\5\7\4\2\u014b\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014ef\3\2\2\2\u014f\u0150\7v\2\2\u0150"+
		"\u0151\7t\2\2\u0151\u0152\7w\2\2\u0152\u0159\7g\2\2\u0153\u0154\7h\2\2"+
		"\u0154\u0155\7c\2\2\u0155\u0156\7n\2\2\u0156\u0157\7u\2\2\u0157\u0159"+
		"\7g\2\2\u0158\u014f\3\2\2\2\u0158\u0153\3\2\2\2\u0159h\3\2\2\2\u015a\u015b"+
		"\7p\2\2\u015b\u015c\7g\2\2\u015c\u015d\7y\2\2\u015d\u015e\7r\2\2\u015e"+
		"\u015f\7c\2\2\u015f\u0160\7k\2\2\u0160\u0161\7t\2\2\u0161j\3\2\2\2\u0162"+
		"\u0163\7p\2\2\u0163\u0164\7w\2\2\u0164\u0165\7n\2\2\u0165\u0166\7n\2\2"+
		"\u0166l\3\2\2\2\u0167\u0168\7h\2\2\u0168\u0169\7u\2\2\u0169\u016a\7v\2"+
		"\2\u016an\3\2\2\2\u016b\u016c\7u\2\2\u016c\u016d\7p\2\2\u016d\u016e\7"+
		"f\2\2\u016ep\3\2\2\2\u016f\u0170\7r\2\2\u0170\u0171\7c\2\2\u0171\u0172"+
		"\7k\2\2\u0172\u0173\7t\2\2\u0173r\3\2\2\2\u0174\u0178\n\6\2\2\u0175\u0176"+
		"\7^\2\2\u0176\u0178\5\5\3\2\u0177\u0174\3\2\2\2\u0177\u0175\3\2\2\2\u0178"+
		"t\3\2\2\2\u0179\u017d\7$\2\2\u017a\u017c\5s:\2\u017b\u017a\3\2\2\2\u017c"+
		"\u017f\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u0180\3\2"+
		"\2\2\u017f\u017d\3\2\2\2\u0180\u0181\7$\2\2\u0181v\3\2\2\2\u0182\u0186"+
		"\7)\2\2\u0183\u0185\5s:\2\u0184\u0183\3\2\2\2\u0185\u0188\3\2\2\2\u0186"+
		"\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0189\3\2\2\2\u0188\u0186\3\2"+
		"\2\2\u0189\u018a\7)\2\2\u018ax\3\2\2\2\u018b\u018f\t\7\2\2\u018c\u018e"+
		"\t\b\2\2\u018d\u018c\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2\2\2\u018f"+
		"\u0190\3\2\2\2\u0190z\3\2\2\2\u0191\u018f\3\2\2\2\20\2|\u0087\u008f\u0094"+
		"\u00a9\u00ad\u00b3\u014d\u0158\u0177\u017d\u0186\u018f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}