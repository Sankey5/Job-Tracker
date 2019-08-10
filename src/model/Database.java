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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253136265747190951L;
	private static Database database;
	private static String customerFileName, equipmentFileName, jobFileName, technicianFileName;
	private static final String dataFileName = "src/data/database";
	private static Logger logger;
	
	// what is this for?
	private Technician currentTech;
	
	private ArrayList<Technician> technicians;
	private ArrayList<Customer> customers;
	private ArrayList<Job> jobs;
	private ArrayList<Equipment> equipment;

	private static final Pattern nonAlphaNum = Pattern.compile("[^a-zA-Z0-9]");
	private static final Pattern nonNumber = Pattern.compile("[^0-9]");
	private static final Pattern emailPattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]*?\\.[a-zA-Z]{3}");
	private static final Pattern phoneNumberPattern = Pattern.compile("[0-9]{3}-?[0-9]{3}-?[0-9]{4}");
	
	private Database() {
		logger = Logger.getLogger(this.getClass().getName());
	//	setDefaultFileNames();
		this.technicians = new ArrayList<Technician>();
		this.customers = new ArrayList<Customer>();
		this.jobs = new ArrayList<Job>();
		this.equipment = new ArrayList<Equipment>();
		load();
	//	loadDatabase();
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
	
	/**
	 * Validate words. Words should only have letters and/or numbers
	 * @param input - input to validate
	 * @return - True if the input is valid.
	 * <br>False if the input contains an illegal character
	 */
	public static boolean validateWord(String input) {
		Matcher m = nonAlphaNum.matcher(input);
		return (! m.find());
	}
	
	public static boolean validateNumber(String input) {
		Matcher m = nonNumber.matcher(input);
		return (! m.find());
	}
	/**
	 * Validate a phone number. Format: ###-###-####. (May contain hyphens).
	 * @param input - input to validate
	 * @return - True if phone number is valid
	 * <br> - False if input does not meet requirements
	 */
	public static boolean validatePhoneNumer(String input) {
		Matcher m = phoneNumberPattern.matcher(input);
		return m.matches();
	}
	/**
	 * Validate an email field. An email can contain any number of alphanumeric characters, 
	 * '@', any number of alphanumeric characters, '.', and 3 letters (com, net, org, etc.).
	 * @param input - input to validate
	 * @return - True if email is valid
	 * <br> - False if the email does not meet the requirements.
	 */
	public static boolean validEmail(String input) {
		Matcher m = emailPattern.matcher(input);
		return m.matches();
	}
	
	public void save() {
		try {
			FileOutputStream file = new FileOutputStream(dataFileName);
			ObjectOutputStream objOut = new ObjectOutputStream(file);
			objOut.writeObject(Database.getInstance());
			file.close();
			objOut.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Data file could not be created default file name: "
					+ dataFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
	}

	public void load(){
		try {
			FileInputStream file = new FileInputStream(dataFileName);
			ObjectInputStream objIn = new ObjectInputStream(file);
			database = (Database) objIn.readObject();
			file.close();
			objIn.close();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Data file could not be created default file name: "
					+ dataFileName);
			e.printStackTrace();
		}
		catch (IOException e) { 
            e.printStackTrace(); 
        } 
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Job> getAssignedJobs(){
		ArrayList<Job> assignedJobs = new ArrayList<Job>();
		for(Technician technician : this.getTechnicians()) {
			for(Job job: technician.getMyJobs()) {
				assignedJobs.add(job);
			}
		}
		return assignedJobs;
	}
	
	public ArrayList<Job> getAvailableJobs(){
		ArrayList<Job> availableJobs = new ArrayList<Job>();
		availableJobs.addAll(this.getJobs());
		availableJobs.removeAll(this.getAssignedJobs());
		return availableJobs;
	}
	
	//getter and setters
	public ArrayList<Technician> getTechnicians() {
		return technicians;
	}

	public void setTechnicians(ArrayList<Technician> technicians) {
		this.technicians = technicians;
	}
	public void addTechnician(Technician technician) {
		this.getTechnicians().add(technician);
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
		jobs.sort(new DateSorter());
		return jobs;
	}
	
	public void addJob(Job job) {
		this.jobs.add(job);	
		jobs.sort(new DateSorter());
	}
	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
		jobs.sort(new DateSorter());
	}
	
	public List<String> getStringJobs() {
		List<String> strings = getJobs().stream()
				.map(object -> Objects.toString(object, null)).collect(Collectors.toList());
		return strings;
	}

	public void addEquipment(Equipment equipment) {
		this.getEquipment().add(equipment);
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
