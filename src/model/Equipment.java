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

import java.io.Serializable;
import java.util.ArrayList;

public class Equipment implements Serializable {
	
	private String equipmentMake;
	private String equipmentModel;
	private String equipmentSerial;
	
	
	
	public Equipment(String equipmentMake, String equipmentModel, String equipmentSerial) {
		super();
		this.equipmentMake = equipmentMake;
		this.equipmentModel = equipmentModel;
		this.equipmentSerial = equipmentSerial;
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
	
	public String toString() {
		return getEquipmentModel() + " - " +  getEquipmentMake() + " @" + getEquipmentSerial() + "\n";
	}


	/**
	 * Compares serial numbers of equipment to determine if potentially different instances
	 * of equipment refer to the same physical piece of equipment
	 * @param equipmentQuery - Equipment being evaluated.
	 * @return - True if the equipment is the same.<br> - False if the equipment is different.
	 */
	public boolean compareTo(Equipment equipmentQuery) {
		if(this.getEquipmentSerial().equals(equipmentQuery.getEquipmentSerial()))
			return true;
		return false;
	}

}
