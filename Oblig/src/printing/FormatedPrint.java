package printing;

import java.io.PrintWriter;
import java.util.Arrays;

	


public class FormatedPrint {
	
	PrintWriter printer;
	double[] seqA2,seqArray,parA2;
	int numberOfThreads;
		
	
	public FormatedPrint() {
		seqA2 = new double[9];
		seqArray = new double[9];
		parA2 = new double[9];
		try{
			printer = new PrintWriter("results.txt");
		}catch(Exception e){
			System.err.println("Exception "+e);
		}
	}	
	
	public void print(int range){
		Arrays.sort(seqA2);
		Arrays.sort(seqArray);
		Arrays.sort(parA2);
		try{
			int kernals = Runtime.getRuntime().availableProcessors(); 
			printer.println("-----------------------------------------");
			printer.println("Machine has "+kernals+" processor kernals");
			printer.println("Processor: Intel(R) Core(TM) i5-2400");
			printer.println("Speed is: 3,4 GHz");
			printer.println("Running with range: "+range);
			printer.format("Time taken own A2 seq:  %12.6f ms",seqA2[4]);
			printer.println();
			printer.format("Time taken Arrays.sort: %12.6f ms",seqArray[4]);
			printer.println();
			printer.format("Time taken parallell:   %12.6f ms",parA2[4]);
			printer.println();
			printer.println("With "+numberOfThreads+" threads");
			printer.println("-----------------------------------------");
			printer.flush();
			System.out.println("Printing Successful");
		}catch(Exception e){
			System.err.println("Exception "+e);
		}
		
	}
	
	public void addTime(double timeUsed2A, double timeUsedArraysSort,double timeUsed, int i) {
		seqA2[i]=timeUsed2A;
		seqArray[i]=timeUsedArraysSort;
		parA2[i]=timeUsed;
		
	}
	public void setNumberOfThreads(int number){
		this.numberOfThreads = number;
		
	}
}
