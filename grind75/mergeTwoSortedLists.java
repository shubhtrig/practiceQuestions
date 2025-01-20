/**
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode head = list1;
        if (list1.val > list2.val) {
            head = list2;
            list2 = list2.next;
        } else {
            list1 = list1.next;
        }

        ListNode ans = head;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                ListNode temp = list2.next;
                head.next = list2;
                list2 = temp;
            } else {
                ListNode temp = list1.next;
                head.next = list1;
                list1 = temp;
            }
            head = head.next;
        }

        if (list1 == null) {
            head.next = list2;
        } else {
            head.next = list1;
        }

        return ans;
    }
}