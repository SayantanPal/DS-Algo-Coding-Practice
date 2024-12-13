package builder;

import model.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DataStructuresFromArray {

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

    public static ArrayList<ArrayList<Integer>> buildGraphAsAdjListOfList(int v, ArrayList<ArrayList<Integer>> edges, boolean bidirectional) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < v ; i++){
            graph.add(new ArrayList<>());
        }

        for(ArrayList<Integer> edge: edges){
            graph.get(edge.get(0)).add(edge.get(1)); // u -> v'
            if(bidirectional) // for undirected graph
                graph.get(edge.get(1)).add(edge.get(0)); // v' -> u
        }
        return graph;
    }

    public static int[][] buildGraphAsAdjMatrix(int v, ArrayList<ArrayList<Integer>> edges, boolean bidirectional) {
        // create adj undirected graph
        int[][] graph = new int[v][v];

        for(ArrayList<Integer> edge: edges){
            graph[edge.get(0)][edge.get(1)] = 1; // u -> v'
            if(bidirectional) // for undirected graph
                graph[edge.get(1)][edge.get(0)] = 1; // v' -> u
        }
        return graph;
    }
}
