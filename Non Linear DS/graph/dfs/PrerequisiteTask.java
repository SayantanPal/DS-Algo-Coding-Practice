package graph.dfs;

public class PrerequisiteTask {

    public static boolean dfs(int currVertex, int[][] adjGraph, boolean[] visited, boolean[] inStack) {
        visited[currVertex] = true;
        inStack[currVertex] = true;

        for (int neighbourVertex = 0; neighbourVertex < adjGraph.length; neighbourVertex++) {
            if (adjGraph[currVertex][neighbourVertex] == 1) { // Only check valid edges
                if (!visited[neighbourVertex]) {
                    if (!dfs(neighbourVertex, adjGraph, visited, inStack)) {
                        return false; // Cycle detected
                    }
                    // return dfs(neighbourVertex, adjGraph, visited, inStack);
                } else if (inStack[neighbourVertex]) {
                    return false; // Back edge found (cycle)
                }
            }
        }

        inStack[currVertex] = false; // Mark node as fully explored
        return true;
    }

    public static boolean prerequisiteTask(int[][] dependency, int n, int m) {
        int[][] adjGraph = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int u = dependency[i][1];
            int v = dependency[i][0];
            adjGraph[u][v] = 1; // Directed edge u -> v
        }

        boolean[] visited = new boolean[n + 1];
        boolean[] inStack = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (!dfs(i, adjGraph, visited, inStack)) {
                    return false; // Cycle detected
                }
            }
        }
        return true; // No cycle found
    }

    public static void main(String[] args) {
        int[][] dependency = {{1,2},{3,2},{1,3}};
        int n = 3;
        int m = 3;
        System.out.println(prerequisiteTask(dependency, n, m)); // true


    }
}
