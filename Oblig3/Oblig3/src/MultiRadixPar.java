import java.util.Random;
import java.util.concurrent.CyclicBarrier;


public class MultiRadixPar {

	int n;
	int [] a;
	int max;
	int index = 1;
	static int numThreads;
	CyclicBarrier cb;
	final static int NUM_BIT =7; // alle tall 6-11 OK

	public static void main(String [] args) {
		if (args.length != 2) {
	     	System.out.println(" bruk : >java ParallellRadix <n> <n> ");
		} else {
			int n = Integer.parseInt(args[0]);
			numThreads = Integer.parseInt(args[1]);
			new MultiRadixPar().doIt(n);
		}
	} // end main

	void doIt (int len) {
		a = new int[len];
		Random r = new Random(123);
		System.out.println("Generating the array a[]:");
		for (int i =0; i < len;i++) {
			a[i] = r.nextInt(len);
			//System.out.println("a["+i+"]:"+a[i]);
	    }
	    a = radixMulti(a);
	} // end doIt

	private int[] radixMulti(int[] a2) {
		max = a[0];
		//Starting with a)
		int numBit = 2, numDigits;
		int[] bit;
		n  = a.length;
		cb = new CyclicBarrier(numThreads+1);
		System.out.println("Starting threads");
		for(int i=0;i<numThreads;i++){
			new Thread(new RadixThread()).start();
		}
		try{
			cb.await();
			System.out.println("max = "+max);
			while (max >= (1L<<numBit) )numBit++;
			// bestem antall bit i numBits sifre
			numDigits = Math.max(1, numBit/NUM_BIT);
			bit = new int[numDigits];
			int rest = (numBit%numDigits), sum =0;;
			
			// fordel bitene vi skal sortere paa jevnt
			for (int i = 0; i < bit.length; i++){
				bit[i] = numBit/numDigits;
				if ( rest-- > 0)  bit[i]++;
			}
			
			int[] t=a, b = new int [n];
			System.out.println("numBit = "+numBit);
			cb.await();
		}catch(Exception e){
			System.err.println(e);
		}

	 	return null;
	}
	synchronized int getNext(){
		index++;
		if(index<n){
			return a[index];
		}
		return -1;
	}
	boolean biggerThanMax(int i){
		if(i>max){
			return true;
		}
		return false;
	}
	synchronized void setMax(int i){
		if(biggerThanMax(i)){
			max = i;
		}
	}
	
	class RadixThread implements Runnable{

		int number = 0;
		
		@Override
		public void run() {
			//a)
			while(number!=-1){
				number = getNext();
				if(biggerThanMax(number)){
					//System.out.println(this.toString()+" is setting max, newMax="+number);
					setMax(number);
				}
			}
			try{
				cb.await();
			}catch(Exception e){
				System.err.println(e);
			}
			System.out.println(this.toString()+" is finished, going to sleep");
			try {
				cb.await();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("wait is done");
			System.out.println("going to do sort");
		}
	}
}
