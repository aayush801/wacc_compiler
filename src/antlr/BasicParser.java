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
		WS=1, COMMENT=2, BASE_TYPE=3, ARRAY_TYPE=4, BEGIN=5, END=6, RETURN=7, 
		CALL=8, EXIT=9, SKIP_STATEMENT=10, IS=11, READ=12, FREE=13, PRINT=14, 
		PRINT_LINE=15, WHILE=16, DO=17, END_WHILE=18, IF=19, THEN=20, ELSE=21, 
		END_IF=22, OPEN_PARENTHESES=23, CLOSE_PARENTHESES=24, OPEN_SQUARE_BRACKET=25, 
		CLOSE_SQUARE_BRACKET=26, PLUS=27, MINUS=28, MULTIPLY=29, DIVIDE=30, MOD=31, 
		GR=32, GRE=33, LS=34, LSE=35, EQ=36, NEQ=37, AND=38, OR=39, NOT=40, LENGTH=41, 
		ORD=42, CHR=43, EQUALS=44, COMMA=45, SEPERATOR=46, INTEGER=47, INTSIGN=48, 
		BOOLEAN=49, CREATE_PAIR=50, PAIR=51, PAIR_FIRST=52, PAIR_SECOND=53, PAIR_TYPE=54, 
		STRING=55, CHARACTER=56, IDENT=57;
	public static final int
		RULE_prog = 0, RULE_func = 1, RULE_param = 2, RULE_paramList = 3, RULE_argList = 4, 
		RULE_stat = 5, RULE_assignLHS = 6, RULE_assignRHS = 7, RULE_expr = 8, 
		RULE_unaryOper = 9, RULE_type = 10, RULE_arrayElem = 11, RULE_array = 12, 
		RULE_pairType = 13, RULE_pairElemType = 14, RULE_pairElem = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "func", "param", "paramList", "argList", "stat", "assignLHS", 
			"assignRHS", "expr", "unaryOper", "type", "arrayElem", "array", "pairType", 
			"pairElemType", "pairElem"
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
			"'chr'", "'='", "','", "';'", null, null, null, "'newpair'", "'null'", 
			"'fst'", "'snd'", "'pair'"
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
			"SEPERATOR", "INTEGER", "INTSIGN", "BOOLEAN", "CREATE_PAIR", "PAIR", 
			"PAIR_FIRST", "PAIR_SECOND", "PAIR_TYPE", "STRING", "CHARACTER", "IDENT"
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
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(BEGIN);
				setState(36);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(33);
						func();
						}
						} 
					}
					setState(38);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(39);
				stat(0);
				setState(40);
				match(END);
				setState(41);
				match(EOF);
				}
				break;
			case OPEN_PARENTHESES:
			case PLUS:
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case INTEGER:
			case BOOLEAN:
			case PAIR:
			case STRING:
			case CHARACTER:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				expr(0);
				setState(44);
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
			setState(48);
			type();
			setState(49);
			match(IDENT);
			setState(50);
			match(OPEN_PARENTHESES);
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BASE_TYPE) | (1L << ARRAY_TYPE) | (1L << PAIR_TYPE))) != 0)) {
				{
				setState(51);
				paramList();
				}
			}

			setState(54);
			match(CLOSE_PARENTHESES);
			setState(55);
			match(IS);
			setState(56);
			stat(0);
			setState(57);
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
		enterRule(_localctx, 4, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			type();
			setState(60);
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
		enterRule(_localctx, 6, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			param();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(63);
				match(COMMA);
				setState(64);
				param();
				}
				}
				setState(69);
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
		enterRule(_localctx, 8, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			expr(0);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(71);
				match(COMMA);
				setState(72);
				expr(0);
				}
				}
				setState(77);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKIP_STATEMENT:
				{
				setState(79);
				match(SKIP_STATEMENT);
				}
				break;
			case BASE_TYPE:
			case ARRAY_TYPE:
			case PAIR_TYPE:
				{
				setState(80);
				type();
				setState(81);
				match(IDENT);
				setState(82);
				match(EQUALS);
				setState(83);
				assignRHS();
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
			case IDENT:
				{
				setState(85);
				assignLHS();
				setState(86);
				match(EQUALS);
				setState(87);
				assignRHS();
				}
				break;
			case READ:
				{
				setState(89);
				match(READ);
				setState(90);
				assignLHS();
				}
				break;
			case FREE:
				{
				setState(91);
				match(FREE);
				setState(92);
				expr(0);
				}
				break;
			case RETURN:
				{
				setState(93);
				match(RETURN);
				setState(94);
				expr(0);
				}
				break;
			case EXIT:
				{
				setState(95);
				match(EXIT);
				setState(96);
				expr(0);
				}
				break;
			case PRINT:
				{
				setState(97);
				match(PRINT);
				setState(98);
				expr(0);
				}
				break;
			case PRINT_LINE:
				{
				setState(99);
				match(PRINT_LINE);
				setState(100);
				expr(0);
				}
				break;
			case IF:
				{
				setState(101);
				match(IF);
				setState(102);
				expr(0);
				setState(103);
				match(THEN);
				setState(104);
				stat(0);
				setState(105);
				match(ELSE);
				setState(106);
				stat(0);
				setState(107);
				match(END_IF);
				}
				break;
			case WHILE:
				{
				setState(109);
				match(WHILE);
				setState(110);
				expr(0);
				setState(111);
				match(DO);
				setState(112);
				stat(0);
				setState(113);
				match(END_WHILE);
				}
				break;
			case BEGIN:
				{
				setState(115);
				match(BEGIN);
				setState(116);
				stat(0);
				setState(117);
				match(END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(121);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(122);
					match(SEPERATOR);
					setState(123);
					stat(2);
					}
					} 
				}
				setState(128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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
		enterRule(_localctx, 12, RULE_assignLHS);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
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
		enterRule(_localctx, 14, RULE_assignRHS);
		int _la;
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESES:
			case PLUS:
			case MINUS:
			case NOT:
			case LENGTH:
			case ORD:
			case CHR:
			case INTEGER:
			case BOOLEAN:
			case PAIR:
			case STRING:
			case CHARACTER:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				expr(0);
				}
				break;
			case OPEN_SQUARE_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				array();
				}
				break;
			case CREATE_PAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				match(CREATE_PAIR);
				setState(137);
				match(OPEN_PARENTHESES);
				setState(138);
				expr(0);
				setState(139);
				match(COMMA);
				setState(140);
				expr(0);
				setState(141);
				match(CLOSE_PARENTHESES);
				}
				break;
			case PAIR_FIRST:
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 4);
				{
				setState(143);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(144);
				match(CALL);
				setState(145);
				match(IDENT);
				setState(146);
				match(OPEN_PARENTHESES);
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << INTEGER) | (1L << BOOLEAN) | (1L << PAIR) | (1L << STRING) | (1L << CHARACTER) | (1L << IDENT))) != 0)) {
					{
					setState(147);
					argList();
					}
				}

				setState(150);
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
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode BOOLEAN() { return getToken(BasicParser.BOOLEAN, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public TerminalNode CHARACTER() { return getToken(BasicParser.CHARACTER, 0); }
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
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(154);
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

				setState(157);
				match(INTEGER);
				}
				break;
			case 2:
				{
				setState(158);
				match(BOOLEAN);
				}
				break;
			case 3:
				{
				setState(159);
				match(PAIR);
				}
				break;
			case 4:
				{
				setState(160);
				match(IDENT);
				}
				break;
			case 5:
				{
				setState(161);
				match(STRING);
				}
				break;
			case 6:
				{
				setState(162);
				match(CHARACTER);
				}
				break;
			case 7:
				{
				setState(163);
				arrayElem();
				}
				break;
			case 8:
				{
				setState(164);
				unaryOper();
				setState(165);
				expr(7);
				}
				break;
			case 9:
				{
				setState(167);
				match(OPEN_PARENTHESES);
				setState(168);
				expr(0);
				setState(169);
				match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(188);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(173);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(174);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULTIPLY) | (1L << DIVIDE) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(175);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(176);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(177);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(178);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(179);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(180);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GR) | (1L << GRE) | (1L << LS) | (1L << LSE) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(181);
						expr(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(183);
						match(AND);
						setState(184);
						expr(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(185);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(186);
						match(OR);
						setState(187);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		enterRule(_localctx, 18, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
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
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BASE_TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(BASE_TYPE);
				}
				break;
			case ARRAY_TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				match(ARRAY_TYPE);
				}
				break;
			case PAIR_TYPE:
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
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
		enterRule(_localctx, 22, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(IDENT);
			setState(205); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(201);
					match(OPEN_SQUARE_BRACKET);
					setState(202);
					expr(0);
					setState(203);
					match(CLOSE_SQUARE_BRACKET);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(207); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 24, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(OPEN_SQUARE_BRACKET);
			setState(210);
			expr(0);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(211);
				match(COMMA);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PARENTHESES) | (1L << PLUS) | (1L << MINUS) | (1L << NOT) | (1L << LENGTH) | (1L << ORD) | (1L << CHR) | (1L << INTEGER) | (1L << BOOLEAN) | (1L << PAIR) | (1L << STRING) | (1L << CHARACTER) | (1L << IDENT))) != 0)) {
					{
					{
					setState(212);
					expr(0);
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(220);
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
		enterRule(_localctx, 26, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(PAIR_TYPE);
			setState(223);
			match(OPEN_PARENTHESES);
			setState(224);
			pairElemType();
			setState(225);
			match(COMMA);
			setState(226);
			pairElemType();
			setState(227);
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
		enterRule(_localctx, 28, RULE_pairElemType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
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
		enterRule(_localctx, 30, RULE_pairElem);
		try {
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR_FIRST:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(PAIR_FIRST);
				setState(232);
				expr(0);
				}
				break;
			case PAIR_SECOND:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				match(PAIR_SECOND);
				setState(234);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return stat_sempred((StatContext)_localctx, predIndex);
		case 8:
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
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\7"+
		"\2%\n\2\f\2\16\2(\13\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\61\n\2\3\3\3\3"+
		"\3\3\3\3\5\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\7\5D\n"+
		"\5\f\5\16\5G\13\5\3\6\3\6\3\6\7\6L\n\6\f\6\16\6O\13\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7z\n\7\3\7\3\7\3\7\7\7\177\n\7\f\7\16\7\u0082\13\7\3\b\3\b"+
		"\3\b\5\b\u0087\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u0097\n\t\3\t\5\t\u009a\n\t\3\n\3\n\5\n\u009e\n\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00ae\n\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00bf\n\n\f\n\16"+
		"\n\u00c2\13\n\3\13\3\13\3\f\3\f\3\f\5\f\u00c9\n\f\3\r\3\r\3\r\3\r\3\r"+
		"\6\r\u00d0\n\r\r\r\16\r\u00d1\3\16\3\16\3\16\3\16\7\16\u00d8\n\16\f\16"+
		"\16\16\u00db\13\16\5\16\u00dd\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00ee\n\21\3\21\2\4\f\22"+
		"\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\7\3\2\35\36\3\2\37!\3\2"+
		"\"\'\4\2\36\36*-\4\2\5\688\2\u010b\2\60\3\2\2\2\4\62\3\2\2\2\6=\3\2\2"+
		"\2\b@\3\2\2\2\nH\3\2\2\2\fy\3\2\2\2\16\u0086\3\2\2\2\20\u0099\3\2\2\2"+
		"\22\u00ad\3\2\2\2\24\u00c3\3\2\2\2\26\u00c8\3\2\2\2\30\u00ca\3\2\2\2\32"+
		"\u00d3\3\2\2\2\34\u00e0\3\2\2\2\36\u00e7\3\2\2\2 \u00ed\3\2\2\2\"&\7\7"+
		"\2\2#%\5\4\3\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2(&\3"+
		"\2\2\2)*\5\f\7\2*+\7\b\2\2+,\7\2\2\3,\61\3\2\2\2-.\5\22\n\2./\7\2\2\3"+
		"/\61\3\2\2\2\60\"\3\2\2\2\60-\3\2\2\2\61\3\3\2\2\2\62\63\5\26\f\2\63\64"+
		"\7;\2\2\64\66\7\31\2\2\65\67\5\b\5\2\66\65\3\2\2\2\66\67\3\2\2\2\678\3"+
		"\2\2\289\7\32\2\29:\7\r\2\2:;\5\f\7\2;<\7\b\2\2<\5\3\2\2\2=>\5\26\f\2"+
		">?\7;\2\2?\7\3\2\2\2@E\5\6\4\2AB\7/\2\2BD\5\6\4\2CA\3\2\2\2DG\3\2\2\2"+
		"EC\3\2\2\2EF\3\2\2\2F\t\3\2\2\2GE\3\2\2\2HM\5\22\n\2IJ\7/\2\2JL\5\22\n"+
		"\2KI\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\13\3\2\2\2OM\3\2\2\2PQ\b\7"+
		"\1\2Qz\7\f\2\2RS\5\26\f\2ST\7;\2\2TU\7.\2\2UV\5\20\t\2Vz\3\2\2\2WX\5\16"+
		"\b\2XY\7.\2\2YZ\5\20\t\2Zz\3\2\2\2[\\\7\16\2\2\\z\5\16\b\2]^\7\17\2\2"+
		"^z\5\22\n\2_`\7\t\2\2`z\5\22\n\2ab\7\13\2\2bz\5\22\n\2cd\7\20\2\2dz\5"+
		"\22\n\2ef\7\21\2\2fz\5\22\n\2gh\7\25\2\2hi\5\22\n\2ij\7\26\2\2jk\5\f\7"+
		"\2kl\7\27\2\2lm\5\f\7\2mn\7\30\2\2nz\3\2\2\2op\7\22\2\2pq\5\22\n\2qr\7"+
		"\23\2\2rs\5\f\7\2st\7\24\2\2tz\3\2\2\2uv\7\7\2\2vw\5\f\7\2wx\7\b\2\2x"+
		"z\3\2\2\2yP\3\2\2\2yR\3\2\2\2yW\3\2\2\2y[\3\2\2\2y]\3\2\2\2y_\3\2\2\2"+
		"ya\3\2\2\2yc\3\2\2\2ye\3\2\2\2yg\3\2\2\2yo\3\2\2\2yu\3\2\2\2z\u0080\3"+
		"\2\2\2{|\f\3\2\2|}\7\60\2\2}\177\5\f\7\4~{\3\2\2\2\177\u0082\3\2\2\2\u0080"+
		"~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\r\3\2\2\2\u0082\u0080\3\2\2\2\u0083"+
		"\u0087\7;\2\2\u0084\u0087\5\30\r\2\u0085\u0087\5 \21\2\u0086\u0083\3\2"+
		"\2\2\u0086\u0084\3\2\2\2\u0086\u0085\3\2\2\2\u0087\17\3\2\2\2\u0088\u009a"+
		"\5\22\n\2\u0089\u009a\5\32\16\2\u008a\u008b\7\64\2\2\u008b\u008c\7\31"+
		"\2\2\u008c\u008d\5\22\n\2\u008d\u008e\7/\2\2\u008e\u008f\5\22\n\2\u008f"+
		"\u0090\7\32\2\2\u0090\u009a\3\2\2\2\u0091\u009a\5 \21\2\u0092\u0093\7"+
		"\n\2\2\u0093\u0094\7;\2\2\u0094\u0096\7\31\2\2\u0095\u0097\5\n\6\2\u0096"+
		"\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\7\32"+
		"\2\2\u0099\u0088\3\2\2\2\u0099\u0089\3\2\2\2\u0099\u008a\3\2\2\2\u0099"+
		"\u0091\3\2\2\2\u0099\u0092\3\2\2\2\u009a\21\3\2\2\2\u009b\u009d\b\n\1"+
		"\2\u009c\u009e\t\2\2\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f"+
		"\3\2\2\2\u009f\u00ae\7\61\2\2\u00a0\u00ae\7\63\2\2\u00a1\u00ae\7\65\2"+
		"\2\u00a2\u00ae\7;\2\2\u00a3\u00ae\79\2\2\u00a4\u00ae\7:\2\2\u00a5\u00ae"+
		"\5\30\r\2\u00a6\u00a7\5\24\13\2\u00a7\u00a8\5\22\n\t\u00a8\u00ae\3\2\2"+
		"\2\u00a9\u00aa\7\31\2\2\u00aa\u00ab\5\22\n\2\u00ab\u00ac\7\32\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u009b\3\2\2\2\u00ad\u00a0\3\2\2\2\u00ad\u00a1\3\2"+
		"\2\2\u00ad\u00a2\3\2\2\2\u00ad\u00a3\3\2\2\2\u00ad\u00a4\3\2\2\2\u00ad"+
		"\u00a5\3\2\2\2\u00ad\u00a6\3\2\2\2\u00ad\u00a9\3\2\2\2\u00ae\u00c0\3\2"+
		"\2\2\u00af\u00b0\f\b\2\2\u00b0\u00b1\t\3\2\2\u00b1\u00bf\5\22\n\t\u00b2"+
		"\u00b3\f\7\2\2\u00b3\u00b4\t\2\2\2\u00b4\u00bf\5\22\n\b\u00b5\u00b6\f"+
		"\6\2\2\u00b6\u00b7\t\4\2\2\u00b7\u00bf\5\22\n\7\u00b8\u00b9\f\5\2\2\u00b9"+
		"\u00ba\7(\2\2\u00ba\u00bf\5\22\n\6\u00bb\u00bc\f\4\2\2\u00bc\u00bd\7)"+
		"\2\2\u00bd\u00bf\5\22\n\5\u00be\u00af\3\2\2\2\u00be\u00b2\3\2\2\2\u00be"+
		"\u00b5\3\2\2\2\u00be\u00b8\3\2\2\2\u00be\u00bb\3\2\2\2\u00bf\u00c2\3\2"+
		"\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\23\3\2\2\2\u00c2\u00c0"+
		"\3\2\2\2\u00c3\u00c4\t\5\2\2\u00c4\25\3\2\2\2\u00c5\u00c9\7\5\2\2\u00c6"+
		"\u00c9\7\6\2\2\u00c7\u00c9\5\34\17\2\u00c8\u00c5\3\2\2\2\u00c8\u00c6\3"+
		"\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\27\3\2\2\2\u00ca\u00cf\7;\2\2\u00cb\u00cc"+
		"\7\33\2\2\u00cc\u00cd\5\22\n\2\u00cd\u00ce\7\34\2\2\u00ce\u00d0\3\2\2"+
		"\2\u00cf\u00cb\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\31\3\2\2\2\u00d3\u00d4\7\33\2\2\u00d4\u00dc\5\22\n\2\u00d5"+
		"\u00d9\7/\2\2\u00d6\u00d8\5\22\n\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2"+
		"\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00dd\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00d5\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2"+
		"\2\2\u00de\u00df\7\34\2\2\u00df\33\3\2\2\2\u00e0\u00e1\78\2\2\u00e1\u00e2"+
		"\7\31\2\2\u00e2\u00e3\5\36\20\2\u00e3\u00e4\7/\2\2\u00e4\u00e5\5\36\20"+
		"\2\u00e5\u00e6\7\32\2\2\u00e6\35\3\2\2\2\u00e7\u00e8\t\6\2\2\u00e8\37"+
		"\3\2\2\2\u00e9\u00ea\7\66\2\2\u00ea\u00ee\5\22\n\2\u00eb\u00ec\7\67\2"+
		"\2\u00ec\u00ee\5\22\n\2\u00ed\u00e9\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"!\3\2\2\2\25&\60\66EMy\u0080\u0086\u0096\u0099\u009d\u00ad\u00be\u00c0"+
		"\u00c8\u00d1\u00d9\u00dc\u00ed";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}