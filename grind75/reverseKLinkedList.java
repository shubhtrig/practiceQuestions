/**
 *  https://leetcode.com/problems/reverse-nodes-in-k-group/
 *  Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode uniHead = null;
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tempHead = head;
        ListNode tempTail = null;
        int count = 1;
        while (head != null) {
            if (count == k) {
                count = 1;
                ListNode headNext = head.next;
                head.next = null;
                
                ListNode tail = reverseLinkedList(tempHead);
                if (tempTail != null)
                    tempTail.next = head;
                
                tail.next = headNext;
                tempTail = tempHead;
                tempHead = headNext;
                head = headNext;
            } else {
                count++;
                head = head.next;
            }

        }
        
        return uniHead;
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head.next == null) {
            if (uniHead == null)
                uniHead = head;
            return head;
        }

        ListNode l = reverseLinkedList(head.next);
        l.next = head;
        head.next = null;
        return head;
    }
}