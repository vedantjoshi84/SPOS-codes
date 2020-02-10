import java.util.Scanner;


public class RoundR {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n;
		System.out.println("Enter no of processess");
		n=sc.nextInt();
		
		int pid[]= new int[n];
		int burstTime[]= new int[n];
		
		System.out.println("Enter Burst Time");
		for(int i=0;i<n;i++)
		{
			burstTime[i]=sc.nextInt();
			pid[i]=i+1;
		}
		int timeSlice;
		System.out.println("Enter time slice");
		timeSlice = sc.nextInt();
		
		findAvgTime(pid, n, burstTime, timeSlice);

	}

	private static void findAvgTime(int[] pid, int n, int[] burstTime,
			int timeSlice) {
		
		int wt[]= new int[n], tat[]=new int[n], total_wt = 0, total_tat = 0; 
		  
	    findWaitingTime(pid, n, burstTime, wt, timeSlice); 
	  
	    findTurnAroundTime(pid, n, burstTime, wt, tat); 
	  
	    System.out.println("Processes " + "\t" + "Burst time " + "\t" +
	         " Waiting time " + "\t" + "Turn around time");
	    for (int i=0; i<n; i++) 
	    { 
	        total_wt = total_wt + wt[i]; 
	        total_tat = total_tat + tat[i];
	        System.out.println(pid[i] + "\t\t" + burstTime[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
	    } 
	  
	    System.out.println("Average waiting time = " + (float)total_wt / (float)n);
		System.out.println("Average turn around time when arrival time given = " + (float)total_tat / (float)n); 
		
	}

	private static void findTurnAroundTime(int[] pid, int n, int[] bt,
			int[] wt, int[] tat) {
		for(int i=0;i<n;i++)
		{
			tat[i]=wt[i]+bt[i];
		}
	}

	private static void findWaitingTime(int[] pid, int n, int[] bt,
			int[] wt, int timeSlice) {
		
		int rem_bt[]= new int[n]; 
	    for (int i = 0 ; i < n ; i++) 
	    {
	    	rem_bt[i] =  bt[i]; 
	    }
	      
	    int t = 0;
	    
	    while(true)
	    {
	    	boolean done=true;
	    	
	    	for(int i=0;i<n;i++)
	    	{
	    		
	    		if(rem_bt[i] > 0)
	    		{
	    			done= false;
	    			
	    			if(rem_bt[i] > timeSlice)
	    			{
	    				t+=timeSlice;
	    				
	    				rem_bt[i]-=timeSlice;
	    			}
	    			else
	    			{
	    				t+=rem_bt[i];
	    				wt[i] = t - bt[i];
	    				rem_bt[i]=0;
	    			}
	    		}
	    	}
	    	if(done==true)
	    	{
	    		break;
	    	}
	    }
		
	}

}
