
public class BinaryTree<T> {

	private Node<T> rootNode;
	
	public BinaryTree() {
		rootNode = null;
	}
	
	public BinaryTree(Node<T> rootNode) {
		this.rootNode = rootNode;
	}
	
	public void insert(Node<T> node, T object) {
		
	}

	public Node<T> getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node<T> rootNode) {
		this.rootNode = rootNode;
	}
	
	private void print(Node<T> node)
	{
		if (node != null)
		{
			if (node.left != null)
				print(node.left);
		
			if (node.right != null)
				print(node.right);
			
			System.out.println(node.toString());
		}
	}
	
	public void print() {
		this.print(this.rootNode);
	}
	
}
