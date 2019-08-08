package temporaryDelete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {
	private ArrayList<Customer> customers;
	private String customerFileName;
	
	public CustomerManager() {
		this.customers = new ArrayList<Customer>();
	}
	
	/**
	 * This function will update the list of customers by reading the customer file.
	 * Note that the current list will be overridden.
	 * Must have a valid customerFileName
	 */
	public void updateCustomers() {
			ArrayList<Customer> updatedCustomers = new ArrayList<Customer>();
			File file = new File(customerFileName);
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] tokenArray = line.split(",");
					ArrayList<String> tokens = new ArrayList<String>();
					for(String token : tokenArray) {
						tokens.add(token);
					}
					updatedCustomers.add(Customer.generateFromTokenList(tokens));
				}
				this.setCustomers(updatedCustomers);
			}
			catch (FileNotFoundException e) {
				// prompt the user to locate the equipment file
				e.printStackTrace();
			}
			finally {
				scanner.close();
			}
	}

	public void saveCustomers() {
		try {
			FileWriter writer = new FileWriter(this.customerFileName);
			for(Customer customer : this.getCustomers()) {
				writer.write(customer.toSaveFormat());
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// prompt user to locate customerFile
			e.printStackTrace();
		}
		catch (IOException e){ 
			e.printStackTrace();
		}
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public String getCustomerFileName() {
		return customerFileName;
	}

	public void setCustomerFileName(String customerFileName) {
		this.customerFileName = customerFileName;
	}
	

}
