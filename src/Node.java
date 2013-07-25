

public class Node<T> {
 
    public T data;
    public Node<T> left;
    public Node<T> right;
 
    public Node() {
    	data = null;
    	left = null;
    	right = null;
    }

    public Node(T data) {
    	this();
        setData(data);
    }
    
    public Node(T data, Node<T> l, Node<T> r) {
    	setData(data);
    	left = l;
    	right = r;
    }
 
    public T getData() {
        return this.data;
    }
 
    public void setData(T data) {
        this.data = data;
    }
}
