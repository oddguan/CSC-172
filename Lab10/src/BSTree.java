public class BSTree {
	BSTNode root;
	public BSTree(){
		this.root = null;
	}
	public BSTree(BSTNode root){
		this.root = root;
	}
	public boolean insert(int key){
		if(this.root==null){
			root = new BSTNode(key,null,null,null);
		}
		else if(this.search(key)){
			return false;
		}
		else{
			BSTNode temp = this.root;
			while(true){	
				if(key<temp.key&&temp.left==null){
					temp.left = new BSTNode(key,temp,null,null);
					break;
				}
				else if(key<temp.key&&(!(temp.left==null))){
					temp = temp.left;
					continue;
				}
				else if(key>temp.key&&temp.right==null){
					temp.right = new BSTNode(key,temp,null,null);
					break;
				}
				else if(key>temp.key&&(!(temp.right==null))){
					temp = temp.right;
					continue;
				}
			}
		}
		return true;
	}
	public boolean remove(int key){
		if(!this.search(key)){
			return false;
		}
		else{
			BSTNode temp = this.root;
			while(temp.key!=key){
				if(key>temp.key){
					temp = temp.right;
				}
				else if(key<temp.key){
					temp = temp.left;
				}
			}
			if(temp.isLeaf()){
				if(temp.key<temp.parent.key){
					temp.parent.left = null;
					temp.parent = null;
				}
				else{
					temp.parent.right = null;
					temp.parent = null;
				}
			}
			else if((!(temp.left==null))^(!(temp.right==null))){
				if(temp.right==null){
					if(temp.key<temp.parent.key){
						temp.parent.left = temp.left;
						temp.left.parent = temp.parent;
					}
					else{
						temp.parent.right = temp.left;
						temp.left.parent = temp.parent;
					}
				}
				else if(temp.left==null){
					if(temp.key<temp.parent.key){
						temp.parent.left = temp.right;
						temp.right.parent = temp.parent;
					}
					else{
						temp.parent.right = temp.right;
						temp.right.parent = temp.parent;
					}
				}
				
			}
			else if((!(temp.left==null))&&(!(temp.right==null))){
				BSTNode temp2 = temp.right;
				while(!(temp2.left==null)){
					temp2 = temp2.left;
				}
				int storage = temp2.key;
				this.remove(storage);
				temp.key=storage;
			}
		}
		return true;
	}
	public boolean search(int key){
		if(this.root.key == key){
			return true;
		}
		else if(this.root.key < key &&(!(this.root.right==null))){
			BSTree temp = new BSTree(this.root.right);
			return temp.search(key);
		}
		else if(this.root.key > key &&(!(this.root.left==null))){
			BSTree temp = new BSTree(this.root.left);
			return temp.search(key);
		}
		else{
			return false;
		}
	}
}
