import java.util.Random;

public class Array {
	private Random ran = new Random();
	private int[][] arr;
	private int[] num = {2,2,2,2,4};
	private boolean chk = true;
	private int i1;
	private int i2;
	public boolean n=false;
	public boolean m=false;
	public boolean f=true;
	private int max=4;
	private int count;
	private int moves;

	public int getMoves(){ //getter for the variable "moves"
		return moves;
	}

	public void setMoves(int moves){ //setter for the variable "moves"
		this.moves=moves;
	}

	public int getMax(){
		return max;
	}


	public Array(){ //constructor of the "Array" class
		arr = new int[4][4]; //initialize a new 4x4 array
		for(int i=0;i<4;i++){ //a double for loop to set everything inside to be 0
			for(int j=0;j<4;j++){
				arr[i][j] = 0;
			}
		}
		count=2; // set the count to be 2 because we initially have two numbers
		moves=0; //moves = 0 because we haven't moved yet
	}
	
	public void addRandomNumber(){ //a method to add a new number into the array

		while(f){ //while f is true
			//initialize the position to be added this new number
			i1 = ran.nextInt(4);
			i2 = ran.nextInt(4);
			if(arr[i1][i2]==0){ // if this random position is empty. If not 0, while loop will perform again until empty space is found
				//swap the new random number into this position
				arr[i1][i2] = num[ran.nextInt(5)];
				f=false; //set f to be false to stop the while loop
				count++; //add up the counter

			}

		}
		f=true; //set f to be true after finishing the while loop for next time use
		
	}

	public boolean checked(){ //a method to check whether the game is over or not
		if(count==16){ //if the board is full (counter = 16 means there are 16 elements on the board already)
			Array testArray = new Array(); //create a new "Array"
			testArray.setArr(arr); //copy the current array into the new "Array" class
			for(int i=1;i<5;i++){ //use the test array to move in 4 directions and merge in 4 directions
				testArray.move(i);
				testArray.merge(i);
			}
			if(testArray.m==false && testArray.n==false){ //if it is unable to either move or merge
				return false; //the game is over
			}
			else{return true;} //else the game is still playable


		}
		else{return true;} // if counter is not 16, the game is still playable
	}
	
	public void reset(){ //a method to reset the game
		arr = new int[4][4]; //make the array a new array
		for(int i=0;i<4;i++){ //set every space to be 0
			for(int j=0;j<4;j++){
				arr[i][j] = 0;
			}
		}
		addRandomNumber(); //add in two Random numbers
		addRandomNumber();
        count=2; //set the counter to be 2
        moves=0; //moves to 0
	}

	public int[][] getArr(){
		return arr;
	} //getter of the variable "arr"

	public void setArr(int[][] newarr){ //setter of the variable "arr"
		//since it is a 2D array, a double for loop is required to copy the array
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				newarr[i][j] = arr[i][j];
			}
		}
	}
	
	
	
	public void move(int direction){ // a method to move everything into one direction
		n=false;
		switch(direction){// a switch statement to control four directions
		case 1: //move left
			for(int i=0;i<4;i++){
				int counter = 0; //initialize a local variable "counter"
				for(int j=0;j<4;j++){
					if(arr[i][j]==0){ //if the space is 0, counter adds up 1
						counter++;
					}
					else if(arr[i][j]!=0 && j==0){
						//if the current element is not 0 but the position is 0, continue since it is at the first place
						continue;
					}
					else if(j==0 && arr[i][j]!=0 && arr[i][j+1]==arr[i][j]){
						//if at position 0, and position 1 is the same element as position 0, continue
						continue;
					}

					else if(counter>0){
						//if more than one space found, counter will be greater than 0
						//move everything counter position before its current position
						arr[i][j-counter]=arr[i][j];
						arr[i][j]=0;
						n=true; //set n to be true to indicate that the array has been "moved"
					}
				}
			}
			break;

		case 2: //move right, method same as case 1
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

					else if(counter>0){
						arr[3-i][3-j+counter]=arr[3-i][3-j];
						arr[3-i][3-j]=0;
						n=true;
					}
				}
			}
			break;
			
		case 3: //move up, same method
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

					else if(counter>0){
						arr[j-counter][i]=arr[j][i];
						arr[j][i]=0;
						n=true;
					}
				}
			}
			break;
		
		case 4: //move down, same method
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
		//a method designed to merge the same element that is next to each other after moving
		m=false;
		switch(direction){//same as "move", using a switch statement to control the whole method
		case 1: //move left
			for(int i=0;i<4;i++){ //double for loop to access every element in the array
				for(int j=1;j<4;j++){
					if(arr[i][j]==arr[i][j-1] && arr[i][j]!=0){
						//if the current element has the same value with the previous one
						//and they both not equal to 0
						arr[i][j-1]+=arr[i][j]; //add them up to the first position
						if(arr[i][j-1]>max){// if the newly added element is larger than the maximum value presented
							max=arr[i][j-1]; //set it to the maximum value presented
						}
						arr[i][j]=0; //set the second position to be 0
						m=true; //set m to be true to indicate that the array has been merged
						count--; //minus the count because one element disappeared

					}
				}
			}
			break;
		
		case 2: //move right, same as above
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[3-i][3-j]==arr[3-i][3-j+1] && arr[3-i][3-j]!=0){
						arr[3-i][3-j+1]+=arr[3-i][3-j];
						if(arr[3-i][3-j+1]>max){
							max=arr[3-i][3-j+1];
						}
						arr[3-i][3-j]=0;
						m=true;
						count--;
					}
				}
			}
			break;
			
		case 3: //move up, same as above
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[j][i]==arr[j-1][i] && arr[j][i]!=0){
						arr[j-1][i]+=arr[j][i];
						if(arr[j-1][i]>max){
							max=arr[j-1][i];
						}
						arr[j][i]=0;
						m=true;
						count--;
					}
				}
			}
			break;
			
		case 4: //move down, same as above
			for(int i=0;i<4;i++){
				for(int j=1;j<4;j++){
					if(arr[3-j][3-i]==arr[4-j][3-i] && arr[3-j][3-i]!=0){
						arr[3-j][3-i]+=arr[4-j][3-i];
						if(arr[3-j][3-i]>max){
							max=arr[3-j][3-i];
						}
						arr[4-j][3-i]=0;
						m=true;
						count--;
					}
				}
			}
			break;
		}
	}
	

}
