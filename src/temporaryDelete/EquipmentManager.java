package temporaryDelete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
	
	private ArrayList<Equipment> equipment;
	private String equipmentFileName;
	
	public EquipmentManager() {
		this.equipment = new ArrayList<Equipment>();
	}
	
	/**
	 * This function will update the equipment list
	 * The equipment file is read and the current list is overridden.
	 * Note: EquipmentManager.equipmentFileName must hold the name of the equipment file.
	 */
	public void updateEquipment() {
		ArrayList<Equipment> updatedEquipment = new ArrayList<Equipment>();
		File file = new File(equipmentFileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokens = line.split(",");
				updatedEquipment.add(Equipment.generateFromTokens(tokens));
			}
			this.setEquipment(updatedEquipment);
		}
		catch (FileNotFoundException e) {
			// prompt the user to locate the equipment file
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}

	public void saveEquipment() {
		try {
			FileWriter writer = new FileWriter(this.equipmentFileName);
			for(Equipment equipment : this.equipment) {
				writer.write(equipment.toSaveFormat());
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// prompt user to locate equipmentFile
			e.printStackTrace();
		}
		catch (IOException e){ 
			e.printStackTrace();
		}
	}
	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(ArrayList<Equipment> equipment) {
		this.equipment = equipment;
	}

	public String getEquipmentFileName() {
		return equipmentFileName;
	}

	public void setEquipmentFileName(String equipmentFileName) {
		this.equipmentFileName = equipmentFileName;
	}
	

}
