// https://school.programmers.co.kr/learn/courses/30/lessons/92343

package KAKAO;

import java.util.ArrayList;
import java.util.HashSet;

public class SheepAndWolf_Graph {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int answer = 0;
    static HashSet<Integer> isVisited = new HashSet<>();
    
    static void dfs(int vertex, int sheep, int wolf, int[] info) {

        for (int canVisitVertex : graph.get(vertex)) {
            if (!isVisited.contains(canVisitVertex)){
                if (info[vertex] == 1) {
                    wolf++;
                } else {
                    sheep++;
                }

                if(wolf>=)
                
                if(sheep > wolf) {
                    answer = Math.max(answer, sheep);
                } else return;
                
                isVisited.add(canVisitVertex);
                dfs(canVisitVertex, sheep, wolf, info);
                isVisited.remove(canVisitVertex);
            }
        }
        
    }
    
    public int solution(int[] info, int[][] edges) {
        for(int i=0; i<info.length; i++) {
           graph.add(new ArrayList<Integer>());
        }
        
       for(int i=0; i<edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
           graph.get(edges[i][1]).add(edges[i][0]);
        }
        
        dfs(0, 0, 0, info);
        
        return answer;
    }
}
