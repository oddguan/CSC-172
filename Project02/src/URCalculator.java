import java.util.*;

public class URCalculator {
    private static Map<String, String> variables = new HashMap<>(); //A hashmap that stores values of variables
    private static boolean empty = false; //a boolean that indicates whether the evaluate stack is empty
    private static String[] split(String input){ //a method that splits the string in a desired way
        //split the string based on the operators
        String[] s = input.split("((?<=[\\+|\\-|\\*|\\/|\\(|\\)\\[\\]\\{\\}])|(?=[\\+|\\-|\\*|\\/|\\(|\\)\\[\\]\\{\\}]))");
        ArrayList<String> sal = new ArrayList<String>(Arrays.asList(s)); //convert the array to an arraylist
        for(int i=0;i<sal.size();i++){ //for each element in the array
            if(sal.get(i).chars().allMatch(Character::isDigit) || variables.containsKey(sal.get(i))){ //if the read is a number
                if(i>=1 && sal.get(i-1).equals("+")){ //if the element before the number is a "+"
                    if(i==1){ //if the number is the second element
                        sal.remove(i-1); //remove the plus sign before it because it has no usage
                        i=-1; //start at the beginning again
                    }
                    else if(i==2 && sal.get(i-2).equals("+")){ //if two elements before the number are all "+"
                        sal.remove(i-1); //remove both of the "+"
                        sal.remove(i-2);
                        i = i - 3; //start at the number again
                    }
                    //if two elements before the number are all "+" and the third element before the number is not a number
                    else if(i>2 && sal.get(i-2).equals("+") && (!sal.get(i-3).chars().allMatch(Character::isDigit) || !variables.containsKey(sal.get(i-3)))){
                        sal.remove(i-1); //remove both of the "+"
                        sal.remove(i-2);
                        sal.add(i-2,"+"); //add another plus
                        i = i - 2;//start at the number again
                    }
                    //if two elements before the number are all "+" and the third element before the number is a number
                    else if(i>2 && sal.get(i-2).equals("+") && (sal.get(i-3).chars().allMatch(Character::isDigit) || variables.containsKey(sal.get(i-3)))){
                        sal.remove(i-1); //only remove 1 "+"
                        i = i - 2; //start again
                    }
                    else if(sal.get(i-2).equals("-")){ //check the invalid expression
                        System.out.println("This is not a valid expression (can't put minus sign before a plus sign)");
                        break;
                    }

                }
                else if(i>=1 && sal.get(i-1).equals("-")){ //if the element before the number is a "-"
                    if(i==1){//if the number is the second element
                        sal.add(0,"0"); //add a zero before the minus sign
                        i=i+1; //start at the next element
                    }
                    //if two elements before the number is a parentheses, remove the minus sign and multiply the element by -1
                    else if(sal.get(i-2).equals("(") || sal.get(i-2).equals("[") || sal.get(i-2).equals("{")){
                        sal.set(i, Double.toString((Double.parseDouble(sal.get(i))*(-1))));
                        sal.remove(i-1);
                        i=i-2;
                    }
                    else if(sal.get(i-2).equals("+")){ //if two elements before the number is a plus sign
                        sal.remove(i-2); //remove the plus sign
                        i = i - 2;
                    }
                    else if(sal.get(i-2).equals("-")){ //if two elements before the number is a minus sign
                        sal.remove(i-1); //remove both of the numbers
                        sal.remove(i-2);
                        sal.add(i-2,"+"); //add a plus sign before the element
                        i=i-3;
                    }
                }
            }
        }
        return sal.toArray(new String[sal.size()]); //return the revised version of the string into an array
    }

