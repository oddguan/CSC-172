import java.util.Scanner;
public class isAnagram {
	public static boolean isanAnagram (String a, String b){
		char[] a1 = a.toCharArray();
		char[] b1 = b.toCharArray();
		if(a1.length!=b1.length){
			return false;
		}
		else{
			for(int i=0;i<a1.length;i++){
				for(int j=0;j<b1.length;j++){
					if(j==(b1.length-1)&&a1[i]!=b1[j]){
						return false;
					}
					if(b1[j]==0){
						continue;
					}
					else if(a1[i]!=(b1[j])){
						continue;
					}
					else{
						b1[j]=0;
						break;
					}
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
		if(isanAnagram(a,b)){
			System.out.println(a+" and "+b+" are anagrams.");
		}
		else{
			System.out.println(a+" and "+b+" are not anagrams.");
		}
		scanner.close();
	}
}
