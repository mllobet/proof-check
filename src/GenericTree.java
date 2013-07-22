import java.util.ArrayList;
import java.util.List;


public class GenericTree<T> {
 
    private Node<T> rootElement;

    public GenericTree() {
    }
 
    public Node<T> getRootElement() {
        return this.rootElement;
    }
 
    public void setRootElement(Node<T> rootElement) {
    	this.rootElement = rootElement;
    }
    
    public String toString() {
        return toList().toString();
    }
    
    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        cross(this.rootElement, list);
        return list;
    }
    
    private void cross(Node<T> element, List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            cross(data, list);
        }
    }
}
