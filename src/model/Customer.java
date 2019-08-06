package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	private String customerCompanyName;
	private String customerName;
	private int customerPhoneNumber;
	private String customerEmail;
	
	public Customer(String customerCompanyName, String customerName, int customerPhoneNumber, String customerEmail) {
		super();
		this.customerCompanyName = customerCompanyName;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
	}
	
	public static Customer generateFromTokenList(ArrayList<String> tokens) {
		// TODO: Add unit testing to ensure proper values here
		return new Customer(tokens.get(0), tokens.get(1), Integer.parseInt(tokens.get(2)), tokens.get(3));
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

	public String toSaveFormat() {
		String ret = this.getCustomerCompanyName() + "," + this.getCustomerName() + ","
				+ String.valueOf(this.getCustomerPhoneNumber()) + "," + this.getCustomerEmail();
		return ret;
	}
}