    private static String evaluateExpression(String input){ //a method that evaluate the expression
        Stack<String> operators = new Stack<String>(); //initialize a empty stack that stores operators
        ArrayList sorted = new ArrayList(); //an arraylist that stores the expression after performing the shunting yard algorithm
        Stack evaluate = new Stack(); //a stack to evaluate the expression
        boolean ope = true; //a boolean to check whether all of the operators are supported in the expression
        empty = false;
        for(String w : split(input)){ //for every element in the input
            if(w.chars().allMatch(Character::isDigit)){ //if the element is a number
                sorted.add(Double.valueOf(w)); //put it into the output arraylist
            }

            else if(w.contains(".")){ //if the element contains a dot, it is a number
                sorted.add(Double.valueOf(w));//put it into the output arraylist
            }

            else if(variables.containsKey(w)){ //if the element is a name of the variable that has a given value before
                try{
                    sorted.add(Double.valueOf(variables.get(w))); //put the value of the variable into the output arraylist
                }
                catch(NullPointerException npe){ //catch the null pointer exception if the value of the variable is null
                    System.out.println("variable has a value of NULL (NullPointerException)");
                }
            }

            else if(operators.isEmpty()){ //else means the element is not a number. if the operator stack is empty, push the operator onto the stack
                operators.push(w);
            }
            //else if the element is one of the supported given operators
            else if(w.equals("(") || w.equals(")") || w.equals("+") || w.equals("-") || w.equals("*") || w.equals("/") || w.equals("]") || w.equals("}") || w.equals("[") || w.equals("{")){
                if(w.equals("(") || w.equals("[") || w.equals("{")){ //if the operator is one of the left parentheses
                    operators.push(w); //push it directly to the stack
                }
                else if(w.equals("*") || w.equals("/")){ //if the operator is either * or /
                    if(operators.peek().equals("(") || operators.peek().equals("[") || operators.peek().equals("{")){ //if the element before is the left parentheses
                        operators.push(w); //push it directly to the stack
                    }
                    else if(operators.peek().equals("+")||operators.peek().equals("-")){ //if the element before is + or -
                        operators.push(w); //push it directly to the stack
                    }
                    else if(operators.peek().equals("*")||operators.peek().equals("/")){ //if the element before is * or /
                        sorted.add(operators.peek()); //add the element before to the output arraylist
                        operators.pop(); //pop the operator before
                        operators.push(w); //push the element onto the stack
                    }
                }
                else if(w.equals("+") || w.equals("-") ){ //if the operator is either + or -
                    if(operators.peek().equals("*") || operators.peek().equals("/")){ //if the element before is * or /
                        sorted.add(operators.peek()); //add the element before to the output arraylist
                        operators.pop(); //pop the operator before
                        sorted.add(w); //push the element onto the stack
                    }
                    else if(operators.peek().equals("+")||operators.peek().equals("-")){ //if the element before is either + or -
                        sorted.add(operators.peek()); //do the same thing as above
                        operators.pop();
                        operators.add(w);
                    }
                    else if(operators.peek().equals("(") || operators.peek().equals("[") || operators.peek().equals("{")){ //if the element before is one of the left parentheses
                        operators.push(w); //push the element directly onto the stack
                    }

                }
                else if(w.equals(")")){ //if the element is the small right parentheses
                    while(!operators.peek().equals("(")){//while the top element on the operator stack is not the left corresponding parentheses
                        if(operators.peek().equals("{") || operators.peek().equals("[")){ //if the top element is not the corresponding parentheses
                            //the order of the parentheses in the expression is wrong
                            System.out.println("Invalid expression (parentheses out of order ) )");
                            ope=false;
                            break; //berak the loop because the expression is wrong
                        }
                        sorted.add(operators.peek()); //add the top element on the operator stack into the output arraylist
                        operators.pop(); //pop the operator
                    }
                    operators.pop(); //pop the last operator
                }
                else if(w.equals("]")){ //same as above
                    while(!operators.peek().equals("[")){
                        if(operators.peek().equals("{") || operators.peek().equals("(")){
                            System.out.println("Invalid expression (parentheses out of order ] )");
                            ope=false;
                            break;
                        }
                        sorted.add(operators.peek());
                        operators.pop();
                    }
                    operators.pop();
                }
                else if(w.equals("}")){ //same as above
                    while(!operators.peek().equals("{")){
                        if(operators.peek().equals("(") || operators.peek().equals("[")){
                            System.out.println("Invalid expression (parentheses out of order } )");
                            ope=false;
                            break;
                        }
                        sorted.add(operators.peek());
                        operators.pop();
                    }
                    operators.pop();
                }
            }

            else{ //if the element is not one of the supported operators
                System.out.println("The operator is not supported");
                ope = false;
                break; //break the shunting yard loop
            }
        }

        while(!operators.isEmpty() && ope){ //while the operator stack is not empty
            if(!(operators.peek().equals("(") || operators.peek().equals(")") || operators.peek().equals("[") || operators.peek().equals("]") || operators.peek().equals("{") || operators.peek().equals("}"))){
                sorted.add(operators.peek()); //pop everything off except the parentheses and add them into the sorted list in the correct order
            }
            operators.pop();
        }
        for(int i=0;i<sorted.size();i++){
            if(sorted.get(i) instanceof Double){ //if the element in the sorted arraylist is a number
                evaluate.push(sorted.get(i)); //push it onto the evaluate stack
            }
            else{ //if the element is the operator
                try{
                    if(sorted.get(i).equals("+")){ //if the operator is +
                        Object first = evaluate.peek(); //sum the top two elements on the evaluate stack
                        evaluate.pop();//delete the old element
                        Object second = evaluate.peek();
                        evaluate.pop();
                        evaluate.push((double)first + (double)second); //push the sum onto the stack
                    }
                    else if(sorted.get(i).equals("-")){ //if the operator is -
                        Object first = evaluate.peek(); //calculate the difference
                        evaluate.pop(); //delete the old element
                        Object second = evaluate.peek();
                        evaluate.pop();
                        evaluate.push((double)second - (double)first); //push the difference onto the stack
                    }
                    else if(sorted.get(i).equals("*")){ //if the operator is *
                        Object first = evaluate.peek();//calculate the multiplication
                        evaluate.pop(); //delete the old element
                        Object second = evaluate.peek();
                        evaluate.pop();
                        evaluate.push((double)first * (double)second); //push the multiplication onto the stack
                    }
                    else if(sorted.get(i).equals("/")){ //if the operator is /
                        Object first = evaluate.peek(); //calculate the division
                        evaluate.pop(); //delete the old element
                        Object second = evaluate.peek();
                        evaluate.pop();
                        evaluate.push((double)second / (double)first); //push the division onto the stack
                    }
                }

                catch(EmptyStackException ese){ //catch any error that causes the stack to be empty
                    empty=true;
                }
            }
        }
        try{
            if(ope){ //if ope is still true, that means every operator is supported
                return evaluate.peek() + ""; //return the result of the expression
            }
            else{
                return null; //return null if evaluation cannot be performed
            }

        }
        catch(EmptyStackException ese){ //catch any error that causes the stack to be empty
            empty = true;
        }
        if(empty){ //if the stack is empty
            System.out.println("The expression is not well formed (Empty Stack)");
        }
        return null; //return null if unable to evaluate
    }

