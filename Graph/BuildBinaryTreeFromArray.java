import java.util.LinkedList;
import java.util.Queue;


public class BuildBinaryTreeFromArray {

    public static BinaryTreeNode buildTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(nums[0]);
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < nums.length) {
            BinaryTreeNode curr = q.poll();
            if (i < nums.length) {
                curr.left = new BinaryTreeNode(nums[i++]);
                q.add(curr.left);
            }
            if (i < nums.length) {
                curr.right = new BinaryTreeNode(nums[i++]);
                q.add(curr.right);
            }
        }
        return root;
    }

}
