// https://www.acmicpc.net/problem/14501

package BackJoon.DP;

import java.util.*;
import java.io.*;

public class Exit_DP_14501 {
    


    public static void main(String[] args) throws IOException {
        

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int remainDay = Integer.parseInt(reader.readLine());
        int[] dp = new int[remainDay+1];
        
        int[] period = new int[remainDay];
        int[] value = new int[remainDay];

        for(int i=0; i<remainDay; i++) {
            String[] input = reader.readLine().split(" ");
            period[i] = Integer.parseInt(input[0]); 
            value[i] = Integer.parseInt(input[1]);
        }

        for(int i=remainDay-1; i>=0; i--) {
            //기간 안에 상담이 가능할 경우
            if(i+period[i] <= remainDay) {
                // 오늘의 최댓값 dp[i] = 이전 이익 최댓값 vs 상담이 끝나는 날짜의 이익 + 이 상담의 이익
                dp[i] = Math.max(dp[i+1], dp[i+period[i]]+value[i]);
            } else {
                dp[i] = dp[i+1];
            }
        }

        System.out.println(dp[0]);
    }
}
