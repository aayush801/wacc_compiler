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
		LSE=10, EQ=11, NEQ=12, AND=13, OR=14, NOT=15, LENGTH=16, ORD=17, CHR=18, 
		OPEN_PARENTHESES=19, CLOSE_PARENTHESES=20, OPEN_SQUARE_BRACKET=21, CLOSE_SQUARE_BRACKET=22, 
		BEGIN=23, END=24, IS=25, SKIP_STATEMENT=26, READ=27, FREE=28, RETURN=29, 
		EXIT=30, PRINT=31, PRINT_LINE=32, SEPERATOR=33, CALL=34, PAIR_TYPE=35, 
		INTEGER_TYPE=36, BOOLEAN_TYPE=37, CHAR_TYPE=38, STRING_TYPE=39, ARRAY_TYPE=40, 
		WHILE=41, DO=42, END_WHILE=43, IF=44, THEN=45, ELSE=46, END_IF=47, BASE_TYPE=48, 
		INTEGER=49, BOOLEAN=50, STRING=51, CHARACTER=52, CREATE_PAIR=53, PAIR=54, 
		PAIR_FIRST=55, PAIR_SECOND=56, IDENT=57, COMMENT=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOL", "ESCAPED_CHAR", "COMMA", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
			"MOD", "GR", "GRE", "LS", "LSE", "EQ", "NEQ", "AND", "OR", "NOT", "LENGTH", 
			"ORD", "CHR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", 
			"CLOSE_SQUARE_BRACKET", "BEGIN", "END", "IS", "SKIP_STATEMENT", "READ", 
			"FREE", "RETURN", "EXIT", "PRINT", "PRINT_LINE", "SEPERATOR", "CALL", 
			"PAIR_TYPE", "INTEGER_TYPE", "BOOLEAN_TYPE", "CHAR_TYPE", "STRING_TYPE", 
			"ARRAY_TYPE", "WHILE", "DO", "END_WHILE", "IF", "THEN", "ELSE", "END_IF", 
			"BASE_TYPE", "DIGIT", "INTSIGN", "INTEGER", "BOOLEAN", "CHAR", "STRING", 
			"CHARACTER", "CREATE_PAIR", "PAIR", "PAIR_FIRST", "PAIR_SECOND", "IDENT", 
			"COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'>='", "'<'", 
			"'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'len'", "'ord'", "'chr'", 
			"'('", "')'", "'['", "']'", "'begin'", "'end'", "'is'", "'skip'", "'read'", 
			"'free'", "'return'", "'exit'", "'print'", "'println'", "';'", "'call'", 
			"'pair'", "'int'", "'bool'", "'char'", "'string'", null, "'while'", "'do'", 
			"'done'", "'if'", "'then'", "'else'", "'fi'", null, null, null, null, 
			null, "'newpair'", "'null'", "'fst'", "'snd'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMA", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "MOD", "GR", "GRE", 
			"LS", "LSE", "EQ", "NEQ", "AND", "OR", "NOT", "LENGTH", "ORD", "CHR", 
			"OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_SQUARE_BRACKET", "CLOSE_SQUARE_BRACKET", 
			"BEGIN", "END", "IS", "SKIP_STATEMENT", "READ", "FREE", "RETURN", "EXIT", 
			"PRINT", "PRINT_LINE", "SEPERATOR", "CALL", "PAIR_TYPE", "INTEGER_TYPE", 
			"BOOLEAN_TYPE", "CHAR_TYPE", "STRING_TYPE", "ARRAY_TYPE", "WHILE", "DO", 
			"END_WHILE", "IF", "THEN", "ELSE", "END_IF", "BASE_TYPE", "INTEGER", 
			"BOOLEAN", "STRING", "CHARACTER", "CREATE_PAIR", "PAIR", "PAIR_FIRST", 
			"PAIR_SECOND", "IDENT", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u0190\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3+\3+"+
		"\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3\63"+
		"\5\63\u013a\n\63\3\64\3\64\3\65\3\65\5\65\u0140\n\65\3\66\5\66\u0143\n"+
		"\66\3\66\6\66\u0146\n\66\r\66\16\66\u0147\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\3\67\3\67\3\67\5\67\u0153\n\67\38\38\38\58\u0158\n8\39\39\79\u015c\n"+
		"9\f9\169\u015f\139\39\39\3:\3:\7:\u0165\n:\f:\16:\u0168\13:\3:\3:\3;\3"+
		";\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3?\3?\7?\u0183"+
		"\n?\f?\16?\u0186\13?\3@\3@\7@\u018a\n@\f@\16@\u018d\13@\3@\3@\2\2A\3\2"+
		"\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17"+
		"!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36"+
		"?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[-]._/a\60c\61e\62g\2i\2k\63m\64o\2q\65"+
		"s\66u\67w8y9{:};\177<\3\2\b\13\2$$\62\62^^ddhhppttvv\u201b\u201b\3\2\62"+
		";\5\2$$))^^\4\2aac|\6\2\62;C\\aac|\3\2\f\f\2\u0196\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2"+
		"\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2"+
		"\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]"+
		"\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2k\3\2\2\2\2m\3\2"+
		"\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2"+
		"\2}\3\2\2\2\2\177\3\2\2\2\3\u0081\3\2\2\2\5\u0083\3\2\2\2\7\u0085\3\2"+
		"\2\2\t\u0087\3\2\2\2\13\u0089\3\2\2\2\r\u008b\3\2\2\2\17\u008d\3\2\2\2"+
		"\21\u008f\3\2\2\2\23\u0091\3\2\2\2\25\u0093\3\2\2\2\27\u0096\3\2\2\2\31"+
		"\u0098\3\2\2\2\33\u009b\3\2\2\2\35\u009e\3\2\2\2\37\u00a1\3\2\2\2!\u00a4"+
		"\3\2\2\2#\u00a7\3\2\2\2%\u00a9\3\2\2\2\'\u00ad\3\2\2\2)\u00b1\3\2\2\2"+
		"+\u00b5\3\2\2\2-\u00b7\3\2\2\2/\u00b9\3\2\2\2\61\u00bb\3\2\2\2\63\u00bd"+
		"\3\2\2\2\65\u00c3\3\2\2\2\67\u00c7\3\2\2\29\u00ca\3\2\2\2;\u00cf\3\2\2"+
		"\2=\u00d4\3\2\2\2?\u00d9\3\2\2\2A\u00e0\3\2\2\2C\u00e5\3\2\2\2E\u00eb"+
		"\3\2\2\2G\u00f3\3\2\2\2I\u00f5\3\2\2\2K\u00fa\3\2\2\2M\u00ff\3\2\2\2O"+
		"\u0103\3\2\2\2Q\u0108\3\2\2\2S\u010d\3\2\2\2U\u0114\3\2\2\2W\u0117\3\2"+
		"\2\2Y\u011d\3\2\2\2[\u0120\3\2\2\2]\u0125\3\2\2\2_\u0128\3\2\2\2a\u012d"+
		"\3\2\2\2c\u0132\3\2\2\2e\u0139\3\2\2\2g\u013b\3\2\2\2i\u013f\3\2\2\2k"+
		"\u0142\3\2\2\2m\u0152\3\2\2\2o\u0157\3\2\2\2q\u0159\3\2\2\2s\u0162\3\2"+
		"\2\2u\u016b\3\2\2\2w\u0173\3\2\2\2y\u0178\3\2\2\2{\u017c\3\2\2\2}\u0180"+
		"\3\2\2\2\177\u0187\3\2\2\2\u0081\u0082\7\f\2\2\u0082\4\3\2\2\2\u0083\u0084"+
		"\t\2\2\2\u0084\6\3\2\2\2\u0085\u0086\7.\2\2\u0086\b\3\2\2\2\u0087\u0088"+
		"\7-\2\2\u0088\n\3\2\2\2\u0089\u008a\7/\2\2\u008a\f\3\2\2\2\u008b\u008c"+
		"\7,\2\2\u008c\16\3\2\2\2\u008d\u008e\7\61\2\2\u008e\20\3\2\2\2\u008f\u0090"+
		"\7\'\2\2\u0090\22\3\2\2\2\u0091\u0092\7@\2\2\u0092\24\3\2\2\2\u0093\u0094"+
		"\7@\2\2\u0094\u0095\7?\2\2\u0095\26\3\2\2\2\u0096\u0097\7>\2\2\u0097\30"+
		"\3\2\2\2\u0098\u0099\7>\2\2\u0099\u009a\7?\2\2\u009a\32\3\2\2\2\u009b"+
		"\u009c\7?\2\2\u009c\u009d\7?\2\2\u009d\34\3\2\2\2\u009e\u009f\7#\2\2\u009f"+
		"\u00a0\7?\2\2\u00a0\36\3\2\2\2\u00a1\u00a2\7(\2\2\u00a2\u00a3\7(\2\2\u00a3"+
		" \3\2\2\2\u00a4\u00a5\7~\2\2\u00a5\u00a6\7~\2\2\u00a6\"\3\2\2\2\u00a7"+
		"\u00a8\7#\2\2\u00a8$\3\2\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7g\2\2\u00ab"+
		"\u00ac\7p\2\2\u00ac&\3\2\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7t\2\2\u00af"+
		"\u00b0\7f\2\2\u00b0(\3\2\2\2\u00b1\u00b2\7e\2\2\u00b2\u00b3\7j\2\2\u00b3"+
		"\u00b4\7t\2\2\u00b4*\3\2\2\2\u00b5\u00b6\7*\2\2\u00b6,\3\2\2\2\u00b7\u00b8"+
		"\7+\2\2\u00b8.\3\2\2\2\u00b9\u00ba\7]\2\2\u00ba\60\3\2\2\2\u00bb\u00bc"+
		"\7_\2\2\u00bc\62\3\2\2\2\u00bd\u00be\7d\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0"+
		"\7i\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7p\2\2\u00c2\64\3\2\2\2\u00c3\u00c4"+
		"\7g\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7f\2\2\u00c6\66\3\2\2\2\u00c7\u00c8"+
		"\7k\2\2\u00c8\u00c9\7u\2\2\u00c98\3\2\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc"+
		"\7m\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7r\2\2\u00ce:\3\2\2\2\u00cf\u00d0"+
		"\7t\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7f\2\2\u00d3"+
		"<\3\2\2\2\u00d4\u00d5\7h\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7g\2\2\u00d7"+
		"\u00d8\7g\2\2\u00d8>\3\2\2\2\u00d9\u00da\7t\2\2\u00da\u00db\7g\2\2\u00db"+
		"\u00dc\7v\2\2\u00dc\u00dd\7w\2\2\u00dd\u00de\7t\2\2\u00de\u00df\7p\2\2"+
		"\u00df@\3\2\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7z\2\2\u00e2\u00e3\7k\2"+
		"\2\u00e3\u00e4\7v\2\2\u00e4B\3\2\2\2\u00e5\u00e6\7r\2\2\u00e6\u00e7\7"+
		"t\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7v\2\2\u00eaD"+
		"\3\2\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7k\2\2\u00ee"+
		"\u00ef\7p\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7p\2\2"+
		"\u00f2F\3\2\2\2\u00f3\u00f4\7=\2\2\u00f4H\3\2\2\2\u00f5\u00f6\7e\2\2\u00f6"+
		"\u00f7\7c\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9\7n\2\2\u00f9J\3\2\2\2\u00fa"+
		"\u00fb\7r\2\2\u00fb\u00fc\7c\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7t\2\2"+
		"\u00feL\3\2\2\2\u00ff\u0100\7k\2\2\u0100\u0101\7p\2\2\u0101\u0102\7v\2"+
		"\2\u0102N\3\2\2\2\u0103\u0104\7d\2\2\u0104\u0105\7q\2\2\u0105\u0106\7"+
		"q\2\2\u0106\u0107\7n\2\2\u0107P\3\2\2\2\u0108\u0109\7e\2\2\u0109\u010a"+
		"\7j\2\2\u010a\u010b\7c\2\2\u010b\u010c\7t\2\2\u010cR\3\2\2\2\u010d\u010e"+
		"\7u\2\2\u010e\u010f\7v\2\2\u010f\u0110\7t\2\2\u0110\u0111\7k\2\2\u0111"+
		"\u0112\7p\2\2\u0112\u0113\7i\2\2\u0113T\3\2\2\2\u0114\u0115\5\61\31\2"+
		"\u0115\u0116\5/\30\2\u0116V\3\2\2\2\u0117\u0118\7y\2\2\u0118\u0119\7j"+
		"\2\2\u0119\u011a\7k\2\2\u011a\u011b\7n\2\2\u011b\u011c\7g\2\2\u011cX\3"+
		"\2\2\2\u011d\u011e\7f\2\2\u011e\u011f\7q\2\2\u011fZ\3\2\2\2\u0120\u0121"+
		"\7f\2\2\u0121\u0122\7q\2\2\u0122\u0123\7p\2\2\u0123\u0124\7g\2\2\u0124"+
		"\\\3\2\2\2\u0125\u0126\7k\2\2\u0126\u0127\7h\2\2\u0127^\3\2\2\2\u0128"+
		"\u0129\7v\2\2\u0129\u012a\7j\2\2\u012a\u012b\7g\2\2\u012b\u012c\7p\2\2"+
		"\u012c`\3\2\2\2\u012d\u012e\7g\2\2\u012e\u012f\7n\2\2\u012f\u0130\7u\2"+
		"\2\u0130\u0131\7g\2\2\u0131b\3\2\2\2\u0132\u0133\7h\2\2\u0133\u0134\7"+
		"k\2\2\u0134d\3\2\2\2\u0135\u013a\5M\'\2\u0136\u013a\5O(\2\u0137\u013a"+
		"\5Q)\2\u0138\u013a\5S*\2\u0139\u0135\3\2\2\2\u0139\u0136\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013af\3\2\2\2\u013b\u013c\t\3\2\2"+
		"\u013ch\3\2\2\2\u013d\u0140\5\t\5\2\u013e\u0140\5\13\6\2\u013f\u013d\3"+
		"\2\2\2\u013f\u013e\3\2\2\2\u0140j\3\2\2\2\u0141\u0143\5i\65\2\u0142\u0141"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0146\5g\64\2\u0145"+
		"\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2"+
		"\2\2\u0148l\3\2\2\2\u0149\u014a\7v\2\2\u014a\u014b\7t\2\2\u014b\u014c"+
		"\7w\2\2\u014c\u0153\7g\2\2\u014d\u014e\7h\2\2\u014e\u014f\7c\2\2\u014f"+
		"\u0150\7n\2\2\u0150\u0151\7u\2\2\u0151\u0153\7g\2\2\u0152\u0149\3\2\2"+
		"\2\u0152\u014d\3\2\2\2\u0153n\3\2\2\2\u0154\u0158\n\4\2\2\u0155\u0156"+
		"\7^\2\2\u0156\u0158\5\5\3\2\u0157\u0154\3\2\2\2\u0157\u0155\3\2\2\2\u0158"+
		"p\3\2\2\2\u0159\u015d\7$\2\2\u015a\u015c\5o8\2\u015b\u015a\3\2\2\2\u015c"+
		"\u015f\3\2\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u0160\3\2"+
		"\2\2\u015f\u015d\3\2\2\2\u0160\u0161\7$\2\2\u0161r\3\2\2\2\u0162\u0166"+
		"\7)\2\2\u0163\u0165\5o8\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2\2\2\u0166"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0169\3\2\2\2\u0168\u0166\3\2"+
		"\2\2\u0169\u016a\7)\2\2\u016at\3\2\2\2\u016b\u016c\7p\2\2\u016c\u016d"+
		"\7g\2\2\u016d\u016e\7y\2\2\u016e\u016f\7r\2\2\u016f\u0170\7c\2\2\u0170"+
		"\u0171\7k\2\2\u0171\u0172\7t\2\2\u0172v\3\2\2\2\u0173\u0174\7p\2\2\u0174"+
		"\u0175\7w\2\2\u0175\u0176\7n\2\2\u0176\u0177\7n\2\2\u0177x\3\2\2\2\u0178"+
		"\u0179\7h\2\2\u0179\u017a\7u\2\2\u017a\u017b\7v\2\2\u017bz\3\2\2\2\u017c"+
		"\u017d\7u\2\2\u017d\u017e\7p\2\2\u017e\u017f\7f\2\2\u017f|\3\2\2\2\u0180"+
		"\u0184\t\5\2\2\u0181\u0183\t\6\2\2\u0182\u0181\3\2\2\2\u0183\u0186\3\2"+
		"\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185~\3\2\2\2\u0186\u0184"+
		"\3\2\2\2\u0187\u018b\7%\2\2\u0188\u018a\n\7\2\2\u0189\u0188\3\2\2\2\u018a"+
		"\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018e\3\2"+
		"\2\2\u018d\u018b\3\2\2\2\u018e\u018f\5\3\2\2\u018f\u0080\3\2\2\2\r\2\u0139"+
		"\u013f\u0142\u0147\u0152\u0157\u015d\u0166\u0184\u018b\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}