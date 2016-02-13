package sequential;

import java.util.Random;

public class ArrayHandler {

	public int[] fillArrayRandom(int[] testArray,int randomInt) {
		Random r = new Random(7361);
		for(int i: testArray){
			testArray[i] = newElement(r,randomInt);
		}
		return testArray;
	}

	public int[] sortArray(int[] array) {
		int max = 0;
		int position = 0;
		int[] sorted = new int[40];
		for(int i=0;i<sorted.length;i++){
			for(int j=0;j<array.length;j++){
				if(array[j]>max){
					max = array[j];
					position = j;
				}
			}
			sorted[i] = max;
			array[position] = -1;
			max = 0;
		}
		return sorted;
	}

	public int newElement(Random r,int range) {
		return r.nextInt(range);
		
	}
	public int[] appendArrays(int[]fortyLargest,int[]restRange) {
		for(int i=0;i<restRange.length;i++){
			if(fortyLargest[39]<restRange[i]){
				fortyLargest[39] = restRange[i];
				fortyLargest = sortArray(fortyLargest);
			}
		}
		return fortyLargest;
	}

}
