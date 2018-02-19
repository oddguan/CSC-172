public class BSTNode{
		int key;
		BSTNode parent;
		BSTNode left;
		BSTNode right;
		BSTNode(int key, BSTNode parent, BSTNode left, BSTNode right){
	            this.key = key;
	            this.parent = parent;
	            this.left = left;
	            this.right = right;
	        }
	    public boolean isLeaf(){
	        if((left==null)&&(right==null)){
	        		return true;
	        }
	        else{
	        	return false;
	        }
	    }
	}