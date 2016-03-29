import java.util.concurrent.CyclicBarrier;

import javax.lang.model.element.NestingKind;




public class ThreadMonitor {

	
	int maxNum;
	int number;
	int numThreads;
	EratosthenesSil es;
	CyclicBarrier barrier;
	
	ThreadMonitor(int numThreads,int maxNum){
		this.number = 1;
		this.maxNum = maxNum;
		this.numThreads = numThreads;
		this.es = new EratosthenesSil(maxNum);
		barrier = new CyclicBarrier(numThreads+1);
	}
	
	void generatePrimes(){
		System.out.println("Starting parrallell");
		long t2 = System.nanoTime();//starting the clock	
		for(int i=0;i<numThreads;i++){
			new Thread(new EsThread()).start(); //making new Threads
		}
		try{barrier.await();
		}catch(Exception e){return;}
		System.out.println("Threads Done");
		double timeUsed = (System.nanoTime()-t2)/1000000.0; //Stopping clock
		System.out.println("Time used = "+timeUsed);
		System.out.println(es.getNumberOfPrimes());
	}
	synchronized int nextPrime(){
		number = es.nextPrime(number);
		//System.out.println(" number = "+number);
		return number;
	}
	
	
	class EsThread implements Runnable{
				
		int startNumber;
		EratosthenesSil lokalEs;
		
		EsThread(){
			this.startNumber = nextPrime();
		}
		
		@Override
		public void run() {
			//System.out.println(" startnumber = "+startNumber);
			int p = startNumber;
			while(p<=Math.sqrt(maxNum)){
				int noPrime = p*p;
				while(noPrime<=maxNum){
					if(es.isPrime(noPrime)){ //test for å sjekke at biten er crossOut() eller ikke
						es.crossOut(noPrime);
					}
					noPrime+=p; //p*xp hvor x er primtallet det krysses av for
				}
				//System.out.println("Thread should be finished");
				//System.out.println(this.toString()+" is getting new prime, done with "+p);
				p=nextPrime();
			}
			try{
				barrier.await();
			}catch(Exception e){
				return;
			}
		}
	}
	class FacThread implements Runnable{

		@Override
		public void run() {
			
		}
		
	}
}
