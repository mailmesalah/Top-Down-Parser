import java.util.*;

abstract class RelOpEList
{
	abstract void printParseTree(String indent);
	abstract void M(HashMap<String,Val> state); // function to interpret this assignment list
	abstract void emitInstructions();
}
