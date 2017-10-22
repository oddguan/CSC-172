import java.awt.Graphics;

import javax.swing.JComponent;

public class EightQueen{
    //Each queen cannot be in the same row, so each row has 1 queen. Then we can just solve for the row index of each queen.

    public static boolean solveBoard(int[] queencolumn,int index){
        if(index>=8){
            return true;
        }
        for(int i=0;i<8;i++){
            queencolumn[index] = i;
            if(isValid(queencolumn,index)){
                if(solveBoard(queencolumn,index+1)==true){
                    return true;
                }
            }
        }

        return false;
    }
    public static boolean isValid(int[] queencolumn,int index){
        for(int i=0;i<index;i++){
            if(queencolumn[i]==queencolumn[index]){
                return false;
            }
            if((index-i)==(queencolumn[index]-queencolumn[i])||(i-index)==(queencolumn[index]-queencolumn[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        int[] queencolumn = new int[8];
        solveBoard(queencolumn, 0);
        for(int i =0;i<8;i++){
            for(int j=0;j<queencolumn[i];j++){
                System.out.print("* ");
            }
            System.out.print("Q");
            for(int k=0;k<7-queencolumn[i];k++){
                System.out.print(" *");
            }
            System.out.println(" ");
        }
    }
}