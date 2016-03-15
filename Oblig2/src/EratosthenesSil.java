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
	final  int [] bitMask2 ={255-1,255-2,255-4,255-8,255-16,255-32,255-64, 255-128}; // kanskje trenger du denne


	 EratosthenesSil (int maxNum) {
        this.maxNum = maxNum;
		bitArr = new byte [(maxNum/16)+1];
		setAllPrime();
        generatePrimesByEratosthenes();

      } // end konstruktor ErathostenesSil

	  void setAllPrime() {
		  for (int i = 0; i < bitArr.length; i++) {
		   bitArr[i] = (byte)255;
	      }
	   }

      void crossOut(int i) {
       // set as not prime- cross out (set to 0)  bit represening 'int i'
             // ** <din kode her>
	   } //

      boolean isPrime (int i) {
          // <din kode her, husk å teste særskilt for 2 (primtall) og andre partall først
          // før du slår opp i bitArr som jo bare representerer oddetallene>
    	 if(i%2==0){ 	//tester for partall
    		 return false;
    	 }
    	 //kjør test for tall i bitArr
    	 return true;
	 }

	  ArrayList<Long> factorize (long num) {
		  ArrayList <Long> fakt = new ArrayList <Long>();
          // <Ukeoppgave i Uke 7: din kode her>
		  return fakt;
	  } // end factorize


      int nextPrime(int i) {
	   // returns next prime number after number 'i'
          // <din kode her>
          return  i;
	  } // end nextTrue


	 void printAllPrimes(){
		 for ( int i = 2; i <= maxNum; i++)
		  if (isPrime(i)) System.out.println(" "+i);

	 }

	  void generatePrimesByEratosthenes() {
		  // krysser av alle  oddetall i 'bitArr[]' som ikke er primtall (setter de =0)
		       crossOut(1);      // 1 er ikke et primtall
		       // < din Kode her, kryss ut multipla av alle primtall <= sqrt(maxNum),
		       // og start avkryssingen av neste primtall p med p*p>

	 	 } // end generatePrimesByEratosthenes


} // end class EratosthenesSil

