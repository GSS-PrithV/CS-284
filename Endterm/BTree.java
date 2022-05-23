class Node<E> {
		
	Integer value;
	Node<E> left;
	Node<E> right;
	
	public Node(Integer value, Node<E> left, Node<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return "value=" + value.toString();
	}
}

public class BTree<E> {
	
	Node<E> root;
	
	
	/**
	* @return a new binary tree resulting from adding the tree recipient 
	/* of the message (i.e. the one referred to by this) and t2.
	*/
	public BTree<E> sumTree(BTree<E> t2){
		BTree<E> temp = t2;	
		temp.root.value = this.root.value + t2.root.value;
		temp.root.left = helper(this.root.left, t2.root.left, temp.root.left);
		temp.root.right = helper(this.root.right, t2.root.right, temp.root.right);
		return temp;	
	}
	
	public Node<E> helper(Node<E> one, Node<E> two, Node<E> three){
		if(one == null && two == null) {
			three = null;
			return three;
		}
		else if(one== null) {
			three= two;
			//three.left = helper(one.left, two.left, three.left);
			//three.right =  helper(one.right, two.right, three.right);
		}
		else if(two == null) {
			three = one;
			//three.left = helper(one.left, two.left, three.left);
			//three.right =  helper(one.right, two.right, three.right);
		}
		else {
			three.value = two.value + one.value;
			three.left = helper(one.left, two.left, three.left);
			three.right =  helper(one.right, two.right, three.right);
		}
		return three;
	}

	public String toString() {
		// string representation of the btree
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses btree and adds to stringbuilder
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
	

}
