package sequential;

public class SequentialSolution {
	ArrayHandler handler;
	int[] fortyHightestScores;
	int range;
	
	public SequentialSolution(int range) {
		handler = new ArrayHandler();
		fortyHightestScores = new int[40];
		fortyHightestScores = handler.fillArrayRandom(fortyHightestScores, range);
		this.range = range;
	}
	
	public int[] getFortyHightest(){
		return fortyHightestScores;
	}
	public void execute(){
		int[] restResults = new int[range-40];
		long t1 = System.nanoTime();
		fortyHightestScores = handler.sortArray(getFortyHightest());
		double timeTakenSort = (System.nanoTime()-t1/1000000.0);
		restResults = handler.fillArrayRandom(restResults, range);
		long t2 = System.nanoTime();
		fortyHightestScores = handler.appendArrays(getFortyHightest(),restResults);
		double timeTakenSortAll = (System.nanoTime()-t2/1000000.0);
		System.out.println("Time taken sorting 40 biggest is: "+timeTakenSort+" ms");
		System.out.println("Time taken sorting all is: "+timeTakenSortAll+" ms");
	}
	
}
