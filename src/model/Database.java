package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	private static Database database;
	private static String customerFileName, equipmentFileName, jobFileName, technicianFileName;
	private ArrayList<Technician> technicians;
	private ArrayList<Customer> customers;
	private ArrayList<Job> jobs;
	private ArrayList<Equipment> equipment;
	private Logger logger;
	private Technician currentTech;
	
	
	private Database() {
		logger = Logger.getLogger(this.getClass().getName());
		setDefaultFileNames();
		this.technicians = new ArrayList<Technician>();
		this.customers = new ArrayList<Customer>();
		this.jobs = new ArrayList<Job>();
		this.equipment = new ArrayList<Equipment>();
		
		/*Test case
		Technician tech = new Technician();
		tech.setName("bob smith");
		Customer customer = new Customer("Big corp", "Bill gates", 3, "@me");
		Equipment hacksaw = new Equipment("blah", "bleh", "1234");
		Job job = new Job(customer, hacksaw, LocalDate.now());
		tech.addEquipment(hacksaw);
		if (tech.giveJob(job)) {
			System.out.print("he got the job\n");
		}
		else {
			System.out.print("he didn't\n");
		}
		technicians.add(tech);
		saveTechnicians();
		end test case */
		loadDatabase();
	}

	/**
	 * A Singleton implemented database. This ensures there is only ever one instance of the database
	 * 
	 * @return
	 */
	public static Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}
	
	
	
	// TODO: build this out
	public void addMemo(String memo) {
	}
	
	public void loadDatabase() {
		loadTechnicians();
		loadCustomers();
		loadEquipment();
		loadJobs();
	}
	/**
	 * Simple default file names
	 * TODO: Load these in from a settings file
	 */
	public void setDefaultFileNames() {
		customerFileName = "src/data/customers";
		equipmentFileName = "src/data/equipment";
		jobFileName = "src/data/jobs";
		technicianFileName = "src/data/technicians";
	}

	public void loadTechnicians() {
		try {
			FileInputStream file = new FileInputStream(technicianFileName);
			ObjectInputStream objIn = new ObjectInputStream(file);
			this.technicians = (ArrayList<Technician>)objIn.readObject();
			file.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Technician file not found, one is being created in the default file name: "
					+ technicianFileName);
			saveTechnicians();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void saveTechnicians() {
		try {
			FileOutputStream file = new FileOutputStream(technicianFileName);
			ObjectOutputStream objOut = new ObjectOutputStream(file);
			objOut.writeObject(this.technicians);
			System.out.print("new file\n");
			file.close();
			objOut.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Technician file could not be created default file name: "
					+ technicianFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void loadCustomers() {
		try {
			FileInputStream file = new FileInputStream(customerFileName);
			ObjectInputStream objIn = new ObjectInputStream(file);
			this.customers = (ArrayList<Customer>)objIn.readObject();
			file.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Customer file not found, one is being created in the default file name: "
					+ customerFileName);
			saveCustomers();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void saveCustomers() {
		try {
			FileOutputStream file = new FileOutputStream(customerFileName);
			ObjectOutputStream objOut = new ObjectOutputStream(file);
			objOut.writeObject(this.customers);
			file.close();
			objOut.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Customers could not be created with default file name: "
					+ customerFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	public void loadEquipment() {
		try {
			FileInputStream file = new FileInputStream(equipmentFileName);
			ObjectInputStream objIn = new ObjectInputStream(file);
			this.equipment = (ArrayList<Equipment>)objIn.readObject();
			file.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Equipment file not found, one is being created in the default file name: "
					+ equipmentFileName);
			saveEquipment();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public void saveEquipment() {
		try {
			FileOutputStream file = new FileOutputStream(equipmentFileName);
			ObjectOutputStream objOut = new ObjectOutputStream(file);
			objOut.writeObject(this.equipment);
			file.close();
			objOut.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Equipment could not be created with default file name: "
					+ equipmentFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	
	public void loadJobs() {
		try {
			FileInputStream file = new FileInputStream(jobFileName);
			ObjectInputStream objIn = new ObjectInputStream(file);
			this.jobs = (ArrayList<Job>)objIn.readObject();
			file.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "Job file not found, one is being created in the default file name: "
					+ jobFileName);
			saveJobs();
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void saveJobs() {
		try {
			FileOutputStream file = new FileOutputStream(jobFileName);
			ObjectOutputStream objOut = new ObjectOutputStream(file);
			objOut.writeObject(this.jobs);
			file.close();
			objOut.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Job could not be created with default file name: "
					+ jobFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}
	
	//getter and setters
	public ArrayList<Technician> getTechnicians() {
		return technicians;
	}

	public void setTechnicians(ArrayList<Technician> technicians) {
		this.technicians = technicians;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public ArrayList<Job> getJobs() {
		return jobs;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(ArrayList<Equipment> equipment) {
		this.equipment = equipment;
	}

	public Technician getCurrentTech() {
		return currentTech;
	}

	public void setCurrentTech(Technician currentTech) {
		this.currentTech = currentTech;
	}

}
