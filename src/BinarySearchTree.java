import java.util.*;
public class BinarySearchTree {
	Node root;
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		root = new Node(20);
		root.left = new Node(18);
		root.right = new Node(50);
		root.left.left = new Node(12);
		root.left.right = new Node(19);
		root.left.left.left = new Node(10);
		root.left.left.right = new Node(14);
		root.right.left = new Node(40);
		root.right.right = new Node(60);
		root.right.right.right = new Node(70);
		root.right.right.right.left = new Node(68);
		root.right.right.right.left.left = new Node(65);
		root.right.right.right.right = new Node(80);
		root.right.right.right.right.left = new Node(75);
	}
}
