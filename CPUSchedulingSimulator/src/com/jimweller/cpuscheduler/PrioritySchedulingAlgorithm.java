/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
	private boolean preemptive;
	private PriorityQueue<Process> pqueue;
	private Process curJob;
	
    PrioritySchedulingAlgorithm(){
    	
    	Comparator<Process> comp = new PriorityComparator();
    	pqueue = new PriorityQueue<Process>(50, comp);
    	curJob = new Process();
    	curJob = null;
    	
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
    	
    	pqueue.add(p);
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
    	if(pqueue.contains(p))
    	{
    		pqueue.remove(p);
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
    		
    	while(!pqueue.isEmpty())
    	{
    		otherAlg.addJob(pqueue.peek());
    		pqueue.remove();
    	}
    }


    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
    	
    	Process nextJob = null;
 
    	
    	if(preemptive && !pqueue.isEmpty()) // Preemptive
    	{
    		nextJob = pqueue.peek();
    	}
    	else // Non-Preemptive
    	{
    		if (!pqueue.isEmpty())
    		{
    			if(curJob == null)
    			{
    				nextJob = pqueue.peek();
    				curJob = nextJob;
    			}
    		    if(curJob.isFinished())
    			{
    		    	nextJob = pqueue.peek();
    				curJob = nextJob;
    			}
    		    nextJob = curJob;
    		}
    	}
    	
    	return nextJob;
    }

    public String getName(){
	return "Single-queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
	return preemptive;
    }
    
    /**
     * @param v  Value to assign to preemptive.
     */
    public void setPreemptive(boolean  v){
	preemptive = v;
    }
    
    // Priority Comparator 
    
    private class PriorityComparator implements Comparator<Process>
    {

		@Override 
		public int compare(Process p1, Process p2) {
			
			int comp = 0;
			
			if(p1 != null && p2 != null)
			{
				if(p1.getPriorityWeight() < p2.getPriorityWeight())
				{
					comp = -1;
				}
				
				if(p1.getPriorityWeight() > p2.getPriorityWeight())
				{
					comp = 1;
				}
			
			}
		
			return comp;
		}
    	
    }
}