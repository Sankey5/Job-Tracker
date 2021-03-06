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

public interface QueueInterface<T> {
	
	public void add(T element);

	/**
	 * removes the first item from the queue
	 * @return the first element from the queue
	 * @throws CollectionException if the queue is empty
	 */
	 public T removeFirst() throws CollectionException;
	 
	 /**
	  * removes the last item from the queue
	  * @return the last element from the queue
	  * @throws CollectionException if the queue is empty
	  */
	 public T remove(T targetElement) throws CollectionException;
	 
	 /**
	  * returns the element from the front of the queue
	  * @return the element from the front of the queue
	  */
	 public T first();
	 /**
	  * checks to see if the particular element is in the queue
	  * @param target the element you wish to find
	  * @return true if the element is in the queue
	  * @throws ElementNotFoundException if the element is not found in the queue
	  */
	 public boolean contains(T target) throws ElementNotFoundException;
	 
	 /**
	  * Checks to see if the queue is empty
	  * @return true if the queue is empty
	  */
	 public boolean isEmpty();
	 /**
	  * shows the number of elements in the queue
	  * @return the number of elements
	  */
	 public int size();
	 /**
	  * Returns a string version of the contents of the queue
	  * @return a string version of the contents of the queue
	  */
	 @Override
	public String toString();
	
}
