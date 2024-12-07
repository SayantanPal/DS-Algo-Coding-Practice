import java.util.*;

/*
* Sample Input 1:
5 4
0 2
0 1
1 2
3 4
Sample Output 1:
0 1 2
3 4
* */

public class BFSDFSForUndirectedDisconnectedGraphUsingRecursion {

    public static void dfsTraversal(ArrayList<ArrayList<Integer>> graph, int currVertex, boolean[] visited, ArrayList<Integer> dfs){

        if(visited[currVertex]) return; // base condition

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourVertex: graph.get(currVertex)){
            // traverse neighbour vertex as current vertex
            dfsTraversal(graph, neighbourVertex, visited, dfs);
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.

        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v];

        // create adj undirected graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < v ; i++){
            graph.add(new ArrayList<>());
        }

        for(ArrayList<Integer> edge: edges){
            graph.get(edge.get(0)).add(edge.get(1)); // u -> v'
            graph.get(edge.get(1)).add(edge.get(0)); // v' -> u
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]){
                dfsTraversal(graph, vertex, visited, dfs);
                result.add(new ArrayList<>(dfs));
                dfs.clear();
            }
        }
        return result;
    }

    public static void dfsTraversal(int[][] graph, int currVertex, boolean[] visited, ArrayList<Integer> dfs){
        if(visited[currVertex]) return; // base condition

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourIndex = 0; neighbourIndex < graph[currVertex].length ; neighbourIndex++){
            if(currVertex != neighbourIndex && graph[currVertex][neighbourIndex] == 1)
                // traverse neighbour vertex as current vertex
                dfsTraversal(graph, neighbourIndex, visited, dfs);
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearch2(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v];

        // create adj undirected graph
        int[][] graph = new int[v][v];

        for(ArrayList<Integer> edge: edges){
            graph[edge.get(0)][edge.get(1)] = 1; // u -> v'
            graph[edge.get(1)][edge.get(0)] = 1; // v' -> u
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]) {
                dfsTraversal(graph, vertex, visited, dfs);
                result.add(new ArrayList<>(dfs));
                dfs.clear();
            }
        }
        return result;
    }

    public static void bfsTraversalUsingRecursion(int[][] graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            for(int neighbourVertex = 0; neighbourVertex < graph[currentVertex].length; neighbourVertex++){
                if(graph[currentVertex][neighbourVertex] == 1 && !visited[neighbourVertex]){
                    visited[neighbourVertex] = true;
                    bfs.add(neighbourVertex);
                    nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalUsingRecursion(graph, visited, bfs, nextLevel);
    }

    public static void bfsTraversalUsingRecursion(ArrayList<ArrayList<Integer>> graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            for(int neighbourVertex : graph.get(currentVertex)){
                if(!visited[neighbourVertex]){
                    visited[neighbourVertex] = true;
                    bfs.add(neighbourVertex);
                    nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalUsingRecursion(graph, visited, bfs, nextLevel);
    }


    public static ArrayList<ArrayList<Integer>> breadthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v];

        // create adj undirected graph
        int[][] graph = new int[v][v];

        for(ArrayList<Integer> edge: edges){
            graph[edge.get(0)][edge.get(1)] = 1; // u -> v'
            graph[edge.get(1)][edge.get(0)] = 1; // v' -> u
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> currentLevel = new ArrayList<>();
        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]) {
                bfs.add(vertex);
                visited[vertex] = true;
                currentLevel.add(vertex);
                bfsTraversalUsingRecursion(graph, visited, bfs, currentLevel);
                result.add(new ArrayList<>(bfs));
                bfs.clear();
            }
        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> breadthFirstSearch2(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v];

        // create adj undirected graph
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < v ; i++){
            graph.add(new ArrayList<>());
        }

        for(ArrayList<Integer> edge: edges){
            graph.get(edge.get(0)).add(edge.get(1)); // u -> v'
            graph.get(edge.get(1)).add(edge.get(0)); // v' -> u
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> currentLevel = new ArrayList<>();

        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]){
                bfs.add(vertex);
                visited[vertex] = true;
                currentLevel.add(vertex);
                bfsTraversalUsingRecursion(graph, visited, bfs, currentLevel);
                result.add(new ArrayList<>(bfs));
                bfs.clear();
            }
        }
        return result;
    }


    public static void main(String[] args){
        /*
        Scanner scanner = new Scanner(System.in);
        int[] firstLineInput = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int v = firstLineInput[0]; // total no of vertex nodes
        int e = firstLineInput[1]; // total no of edge
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < e; i++){
            int[] edgeInput = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.add(new ArrayList<>(Arrays.asList(edgeInput[0], edgeInput[1])));
        }
        */
        int v = 10; // total no of vertex node
        int e = 8; // total no of edges
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {{

            add(new ArrayList<>(Arrays.asList(0, 1)));
            add(new ArrayList<>(Arrays.asList(0, 3)));
            add(new ArrayList<>(Arrays.asList(0, 2)));
            add(new ArrayList<>(Arrays.asList(1, 4)));
            add(new ArrayList<>(Arrays.asList(1, 7)));
            add(new ArrayList<>(Arrays.asList(2, 5)));
            add(new ArrayList<>(Arrays.asList(3, 6)));
            add(new ArrayList<>(Arrays.asList(8, 9)));
        }};

        // not preserving order of search using adj list
        ArrayList<ArrayList<Integer>> result = depthFirstSearch(v, e, edges);
        System.out.println(result);

        // preserving order of search from left to right using adj matrix
        ArrayList<ArrayList<Integer>> result2 = depthFirstSearch2(v, e, edges);
        System.out.println(result2);

        // preserving order of search from left to right using adj matrix
        ArrayList<ArrayList<Integer>> result3 = breadthFirstSearch(v, e, edges);
        System.out.println(result3);

        // not preserving order of search using adj list
        ArrayList<ArrayList<Integer>> result4 = breadthFirstSearch2(v, e, edges);
        System.out.println(result4);
    }
}
