
public class BinarySearchTree2 {
	Node root;
	public BinarySearchTree2() {
		// TODO Auto-generated constructor stub
		root = new Node(70);
		root.left = new Node(50);
		root.right = new Node(75);
		root.left.left = new Node(24);
		root.left.right = new Node(54);
		root.left.right.right = new Node(27);
	}
}
