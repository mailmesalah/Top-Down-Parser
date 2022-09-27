import java.util.*;

class AndBoolTerm extends boolTerm
{
	boolPrimary boolprimary;
	boolTerm boolterm;

	AndBoolTerm(boolPrimary p, boolTerm t)
	{
		boolprimary = p;
		boolterm = t;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <expr>");
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		
		boolprimary.printParseTree(indent1);
		if (boolterm != null)
		{
			lexArithArray.displayln(indent1 + indent1.length() + " &&");
			boolterm.printParseTree(indent1);
		}
	}

	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = boolprimary.Eval(state);
		Val    termVal =    boolterm.Eval(state);
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
		boolprimary.emitInstructions();
		boolterm.emitInstructions();
		lexArithArray.displayln("mul");
	}
}