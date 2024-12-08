import java.lang.reflect.Array;
import java.util.*;

class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

     public BinaryTreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BFSForBinaryTree {

    public static ArrayList<Integer> getLevelOrder(BinaryTreeNode root) {
        //Your code goes here

        Queue<BinaryTreeNode> q = new LinkedList<>();
        ArrayList<Integer> bfs = new ArrayList<>();

        if(root == null || root.val == -1) return bfs;
        q.add(root);

        while(!q.isEmpty()){
            BinaryTreeNode frontHead = q.poll();
            bfs.add(frontHead.val);

            BinaryTreeNode lc = frontHead.left;
            if(lc!= null && lc.val != -1){
                q.add(lc);
            }

            BinaryTreeNode rc = frontHead.right;
            if(rc != null && rc.val != -1){
                q.add(rc);
            }
        }
        return bfs;
    }

    public static void breadthFirstSearchForBinaryTree(BinaryTreeNode root, ArrayList<Integer> bfs, ArrayList<BinaryTreeNode> currLevel){
        if(currLevel.isEmpty()) return;
        ArrayList<BinaryTreeNode> nextLevel = new ArrayList<>();

        for(BinaryTreeNode currVertex: currLevel){
//            if(currVertex != null && currVertex.val != -1){
                BinaryTreeNode lc = currVertex.left;
                BinaryTreeNode rc = currVertex.right;
                if(lc != null && lc.val != -1){
                    bfs.add(lc.val);
                    nextLevel.add(lc);
                }
                if(rc != null && rc.val != -1){
                    bfs.add(rc.val);
                    nextLevel.add(rc);
                }
//            }
        }
        breadthFirstSearchForBinaryTree(root, bfs, nextLevel);
    }

    public static ArrayList<Integer> getLevelOrder2(BinaryTreeNode root) {
        //Your code goes here

        ArrayList<BinaryTreeNode> currLevel = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();

        if(root == null || root.val == -1) return bfs;

        currLevel.add(root);
        bfs.add(root.val);
        breadthFirstSearchForBinaryTree(root, bfs, currLevel);
        return bfs;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int[] inputSequence = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer:: parseInt).toArray();

        //        for(int i = 0; i <  inputSequence.length; i++) {
//            if(inputSequence[i] != -1) {
//                BinaryTreeNode<Integer> root = new BinaryTreeNode<>(inputSequence[i]);
//                root.left = new BinaryTreeNode<>(inputSequence[2 * i + 1]);
//                root.right = new BinaryTreeNode<>(inputSequence[2 * i + 2]);
//            }
//        }

        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        System.out.println(getLevelOrder(root));
        System.out.println(getLevelOrder2(root));
    }
}
