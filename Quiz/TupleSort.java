import java.util.ArrayList;
import java.util.Collections;

public class TupleSort {
	
   class Tuple implements Comparable<Tuple>{
	    int[] tuple;
		
		public Tuple(int[] this_tuple) {
			this.tuple = this_tuple;
		}

		/** you need to implement this function */
		@Override
		public int compareTo(Tuple other_tuple) {
			//System.out.println(other_tuple.tuple.length);
			//System.out.println(this.tuple[1]);
			int L = Math.min(this.tuple.length,other_tuple.tuple.length);
			
			for(int i = 0; i < L; i++) {
				if(this.tuple[i] != other_tuple.tuple[i]) {
					if(this.tuple[i] > other_tuple.tuple[i]) {
						return 1;
					}
					else if(this.tuple[i] < other_tuple.tuple[i]) {
						return -1;
					}
				}
			}
			
			if(this.tuple.length == other_tuple.tuple.length) {
				return 0;
			}
			else if(this.tuple.length > other_tuple.tuple.length) {
				return 1;
			}
			return -1;
		}
	}
   
    public TupleSort() {
		
    }
    
	public int test_compareTo_gs(int[] arr1, int[] arr2) {	
		Tuple t1 = new Tuple(arr1);
		Tuple t2 = new Tuple(arr2);		
		return t1.compareTo(t2);
	}
	
	public ArrayList<int[]> test_tuplesort_gs(ArrayList<int[]> list) {	
		int len = list.size();
			Tuple[] tuple = new Tuple[len];
			for (int i = 0; i < len; i++) {
				tuple[i] = new Tuple(list.get(i));
			}		   
			Tuple[] sorted_tuple = tuple_sort (tuple);
			
			ArrayList<int[]> sorted_list = new ArrayList<int[]>();	  
			for (int i = 0; i < len; i++) {
				if (sorted_tuple[i] != null)
					sorted_list.add(sorted_tuple[i].tuple);
				else
					sorted_list.add(new int[]{-1});
			}	
			
			return sorted_list;
	}
	
   
   /** you need to implement this function */
   
   public Tuple[] tuple_sort(Tuple[] array) {
	   int L = array.length;
	   for(int i = 0; i < L - 1; i++) {
		   for(int j = 0; j < L - i - 1; j++ ) {
			   if(array[j].compareTo(array[j+1]) == 1) {
				   Tuple temp = array[j];
				   array[j] = array[j+1];
				   array[j+1] = temp;
			   }
		   }
	   }
	   return array;
   }
   
   public Tuple[] helper(Tuple[] array, Tuple low, Tuple high) {
	   if(low.compareTo(high) == -1) {
		   
	   }
	   return new Tuple[array.length];
   }
   
   public void print_tuple_array(Tuple[] array) {
	   
	   String actual_output = "";
	   
	   for(int i = 0; i < array.length; i++) {
		   
		   String output = "(";
		   for(int x = 0; x < array[i].tuple.length; x++)
			   output += array[i].tuple[x] + ", ";
		   output = output.substring(0, output.length()-2);
		   
		   output += ")";
		   
		   actual_output += output + ", ";
		   
	   }
	   
	   System.out.println(actual_output.substring(0, actual_output.length()-2));
	   
   }
   
   public void test_tuple_sort () {
	   Tuple [] test_tuple = new Tuple [5];
	   test_tuple [0] = new Tuple (new int[] {1 , 2});
	   test_tuple [1] = new Tuple (new int[] {2});
	   test_tuple [2] = new Tuple (new int[] {1 , 1 , 1});
	   test_tuple [3] = new Tuple (new int[] {1 , 5 , 0 , 5});
	   test_tuple [4] = new Tuple (new int[] {1 , 5 , -1});
	   System.out.println("Before sorting: ");
	   this.print_tuple_array(test_tuple);
	   Tuple [] sorted_tuple = this. tuple_sort ( test_tuple );
	   System.out.println("After sorting: ");
	   this.print_tuple_array(test_tuple);
   }
   
   public static void main(String[] args) {
	  
	    
	    //20 points all correct
	    //System.out.println(tp.test_compareTo_gs(arr1, arr1));
	    
	    //System.out.println(tp.test_compareTo_gs(arr1, arr2));
	    
	   //System.out.println(tp.test_compareTo_gs(arr2, arr1));
       
       //20 points all correct
       //System.out.println(tp.test_compareTo_gs(arr3, arr2));
       
       //System.out.println(tp.test_compareTo_gs(arr1, arr4));
       
       //System.out.println(tp.test_compareTo_gs(arr3, arr4) );
       
       
	   
   }

}