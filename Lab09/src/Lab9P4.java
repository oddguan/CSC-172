import java.util.PriorityQueue;

public class Lab9P4 {
	public static int Jesse_cookies(int[] cookies, int k){
		int total = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0;i<cookies.length;i++){
			pq.offer(cookies[i]);
		}
		while(pq.peek()<k){
			if(pq.size()<=1){
				return -1;//not possible case
			}
			else{
				int storage = pq.poll()+2*pq.poll();
				pq.offer(storage);
				total++;
			}
		}
		return total;
	}
}
