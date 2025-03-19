package KAKAO;

import java.util.HashMap;

/*

https://school.programmers.co.kr/learn/courses/30/lessons/258711

도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프들이 있습니다. 이 그래프들은 1개 이상의 정점과, 정점들을 연결하는 단방향 간선으로 이루어져 있습니다.

    크기가 n인 도넛 모양 그래프는 n개의 정점과 n개의 간선이 있습니다. 도넛 모양 그래프의 아무 한 정점에서 출발해 이용한 적 없는 간선을 계속 따라가면 나머지 n-1개의 정점들을 한 번씩 방문한 뒤 원래 출발했던 정점으로 돌아오게 됩니다. 도넛 모양 그래프의 형태는 다음과 같습니다.



    크기가 n인 막대 모양 그래프는 n개의 정점과 n-1개의 간선이 있습니다. 막대 모양 그래프는 임의의 한 정점에서 출발해 간선을 계속 따라가면 나머지 n-1개의 정점을 한 번씩 방문하게 되는 정점이 단 하나 존재합니다. 막대 모양 그래프의 형태는 다음과 같습니다.



    크기가 n인 8자 모양 그래프는 2n+1개의 정점과 2n+2개의 간선이 있습니다. 8자 모양 그래프는 크기가 동일한 2개의 도넛 모양 그래프에서 정점을 하나씩 골라 결합시킨 형태의 그래프입니다. 8자 모양 그래프의 형태는 다음과 같습니다.



도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프가 여러 개 있습니다. 이 그래프들과 무관한 정점을 하나 생성한 뒤, 각 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프의 임의의 정점 하나로 향하는 간선들을 연결했습니다.
그 후 각 정점에 서로 다른 번호를 매겼습니다.
이때 당신은 그래프의 간선 정보가 주어지면 생성한 정점의 번호와 정점을 생성하기 전 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 구해야 합니다.

그래프의 간선 정보를 담은 2차원 정수 배열 edges가 매개변수로 주어집니다. 이때, 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 순서대로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
    

[정리]

들어가는 간선 수 2개 이상, 나가는 간선수 2개 이상은 8자
들어오는 정점이 1개, 나가는 정점이 없으면 막대 (막대 맨 위) 
출발 정점의 나가는 간선에서 8자 그래프와 막대 그래프를 빼면 도넛

 */
public class DoughnutAndStickGraph {

    public int[] solution(int[][] edges) {
        HashMap<Integer, Integer> in = new HashMap<>();
        HashMap<Integer, Integer> out = new HashMap<>();
        int[] answer = { 0, 0, 0, 0 };
        // 생성한 정점의 번호, 도넛, 막대, 8자

        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        for (int node : out.keySet()) {
            int outEdgeCount = out.getOrDefault(node, 0);
            int inEdgeCount = in.getOrDefault(node, 0);

            if (outEdgeCount >= 2) {

                if (inEdgeCount == 0) {
                    answer[0] = node; // 맞음
                } else if (inEdgeCount >= 2) {
                    answer[3]++;
                }
            }

        }

        for (int node : in.keySet()) {
            int outEdgeCount = out.getOrDefault(node, 0);
            int inEdgeCount = in.getOrDefault(node, 0);
            if (inEdgeCount >= 1 && outEdgeCount == 0) {
                answer[2]++; // 맞음
            }
        }

        answer[1] = out.getOrDefault(answer[0], 0) - answer[3] - answer[2];
        return answer;
    }

    public static void main(String[] args) {

        int[][] edges = { { 4, 11 }, { 1, 12 }, { 8, 3 }, { 12, 7 }, { 4, 2 }, { 7, 11 }, { 4, 8 }, { 9, 6 },
                { 10, 11 }, { 6, 10 }, { 3, 5 }, { 11, 1 }, { 5, 3 }, { 11, 9 }, { 3, 8 } };
        new DoughnutAndStickGraph().solution(edges);
    }
}
