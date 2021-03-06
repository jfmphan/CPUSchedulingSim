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


<<<<<<< HEAD
    private Vector<Process> jobs;
    
	FCFSSchedulingAlgorithm(){
		activeJob = null;
		jobs = new Vector<Process>(); 	
=======
public class FCFSSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    private PriorityQueue<Process> pqueue;
    
	FCFSSchedulingAlgorithm(){
		activeJob = null;
		Comparator<Process> comp = new FCComparator();
    	pqueue = new PriorityQueue<Process>(50, comp);
>>>>>>> Justin's-Branch
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
<<<<<<< HEAD
    	jobs.add(p);
=======
    	pqueue.add(p);
>>>>>>> Justin's-Branch
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
    	
<<<<<<< HEAD
    	return jobs.remove(p);
=======
    	return pqueue.remove(p);
>>>>>>> Justin's-Branch

    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
<<<<<<< HEAD
    	for (int i = jobs.size()-1; i >= 0; i--) {
    	    Process job = this.jobs.get(0);
    	    this.removeJob(job);
    	    otherAlg.addJob(job);
=======
    	
    	while(!pqueue.isEmpty())
    	{
    		otherAlg.addJob(pqueue.peek());
    		pqueue.remove();
>>>>>>> Justin's-Branch
    	}
    	
    }


    public boolean shouldPreempt(long currentTime){
    	return true;
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){

<<<<<<< HEAD
    	activeJob = jobs.firstElement();
=======
    	activeJob = pqueue.peek();
>>>>>>> Justin's-Branch
    	return activeJob;

    }

    public String getName(){
    	return "First-come first-served";
    }
    
    private class FCComparator implements Comparator<Process>
    {
    	public int compare(Process p1, Process p2) 
		{
			
			int comp = 0;
			
			if(p1 != null && p2 != null)
			{
				if(p1.getArrivalTime() < p2.getArrivalTime())
				{
					comp = -1;
				}
				
				if(p1.getArrivalTime() > p2.getArrivalTime())
				{
					comp = 1;
				}
			
			}
		
			return comp;
		}
    }

}