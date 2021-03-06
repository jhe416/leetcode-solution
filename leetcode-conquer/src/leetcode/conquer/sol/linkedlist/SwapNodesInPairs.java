package leetcode.conquer.sol.linkedlist;

import leetcode.conquer.list.ListNode;

/*
 * a Time O(n), space O(n) solution on swapping adjacent nodes.
 * loop through by pair if possible and build a new List Node with a dummy
 * if we cant loop through by pair meaning when have reached to the end with only one left 
 * then we .next on the dummy with the current.
 */
public class SwapNodesInPairs {
	public  SwapNodesInPairs() {}
	
    public ListNode swapPairsMySol(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while(head != null && head.next != null){            
            prev.next = head.next;
            head.next = prev.next.next;
            prev.next.next = head;
            
            prev = head;
            head = head.next;
        }
        
        return dummy.next;
    }
    
	public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        ListNode cur = head;
        
        while(cur!= null){
            if(cur.next != null){
                dummy.next = new ListNode(cur.next.val);
                dummy.next.next = new ListNode(cur.val);
                dummy = dummy.next.next;
                cur = cur.next.next;
            }else{
                dummy.next = new ListNode(cur.val);
                dummy = dummy.next;
                cur = cur.next;
            }
        }
        
        return res.next;
    }
}
