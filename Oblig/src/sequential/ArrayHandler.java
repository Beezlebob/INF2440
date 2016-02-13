package sequential;

import java.util.Random;

public class ArrayHandler {

	public int[] fillArrayRandom(int[] array,int randomInt) {
		Random r = new Random(7361);
		for(int i = 0;i<array.length;i++){
			array[i] = newElement(r,randomInt);
		}
		return array;
	}

	public int[] sortArray(int[] array) {
		int i,t;
		for(int k = 0;k<array.length-1;k++){
			t = array[k+1];
			i = k;
			while(i>=0 && array[i]<t){
				array[i+1]=array[i];
				i--;
			}
			array[i+1] = t;
		}
		return array;
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
