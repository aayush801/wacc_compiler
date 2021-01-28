// Generated from ./BasicParser.g4 by ANTLR 4.9.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
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
	public static final int
		RULE_prog = 0, RULE_func = 1, RULE_stat = 2, RULE_assignLHS = 3, RULE_assignRHS = 4, 
		RULE_expr = 5, RULE_binaryOper = 6, RULE_unaryOper = 7, RULE_type = 8, 
		RULE_arrayElem = 9, RULE_array = 10, RULE_pairType = 11, RULE_pairElemType = 12, 
		RULE_pairElem = 13, RULE_param = 14, RULE_paramList = 15, RULE_argList = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "func", "stat", "assignLHS", "assignRHS", "expr", "binaryOper", 
			"unaryOper", "type", "arrayElem", "array", "pairType", "pairElemType", 
			"pairElem", "param", "paramList", "argList"
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

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			int _alt;
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				match(BEGIN);
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(35);
						func();
						}
						} 
					}
					setState(40);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(41);
				stat(0);
				setState(42);
				match(END);
				setState(43);
				match(EOF);
				}
				break;
			case OPEN_PARENTHESES:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case PLUS:
			case MINUS:
			case BOOLEAN:
			case STRING:
			case CHARACTER:
			case PAIR:
			case INTEGER:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				expr(0);
				setState(46);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			type();
			setState(51);
			match(IDENT);
			setState(52);
			match(OPEN_PARENTHESES);
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASE_TYPE) | (1L << ARRAY_TYPE) | (1L << PAIR_TYPE))) != 0)) {
				{
				setState(53);
				paramList();
				}
			}

			setState(56);
			match(CLOSE_PARENTHESES);
			setState(57);
			match(IS);
			setState(58);
			stat(0);
			setState(59);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKIP_STATEMENT() { return getToken(BasicParser.SKIP_STATEMENT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode EQUALS() { return getToken(BasicParser.EQUALS, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public TerminalNode PRINT_LINE() { return getToken(BasicParser.PRINT_LINE, 0); }
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode END_IF() { return getToken(BasicParser.END_IF, 0); }
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public TerminalNode END_WHILE() { return getToken(BasicParser.END_WHILE, 0); }
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode SEPERATOR() { return getToken(BasicParser.SEPERATOR, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP_STATEMENT:
				{
				setState(62);
				match(SKIP_STATEMENT);
				}
				break;
			case BASE_TYPE:
			case ARRAY_TYPE:
			case PAIR_TYPE:
				{
				setState(63);
				type();
				setState(64);
				match(IDENT);
				setState(65);
				match(EQUALS);
				setState(66);
				assignRHS();
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
			case IDENT:
				{
				setState(68);
				assignLHS();
				setState(69);
				match(EQUALS);
				setState(70);
				assignRHS();
				}
				break;
			case READ:
				{
				setState(72);
				match(READ);
				setState(73);
				assignLHS();
				}
				break;
			case FREE:
				{
				setState(74);
				match(FREE);
				setState(75);
				expr(0);
				}
				break;
			case RETURN:
				{
				setState(76);
				match(RETURN);
				setState(77);
				expr(0);
				}
				break;
			case EXIT:
				{
				setState(78);
				match(EXIT);
				setState(79);
				expr(0);
				}
				break;
			case PRINT:
				{
				setState(80);
				match(PRINT);
				setState(81);
				expr(0);
				}
				break;
			case PRINT_LINE:
				{
				setState(82);
				match(PRINT_LINE);
				setState(83);
				expr(0);
				}
				break;
			case IF:
				{
				setState(84);
				match(IF);
				setState(85);
				expr(0);
				setState(86);
				match(THEN);
				setState(87);
				stat(0);
				setState(88);
				match(ELSE);
				setState(89);
				stat(0);
				setState(90);
				match(END_IF);
				}
				break;
			case WHILE:
				{
				setState(92);
				match(WHILE);
				setState(93);
				expr(0);
				setState(94);
				match(DO);
				setState(95);
				stat(0);
				setState(96);
				match(END_WHILE);
				}
				break;
			case BEGIN:
				{
				setState(98);
				match(BEGIN);
				setState(99);
				stat(0);
				setState(100);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(104);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(105);
					match(SEPERATOR);
					setState(106);
					stat(2);
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignLHS);
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRHSContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TerminalNode CREATE_PAIR() { return getToken(BasicParser.CREATE_PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignRHS);
		int _la;
		try {
			setState(134);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESES:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case PLUS:
			case MINUS:
			case BOOLEAN:
			case STRING:
			case CHARACTER:
			case PAIR:
			case INTEGER:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				expr(0);
				}
				break;
			case OPEN_SQUARE_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				array();
				}
				break;
			case CREATE_PAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				match(CREATE_PAIR);
				setState(120);
				match(OPEN_PARENTHESES);
				setState(121);
				expr(0);
				setState(122);
				match(COMMA);
				setState(123);
				expr(0);
				setState(124);
				match(CLOSE_PARENTHESES);
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(127);
				match(CALL);
				setState(128);
				match(IDENT);
				setState(129);
				match(OPEN_PARENTHESES);
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << PLUS) | (1L << MINUS) | (1L << BOOLEAN) | (1L << STRING) | (1L << CHARACTER) | (1L << PAIR) | (1L << INTEGER) | (1L << IDENT))) != 0)) {
					{
					setState(130);
					argList();
					}
				}

				setState(133);
				match(CLOSE_PARENTHESES);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(BasicParser.INTEGER, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode BOOLEAN() { return getToken(BasicParser.BOOLEAN, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode CHARACTER() { return getToken(BasicParser.CHARACTER, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public BinaryOperContext binaryOper() {
			return getRuleContext(BinaryOperContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(137);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(140);
				match(INTEGER);
				}
				break;
			case 2:
				{
				setState(141);
				match(BOOLEAN);
				}
				break;
			case 3:
				{
				setState(142);
				match(PAIR);
				}
				break;
			case 4:
				{
				setState(143);
				match(IDENT);
				}
				break;
			case 5:
				{
				setState(144);
				match(STRING);
				}
				break;
			case 6:
				{
				setState(145);
				match(CHARACTER);
				}
				break;
			case 7:
				{
				setState(146);
				arrayElem();
				}
				break;
			case 8:
				{
				setState(147);
				match(OPEN_PARENTHESES);
				setState(148);
				expr(0);
				setState(149);
				match(CLOSE_PARENTHESES);
				}
				break;
			case 9:
				{
				setState(151);
				unaryOper();
				setState(152);
				expr(2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(162);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(156);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(157);
					binaryOper();
					setState(158);
					expr(2);
					}
					} 
				}
				setState(164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BinaryOperContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode DIVIDE() { return getToken(BasicParser.DIVIDE, 0); }
		public TerminalNode MULTIPLY() { return getToken(BasicParser.MULTIPLY, 0); }
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public TerminalNode GRE() { return getToken(BasicParser.GRE, 0); }
		public TerminalNode GR() { return getToken(BasicParser.GR, 0); }
		public TerminalNode LSE() { return getToken(BasicParser.LSE, 0); }
		public TerminalNode LS() { return getToken(BasicParser.LS, 0); }
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(BasicParser.NEQ, 0); }
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public BinaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBinaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryOperContext binaryOper() throws RecognitionException {
		BinaryOperContext _localctx = new BinaryOperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_binaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULTIPLY) | (1L << DIVIDE) | (1L << MOD) | (1L << GR) | (1L << GRE) | (1L << LS) | (1L << LSE) | (1L << EQ) | (1L << NEQ) | (1L << AND) | (1L << OR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode LENGTH() { return getToken(BasicParser.LENGTH, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << MINUS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BASE_TYPE() { return getToken(BasicParser.BASE_TYPE, 0); }
		public TerminalNode ARRAY_TYPE() { return getToken(BasicParser.ARRAY_TYPE, 0); }
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BASE_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				match(BASE_TYPE);
				}
				break;
			case ARRAY_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(ARRAY_TYPE);
				}
				break;
			case PAIR_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public List<TerminalNode> OPEN_SQUARE_BRACKET() { return getTokens(BasicParser.OPEN_SQUARE_BRACKET); }
		public TerminalNode OPEN_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.OPEN_SQUARE_BRACKET, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_SQUARE_BRACKET() { return getTokens(BasicParser.CLOSE_SQUARE_BRACKET); }
		public TerminalNode CLOSE_SQUARE_BRACKET(int i) {
			return getToken(BasicParser.CLOSE_SQUARE_BRACKET, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(IDENT);
			setState(179); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(175);
					match(OPEN_SQUARE_BRACKET);
					setState(176);
					expr(0);
					setState(177);
					match(CLOSE_SQUARE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(181); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode OPEN_SQUARE_BRACKET() { return getToken(BasicParser.OPEN_SQUARE_BRACKET, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_SQUARE_BRACKET() { return getToken(BasicParser.CLOSE_SQUARE_BRACKET, 0); }
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(OPEN_SQUARE_BRACKET);
			setState(184);
			expr(0);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(185);
				match(COMMA);
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << PLUS) | (1L << MINUS) | (1L << BOOLEAN) | (1L << STRING) | (1L << CHARACTER) | (1L << PAIR) | (1L << INTEGER) | (1L << IDENT))) != 0)) {
					{
					{
					setState(186);
					expr(0);
					}
					}
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(194);
			match(CLOSE_SQUARE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR_TYPE() { return getToken(BasicParser.PAIR_TYPE, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(PAIR_TYPE);
			setState(197);
			match(OPEN_PARENTHESES);
			setState(198);
			pairElemType();
			setState(199);
			match(COMMA);
			setState(200);
			pairElemType();
			setState(201);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public TerminalNode BASE_TYPE() { return getToken(BasicParser.BASE_TYPE, 0); }
		public TerminalNode ARRAY_TYPE() { return getToken(BasicParser.ARRAY_TYPE, 0); }
		public TerminalNode PAIR_TYPE() { return getToken(BasicParser.PAIR_TYPE, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pairElemType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASE_TYPE) | (1L << ARRAY_TYPE) | (1L << PAIR_TYPE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode PAIR_FIRST() { return getToken(BasicParser.PAIR_FIRST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PAIR_SECOND() { return getToken(BasicParser.PAIR_SECOND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pairElem);
		try {
			setState(209);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR_FIRST:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(PAIR_FIRST);
				setState(206);
				expr(0);
				}
				break;
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				match(PAIR_SECOND);
				setState(208);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			type();
			setState(212);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			param();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(215);
				match(COMMA);
				setState(216);
				param();
				}
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			expr(0);
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(223);
				match(COMMA);
				setState(224);
				expr(0);
				}
				}
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u00e9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\63\n"+
		"\2\3\3\3\3\3\3\3\3\5\39\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\5\4i\n\4\3\4\3\4\3\4\7\4n\n\4\f\4\16\4q\13\4\3\5\3\5\3\5\5\5v\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0086\n\6\3"+
		"\6\5\6\u0089\n\6\3\7\3\7\5\7\u008d\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u009d\n\7\3\7\3\7\3\7\3\7\7\7\u00a3\n\7\f"+
		"\7\16\7\u00a6\13\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\5\n\u00af\n\n\3\13\3\13"+
		"\3\13\3\13\3\13\6\13\u00b6\n\13\r\13\16\13\u00b7\3\f\3\f\3\f\3\f\7\f\u00be"+
		"\n\f\f\f\16\f\u00c1\13\f\5\f\u00c3\n\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\17\3\17\5\17\u00d4\n\17\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\7\21\u00dc\n\21\f\21\16\21\u00df\13\21\3\22\3\22\3\22\7\22"+
		"\u00e4\n\22\f\22\16\22\u00e7\13\22\3\22\2\4\6\f\23\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"\2\6\3\2\36\37\3\2\36*\4\2\32\35\37\37\5\2\3\3//"+
		"\67\67\2\u00ff\2\62\3\2\2\2\4\64\3\2\2\2\6h\3\2\2\2\bu\3\2\2\2\n\u0088"+
		"\3\2\2\2\f\u009c\3\2\2\2\16\u00a7\3\2\2\2\20\u00a9\3\2\2\2\22\u00ae\3"+
		"\2\2\2\24\u00b0\3\2\2\2\26\u00b9\3\2\2\2\30\u00c6\3\2\2\2\32\u00cd\3\2"+
		"\2\2\34\u00d3\3\2\2\2\36\u00d5\3\2\2\2 \u00d8\3\2\2\2\"\u00e0\3\2\2\2"+
		"$(\7\4\2\2%\'\5\4\3\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3\2\2"+
		"\2*(\3\2\2\2+,\5\6\4\2,-\7\5\2\2-.\7\2\2\3.\63\3\2\2\2/\60\5\f\7\2\60"+
		"\61\7\2\2\3\61\63\3\2\2\2\62$\3\2\2\2\62/\3\2\2\2\63\3\3\2\2\2\64\65\5"+
		"\22\n\2\65\66\7:\2\2\668\7\26\2\2\679\5 \21\28\67\3\2\2\289\3\2\2\29:"+
		"\3\2\2\2:;\7\27\2\2;<\7\n\2\2<=\5\6\4\2=>\7\5\2\2>\5\3\2\2\2?@\b\4\1\2"+
		"@i\7\t\2\2AB\5\22\n\2BC\7:\2\2CD\7+\2\2DE\5\n\6\2Ei\3\2\2\2FG\5\b\5\2"+
		"GH\7+\2\2HI\5\n\6\2Ii\3\2\2\2JK\7\13\2\2Ki\5\b\5\2LM\7\f\2\2Mi\5\f\7\2"+
		"NO\7\6\2\2Oi\5\f\7\2PQ\7\b\2\2Qi\5\f\7\2RS\7\r\2\2Si\5\f\7\2TU\7\16\2"+
		"\2Ui\5\f\7\2VW\7\22\2\2WX\5\f\7\2XY\7\23\2\2YZ\5\6\4\2Z[\7\24\2\2[\\\5"+
		"\6\4\2\\]\7\25\2\2]i\3\2\2\2^_\7\17\2\2_`\5\f\7\2`a\7\20\2\2ab\5\6\4\2"+
		"bc\7\21\2\2ci\3\2\2\2de\7\4\2\2ef\5\6\4\2fg\7\5\2\2gi\3\2\2\2h?\3\2\2"+
		"\2hA\3\2\2\2hF\3\2\2\2hJ\3\2\2\2hL\3\2\2\2hN\3\2\2\2hP\3\2\2\2hR\3\2\2"+
		"\2hT\3\2\2\2hV\3\2\2\2h^\3\2\2\2hd\3\2\2\2io\3\2\2\2jk\f\3\2\2kl\7-\2"+
		"\2ln\5\6\4\4mj\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2p\7\3\2\2\2qo\3\2"+
		"\2\2rv\7:\2\2sv\5\24\13\2tv\5\34\17\2ur\3\2\2\2us\3\2\2\2ut\3\2\2\2v\t"+
		"\3\2\2\2w\u0089\5\f\7\2x\u0089\5\26\f\2yz\7\63\2\2z{\7\26\2\2{|\5\f\7"+
		"\2|}\7,\2\2}~\5\f\7\2~\177\7\27\2\2\177\u0089\3\2\2\2\u0080\u0089\5\34"+
		"\17\2\u0081\u0082\7\7\2\2\u0082\u0083\7:\2\2\u0083\u0085\7\26\2\2\u0084"+
		"\u0086\5\"\22\2\u0085\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0087\u0089\7\27\2\2\u0088w\3\2\2\2\u0088x\3\2\2\2\u0088y\3\2\2"+
		"\2\u0088\u0080\3\2\2\2\u0088\u0081\3\2\2\2\u0089\13\3\2\2\2\u008a\u008c"+
		"\b\7\1\2\u008b\u008d\t\2\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u009d\78\2\2\u008f\u009d\7\60\2\2\u0090\u009d\7\64"+
		"\2\2\u0091\u009d\7:\2\2\u0092\u009d\7\61\2\2\u0093\u009d\7\62\2\2\u0094"+
		"\u009d\5\24\13\2\u0095\u0096\7\26\2\2\u0096\u0097\5\f\7\2\u0097\u0098"+
		"\7\27\2\2\u0098\u009d\3\2\2\2\u0099\u009a\5\20\t\2\u009a\u009b\5\f\7\4"+
		"\u009b\u009d\3\2\2\2\u009c\u008a\3\2\2\2\u009c\u008f\3\2\2\2\u009c\u0090"+
		"\3\2\2\2\u009c\u0091\3\2\2\2\u009c\u0092\3\2\2\2\u009c\u0093\3\2\2\2\u009c"+
		"\u0094\3\2\2\2\u009c\u0095\3\2\2\2\u009c\u0099\3\2\2\2\u009d\u00a4\3\2"+
		"\2\2\u009e\u009f\f\3\2\2\u009f\u00a0\5\16\b\2\u00a0\u00a1\5\f\7\4\u00a1"+
		"\u00a3\3\2\2\2\u00a2\u009e\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\r\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8"+
		"\t\3\2\2\u00a8\17\3\2\2\2\u00a9\u00aa\t\4\2\2\u00aa\21\3\2\2\2\u00ab\u00af"+
		"\7\3\2\2\u00ac\u00af\7/\2\2\u00ad\u00af\5\30\r\2\u00ae\u00ab\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af\23\3\2\2\2\u00b0\u00b5\7:\2\2"+
		"\u00b1\u00b2\7\30\2\2\u00b2\u00b3\5\f\7\2\u00b3\u00b4\7\31\2\2\u00b4\u00b6"+
		"\3\2\2\2\u00b5\u00b1\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\25\3\2\2\2\u00b9\u00ba\7\30\2\2\u00ba\u00c2\5\f\7"+
		"\2\u00bb\u00bf\7,\2\2\u00bc\u00be\5\f\7\2\u00bd\u00bc\3\2\2\2\u00be\u00c1"+
		"\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00bb\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c5\7\31\2\2\u00c5\27\3\2\2\2\u00c6\u00c7\7\67\2\2\u00c7"+
		"\u00c8\7\26\2\2\u00c8\u00c9\5\32\16\2\u00c9\u00ca\7,\2\2\u00ca\u00cb\5"+
		"\32\16\2\u00cb\u00cc\7\27\2\2\u00cc\31\3\2\2\2\u00cd\u00ce\t\5\2\2\u00ce"+
		"\33\3\2\2\2\u00cf\u00d0\7\65\2\2\u00d0\u00d4\5\f\7\2\u00d1\u00d2\7\66"+
		"\2\2\u00d2\u00d4\5\f\7\2\u00d3\u00cf\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\35\3\2\2\2\u00d5\u00d6\5\22\n\2\u00d6\u00d7\7:\2\2\u00d7\37\3\2\2\2\u00d8"+
		"\u00dd\5\36\20\2\u00d9\u00da\7,\2\2\u00da\u00dc\5\36\20\2\u00db\u00d9"+
		"\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de"+
		"!\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e5\5\f\7\2\u00e1\u00e2\7,\2\2\u00e2"+
		"\u00e4\5\f\7\2\u00e3\u00e1\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6#\3\2\2\2\u00e7\u00e5\3\2\2\2\24(\628ho"+
		"u\u0085\u0088\u008c\u009c\u00a4\u00ae\u00b7\u00bf\u00c2\u00d3\u00dd\u00e5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}