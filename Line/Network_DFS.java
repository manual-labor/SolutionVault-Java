// https://school.programmers.co.kr/learn/courses/30/lessons/43162

package Line;
import java.util.*;

class Network_DFS {
    static boolean[] isVisited;
    
    static void dfs(int number, int[][] computers) {
        isVisited[number] = true;
        
        for(int i=0; i<computers[number].length; i++) {
            if(computers[number][i]==1 && isVisited[i]==false) {
                dfs(i, computers);
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
    
        isVisited = new boolean[n];
        
        int answer = 0;
        for(int i=0; i<computers.length; i++) {
            if(isVisited[i] == false) {
                answer++;
                dfs(i, computers);
            }
        }
        
        return answer;
    }
}
