package Algorithm.Graph;

import DataStruct.Queue.Queue;
import DataStruct.Stack.MyStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    // given a non-directed graph (can be disconnected, hence having >= 2 components)
    // some algorithms on the given graph
    public static void BFS(Map<Integer, List<Integer>> adjList, int start) {
        int N = adjList.size();
        List<Integer> res = new ArrayList<>(N+1);
        // need Queue
        Queue<Integer> bq = new Queue<>();
        boolean[] visited = new boolean[N+1];
        bq.enqueue(start);
        visited[start] = true;

        while (!bq.isEmpty()) {
            int curr = bq.dequeue();
            res.add(curr);
            for(int neighbor : adjList.get(curr)) {
                if (visited[neighbor]) continue;
                bq.enqueue(neighbor);
                visited[neighbor] = true;
            }
        }
        System.out.println(res);
    }

    public static void DFS(Map<Integer, List<Integer>> adjList, int start) {
        int N = adjList.size();
        List<Integer> res = new ArrayList<>(N+1);
        // need Stack
        MyStack<Integer> ds = new MyStack<>();
        boolean[] visited = new boolean[N+1];
        ds.push(start);

        while (!ds.isEmpty()) {
            int curr = ds.pop();
            if (visited[curr]) continue;
            // else
            visited[curr] = true;
            res.add(curr);

            List<Integer> t = adjList.get(curr);
            for (int i = t.size()-1; i >=0; i--) {
                if (!visited[t.get(i)])
                    ds.push(t.get(i));
            }
        }
        System.out.println(res);
    }

    public static void Prim(Map<Integer, List<Integer>> adjList) {}
    public static void Kruskal(Map<Integer, List<Integer>> adjList) {
        // edges get selected into the spanning tree order by weight asc
        // as long as there is no cycle
        // Min priority queue for Pair <weight, [u, v]> represents edges
        // poll from edges and put to graph, check for any cycle in new graph
    }

    // bfs to detect cycle
    // if meet 1 unvisited neighbor, process like usual
    // if meet 1 visited neighbor node, and it is NOT parent -> a cycle
    private static boolean bfsCycle(int start, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        // { [v, parent_of_v] }
        Queue<int[]> q = new Queue<>();
        q.enqueue(new int[]{start, -1});
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.peek()[0];
            int parent = q.dequeue()[1];
            for (int neighbor : adjList.get(curr)) {
                if (!visited[neighbor]) {
                    q.enqueue(new int[]{neighbor, curr});
                    visited[neighbor] = true;
                }
                else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    // call bfs on graph with every node as start to check all its component(s)
    // and check each component for cycle
    // {
    //  0: [1]
    //  1: [0, 2, 3]
    //  2: []
    //  3: [1]
    //  4: [5, 6]
    //  5: [4, 6]
    //  6: [4, 5]
    // } <- this (disconnected graph) contains cycle
    public static boolean hasCycle(Map<Integer, List<Integer>> adjList) {
        int V = adjList.size();
        boolean[] visited = new boolean[V+1];
        for (int start : adjList.keySet()) {
            if (!visited[start]) {
                if(bfsCycle(start, adjList, visited)) return true;
            }
        }
        return false;
    }
}
