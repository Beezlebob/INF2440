///--------------------------------------------------------
//
//     File: EratosthenesSil.java for INF2440-2016
//     implements bit-array (Boolean) for prime numbers
//     written by:  Arne Maus , Univ of Oslo,
//
//--------------------------------------------------------
import java.util.*;
/**
* Implements the bitArray of length 'maxNum' [0..maxNum/16 ]
*   1 - true (is prime number)
*   0 - false
*  can be used up to 2 G Bits (integer range)
*  16 numbers, i.e. 8 odd numbers per byte (bitArr[0] represents 1,3,5,7,9,11,13,15 )
*
*/
public class EratosthenesSil {
	byte [] bitArr ;           // bitArr[0] represents the 8 integers:  1,3,5,...,15, and so on
	int  maxNum;               // all primes in this bit-array is <= maxNum
	final  int [] bitMask = {1,2,4,8,16,32,64,128};  // kanskje trenger du denne
	int antPrimTall;

	 EratosthenesSil (int maxNum) {
		 antPrimTall = 1; //satt til 1 pga 2 er et primtall som ikke representeres i bitArr
		 this.maxNum = maxNum;
		 bitArr = new byte [(maxNum/16)+1];
		 setAllPrime();
	 } // end konstruktor ErathostenesSil

	  void setAllPrime() {
		  for (int i = 0; i < bitArr.length; i++) {
		   bitArr[i] = (byte)255;
	      }
	   }

	  void crossOut(int i) {
       // set as not prime- cross out (set to 0)  bit represening 'int i'
          //get which of the indexes of bitArr the number is
    	  //flip the chosen bit with
    	  int indexOfNumber = i/16;
    	  int positionOfNumber = (i%16)/2;
    	  //Checking which index in the bitArr the number has
    	  bitArr[indexOfNumber] = (byte) (bitArr[indexOfNumber]-bitMask[positionOfNumber]);
      } //

	  synchronized boolean isPrime (int i) {
    	 if(i%2==0&&i!=2){ 	//tester for partall
    		 return false;
    	 }else if(i==2){
    		 return true;
    	 }
    	 int indexOfNumber = i/16;
    	 int positionOfNumber = (i%16)/2;
    	 if(bitArr[indexOfNumber]==0){
    		 return false;
    	 }
    	 if(((bitArr[indexOfNumber] >>positionOfNumber) & 1)==0){ //test if bit is 0 or 1, if 0 test should go through
    		 	return false;
    	 }    	 
    	 return true;
	 }

	  ArrayList<Long> factorize (long num) {
		  ArrayList <Long> fakt = new ArrayList <Long>();
		  int factor = 1;
		  long confirmedFactor;
		  while(num%(long)2==0){
			  fakt.add((long)2);
			  num = num/2;
		  }
		  factor = nextPrime(factor);
		  while(factor<maxNum){
			  while(num%(long)factor==0){
				  confirmedFactor = (long)factor;
				  fakt.add(confirmedFactor);
				  num = num/confirmedFactor;
			  }
			  factor = nextPrime(factor);
		  }
		  if(num!=1){
			  fakt.add(num);
		  }
		  return fakt;
	  } // end factorize
	  
	  
     synchronized int nextPrime(int i) {
	   // returns next prime number after number 'i'
          for(int j = 2;j<maxNum;j=j+2){
        	  if(isPrime(i+j)){
        		  return i+j;
        	  }
          }
          return 0;
	  } // end nextTrue


	 void printAllPrimes(){
		 for ( int i = 2; i <= maxNum; i++)
		  if (isPrime(i)) System.out.println(" "+i);

	 }
	 int getNumberOfPrimes(){
		 for(int i=3;i<maxNum;i=i+2){
			 if(isPrime(i)){
				 antPrimTall++;
			 }
		 }
		 return antPrimTall;
	 }
	 void printBytes(){
		 for(int i=0;i<bitArr.length;i++){
			 if(Integer.toBinaryString(bitArr[i]).length()<8){
				 System.out.print("bitArr["+i+"]:");
				 int j = 8-Integer.toBinaryString(bitArr[i]).length();
				 while(j>0){
					 System.out.print("0");
					 j--;
				 }
				 System.out.println(Integer.toBinaryString(bitArr[i]));
			 }else{
				 System.out.println("bitArr["+i+"]:"+Integer.toBinaryString(bitArr[i]).substring(24));
			 }
		 }
	 }
	  void generatePrimesByEratosthenes() {
		  // krysser av alle  oddetall i 'bitArr[]' som ikke er primtall (setter de =0)
		  	long t1 = System.nanoTime();//starting the clock
		       crossOut(1);      // 1 er ikke et primtall
		       int p = 1;
		       p = nextPrime(p);
		       while(p<=Math.sqrt(maxNum)){
		    	   int noPrime = p*p;
		    	   while(noPrime<=maxNum){
		    		   if(isPrime(noPrime)){ //test for å sjekke at biten er crossOut() eller ikke
		    			   crossOut(noPrime);
		    		   	}
		    		   noPrime+=p; //p*xp hvor x er primtallet det krysses av for
		    	   	}
		    	   p = nextPrime(p);
		       	}
		       double timeUsed = (System.nanoTime()-t1)/1000000.0; //Stopping clock
		      // printBytes();
		      // printAllPrimes();
		       getNumberOfPrimes();
		       System.out.println("Max primtall m:"+maxNum);
		       System.out.printf("Genererte alle primtall <= "+maxNum+" på: %7.2f ms\n",timeUsed);
		       System.out.printf("med eratosthenes sil (%1.8f ms/primtall)\n",timeUsed/antPrimTall);	
		       System.out.print("number of primes: "+antPrimTall);
		       System.out.printf(",dvs %1.2f ",((double)antPrimTall/(double)maxNum)*100);
		       System.out.println("%");
		       
		       //TODO: Add factorization
		       //TODO: Make parallell
	  } // end generatePrimesByEratosthenes

	  

} // end class EratosthenesSil

