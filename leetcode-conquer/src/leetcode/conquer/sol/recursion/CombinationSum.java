package leetcode.conquer.sol.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * This solution is based on this link https://www.youtube.com/watch?v=HHUIH1b1-kA
 * the logic is similar to the subsets and combination questions. 
 * time o(n*2^n) worst case will involve finding all possible combinations
 * space o(2^n)
 */
public class CombinationSum {
	public CombinationSum() {}
	private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target == 0) return new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, target, 0, new ArrayList<>());
        return res;
    }
    
    private void helper(int[] nums, int target, int start, List<Integer> arr){
        if(target == 0){
            res.add(new ArrayList<>(arr));
            return;
        }
        //when target is smaller than nums[i], no point do further minus. Instead, 
        //we return to last call and remove the last index from the arr and increase the index and add the new index to the arr
        //we no longer pass i+1 to the helper since this question allows repeat index.
        for(int i=start;i<nums.length && target >= nums[i];i++){
            arr.add(nums[i]);
            helper(nums,target-nums[i],i,arr);
            arr.remove(arr.size()-1);
        }
    }
    
    public List<List<Integer>> combinationSumTwo(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new ArrayList<>();
        helperTwo(nums,target,new ArrayList<>(),0, 0);
        return res;
    }
    
    //the index param here prevents duplicate result, repeating char results can only happen once
    //eg, 2,2,3 but cant have 2,3,2, once 3 is reached recursion cannot go back to retrieve 2.
    private void helperTwo(int[] nums, int target, List<Integer> arr, int curr, int index){
        if(curr == target){
            res.add(new ArrayList<>(arr));
            return;
        }
        
        if(curr>target) return;
        
        for(int i=index;i<nums.length;i++){
            arr.add(nums[i]);
            helperTwo(nums,target,arr,curr+nums[i],i);
            arr.remove(arr.size()-1);
        }
    }
}
