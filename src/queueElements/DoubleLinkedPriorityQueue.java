/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package queueElements;

import exceptions.*;

public class DoubleLinkedPriorityQueue<T> implements QueueInterface<T>{
	private int count;
	private DoubleLinkedNode<T> first, last;
	
	public DoubleLinkedPriorityQueue() {
		count = 0;
		first = last = null;
	}
	
	public DoubleLinkedPriorityQueue(T element) {
		DoubleLinkedNode<T> node = new DoubleLinkedNode(element);
		count = 1;
		first = last = node;
	}
	
	@Override
	public void add(T element) {
		DoubleLinkedNode<T> newElement = new DoubleLinkedNode<T>(element);
		
		while(newElement.getNext() != last) {
			
		}
		
		count++;
	}
	
	@Override
	public T removeFirst() throws CollectionException{
		if(isEmpty())
			throw new CollectionException("Unordered List");
		
		T result = first.getElement();
		first = first.getNext();
		
		count--;
		
		return result;
	}

	@Override
	public T remove(T targetElement) throws CollectionException,
	ElementNotFoundException{
		if(isEmpty())
			throw new CollectionException("LinkedList");
		
		boolean found = false;
		DoubleLinkedNode<T> previous = null;
		DoubleLinkedNode<T> current = first;
		
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
		
		return current.getElement();
	}

	@Override
	public T first() {return first.getElement();}

	@Override
	public boolean contains(T target) throws CollectionException{
		if(isEmpty())
			throw new CollectionException("Unordered List");
		
		boolean checker = false;
		DoubleLinkedNode<T> previous = null;
		DoubleLinkedNode<T> current = first;
		
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
	
	@Override
	public String toString() throws CollectionException {
		if(isEmpty())
			throw new CollectionException("Unordered List");
		DoubleLinkedNode<T> temp = first;
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
