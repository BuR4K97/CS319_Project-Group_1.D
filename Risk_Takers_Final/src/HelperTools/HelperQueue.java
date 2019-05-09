package HelperTools;

public class HelperQueue<ElementType> {

	private static class QueueNode<ElementType> {
		private ElementType element;
		private QueueNode<ElementType> next;
	}
	
	private QueueNode<ElementType> root;
	
	public void queue(ElementType insert) {
		if(root == null) {
			root = new QueueNode<ElementType>(); 
			root.element = insert;
		}
		
		QueueNode<ElementType> currNode = root;
		while(currNode.next != null) {
			if(currNode.element.equals(insert)) 
				return;
			currNode = currNode.next; 
		}
		
		currNode.next = new QueueNode<ElementType>();
		currNode.element = insert;
	}
	
	public ElementType dequeue() {
		if(root == null) return null;
		
		ElementType extract = root.element;
		root = root.next;
		return extract;
	}
	
	public ElementType retrieveElement(int index) {
		QueueNode<ElementType> currNode = root;
		for(int i = 0; i < index; i++) {
			if(currNode == null) 
				return null;
			currNode = currNode.next;
		}
		
		if(currNode == null) 
			return null;
		return currNode.element;
	}
	
}
