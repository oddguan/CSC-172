
public class Lab9P2 {
	public static BTNode reconstruct_tree(int[] preOrder, int[] inOrder) {
	    int preOrderStart = 0;
	    int preOrderEnd = preOrder.length-1;
	    int inOrderStart = 0;
	    int inOrderEnd = inOrder.length-1;
	    return reconstructTree(preOrder, preOrderStart, preOrderEnd, inOrder, inOrderStart, inOrderEnd);
	}
	public static int getIndex(int[] array, int element){//get the index of an element
		for(int i=0;i<array.length;i++){
			if(element==array[i]){
				return i;
			}
		}
		return -1;
	}
	public static BTNode reconstructTree(int[] preOrder, int preOrderStart, int preOrderEnd, int[] inOrder, int inOrderStart, int inOrderEnd){
	    if(preOrderStart>preOrderEnd||inOrderStart>inOrderEnd){//Invalid array
	        return null;
	    }
	    int storage = preOrder[preOrderStart];
	    BTNode root = new BTNode(storage);
	    if(getIndex(inOrder,storage)==-1){
	    	return null;
	    }
	    int index = getIndex(inOrder,storage);
	    root.left = reconstructTree(preOrder, 
	    		preOrderStart+1, 
	    		preOrderStart+(index-inOrderStart), //This will give an array containing every nodes on the root's left side (from preOrder array)
	    		inOrder, 
	    		inOrderStart, 
	    		index-1);//Similarly, this will give an array containing every nodes on the root's left side (from inOrder array)
	    root.right= reconstructTree(preOrder, 
	    		preOrderStart+(index-inOrderStart)+1, //Everything on the right side (excluding the elements on the above method)
	    		preOrderEnd, 
	    		inOrder, 
	    		index+1, //Everything on the right side (excluding the elements on the above method)
	    		inOrderEnd);
	    return root;
	}
	public static void printInOrder(BTNode root){
		if(root!=null){
			printInOrder(root.left);
			System.out.print(root.data+" ");
			printInOrder(root.right);
		}
	}
	public static void printPreOrder(BTNode root){
		if(root!=null){
			System.out.print(root.data+" ");
			printPreOrder(root.left);
			printPreOrder(root.right);
		}
	}
	public static void main(String[] args){
		int[] InOrder = new int[] {3,4,8,7,0,1,5,9,2};
		int[] PreOrder = new int[] {5,4,3,0,8,7,1,2,9};
		printInOrder(reconstruct_tree(PreOrder, InOrder));
		printPreOrder(reconstruct_tree(PreOrder, InOrder));
	}
}
