package Programmers.DP;
import java.util.*;

class CodingTest_DP {
    public int solution(int alp, int cop, int[][] problems) {
        
        //필요 알고력, 필요 코딩력, 얻는 알고력, 얻는 코딩력, 필요 시간
        // 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간
        
        int targetAlp = 0;
        int targetCop = 0;
        for (int i=0; i<problems.length; i++) {
            targetAlp = Math.max(targetAlp, problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
        }
        
        
       //DP 배열 dp[i][j]는 알고력이 i, 코딩력이 j에 도달했을 때 소모한 시간(비용)의 최솟값을 의미
        int[][] dp = new int[targetAlp+1][targetCop+1];
        
        
        // 이미 초깃값이 목표값보다 더 큰 경우 방지
        alp = Math.min(targetAlp, alp);
        cop = Math.min(targetCop, cop);
        
        //최솟값 구하므로 초기값은 최댓값으로
        for(int i=0; i<=targetAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp; i<=targetAlp; i++)
        for(int j=cop; j<=targetCop; j++) {
            
            //기본문제 풀이
            if(i < targetAlp) {
                // 다른 경우의 최솟값 vs 공부를 하는 경우
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
            }
            if(j < targetCop) {
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
            }
            
            for(int[] problem : problems) {
                if(i>= problem[0] && j>= problem[1]) {
                    int maxAlpReward = Math.min(targetAlp, i+problem[2]);
                    int maxCopReward = Math.min(targetCop, j+problem[3]);
                    dp[maxAlpReward][maxCopReward] = Math.min(dp[i][j]+problem[4], dp[maxAlpReward][maxCopReward]);
                }
            }
        }
        
        
        return dp[targetAlp][targetCop];
    }
}