package temporaryDelete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Job;

public class JobManager {
	private ArrayList<Job> jobs;
	private String jobFileName;
	
	public JobManager() {
		this.jobs = new ArrayList<Job>();
	}

	public void addJob(Job job) {
		this.jobs.add(job);
	}
	public ArrayList<Job> getJobs() {
		return jobs;
	}
	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}
	public String getJobFileName() {
		return jobFileName;
	}
	public void setJobFileName(String jobFileName) {
		this.jobFileName = jobFileName;
	}
	
	/**
	 * This function will update the list of jobs by reading the job file.
	 * Note that this function will override any previous jobs in the jobs list
	 * with whatever is located in the job file.
	 */
	public void updateJobs() {
		ArrayList<Job> updatedJobs = new ArrayList<Job>();
		File file = new File(jobFileName);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] tokensArray = line.split(",");
				ArrayList<String> tokens = new ArrayList<String>();
				for (String token : tokensArray) {
					tokens.add(token);
				}
				updatedJobs.add(Job.generateFromTokenList(tokens));
			}
			this.setJobs(updatedJobs);
		}
		catch (FileNotFoundException e) {
			// prompt the user to locate the equipment file
			e.printStackTrace();
		}
		finally {
			scanner.close();
		}
	}

	/**
	 * This function will save the current list of jobs to the job file
	 * Note that this will override anything currently in the job file.
	 */
	public void saveJobs() {
		try {
			FileWriter writer = new FileWriter(this.jobFileName);
			for(Job job : this.jobs) {
				writer.write(job.toSaveFormat());
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// prompt user to locate jobfile
			e.printStackTrace();
		}
		catch (IOException e){ 
			e.printStackTrace();
		}
	}
}
