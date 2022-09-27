import java.util.*;

class NegPrimary extends Primary
{
	Primary primary;


	NegPrimary(Primary p)
	{
		primary = p;
		
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";

		lexArithArray.displayln(indent + indent.length() + " <expr>");
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		lexArithArray.displayln(indent + indent.length() + " <E>");
		lexArithArray.displayln(indent + indent.length() + " <term>");
		lexArithArray.displayln(indent + indent.length() + " <primary> ");
		lexArithArray.displayln(indent + " " + (indent.length() + 1)+ " <negPrimary>"+ " -" );
		
		primary.printParseTree(indent1 + " ");
	}


	Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = primary.Eval(state);
		Val    termVal =    primary.Eval(state);
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
		primary.emitInstructions();
		lexArithArray.displayln("neg");
	}
}