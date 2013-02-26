/** FCFSSchedulingAlgorithm.java
 * 
 * A first-come first-served scheduling algorithm.
 *
 * @author: Mannchuoy Yam(16461232)
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    private Vector<Process> jobs;
    
	FCFSSchedulingAlgorithm(){
		activeJob = null;
		jobs = new Vector<Process>(); 	
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
    	jobs.add(p);
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
    	return jobs.remove(p);
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    	for (int i = jobs.size()-1; i >= 0; i--) {
    	    Process job = this.jobs.get(0);
    	    this.removeJob(job);
    	    otherAlg.addJob(job);
    	}
    	
    }


    public boolean shouldPreempt(long currentTime){
    	return true;
    	
    
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
    	activeJob = jobs.firstElement();
    	return activeJob;
    }

    public String getName(){
    	return "First-come first-served";
    }
}