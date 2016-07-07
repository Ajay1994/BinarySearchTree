import java.util.Stack;

class LNode{
	int data;
	LNode next;
	LNode(int data){
		this.data = data;
		this.next = null;
	}
}
public class BST extends BinarySearchTree{
	BST(){
		super();
	}
	private void preOrder(Node root){
		if(root == null) return;
		System.out.print(root.data + " -> ");
		preOrder(root.left);
		preOrder(root.right);
	}
	private void inOrder(Node root){
		if(root == null) return;
		inOrder(root.left);
		System.out.print(root.data + " -> ");
		inOrder(root.right);
	}
	private boolean searchKey(Node root, int key){
		if(root == null) return false;
		if(root.data == key) return true;
		if(key < root.data) return searchKey(root.left, key);
		else return searchKey(root.right, key);
	}
	private Node insertInBST(Node root , int key){
		if(root == null) return new Node(key);
		if(key <= root.data) root.left = insertInBST(root.left, key);
		if(key > root.data) root.right = insertInBST(root.right, key);
		return root;
	}
	private Node getLeftmostSuccessor(Node root){
		while(root.left != null) root = root.left;
		return root;
	}
	private Node getRightmostSuccessor(Node root){
		while(root.right != null) root = root.right;
		return root;
	}
	private Node deleteNode(Node root, int key){
		if(root == null) return root;
		if(key < root.data) root.left = deleteNode(root.left, key);
		else if(key > root.data) root.right = deleteNode(root.right, key);
		else{
			if(root.left == null && root.right == null) return null;
			if(root.left == null) return root.right;
			if(root.right == null) return root.left;
			Node inOrderSucc = getLeftmostSuccessor(root.right);
			
			//Copy the inorder succ data into the node to be deleted 
			root.data = inOrderSucc.data;
			
			//Delete the inorder successor
			root.right = deleteNode(root.right, inOrderSucc.data);
		}
		return root;
	}
	static Node predecessor = null;
	static Node inOrderSucc = null;
	private void getInAndPreSuccesor(Node root, int key){
		if(root == null) return;
		if(key < root.data){
			inOrderSucc = root;
			getInAndPreSuccesor(root.left, key);
		}else if(key > root.data){
			predecessor = root;
			getInAndPreSuccesor(root.right, key);
		}else{
			if(root.left != null){
				predecessor = getRightmostSuccessor(root.left);
			}
			if(root.right != null){
				inOrderSucc = getLeftmostSuccessor(root.right);
			}
			return;
		}
	}
	private boolean checkIfBST(Node root){
		if(root == null) return true;
		if(root.left != null && root.left.data > root.data) return false;
		if(root.right != null && root.right.data < root.data) return false;
		boolean isLeft = checkIfBST(root.left);
		boolean isRight = checkIfBST(root.right);
		return isLeft && isRight;
	}
	
	private void printSortedArray(int[] array, int start, int end){
		if(start > end) return;
		printSortedArray(array, start * 2 + 1, end);
		System.out.print(array[start] + " | ");
		printSortedArray(array, start * 2 + 2, end);
	}
	
	static int k = 0;
	private void kthSmallestElemnt(Node root, int num){
		if(root == null) return;
		kthSmallestElemnt(root.left, num);
		BST.k += 1;
		if(BST.k == num) System.out.println("Kth Smallest Elemnt is : "+ root.data);
		kthSmallestElemnt(root.right, num);
	}
	
	private void printBSTInRange(Node root, int key1, int key2){
		if(root == null) return;
		if(key1 < root.data) printBSTInRange(root.left, key1, key2);
		if(root.data > key1 && root.data < key2) System.out.print(root.data + " | ");
		if(key2 > root.data) printBSTInRange(root.right, key1, key2);
	}
	
	public Node makeBSTFromSortedArray(int array[], int start, int end){
		if(start > end) return null;
		int mid = (start + end) / 2;
		Node root = new Node(array[mid]);
		root.left = makeBSTFromSortedArray(array, start, mid -1);
		root.right = makeBSTFromSortedArray(array, mid + 1, end);
		return root;
	}
	
	class Info{
		int size;
		boolean isBST;
		public Info(int size, boolean isBST){
			this.size = size;
			this.isBST = isBST;
		}
	}
	public Info getLargestBST(Node root){
		if(root == null) return new Info(0, true);
		if(root.left == null && root.right == null) return new Info(1 , true);
		
		Info left = getLargestBST(root.left);
		Info right = getLargestBST(root.right);
		
		if(left.isBST == false || right.isBST == false || (root.left != null && root.left.data > root.data) 
				|| (root.right != null && root.right.data < root.data)){
			return new Info(Utils.max(left.size, right.size), false);
		}else{
			//System.out.println(root.data);
			return new Info(left.size + right.size + 1, true);
		}
	}
	private Node removeBSTOutRange(Node root, int min, int max){
		if(root == null) return null;
		root.left = removeBSTOutRange(root.left, min, max);
		root.right = removeBSTOutRange(root.right, min, max);
		if(root.data < min) return root.right;
		if(root.data > max) return root.left;
		return root;
	}
	
