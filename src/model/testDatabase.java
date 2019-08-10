
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class testDatabase {
	private static Database database;

	public static void loadSampleData() {
		database = Database.getInstance();
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
		bill.setPhoneNumber("210456789");
		bill.setStatus("Sample");
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
		john.setPhoneNumber("2145784561");
		john.setStatus("Sample");
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
		
		Equipment solder2 = new Equipment("Watts", "Solder", "S-07");
		Equipment wiresplitter2 = new Equipment("Jameson", "wiresplitter", "6305");
		database.addEquipment(solder2);
		database.addEquipment(wiresplitter2);
		Customer carlHen = new Customer("AutoZone", "Carl Henries", 2101578321, "Carl@Autozone.com");
		Customer hannahBurns = new Customer("Resident", "Hannah Burns", 2108357123, "Hannah@Gmail.com");
		database.addCustomer(carlHen);
		database.addCustomer(hannahBurns);
		Job carlJob = new Job(carlHen, solder2, LocalDate.of(2019, 8, 25));
		Job hannahJob = new Job(hannahBurns, wiresplitter2, LocalDate.of(2019, 8, 13));
		carlJob.setPriority(Priority.Low);
		hannahJob.setPriority(Priority.Medium);
		database.addJob(carlJob);
		database.addJob(hannahJob);
		
		
		database.save();
	}
}