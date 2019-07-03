package model;

import java.time.LocalDate;

public class Job {
	private LocalDate deadlineEntry;
	private String customerCompanyName;
	private String customerName;
	private int customerPhoneNumber;
	private String equipmentMake;
	private String equipmentModel;
	private String equipmentSerial;
	private String customerEmail;
	
	public Job(LocalDate deadlineEntry, String customerCompanyName, String customerName, int customerPhoneNumber,
			String equipmentMake, String equipmentModel, String equipmentSerial, String customerEmail) {
		super();
		this.deadlineEntry = deadlineEntry;
		this.customerCompanyName = customerCompanyName;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.equipmentMake = equipmentMake;
		this.equipmentModel = equipmentModel;
		this.equipmentSerial = equipmentSerial;
		this.customerEmail = customerEmail;
	}
	
	
}
