import java.util.*;

class Block extends Statement
{
	SList s;

	Block(SList exp)
	{
		s = exp;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <statement>"); 
		lexArithArray.displayln(indent + indent.length() + " <block>"); 
		lexArithArray.displayln(indent + (indent.length()+1) + " {");
		s.printParseTree(indent+" ");
		lexArithArray.displayln(indent + (indent.length()+1) + " }");
	}

	/*Val Eval(HashMap<String,Val> state)
	{
		return e.Eval(state);
	}*/
	/*Val Eval(HashMap<String,Val> state)
	{
		Val primaryVal = ((SList) s).Eval(state);
		Val    termVal =    ((SList) s).Eval(state);
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
		s.emitInstructions();
	}
}