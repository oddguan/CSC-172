import java.util.Scanner;

public class isRotation {
	public static boolean isARotation(String a, String b){
		char[] a1 = a.toCharArray();
		char[] b1 = b.toCharArray();
		int gap=0;
		
		if(a1.length!=b1.length){
			return false;
		}
		else{
			for(int j =0;j<b1.length;j++){
				if(a1[0]==b1[j]){
					gap = j;
					break;
				}
			}
			for(int i=0;i<a1.length;i++){
				if(a1[i]!=(b1[(i+gap)%a1.length])){
					return false;
				}
				else{
					continue;
				}
			}
		}
		return true;
	}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the first word: ");
		String a = scanner.nextLine();
		System.out.print("Please enter the second word: ");
		String b = scanner.nextLine();
		if(isARotation(a,b)){
			System.out.println(a+" and "+b+" are rotations.");
		}
		else{
			System.out.println(a+" and "+b+" are not rotations.");
		}
		scanner.close();
	}
}

