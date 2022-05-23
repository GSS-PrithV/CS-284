public class BinaryNumber {
private int[] bin;


//creates  a  binary  number  of  length "length" and consisting only of zeros
	public BinaryNumber(int length){
		
		//checks if length is a positive integer 
		if(length <= 0){
			throw new IllegalArgumentException();
		}

		//creates a temporary int array made of 0 and then sets the main binary array equal to it
		int[] temp = new int[length];
		bin = temp;
		
	}
	
//creates  a  binary  number  using a string	
	public BinaryNumber(String str){
		
		//Checks to see if string is longer than 0 characters
		if(str.length() < 0 ) {
			throw new  IllegalArgumentException();
		}
		
		//creates temp array with length of string
		int[] temp = new int[str.length()];
		
		//for-loop goes to convert the string to an int array, also checks if the string is binary
		for(int i = 0; i < str.length(); i++) {
			int x = Integer.parseInt(Character.toString(str.charAt(i)));
			if(x == 0 || x == 1) {
				temp[i] = x;
			}
			else
				throw new  IllegalArgumentException();
		}
		
		bin = temp;
			
	}
	
//returns the length of a binary
	public int getLength(){
		return bin.length;
	}

//returns the integer array representing a binary number
	public int[] getInnerArray(){
		return bin;
	}

//returns a digit of a binary number given an index.
	public int getDigit(int index){
		if(index < 0 || index > bin.length-1) {
			throw new  IllegalArgumentException();
		}
		return bin[index];
	}

//returns a binary number in its decimal notation
	public int toDecimal(){
		int d = 0;
		int index = 0;
	    for(int i = bin.length-1; i >= 0; i--){
	    	d += (bin[index] * Math.pow(2, i));
	        index++;
	    }
	    return d;
	}

//returns a binary number with all the digits shifted any number of places to the left or right.
//The direction parameter indicates:
//a left shift when the value is -1, right shift when the value is 1
	public void bitShift(int direction, int amount){
		
		if (amount <= 0) {
			throw new  IllegalArgumentException();
		}
		
		if ( direction == 1) {
			int NewL = bin.length - amount;	
			int[] temp = new int [NewL];
			for(int i = 0; i < NewL; i++) {
				temp[i] = bin[i];
			}
			bin = temp;
		}
		else if (direction == -1){
			int NewL = bin.length + amount;
			int[] temp = new int [NewL];
			for(int i = 0; i < bin.length; i++) {
				temp[i] = bin[i];
			}
			bin = temp;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2){
		
		if (bn1.getLength() != bn2.getLength()) {
			throw new  IllegalArgumentException();
		}
		
		int[] temp = new int[bn1.getLength()];
		
		for (int i = 0; i < bn1.getLength(); i++) {
			if (bn1.getDigit(i) == 1 || bn2.getDigit(i) == 1) {
				temp[i] = 1;
			} 
		}
		return temp;
	}
	
	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2){
		
		if (bn1.getLength() != bn2.getLength()) {
			throw new  IllegalArgumentException();
		}
		
		int[] temp = new int[bn1.getLength()];
		
		for (int i = 0; i < bn1.getLength(); i++) {
			if (bn1.getDigit(i) == 1 && bn2.getDigit(i) == 1) {
				temp[i] = 1;
			} 
		}
		return temp;
	}
	
	public void add(BinaryNumber aBinaryNumber){
		
		if(bin.length > aBinaryNumber.getLength()) {
			String s = Integer.toBinaryString( (1 << bin.length) | aBinaryNumber.toDecimal() ).substring(1);
			aBinaryNumber = new BinaryNumber(s);
			}
		
		else if (bin.length < aBinaryNumber.getLength()) {
			String s = Integer.toBinaryString( (1 << aBinaryNumber.getLength()) | this.toDecimal() ).substring(1);
			
	        int[] temp = new int[s.length()];
	  
	        for (int i = 0; i < s.length(); i++) {
	            temp[i] = Integer.parseInt(String.valueOf((s.charAt(i))));
	        }
	        
	        bin = temp;
		}
			
		int[] temp = new int[bin.length];
		
		int carry = 0;
		for (int i = bin.length - 1; i > -1; i--) {
			// Adder counter.
			int adder =bin[i] + aBinaryNumber.getDigit(i) + carry;
			if (adder == 0) {
				// Both numbers and the carry are 0.
				temp[i] = 0;
				carry = 0;
			} else if (adder == 1) {
				// There's one 1.
				temp[i] = 1;
				carry = 0;
			} else if (adder == 2) {
				// There are two ones.
				temp[i] = 0;
				carry = 1;
			} else if (adder == 3) {
				// Both numbers are 1 and the carry is 1.
				temp[i] = 1;
				carry = 1;
			}
		}
		
		bin = temp;
		
		if(carry == 1) {
			int[] temp2 = new int[bin.length+1];
			temp2[0] = 1;
			for( int i = 1; i < temp2.length; i++) {
				temp2[i] = bin[i-1];
			}
			
			bin = temp2;
		}
			
		
		System.out.println(this.toString());
		}
		
	public String toString(){
		String str = new String();
		for (int i = 0; i < this.getLength(); i++) {
			str += bin[i];
		}
		return str;
	}
	
	public static void main(String[]args) {
		//BinaryNumber p1 = new BinaryNumber("");
		//p1.bitShift(-1, 2);
		//System.out.println(p1.toDecimal());
		//System.out.println(p1.toString());
		System.out.println(5/2);
	}

}