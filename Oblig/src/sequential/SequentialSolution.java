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
		fortyHightestScores = handler.sortArray(getFortyHightest());
		restResults = handler.fillArrayRandom(restResults, range);
		fortyHightestScores = handler.appendArrays(getFortyHightest(),restResults);		
	}
	
}
