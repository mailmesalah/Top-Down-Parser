import java.util.*;

/*abstract class Do extends Statement
{
	abstract void printParseTree(String indent);
	public abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}*/



class If 
{
	expr ex;
	Statement st;

	If(expr p, Statement t)
	{
		ex = p;
		st = t;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		lexArithArray.displayln(indent + indent.length() + " <statement>");
		lexArithArray.displayln(indent + indent.length() + " <cond>");
		lexArithArray.displayln(indent + (indent.length() + 1)+ " if" + " (");
		ex.printParseTree(indent1);
		lexArithArray.displayln(indent + (indent.length() + 1)+ " )");
		st.printParseTree(indent1);
	}

	public Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = ex.Eval(state);
		Val    termVal =   st.Eval(state);
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
	}
	
	void emitInstructions()
	{
		ex.emitInstructions();
		st.emitInstructions();
		lexArithArray.displayln("mul");
	}
}