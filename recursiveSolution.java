/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Start the mirror check from the root's children
        return isMirror(root.left, root.right);
    }

    /**
     * Helper function to check if two subtrees t1 and t2 are mirrors of each other.
     */
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // 1. Base Case: Both nodes are null (symmetric)
        if (t1 == null && t2 == null) {
            return true;
        }

        // 2. Base Case: One is null OR values don't match (not symmetric)
        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }

        // 3. Recursive Step: Check the mirrored pairs
        // - t1's left must mirror t2's right (outside pair)
        // - t1's right must mirror t2's left (inside pair)
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}