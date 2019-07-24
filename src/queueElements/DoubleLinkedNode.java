package queueElements;

public class DoubleLinkedNode<T> {
	private T element;
	private DoubleLinkedNode<T> next, previous;
	
	public DoubleLinkedNode(T element) {
		this.element = element;
		next = previous = null;
	}
	
	public DoubleLinkedNode() {
		element = null;
		next = previous = null;
	}

	public void setElement(T element) {
		this.element = element;
	}
	
	public void setNext(DoubleLinkedNode<T> node) {
		next = node;
	}
	
	public void setPrevious(DoubleLinkedNode<T> node) {
		previous = node;
	}
	
	public T getElement(){return element;}
	public DoubleLinkedNode<T> getNext(){return next;}
	public DoubleLinkedNode<T> getPrevious(){return previous;}
	
	@Override
	public String toString() {
		return element.toString();
	}
}
