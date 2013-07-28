package source;

import java.util.ArrayList;

public class Expression
{
	private final boolean debug = false;
	private ProofTree _tree;
	private String _string;

	public Expression(String s) throws IllegalLineException
	{
		_string = s;
		
		ArrayList<Token> tokens = new ArrayList<Token>();
		ExpressionParser parser = new ExpressionParser(s);
		while (true)
		{
			Token token = parser.nextToken();
			if (token == null)
				break;
			tokens.add(token);
		}

		if (tokens.size() == 0)
			throw new IllegalLineException("Expression doesn't contain any known token");
		
		_tree = ProofTree.buildTree(tokens);
		_tree.print();

	}

	public boolean isImplication()
	{
		if (debug) {
			System.out.println("isImplication evaluates to: " + ((ProofNode)_tree.root).equals(new ProofNode(new Token(Token.Type.IMPLICATION_OPERATOR, "=>"))) );
			System.out.println(_tree.root);
		}
		return ((ProofNode)_tree.root).equals(new ProofNode(new Token(Token.Type.IMPLICATION_OPERATOR, "=>")));
	}

	public ProofTree getTree()
	{
		return _tree;
	}
	
	public String toString()
	{
		return _string;
	}
}
