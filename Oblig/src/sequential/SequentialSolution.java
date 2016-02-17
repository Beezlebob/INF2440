package sequential;

import java.util.Arrays;

import arrayHandling.ArrayHandler;

public class SequentialSolution {
	ArrayHandler handler;
	int[] fortyHighestScores;
	int range;
	public double timeUsed2A;
	public double timeUsedArraysSort;
	
	public SequentialSolution(int range) {
		handler = new ArrayHandler();
		fortyHighestScores = new int[40];
		this.range = range;
	}
	
	public int[] getFortyHighest(){
		return fortyHighestScores;
	}
	public void execute(){
		int[] sortTestArray = new int[range];
		int[] restResults = new int[range-40];
		fortyHighestScores = handler.fillArrayRandom(fortyHighestScores, range);
		restResults = handler.fillArrayRandom(restResults, range);
		sortTestArray = handler.fillArrayRandom(sortTestArray, range);
		long t2 = System.nanoTime();
		Arrays.sort(sortTestArray);
		timeUsedArraysSort = ((System.nanoTime()-t2)/1000000.0);
		long t1 = System.nanoTime();
		fortyHighestScores = handler.sortArray(fortyHighestScores);
		fortyHighestScores = handler.appendArrays(fortyHighestScores,restResults);
		timeUsed2A = ((System.nanoTime()-t1)/1000000.0);
	}
	
}
