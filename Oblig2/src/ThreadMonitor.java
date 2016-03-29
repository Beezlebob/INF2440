import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import javax.lang.model.element.NestingKind;




public class ThreadMonitor {

	
	int maxNum;
	int number;
	int numThreads;
	long factorizable;
	int factorsIndex;
	long finalNum;
	int run;
	EratosthenesSil es;
	CyclicBarrier barrier;
	ArrayList<ArrayList<Long>> factors;
	
	ThreadMonitor(int numThreads,int maxNum){
		this.number = 1;
		this.maxNum = maxNum;
		this.numThreads = numThreads;
		this.es = new EratosthenesSil(maxNum);
		barrier = new CyclicBarrier(numThreads+1);
		factors = new ArrayList<ArrayList <Long>>();
		for(int i=0;i<100;i++){
			factors.add(new ArrayList<Long>());
		}
		run = 0;
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
	ArrayList<ArrayList<Long>> factorize(long num) {
		number = 1;
		finalNum = num;
		factorizable = num;
		for(int i=0;i<numThreads;i++){
			new Thread(new FacThread()).start();
		}
		try{barrier.await();
		}catch(Exception e){return factors;}
		
		return factors;
	}
	synchronized int nextPrime(){
		if(number==1&&factorizable!=0){
			number = 2;
		}else if(number==2){
			number = es.nextPrime(1);
		}else if(number>=maxNum){
			nextNumber();
		}else if(number == 9973){
			factors.get(run).add(factorizable);
			nextNumber();
		}else{
			number = es.nextPrime(number);
		}
		return number;
	}
	synchronized boolean isFactor(int threadFactor){
		if(factorizable%threadFactor==0){
			return true;
		}
		try {
			wait(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	void addFactor(int threadFactor){
		factors.get(run).add((long)threadFactor);
		factorizable = factorizable/(long)threadFactor;
		if(factorizable==1||factorizable==2){
			nextNumber();
		}
	}
	synchronized void nextNumber(){
		number = 2;
		if(run<100){
			run++;
			factorizable = finalNum-run;
		}
	}
	
	
	class EsThread implements Runnable{
				
		int startNumber;
				
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
		
		int startNumber;
		
		FacThread(){
			this.startNumber = nextPrime();
		}
		
		@Override
		public void run() {
			while(run<100){
				if(startNumber == 1){
					nextNumber();
				}
				while(isFactor(startNumber)){
					addFactor(startNumber);
				}
				startNumber = nextPrime();
			}
			try{
				barrier.await();
			}catch(Exception e){
				return;
			}
		} 
	}
}
