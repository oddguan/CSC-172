public class MakeChange {
    public static int makeChange(int n) {
        if(n<0) {
            System.out.println("That's not valid.");
            return 0;
        }
        else if(n==0) {
            return 0;
        }
        else if(n<5) {
            return 1;
        }
        else {
            if(n%50==0) {
                return makeChange(n-1)+3;
            }
            else if(n%25==0) {
                return makeChange(n-1)+2;
            }
            else if(n%10==0) {
                return makeChange(n-1)+2;
            }
            else if(n%5==0) {
                return makeChange(n-1)+1;
            }
            else {
                return makeChange(n-1);
            }
        }
    }
    public static void main(String[] args) {
        for(int i =0;i<=20;i++) {
            System.out.println(makeChange(i));
        }
    }
}