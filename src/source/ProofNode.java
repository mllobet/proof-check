package source;

public class ProofNode extends Node<Token> {

	private final boolean debug = false;
	
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
		
		if (this.unaryFlag == true)
			return "~" + this.getData().getValue();
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
	
	@Override 
	public boolean equals(Object o)
	{
		if(debug)
			System.out.println("ProofNode equals");
		return (this.getData().equals(((ProofNode)o).getData()) && this.unaryFlag == ((ProofNode)o).unaryFlag);
	}
	
	public boolean equalsNoSign(Object o)
	{
		return (this.getData().equals(((ProofNode)o).getData()));
	}
	
	public boolean equalsOpositeSign(Object o)
	{
		return (this.getData().equals(((ProofNode)o).getData()) && this.isUnaryFlag() != ((ProofNode)o).isUnaryFlag());
	}
}
