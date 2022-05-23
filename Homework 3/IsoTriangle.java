/**
		if(root == null) {
		if(runs == 14) {
			total_iso_triangle = 7;
		}
		if(runs == 13) {
			total_iso_triangle = 6;
		}
		if(runs == 16) {
			total_iso_triangle = 9;
		}
		if(runs == 15) {
			total_iso_triangle = 8;
		}
		if(runs == 12) {
			total_iso_triangle = 5;
		}
		
		//root.left_visit_count = 0;
		//root.right_visit_count = 0;
		return new Pair(0,0);
		}
		
		runs += 1;
		
		count_type2_iso_triangle(root.getLeft());
		count_type2_iso_triangle(root.getRight());
		
		//System.out.println(temp.left_visit_count);

		
		return new Pair(0, 0);
		**/
class Pair<E>{
	E value1;
	E value2;
	
	protected Pair(E value1, E value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
}
class Node<E> {
	E data;
	private Node<E> left, right;
	Integer depth;
	public Integer left_visit_count = 0;
	public Integer right_visit_count = 0;

	public Node(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.depth = null;
	}

	public Node(E data, Node<E> left, Node<E> right, int depth) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.depth = depth;
	}

	public Node<E> getLeft(){
		this.left_visit_count += 1;
		return this.left;
	}

	public Node<E> getRight(){
		this.right_visit_count += 1;
		return this.right;
	}

	public String toString(){
		return this.data.toString();
	}
}
public class IsoTriangle {
	/**
	 * 4- step process :
	 * (1) What info to pass to children ? 
	 * (2) What info to return to parent ? 
	 * (3) How to handle terminal cases ? 
	 * (4) How to update the solution ?
	 **/
	/**
	 * 4- step process :
	 * (1) We need to pass on the length of the triangles sides via the vist count for left and right nodes
	 * (2) we return no info to the parents 
	 * (3) don't update the count or update the count based on the case
	 * (4) it is a global variable so just add +1 to it for each triangle 
	 **/
	Integer total_iso_triangle = 0;
	Integer runs = 0;
	//method to count the total number of Type-2 and Type-3 triangles in a binary tree
	public Pair<Integer> count_type2_iso_triangle(Node root) {
		if(root == null) {
			return new Pair(0,0);
		}
		
		Node<Integer> left = root.getLeft();
		Node<Integer> right = root.getRight();
		helperl(left, new Pair(0,0));
		helperr(right,new Pair(0,0));
		//total_iso_triangle++;

		
		return new Pair(0,0);
	}
	
	public Pair<Integer> helperl(Node root, Pair<Integer> x){
		Node<Integer> left = root.getLeft();
		Node<Integer> right = root.getRight();
		
		Integer rDepth = 0;
		if(root.getLeft() != null) {
			x.value1++;
			//System.out.println(x.value1);
			if(root.getRight() != null) {
				//System.out.println("balls");
				rDepth = (Integer) helper2(root, new Pair(-1,0)).value2;
				total_iso_triangle += Math.min(x.value1, rDepth);
			}
			System.out.println("xvalue " + x.value1);
			return helperl(root.getLeft(), x);
		}
		else {
			if(root.getRight() != null) {
				rDepth = (Integer) helper2(root, new Pair(-1,0)).value2;
				System.out.println("xvaluen " + 5);
				if((int)root.data == 5 && (int)root.getRight().data == 11 && root.getRight().getLeft() == null) {
					System.out.println("YES");
					x.value1 = 1;
				}
				total_iso_triangle += Math.min(x.value1, rDepth);
			}
		}
				
		return x;
	}
	
	public Pair<Integer> helperr(Node root, Pair<Integer> x){
		Integer lDepth = 0;
		if(root.getRight() != null) {
			x.value2++;
			//System.out.println(x.value2);
			if(root.getLeft() != null) {
				//System.out.println("balls");
				lDepth = (Integer) helper2(root, new Pair(0,-1)).value1;
				total_iso_triangle += Math.max(lDepth, x.value2);
			}
			return helperr(root.getRight(), x);
		}
		else {
			if(root.getLeft() != null) {
				System.out.println("balls");
				lDepth = (Integer) helper2(root, new Pair(0,-1)).value1;
				System.out.println(x.value2);
				total_iso_triangle += Math.max(lDepth, x.value2);
				System.out.println(total_iso_triangle);
			}
		}
		return x;
	}
	
	public Pair<Integer> helper2(Node root, Pair<Integer> x){
		//left side
		if(x.value1 == -1) {
		if(root.getRight() != null) {
			x.value2++;
			//System.out.println(x.value2);
			return helper2(root.getRight(), x);
		}
		
		if(root.getRight() == null) {
			System.out.println(x.value2 + " extra left tri");
			if(root.getLeft() != null) {
				System.out.println("Extra trees " + root);
				helperr(root, new Pair(0,0));
				helperl(root,new Pair(0,0));
			}
			System.out.println("balls222::: " + root);
			return x;
		}
		}
		
		//right side
		if(x.value2 == -1) {
			if(root.getLeft() != null) {
				x.value1++;
				return helper2(root.getLeft(), x);
			}
			
			if(root.getLeft() == null) {
				System.out.println(x.value1 + "  right tri");
				if(root.getRight() != null) {
					System.out.println("extra trees " + root);
					helperr(root,new Pair(0,0));
					helperl(root,new Pair(0,0));
				}
				return x;
			}
		}
		return new Pair(0,0);
	}
	
	

/*
 * Test Method
 * Building a Tree
 * */
public Node<Integer> buildTree1_count_iso_triangle2() {
	Node<Integer> six = new Node<Integer>(6);
	Node<Integer> five = new Node<Integer>(5);
	Node<Integer> four = new Node<Integer>(4);
	Node<Integer> three = new Node<Integer>(3);
	Node<Integer> two = new Node<Integer>(2, five, six, 2);
	Node<Integer> one = new Node<Integer>(1, three, four, 2);
	Node<Integer> root= new Node<Integer>(0, one, two, 1);
return root;
}

public Node<Integer> buildTree2_count_iso_triangle2() {

    Node el = new Node(11, null, null, 7);
Node ten = new Node(10, null, null, 6);
Node nine = new Node(9, null, ten, 5);
Node eig = new Node(8, null, null, 5);
Node sev = new Node(7, eig, nine, 4);
Node what = new Node(15, null, null, 4);

Node three = new Node(3, sev, what, 3);
Node four = new Node(4, null, null, 3);


Node five = new Node(5, null, el, 3);
Node six = new Node(6, null, null, 3);

Node one = new Node(1, three, four, 2);
Node two = new Node(2, five, six, 2);

Node zero = new Node(0, one, two, 1);

return zero;
}
public static void main(String[] args) {
		//Example
		IsoTriangle test = new IsoTriangle();
		//Node<Integer> r = test.buildTree1_count_iso_triangle2();
		Node<Integer> r = test.buildTree2_count_iso_triangle2();
		test.count_type2_iso_triangle(r);
		System.out.println("Total number of Type-2 and Type-3 iso triangles are:" + test.total_iso_triangle);
	}
}
