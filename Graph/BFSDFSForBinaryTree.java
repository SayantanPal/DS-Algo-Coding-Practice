import java.util.*;

public class BFSDFSForBinaryTree {

    public static ArrayList<Integer> breadthFirstSearchForBinaryTreeNonRecursive(BinaryTreeNode root) {
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

    public static void breadthFirstSearchUsingRecursion(ArrayList<Integer> bfs, ArrayList<BinaryTreeNode> currLevel){
        if(currLevel.isEmpty()) return;
        ArrayList<BinaryTreeNode> nextLevel = new ArrayList<>();

        for(BinaryTreeNode currVertex: currLevel){
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
        }
        breadthFirstSearchUsingRecursion(bfs, nextLevel);
    }

    public static ArrayList<Integer> breadthFirstSearchForBinaryTree(BinaryTreeNode root) {
        //Your code goes here

        ArrayList<BinaryTreeNode> currLevel = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();

        if(root == null || root.val == -1) return bfs;

        currLevel.add(root);
        bfs.add(root.val);
        breadthFirstSearchUsingRecursion(bfs, currLevel);
        return bfs;
    }

    public static void depthFirstSearchUsingRecursion(BinaryTreeNode currVertex, ArrayList<Integer> dfs) {
        if (currVertex == null || currVertex.val == -1) return;

        dfs.add(currVertex.val);
        depthFirstSearchUsingRecursion(currVertex.left, dfs);
        depthFirstSearchUsingRecursion(currVertex.right, dfs);
    }

    public static ArrayList<Integer> depthFirstSearchForBinaryTree(BinaryTreeNode root){
        ArrayList<Integer> dfs = new ArrayList<>();
        depthFirstSearchUsingRecursion(root, dfs);
        return dfs;
    }

    public static ArrayList<Integer> depthFirstSearchForBinaryTreeNonRecursive(BinaryTreeNode root) {

        Stack<BinaryTreeNode> stack = new Stack<>();
        ArrayList<Integer> dfs = new ArrayList<>();

        if(root == null || root.val == -1) return dfs;
        stack.push(root);

        while(!stack.isEmpty()){
            BinaryTreeNode currVertex = stack.pop();
            dfs.add(currVertex.val);

            BinaryTreeNode rc = currVertex.right;
            if(rc != null && rc.val != -1){
                stack.push(currVertex.right);
            }

            BinaryTreeNode lc = currVertex.left;
            if(lc != null && lc.val != -1){
                stack.push(currVertex.left);
            }
        }
         return dfs;
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

//        BinaryTreeNode root = new BinaryTreeNode(1);
//        root.left = new BinaryTreeNode(2);
//        root.right = new BinaryTreeNode(3);
//        root.left.left = new BinaryTreeNode(4);
//        root.left.right = new BinaryTreeNode(5);

        int[] nums = { 1, 2, 3, 4, 5, 6, 6, 6, 6 };
        BinaryTreeNode root = DataStructuresFromArray.buildTree(nums);

        System.out.println(breadthFirstSearchForBinaryTreeNonRecursive(root));
        System.out.println(breadthFirstSearchForBinaryTree(root));
        System.out.println(depthFirstSearchForBinaryTree(root));
        System.out.println(depthFirstSearchForBinaryTreeNonRecursive(root));
    }
}
