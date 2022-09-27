

/*abstract class While extends Statement
{
	abstract void printParseTree(String indent);
	public abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}*/
import java.util.*;

class While extends Statement
{
	expr ex;
	Statement st;

	While(expr p, Statement st1)
	{
		ex = p;
		st = st1;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <statement>");
		lexArithArray.displayln(indent + indent.length() + " <while>");
		lexArithArray.displayln(indent1 + (indent1.length()+1) + " while" + " (");
		ex.printParseTree(indent1);
		lexArithArray.displayln(indent1 + (indent1.length()+1) + " )");
		
		st.printParseTree(indent1);
	}

	public Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = ex.Eval(state);
		Val    termVal =    st.Eval(state);
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
		lexArithArray.displayln("while");
	}
}