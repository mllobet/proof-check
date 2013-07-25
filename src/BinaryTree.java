public class BinaryTree<G> {

	private Node myRoot;

	public BinaryTree ( ) {
		myRoot = null;
	}

	public BinaryTree (Node t) {
		myRoot = t;
	}

	public void print ( ) {
		if (myRoot != null) {
			printHelper (myRoot, 0);
		}
	}

	private static final String indent1 = "    ";

	private void printHelper (Node<G> root, int indent) {
		if(root.left != null) printHelper (root.left, indent + 1) ;
		println (root.data, indent);
		if(root.right != null) printHelper (root.right, indent + 1) ;
	}

	private static void println (Object obj, int indent) {
		for (int k=0; k<indent; k++) {
			System.out.print (indent1);
		}
		System.out.println (obj);
	}


}