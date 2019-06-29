package queueElements;

import exceptions.*;

public class PriorityQueue<T> implements QueueInterface<T>{
	private int count;
	private LinearNode<T> first, last;
	private int modCount;
	
	public PriorityQueue() {
		count = 0;
		first = last = null;
		modCount = 0;
	}
	
	public PriorityQueue(T element) {
		LinearNode<T> node = new LinearNode(element);
		count = 1;
		first = last = node;
		modCount = 0;
	}
	
	@Override
	public T removeFirst() throws CollectionException{
		if(isEmpty())
			throw new CollectionException("Unordered List");
		
		T result = first.getElement();
		first = first.getNext();
		
		count--;
		modCount++;
		
		return result;
	}

	@Override
	public T remove(T targetElement) throws CollectionException,
	ElementNotFoundException{
		if(isEmpty())
			throw new CollectionException("LinkedList");
		
		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = first;
		
		while(current!=null && !found) 
			if(targetElement.equals(current.getElement()))
				found = true;
			else {
				previous = current;
				current = current.getNext();
			}
		if(!found)
			throw new ElementNotFoundException("LinkedList");
		
		if(size() == 1)
			first = last = null;
		else if(current.equals(first))
			first = current.getNext();
		else if(current.equals(last)) {
			last = previous;
			last.setNext(null);
		}
		else
			previous.setNext(current.getNext());
		
		count--;
		modCount++;
		
		return current.getElement();
	}

	@Override
	public T first() {return first.getElement();}

	@Override
	public boolean contains(T target) throws CollectionException{
		if(isEmpty())
			throw new CollectionException("Unordered List");
		
		boolean checker = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = first;
		
		while(checker != true && current != null) {
			if(current.getElement().equals(target))
				checker = true;
			else {
				previous = current;
				current = current.getNext();
			}	
		}
		return checker;
	}
	
	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		boolean checker = false;
		if(size() == 0)
			checker = true;
		return checker;
	}
	
	public String toString() throws CollectionException {
		if(isEmpty())
			throw new CollectionException("Unordered List");
		LinearNode<T> temp = first;
		String content = "";
		int i = 0;
		do {
			content += temp + "\n";
			temp = temp.getNext();
			i++;
		}while(i<size());
		return content;
	}

}
