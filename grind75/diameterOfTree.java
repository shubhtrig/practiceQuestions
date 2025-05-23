/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameter(root);
        
        return max;
    }

    private int diameter(TreeNode root) {
        if (root == null)
            return 0;
        int left =  diameter(root.left);
        int right = diameter(root.right);
        int curr = left + right;
        if (curr > max)
            max = curr;
        return Math.max(left, right) + 1;
    }
}