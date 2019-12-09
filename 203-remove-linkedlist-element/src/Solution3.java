import java.util.List;
import java.util.LinkedList;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head = null)   // 问题规模最小的解是什么
            return head;

        head.next = removeElements(head.next, val);   // 递归删除
//        if (head.val == val)
//            return res;
//        else{
//            head.next = res;
//            return head;
//        }
        return head.val == val ? head.next :head;
    }

    public static void  main(String[] args){
        int[] nums = {1,2,4,5,60};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head,6);
        System.out.println(head);
    }
}

/*
对链表增删改查进行递归进行
 */
