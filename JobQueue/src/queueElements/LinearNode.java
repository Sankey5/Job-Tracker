package queueElements;

import java.time.LocalDateTime;

public class LinearNode<T> {
	private T element;
	private LocalDateTime timeStamp;
	private LinearNode<T> next;
	
	public LinearNode(T element) {
		this.element = element;
		timeStamp = LocalDateTime.now();
		next = null;
	}
	
	public LinearNode() {
		element = null;
		timeStamp = LocalDateTime.now();
		next = null;
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setElement(T element) {
		this.element = element;
	}
	
	public void setNext(LinearNode<T> node) {
		next = node;
	}
	
	public T getElement(){return element;}
	public LinearNode<T> getNext(){return next;}
	
	public String toString() {
		return element.toString();
	}
}
