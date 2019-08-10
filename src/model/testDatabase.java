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
		Equipment carJack = new Equipment("Champion", "Jack5000", "5001");
		Equipment wrench = new Equipment("Stanley", "big wrench", "5002");
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
		database.addJob(bobJob);
		
		database.save();
	}
}