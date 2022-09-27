import java.util.*;

abstract class Term
{
	abstract void printParseTree(String indent);
	//for work abstract Val Eval(HashMap<String,Val> state);
	abstract void emitInstructions();
}