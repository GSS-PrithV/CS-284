import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pair{
	Integer max_left_path_value;
	Integer max_right_path_value;

	protected Pair(Integer max_left_path_value, Integer max_right_path_value) {
		this.max_left_path_value = max_left_path_value;
		this.max_right_path_value = max_right_path_value;
	}

	@Override
	public String toString() {
		return "Pair{" +
				"max_left_path_value=" + max_left_path_value +
				", max_right_path_value=" + max_right_path_value +
				'}';
	}
}

class Node<E extends Comparable<E>> {

	E value;
	Node<E> l_child;
	Node<E> r_child;


	Node(E value, Node<E> l_child, Node<E> r_child) {
		this.value = value;
		this.l_child = l_child;
		this.r_child = r_child;
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}
}



public class Triangle {


	private Integer maxSum = -1;

	public Pair max_triangle(Node < Integer > root ) {
		
		int ms = 0;
		int ml = 0;
		int mr = 0;
		Node<Integer> temp = root;
		Node<Integer> temp2 = root;
		
		if(root == null) {
			return new Pair(0,0);
		}

		while(temp.l_child != null) {
			if(temp.l_child.value > ml) {
				ml = temp.l_child.value;
			}
			temp = temp.l_child;
		}
		
		while(temp2.r_child != null) {
			if(temp2.r_child.value > mr) {
				mr = temp2.r_child.value;
			}
			temp2 = temp2.r_child;
		}
		
		
		Pair holder = new Pair(ml,mr);
		ms = ml + mr + root.value;
		if(ml == 0 || mr == 0) {
			ms = 0;
		}
		if(ms > maxSum) {
			maxSum = ms;
		}
		
		max_triangle(root.l_child);
		max_triangle(root.r_child);		
		return holder;
		
	}


	public Integer getMaxSum() {
		return maxSum;
	}

	public Node<Integer> testcase1() {
		Node<Integer> six = new Node<Integer>(6, null, null);
		Node<Integer> five = new Node<Integer>(5, null, null);
		Node<Integer> four = new Node<Integer>(4, null, null);
		Node<Integer> three = new Node<Integer>(3, null, null);
		Node<Integer> two = new Node<Integer>(2, five, six);
		Node<Integer> one = new Node<Integer>(1, three, four);
		Node<Integer> root= new Node<Integer>(0, one, two);
		return root;
	}

	public static void main(String[] args) {
		//Example
		Triangle test = new Triangle();
		Node<Integer> r = test.testcase1();
		test.max_triangle(r);
		System.out.println("The max triangle sum is:" + test.getMaxSum());
	}
}

