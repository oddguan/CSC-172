// Import any package as required
import java.io.*;
import java.util.*;

public class HuffmanSubmit implements Huffman {
  
	// Feel free to add more methods and variables as required.

    static class Node{
        Character value = null;
        Integer freq = null;
        Node left = null;
        Node right = null;
        public Node(char value, int freq){
            this.value = value;
            this.freq = freq;
        }
        public Node(Character value, Integer freq, Node left, Node right){
            this.value = value;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

	public void encode(String inputFile, String outputFile, String freqFile) throws Exception {
		// TODO: Your code here
        BinaryIn in = new BinaryIn(inputFile);
        ArrayList<Character> ar = new ArrayList<Character>();
        while(!in.isEmpty()){
            char temp = in.readChar();
            ar.add(temp);
        }
        HashMap<Character, Integer> store = new HashMap<Character, Integer>();
        for(Character c : ar){
            if(store.containsKey(c)){
                store.replace(c, store.get(c)+1);
            }
            else{
                store.put(c,1);
            }
        }
        HashMap<Character, String> charToBin = new HashMap<Character, String>();
        Node root = createBinaryTree(createPriorityQueue(store));
        printTree(root);
        printFrequencyFile(store, freqFile);
        changeCharToBinaryRepresentation(root, charToBin, "");
        printEncFile(outputFile, ar, charToBin);
   }

   public void decode(String inputFile, String outputFile, String freqFile) throws Exception {
		// TODO: Your code here
       BinaryIn in = new BinaryIn(inputFile);
       BinaryOut out = new BinaryOut(outputFile);
       String s;
       HashMap<Character, Integer> store = new HashMap<Character,Integer>();
       Scanner scan = null;
       try{
           scan = new Scanner(new File(freqFile));

       }
       catch (FileNotFoundException e){
           e.printStackTrace();
       }
       while(scan.hasNext()){
           s = scan.nextLine();
           String[] temp = s.split(":");
           char c = (char) Integer.parseInt(temp[0],2);
           int d = Integer.parseInt(temp[1]);
           store.put(c, d);
       }
       scan.close();
       Node root = createBinaryTree(createPriorityQueue(store));
       printTree(root);
       Node position = root;
       while(!in.isEmpty()){
           boolean boo = in.readBoolean();
           if(position.value!=null){
               out.write(position.value);
               out.flush();
               position=root;
           }

           if(boo){
               position=position.right;
           }
           else{
               position=position.left;
           }
       }
       out.flush();
   }


    private static  PriorityQueue<Node> createPriorityQueue(Map<Character, Integer> map) {
       PriorityQueue<Node> prio = new PriorityQueue<Node>(2, new Comparator<Node>() {
           @Override
           public int compare(Node n1, Node n2) {
               return (n1.freq.compareTo(n2.freq));
           }
       });
       for(Map.Entry<Character, Integer> entry : map.entrySet()){
           Node temp = new Node(entry.getKey(), entry.getValue());
           prio.add(temp);
       }
       return prio;
    }

    private static Node createBinaryTree(PriorityQueue<Node> pq){
       while(pq.size()>=2){
           Node temp1 = pq.poll();
           Node temp2 = pq.poll();
           Node temp3 = new Node(null, (temp1.freq+temp2.freq), temp1, temp2);
           pq.add(temp3);
       }
       return pq.poll();
    }

    private static void printTree(Node root){
        if(root.value!=null){
            System.out.println(root.value + ":" + root.freq);
        }
        if(root.left!=null){
            printTree(root.left);
        }
        if(root.right!=null){
            printTree(root.right);
        }
    }

    private static void changeCharToBinaryRepresentation(Node root, HashMap<Character, String> charToBin, String bi){
        if(root.value!=null){
            charToBin.put(root.value, bi);
        }
        else{
            changeCharToBinaryRepresentation(root.left, charToBin, bi+"0");
            changeCharToBinaryRepresentation(root.right, charToBin,bi+"1");
        }
    }

    private static void printFrequencyFile(HashMap<Character, Integer> map, String freqFile) throws Exception{
        BufferedWriter pr = new BufferedWriter(new FileWriter(freqFile));
        Iterator it = map.entrySet().iterator();
        pr.flush();
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            String toBeAdded = "";
            if(Integer.toBinaryString(entry.getKey()).length()<8){
                toBeAdded = Integer.toBinaryString(entry.getKey());
                while(toBeAdded.length()<8){
                    toBeAdded = "0" + toBeAdded;
                }
            }
            else{
                toBeAdded = Integer.toBinaryString(entry.getKey());
            }
            pr.write(toBeAdded + ":" + entry.getValue());
            pr.newLine();
        }
        pr.close();
    }

    private static void printCharToBin(HashMap<Character, String> map){

        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void printEncFile(String fileName, ArrayList<Character> ar, HashMap<Character,String> map) throws Exception{
        BinaryOut out = new BinaryOut(fileName);
        for(Character c : ar){
            String val = map.get(c);
            String[] va = val.split("");
            for(String s : va){
                if(s.equals("0")){
                    out.write(false);
                }
                if(s.equals("1")){
                    out.write(true);
                }
            }
        }
        out.flush();
    }

   public static void main(String[] args) throws Exception{
      Huffman  huffman = new HuffmanSubmit();
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
		// After decoding, both ur.jpg and ur_dec.jpg should be the same. 
		// On linux and mac, you can use `diff' command to check if they are the same.
   }



}
