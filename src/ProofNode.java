
public class ProofNode extends Node<Token> {

	private boolean unaryFlag;
	
	public ProofNode(Token token) {
		super(token);
		setUnaryFlag(false);
	}
	
	public ProofNode(Token token, Node<Token> l , Node<Token> r) {
		super(token, l, r);
	}
	
	public void linkTo(ProofNode leftNode, ProofNode rightNode) {
		
		this.setLeft(leftNode);
		this.setRight(rightNode);
	}
	
	public String toString() {
		return this.getData().getValue();
	}

	public boolean isUnaryFlag() {
		return unaryFlag;
	}

	public void setUnaryFlag(boolean unaryFlag) {
		this.unaryFlag = unaryFlag;
	}
	
	public void switchUnaryFlag()
	{
		this.unaryFlag = !this.unaryFlag;
	}
	
}
