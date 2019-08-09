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
