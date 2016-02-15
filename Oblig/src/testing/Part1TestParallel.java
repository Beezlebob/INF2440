package testing;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import parallel.*;

import junit.framework.TestCase;

public class Part1TestParallel extends TestCase{

	Logger logger;	
	
	public Part1TestParallel() {
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
//	 @Test ParallelSolutioin
	public void testGetFortyHighest(){
		ThreadMonitor tm = new ThreadMonitor(4,1000);
		assertNotNull(tm.getFortyHighest());
	}
//	 @Test FillArray
	public void testFillArray(){
		ThreadMonitor tm = new ThreadMonitor(4,1000);
		tm.execute();
		assertNotNull(tm.getFortyHighest()[0]);
	}
//	 @Test started Threads
	public void testStartedThreads(){
		int threads = 4;
		ThreadMonitor tm = new ThreadMonitor(threads, 1000);
		tm.execute();
		assertEquals(threads, tm.numThreads);
	}

	public static void main(String[] args) {
		Part1TestParallel test = new Part1TestParallel();
		test.createLogger();
	}
	
	
}