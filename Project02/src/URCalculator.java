import java.util.*;

public class URCalculator {
    private static Map<String, String> variables = new HashMap<>();

    private static String[] split(String input){
        return input.split("((?<=[\\+|\\-|\\*|\\/|\\(|\\)])|(?=[\\+|\\-|\\*|\\/|\\(|\\)]))");
    }

    private static String evaluateExpression(String input){
        Stack<String> operators = new Stack<String>();
        ArrayList sorted = new ArrayList();
        Stack evaluate = new Stack();
        boolean ope = true;
        for(String w : split(input)){

            if(w.chars().allMatch(Character::isDigit)){
                sorted.add(Double.valueOf(w));
            }

            else if(w.contains(".")){
                sorted.add(Double.valueOf(w));
            }

            else if(variables.containsKey(w)){
                sorted.add(Double.valueOf(variables.get(w)));
            }

            else if(operators.isEmpty()){
                operators.push(w);
            }

            else if(w.equals("(") || w.equals(")") || w.equals("+") || w.equals("-") || w.equals("*") || w.equals("/")){
                if(w.equals("(")){
                    operators.push(w);
                }
                else if(w.equals("*") || w.equals("/")){
                    if(operators.peek().equals("(")){
                        operators.push(w);
                    }
                    else if(operators.peek().equals("+")||operators.peek().equals("-")){
                        operators.push(w);
                    }
                    else if(operators.peek().equals("*")||operators.peek().equals("/")){
                        sorted.add(operators.peek());
                        operators.pop();
                        operators.push(w);
                    }
                }
                else if(w.equals("+") || w.equals("-") ){
                    if(operators.peek().equals("*") || operators.peek().equals("/")){
                        sorted.add(operators.peek());
                        operators.pop();
                        sorted.add(w);
                    }
                    else if(operators.peek().equals("+")||operators.peek().equals("-")){
                        sorted.add(operators.peek());
                        operators.pop();
                        operators.add(w);
                    }
                    else if(operators.peek().equals("(")){
                        operators.push(w);
                    }

                }
                else if(w.equals(")")){
                    while(!operators.peek().equals("(")){
                        sorted.add(operators.peek());
                        operators.pop();
                    }
                    operators.pop();
                }
            }

            else{
                System.out.println("The operator is not supported");
                ope = false;
                break;
            }
        }

        while(!operators.isEmpty() && ope){
            if(!(operators.peek().equals("(") || operators.peek().equals(")"))){
                sorted.add(operators.peek());
            }

            operators.pop();
        }
        for(int i=0;i<sorted.size();i++){
            if(sorted.get(i) instanceof Double){
                evaluate.push(sorted.get(i));
            }
            else{
                if(sorted.get(i).equals("+")){
                    Object first = evaluate.peek();
                    evaluate.pop();
                    Object second = evaluate.peek();
                    evaluate.pop();
                    evaluate.push((double)first + (double)second);
                }
                else if(sorted.get(i).equals("-")){
                    Object first = evaluate.peek();
                    evaluate.pop();
                    Object second = evaluate.peek();
                    evaluate.pop();
                    evaluate.push((double)second - (double)first);
                }
                else if(sorted.get(i).equals("*")){
                    Object first = evaluate.peek();
                    evaluate.pop();
                    Object second = evaluate.peek();
                    evaluate.pop();
                    evaluate.push((double)first * (double)second);
                }
                else if(sorted.get(i).equals("/")){
                    Object first = evaluate.peek();
                    evaluate.pop();
                    Object second = evaluate.peek();
                    evaluate.pop();
                    evaluate.push((double)second / (double)first);
                }
            }
        }
        try{
            return evaluate.peek() + "";
        }
        catch(EmptyStackException ese){
            System.out.println("The expression is not well formed");
        }
        return null;
    }

    private static void storeVariable(String input){
        String left = "";
        String right = "";
        ArrayList<Integer> p = new ArrayList<>();
        if(input.equals("clearall")){
            variables = new HashMap<>();
            System.out.println("All variables cleared.");
        }
        else if(input.contains("clear")){
        }
        else{
            for(int i=0;i<input.length();i++){
                if(input.charAt(i) == '='){
                    p.add(i);
                }
            }
            if(p.get(p.size()-1)>0){
                left = input.substring(0, p.get(p.size()-1));
                right = input.substring(p.get(p.size()-1)+1);
                evaluateExpression(right);
            }

            int post = 0;
            for(int i=0;i<p.size();i++){
                if(variables.containsKey(input.substring(post,p.get(i)))){
                    System.out.print(input.substring(post,p.get(i))+" ");
                    System.out.println(evaluateExpression(right));
                    variables.replace(input.substring(post,p.get(i)), evaluateExpression(right));
                }
                else{
                    variables.put(input.substring(post,p.get(i)), evaluateExpression(right));
                    System.out.print(input.substring(post,p.get(i))+" ");
                    System.out.println(variables.get(input.substring(post,p.get(i))));
                    post = p.get(i)+1;
                }
            }
        }
    }

    public static void main(String[] args) {
        boolean goOn = true;
        System.out.println("Welcome to use my calculator.");
        while(goOn){
            System.out.println("Enter the equation, store the value of a specific variable or clear the storage of variables below:");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            if(input.equals("exit")){
                System.out.println("Thanks for using!");
                goOn=false;
                System.exit(0);
            }
            if(input.contains("=") || input.contains("clear")){
                storeVariable(input);
            }

            else{
                if(evaluateExpression(input)!=null){
                    System.out.println(evaluateExpression(input));
                }
            }
        }
    }

}
