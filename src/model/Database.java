package model;

public class Database {
	private static Database database;
	private static String customerFileName, equipmentFileName, jobFileName;
	private CustomerManager customerManager;
	private EquipmentManager equipmentManager;
	private JobManager jobManager;
	
	private Database() {
		updateDatabase();
	}

	/**
	 * A Singleton implemented database. This ensures there is only ever one instance of the database
	 * @return
	 */
	public static Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}
	
	public void updateDatabase() {
		this.customerManager = new CustomerManager();
		this.customerManager.setCustomerFileName(customerFileName);
		this.customerManager.updateCustomers();
		
		this.equipmentManager = new EquipmentManager();
		this.equipmentManager.setEquipmentFileName(equipmentFileName);
		this.equipmentManager.updateEquipment();
		
		this.jobManager = new JobManager();
		this.jobManager.setJobFileName(jobFileName);
		this.jobManager.updateJobs();
	}
	
	public void saveDatabase() {
		this.customerManager.saveCustomers();
		this.equipmentManager.saveEquipment();
		this.jobManager.saveJobs();
	}
	public CustomerManager getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}
	public EquipmentManager getEquipmentManager() {
		return equipmentManager;
	}
	public void setEquipmentManager(EquipmentManager equipmentManager) {
		this.equipmentManager = equipmentManager;
	}
	public JobManager getJobManager() {
		return jobManager;
	}
	public void setJobManager(JobManager jobManager) {
		this.jobManager = jobManager;
	}
}
