package source;

import java.util.ArrayList;

public class Expression
{
	private final boolean debug = false;
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
}
