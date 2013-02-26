/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.PriorityQueue;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
	private boolean preemptive;
	private PriorityQueue<Process> pqueue;
	
    PrioritySchedulingAlgorithm(){
    	pqueue = new PriorityQueue<Process>();
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
    	Process curJob = pqueue.peek();
    	
    	if(preemptive && (curJob != null)) // Preemptive
    	{
    		if(curJob.isFinished())
    		{
    			pqueue.remove();
    			nextJob = pqueue.peek();
    		}
    		nextJob = curJob;
    	}
    	else // Non-Preemptive
    	{
    		if (!pqueue.isEmpty())
    		{
    			if (currentTime == 0)
    			{
    				nextJob = pqueue.peek();
    			}
    			else
    			{
    				if(currentTime == curJob.getFinishTime())
    				{
    					pqueue.remove();
    					nextJob = pqueue.peek();
    				}
    			}
    			
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
}