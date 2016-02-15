package sequential;

import java.util.Arrays;

import arrayHandling.ArrayHandler;

import printing.FormatedPrint;

public class SequentialSolution {
	ArrayHandler handler;
	int[] fortyHightestScores;
	int range;
	FormatedPrint fp;
	public double timeUsed2A;
	public double timeUsedArraysSort;
	
	public SequentialSolution(int range) {
		fp = new FormatedPrint();
		handler = new ArrayHandler();
		fortyHightestScores = new int[40];
		this.range = range;
	}
	
	public int[] getFortyHighest(){
		return fortyHightestScores;
	}
	public void execute(){
		int[] sortTestArray = new int[range];
		int[] restResults = new int[range-40];
		fortyHightestScores = handler.fillArrayRandom(fortyHightestScores, range);
		restResults = handler.fillArrayRandom(restResults, range);
		sortTestArray = handler.fillArrayRandom(sortTestArray, range);
		long t2 = System.nanoTime();
		Arrays.sort(sortTestArray);
		timeUsedArraysSort = ((System.nanoTime()-t2)/1000000.0);
		long t1 = System.nanoTime();
		fortyHightestScores = handler.sortArray(getFortyHighest());
		fortyHightestScores = handler.appendArrays(getFortyHighest(),restResults);
		timeUsed2A = ((System.nanoTime()-t1)/1000000.0);
	}
	
}
