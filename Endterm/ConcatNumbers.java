import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class The_Comparator implements Comparator<String> {
		
	/**
	* Step 1: Compare two large numbers 
	* return -1 if numberA > numberB;
	* return 1 if numberA < numberB;
	* return 0 if they are equal.
	*/
	public int compare(String numberA, String numberB)
	{
		if(numberA.compareTo(numberB) > 0) {
			return -1;
		}
		if(numberA.compareTo(numberB) < 0) {
			return 1;
		}
		return 0;
	}
}


public class ConcatNumbers {
		
	/**
	* Step 2: Check whether a large number concatenated by numberA and numberB is divisible by 3.
	* return true if the concatenated number can be divisible by 3
    * return false if the concatenated number can not be divisible by 3
    */	
	
	public boolean check_concatenation_dividable_by_three(String numberA, String numberB) {
		int n = numberA.length();
        int da = 0;
        for (int i=0; i<n; i++) {
            da += (numberA.charAt(i)-'0');
        }
        
        int n2 = numberB.length();
        int db = 0;
        for (int i=0; i<n2; i++) {
            db += (numberB.charAt(i)-'0');
        }
		return (da+db)%3 == 0;
	}

	
	/**
	 * Step 3: 
	* @return a string list which contains the maximum K concatenated numbers which can be divisible by 3.
    */	
	public String[] KMaxConcatenations(String[] A, String[] B, int N, int K)
	{
		String[] temp = new String[K];
		PriorityQueue<String> pq = new PriorityQueue<String>(new The_Comparator());
		
		for(int i = 0; i < N; i++) {
			for(int l = 0; l < N; l++) {
				if(check_concatenation_dividable_by_three(A[i], B[l]) == true) {
					pq.add((A[i] + B[l]));
				}
			}
		}
		
		
		//Object[] temp2 = pq.toArray();
		
		for(int i = 0; i < K; i++) {
			temp[i] = pq.poll();
		}
		
		//String z = temp[2];
		//temp[2] = temp [1];
		//temp[1]= z;
		
		return temp;
	}

	
	public static void main(String[] args) {	
		
	}
}
