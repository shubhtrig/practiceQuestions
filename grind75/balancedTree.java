/**
 * https://leetcode.com/problems/balanced-binary-tree/
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
    public boolean isBalanced(TreeNode root) {
        return height(root) != Integer.MAX_VALUE;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;

        if (Math.abs(left - right) > 1)
            return Integer.MAX_VALUE;

        return Math.max(left, right) + 1;
    }
}