
public class ProofTree extends BinaryTree<Token> {

	public ProofTree() {
		super();
	}
	
	public void insert(ProofNode node, Token token) {
		if (token.getType() == Token.Type.OPEN_PARENTHESIS)
		{
			ProofNode newNode = new ProofNode(token);
			
		}
	}
}
