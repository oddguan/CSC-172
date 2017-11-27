public class Lab9P1 {
    class BTNode{
        int data;
        BTNode left;
        BTNode right;

        BTNode(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        BTNode(int data, int left, int right){
            this.data = data;
            this.left = new BTNode(left);
            this.right = new BTNode(right);
        }
    }

    private static void printGivenLevel(BTNode root, int i){
        if(root == null){
            return;
        }
        if(i==1){
            System.out.print(root.data + " ");
        }
        else{
            printGivenLevel(root.left, i-1);
            printGivenLevel(root.right, i-1);
        }
    }
    private static void level_order_print(BTNode root){
        int height = height(root);
        for(int i=0;i<height+1;i++){
            printGivenLevel(root,i);
            if(i!=0){
                System.out.println("");
            }
        }
    }

    private static int height(BTNode root){
        if (root == null){
            return 0;
        }
        else{
            int lheight = (height(root.left));
            int rheight = (height(root.right));
            if(lheight > rheight){
                return (lheight + 1);
            }
            else{
                return (rheight + 1);
            }
        }
    }

    public static void main(String[] args){
        Lab9P1 a = new Lab9P1();
        Lab9P1.BTNode root = a.new BTNode(1,2,3);
        root.left = a.new BTNode(root.left.data, 4,5);
        root.right = a.new BTNode(root.right.data, 6,7);
        System.out.println("height: " + height(root));
        level_order_print(root);

    }
}