    private static void storeVariable(String input){ //a method that stores the value of the variable
        String right = "";
        ArrayList<Integer> p = new ArrayList<>();
        if(input.equals("clearall")){ //if the user wants to clear all the memories of the variables
            variables = new HashMap<>(); //re-initialize the variable hashmap
            System.out.println("All variables cleared.");
        }
        else if(input.contains("clear")){//if not clear all and still contains clear
            String[] s = input.split("r",2); //read the substring after "clear"
            if(variables.containsKey(s[1])){ //if there is a variable that the user wants to clear
                variables.remove(s[1]); //remove the key
                System.out.println(s[1] + " has been removed");
            }
            else{
                System.out.println("There is no value that has been stored for this variable");
            }
        }
        else{ //read all of the equal signs in the input, store the index
            for(int i=0;i<input.length();i++){
                if(input.charAt(i) == '='){
                    p.add(i);
                }
            }
            if(p.get(p.size()-1)>0){ //stores the value in a string
                right = input.substring(p.get(p.size()-1)+1);
            }

            int post = 0;
            for(int i=0;i<p.size();i++){ //if the user is replacing a exsisting variable
                if(variables.containsKey(input.substring(post,p.get(i))) && evaluateExpression(right)!=null){
                    //output the variable name and the value that has been stored for this variable
                    System.out.print(input.substring(post,p.get(i))+" = ");
                    System.out.println(evaluateExpression(right)+" stored");
                    variables.replace(input.substring(post,p.get(i)), evaluateExpression(right));
                }
                else if(evaluateExpression(right)!=null){ //if the expression in the right is not null
                    try{ //try to store the value into the hashmap
                        variables.put(input.substring(post,p.get(i)), evaluateExpression(right));
                        System.out.print(input.substring(post,p.get(i))+" = ");
                        System.out.println(variables.get(input.substring(post,p.get(i)))+" stored");
                        post = p.get(i)+1;
                    }
                    catch(NumberFormatException nfe){ //catch the null pointer exception if the user is trying to store the value of the variable to be null
                        System.out.println("THe equation is not well formed (NumberFormatException: Empty String)");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        boolean goOn = true; //a boolean to control the program
        System.out.println("Welcome to use my calculator.");
        while(goOn){
            System.out.println("Enter the equation, store the value of a specific variable or clear the storage of variables below:");
            Scanner scan = new Scanner(System.in); //scan the input from the user
            String input = scan.nextLine();
            if(input.equals("exit")){ //if the user wants to exit the program
                System.out.println("Thanks for using!");
                goOn=false;
                System.exit(0); //exit
            }
            if(input.contains("=") || input.contains("clear")){ //if the input contains equal sign or contains clear
                storeVariable(input); //perform the storevariable method
            }
            else{
                if(evaluateExpression(input)!=null){ //if evaluating the expression does not return null
                    System.out.print(">>>");
                    System.out.println(evaluateExpression(input)); //print out the result
                }
            }
        }
    }
}
