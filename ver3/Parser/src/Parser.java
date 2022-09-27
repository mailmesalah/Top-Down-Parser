/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<assignment list> --> <assignment> | <assignment> <assignment list>
<assignment> --> <id> = <E> ";"
<E> --> <term> | <term> + <E> | <term> - <E>
<term> --> <primary> | <primary> * <term> | <primary> / <term>
<primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")" 

The definitions of the tokens are given in the lexical analyzer class file "lexArithArray.java".

The following variables and functions of the "lexArithArray" class are used:

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token
static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks. 

**/
public class Parser extends lexArith
{
	static boolean errorFound = false;
	/*public static AssignmentList assignmentList()
	// <assignment list> --> <assignment> | <assignment> <assignment list>
	{
		Assignment assignment = assignment();
		if ( state == State.id )
		{
			AssignmentList assignmentList = assignmentList();
			return new MultipleAssignment(assignment, assignmentList);
		}
		else
			return assignment;
	}*/	
	public static SList slist()
	
	// <assignment list> --> <assignment> | <assignment> <assignment list>
	
	{
		Statement statement = statement();
		if ( state == State.LBrace )
		{
			MultipleStatement sl = multiplestatement();
			return new MultipleStatement(statement,sl);
		}
		else
			return statement;
	}
	public static MultipleStatement multiplestatement()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		if ((state == State.id)||(state == State.keyword_if )||( state == State.keyword_do )||( state == State.keyword_while)||( state == State.LBrace ))
		{			
			getToken();
			Statement el = statement();
			getToken();
			MultipleStatement relist2 = multiplestatement();
			return new MultipleStatement(el, relist2);
		}
		
