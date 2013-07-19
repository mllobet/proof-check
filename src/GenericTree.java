import java.util.ArrayList;
import java.util.List;


public class GenericTree<T> {
 
    private Node<T> _rootElement;

    public GenericTree() {
        super();
    }
 
    public Node<T> getRootElement() {
        return _rootElement;
    }
 
    public void setRootElement(Node<T> rootElement) {
    	_rootElement = rootElement;
    }
    
    public String toString() {
        return toList().toString();
    }
    
    public List<Node<T>> toList() {
        List<Node<T>> list = new ArrayList<Node<T>>();
        cross(_rootElement, list);
        return list;
    }
    
    private void cross(Node<T> element, List<Node<T>> list) {
        list.add(element);
        for (Node<T> data : element.getChildren()) {
            cross(data, list);
        }
    }
}
