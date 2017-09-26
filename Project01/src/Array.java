import java.util.Random;

public class Array {
	Random ran = new Random();
	private int[][] arr;
	private int[] num = {2,2,2,2,4};
	private boolean chk = true;
	private int i1;
	private int i2;
	public boolean n=false;
	public boolean m=false;
	public boolean f=true;

	public Array(){
		arr = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				arr[i][j] = 0;
			}
		}
	}
	
	public void addRandomNumber(){

		while(f){
			i1 = ran.nextInt(4);
			i2 = ran.nextInt(4);
			if(arr[i1][i2]==0){
				arr[i1][i2] = num[ran.nextInt(5)];
				f=false;
			}

		}
		f=true;
		
	}
	
	public void reset(){
		arr = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				arr[i][j] = 0;
			}
		}
		addRandomNumber();
	}
	
	public int[][] getArr(){
		return arr;
	}
	
	
	
	public void move(int direction){
		n=false;
		switch(direction){
		case 1: //move left
			for(int i=0;i<4;i++){
				int counter = 0;
				for(int j=0;j<4;j++){
					if(arr[i][j]==0){
						counter++;
					}
					else if(arr[i][j]!=0 && j==0){
						continue;
					}
					else if(j==0 && arr[i][j]!=0 && arr[i][j+1]==arr[i][j]){
						continue;
					}
//					else if(j==1 && arr[i][j]!=0 && arr[i][j-1]==arr[i][j]){
//						continue;
//					}
					else if(counter>0){
						arr[i][j-counter]=arr[i][j];
						arr[i][j]=0;
						n=true;
					}
				}
			}
			break;
		case 2:
			for(int i=0;i<4;i++){
				int counter = 0;
				for(int j=0;j<4;j++){
					if(arr[3-i][3-j]==0){
						counter++;
					}
					else if(arr[3-i][3-j]!=0 && (j)==0){
						continue;
					}
					else if(j==0 && arr[3-i][3-j]!=0 && arr[3-i][2-j]==arr[3-i][3-j]){
						continue;
					}
//					else if(j==1 && arr[3-i][3-j]!=0 && arr[3-i][4-j]==arr[3-i][3-j]){
//						continue;
//					}
					else if(counter>0){
						arr[3-i][3-j+counter]=arr[3-i][3-j];
						arr[3-i][3-j]=0;
						n=true;
					}
				}
			}
			break;
			
		case 3:
			for(int i=0;i<4;i++){
				int counter = 0;
				for(int j=0;j<4;j++){
					if(arr[j][i]==0){
						counter++;
					}
					else if(arr[j][i]!=0 && j==0){
						continue;
					}
					else if(j==0 && arr[j][i]!=0 && arr[j+1][i]==arr[j][i]){
						continue;
					}
//					else if(j==1 && arr[j][i]!=0 && arr[j-1][i]==arr[j][i]){
//						continue;
//					}
					else if(counter>0){
						arr[j-counter][i]=arr[j][i];
						arr[j][i]=0;
						n=true;
					}
				}
			}
			break;
		
		case 4:
			for(int i=0;i<4;i++){
				int counter = 0;
				for(int j=0;j<4;j++){
					if(arr[3-j][3-i]==0){
						counter++;
					}
					else if(arr[3-j][3-i]!=0 && j==0){
						continue;
					}
					else if(j==0 && arr[2-j][3-i]!=0 && arr[2-j][3-i]==arr[3-j][3-i]){
						continue;
					}
					else if(j==1 && arr[3-j][3-i]!=0 && arr[3-j][3-i]==arr[4-j][3-i]){
						continue;
					}
					else if(counter>0){
						arr[3-j+counter][3-i]=arr[3-j][3-i];
						arr[3-j][3-i]=0;
						n=true;
					}
				}
			}
			break;
		}
	}
	
	public void merge(int direction){
		m=false;
		switch(direction){
		case 1:
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[i][j]==arr[i][j-1] && arr[i][j]!=0){
						arr[i][j-1]+=arr[i][j];
						arr[i][j]=0;
						m=true;
						
					}
				}
			}
			break;
		
		case 2:
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[3-i][3-j]==arr[3-i][3-j+1] && arr[3-i][3-j]!=0){
						arr[3-i][3-j+1]+=arr[3-i][3-j];
						arr[3-i][3-j]=0;
						m=true;
					}
				}
			}
			break;
			
		case 3:
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[j][i]==arr[j-1][i] && arr[j][i]!=0){
						arr[j-1][i]+=arr[j][i];
						arr[j][i]=0;
						m=true;
					}
				}
			}
			break;
			
		case 4:
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[3-j][3-i]==arr[4-j][3-i] && arr[3-j][3-i]!=0){
						arr[3-j][3-i]+=arr[4-j][3-i];
						arr[4-j][3-i]=0;
						m=true;
					}
				}
			}
			break;
		}
	}
	

}
