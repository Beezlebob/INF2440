package testing;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import arrayHandling.ArrayHandler;

import sequential.*;

import junit.framework.TestCase;

public class Part1TestSequential extends TestCase {

	/**
	 * @param args
	 */
	Logger logger;	
	
	public Part1TestSequential() {
		createLogger();
	}
 
	
//	 @Before
	public void createLogger() {
		// Create a logger.
		logger = Logger.getLogger(Part1TestSequential.class.getName());
		Handler[] handlers = logger.getHandlers();
		for (int i = 0; i < handlers.length; ++i) {
			logger.removeHandler(handlers[i]);
		}
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		
    //  logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);		
	}
	
	
//	 @Test SequentialSolutioin
	public void testGetFortyHighest(){
		SequentialSolution seq = new SequentialSolution(1000);
		assertNotNull(seq.getFortyHighest());
	}
// 	 @Test Execute
	public void testExecute(){
		SequentialSolution seq = new SequentialSolution(1000);
		int[] testArray = seq.getFortyHighest();
		seq.execute();
		assertNotSame(testArray[39],seq.getFortyHighest()[39]);
	}
//	 @Test with array.sort()
	public void testArraySortGeneric(){
		ArrayHandler handler = new ArrayHandler();
		int range = 1000;
		int[] testArrayA = new int[40];
		int[] testArrayB = new int[40];
		testArrayA = handler.fillArrayRandom(testArrayA, range);
		for(int i=0;i<testArrayA.length;i++){
			testArrayB[i] = testArrayA[i];
		}
		testArrayA = handler.sortArray(testArrayA);
		Arrays.sort(testArrayB);
		assertTrue(testArrayA[0]==testArrayB[39]);
		assertTrue(testArrayA[39]==testArrayB[0]);
		
	}
	
	public static void main(String[] args) {
		Part1TestSequential test = new Part1TestSequential();
		test.createLogger();
	}

}
