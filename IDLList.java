import java.util.ArrayList;

public class IDLList<E> {
	//Properties of IDLList<E>:
	private ArrayList<Node<E>> indices;
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	//Inner class Node<E>
	private class Node<E> {
		//Properties of Node<E>:
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		//Constructor of Node<E>, given 'E' elem
		public Node(E elem) {
			this.data = elem;
			this.prev = null;
			this.next = null;
		}
		
		//Constructor of Node<E>, given 'E' elem, a link to the previous node, and
		//a link to the next node
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//Constructor of IDLList
	public IDLList() {
		this.indices = new ArrayList<Node<E>>();
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	
	//Constructor of IDLList
	public IDLList(E[] newIndices) {

		this.indices = new ArrayList<Node<E>>();
        if(newIndices.length>0){
            for(int i = 0; i<newIndices.length; i++){
                this.indices.add(new Node<E>(newIndices[i]));  
            }
            this.size = this.indices.size();
		    this.head = this.indices.get(0);
		    this.tail = this.indices.get(this.size - 1);

            for(int i = 0; i<newIndices.length;i++){
                if(i == 0){
                    this.indices.get(i).prev = null;
                    this.indices.get(i).next = this.indices.get(i+1);
                }
                else if(i == newIndices.length-1){
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = null;
                }
                else{
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = this.indices.get(i+1);
                }
            }
        }
        else{
            this.size = 0;
		    this.head = null;
		    this.tail = null;
        }
	}
	
	
	//Given an index, the .add() method adds a new node at the given index 
	public boolean add(int index, E elem) {
		if(index < 0) {
			throw new IllegalStateException();		
			}
		else if (index > this.size) {
			throw new IllegalStateException();		}
		
		if( index == 0) {
			add(elem);
		}
		else if (size == 1) {
			
		}
		else {
			Node<E> h = indices.get(index);
			Node<E> n = new Node<>(elem, h, h.prev);
			h.prev.next = n;
			h.prev = n;
			this.size++;
			indices.add(index, n);
		}
		return true;
	}
	
	//The .add() method adds a new node at the head
	public boolean add(E elem) {
		if(this.size == 0) {
			head = new Node<>(elem,tail,null);
			tail = head;
		}
		else if(this.size == 1) {
			head = new Node<>(elem,tail,null);
			tail.prev = head;
			tail.next = null;
		}
		else {
			head.next.prev = new Node<>(elem,head,null);
		}
		
		indices.add(0, head);
		this.size++;
		
		return true;
	}
	
	//The .append() method adds a new node at the tail
	public boolean append(E elem) {
		if(size == 0) {
			this.add(elem);
		}
		else if(size == 1) {
			//System.out.println("Balls");
			add(0,elem);
			/*Node<E> temp = head;
			head = tail;
			tail = head;*/ 
			//return indices.add(tail);
			Node<E> temp2 = tail;
			Node<E> temp = head;
			temp.next = null;
			temp.prev = tail.prev;
			temp2.next = head.next;
			temp2.prev = null;
			head = temp2;
			tail = temp;
			//System.out.println(head.data);
			indices.add(size-1, tail);
			indices.add(0,head);
			/*System.out.println(elem);
			tail = new Node<>(elem,null,tail.prev);
			System.out.println(tail.data);
			head.next = tail;
			tail.prev = head;
			System.out.println(head.next.data);
			this.size++;*/
		}
		else {
			tail.next = new Node<E>(elem,null,tail);
			tail = tail.next;
			indices.add(size, tail);
			this.size++;;
		}
		//return true;
		return indices.add(tail);
	}

	//The .get() method returns the data of a node at the given index
	public E get(int index) {
		if(index < 0 ) {
			throw new IllegalStateException();
		}
		else if (index > this.size) {
			throw new IllegalStateException();		}
		else if (index == 0) {
			return head.data;
		}
		else if (index == size-1) {
			return tail.data;
		}
		else {
			Node<E> temp = head;
			for(int i = 0; i < index; i++) {
				temp = temp.next;
			}
		return temp.data;	
		}
	}
	
	//The .getHead() method returns the data of the node at the head
	public E getHead() {
		if(this.size == 0) {
			return null;
		}
        return head.data;
	}
		
	//The .getLast() method returns the data of the node at the tail
	public E getLast() {
		if(this.size == 0) {
			return null;
		}
        return tail.data;
	}

	//The .remove() method removes the node at the head and returns the node's data
	public E remove() {
		if(this.size == 0) {
			throw new IllegalStateException();
		}
		else if (this.size == 1) {
			E temp = head.data;
			head = null;
			tail = null;
			size = 0;
			indices.clear();
			return temp;
		}
		
		E temp = head.data;
		head = head.next;
		size--;
		indices.remove(0);
		return temp;
 	}	
		
	//The .removeLast() method removes the node at the tail and returns the node's data
	public E removeLast() {
        if(this.size == 0) {
        	throw new IllegalStateException();
        }
        if(this.size == 1) {
        	/*E temp = tail.data;
        	tail = null;
        	head = null;
        	size = 0;
        	indices.clear();
        	return temp;*/
        	return remove();
        }
        E temp = tail.data;
        tail = tail.prev;
        tail.next = null;
        this.size--;
        indices.remove(this.size);
        return temp;
	}
		
	//Given an index, the .removeAt() method removes the node at the index and returns its data
	public E removeAt(int index) {
		if(size <= 0 && index > 0) {
			throw new IllegalStateException();		
		}
		if(size <= 0) {
			return null;
		}
		if(index < 0) {
			throw new IllegalStateException();	
		}
		if(index > size) {
			throw new IllegalStateException();
		}
		if(index == 1) {
			throw new IllegalStateException();
		}
		if(index == 0) {
			return remove();
		}
		if(index == size-1) {
			return removeLast();
		}
		if(size == 1) {
			return remove();
		}
		if(index == size-2) {
			Node<E> temp = head;
			for(int i = 0; i < index-1; i++) {
				temp = temp.next;
			}
			E t = temp.data;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			return t;
		}
		//System.out.println(index);
		//System.out.println(size);
		Node<E> temp = head;
		for(int i = 0; i < index-1; i++) {
			temp = temp.next;
		}
		temp = temp.next;
		//System.out.println(temp.data);
		E t = temp.data;
		remove(t);
		//temp.prev.next = temp.next;
		//temp.next.prev = temp.prev;
		return t;
	}
	
	//Given an element to remove, the .remove() method removes the node that is the first instance
	//in which the data is found to be the same
	public boolean remove(E elem) {
		if(size <= 0) {
			return false;
		}
		if(elem.equals(head.data)) {
			remove();
			return true;
		}
		if(elem.equals(tail.data)) {
			removeLast();
			return true;
		}
		Node<E> temp = head;
		int i = 0;
		while(temp.data != elem && temp.next != null) {
			 /*if(temp.data.equals(elem)) {
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
				size--;
				indices.remove(i);
				return true; 
				}*/
			i++;
			temp = temp.next;
		}
		if(temp.next == null) {
			return false;
			}
		
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		size--;
		indices.remove(i);
		return true;
		
	}

	//The .size() method returns the size of the ArrayList
	public int size() {
		return this.size;
	}

	//Converts the ArrayList into a readible string of each Node's data
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		if (this.size > 0) {
			for (int i = 0; i < this.size; i++) {
				result.append(this.indices.get(i).data + ", ");
			}
			result.delete(result.length() - 2, result.length());
		}
		result.append("]");
		return result.toString();
	}

	public static void main(String[] args) {
		/*String al10[] = new String[7];
		al10[0] = "A";
		        al10[1] = "B";
		        al10[2] = "C";
		        al10[3] = "D";
		        al10[4] = "E";
		        al10[5] = "F";
		        al10[6] = "G";
		IDLList<String> L102 = new IDLList<String>(al10);
		System.out.println(L102.toString());
		L102.removeAt(4);
		//L102.remove("A");
		System.out.println(L102.toString());
		*/
		
		IDLList<String> L6 = new IDLList<String>();
		L6.append("X");
		L6.append("Y");
		L6.append("Z");
		System.out.println(L6.toString());
		
		/*String al9[] = new String[0];
	    al9[0] = "A";
	            al9[1] = "B";
	            al9[2] = "C";
	            al9[3] = "D";
	            al9[4] = "E";
	            al9[5] = "F";
	            al9[6] = "G";
	    IDLList<String> L92 = new IDLList<String>(al9);
	   L92.append("B");
	   System.out.println(L92.toString());
	   L92.append("G");
	   System.out.println(L92.toString());
	   */
	}
}
