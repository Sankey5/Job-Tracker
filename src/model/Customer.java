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
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	private String customerCompanyName;
	private String customerName;
	private int customerPhoneNumber;
	private String customerEmail;
	
	public Customer(String customerCompanyName, String customerName, int customerPhoneNumber, String customerEmail) {
		this.customerCompanyName = customerCompanyName;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
	}
	public String getCustomerCompanyName() {
		return customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public String toString() {
		return getCustomerName() + " - #" + getCustomerPhoneNumber() + "\n"
			 + "Email: " + getCustomerEmail() + "\nCompany Name: " + getCustomerCompanyName();
	}
}
