import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ProofTree extends BinaryTree<Token> {
	
	static final String kErrorVariable = "Variable exception";
	static final String kErrorOperator = "Operator exception";
	static final String kErrorParenthesis = "Parenthesis Exception";
	static final String kErrorUnary = "Unary Operator Exception";
	static final String kErrorMissing = "Missing argument";
		
	public ProofTree() {
		super();
	}
	
	public ProofTree(ProofNode root) {
		super(root);
	}
	
	private static Queue<Token> infixToPostfix(ArrayList<Token> tokenArray) throws IllegalLineException {
		
		Stack<Token> operatorStack = new Stack<Token>();
		Queue<Token> linkedList = new LinkedList<Token>();
		
		Stack<Token.Type> errorStack = new Stack<Token.Type>();
		Stack<Token.Type> parenthesisStack = new Stack<Token.Type>();
		
		for (Iterator<Token> iter = tokenArray.iterator(); iter.hasNext();) 
		{
			Token token = iter.next();
		
			if (token.getType() == Token.Type.OPEN_PARENTHESIS)
			{
				parenthesisStack.push(Token.Type.OPEN_PARENTHESIS);
				operatorStack.push(token);
			}
			else if (token.getType() == Token.Type.CLOSE_PARENTHESIS)
			{
				if (parenthesisStack.empty() == true)
					throw new IllegalLineException(kErrorParenthesis);
				
				Token.Type type = parenthesisStack.pop();
				if (type != Token.Type.OPEN_PARENTHESIS)
					throw new IllegalLineException(kErrorParenthesis);
				
				while (!operatorStack.isEmpty())
				{					
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
				
				if (errorStack.empty() == false)
				{
					Token.Type lastType = errorStack.pop();
					if (lastType == Token.Type.VARIABLE)
						throw new IllegalLineException(kErrorVariable);
				}

				errorStack.push(Token.Type.VARIABLE);
			}
			else if (token.getType() == Token.Type.UNARY_NOT_OPERATOR)
			{
			
				operatorStack.push(token);
				
			}
			else
			{
				
				if (operatorStack.empty() == false)
				{
					Token lastToken = operatorStack.lastElement();
				
					if (lastToken.getType() == Token.Type.UNARY_NOT_OPERATOR)
					{
						operatorStack.pop();
						linkedList.add(lastToken);
					}
				}
				
				operatorStack.push(token);
				
				if (errorStack.empty() == true)
					throw new IllegalLineException(kErrorOperator);

				Token.Type type = errorStack.pop();
				
				if (type != Token.Type.VARIABLE)
					throw new IllegalLineException(kErrorOperator);
					
				errorStack.push(token.getType());
			}
		}
		
		while (!operatorStack.isEmpty())
		{
			Token token = operatorStack.pop();
			linkedList.add(token);
		}
		
		if (parenthesisStack.empty() == false)
			throw new IllegalLineException(kErrorParenthesis);
				
		return linkedList;
	}
	
	public static ProofTree buildTree(ArrayList<Token> tokenArray) throws IllegalLineException {

		Queue<Token> queue = infixToPostfix(tokenArray);
		
		for (Iterator<Token> iter = queue.iterator(); iter.hasNext();)
		{
			System.out.println(iter.next().getValue());
		}
		
		Stack<ProofNode> treeNodeStack = new Stack<ProofNode>();
		
		for (Iterator<Token> iter = queue.iterator(); iter.hasNext();)
		{
			Token token = iter.next();
			
			if (token.getType() == Token.Type.VARIABLE)
			{
				treeNodeStack.push(new ProofNode(token));
			}
			else if (token.getType() == Token.Type.UNARY_NOT_OPERATOR)
			{
				if (treeNodeStack.isEmpty() == true)
					throw new IllegalLineException(kErrorUnary);
				
				treeNodeStack.lastElement().switchUnaryFlag();
			}
			else
			{
				ProofNode parentNode = new ProofNode(token);
				
				if (treeNodeStack.size() < 2)
					throw new IllegalLineException(kErrorMissing);
				
				ProofNode rightNode = treeNodeStack.pop();
				ProofNode leftNode = treeNodeStack.pop();

				parentNode.linkTo(leftNode, rightNode);
				treeNodeStack.push(parentNode);
			}
		}
		
		return new ProofTree(treeNodeStack.pop());
		
	}

}
