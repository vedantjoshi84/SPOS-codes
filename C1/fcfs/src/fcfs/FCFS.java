package fcfs;
import java.util.*;

public class FCFS {
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		int n;
		System.out.println("Enter no of processess");
		n=sc.nextInt();
		
		int pid[]= new int[n];
		int burstTime[]= new int[n];
		int arrivalTime[]= new int[n];
		
		System.out.println("Enter process id's");
		for(int i=0;i<n;i++)
		{
			pid[i]=sc.nextInt();
		}
		
		System.out.println("Enter burst time");
		for(int i=0;i<n;i++)
		{
			burstTime[i]=sc.nextInt();
		}
		
		System.out.println("Enter Arrival time");
		for(int i=0;i<n;i++)
		{
			arrivalTime[i]= sc.nextInt();
		}
		
		findAvgTime(pid, n, burstTime, arrivalTime);
	}
private static void findAvgTime(int[] pid, int n, int[] burstTime, int[] arrivalTime) {
	
	int total_wt = 0, total_tat = 0; 
	int wt[]= new int[n];
	int tat[]= new int[n];

	int total_wta = 0, total_tata =0;
	int wta[]= new int[n];
	int tata[]= new int[n];
	
	findWaitingTime(pid, n, burstTime, wt);
	findTurnAroundTime(pid, n, burstTime, tat, wt);
	
	System.out.println("Process" + "\t" + "Burst Time" + "\t" + "Waiting Time" + "\t" + "Turn Around Time");
	
	 for (int  i=0; i<n; i++) 
	    { 
	        total_wt = total_wt + wt[i]; 
	        total_tat = total_tat + tat[i]; 
	        System.out.println(i+1 + "\t\t" + burstTime[i] + "\t\t" + wt[i] + "\t\t" + tat[i]); 
	    } 
	  
	 System.out.println("Average waiting time = " + (float)total_wt / (float)n);
	 System.out.println("Average turn around time = " + (float)total_tat / (float)n);
	 
	 System.out.println("Waiting time when Arrival time is given: " + "\n" + "Process" + "\t" + "Waiting time" + "\t" + "Turn Around Time");
	 for(int i=0;i<n;i++)
	 {
		wta[i] = wt[i]	- arrivalTime[i];
		tata[i] = burstTime[i] + wta[i];
		System.out.println(i + "\t" + wta[i] + "\t" + tata[i]);
	 }
	 
	 for (int  i=0; i<n; i++) 
	    { 
	        total_wta = total_wta + wta[i]; 
	        total_tata = total_tata + tata[i]; 
	        System.out.println(i+1 + "\t\t" + burstTime[i] + "\t\t" + wta[i] + "\t\t" + tata[i]); 
	    }
	 System.out.println("Average waiting time when arrival time given = " + (float)total_wta / (float)n);
	 System.out.println("Average turn around time when arrival time given = " + (float)total_tata / (float)n);
	 
	
	}
private static void findTurnAroundTime(int[] pid, int n, int[] burstTime,
		int[] tat, int[] wt) {
	
	for (int  i = 0; i < n ; i++) 
        tat[i] = burstTime[i] + wt[i]; 
	
}
private static void findWaitingTime(int[] pid, int n, int[] burstTime, int[] wt) {
	
	wt[0]=0;
	
	for(int i=1;i<n;i++)
	{
		wt[i]= wt[i-1] + burstTime[i-1];
	}
	
}
}

