import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {

	private static class Node<E> {
		E data;
		int priority;
		Node<E> left;
		Node<E> right;

		public Node(E data, int priority) {
			if (data == null)
				throw new IllegalArgumentException();
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;

		}

		Node<E> rotateRight() {
			if (this.left == null)
				return this;
			Node<E> b = this.left;
			this.left = b.right;
			b.right = this;
			return b;
		}

		Node<E> rotateLeft() {
			if (this.right == null)
				return this;
			Node<E> b = this.right;
			this.right = b.left;
			b.left = this;
			return b;
		}

		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
	}
	
	private Random priorityGenerator;
	private Node<E> root;

	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}

	public boolean add(E key) {
		int p = priorityGenerator.nextInt();
		return add(key, p);
	}

	public boolean add(E key, int priority) {
		Stack<Node<E>> s = new Stack<Node<E>>();
		Node<E> temp = root;
		Node<E> node = new Node<E>(key, priority);
		
		return add2(node, temp, s);
	}
	
	public boolean add2(Node<E> node, Node<E> temp, Stack<Node<E>> s) {
		if(root == null) {
			root = new Node<E>(node.data,node.priority);
			return true;
		}
			int c = temp.data.compareTo(node.data);
			if(c == 0) {
			return	false;
			}
			if(c > 0) {
				s.push(temp);
				if(temp.left == null) {
					temp.left = node;
					reheap(s, node);
					return true;
				}
				return add2(node, temp.left, s);
				
			}
			if(c < 0) {
				s.push(temp);
				if(temp.right == null) {
					temp.right = node;
					reheap(s, node);
					return true;
				}
				return add2(node, temp.right, s);
			}
		return false;
	}
	private void reheap(Stack<Node<E>> s, Node<E> curr) {
		// helper to add, restores invariant
		while(!s.empty()) {
			//System.out.println(curr.priority);
		if(s.peek().priority < curr.priority ) {
			//System.out.println("Popped");
			Node<E> temp = s.pop();
			int c = temp.data.compareTo(curr.data);
			//System.out.println(curr);
			//System.out.println(s);
			
			if(c > 0) {
				//System.out.println("YesR");
				curr = temp.rotateRight();
				if(!s.empty()) {
					if(s.peek().left == temp) {
						s.peek().left = curr;
					}
					else {s.peek().right = curr;}
				}
			}
			if(c < 0){
				//System.out.println("YesL");
				curr = temp.rotateLeft();
				if(!s.empty()) {
					if(s.peek().left == temp) {
						s.peek().left = curr;
					}
					else {s.peek().right = curr;}
				}
			}

			if(s.empty()) root = curr;
		}
		
		else return;
		}
	}


	public boolean delete(E key) {
		Node<E> temp = root;
		if(find(key)) {
			while(temp != null) {
				System.out.println(temp);
				if(temp.left != null) {
					if(temp.left.data.compareTo(key) == 0) {
						System.out.println("Left");
						temp.left = delete(key,temp.left);
						}
				}
				if(temp.right != null) {
					if(temp.right.data.compareTo(key) == 0) {
						System.out.println("Right");
						temp.right = delete(key,temp.right);
					}
				}
				if( temp.data.compareTo(key) == 0) {
					System.out.println("M");
					temp = delete(key, temp);
					return true;
				}
				if( temp.data.compareTo(key) < 0) {
					temp = temp.right;
				}
				else if( temp.data.compareTo(key) > 0) {
					temp = temp.left;
				}
			}
		}
		return false;
	}
	
	private Node<E> delete(E key, Node<E> current) {
		System.out.println(current);
		if(current.right == null) {
			current = current.left;
		}
		else if(current.left == null) {
			current = current.right;
		}
		else if (current.right.priority < current.left.priority) {
		
			current = current.rotateRight();
			current.right = delete(key, current.right);
		}
		else if (current.right.priority > current.left.priority) {
			current = current.rotateLeft();
			current.left = delete(key, current.left);
		}
	
		
		return current;
	}

	public boolean find(E key) {
		// return if node with key key exists
		Node<E> temp = root;
		while(temp != null ) {
			int c = temp.data.compareTo(key);
			if(c == 0) {
				return true;
			}
			if(c > 0) {
				temp = temp.left;
			}
			if(c < 0) { 
				temp = temp.right;
			}
		}
		return false;
	}
	

	public String toString() {
		// string representation of the treap
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses treap and adds to stringbuilder
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}

	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		
		String s1 = testTree.toString();
		System.out.println(s1);
		System.out.println("==============");
		testTree.delete(2);
		System.out.println("==============");
		String s2 = testTree.toString();
		System.out.println(s2);
		//System.out.println(testTree.find(5));
	}
}