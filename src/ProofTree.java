import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ProofTree extends BinaryTree<Token> {
		
	public ProofTree() {
		super();
	}
	
	public ProofTree(ProofNode root) {
		super(root);
	}
	
	public static Queue<Token> infixToPostfix(ArrayList<Token> tokenArray) {
		
		Stack<Token> operatorStack = new Stack<Token>();
		Queue<Token> linkedList = new LinkedList<Token>();
		
		for (Iterator<Token> iter = tokenArray.iterator(); iter.hasNext();) 
		{
			Token token = iter.next();
		
			if (token.getType() == Token.Type.OPEN_PARENTHESIS)
			{
				operatorStack.push(token);
			}
			else if (token.getType() == Token.Type.CLOSE_PARENTHESIS)
			{
				while (!operatorStack.isEmpty())
				{
					// Check for operatorStack empty => error no open_parenthesis
					
					Token currentToken = operatorStack.pop();
					if (currentToken.getType() == Token.Type.OPEN_PARENTHESIS)
					{
						break;
					}
					
					linkedList.add(currentToken);
				}
			}
			else if (token.getType() == Token.Type.VARIABLE)
			{
				linkedList.add(token);
			}
			else
			{
				// Check for operator priority
				
				operatorStack.push(token);
			}
		}
		
		while (!operatorStack.isEmpty())
		{
			Token token = operatorStack.pop();
			linkedList.add(token);
		}
		
		return linkedList;
	}
	
	public static ProofTree buildTree(Queue<Token> queue) {

		Stack<ProofNode> treeNodeStack = new Stack<ProofNode>();
		
		for (Iterator<Token> iter = queue.iterator(); iter.hasNext();)
		{
			Token token = iter.next();
			
			if (token.getType() == Token.Type.VARIABLE)
			{
				treeNodeStack.push(new ProofNode(token));
			}
			else
			{
				ProofNode parentNode = new ProofNode(token);
				
				ProofNode rightNode = treeNodeStack.pop();
				ProofNode leftNode = treeNodeStack.pop();

				parentNode.linkTo(leftNode, rightNode);
				treeNodeStack.push(parentNode);
			}
		}
		
		return new ProofTree(treeNodeStack.pop());
		
	}

}
