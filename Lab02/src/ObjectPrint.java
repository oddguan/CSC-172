import java.util.function.Function;

public class ObjectPrint {
	
	/*
	public static void printArray(Object[] list){
		for(Object o : list){
			System.out.println(o);
		}
	}
	*/
	
	
	/*
	public static void printArray(Integer[] list){
		for(int object : list){
			System.out.println(object);
		}
	}
	
	public static void printArray(String[] list){
		for(String object : list){
			System.out.println(object);
		}
	}
	
	public static void printArray(Double[] list){
		for(double object : list){
			System.out.println(object);
		}
	}
	
	public static void printArray(Character[] list){
		for(char object : list){
			System.out.println(object);
		}
		
	}
	*/
	
	
	public static <T> void printArray(T[] list){
		for(T object : list){
			System.out.println(object);
		}
	}
	
	/* Compiler Warnings:
	 * Comparable is a raw type. References to generic type Comparable<T> should be parameterized
	 * Type safety: The method compareTo(Object) belongs to the raw type Comparable. References to generic type Comparable<T> should be parameterized
	 *
	public static Comparable getMax(Comparable [] anArray){
		Comparable Max;
		Max = anArray[0];
		for(int i=1;i<anArray.length;i++){
			if(Max.compareTo(anArray[i])>0){
				continue;
			}
			else{
				Max = anArray[i];
			}
		}
		return Max;
	}
	*/
	
	
	public static <T extends Comparable <T>> T getMax(T[] anArray){
		T Max;
		Max = anArray[0];
		for(int i=1;i<anArray.length;i++){
			if(Max.compareTo(anArray[i])>0){
				continue;
			}
			else{
				Max = anArray[i];
			}
		}
		return Max;
	}
	
	
	
	
	public static void main(String[] args){
		Function <Character [],Character> findMax = (Character [] array) -> {
			char Max;
			Max = array[0];
			for(int i=1;i<array.length;i++){
				if(Max>array[i]){
					continue;
				}
				else{
					Max = array[i];
				}
			}
			return Max;
		};
		
		Integer [] intArry = {1, 2, 3, 4, 5 };
		Double [] doubArry = {1.1, 2.2, 3.3, 4.4};
		Character [] charArray = {'H','E','L', 'L', 'O' };
		String [] strArray = {"once", "upon", "a", "time" };
		printArray(intArry);
		printArray(doubArry);
		printArray(charArray);
		printArray(strArray);
		System.out.println("max Integer is: " + getMax(intArry));
		System.out.println("max Double is: " + getMax(doubArry));
		System.out.println("max Character is: " + getMax(charArray));
		System.out.println("max String is: " + getMax(strArray));
		System.out.println("Max Character is: " + findMax.apply(charArray));
	}
}
