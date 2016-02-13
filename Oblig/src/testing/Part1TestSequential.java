package testing;

import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	
//	 @Test Task 1.1 ArrayHandler
	public void testFillArray(){
		int[] testArray = new int[40];
		ArrayHandler handler = new ArrayHandler();
		testArray = handler.fillArrayRandom(testArray,1000);
		for(int i = 0;i<testArray.length;i++){
			assertNotNull("should be filled",testArray[i]);
		}
	}
// 	 @Test Task 1.1 ArrayHandler
	public void testSortArray(){
		int[] testArray = new int[40];
		ArrayHandler handler = new ArrayHandler();
		for(int i=0;i<testArray.length;i++){
			testArray[i] = i;
		}
		testArray = handler.sortArray(testArray);
		assertTrue(testArray[0]>testArray[1]);
		assertTrue(testArray[3]>testArray[4]);
	}
//	 @Test Task 1.2 a ArrayHandler
	public void testNewElement(){
		ArrayHandler handler = new ArrayHandler();
		Random r = new Random(7361);
		int i = handler.newElement(r, 1000);
		int j = handler.newElement(r, 1000);
		assertNotSame(i,j);
	}
//	 @Test Task 1.2 b ArrayHandler
	public void testAppendArray() {
		ArrayHandler handler = new ArrayHandler();
		int range = 1000;
		int[] testArrayA = new int[40];
		int[] testArrayB = new int[960];
		testArrayA = handler.fillArrayRandom(testArrayA, range);
		testArrayA = handler.sortArray(testArrayA);
		testArrayB = handler.fillArrayRandom(testArrayB, range);
		int[] original = testArrayA;
		testArrayA = handler.appendArrays(testArrayA, testArrayB);
		assertNotSame(original[39],testArrayA[39]);
	}
//	 @Test SequentialSolutioin
	public void testGetFortyHighest(){
		SequentialSolution seq = new SequentialSolution(1000);
		assertNotNull(seq.getFortyHightest());
	}
// 	 @Test Execute
	public void testExecute(){
		SequentialSolution seq = new SequentialSolution(1000);
		int[] testArray = seq.getFortyHightest();
		seq.execute();
		assertNotSame(testArray[39],seq.getFortyHightest()[39]);
	}
	
	public static void main(String[] args) {
		Part1TestSequential test = new Part1TestSequential();
		test.createLogger();
	}

}
