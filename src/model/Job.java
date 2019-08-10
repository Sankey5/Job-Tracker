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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Job implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// determines position in priority queue
	private LocalDate deadlineEntry;
	private LocalDate dateFinished;
	private Customer customer;
	private Equipment equipment;
	private String details;
	private String techName;
	private Priority priority;
	/**@author Kenny
	 * 
	 * @param customer - customer who requested the job
	 * @param equipment - tooling required for completing the job
	 * @param deadlineEntry - deadline for the job
	 */
	public Job(Customer customer, Equipment equipment, LocalDate deadlineEntry) {
		this.customer = customer;
		this.equipment = equipment;
		this.deadlineEntry = deadlineEntry;
		this.techName = "";
		this.details = "No additional details.";
		priority = Priority.Medium;
	}

	public void setTechName(String name) {
		this.techName = name;
	}
	public String getTechName() {
		return this.techName;
	}
	public LocalDate getDeadlineEntry() {
		return deadlineEntry;
	}

	public void setDeadlineEntry(LocalDate deadlineEntry) {
		this.deadlineEntry = deadlineEntry;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public void setMemo(String memo) {
		this.details = memo;
	}
	
	public String getMemo() {
		return this.details;
	}
	public String dateToSaveFormat() {
		return this.deadlineEntry.toString().replace("-", ",");
	}
	
	public String toString() {
		return this.deadlineEntry + ": " +this.customer.getCustomerName() + " - " +  this.equipment.getEquipmentMake();
	}
	
	public String toDescription() {
		String header = toString() + "\n";
		String subject = this.customer.toString() + "\n";
		String body = "Tool needed:" +  this.equipment.toString()
					+ "Deadline: " + this.deadlineEntry 
                    + "\nDetails: " + this.getMemo();
		return header + subject + body;
	}

	public LocalDate getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(LocalDate dateFinished) {
		this.dateFinished = dateFinished;
	}

}
