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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class testDatabase {
	private static Database database;

	public static void loadSampleData() {
		database = Database.getInstance();
		/*
		int j = 6; //Number of jobs
		int w = 4; // Number of workers
		int i = 0;
		int k = 0;
		
		for(k = 0; k < w; k++) {
			Technician temp = new Technician("Worker");
			temp.setName("Worker" + k);
			temp.setPhoneNumber("210-458-675" + k);
			temp.setStatus("New Hire");
			temp.setNotice("Welcome aboard!\nWe have plenty of work to do!");
			System.out.println(temp);
			for(i = 0; i < j; i++) {
				Customer guy = new Customer("Comapany"+i, "Customer"+i, 210456123+i, "Customer"+i+"@email.com");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
				Equipment tool = new Equipment("Make"+i, "Model"+i, "Serial"+i);
				Job work = new Job(guy, tool, LocalDate.of(2020, 2, i+1));
				work.setPriority(Priority.High);
				ArrayList<Equipment> inventory = new ArrayList<Equipment>();
				inventory.add(tool);
				database.addEquipment(tool);
				database.addCustomer(guy);
				temp.addEquipment(tool);
				temp.giveJob(work);
			}
			
			for(i = j; i < j*2; i++) {
				Customer guy = new Customer("Company"+i, "Customer"+i, 210147258+i,"Customer"+i+"@email.com");
				Equipment tool = new Equipment("Make"+(i-2), "Model"+(i-2), "Serial"+(i-2));
				Job work = new Job(guy, tool, LocalDate.of(2020, 2, i+1));
				work.setPriority(Priority.Medium);
				database.addEquipment(tool);
				database.addCustomer(guy);
				database.addJob(work);
			}
			for(i = 0; i < j/2 ;i++) {
				Customer guy = new Customer("Company"+(i+(j*5)), "Customer"+(i+(j*5)), 210369258+i, "Customer"+(i+(j*5))+"@email.com");
				Equipment tool = new Equipment("Make"+i, "Model"+i, "Serial"+i);
				LocalDate date = LocalDate.of(2020, 5, i+1);
				Job work = new Job(guy, tool, date);
				work.setPriority(Priority.Low);
				temp.giveJob(work);
				temp.completedJob(work);
			}
			database.addTechnician(temp);

		}
		*/
		Equipment carJack = new Equipment("Champion", "Jack5000", "kjw-01");
		Equipment wrench = new Equipment("Stanley", "big wrench", "3002");
		ArrayList<Equipment> inventory = new ArrayList<Equipment>();
		inventory.add(carJack);
		inventory.add(wrench);
		database.addEquipment(carJack);
		database.addEquipment(wrench);
		Customer bobSmith = new Customer("Discount Tire Co.", "Bob Smith", 2105559876, "Bob@DiscountTire.com");
		Customer annitaDrinque = new Customer("Brake Check", "Annita Drinque", 2108880123, "Annita@BrakeCheck.net");
		database.addCustomer(bobSmith);
		database.addCustomer(annitaDrinque);
		Technician bill = new Technician("Bill");
		bill.setEquipmentList(inventory);
		database.addTechnician(bill);
		Job bobJob = new Job(bobSmith, carJack, LocalDate.of(2019, 8, 20));
		Job annitaJob = new Job(annitaDrinque, wrench,LocalDate.of(2019, 8, 9));
		annitaJob.setPriority(Priority.Medium);
		bobJob.setPriority(Priority.Low);
		database.addJob(bobJob);
		database.addJob(annitaJob);
		
		Equipment screwdriver = new Equipment("Phillips", "ScrewDriver", "5001");
		Equipment hammer = new Equipment("Haxx", "Hammer", "5002");
		ArrayList<Equipment> inventory2 = new ArrayList<Equipment>();
		inventory2.add(screwdriver);
		inventory2.add(hammer);
		database.addEquipment(screwdriver);
		database.addEquipment(hammer);
		Customer timAnders = new Customer("Walmart", "Timothy Anders", 2105559876, "Timothy@Walmart.com");
		Customer janeFergurson = new Customer("Express Oil", "Jane Fergurson", 2108880123, "Jane@ExpressOil.net");
		database.addCustomer(timAnders);
		database.addCustomer(janeFergurson);
		Technician john = new Technician("John");
		john.setEquipmentList(inventory2);
		database.addTechnician(john);
		Job janeJob = new Job(janeFergurson, hammer, LocalDate.of(2019, 8, 21));
		Job timJob = new Job(timAnders, screwdriver, LocalDate.of(2019, 8, 10));
		janeJob.setPriority(Priority.High);
		timJob.setPriority(Priority.Medium);
		database.addJob(janeJob);
		database.addJob(timJob);
		
		Equipment solder = new Equipment("Watts", "Solder", "S-01");
		Equipment wiresplitter = new Equipment("Jameson", "wiresplitter", "5702");
		database.addEquipment(solder);
		database.addEquipment(wiresplitter);
		Customer sarahAnders = new Customer("Walmart", "Sarah Anders", 2101594321, "Sarah@Walmart.com");
		Customer matthewFergurson = new Customer("Express Oil", "Matthew Fergurson", 2108146123, "Matthew@ExpressOil.net");
		database.addCustomer(sarahAnders);
		database.addCustomer(matthewFergurson);
		Job sarahJob = new Job(matthewFergurson, solder, LocalDate.of(2019, 8, 22));
		Job matthewJob = new Job(sarahAnders, wiresplitter, LocalDate.of(2019, 8, 11));
		sarahJob.setPriority(Priority.High);
		matthewJob.setPriority(Priority.Medium);
		database.addJob(sarahJob);
		database.addJob(matthewJob);
		bill.addEquipment(wiresplitter);
		john.addEquipment(solder);
		database.save();
	}
}