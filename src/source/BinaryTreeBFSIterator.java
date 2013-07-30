package source;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;


public class BinaryTreeBFSIterator<T> implements Iterator<Node<T>> {

	LinkedList<Node<T>> _queue;
	
	BinaryTreeBFSIterator(Node<T> root) {
		_queue = new LinkedList<Node<T>>();
		_queue.add(root);
			
	}
	
	@Override
	public boolean hasNext() {
		return !_queue.isEmpty();
	}

	@Override
	public Node<T> next() {
		
		Node<T> ret = _queue.pop();
		if (ret.getLeft() != null)
			_queue.add(ret.getLeft());
		if (ret.getRight() != null)
			_queue.add(ret.getRight());
		
		return ret;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}