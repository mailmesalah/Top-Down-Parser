import java.util.*;

class OrExpr extends expr
{
	boolTerm boolterm;
	expr ex;

	OrExpr(boolTerm p, expr t)
	{
		boolterm = p;
		ex = t;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <expr>");
		boolterm.printParseTree(indent1);
		lexArithArray.displayln(indent1 + indent1.length() + " ||");
		ex.printParseTree(indent1);
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = boolterm.Eval(state);
		Val    termVal =    ex.Eval(state);
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
		boolterm.emitInstructions();
		ex.emitInstructions();
		lexArithArray.displayln("mul");
	}
}