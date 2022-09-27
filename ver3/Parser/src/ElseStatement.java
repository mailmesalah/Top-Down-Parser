import java.util.*;

class ElseStatement 
{
	 Statement st;


	ElseStatement( Statement p)
	{
		st = p;
		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + "else <statement> ");
		//lexArithArray.displayln(indent + " " + (indent.length() + 1)+ " <negPrimary>"+ " -" );
		
		st.printParseTree(indent1 + " ");
	}


/*	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = st.Eval(state);
		Val    termVal = ((Statement) st).Eval(state);
		if ( primaryVal == null || termVal == null )
			return null;
		
		// The result will be float if one or both of the arguments are float.
		
		Class primaryClass = primaryVal.getClass();
		Class    termClass =    termVal.getClass();

		if ( primaryClass == IntVal.class && termClass == IntVal.class )
		{
			((IntVal)primaryVal).val = ((IntVal)primaryVal).val * ((IntVal)termVal).val;
			return primaryVal;
		}
		else if ( primaryClass == IntVal.class ) // termClass == FloatVal.class
		{
			((FloatVal)termVal).val = ((IntVal)primaryVal).val * ((FloatVal)termVal).val;
			return termVal;
		}
		
		else // primaryClass == FloatVal.class
		{
			((FloatVal)primaryVal).val = primaryVal.floatVal() * termVal.floatVal();
			return primaryVal;
		}
	}*/
	
	void emitInstructions()
	{
		st.emitInstructions();
		lexArithArray.displayln("else statement");
	}

	public void M(HashMap<String, Val> state) {
		// TODO Auto-generated method stub
		
	}
}