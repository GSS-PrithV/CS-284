import java.util.ArrayList;

public class SingleLinkedList<E> {
	//head reference
	private Node<E> headNode = null;
	
	//get headNode
	public Node<E> getHeadNode() {
		return headNode;
	}
	
	//set headNode
	public void setHeadNode(Node<E> node) {
		headNode = node;
	}
	
	/**
	 * Determines whether the recipient list has cycles.
	 * @return boolean(true | false) -> If cycle return true else return false
	 */
	public Boolean hasCycle() {
		
		Node<E> S = headNode;
		Node<E> F = headNode;
		
		while((F.getNext() !=  null && S.getNext() != null) ) {
			//System.out.println("Balls");
			S = S.getNext();
			F = F.getNext().getNext();
				
			if(S == F) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		
		
		//Testing each test case
		SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		Node<Integer> node1 = new Node<>(11);
		Node<Integer> node2 = new Node<>(12);
		Node<Integer> node3 = new Node<>(13);
		Node<Integer> node4 = new Node<>(14);
		Node<Integer> node5 = new Node<>(15);
		Node<Integer> node6 = new Node<>(16);
		Node<Integer> node7 = new Node<>(17);
		Node<Integer> node8 = new Node<>(18);
		Node<Integer> node9 = new Node<>(19);
		Node<Integer> node10 = new Node<>(20);
		Node<Integer> node11 = new Node<>(21);
		Node<Integer> node12 = new Node<>(22);
		list.setHeadNode(node1);
		node1.setNext(node2); 
		node2.setNext(node3); 
		node3.setNext(node4); 
		node4.setNext(node5); 
		node5.setNext(node6); 
		node6.setNext(node7); 
		node7.setNext(node8); 
		node8.setNext(node9);
		node9.setNext(node10);
		node10.setNext(node11);
		node11.setNext(node12);
	
		
		
		
		//Validate if list contains cycle
				if(list.hasCycle()) {
					System.out.println("List contains cycle");
				} else {
					System.out.println("List doesn't contain cycle");
				}
		//
		
		
		//TestCase 1
		//list contains three nodes 1, 2, 3 and they are linked as below
		//1 -> 2 -> 3 -> 1
		//Answer: List has a cycle, because 3 is pointing back to 1 
		/*SingleLinkedList<Integer> list = new SingleLinkedList<>();
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		list.headNode = node1;
		node1.setNext(node2); 
		node2.setNext(node3);
		node3.setNext(node1);
		
		//Validate if list contains cycle
		if(list.hasCycle()) {
			System.out.println("List contains cycle");
		} else {
			System.out.println("List doesn't contain cycle");
		}
		
		//TestCase 2
		//list2 contains two nodes 10, 20 and they are linked as below
		//10 -> 20
		//Answer: list2 doesn't contain a cycle
		SingleLinkedList<Integer> list2 = new SingleLinkedList<>();
		Node<Integer> node4 = new Node<Integer>(10);
		Node<Integer> node5 = new Node<Integer>(20);
		list2.headNode = node4;
		node4.setNext(node5);
		
		//Validate if list contains cycle
		if(list2.hasCycle()) {
			System.out.println("List2 contains cycle");
		} else {
			System.out.println("List2 doesn't contain cycle");
		}
		*/
	}
}