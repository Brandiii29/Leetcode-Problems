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
import java.util.LinkedList;
import java.util.Queue;

class iterativeSolution {
    /**
     * Checks if a binary tree is symmetric (a mirror image of itself around the center).
     * This is the iterative solution using a Queue.
     * * @param root The root of the binary tree.
     * @return true if the tree is symmetric, false otherwise.
     */
    public boolean isSymmetric(TreeNode root) {
        // A null tree is considered symmetric
        if (root == null) {
            return true;
        }

        // We use a Queue to store pairs of nodes that should be mirrors of each other.
        Queue<TreeNode> queue = new LinkedList<>();
        
        // Start by adding the root's left and right children as the first pair.
        // For a symmetric tree, these must be mirrors.
        queue.offer(root.left);
        queue.offer(root.right);

        // Process the queue until it's empty
        while (!queue.isEmpty()) {
            // Dequeue a pair of nodes
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            // Case 1: Both nodes are null. This pair is symmetric. Continue to the next pair.
            if (t1 == null && t2 == null) {
                continue;
            }

            // Case 2: One node is null OR their values do not match. 
            // The structure or values violate symmetry. Return false.
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            // Case 3: Both nodes exist and their values match. 
            // We need to check their children in a mirrored fashion.
            
            // Add the 'outside' pair: t1.left must mirror t2.right
            queue.offer(t1.left);
            queue.offer(t2.right);

            // Add the 'inside' pair: t1.right must mirror t2.left
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        // If the loop completes, all checked pairs were symmetric.
        return true;
    }
}