package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Job implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// determines position in priority queue
	private LocalDate deadlineEntry;
	private Customer customer;
	private Equipment equipment;
	
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
	
	
}
