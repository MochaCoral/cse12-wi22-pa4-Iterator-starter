/**
 * Name: Morales, Kyle
 * Email:
 * Sources used: zybooks, piazza answers, PA documentation
 * 
 * Description: Implementation of MyLinkedList<E>
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** 
 * MyLinkedList<E> is an extension of te AbstractList<E> class. 
 * It is a doubly linked list with two sentinel nodes for the head
 * and tail of the list
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	protected class MyListIterator implements ListIterator<E> {
		Node left;
		Node right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

		public MyListIterator() {
			idx = 0;
			forward = true;
			canRemoveOrSet = false;
			left = head;
			right = head.getNext();
		}

		@Override
		public boolean hasNext() {
			if(idx == size) {
				return false;
			}
			else {
				return true;
			}
		}

		@Override
		public E next() throws NoSuchElementException {
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				forward = true;
				canRemoveOrSet = true;
				left = right;
				right = right.getNext();
				idx++;
				return left.getElement();
			}
		}

		@Override
		public boolean hasPrevious() {
			if(idx == 0) {
				return false;
			}
			else {
				return true;
			}
		}

		@Override
		public E previous() throws NoSuchElementException {
			if(!this.hasPrevious()) {
				throw new NoSuchElementException();
			}
			else {
				forward = false;
				canRemoveOrSet = true;
				right = left;
				left = left.getPrev();
				idx--;
				return right.getElement();
			}
		}

		@Override
		public int nextIndex() {
			if(this.hasNext()) {
				return idx;
			}
			else {
				return size;
			}
		}

		@Override
		public int previousIndex() {
			int pidx = idx;
			if(this.hasPrevious()) {
				return --pidx;
			}
			else {
				return -1;
			}
		}

		@Override
		public void remove() throws IllegalStateException {
			if(!canRemoveOrSet) {
				throw new IllegalStateException();
			}
			else {
				if(forward) {
					right.setPrev(left.getPrev());
					left.getPrev().setNext(right);
					left = left.getPrev();
					idx--;
				}
				else {
					left.setNext(right.getNext());
					right.getNext().setPrev(left);
					right = right.getNext();
				}
				size--;
				canRemoveOrSet = false;
			}
		}

		@Override
		public void set(E e) throws IllegalStateException, 
			NullPointerException {
			if(e == null) {
				throw new NullPointerException();
			}
			else if(!canRemoveOrSet) {
				throw new IllegalStateException();
			}
			else {
				if(forward) {
					left.setElement(e);
				}
				else {
					right.setElement(e);
				}
			}
			
			
		}

		@Override
		public void add(E e) throws NullPointerException{
			Node newNode = new Node(e);
			if(e == null) {
				throw new NullPointerException();
			}
			else {
				left.setNext(newNode);
				right.setPrev(newNode);
				newNode.setPrev(left);
				newNode.setNext(right);
				left = newNode;
				canRemoveOrSet = false;
				idx++;
				size++;
			}
			
		}
		

	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		size = 0;
		head = new Node(null);
		tail = new Node(null);
		head.setNext(tail);
		tail.setPrev(head);
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns the element of node at the specified position node 
	 * 
	 * @param index the position index of the node
	 * @return the element of the node
	 * @throws IndexOutOfBoundsException for indices outside of list
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		Node curr = head;
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i <= index; i++) {
			curr = curr.getNext();
		}
		return curr.getElement();
	}

	/**
	 * Inserts element at specified position index
	 * 
	 * @param index the specific index
	 * @param data the data to be inserted
	 * @throws NullPointerException for null data entry
	 * @throws IndexOutOfBoundsException for indices outside of list 
	 */
	@Override
	public void add(int index, E data) throws NullPointerException, 
		IndexOutOfBoundsException {
		Node curr = head;
		Node newNode = new Node(data);
		if(data == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(this.size() == 0) { //when list is empty
			this.head.setNext(newNode);
			this.tail.setPrev(newNode);
			newNode.setPrev(this.head);
			newNode.setNext(this.tail);
		}
		else {
			for(int i = 0; i < index; i++) {
				curr = curr.getNext();
			}
			Node succ = curr.getNext();
			newNode.setNext(curr.getNext());
			newNode.setPrev(curr);
			curr.setNext(newNode);
			succ.setPrev(newNode);
		}
		size++;
	}

	/**
	 * Appends data to a list
	 * 
	 * @param data data to be appended to the list
	 * @return true, due to the abstract method definition
	 * @throws NullPointerException
	 */
	public boolean add(E data) throws NullPointerException {
		Node newNode = new Node(data);
		if(data == null) {
			throw new NullPointerException();
		}
		if(this.size() == 0) { //when list is empty
			this.head.setNext(newNode);
			this.tail.setPrev(newNode);
			newNode.setPrev(this.head);
			newNode.setNext(this.tail);
		}
		else {
			this.tail.getPrev().setNext(newNode);
			newNode.setPrev(this.tail.prev);
			this.tail.setPrev(newNode);
		}
		size++;
		return true;
	}

	/**
	 * Set the element for the node at index to data and return the previously 
	 * stored node
	 * 
	 * @param index the position index of the node
	 * @param data the data overwritting the specified node
	 * @return the previously stored contents of the overwritten node
	 * @throws IndexOutOfBoundsException for indices outside of list
	 * @throws NullPointerException for null data entry
	 */
	public E set(int index, E data) throws IndexOutOfBoundsException, NullPointerException {
		Node curr = head;
		E val;
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(data == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i <= index; i++) {
			curr = curr.getNext();
		}
		val = curr.getElement();
		curr.setElement(data);
		return val;
	}

	/**
	 * Remove the node from the position index and return the removed node data
	 * 
	 * @param index the index of the to be removed node
	 * @return the data within the removed node
	 * @throws IndexOutBoundException 
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
		Node curr = head;
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i <= index; i++) {
			curr = curr.getNext();
		}
		curr.prev.setNext(curr.getNext());
		curr.getNext().setPrev(curr.prev);
		size--;
		return curr.getElement();
	}

	/**
	 * clears a list
	 */
	public void clear() {
		size = 0;
		this.head.setNext(tail);
		this.tail.setPrev(head);
	}
	/**
	 * determines whether the list is empty or not
	 * @return boolean denoting the status of list emptiness
	 */
	public boolean isEmpty() {
		if(this.head.getNext() == this.tail && this.tail.getPrev() == this.head) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * A helper method that returns the Node at a specific index.
	 * 
	 * @param index index of the the node
	 * @return the node in question
	 * @throws IndexOutOfBoundsException
	 */
	protected Node getNth(int index) throws IndexOutOfBoundsException{
		Node curr = this.head;
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for(int i = 0; i <= index; i++) {
				curr = curr.getNext();
			}
		}
		return curr;
	}
}