public class Heap {

    public void heapify(int[] arr){
        int end = arr.length - 1;
        int start = (end-1)/2;
        while(start>=0){
            heapDown(arr, start, end);
            start--;
        }
    }

    private void heapDown(int[] arr, int start, int end){
        int root = start;
        while((2*root+1)<=end){
            int child = 2*root+1;
            if((child+1)<=end && arr[child]<arr[child+1]){
                child=child+1;
            }
            if(arr[root]<arr[child]){
                int temp = arr[child];
                arr[child] = arr[root];
                arr[root] = temp;
                root = child;
            }
            else{
                return;
            }
        }
    }

    public void heapsort(int[] arr){
        int n = arr.length-1;
        while(n>0){
            int start = (n-1)/2;
            while(start>=0){
                heapDown(arr,start,n-1);
                start--;
            }
            int temp = arr[0];
            arr[0] = arr[n];
            arr[n] = temp;
            n--;
        }
    }
}
