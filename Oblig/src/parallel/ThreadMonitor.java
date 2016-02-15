package parallel;

import java.util.concurrent.CyclicBarrier;

import arrayHandling.ArrayHandler;

public class ThreadMonitor {

	CyclicBarrier barrier;
	public int numThreads,range;
	int[] fortyFirst;
	ArrayHandler handler;
	public double timeUsed;
	
	public ThreadMonitor(int numThreads,int range) {
		this.numThreads = numThreads;
		handler = new ArrayHandler();
		this.range = range;
		fortyFirst = new int[40];
		barrier = new CyclicBarrier(numThreads+1);
	}
	public void execute(){
		fortyFirst = handler.fillArrayRandom(getFortyHighest(), range);
		fortyFirst = handler.sortArray(getFortyHighest());
		int[][] restArrays = new int[numThreads][range/numThreads];
		for(int i=0;i<restArrays.length;i++){
			restArrays[i] = handler.fillArrayRandom(restArrays[i], range);
		}
		long t = System.nanoTime();
		for(int i =0;i<numThreads;i++){
			new Thread(new ParallelThread(getFortyHighest(),restArrays[i])).start();
		}
		try{barrier.await();
		}catch(Exception e){return;}
		timeUsed = (System.nanoTime()-t)/1000000.0;
	}
	public int[] getFortyHighest(){
		return fortyFirst;
	}
	//Thread class
	class ParallelThread implements Runnable {
	
		int[] fortyFirstThread;
		int[] restArray;
		ArrayHandler threadArrayHandler;
		
		public ParallelThread(int[] fortyFirst,int[] restArray) {
			this.fortyFirstThread = fortyFirst.clone();
			this.restArray = restArray.clone();
			threadArrayHandler = new ArrayHandler();
		}
		
		@Override
		public void run() {
			fortyFirstThread = threadArrayHandler.appendArrays(fortyFirstThread, restArray);
			try{
				barrier.await();
			}catch(Exception e){
				return;
			}
		
		}
	}
}
