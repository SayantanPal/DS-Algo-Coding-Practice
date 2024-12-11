import java.util.*;

public class MirrorBinaryTree {

    public static BinaryTreeNode mirrorTreeRecursion(BinaryTreeNode root){
        if(root == null || root.val == -1) return null;

        BinaryTreeNode lc = mirrorTreeRecursion(root.left);
        BinaryTreeNode rc = mirrorTreeRecursion(root.right);

        root.left = rc;
        root.right = lc;

        return root;
    }

    public static void mirrorTree(BinaryTreeNode node) {
        // Write your code here.

        if(node == null || node.val == -1) return;

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(node);


        while(!q.isEmpty()){
            BinaryTreeNode currVertex = q.poll();


            BinaryTreeNode temp = currVertex.left;
            currVertex.left = currVertex.right;
            currVertex.right = temp;

            if(currVertex.left != null){
                q.add(currVertex.left);
            }
            if(currVertex.right != null){
                q.add(currVertex.right);
            }
        }
    }

    public static void inOrderPrintTree(BinaryTreeNode root) {
        if (root == null || root.val == -1) {
            return;
        }
        inOrderPrintTree(root.left);
        System.out.print(root.val + " ");
        inOrderPrintTree(root.right);
    }


    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
        BinaryTreeNode root = BuildBinaryTreeFromArray.buildTree(nums);
        BinaryTreeNode root2 = BuildBinaryTreeFromArray.buildTree(nums);

        inOrderPrintTree(root);
        System.out.println();

        mirrorTree(root);
        inOrderPrintTree(root);
        System.out.println();

        mirrorTreeRecursion(root2);
        inOrderPrintTree(root);
    }
}