	static Node head = null;
	static Node tail = null;
	private void convertBSTToDLL(Node root){
		if(root == null) return;
		if(root.left != null) convertBSTToDLL(root.left);
		if(tail != null) tail.right = root;
		else head = root;
		
		root.left = tail;
		
		tail = root;
		if(root.right != null) convertBSTToDLL(root.right);
	}
	private void printDLL(){
		Node start = BST.head;
		while(start != null){
			System.out.print(start.data + " | ");
			start = start.right;
		}
	}
	private void iterativeInorder(Node root){
		Stack<Node> stack = new Stack<Node>();
		Node current = root;
		do{
			while(current != null) {
				stack.push(current);
				current = current.left;
			}
			Node node = stack.pop();
			System.out.print(node.data + " | ");
			if(node.right != null){
				current = node.right;
			}
			if(node.right == null && stack.isEmpty()) break;
		}while(true);
	}
	//Not Working ...
	/*
	public void mergeTwoBST(Node root1, Node root2){
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		Node current1 = root1;
		Node current2 = root2;
		do{
			while(current1 != null){
				stack1.push(current1);
				current1 = current1.left;
			}
			while(current2 != null){
				stack2.push(current2);
				current2 = current2.left;
			}
			if(stack1.isEmpty() && stack2.isEmpty()) break;
			if(!stack1.isEmpty() && !stack2.isEmpty()){
				if(stack1.peek().data < stack2.peek().data){
					Node node = stack1.pop();
					current1 = node.right;
					System.out.print(node.data +" | ");
				}else if(stack1.peek().data > stack2.peek().data){
					Node node = stack2.pop();
					current2 = node.right;
					System.out.print(node.data +" | ");
				}else if(stack1.peek().data == stack2.peek().data){
					Node node = stack1.pop();
					current1 = node.right;
					System.out.print(node.data +" | ");
					
					Node node1 = stack2.pop();
					current2 = node1.right;
					System.out.print(node1.data +" | ");
				}
			}else if(!stack2.isEmpty()){
				Node node = stack2.pop();
				current2 = node.right;
				System.out.print(node.data +" | ");
			}else if(!stack1.isEmpty()){
				Node node = stack1.pop();
				current1 = node.right;
				System.out.print(node.data +" | ");
			}
			
		}while(true);
	}
	*/
	static Node first = null;
	static Node middle = null;
	static Node last = null;
	static Node prev = null;
	private void findSwappedNodes(Node root){
		if(root == null) return;
		findSwappedNodes(root.left);
		
		if(prev != null && prev.data > root.data){
			if(first == null){ // Indicator that this is first violation and nodes are not adjacent.
				first = prev;
				middle = root;
			}else{
				last = root;
			}
		}
		prev = root;
		findSwappedNodes(root.right);
	}
	static int preIndex = 0;
	private Node createTreePreOrder(int[] preOrder, int min, int max){
		if(preIndex >= preOrder.length) return null;
		Node root = null;
		if(preOrder[preIndex] >= min && preOrder[preIndex] <= max){
			 root = new Node(preOrder[preIndex]);
			 preIndex++;
		}
		if(root != null){
			root.left = createTreePreOrder(preOrder, min, root.data - 1);
			root.right = createTreePreOrder(preOrder, root.data + 1, max);
		}
		return root;
	}
	static Node prevNode = null;
	private void getFloor(Node root, int key){
		if(root == null) return;
		getFloor(root.left, key);
		//System.out.println(root.data);
		if(key >= root.data) prevNode = root;
		getFloor(root.right, key);
	}
	
