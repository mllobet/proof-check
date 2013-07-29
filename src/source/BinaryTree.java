package source;

import java.util.Iterator;

public class BinaryTree<T> {
	
	protected final boolean debug = true;

	public Node<T> root;

	public BinaryTree ( ) {
		root = null;
	}

	public BinaryTree (Node<T> t) {
		root = t;
	}
	
	public BinaryTree (T in) {
		root = new Node<T> (in);
	}
	
	public BinaryTree (T in, Node<T> left, Node<T> right) {
		root = new Node<T> (in, left, right);
	}
	
	public Iterator<Node<T>> iterator()
	{
		return new BinaryTreeDFSIterator<T>(root);
	}
	
	public void print() {
		if (root != null) {
			printHelper (root, 0);
		}
	}

	private static final String kIndent = "    ";

	private void printHelper(Node<T> root, int indent) {
		if (root.getLeft() != null) 
			printHelper(root.getLeft(), indent + 1);
		println (root, indent);
		if (root.getRight() != null) 
			printHelper(root.getRight(), indent + 1);
	}

	private static void println(Object obj, int indent) 
	{
		for (int k = 0; k < indent; k++) {
			System.out.print(kIndent);
		}
		System.out.println(obj);
	}
	
}

