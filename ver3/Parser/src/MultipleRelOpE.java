import java.util.*;

class MultipleRelOpE 
{
	RelOpE re;
	MultipleRelOpE relist;
	

	MultipleRelOpE(RelOpE a, MultipleRelOpE al)
	{
		re = a;
		relist = al;
	}
 
	void printParseTree(String indent)
	{		
		re.printParseTree(indent);
		relist.printParseTree(indent);
	}

	/*void M(HashMap<String,Val> state) // function to interpret this list of multiple assignments
	{
		re.M(state);
		relist.M(state);
	}
	*///for work
	void emitInstructions()
	{
		re.emitInstructions();
		relist.emitInstructions();
	}

}
