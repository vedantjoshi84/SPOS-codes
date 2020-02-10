package sjfP;
import java.util.*;

public class sjf {

	private static final int INT_MAX = 999;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n;
		
		System.out.println("Enter no of processess");
		n=sc.nextInt();
		int ct[]= new int[n];
		
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
		
		System.out.println("Enter arrival time");
		for(int i=0;i<n;i++)
		{
			arrivalTime[i]=sc.nextInt();
		}
	
		findAvgTime(pid, n, burstTime, arrivalTime, ct);

	}

	private static void findAvgTime(int[] pid, int n, int[] burstTime, int[] arrivalTime, int[] ct) {
		
		int total_wt = 0, total_tat = 0; 
		int wt[]= new int[n];
		int tat[]= new int[n];
		
		findWaitingTime(pid, n, burstTime, arrivalTime, wt, ct);
		findTurnAroundTime(pid, n, arrivalTime, tat, ct);
		
		for(int i=0;i<n;i++)
		{
			wt[i]= tat[i] - burstTime[i];
			total_wt = total_wt + wt[i];
			total_tat = total_tat + tat[i];
		}
		
		System.out.println("pid  arrival  burst  complete turn waiting");
	    for(int i=0;i<n;i++)
	    {
	    	System.out.println(pid[i] +"\t"+ arrivalTime[i]+"\t"+ burstTime[i] +"\t"+ ct[i] +"\t"+ tat[i] +"\t"+ wt[i]);
	    }
	    
	    System.out.println("\naverage tat is "+ (float)(total_wt/n));
	    System.out.println("average wt is "+ (float)(total_tat/n));
		
	}

	private static void findTurnAroundTime(int[] pid, int n, int[] at, int[] tat,
			int[] ct) {
		for(int i=0;i<n;i++)
		{
			tat[i]= ct[i] - at[i];
		}
		
	}

	private static void findWaitingTime(int[] pid, int n, int[] bt, int[] at,
			int[] wt, int[] ct) {
		
		int st=0,c=n,tot=0,min=INT_MAX;
		int flag[] = new int[n];
		int rt[]= new int[n];
		
		for(int i=0;i<n;i++)
		{
			flag[i]=0;
			rt[i]= bt[i];
		}
		
		while(true)
		{
			if(tot==n)
			{
				break;
			}
			
			for(int i=0;i<n;i++)
			{
				if(at[i]<=st && flag[i]==0 && rt[i]<min)
				{
					min= rt[i];
					c=i;
				}
			}
			
			if(c==n)
			{
				st++;
			}
			else
			{
				rt[c]--;
				st++;
				if(rt[c]==0)
				{
					ct[c]= st;
					flag[c]=1;
					tot++;
				}
			}
		}
		
	}

}
