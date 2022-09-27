import java.util.*;

class boolLiteral extends Primary
{
	String id;

	boolLiteral(String ident)
	{
		id = ident;
	}

	void printParseTree(String indent)
	{
		lexArithArray.displayln(indent + indent.length() + " <expr>");
		lexArithArray.displayln(indent + indent.length() + " <boolTerm>");
		lexArithArray.displayln(indent + indent.length() + " <boolPrimary>");
		lexArithArray.displayln(indent + indent.length() + " <E>");
		lexArithArray.displayln(indent + indent.length() + " <term>");
		lexArithArray.displayln(indent + indent.length() + " <primary> ");
		lexArithArray.displayln(indent + " " + (indent.length() + 1)+ " <boolLiteral> " + id);
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