import java.util.ArrayList;


public class Oblig2 {
	static EratosthenesSil es;
	
	public static void main(String[] args) {
		final long doubleOfMaxNum = Long.parseLong(args[0])*Long.parseLong(args[0])-1;
		es = new EratosthenesSil(Integer.parseInt(args[0]));
		es.generatePrimesByEratosthenes();
		runFactorization(doubleOfMaxNum);
		ThreadMonitor tm = new ThreadMonitor(8,Integer.parseInt(args[0]));
		tm.generatePrimes();
		long t3 = System.nanoTime();//starting the clock
		ArrayList<ArrayList<Long>> threadFactors = tm.factorize(doubleOfMaxNum);
		double timeUsedFacPar = (System.nanoTime()-t3)/1000000.0; //Stopping clock
		System.out.println(timeUsedFacPar);
		for(int i=0;i<threadFactors.size()-1;i++){
			System.out.print(doubleOfMaxNum-i+" = ");
			printFactors(threadFactors.get(i));
		}
		
	}
	static void runFactorization(long num){
	ArrayList<ArrayList> doneFactors = new ArrayList<ArrayList>();
	System.out.println("Running Factorization...");
	System.out.println("Progress");
	System.out.print("|");
		long t1 = System.nanoTime();//starting the clock	
		for(int i=0;i<100;i++){
			//System.out.println("factorizing "+(num-i));
			doneFactors.add(es.factorize(num-i));
			if(i%10==0){
				System.out.print("-");
			}
		}
		double timeUsed = (System.nanoTime()-t1)/1000000.0; //Stopping clock
		System.out.println("|");
		for(int j=doneFactors.size()-1;j>=0;j--){
			if(j<11){
				System.out.print((num-j)+" = ");
				printFactors(doneFactors.get(j));
				System.out.println("");
			}else if(j>doneFactors.size()-3){
				System.out.print((num-j)+" = ");
				printFactors(doneFactors.get(j));
				System.out.println("");
			}else if(j==50){
				System.out.println("");
				System.out.println("---------------------------");
				System.out.println("");
			}
		}
		System.out.printf("100 Faktoriseringen beregnet på %12.4f ms\n",timeUsed);
		System.out.printf("dvs: %8.4f ms per faktorisering\n",timeUsed/100);
	}
	static void printFactors(ArrayList<Long> factors){
		System.out.print(factors.get(0));
		for(int i=1;i<factors.size();i++){
			System.out.print("*");
			System.out.print(factors.get(i));
		}
		System.out.println();
	}
}
