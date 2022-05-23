/** the bar consists of 3 data fields:
* 1. bar type, which is either stone or salt;
* 2. height , which is a positive integer of the bar, you may assume all stone bars have unique heights; 
* 3. next, which points to the next bar;
*/
class Bar { 
		
	String type;
	Integer height; 
	Bar next;

	public Bar(String barType , Integer barHeight) {
	    type = barType; 
		height = barHeight;
	} 
}

public class LinkedListofBar {

	Bar head; // the head of the linked list
	public LinkedListofBar(Bar newHead) {
		this.head = newHead; 
	}
	
	/**
	* get the water mass and concentration of the pool
	* @return an array {waterMass, concentration}, where waterMass is
	* the mass of water in the pool, and concentration = saltMass / waterMass */
	public double[] fillInWater() {
		
		if (this.head == null) 
			return new double [] {};	
		findTallest(this.head);	
		findSecondTallest(this.head);
		return computeMassAndConcentration ();
	}
	
	
	/** step 1: finding the height and the pointer 
	 *  to the tallest bar
	 */
	Bar tallestNode = null; // the reference to the tallest node;
	int tallestIdx = -1; // the index of the tallest node in the linked list; 
	int topHeight = -1; // the height of the tallest node;
	
	public void findTallest(Bar node) {
	
		/** You can use the while loop to compute:
		 * 1. topHeight: an integer variable declared above which is the height of the tallest node , e.g., 5;
		 * 2. tallestNode: a Bar object declared above referencing the tallest node;
		 * 3. tallestIdx: an integer variable declared above which is the index of the tallest node , e.g., 1;
		 */
		int max = 0;
		Bar temp = null;
		int ind = 0;
		int find = 0;
		LinkedListofBar temp2 = new LinkedListofBar(this.head);
		
		while(temp2.head != null) {
			if(max < temp2.head.height && temp2.head.type == "stone") {
				max = temp2.head.height; 
				temp = temp2.head;
				find = ind;
			}
			ind++;
			temp2.head = temp2.head.next;
		}
		
		tallestNode = temp;
		tallestIdx = find;
		topHeight = max;

	}
	
	
	/** step 2: finding the height and the pointer to the 2nd tallest bar
	 */
	Bar secondTallestNode = null; // the reference to the 2nd tallest node;
	int secondTallestIdx = -1; // the index of the 2nd tallest node in the linked list; 
	int secondHeight = -1; // the height of the second tallest node;
	
	public void findSecondTallest(Bar node) {
		/**  You can use the while loop to compute:
		* 1. secondheight: an integer variable declared above which is the height of the 2nd tallest node , e.g., 4;
		* 2. secondTallestNode: the Bar object declared above referencing the 2nd tallest node;
		* 3. secondTallestIdx: an integer variable declared above which is the index of the 2nd tallest node , e.g., 5;
		*/
		
		int max = 0;
		Bar temp = null;
		int ind = 0; 
		int find = 0;
		LinkedListofBar temp2 = new LinkedListofBar(this.head);
		
		while(temp2.head != null) {
			if(max < temp2.head.height && temp2.head.type == "stone" && temp2.head.height != topHeight) {
				max = temp2.head.height; 
				temp = temp2.head;
				find = ind;
			}
			ind++;
			temp2.head = temp2.head.next;
		}
		
		secondTallestNode = temp;
		secondTallestIdx = find;
		secondHeight = max;

		


	}


	/** step 3: compute the water mass and the concentration of the salt water pool
	 * @return the water mass and the concentration in the form of list
	 */
	public double[] computeMassAndConcentration() { 
		
		int z = topHeight- secondHeight; 
		
		double mass = 0;
		double masss = 0;
		int ind = 0; 
		int salt = 0;
		int stone = 0;
		
		while(this.head != null) {
			if(tallestIdx <= secondTallestIdx) {
				if(ind >= tallestIdx) {
					if(this.head.type.equals("stone")){
						//System.out.println("adding stone: " + (secondHeight - this.head.height));
						mass += (secondHeight - this.head.height);
					}
					else {
						//System.out.println("adding salt: " + (secondHeight));
						mass += secondHeight;
						masss += this.head.height;
					}
				}
			}
			if(tallestIdx > secondTallestIdx) {
				if(ind >= secondTallestIdx && ind < tallestIdx) {
					if(this.head.type.equals("stone")){
						System.out.println("adding stone: " + (secondHeight - this.head.height));
						mass += (secondHeight - this.head.height);
					}
					else {
						System.out.println("adding salt: " + (secondHeight));
						mass += secondHeight;
						masss += this.head.height;
					}
				}
			}
			ind++;
			this.head = this.head.next;
		}
		
		//System.out.println(masss);
		double[]temp = new double[]{mass,masss/mass};
		System.out.println("Mass: " + temp[0]);
		System.out.println("concentration: " +temp[1]);
		return temp;
	}
	
	public static void main(String[]args) {
		Bar bar1 = new Bar("stone", 10);
		Bar bar2 = new Bar("stone", 35);
		Bar bar3 = new Bar("salt", 18);
		Bar bar4 = new Bar("stone", 29);
		Bar bar5 = new Bar("salt", 20);
		Bar bar6 = new Bar("salt", 25);
		Bar bar7 = new Bar("stone", 40);
		bar1.next = bar2;
		bar2.next = bar3;
		bar3.next = bar4;
		bar4.next = bar5;
		bar5.next = bar6;
		bar6.next = bar7;
		LinkedListofBar barList = new LinkedListofBar(bar1);
		;
		double[] results = barList.fillInWater();
		
		
		System.out.println(results.equals(new double[]{8.0, 0.375}));
		//System.out.println(results[0]);
		//System.out.println(results[1]);
	}
}