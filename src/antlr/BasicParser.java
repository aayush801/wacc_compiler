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
		COMMA=1, PLUS=2, MINUS=3, MULTIPLY=4, DIVIDE=5, MOD=6, GR=7, GRE=8, LS=9, 
		LSE=10, EQ=11, NEQ=12, AND=13, OR=14, EQUALS=15, NOT=16, LENGTH=17, ORD=18, 
		CHR=19, OPEN_PARENTHESES=20, CLOSE_PARENTHESES=21, OPEN_SQUARE_BRACKET=22, 
		CLOSE_SQUARE_BRACKET=23, BEGIN=24, END=25, IS=26, SKIP_STATEMENT=27, READ=28, 
		FREE=29, RETURN=30, EXIT=31, PRINT=32, PRINT_LINE=33, SEPERATOR=34, CALL=35, 
		TYPE=36, PAIR_TYPE=37, BASE_TYPE=38, ARRAY_TYPE=39, WHILE=40, DO=41, END_WHILE=42, 
		IF=43, THEN=44, ELSE=45, END_IF=46, INTEGER=47, BOOLEAN=48, STRING=49, 
		CHARACTER=50, CREATE_PAIR=51, PAIR=52, PAIR_FIRST=53, PAIR_SECOND=54, 
		IDENT=55, COMMENT=56, WS=57;
	public static final int
		RULE_binaryOper = 0, RULE_unaryOper = 1, RULE_stat = 2, RULE_assignLHS = 3, 
		RULE_assignRHS = 4, RULE_expr = 5, RULE_arrayElem = 6, RULE_array = 7, 
		RULE_pairType = 8, RULE_pairElemType = 9, RULE_pairElem = 10, RULE_prog = 11, 
		RULE_func = 12, RULE_param = 13, RULE_paramList = 14, RULE_argList = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"binaryOper", "unaryOper", "stat", "assignLHS", "assignRHS", "expr", 
			"arrayElem", "array", "pairType", "pairElemType", "pairElem", "prog", 
			"func", "param", "paramList", "argList"
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
		enterRule(_localctx, 0, RULE_binaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
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
		enterRule(_localctx, 2, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR))) != 0)) ) {
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

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKIP_STATEMENT() { return getToken(BasicParser.SKIP_STATEMENT, 0); }
		public TerminalNode TYPE() { return getToken(BasicParser.TYPE, 0); }
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
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP_STATEMENT:
				{
				setState(37);
				match(SKIP_STATEMENT);
				}
				break;
			case TYPE:
				{
				setState(38);
				match(TYPE);
				setState(39);
				match(IDENT);
				setState(40);
				match(EQUALS);
				setState(41);
				assignRHS();
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
			case IDENT:
				{
				setState(42);
				assignLHS();
				setState(43);
				match(EQUALS);
				setState(44);
				assignRHS();
				}
				break;
			case READ:
				{
				setState(46);
				match(READ);
				setState(47);
				assignLHS();
				}
				break;
			case FREE:
				{
				setState(48);
				match(FREE);
				setState(49);
				expr(0);
				}
				break;
			case RETURN:
				{
				setState(50);
				match(RETURN);
				setState(51);
				expr(0);
				}
				break;
			case EXIT:
				{
				setState(52);
				match(EXIT);
				setState(53);
				expr(0);
				}
				break;
			case PRINT:
				{
				setState(54);
				match(PRINT);
				setState(55);
				expr(0);
				}
				break;
			case PRINT_LINE:
				{
				setState(56);
				match(PRINT_LINE);
				setState(57);
				expr(0);
				}
				break;
			case IF:
				{
				setState(58);
				match(IF);
				setState(59);
				expr(0);
				setState(60);
				match(THEN);
				setState(61);
				stat(0);
				setState(62);
				match(ELSE);
				setState(63);
				stat(0);
				setState(64);
				match(END_IF);
				}
				break;
			case WHILE:
				{
				setState(66);
				match(WHILE);
				setState(67);
				expr(0);
				setState(68);
				match(DO);
				setState(69);
				stat(0);
				setState(70);
				match(END_WHILE);
				}
				break;
			case BEGIN:
				{
				setState(72);
				match(BEGIN);
				setState(73);
				stat(0);
				setState(74);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(78);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(79);
					match(SEPERATOR);
					setState(80);
					stat(2);
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
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
		try {
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case OPEN_PARENTHESES:
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case CHARACTER:
			case PAIR:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				expr(0);
				}
				break;
			case OPEN_SQUARE_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				array();
				}
				break;
			case CREATE_PAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				match(CREATE_PAIR);
				setState(94);
				match(OPEN_PARENTHESES);
				setState(95);
				expr(0);
				setState(96);
				match(COMMA);
				setState(97);
				expr(0);
				setState(98);
				match(CLOSE_PARENTHESES);
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 4);
				{
				setState(100);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(101);
				match(CALL);
				setState(102);
				match(IDENT);
				setState(103);
				match(OPEN_PARENTHESES);
				setState(104);
				argList();
				setState(105);
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
		public TerminalNode BOOLEAN() { return getToken(BasicParser.BOOLEAN, 0); }
		public TerminalNode CHARACTER() { return getToken(BasicParser.CHARACTER, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(110);
				match(INTEGER);
				}
				break;
			case 2:
				{
				setState(111);
				match(BOOLEAN);
				}
				break;
			case 3:
				{
				setState(112);
				match(CHARACTER);
				}
				break;
			case 4:
				{
				setState(113);
				match(STRING);
				}
				break;
			case 5:
				{
				setState(114);
				match(PAIR);
				}
				break;
			case 6:
				{
				setState(115);
				match(IDENT);
				}
				break;
			case 7:
				{
				setState(116);
				arrayElem();
				}
				break;
			case 8:
				{
				setState(117);
				unaryOper();
				setState(118);
				expr(3);
				}
				break;
			case 9:
				{
				setState(120);
				match(OPEN_PARENTHESES);
				setState(121);
				expr(0);
				setState(122);
				match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(126);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(127);
					binaryOper();
					setState(128);
					expr(3);
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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
		enterRule(_localctx, 12, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(IDENT);
			setState(140); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(136);
					match(OPEN_SQUARE_BRACKET);
					setState(137);
					expr(0);
					setState(138);
					match(CLOSE_SQUARE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(142); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		enterRule(_localctx, 14, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(OPEN_SQUARE_BRACKET);
			setState(145);
			expr(0);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(146);
				match(COMMA);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << OPEN_PARENTHESES) | (1L << INTEGER) | (1L << BOOLEAN) | (1L << STRING) | (1L << CHARACTER) | (1L << PAIR) | (1L << IDENT))) != 0)) {
					{
					{
					setState(147);
					expr(0);
					}
					}
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(155);
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
		enterRule(_localctx, 16, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(PAIR_TYPE);
			setState(158);
			match(OPEN_PARENTHESES);
			setState(159);
			pairElemType();
			setState(160);
			match(COMMA);
			setState(161);
			pairElemType();
			setState(162);
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
		enterRule(_localctx, 18, RULE_pairElemType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR_TYPE) | (1L << BASE_TYPE) | (1L << ARRAY_TYPE))) != 0)) ) {
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
		enterRule(_localctx, 20, RULE_pairElem);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR_FIRST:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				match(PAIR_FIRST);
				setState(167);
				expr(0);
				}
				break;
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(PAIR_SECOND);
				setState(169);
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

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
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
		enterRule(_localctx, 22, RULE_prog);
		try {
			int _alt;
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				match(BEGIN);
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(173);
						func();
						}
						} 
					}
					setState(178);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(179);
				stat(0);
				setState(180);
				match(END);
				}
				break;
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case OPEN_PARENTHESES:
			case INTEGER:
			case BOOLEAN:
			case STRING:
			case CHARACTER:
			case PAIR:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
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

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(BasicParser.TYPE, 0); }
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
		enterRule(_localctx, 24, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(TYPE);
			setState(186);
			match(IDENT);
			setState(187);
			match(OPEN_PARENTHESES);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(188);
				paramList();
				}
			}

			setState(191);
			match(CLOSE_PARENTHESES);
			setState(192);
			match(IS);
			setState(193);
			stat(0);
			setState(194);
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

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(BasicParser.TYPE, 0); }
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
		enterRule(_localctx, 26, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(TYPE);
			setState(197);
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
		enterRule(_localctx, 28, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			param();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(200);
				match(COMMA);
				setState(201);
				param();
				}
				}
				setState(206);
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
		enterRule(_localctx, 30, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			expr(0);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(208);
				match(COMMA);
				setState(209);
				expr(0);
				}
				}
				setState(214);
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
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u00da\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4O\n\4\3\4\3\4\3\4\7\4T\n\4\f\4\16\4W\13"+
		"\4\3\5\3\5\3\5\5\5\\\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6n\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7\177\n\7\3\7\3\7\3\7\3\7\7\7\u0085\n\7\f\7\16\7"+
		"\u0088\13\7\3\b\3\b\3\b\3\b\3\b\6\b\u008f\n\b\r\b\16\b\u0090\3\t\3\t\3"+
		"\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\5\t\u009c\n\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u00ad\n\f\3\r\3\r\7"+
		"\r\u00b1\n\r\f\r\16\r\u00b4\13\r\3\r\3\r\3\r\3\r\5\r\u00ba\n\r\3\16\3"+
		"\16\3\16\3\16\5\16\u00c0\n\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\7\20\u00cd\n\20\f\20\16\20\u00d0\13\20\3\21\3\21\3\21"+
		"\7\21\u00d5\n\21\f\21\16\21\u00d8\13\21\3\21\2\4\6\f\22\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \2\5\3\2\4\20\4\2\5\5\22\25\3\2\')\2\u00ed\2"+
		"\"\3\2\2\2\4$\3\2\2\2\6N\3\2\2\2\b[\3\2\2\2\nm\3\2\2\2\f~\3\2\2\2\16\u0089"+
		"\3\2\2\2\20\u0092\3\2\2\2\22\u009f\3\2\2\2\24\u00a6\3\2\2\2\26\u00ac\3"+
		"\2\2\2\30\u00b9\3\2\2\2\32\u00bb\3\2\2\2\34\u00c6\3\2\2\2\36\u00c9\3\2"+
		"\2\2 \u00d1\3\2\2\2\"#\t\2\2\2#\3\3\2\2\2$%\t\3\2\2%\5\3\2\2\2&\'\b\4"+
		"\1\2\'O\7\35\2\2()\7&\2\2)*\79\2\2*+\7\21\2\2+O\5\n\6\2,-\5\b\5\2-.\7"+
		"\21\2\2./\5\n\6\2/O\3\2\2\2\60\61\7\36\2\2\61O\5\b\5\2\62\63\7\37\2\2"+
		"\63O\5\f\7\2\64\65\7 \2\2\65O\5\f\7\2\66\67\7!\2\2\67O\5\f\7\289\7\"\2"+
		"\29O\5\f\7\2:;\7#\2\2;O\5\f\7\2<=\7-\2\2=>\5\f\7\2>?\7.\2\2?@\5\6\4\2"+
		"@A\7/\2\2AB\5\6\4\2BC\7\60\2\2CO\3\2\2\2DE\7*\2\2EF\5\f\7\2FG\7+\2\2G"+
		"H\5\6\4\2HI\7,\2\2IO\3\2\2\2JK\7\32\2\2KL\5\6\4\2LM\7\33\2\2MO\3\2\2\2"+
		"N&\3\2\2\2N(\3\2\2\2N,\3\2\2\2N\60\3\2\2\2N\62\3\2\2\2N\64\3\2\2\2N\66"+
		"\3\2\2\2N8\3\2\2\2N:\3\2\2\2N<\3\2\2\2ND\3\2\2\2NJ\3\2\2\2OU\3\2\2\2P"+
		"Q\f\3\2\2QR\7$\2\2RT\5\6\4\4SP\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V"+
		"\7\3\2\2\2WU\3\2\2\2X\\\79\2\2Y\\\5\16\b\2Z\\\5\26\f\2[X\3\2\2\2[Y\3\2"+
		"\2\2[Z\3\2\2\2\\\t\3\2\2\2]n\5\f\7\2^n\5\20\t\2_`\7\65\2\2`a\7\26\2\2"+
		"ab\5\f\7\2bc\7\3\2\2cd\5\f\7\2de\7\27\2\2en\3\2\2\2fn\5\26\f\2gh\7%\2"+
		"\2hi\79\2\2ij\7\26\2\2jk\5 \21\2kl\7\27\2\2ln\3\2\2\2m]\3\2\2\2m^\3\2"+
		"\2\2m_\3\2\2\2mf\3\2\2\2mg\3\2\2\2n\13\3\2\2\2op\b\7\1\2p\177\7\61\2\2"+
		"q\177\7\62\2\2r\177\7\64\2\2s\177\7\63\2\2t\177\7\66\2\2u\177\79\2\2v"+
		"\177\5\16\b\2wx\5\4\3\2xy\5\f\7\5y\177\3\2\2\2z{\7\26\2\2{|\5\f\7\2|}"+
		"\7\27\2\2}\177\3\2\2\2~o\3\2\2\2~q\3\2\2\2~r\3\2\2\2~s\3\2\2\2~t\3\2\2"+
		"\2~u\3\2\2\2~v\3\2\2\2~w\3\2\2\2~z\3\2\2\2\177\u0086\3\2\2\2\u0080\u0081"+
		"\f\4\2\2\u0081\u0082\5\2\2\2\u0082\u0083\5\f\7\5\u0083\u0085\3\2\2\2\u0084"+
		"\u0080\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087\r\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008e\79\2\2\u008a\u008b"+
		"\7\30\2\2\u008b\u008c\5\f\7\2\u008c\u008d\7\31\2\2\u008d\u008f\3\2\2\2"+
		"\u008e\u008a\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091"+
		"\3\2\2\2\u0091\17\3\2\2\2\u0092\u0093\7\30\2\2\u0093\u009b\5\f\7\2\u0094"+
		"\u0098\7\3\2\2\u0095\u0097\5\f\7\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2"+
		"\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009c\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b\u0094\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009e\7\31\2\2\u009e\21\3\2\2\2\u009f\u00a0\7\'\2\2\u00a0\u00a1"+
		"\7\26\2\2\u00a1\u00a2\5\24\13\2\u00a2\u00a3\7\3\2\2\u00a3\u00a4\5\24\13"+
		"\2\u00a4\u00a5\7\27\2\2\u00a5\23\3\2\2\2\u00a6\u00a7\t\4\2\2\u00a7\25"+
		"\3\2\2\2\u00a8\u00a9\7\67\2\2\u00a9\u00ad\5\f\7\2\u00aa\u00ab\78\2\2\u00ab"+
		"\u00ad\5\f\7\2\u00ac\u00a8\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\27\3\2\2"+
		"\2\u00ae\u00b2\7\32\2\2\u00af\u00b1\5\32\16\2\u00b0\u00af\3\2\2\2\u00b1"+
		"\u00b4\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\3\2"+
		"\2\2\u00b4\u00b2\3\2\2\2\u00b5\u00b6\5\6\4\2\u00b6\u00b7\7\33\2\2\u00b7"+
		"\u00ba\3\2\2\2\u00b8\u00ba\5\f\7\2\u00b9\u00ae\3\2\2\2\u00b9\u00b8\3\2"+
		"\2\2\u00ba\31\3\2\2\2\u00bb\u00bc\7&\2\2\u00bc\u00bd\79\2\2\u00bd\u00bf"+
		"\7\26\2\2\u00be\u00c0\5\36\20\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2"+
		"\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\7\27\2\2\u00c2\u00c3\7\34\2\2\u00c3"+
		"\u00c4\5\6\4\2\u00c4\u00c5\7\33\2\2\u00c5\33\3\2\2\2\u00c6\u00c7\7&\2"+
		"\2\u00c7\u00c8\79\2\2\u00c8\35\3\2\2\2\u00c9\u00ce\5\34\17\2\u00ca\u00cb"+
		"\7\3\2\2\u00cb\u00cd\5\34\17\2\u00cc\u00ca\3\2\2\2\u00cd\u00d0\3\2\2\2"+
		"\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\37\3\2\2\2\u00d0\u00ce"+
		"\3\2\2\2\u00d1\u00d6\5\f\7\2\u00d2\u00d3\7\3\2\2\u00d3\u00d5\5\f\7\2\u00d4"+
		"\u00d2\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7!\3\2\2\2\u00d8\u00d6\3\2\2\2\21NU[m~\u0086\u0090\u0098\u009b"+
		"\u00ac\u00b2\u00b9\u00bf\u00ce\u00d6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}