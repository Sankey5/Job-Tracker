package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;




public class Technician implements Serializable {

	private String name;
	// List of jobs owned by a technician
	private ArrayList<Job> myJobs;
	private ArrayList<Job> completedJobs;
	// List of equipment a technician is proficient with
	private ArrayList<Equipment> equipmentList;
	private String id;
	private String password;
	private String phoneNumber;
	private String stats;
	private String status;
	private String notice;
	
	public Technician() {
		this.myJobs = new ArrayList<Job>();
		this.equipmentList = new ArrayList<Equipment>();
		this.id = this.name;
		this.password = "";
		
	}
	
	/**
	 * This function attempts to give a job to a technician.
	 * If the technician does not have that job in their equipmentList,
	 * the job is not added.
	 * @param job - Job to give to the technician
	 * @return - True if job was successfully added.
	 * <br> - False if the job was not added.
	 */
	public boolean giveJob(Job job) {
		if(this.canUseEquipment(job.getEquipment())) {
			this.addJob(job);
			return true;
		}
		return false;
	}
	
	public void addEquipment(Equipment newEquipment) {
		this.equipmentList.add(newEquipment);
	}
	/**
	 * Checks if a technician has the ability to use the specified equipment
	 * @param equipmentQuery - Equipment required
	 * @return - True if the technician can use the specified equipment.
	 * <br> - False if the technician cannot use the given equipment
	 */
	private boolean canUseEquipment(Equipment equipmentQuery) {
		for(Equipment equipment : this.equipmentList) {
			if(equipment.compareTo(equipmentQuery)) {
				return true;
			}
		}
		return false;
	}
	
	private void addJob(Job job) {
		this.myJobs.add(job);
	}
	public ArrayList<Equipment> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(ArrayList<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Job> getMyJobs() {
		return myJobs;
	}
	public void setMyJobs(ArrayList<Job> myJobs) {
		this.myJobs = myJobs;
	}
	
	public List<String> getStringJobs() {
		List<String> strings = getMyJobs().stream()
				.map(object -> Objects.toString(object, null)).collect(Collectors.toList());
		return strings;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String toString() {
		return getName() + " - Jobs: " + getMyJobs().size();
	}
	
	public String toExtras() {
		return "Contact: " + getName() + " - " + getPhoneNumber() + "\nEquipment Registered: \n" 
				+ getEquipmentList().toString().replaceAll("\\[|, |\\]", "") + "Stats: "+ getStats() + "\nStatus: " + getStatus() + "\nNotices: \n" + getNotice();
	}

	public ArrayList<Job> getCompletedJobs() {
		return completedJobs;
	}

	public void setCompletedJobs(ArrayList<Job> completedJobs) {
		this.completedJobs = completedJobs;
	}
	
	public void removeJob(Job job) {
		this.myJobs.remove(job);
	}
	
	public void completedJob(Job job) {
		this.completedJobs.add(job);
		removeJob(job);
	}
	
}
