import java.util.*;

class RelOp {
	String id;

	RelOp(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <relop> " + id);
		//lexArithArray.displayln(indent + " " + (indent.length() + 1)+ " <boolLiteral> " + id);
	}
	
	Val Eval(HashMap<String,Val> state)
	{
		Val idVal = state.get(id);
		if ( idVal != null )
			return idVal.cloneVal();
		else
		{
			System.out.println( "variable "+id+" does not have a value" );
			return null;
		}
	}
	
	void emitInstructions()
	{
		lexArithArray.displayln("push " + id);
	}
}