	static int ceil = Integer.MAX_VALUE;
	private void getCeil(Node root, int key){
		if(root == null) return;
		getCeil(root.left, key);
		//System.out.println(root.data);
		if(root.data > key) ceil = Utils.min(ceil, root.data);
		getCeil(root.right, key);
	}
	//Sorted Linked List to BST
	static LNode Lhead = null;
	private Node sortedListToBST(int num){
		if(num <= 0) return null;
		Node left = sortedListToBST(num / 2);
		
		//Need a static reference of head which can update the parent call head from the current call.
		Node root = new Node(Lhead.data);
		root.left = left;
		
		Lhead = Lhead.next;
		
		root.right = sortedListToBST(num - num/2 - 1);
		return root;
	}
	static int i = 1;
	private void getKthLargestELemnt(Node root, int k){
		if(root == null) return;
		getKthLargestELemnt(root.right, k);
		
		if(k == i) System.out.println("Kth Largest Elemnt is :"+ root.data);
		i += 1;
		
		getKthLargestELemnt(root.left, k);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("\n______________________________Check For preorder Print_________________________\n");
		BST tree = new BST();
		tree.preOrder(tree.root);
		
		System.out.println("\n______________________________Check For Searching a Key_________________________\n");
		tree = new BST();
		System.out.println("Is key present : "+ tree.searchKey(tree.root, 89));
		
		System.out.println("\n______________________________Check For Inserting a Key_________________________\n");
		tree = new BST();
		tree.preOrder(tree.root);
		System.out.println();
		Node newRoot = tree.insertInBST(tree.root, 25);
		tree.preOrder(newRoot);
		
		System.out.println("\n______________________________Check For Inorder Succ_________________________\n");
		tree = new BST();
		System.out.println("Inorder successor :" + tree.getLeftmostSuccessor(tree.root).data);
		
		

		System.out.println("\n______________________________Check For Deleteing a Key_________________________\n");
		tree = new BST();
		tree.preOrder(tree.root);
		System.out.println();
		newRoot = tree.deleteNode(tree.root, 70);
		tree.preOrder(newRoot);
		
		System.out.println("\n______________________________Check For Inorder Successor and Predecessor  a Key_________________________\n");
		tree = new BST();
		int key = 19;
		tree.inOrder(tree.root);
		tree.getInAndPreSuccesor(tree.root, key);
		System.out.println("\nKey : "+key+" Inorder :" + inOrderSucc.data + " and Preorder :"+predecessor.data);
		
		System.out.println("\n______________________________Check For If the TRee is BST_________________________\n");
		tree = new BST();
		System.out.println("Is BST : "+ tree.checkIfBST(tree.root));
		
		System.out.println("\n______________________________Check For Sorted order printing in BST_________________________\n");
		tree = new BST();
		int array[] = {4, 2, 5, 1, 3};
		tree.printSortedArray(array, 0, array.length -1 );
		
		System.out.println("\n______________________________Check For kth smallest element in BST_________________________\n");
		tree = new BST();
		tree.kthSmallestElemnt(tree.root, 6);
		
		System.out.println("\n______________________________Check For Morris Preorder Traversal BST_________________________\n");
		tree = new BST();
		MorrisTraversal.preOrder(tree.root);
		
		System.out.println("\n______________________________Check For Morris inOrder Traversal BST_________________________\n");
		tree = new BST();
		MorrisTraversal.inOrder(tree.root);
		
		System.out.println("\n______________________________Check For printing keys in given range BST_________________________\n");
		tree = new BST();
		tree.printBSTInRange(tree.root, 19, 68);
		
		System.out.println("\n______________________________Check For creating BST from sorted array_________________________\n");
		tree = new BST();
		int[] sortArray = {1, 2, 4, 6, 9, 10};
		newRoot = tree.makeBSTFromSortedArray(sortArray, 0, sortArray.length -1);
		tree.preOrder(newRoot);
		
		System.out.println("\n______________________________Check For checking largest BST_________________________\n");
		tree = new BST();
		System.out.println("Size of largest BST :" + tree.getLargestBST(tree.root).size);
		
		System.out.println("\n______________________________Check For removing keys out of given range BST_________________________\n");
		tree = new BST();
		newRoot = tree.removeBSTOutRange(tree.root, 19, 68);
		tree.inOrder(newRoot);
		
		System.out.println("\n______________________________Check For Converting BST to DLL_________________________\n");
		tree = new BST();
		tree.convertBSTToDLL(tree.root);
		tree.printDLL();
		
		System.out.println("\n______________________________Check For Iterative Traversal_________________________\n");
		tree = new BST();
		tree.iterativeInorder(tree.root);
		
		/*
		System.out.println("\n______________________________Check For Merging of two BST_________________________\n");
		BST tree1 = new BST();
		BinarySearchTree2 tree2 = new BinarySearchTree2();
		tree.mergeTwoBST(tree1.root, tree2.root);
		*/
		
		System.out.println("\n______________________________Check For Swap of nodes_________________________\n");
		tree = new BST();
		tree.findSwappedNodes(tree.root);
		System.out.println("fdasf");
		//System.out.println(tree.first.data  + " " + tree.middle.data + " "+ tree.last.data);
		
		System.out.println("\n______________________________Check For Construction of tree from preorder_________________________\n");
		tree = new BST();
		int pre[] = {10, 5, 1, 7, 40, 50};
		newRoot = tree.createTreePreOrder(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
		tree.inOrder(newRoot);
		
		System.out.println("\n______________________________Check For Floor of node_________________________\n");
		tree = new BST();
		tree.getFloor(tree.root, 69);
		if(tree.prevNode == null) System.out.println("-1");
		else System.out.println("Floor is : "+ tree.prevNode.data);
		
		System.out.println("\n______________________________Check For Ceil of node_________________________\n");
		tree = new BST();
		tree.getCeil(tree.root, 69);
		System.out.println("ceil is : "+ tree.ceil);
		
		System.out.println("\n______________________________Check For construction of BST from Linked List_________________________\n");
		Lhead = new LNode(10);
		Lhead.next = new LNode(20);
		Lhead.next.next = new LNode(30);
		Lhead.next.next.next = new LNode(40);
		Lhead.next.next.next.next = new LNode(50);
		Lhead.next.next.next.next.next = new LNode(60);
		Lhead.next.next.next.next.next.next = new LNode(70);
		
		tree = new BST();
		newRoot = tree.sortedListToBST(7);
		tree.preOrder(newRoot);
		
		System.out.println("\n______________________________Check For Kth largest Elment in array_________________________\n");
		tree = new BST();
		tree.inOrder(tree.root);
		System.out.println();
		tree.getKthLargestELemnt(tree.root, 7);
	}

}
