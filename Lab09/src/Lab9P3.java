import java.io.*;
import java.util.*;

public class Lab9P3{
    private static ArrayList readFile(String file) throws Exception{
        ArrayList<String> st = new ArrayList<String>();
        ArrayList<String> output = new ArrayList<String>();
        String r;
        File read = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(read));
        while((r = br.readLine())!=null){
            st.add(r);
        }
        for(String s : st){
            String[] array = s.split("\\s+");
            for(String a : array){
                output.add(a);
            }
        }
        return output;
    }

    private static void countFreq(ArrayList<String> al) throws Exception{
        Map<String, Integer> store = new HashMap<String, Integer>();
        for(String s : al){
            if(store.containsKey(s)){
                store.replace(s, store.get(s)+1);
            }
            else{
                store.put(s,1);
            }
        }
        PrintWriter pr = new PrintWriter(new File("Frequency"));
        Iterator it = store.entrySet().iterator();
        pr.flush();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + ":" + pair.getValue());
            pr.println(pair.getKey() + ":" + pair.getValue());
            it.remove();
        }
        pr.close();

    }


    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the path of the file:");
        String read = scan.nextLine();
        ArrayList ar = readFile(read);
        for(Object o : ar){
            System.out.println(o);
        }
        countFreq(ar);
    }

}
