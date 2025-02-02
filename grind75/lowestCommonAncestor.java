/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();

        storePath(root, p, pathP);
        storePath(root, q, pathQ);

        List<TreeNode> finalPath = pathQ.size() < pathP.size() ? pathQ : pathP;
        for (int i=0; i<finalPath.size(); i++) {
            if (pathQ.get(i) != pathP.get(i)) {
                return pathQ.get(i-1);
            }
        }

        return finalPath.get(finalPath.size() - 1);
    }

    private boolean storePath(TreeNode root, TreeNode p, List<TreeNode> path) {
        if (root == null)
            return false;
       
        path.add(root);
        if (root.val == p.val) {
            return true;
        }

        if (storePath(root.left, p, path)) {
            return true;
        }

        if (storePath(root.right, p, path)) {
            return true;
        }

        path.removeLast();
        return false;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }
}