//https://www.acmicpc.net/problem/1504

/*
 * 
문제

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.
입력

첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.
출력

첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.


4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3

7

 */

package BackJoon.Nexon;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int vertex;
    int cost;

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}

public class Shortest_Distance_Dijkstra_1504 {
    static int[] distance;
    static int MAX_COST = 200000 * 100;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    static int dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        HashSet<Integer> isVisited = new HashSet<>();

        Arrays.fill(distance, MAX_COST);

        queue.offer(new Node(start, 0));
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (!isVisited.contains(currentNode.vertex)) {
                isVisited.add(currentNode.vertex);
                for (Node node : graph.get(currentNode.vertex)) {
                    if (distance[node.vertex] > currentNode.cost + node.cost) {
                        distance[node.vertex] = currentNode.cost + node.cost;
                        queue.offer(new Node(node.vertex, currentNode.cost + node.cost));
                    }
                }
            }

        }

        return distance[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] vertexAndEdgeCount = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(vertexAndEdgeCount[0]);
        int edgeCount = Integer.parseInt(vertexAndEdgeCount[1]);
        distance = new int[vertexCount + 1];

        for (int i = 0; i <= vertexCount; i++) {
            graph.add(new ArrayList<>());
        }

        Arrays.fill(distance, MAX_COST);

        for (int i = 0; i < edgeCount; i++) {
            String[] vertexAndEdge = reader.readLine().split(" ");
            graph.get(Integer.parseInt(vertexAndEdge[0]))
                    .add(new Node(Integer.parseInt(vertexAndEdge[1]), Integer.parseInt(vertexAndEdge[2])));
            // 방향성이 없으므로 반대방향에도 설정
            graph.get(Integer.parseInt(vertexAndEdge[1]))
                    .add(new Node(Integer.parseInt(vertexAndEdge[0]), Integer.parseInt(vertexAndEdge[2])));
        }

        String[] stopOver = reader.readLine().split(" ");
        int stopOverVertex1 = Integer.parseInt(stopOver[0]);
        int stopOverVertex2 = Integer.parseInt(stopOver[1]);

        // 1 -> 경유 1 -> 경유 2 -> N

        int case1Cost = dijkstra(1, stopOverVertex1) + dijkstra(stopOverVertex1, stopOverVertex2)
                + dijkstra(stopOverVertex2, vertexCount);
        int case2Cost = dijkstra(1, stopOverVertex2) + dijkstra(stopOverVertex2, stopOverVertex1)
                + dijkstra(stopOverVertex1, vertexCount);

        // 1 -> 경유 2 - > 경유 1 -> N

        int answer = (case1Cost >= MAX_COST && case2Cost >= MAX_COST) ? -1
                : Math.min(case1Cost, case2Cost);

        System.out.println(answer);
    }
}
