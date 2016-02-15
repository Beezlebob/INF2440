package arrayHandling;

import java.util.Random;

public class ArrayHandler {
	
	Random r;
	
	public ArrayHandler(){
		r = new Random(7361);
	}
		
	public int[] fillArrayRandom(int[] array,int range) {
		for(int i = 0;i<array.length;i++){
			array[i] = newElement(r,range);
		}
		return array;
	}

	public int[] sortArray(int[] array) {
		int[] sorted = new int[40];
		int position = 0;
		int max = 0;
		for(int j=0;j<sorted.length;j++){
			for(int i=0;i<array.length;i++){
				if(array[i]>max){
					max = array[i];
					position = i;
				}
			}
			sorted[j] = max;
			//System.out.println(sorted[j]);
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
