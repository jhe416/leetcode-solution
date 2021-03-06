package leetcode.conquer.sol.queue.priority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * The idea of this solution is build a PriorityQueue on the given arr, 
 * The priority queue will contain a map entry with the given arr number and count
 * during building priority queue if the size is greater than k then we remove one from the queue
 * the removed one is guarantee to have a less frequent number than any other ones in the queue. due to the sort property set to the queue
 * (a,b) -> (a.getValue() - b.getValue()) from small to large
 * when we remove always the smallest one gets moved out.
 * each add on priority queue is log(n) because of the sort. 
 * so the Time complexity in this will be nlog(k) since we only keep k records in the queue
 * Space O(n)
 */
public class TopKFrequentElements {
	public TopKFrequentElements() {}

	public List<Integer> topKFrequent(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k ==  0) return new ArrayList<>();

		Map<Integer,Integer> map = new HashMap<>();

		for(int i : nums){
			int count = map.getOrDefault(i,0);
			map.put(i,count);
		}

		PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>((a,b) -> (a.getValue() - b.getValue()));

		for(Map.Entry<Integer,Integer> e : map.entrySet()){
			queue.offer(e);
			if(queue.size() > k){
				queue.poll();
			}
		}

		List<Integer> res = new LinkedList<>();

		while(!queue.isEmpty()) res.add(0,queue.poll().getKey());

		return res;
	}
	
    public int[] topKFrequentNew(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k == 0) return new int[0];
        Arrays.sort(nums);
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)-> (a[0]-b[0]));
        
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(i== nums.length-1 || nums[i] != nums[i+1]){
                q.offer(new int[]{i-j+1,nums[i]});
                j = i+1;
            }
            
            if(q.size() > k) q.poll();
        }
        
        int[] res = new int[k];
        while(k>0){
            res[k-1] = q.poll()[1];
            k--;
        }
        
        return res;
    }
}
