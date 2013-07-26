import java.util.ArrayList;

public class Expression
{
	private ProofTree _tree;
	
	public Expression(String s) throws IllegalLineException
	{
		ArrayList<Token> tokens = new ArrayList<Token>();
		ExpressionParser parser = new ExpressionParser(s);
		while (true)
		{
			Token token = parser.nextToken();
			if (token == null)
				break;
			tokens.add(token);
		}
		
		_tree = ProofTree.buildTree(tokens);
    	_tree.print();

	}
	
	public ProofTree getTree()
	{
		return _tree;
	}
}
