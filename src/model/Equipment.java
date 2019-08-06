package model;

import java.util.ArrayList;

public class Equipment {
	
	private String equipmentMake;
	private String equipmentModel;
	private String equipmentSerial;
	
	
	
	public Equipment(String equipmentMake, String equipmentModel, String equipmentSerial) {
		super();
		this.equipmentMake = equipmentMake;
		this.equipmentModel = equipmentModel;
		this.equipmentSerial = equipmentSerial;
	}
	
	public static Equipment generateFromTokens(String[] tokens) {
		// TODO: Unit test that all are valid fields
		return new Equipment(tokens[0], tokens[1], tokens[2]);
	}
	public static Equipment generateFromTokenList(ArrayList<String> tokens) {
		return new Equipment(tokens.get(0), tokens.get(1), tokens.get(2));
	}
	public String getEquipmentMake() {
		return equipmentMake;
	}
	public void setEquipmentMake(String equipmentMake) {
		this.equipmentMake = equipmentMake;
	}
	public String getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getEquipmentSerial() {
		return equipmentSerial;
	}
	public void setEquipmentSerial(String equipmentSerial) {
		this.equipmentSerial = equipmentSerial;
	}
	
	public String toSaveFormat() {
		String ret = this.getEquipmentMake() + "," + this.getEquipmentModel() + "," + this.getEquipmentSerial();
		return ret;
	}

}
