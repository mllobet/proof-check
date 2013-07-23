
public class ProofNode extends Node<Token> {

	public ProofNode(Token token)
	{
		super(token);
	}
	
	public void linkTo(ProofNode leftNode, ProofNode rightNode) {
		this.left = leftNode;
		this.right = rightNode;
	}
	
	public String toString() {
		return "Node : " + this.data.getValue();
	}
	
}
