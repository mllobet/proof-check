package source;


public class Node<T> {
 
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;
 
    public Node() {
    	this.data = null;
    	this.left = null;
    	this.right = null;
    	parent = null;
    }

    public Node(T data) {
    	this();
        setData(data);
    }
    
    public Node(T data, Node<T> l, Node<T> r) {
    	setData(data);
    	l.parent = this;
    	r.parent = this;
    	this.left = l;
    	this.right = r;
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
    
    public void setLeft(Node<T> left)
    {
    	left.parent = this;
    	this.left = left;
    }
    
    public Node<T> getLeft()
    {
    	return this.left;
    }
    
    public void setRight(Node<T> right)
    {
    	right.parent = this;
    	this.right = right;
    }
    
    public Node<T> getRight()
    {
    	return this.right;
    }
    
    public void setParent(Node<T> parent)
    {
    	this.parent = parent;
    }
    
    public Node<T> getParent()
    {
    	return this.parent;
    }
    
    public Node<T> getLeftMost()
    {
    	Node<T> node = this;
    	while (node != null && node.left != null)
   		 	node = node.left;
    	
    	return node;
    }
}
