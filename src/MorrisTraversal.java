
public class MorrisTraversal {
	//Keep Moving left while establishing the link between the node and its in order pred
	//Print the data once you revisit the node( Can be detected if you coming to node using the 
	//treaded link - pred right is current and not null)
	public static void inOrder(Node root){
		Node current = root;
		while(current != null){
			//Idea is to establish the link between the in order predecessor with the current node
			
			//1. If there is no left child simply print the current data and move right
			if(current.left == null){
				System.out.print(current.data + " | ");
				current = current.right;
			}
			
			//2. If left is not null, find the in order predecessor and make note that it may be 
			//threaded, so remove the thread if present ( pred.right != current )
			Node pred = current.left;
			while(pred.right != null && pred.right != current){
				pred = pred.right;
			}
			
			if(pred.right == null){
				// Change current to the pred
				pred.right = current;
				current = pred;
			}else{
				//Current pred node is threaded, and made sure all the nodes to left of current are processed
				pred.right = null;
				System.out.print(current.data + " | ");
				current = current.right;
			}
		}
	}
	
	public static void preOrder(Node root){
		Node current = root;
		while(current != null){
			//Idea is to establish the link between the in order predecessor with the current node
			
			//1. If there is no left child simply print the current data and move right
			if(current.left == null){
				System.out.print(current.data + " | ");
				current = current.right;
			}
			
			//2. If left is not null, find the inorder predecessor and make note that it may be 
			//threaded, so remove the thread if present ( pred.right != current )
			Node pred = current.left;
			while(pred.right != null && pred.right != current){
				pred = pred.right;
			}
			
			if(pred.right == null){
				// Change current to the pred
				pred.right = current;
				//(Imp Note - Befor visiting the current pred, we print the current state then we visit the pred)
				System.out.print(current.data + " | ");
				current = current.left;
			}else{
				//Current pred node is threaded, and made sure all the nodes to left of current are processed
				pred.right = null;
				
				current = current.right;
			}
		}
	}
}
