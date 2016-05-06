import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import javax.naming.BinaryRefAddr;


public class MultiRadixPar {

	int n, max, mask, acumVal;
	int [] a,b,bit;
	int index = 1;
	int bitIndex = 0;
	int countIndex = 0;
	int allCount[];
	ArrayList<int[]> allCountBuffer;
	static int numThreads;
	final static int NUM_BIT =7; // alle tall 6-11 OK
	CyclicBarrier cb;
	
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
		//Starting with a)
		int numBit = 2, numDigits;
		acumVal = 0;
		n  = a.length;
		cb = new CyclicBarrier(numThreads+1);
		System.out.println("Starting threads");
		long tt = System.nanoTime();
		
		for(int i=0;i<numThreads;i++){
			new Thread(new RadixThread()).start();					//Creating and starting the threads
		}
		try{ 														//Calls await for the threads to finish with part a)
			cb.await();
			while (max >= (1L<<numBit) ){								
				numBit++;
			}
			numDigits = Math.max(1, numBit/NUM_BIT);				//Counts how many bits one needs to represent the number
			bit = new int[numDigits];
			int rest = (numBit%numDigits), sum =0;;
			
			for (int i = 0; i < bit.length; i++){					//Splits the bits we are sorting evenly
				bit[i] = numBit/numDigits;
				if ( rest-- > 0)  bit[i]++;
			}
																	//Declaring some new helpful variables
			int[] t=a;
			b = new int [n];
			index = 0;												//Resetting index for later use
			getBit();
			allCount = new int[mask+1];
			
			cb.await(); 											//Starting the threads again
			index  = 0;
			allCountBuffer = new ArrayList<int[]>(numThreads);		//Initializing allCount for counting frequency of numbers
			cb.await();
			setAllCount();											//Adding all the local counters into the main one
			/*
			cb.await();
			 double tid = (System.nanoTime() -tt)/1000000.0;
			System.out.println("done in "+tid);*/
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	
	/*
	 * Returns the next number in the array a[] for the threads to use
	 * Returns -1 when the index is bigger than the size()
	*/
	int getNext(){
		index++;
		if(index<n){
			return a[index];
		}
		return -1;
	}
	
	/*
	 * Sets the max value of a[]
	 */
	void setMax(int number){
		if(number > max){
			max = number;
		}
	}
	/*
	 * Remove this shit
	 */
	synchronized void plusAcumVal(int i) {
		acumVal = acumVal+i;
	}
	
	/*
	 * Adds the arrays to a helper arrayList for later merging
	 */
	void addToAllCount(int count[]){
		allCountBuffer.add(count);
	}
	
	/*
	 * Merges the arrays for global use 
	 */
	void setAllCount(){
		for(int[] countArray: allCountBuffer){
			for(int i=0;i<countArray.length;i++){
				allCount[i] += countArray[i];	
			}
		}
	}
	
	/*
	 * Returns the bit of the number one should now use
	 */
	int getBit() {
		mask = (1<<bit[bitIndex]) -1;
		return bit[bitIndex];
	}
	/*
	 * Remove this shit
	 */
	synchronized int getCountIndex(){
		if((countIndex+1)>mask){
			return -1;
		}
		return countIndex++;		
	}
	/*
	 * Sets the shift of the bit[] 
	 * i.e how much one should shift to get the current number
	 */
	
	int setShift(){
		return 0;
	}
	/*
	 * Remove this shit
	 */
	synchronized void setAllCountIndex(int index,int value){
		allCount[index] = value;
	}
	
	
	class RadixThread implements Runnable{
		
		
																	//initializing some local variables
		int number = 0;
		int localMax = 0;
		int count[];
		int value;
		
		@Override
		public void run() {
			//a)
			while(number!=-1){
				number = getNext();
				if(number>localMax){
					localMax = number;
				}
			}
			try{
				setMax(localMax);									//sets the global max for later use
				cb.await(); 										//waiting for barrier to finish
				
				//b)
				cb.await();
				number = getNext(); 								//Starts to iterate through the numbers again
				count = new int[mask+1];
				int shift = setShift();
				while(number!=-1){									//Counting the frequency of numbers and adding them to local counter array
					number = getNext();
					if(number != -1){
						count[(number>>> shift) & mask]++; 
					}
				}
				addToAllCount(count);								//Adding local count to the main counter
				cb.await();	
				number = 0;
				/*
				 * TODO: Remove rest of synchronized methods and re-write the rest of this code:
				//c)
				while(number != -1){
					number = getCountIndex();
					if(number != -1  &&number !=0){
						value = allCount[number];
						setAllCountIndex(number, acumVal);
						plusAcumVal(value);
					}
				}
				number = 0;
				cb.await();
				//d)
				while(number != -1){
					number = getNext();
					if(number != -1){
						b[allCount[(a[number]>>>shift) & mask]++] = a[number];
					}
				}*/
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
