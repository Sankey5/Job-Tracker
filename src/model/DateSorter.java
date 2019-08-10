package model;

import java.util.Comparator;

public class DateSorter implements Comparator<Job> {

	@Override
	public int compare(Job j1, Job j2) {
		// TODO Auto-generated method stub
		return j1.getDeadlineEntry().compareTo(j2.getDeadlineEntry());
	}
}
