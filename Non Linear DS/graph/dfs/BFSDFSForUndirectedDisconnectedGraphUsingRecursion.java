package graph.dfs;

import builder.DataStructuresFromArray;

import java.util.*;
import java.util.stream.IntStream;

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

    public static void dfsTraversalRecursive_v1(ArrayList<ArrayList<Integer>> graph, boolean[] visited, ArrayList<Integer> dfs, int currVertex){

        if(visited[currVertex]) return; // base condition

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourVertex: graph.get(currVertex)){
            // traverse neighbour vertex as current vertex
            dfsTraversalRecursive_v1(graph, visited, dfs, neighbourVertex);
        }
    }

    public static void dfsTraversalRecursive_v2(ArrayList<ArrayList<Integer>> graph, boolean[] visited, ArrayList<Integer> dfs, int currVertex){

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourVertex: graph.get(currVertex)){
            // traverse neighbour vertex as current vertex
            if(!visited[neighbourVertex])
                dfsTraversalRecursive_v2(graph, visited, dfs, neighbourVertex);
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearchRecUsingAdjListOfList(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops


        // create adj undirected graph
        ArrayList<ArrayList<Integer>> graph = DataStructuresFromArray.buildGraphAsAdjListOfList(v, edges, true);

        int noOfDisconnectedComponents = 0;
        // traverse all unvisited vertices to not miss out on any isolated connected components
        for(int startVertex = 0; startVertex < v ; startVertex++){
            if(!visited[startVertex]){
                noOfDisconnectedComponents++;
//                dfsTraversalRecursive_v1(graph, visited, dfs, startVertex);
                dfsTraversalRecursive_v2(graph, visited, dfs, startVertex);
                result.add(new ArrayList<>(dfs));
                dfs.clear();
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }

    public static void dfsTraversalRecursive_ver1(int[][] graph, int currVertex, boolean[] visited, ArrayList<Integer> dfs){
        if(visited[currVertex]) return; // base condition

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourVertex = 0; neighbourVertex < graph[currVertex].length ; neighbourVertex++){
            if(currVertex != neighbourVertex && graph[currVertex][neighbourVertex] == 1)
                // traverse neighbour vertex as current vertex
                dfsTraversalRecursive_ver1(graph, neighbourVertex, visited, dfs);
        }
    }

    public static void dfsTraversalRecursive_ver2(int[][] graph, int currVertex, boolean[] visited, ArrayList<Integer> dfs){

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for(int neighbourVertex = 0; neighbourVertex < graph[currVertex].length ; neighbourVertex++){
            if(currVertex != neighbourVertex && graph[currVertex][neighbourVertex] == 1 && !visited[neighbourVertex])
                // traverse neighbour vertex as current vertex
                dfsTraversalRecursive_ver2(graph, neighbourVertex, visited, dfs);
        }
    }

    public static void dfsTraversalRecursive_ver3(int[][] graph, int currVertex, boolean[] visited, ArrayList<Integer> dfs) {

        dfs.add(currVertex);
        visited[currVertex] = true;

        // find neighbour vertices of current vertex
        for (int neighbourVertex : IntStream.range(0, graph[currVertex].length)
                                    .filter(vertex -> graph[currVertex][vertex] == 1)
                                    .boxed() // Convert to Integer stream
                                    .toList()) {
            if (!visited[neighbourVertex]) {  // traverse neighbour vertex as current vertex
                dfsTraversalRecursive_ver3(graph, neighbourVertex, visited, dfs);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearchRecUsingAdjMatrix(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        // traverse all unvisited vertices to not miss out on any isolated connected components
        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]) {
                noOfDisconnectedComponents++;
//                dfsTraversalRecursive_ver1(graph, vertex, visited, dfs);
//                dfsTraversalRecursive_ver2(graph, vertex, visited, dfs);
                dfsTraversalRecursive_ver3(graph, vertex, visited, dfs);
                result.add(new ArrayList<>(dfs));
                dfs.clear();
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }

    public static void bfsTraversalUsingRecursion_ver1(int[][] graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            for(int neighbourVertex = 0; neighbourVertex < graph[currentVertex].length; neighbourVertex++){
                if(graph[currentVertex][neighbourVertex] == 1 && currentVertex != neighbourVertex && !visited[neighbourVertex]){
                    visited[neighbourVertex] = true;
                    bfs.add(neighbourVertex);
                    nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalUsingRecursion_ver1(graph, visited, bfs, nextLevel);
    }

    public static void bfsTraversalRecursive_ver2(int[][] graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            visited[currentVertex] = true;
            bfs.add(currentVertex);
            for(int neighbourVertex = 0; neighbourVertex < graph[currentVertex].length; neighbourVertex++){
                if(graph[currentVertex][neighbourVertex] == 1 && currentVertex != neighbourVertex && !visited[neighbourVertex]){
                    nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalRecursive_ver2(graph, visited, bfs, nextLevel);
    }

    public static void bfsTraversalRecursive_ver3(int[][] graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            visited[currentVertex] = true;
            bfs.add(currentVertex);
            for(int neighbourVertex: IntStream.range(0, graph[currentVertex].length)
                                    .filter(vertex -> graph[currentVertex][vertex] == 1)
                                    .boxed() // Convert to Integer stream
                                    .toList() ){
                 if(!visited[neighbourVertex]){
                        nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalRecursive_ver3(graph, visited, bfs, nextLevel);
    }

    public static ArrayList<ArrayList<Integer>> breadthFirstSearchNonRecUsingAdjMatrix_ver1(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(vertex);

                while(!q.isEmpty()) {
                    int currVertex = q.poll();
                    visited[currVertex] = true;
                    bfs.add(currVertex);

                    for(int neighbourVertex = 0; neighbourVertex < v; neighbourVertex++){
                        if(currVertex!= neighbourVertex && graph[currVertex][neighbourVertex] == 1 && !visited[neighbourVertex]){
                            q.add(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(bfs));
                bfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }

    public static ArrayList<ArrayList<Integer>> breadthFirstSearchNonRecUsingAdjMatrix_ver2(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(vertex);

                while(!q.isEmpty()) {
                    int currVertex = q.poll();
                    visited[currVertex] = true;
                    bfs.add(currVertex);

                    for(int neighbourVertex : IntStream.range(0, graph[currVertex].length).filter(u -> graph[currVertex][u] == 1).boxed().toList()){
                        if(!visited[neighbourVertex]){
                            q.add(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(bfs));
                bfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }


    public static ArrayList<ArrayList<Integer>> depthFirstSearchNonRecUsingAdjMatrix_ver1(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(vertex);

                while(!stack.isEmpty()) {
                    int currVertex = stack.pop();
                    visited[currVertex] = true;
                    dfs.add(currVertex);

                    for(int neighbourVertex = v - 1; neighbourVertex >= 0; neighbourVertex--){
                        if(currVertex!= neighbourVertex && graph[currVertex][neighbourVertex] == 1 && !visited[neighbourVertex]){
                            stack.push(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(dfs));
                dfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }

    public static ArrayList<ArrayList<Integer>> depthFirstSearchNonRecUsingAdjMatrix_ver2(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(vertex);

                while(!stack.isEmpty()) {
                    int currVertex = stack.pop();
                    visited[currVertex] = true;
                    dfs.add(currVertex);

                    for(int neighbourVertex : IntStream.iterate(v - 1, i -> i >= 0, i -> i - 1).filter(u -> graph[currVertex][u] == 1).boxed().toList()){
                        if(!visited[neighbourVertex]){
                            stack.push(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(dfs));
                dfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }


    public static ArrayList<ArrayList<Integer>> depthFirstSearchNonRecUsingAdjListOfList(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        ArrayList<ArrayList<Integer>> graph = DataStructuresFromArray.buildGraphAsAdjListOfList(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(vertex);

                while(!stack.isEmpty()) {
                    int currVertex = stack.pop();
                    visited[currVertex] = true;
                    dfs.add(currVertex);

                    for(int neighbourVertex : graph.get(currVertex).reversed()){
                        if(!visited[neighbourVertex]){
                            stack.push(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(dfs));
                dfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }



    public static ArrayList<ArrayList<Integer>> breadthFirstSearchNonRecUsingAdjListOfList(int v, int e, ArrayList<ArrayList<Integer>> edges){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj list of list
        ArrayList<ArrayList<Integer>> graph = DataStructuresFromArray.buildGraphAsAdjListOfList(v, edges, true);

        int noOfDisconnectedComponents = 0;
        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]) {
                Queue<Integer> q = new LinkedList<>();
                q.add(vertex);

                while(!q.isEmpty()) {
                    int currVertex = q.poll();
                    visited[currVertex] = true;
                    bfs.add(currVertex);

                    for(int neighbourVertex: graph.get(currVertex)){
                        if(!visited[neighbourVertex]){
                            q.add(neighbourVertex);
                        }
                    }
                }
                result.add(new ArrayList<>(bfs));
                bfs.clear();
                noOfDisconnectedComponents++;
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }


    public static void bfsTraversalRecursive_ver1(ArrayList<ArrayList<Integer>> graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
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

        bfsTraversalRecursive_ver1(graph, visited, bfs, nextLevel);
    }

    public static void bfsTraversalRecursive_ver2(ArrayList<ArrayList<Integer>> graph, boolean[] visited, ArrayList<Integer> bfs, List<Integer> currentLevel){
        if(currentLevel.isEmpty()) return; // base condition

        ArrayList<Integer> nextLevel = new ArrayList<>();

        // find neighbour vertices of vertices in current level
        for(int currentVertex: currentLevel){
            visited[currentVertex] = true;
            bfs.add(currentVertex);
            for(int neighbourVertex : graph.get(currentVertex)){
                if(!visited[neighbourVertex]){
                    nextLevel.add(neighbourVertex);
                }
            }
        }

        bfsTraversalRecursive_ver2(graph, visited, bfs, nextLevel);
    }


    public static ArrayList<ArrayList<Integer>> breadthFirstSearchRecUsingAdjMatrix(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        int[][] graph = DataStructuresFromArray.buildGraphAsAdjMatrix(v, edges, true);

        int noOfDisconnectedComponents = 0;
        // traverse all unvisited vertices to not miss out on any isolated connected components
        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]) {
                ArrayList<Integer> currentLevel = new ArrayList<>(Collections.singletonList(vertex));
//                currentLevel.add(vertex); // needs to be skipped if already initializing the list with vertex using Collections.singletonList(vertex)
//                bfs.add(vertex);         // for bfs only in ver 1
//                visited[vertex] = true;  // for bfs only in ver 1
//                bfsTraversalUsingRecursion_ver2(graph, visited, bfs, currentLevel);
//                bfsTraversalRecursive_ver2(graph, visited, bfs, currentLevel);
                bfsTraversalRecursive_ver3(graph, visited, bfs, currentLevel);
                noOfDisconnectedComponents++;
                result.add(new ArrayList<>(bfs));
                bfs.clear();
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
        return result;
    }

    public static ArrayList<ArrayList<Integer>> breadthFirstSearchRecUsingAdjListOfList(int v, int e, ArrayList<ArrayList<Integer>> edges) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[v]; // 1D array to mark vertex as visited once scanned and to avoid revisit of same vertex for bfs/dfs in graph ds because of possibility for existence of hidden loops

        // create adj undirected graph
        ArrayList<ArrayList<Integer>> graph = DataStructuresFromArray.buildGraphAsAdjListOfList(v, edges, true);

        int noOfDisconnectedComponents = 0;
        // traverse all unvisited vertices to not miss out on any isolated connected components
        for(int vertex = 0; vertex < v ; vertex++){
            if(!visited[vertex]){
                ArrayList<Integer> currentLevel = new ArrayList<>(Collections.singletonList(vertex));
//                currentLevel.add(vertex);// needs to be skipped if already initializing the list with vertex using Collections.singletonList(vertex)
//                bfs.add(vertex);        // for bfs only in ver 1
//                visited[vertex] = true; // for bfs only in ver 1
//                bfsTraversalUsingRecursion(graph, visited, bfs, currentLevel);
                bfsTraversalRecursive_ver2(graph, visited, bfs, currentLevel);
                result.add(new ArrayList<>(bfs));
                noOfDisconnectedComponents++;
                bfs.clear();
            }
        }
        System.out.printf("No of disconnected components: %d\n", noOfDisconnectedComponents);
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

        // preserving insertion order of search using adj list
        ArrayList<ArrayList<Integer>> result = depthFirstSearchRecUsingAdjListOfList(v, e, edges);
        System.out.println("DFS via adj list of list using recursion: "+result);

        // preserving insertion order of search using adj list
        ArrayList<ArrayList<Integer>> result2 = depthFirstSearchNonRecUsingAdjListOfList(v, e, edges);
        System.out.println("DFS via adj list of list without using recursion: "+result2+"\n");

        // preserving sorted order from left to right using adj two_dimensional_grid_matrix
        ArrayList<ArrayList<Integer>> result3 = depthFirstSearchRecUsingAdjMatrix(v, e, edges);
        System.out.println("DFS via adj two_dimensional_grid_matrix using recursion: "+result3);

        ArrayList<ArrayList<Integer>> result4 = depthFirstSearchNonRecUsingAdjMatrix_ver2(v, e, edges);
        System.out.println("DFS via adj two_dimensional_grid_matrix without using recursion: "+result4+"\n");

        // preserving sorted order from left to right using adj two_dimensional_grid_matrix
        ArrayList<ArrayList<Integer>> result5 = breadthFirstSearchRecUsingAdjMatrix(v, e, edges);
        System.out.println("BFS via adj two_dimensional_grid_matrix using recursion: "+result5);

        // preserving sorted order from left to right using adj two_dimensional_grid_matrix
        ArrayList<ArrayList<Integer>> result6 = breadthFirstSearchNonRecUsingAdjMatrix_ver2(v, e, edges);
        System.out.println("BFS via adj two_dimensional_grid_matrix without using recursion: "+result6+"\n");

        // preserving insertion order of search using adj list
        ArrayList<ArrayList<Integer>> result7 = breadthFirstSearchRecUsingAdjListOfList(v, e, edges);
        System.out.println("BFS via adj list of list using recursion: "+result7);

        // preserving insertion order of search using adj list
        ArrayList<ArrayList<Integer>> result8 = breadthFirstSearchNonRecUsingAdjListOfList(v, e, edges);
        System.out.println("BFS via adj list of list without using recursion: "+result8+"\n");
    }
}
