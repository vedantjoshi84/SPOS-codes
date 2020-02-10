import java.util.Scanner;


public class Priority {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n;
		System.out.println("Enter no of processess");
		n=sc.nextInt();
		
		int pid[]= new int[n];
		int burstTime[]= new int[n];
		int priority[]= new int[n];
		
		System.out.println("Enter burst time");
		for(int i=0;i<n;i++)
		{
			burstTime[i]=sc.nextInt();
			pid[i]= i+1;
		}
		
		System.out.println("Enter priority");
		for(int i=0;i<n;i++)
		{
			priority[i]=sc.nextInt();
		}
		
		findAvgTime(pid, n, burstTime, priority);

	}

	private static void findAvgTime(int[] pid, int n, int[] burstTime,
			int[] priority) {
		
		int total_wt=0, total_tt=0;
		int wt[]= new int[n];
		int tat[]= new int[n];
		
		findWaitingTime(pid, n, burstTime, wt, priority);
		findTurnAroundTime(pid, n, burstTime, tat, wt);
		
		System.out.println("Process" + "\t" + "Burst Time" + "\t" + "Waiting Time" + "\t" + "Turn Around Time");

		 for (int  i=0; i<n; i++) 
		 {
		 
	        total_wt = total_wt + wt[i]; 
	        total_tt = total_tt + tat[i]; 
	        System.out.println(pid[i] + "\t\t" + burstTime[i] + "\t\t" + wt[i] + "\t\t" + tat[i]); 
		 } 
		  
		 System.out.println("Average waiting time = " + (float)total_wt / (float)n);
		 System.out.println("Average turn around time when arrival time given = " + (float)total_tt / (float)n);
		
	}

	private static void findTurnAroundTime(int[] pid, int n, int[] bt,
			int[] tat, int[] wt) {
		
		for(int i=0;i<n;i++)
		{
			tat[i]=wt[i]+bt[i];
		}
		
	}

	private static void findWaitingTime(int[] pid, int n, int[] bt,
			int[] wt, int[] pr) {
		
		int temp_pid=0, temp_bt=0, temp_pr=0;
		
		for(int i=0;i<n-1;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				if(pr[j] > pr[j+1])
				{
					temp_pr= pr[j];
					pr[j]= pr[j+1];
					pr[j+1]= temp_pr;
					
					temp_pid= pid[j];
					pid[j]= pid[j+1];
					pid[j+1]= temp_pid;
					
					temp_bt= bt[j];
					bt[j]= bt[j+1];
					bt[j+1]= temp_bt;
				}
			}
		}
		
		wt[0]=0;
		for(int i=1;i<n;i++)
		{
			wt[i]=wt[i-1]+bt[i-1];
		}
		
	}

}
