import parallel.ThreadMonitor;
import printing.FormatedPrint;
import sequential.SequentialSolution;


public class Oblig1 {
	static int range;
	static int numbThreads;
		
	public static void main(String[] args) {
		if(args.length == 0){
			range = 1000;
			numbThreads = 4;
			System.out.println("For custom range and number of threads run with arguments: Oblig1 -range -numberOfThreads. Now running with range 1000 and 4 threads");
		}else{
			range = Integer.parseInt(args[0]);
			numbThreads = Integer.parseInt(args[1]);
		}
		SequentialSolution seq = new SequentialSolution(range);
		ThreadMonitor tm = new ThreadMonitor(numbThreads, range);
		//For printing
		FormatedPrint fp = new FormatedPrint();
		fp.setNumberOfThreads(numbThreads);
		for(int i =0;i<9;i++){
			seq.execute();
			tm.execute();
			fp.addTime(seq.timeUsed2A, seq.timeUsedArraysSort, tm.timeUsed, i);		
		}
		fp.print(range);
		
	}
}
