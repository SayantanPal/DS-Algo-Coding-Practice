package graph.dfs;

import java.util.List;

public class CycleDetectionInDirectedGraph {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];

        for (int i = 0; i < V; i++)
            if (dfs(i, visited, recStack, adj)) return true;
        return false;
    }
    private boolean dfs(int node, boolean[] visited, boolean[] recStack, List<List<Integer>> adj) {
        if (recStack[node]) return true;
        if (visited[node]) return false;
        visited[node] = true;
        recStack[node] = true;
        for (int neighbor : adj.get(node))
            if (dfs(neighbor, visited, recStack, adj)) return true;
        recStack[node] = false;
        return false;
    }
}