		else
			return null;
	}
	public static Statement statement()
	// <statement> → <assignment> | <increment> | <decrement> | <block> | <cond> | <while> | <do> 
	{
		if ((state == State.id)||(state == State.keyword_if )||( state == State.keyword_do )||( state == State.keyword_while)||( state == State.LBrace ))
		{
			if ( state == State.id )//if for id
			{
				Id id = new Id(t);
				System.out.println("id" + t);
				getToken();
				if (( state == State.assign ) || ( state == State.incr ) || ( state == State.decr )) //if for check type
				{
					if 	( state == State.assign )//if for assign
					{
						System.out.println(state);
						getToken();
						System.out.println("getToken cho expr");
						System.out.println("state truoc' khi lay token"+state);
						expr ex = Expr();
						System.out.println("get Expr");
						System.out.println("state sau khi lay token"+state);
						
						if ( state == State.semicolon )
						{
							System.out.println("Get ;");
							getToken();
							return new Assignment(id, ex);
						}
						else
							errorMsg(4);
					}//end if for assign
					else if ( state == State.incr )//if for incr
					{
						System.out.println("Incr:Just do it"+state);
						getToken();						
						System.out.println("Incr:Just do it"+state);
						if ( state == State.semicolon )
						{
							System.out.println("Incr:Just do it"+state);
							return new Increment(id);
							
						}
						else
						{System.out.println("Incr:Just in Else "+state);
							errorMsg(4);}
					}//end if for incr
					else// case for decr
					{
						getToken();						
						if ( state == State.semicolon )
						{
							getToken();
							return new Decrement(id);
						}
						else
							errorMsg(4);
					}//end if for decr				    
				}//end if for check type of id
			}//end if for id
			else if (( state == State.LBrace )) //if for block
			{
				getToken();
				SList s = slist();
				if ( state == State.RBrace )
				{
					getToken();
					return new Block(s);
				}
				else errorMsg(3);
			}//end if for block
			else if (( state == State.keyword_if ))//if for cond
			{				
				getToken();
				Cond c = cond();	
				return c;
			}//end if for cond
			else if (( state == State.keyword_while ))//if for while
			{				
				getToken();
				While c = whileS();	
				return c;
			}//end if for while
			else if (( state == State.keyword_do )) //if for do
			{				
				getToken();
				Do c = doS();	
				return c;				
			}//end if for do
			else
				errorMsg(3);	
		}else errorMsg(4); //not statement
		return null;
	}
	public static boolLiteral boolliteral()
	{	
		if ( state == State.keyword_false )
		{
			boolLiteral a = new boolLiteral(t);
			getToken();
			return a;
		}
		else if ( state == State.keyword_true )
		{
			boolLiteral a = new boolLiteral(t);
			getToken();
			return a;
		}
		else
		{
			errorMsg(6);
			return null;
		}
	}
	public static Assignment assignment()	
	// <assignment> --> <id> = <expr> ";"
	
	{
		if ( state == State.id )
		{
			Id id = new Id(t);
			getToken();
			if ( state == State.assign )
			{
				getToken();
				expr ex = Expr();
				if ( state == State.semicolon )
				{
					getToken();
					return new Assignment(id, ex);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static Increment increment()	
	// 
	
	{
		if ( state == State.id )
		{
			Id id = new Id(t);
			getToken();
			if ( state == State.incr )
			{
				getToken();
				
				if ( state == State.semicolon )
				{
					getToken();
					return new Increment(id);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static Decrement decrement()	
	// <assignment> --> <id> = <expr> ";"
	
	{
		if ( state == State.id )
		{
			Id id = new Id(t);
			getToken();
			if ( state == State.decr )
			{
				getToken();
				
				if ( state == State.semicolon )
				{
					getToken();
					return new Decrement(id);
				}
				else
					errorMsg(4);
			}
			else
				errorMsg(3);
		}
		else
			errorMsg(5);
		return null;
	}
	public static Cond cond()
	// <term> --> <primary> | <primary> * <term> | <primary> / <term>
	{
		MultipleElseStatement mes;
		
		if ( state == State.keyword_if )
		{	
			if ( state == State.LParen)
			{
				getToken();
				expr ex = Expr();				
				if ( state == State.RParen)
				{
					getToken();
					Statement st = statement();	
					if ( state == State.keyword_else)
					{
						getToken();
						 mes = multipleelsestatement();
					}
					else
						mes = null;
					If if3 = new If (ex,st);
					return new Cond(if3,mes);
				}
				else return null;
			}			
			else return null;
		}
		else return null;
		
	}
	public static MultipleElseStatement multipleelsestatement()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		if (state == State.keyword_else)
		{			
			getToken();
			ElseStatement el = elsestatement();
			MultipleElseStatement relist2 = multipleelsestatement();
			return new MultipleElseStatement(el, relist2);
		}
		
		else
			return null;
	}
	public static ElseStatement elsestatement()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		getToken();
		
		if  ( (state == State.keyword_else))
		{
			Statement st = statement();
			 return new ElseStatement(st);
		}
		else
		{
			errorMsg(6);
			return null;
		}
	}
	public static Do doS()	
	{
		
		if ( state == State.keyword_do )
		{
			getToken();
			Statement st = statement();
			if ( state == State.keyword_while )
			{
				getToken();
				if ( state == State.LParen)
				{
					getToken();
					expr ex = Expr();
					
					if ( state == State.LParen)
						return new Do(st,ex);
					else errorMsg(5);
				}
				else errorMsg(4);
			}
			else
			errorMsg(4);
		}
		else
		errorMsg(3);
		return null;
	}
	public static While whileS()	
	{
		
		if ( state == State.keyword_while )
		{
			getToken();
			if (state == State.LParen)
			{
				getToken();
				expr ex = Expr();
				if ( state == State.LParen)
				{
					getToken();
					Statement st = statement();
					return new While(ex,st);
				}
				else errorMsg(5);
			}
			else errorMsg(4);
		}
		else
		errorMsg(3);
		return null;
	}
	public static Block block()	
	// <assignment> --> <id> = <expr> ";"
	
	{
		if ( state == State.LBrace)
		{
			getToken();
			SList s = slist();			
			if ( state == State.RBrace )
			{
				getToken();
				return new Block(s);
			}
			else
			errorMsg(4);
		}
		else
		errorMsg(3);
		return null;
	}
	public static E E()
	// <E> --> <term> | <term> + <E> | <term> - <E>
	{
		System.out.println("E: state truoc' khi lay token"+state);
		Term term = term();
		if ( state == State.add )
		{			
			getToken();
			E e = E();
			return new AddE(term, e);
		}
		else if ( state == State.sub )
		{
			getToken();
			E e = E();
			return new SubE(term, e);
		}
		else
			return new SingleTerm(term);
	}
	public static MultipleRelOpE multiplerelope()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		if ( (state == State.lt)||(state == State.le)||(state == State.gt)||(state == State.ge)||(state == State.eq)||(state == State.neq))
		{			
			getToken();
			RelOpE re2 = relope();
			MultipleRelOpE relist2 = multiplerelope();
			return new MultipleRelOpE(re2, relist2);
		}
		
		else
			return null;
	}
	public static RelOpE relope()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		
		if  ( (state == State.lt)||(state == State.le)||(state == State.gt)||(state == State.ge)||(state == State.eq)||(state == State.neq))
		{
			RelOp relop2 = relop();
			E e2 = E();
			RelOpE a = new RelOpE(relop2,e2);

			return a;
		}
		else
		{
			errorMsg(6);
			return null;
		}
	}
	public static RelOp relop()

	// <E> --> <term> | <term> + <E> | <term> - <E>

	{
		
		if  ( (state == State.lt)||(state == State.le)||(state == State.gt)||(state == State.ge)||(state == State.eq)||(state == State.neq))
		{
			RelOp a = new RelOp(t);
			getToken();
			return a;
		}
		
		else
		{
			errorMsg(6);
			return null;
		}
	}
	public static boolPrimary boolprimary()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		boolPrimary k;
		System.out.println("boolPrimary: state truoc' khi lay token"+state);
		E e = E();

		System.out.println("boolPrimary: state sau khi lay token"+state);
		MultipleRelOpE m = multiplerelope();
		k = new boolPrimary(e,m);
		return k;
		
		//return new DivTerm(primary, term);
	}
	public static boolTerm boolterm()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		System.out.println("boolTerm: state truoc' khi lay token"+state);
		boolPrimary bp = boolprimary();
		System.out.println("boolTerm: state sau khi lay token"+state);
		if ( state == State.and )
		{			
			getToken();
			boolTerm bt = boolterm();
			return new AndBoolTerm(bp, bt);
		}
		
		else
			return new SingleBoolPrimary(bp);
	}
	public static expr Expr()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		System.out.println("Expr: state truoc' khi lay token"+state);
		
		System.out.println("Expr: state sau khi lay token"+state);
		boolTerm bt = boolterm();
		if ( state == State.or )
		{			
			getToken();
			expr ex = Expr();
			return new OrExpr(bt, ex);
		}
		
		else
		{
			System.out.println("Ket qua " +state);
			return new SingleBoolTerm(bt);
		}
	}
	public static Term term()

	// <term> --> <primary> | <primary> * <term> | <primary> / <term>

	{
		System.out.println("Term: state truoc' khi lay token"+state);
		Primary primary = primary();
		System.out.println("Term: state sau khi lay token"+state);
		System.out.println("Term: state cuoi khi lay token"+state);
		if ( state == State.mul )
		{			
			getToken();
			Term term = term();
			return new MulTerm(primary, term);
		}
		else if ( state == State.div )
		{
			getToken();
			Term term = term();
			return new DivTerm(primary, term);
		}
		else
		{
			System.out.println("Single Primary"+state);
			return new SinglePrimary(primary);
		}
	}
	public static Primary primary()
	// <primary> --> <id> | <int> | <float> | <floatE> | "(" <E> ")"
	{
		switch ( state )
		{
			case id:
										
				Id id = new Id(t);
				getToken();				
				return id;				
			case Int:
				Int intElem = new Int(Integer.parseInt(t));
				System.out.println("Primary: state truoc khi lay token"+state);
				getToken();
				System.out.println("Primary: state sau khi lay token"+state);
				return intElem;
			case Float: case FloatE:
				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;
			case keyword_false: case keyword_true:
				boolLiteral true_false= new boolLiteral(t);
				getToken();
				return true_false;
			case LParen:				
				getToken();
				expr e = Expr();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}		
			case sub:				
				getToken();
				System.out.println("Sub"+state);
				Primary primary = primary();
				NegPrimary neg = new NegPrimary(primary);
				return neg;
			case inv:
				System.out.println("Inv"+state);									
				getToken();
				System.out.println("Inv:"+state);
				Primary primary1 = primary();
				Primary nt = new NotPrimary(primary1);
				getToken();
				System.out.println("Inv:" + state);								
				return nt;
			/*case lt: case le: case gt: case ge: case eq: case neq:
				getToken();*/
			default:
				errorMsg(2);
				return null;
		}
	}
	
	public static void errorMsg(int i)
	{
		errorFound = true;
		
		display(t + ": unexpected symbol where");

		switch( i )
		{
		case 1:	displayln(" arith op or ) expected"); return;
		case 2: displayln(" id, int, float, or ( expected"); return;
		case 3:	displayln(" = expected"); return;
		case 4:	displayln(" ; expected"); return;
		case 5:	displayln(" id expected"); return;	
		
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing the source code of an assignment list
		// argv[1]: output file to display the parse tree
		//System.out.println("//input")
		
		setLex( "input.txt", "output.txt");

		getToken();
		Statement st1 = statement(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! errorFound )
		{
			st1.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file
		}
		closeIO();
	}
}
