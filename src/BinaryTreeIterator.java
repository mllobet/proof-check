import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class BinaryTreeIterator<T> implements Iterator<Node<T>> {

	Node<T> next;
	
	BinaryTreeIterator(Node<T> root) {
		
		next = root;
		
		while (next != null && next.getLeft() != null)
			next = next.getLeft();	
		
	}
	
	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public Node<T> next() {
		
	     if (!hasNext()) 
	    	 throw new NoSuchElementException();

	     Node<T> node = next;
	     
	     if (node != null && node.getRight() != null)
	     {
	    	 next = next.getRight();
	    	 while (next != null && next.getLeft() != null)
	    		 	next = next.getLeft();
	     }
	     else
	     {
	    	 while (next != null)
	    	 {
	    		 if (next.getParent() == null)
	    		 {
	    			 next = null;
	    			 return node;
	    		 }
	    		 
	    		 if (next.getParent().getLeft() == next)
	    		 {
	    			 next = next.getParent();
	    			 return node;
	    		 }
	    		 
	    		 next = next.getParent();
	    	 }	    	 
	     }
	     
		 return node;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
