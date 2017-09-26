import java.util.ArrayList;

public class Print {
	public static void print2Darray(int[][] array){
		for(int i=0;i<4;i++){
			System.out.println();
			for(int j=0;j<4;j++){
				System.out.printf("%-6d",array[i][j]);
			}
		}
	}
	
	public static void print2DList(ArrayList<ArrayList<Integer>> array){
		for(int i=0;i<4;i++){
			System.out.println();
			for(int j=0;j<4;j++){
				System.out.printf("%-6d",array.get(i).get(j));
			}
		}
	}
	
	public static void runningSum2DArray(int[][] array, int direction){
		switch(direction){
		case 1:
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					for(int k=j;k<3;k++){
						array[i][j] += array[i][k+1];
					}
				}
			}
			
			break;
		
		case 2:
			for(int i=0;i<4;i++){
				for(int j=3;j>-1;j--){
					for(int k=j;k>0;k--){
						array[i][j] += array[i][k-1];
					}
				}
			}
			
			break;
		
		case 3:
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					for(int k=j;k<3;k++){
						array[j][i] += array[k+1][i];
					}
				}
			}
			break;
		
		case 4:
			for(int i=0;i<4;i++){
				for(int j=3;j>-1;j--){
					for(int k=j;k>0;k--){
						array[j][i] += array[k-1][i];
					}
				}
			}
			break;
		}
		
	}
	
	
	public static void runningSum2DArrayList(ArrayList<ArrayList<Integer>> array, int direction){
		switch(direction){
		case 1:
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					for(int k=j;k<3;k++){
						array.get(i).set(j, array.get(i).get(j)+array.get(i).get(k+1));
					}
				}
			}
			
			break;
		
		case 2:
			for(int i=0;i<4;i++){
				for(int j=3;j>-1;j--){
					for(int k=j;k>0;k--){
						array.get(i).set(j, array.get(i).get(j)+array.get(i).get(k-1));
					}
				}
			}
			
			break;
		
		case 3:
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					for(int k=j;k<3;k++){
						array.get(j).set(i, array.get(j).get(i)+array.get(k+1).get(i));
					}
				}
			}
			break;
		
		case 4:
			for(int i=0;i<4;i++){
				for(int j=3;j>-1;j--){
					for(int k=j;k>0;k--){
						array.get(j).set(i, array.get(j).get(i)+array.get(k-1).get(i));
					}
				}
			}
			break;
		}
		
	}
	
	
	
	
	public static void main(String[] args){
		int[][] arr = {{1,2,33,4},{1,2,36,4},{19,2,3,4},{3,4,5,6}};
		print2Darray(arr);
		ArrayList<ArrayList<Integer>> ar = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<4;i++){
			ar.add(new ArrayList<Integer>());
		}
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				ar.get(i).add(arr[i][j]);
			}
		}
		print2DList(ar);
		
		runningSum2DArray(arr,1);
		System.out.println("");
		print2Darray(arr);
		runningSum2DArray(arr,2);
		System.out.println("");
		print2Darray(arr);
		runningSum2DArray(arr,3);
		System.out.println("");
		print2Darray(arr);
		runningSum2DArray(arr,4);
		System.out.println("");
		print2Darray(arr);
		runningSum2DArrayList(ar,1);
		System.out.println("");
		print2DList(ar);
		runningSum2DArrayList(ar,2);
		System.out.println("");
		print2DList(ar);
		runningSum2DArrayList(ar,3);
		System.out.println("");
		print2DList(ar);
		runningSum2DArrayList(ar,4);
		System.out.println("");
		print2DList(ar);
		
	}
}
