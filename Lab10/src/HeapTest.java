public class HeapTest {
    public static void main(String[] args) {
        int[] arr = {5,18,3,25,27, 45, 97, 88, 26, 16, 49, 67};
        Heap heap = new Heap();
        heap.heapify(arr);
        for(Integer i : arr){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        int[] arr2 = {15, 99, 3, 77, 27, 45, 7, 88, 26, 5};
        heap.heapsort(arr2);
        for(Integer i : arr2){
            System.out.print(i + " ");
        }
    }
}
