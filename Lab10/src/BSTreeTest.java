public class BSTreeTest {
	public static void printInOrder(BSTNode root){
		if(!(root==null)){
			printInOrder(root.left);
			System.out.print(root.key+" ");
			printInOrder(root.right);
		}
	}
	public static void main(String[] args){
		BSTree test = new BSTree();
		int[] data = new int[]{5,18,3,25,27,45,97,88,26,15,17,16};
		for(int i=0;i<data.length;i++){
			test.insert(data[i]);
		}
		printInOrder(test.root);
		if(test.search(3)){
			System.out.println("3: search successful.");
		}
		else{
			System.out.println("3: search unsuccessful.");
		}
		if(test.search(88)){
			System.out.println("88: search successful.");
		}
		else{
			System.out.println("88: search unsuccessful.");
		}
		if(test.search(27)){
			System.out.println("27: search successful.");
		}
		else{
			System.out.println("27: search unsuccessful.");
		}
		if(test.search(28)){
			System.out.println("28: search successful.");
		}
		else{
			System.out.println("28: search unsuccessful.");
		}
		if(test.remove(88)==false){
			System.out.println("88: Item not found.");
		}
		printInOrder(test.root);
		System.out.println("");
		if(test.remove(18)==false){
			System.out.println("18: Item not found.");
		}
		printInOrder(test.root);
		System.out.println("");
		if(test.remove(5)==false){
			System.out.println("5: Item not found.");
		}
		printInOrder(test.root);
		System.out.println("");
		if(test.remove(30)==false){
			System.out.println("30: Item not found.");
		}
		printInOrder(test.root);
	}
}
