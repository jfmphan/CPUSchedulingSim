/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;
//import java.util.Comparator;
//import com.jimweller.cpuscheduler.PrioritySchedulingAlgorithm.PriorityComparator;


public class SJFSchedulingAlgorithm extends PrioritySchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    private boolean preemptive;
 
	private PriorityQueue<Process> QQ;
	private Process curJob;
	
    SJFSchedulingAlgorithm()
    {
    	Comparator<Process> RTcomp = new RTComparator();
    	QQ = new PriorityQueue<Process>(50, RTcomp);
    	curJob = new Process();
    	curJob = null;

    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p)
    {
    	QQ.add(p);
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p)
    {
    	if(QQ.contains(p))
    	{
    		QQ.remove(p);
    		return true;
    	}
    	else
    			return false;
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
	when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) 
    {
    	while(!QQ.isEmpty())
    	{
    		otherAlg.addJob(QQ.peek());
    		QQ.remove();
    	}
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime)
    {
Process nextJob = null;
 
    	
    	if(preemptive && !QQ.isEmpty()) // Preemptive
    	{
    		nextJob = QQ.peek();
    	}
    	else // Non-Preemptive
    	{
    		if (!QQ.isEmpty())
    		{
    			if(curJob == null)
    			{
    				nextJob = QQ.peek();
    				curJob = nextJob;
    			}
    		    if(curJob.isFinished())
    			{
    		    	nextJob = QQ.peek();
    				curJob = nextJob;
    			}
    		    nextJob = curJob;
    		}
    	}
    	
    	return nextJob;
    }

    public String getName()
    {
	return "Shortest job first";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive()
    {
	return preemptive;
    }
    
    /**
     * @param v  Value to assign to preemptive.
     */
    public void setPreemptive(boolean  v)
    {
	preemptive = v;
    }
    
    //Remaining time comparator
    private class RTComparator implements Comparator<Process>
    {
    	public int compare(Process p1, Process p2) 
		{
			
			int comp = 0;
			
			if(p1 != null && p2 != null)
			{
				if(p1.getBurstTime() < p2.getBurstTime())
				{
					comp = -1;
				}
				
				if(p1.getBurstTime() > p2.getBurstTime())
				{
					comp = 1;
				}
			
			}
		
			return comp;
		}
    }
     
}