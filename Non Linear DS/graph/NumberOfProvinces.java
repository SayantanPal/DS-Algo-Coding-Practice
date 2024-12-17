package graph;

// Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
// Link: https://www.naukri.com/code360/problems/find-the-number-of-states_1377943?interviewProblemRedirection=true&practice_topic%5B%5D=Breadth-first%20Search&practice_topic%5B%5D=Depth-first%20Search&difficulty%5B%5D=Medium&sort_entity=company_count&sort_order=DESC&leftPanelTabValue=PROBLEM
public class NumberOfProvinces {

    public static void DFS(int[][] graph, int currVertex, boolean[] visited, int n){
        if(visited[currVertex]) return;

        visited[currVertex] = true;

        for(int neighbourVertex = 0; neighbourVertex < n; neighbourVertex++){
            if(currVertex != neighbourVertex && graph[currVertex][neighbourVertex] == 1){
                DFS(graph, neighbourVertex, visited, n);
            }
        }

    }

    public static int findNumOfProvinces(int[][] roads, int n) {
        // Write your code here.
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int vertex = 0; vertex < n; vertex++){
            if(!visited[vertex]){
                DFS(roads, vertex, visited, n);
                count++;
            }
        }
        return count;
    }

}
