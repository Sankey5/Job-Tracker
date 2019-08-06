package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	/**
	 * A choppy generator that will read in a job as a csv
	 * The csv format expected is: Customer, Equipment, LocalDate.
	 * @param tokens
	 * @return
	 */
	public static Job generateFromTokenList(ArrayList<String> tokens) {
		return new Job(Customer.generateFromTokenList((ArrayList<String>)tokens.subList(0, 4))
				, Equipment.generateFromTokenList((ArrayList<String>) tokens.subList(4, 7))
				, LocalDate.of(Integer.parseInt(tokens.get(7)), Integer.parseInt(tokens.get(8)), Integer.parseInt(tokens.get(9))));
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
	public String dateToSaveFormat() {
		return this.deadlineEntry.toString().replace("-", ",");
	}
	public String toSaveFormat() {
		String ret = this.getCustomer().toSaveFormat() + "," + this.getEquipment().toSaveFormat() + "," + this.dateToSaveFormat();
		return ret;
	}
	
}
