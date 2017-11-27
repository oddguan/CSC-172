/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with n^2 running time. Reads n integers
 *  and counts the number of pairs that sum to exactly 0.
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 *
 *  % java TwoSum 32Kints.txt
 *  273
 *
 ******************************************************************************/

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Sorting {
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < (arr.length - 1); i++) {
            for (int j = 0; j < (arr.length - i - 1); j++) {
                if (arr[j] <= arr[j + 1]) {
                    continue;
                } else {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }


    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }


    public static int[] insersionSort(int[] arr) {
        int temp, j;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
        return arr;
    }


    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int[] left = new int[arr.length / 2];
        int[] right = new int[arr.length - arr.length / 2];
        System.arraycopy(arr, 0, left, 0, arr.length / 2);
        System.arraycopy(arr, arr.length / 2, right, 0, arr.length - arr.length / 2);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
        return arr;
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        int temp;
        int i = left;
        int j = right;
        int pivot = left + (right - left) / 2;
        while (i <= j) {
            while (arr[i] < arr[pivot]) {
                i++;
            }
            while (arr[j] > arr[pivot]) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (left < j) {
            quickSort(arr, left, j);
        }
        if (i < right) {
            quickSort(arr, i, right);
        }
        return arr;
    }

    public static int[] radixsort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        final int BYTE_MAX = 255;  // an 32-bit integer contains 4 bytes
        int[] countingArray = new int[BYTE_MAX + 1];
        int[] auxiliary = new int[array.length];

        for (int n = 1; n <= 4; n++) {
            for (int i = 0; i <= BYTE_MAX; i++) {
                countingArray[i] = 0;
            }
            for (int i = 0; i < array.length; i++) {
                // c[i] = |{key = i}|
                // c[array[i]] = c[array[i]] + 1;
                int nth = getNthByte(array[i], n);
                countingArray[nth] = countingArray[nth] + 1;
            }
            for (int i = 1; i <= BYTE_MAX; i++) {
                // c[i] = |{key <= i}|
                countingArray[i] += countingArray[i - 1];
            }
            // System.out.println("c : " + Arrays.toString(c));
            for (int i = array.length - 1; i >= 0; i--) {
                int nth = getNthByte(array[i], n);
                // b[c[array[i]] - 1] = array[i];
                // c[array[i]]--;
                auxiliary[countingArray[nth] - 1] = array[i];
                countingArray[nth]--;
            }

            array = auxiliary;
            auxiliary = new int[array.length];
            // Debug.logVerbose("sort, nth: " + n + ", " + Arrays.toString(array));
        }
        // Debug.logTest(Arrays.toString(array));
        return array;
    }

    static int getNthByte(int number, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        int n1 = number >>> ((n - 1) << 3) << ((n - 1) << 3);
        int n0 = number >>> (n << 3) << (n << 3);
        if(n == 4) n0 = 0;
        return (n1 - n0) >>> (n - 1) * 8;
    }

    /**
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort
     * 3 = Insertion Sort
     * 4 = Mergesort
     * 5 = Quicksort
     * 6 = Radix Sort
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] methods = {"Java Default sort", "Bubble sort", "Selection Sort", "Insersion Sort", "Merge Sort", "Quick Sort", "Radix Sort"};
        In in = new In(args[0]);
        int in2 = Integer.parseInt(args[1]);

        // Storing file input in an array
        int[] a = in.readAllInts();
        Arrays.sort(a);
        int[] b = a;
        int length = b.length;
        int[] c = new int[length];
        for (int i = 0; i < length / 2; i++) {
            c[i] = b[length - i - 1];
            c[length - i - 1] = b[i];
        }
        int[] d = a;
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            int ran1 = rand.nextInt(10);
            int ran2 = rand.nextInt(10);
            int temp = d[ran1];
            d[ran1] = d[ran2];
            d[ran2] = temp;
        }
        int[][] arra = {a, b, c, d};
        String[] arrayName = {"a", "b", "c", "d"};
        PrintWriter writer = null;

        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        // c contains all integers n in reverse order
        // (you can have your own O(n) solution to get c from b
        // d contains almost sorted array
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.

        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  * 6 = Radix Sort

        //  Perform sorting on a,b,c,d. Print runtime for each case along with timestamp and record those.
        // For runtime and priting, you can use the same code from Lab 4 as follows:
        // For each array, a, b, c, d:
        int counter = 0;
        for (int[] o : arra) {
            Stopwatch timer = new Stopwatch();
            switch (in2) {
                case 0:
                    Arrays.sort(o);
                    break;
                case 1:
                    bubbleSort(o);
                    break;
                case 2:
                    selectionSort(o);
                    break;
                case 3:
                    insersionSort(o);
                    break;
                case 4:
                    mergeSort(o);
                    break;
                case 5:
                    quickSort(o, 0, o.length-1);
                    break;
                case 6:
                    radixsort(o);
                    break;
            }
            double time = timer.elapsedTimeMillis();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            //TODO: Replace with your own netid
            String netID = "cguan3";
            //TODO: Replace with the algorithm used 
            String algorithmUsed = methods[in2];
            //TODO: Replace with the  array used
            String arrayUsed = " ";
            if (counter == 0) {
                arrayUsed = "a";
                try{
                    File file = new File("a");
                    writer = new PrintWriter(file);
                    for(int i=0;i<a.length;i++){
                        writer.println(a[i]);
                    }
                    writer.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if (counter == 1) {
                arrayUsed = "b";
                try{
                    File file = new File("b");
                    writer = new PrintWriter(file);
                    for(int i=0;i<b.length;i++){
                        writer.println(b[i]);
                    }
                    writer.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if (counter == 2) {
                arrayUsed = "c";
                try{
                    File file = new File("c");
                    writer = new PrintWriter(file);
                    for(int i=0;i<c.length;i++){
                        writer.println(c[i]);
                    }
                    writer.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if (counter == 3) {
                arrayUsed = "d";
                try{
                    File file = new File("d");
                    writer = new PrintWriter(file);
                    for(int i=0;i<d.length;i++){
                        writer.println(d[i]);
                    }
                    writer.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
            counter++;
        }


        // TODO: Perform Sorting and store the result in an  array


    }
} 

