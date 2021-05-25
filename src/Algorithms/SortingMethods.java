package Algorithms;

import java.util.Arrays;

public class SortingMethods {
	
	/* Repeatedly searches array for smallest element, then inserts it to proper place
	 * TIME COMPLEXITY: O(n^2) <-- Average			
	 */
	
	static int[] selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = i;
			for(int j = i; j < arr.length; j++) {
				if(arr[min] > arr[j]){
					min = j;
				}
			}
			
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
		return arr;
	}
	
	/* Compares each pair of elements, and swaps if following one is larger
	 * TIME COMPLEXITY: O(n^2) <-- Average
	 * Consistently better than Insertion sort, since usually searches arr less than n times
	 */
	
	static int[] bubbleSort(int[] arr) {
		
		boolean swapped = false;
		
		while(true) {
			for(int i = 0; i < arr.length - 1; i++) {
				if(arr[i] > arr[i+1]) {
					int temp = arr[i + 1];
					arr[i+1] = arr[i];
					arr[i] = temp;
					swapped = true;
				}
			}
			
			if(!swapped) {
				break;
			}
			
			swapped = false;
		}
		
		return arr;
	} 
	
	/* Helper for quicksort, performs single partition of quickSort
	 */
	
	public static int partition(int[] arr, int first, int last){
		int pivot = first;
		int left = first;
		int right = last;
		
		while(left < right) {
			while(arr[pivot] >= arr[left] && left < arr.length) {
				left++;
			}
			
			while(arr[pivot] < arr[right] && right > 0) {
				right--;
			}
			
			if (left < right) {
				int temp = arr[left]; 
				arr[left] = arr[right];
				arr[right] = temp;
			}
		}
		
		int temp = arr[pivot]; 
		arr[pivot] = arr[left - 1];
		arr[left - 1] = temp;
		
		return right;
	}
	
	/* Recursively divides arr into several sub arrays, sorting one element of each array each time 
	 * TIME COMPLEXITY: O(nlogn) <-- Average
	 * Consistently fast, easily tweaked to make worst O(n^2) extremely unlikely 
	 */
	
	static void quickSort(int[] arr, int first, int last) {
		if(first < last) {
			int pivot = partition(arr, first, last);
			quickSort(arr, first, pivot - 1);
			quickSort(arr, pivot + 1, last);
		}
	}
	
	/* Splits array in half, taking the smallest element of the two until end is reached
	 * Helper for mergeSort
	 */
	
	static int[] merge(int[] arr, int left, int middle, int right) {
		int leftLength = middle - left + 1;
		int rightLength = right - middle;
		
		// {1,2,3}
		
		// Construct arrays
		int[] L1 = new int[leftLength];
		int[] L2 = new int[rightLength];
 		
		for(int i = 0; i < leftLength; i++) {
			L1[i] = arr[left + i];
		}
		
		for (int j = 0; j < rightLength; j++) {
			L2[j] = arr[middle + 1 + j];
		}
		
		// Merge arrays
		
		// Current index of Left (L1) && Right (L2) sub-arrays
		int l = 0, r = 0;
		
		// Pointer to left index of array
		int k = left;
		
		// Merge alg.
		while(l < leftLength && r < rightLength) {
			
			if(L1[l] <= L2[r]) {
				arr[k] = L1[l];
				l++;
			}
			
			else {
				arr[k] = L2[r];
				r++;				
			}
			
			k++;
		}
		
		// Fill with remaining elements
		while(l < leftLength) {
			arr[k] = L1[l];
			l++;
			k++;
		}
		
		while(r < rightLength) {
			arr[k] = L2[r];
			r++;
			k++;
		}
		
		return arr;		
	}
	
	/* Recursively calls mergeSort on each half, until every peice of array sorted then merges all together
	 * TIME COMPLEXITY: O(nlogn) <-- Average, Worst, Best 
	 * Consistent sorting for any array
	 */
	
	static void mergeSort(int[] arr, int left, int right) {
		if(left < right) {
			int middle = left + (right - left) / 2;
			mergeSort(arr, left, middle);
			mergeSort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {4,5,3,7,8,1,2,9,6};
		//bubbleSort(arr);
		mergeSort(arr, 0, arr.length - 1);
		//quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		//System.out.println("Show me something");
		
		}
	
}

