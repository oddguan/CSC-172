import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class test {
    public static void main(String[] args) throws Exception {
        String s;
        HashMap<Character, Integer> store = new HashMap<Character,Integer>();
        BufferedReader br = new BufferedReader(new FileReader("/home/oddguan/IdeaProjects/csc172/Project03/src/freq.txt"));
        while((s = br.readLine()) != null){
            String[] temp = s.split(":");
            Character c = (char) Integer.parseInt(temp[0],2);
            System.out.print(c);
            store.put(c, Integer.parseInt(temp[1]));
            System.out.println(":"+Integer.parseInt(temp[1]));
        }
    }
}
