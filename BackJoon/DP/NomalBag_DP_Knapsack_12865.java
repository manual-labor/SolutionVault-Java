package BackJoon.DP;
/*
 
https://www.acmicpc.net/problem/12865

문제

이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
입력

첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.
출력

한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

4 7
6 13
4 8
3 6
5 12

14
 */

 import java.util.*;
 import java.io.*;


public class NomalBag_DP_Knapsack_12865 {


    public static void main(String[] args) throws IOException {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       
       String[] countAndWeight = reader.readLine().split(" ");
       int count = Integer.parseInt(countAndWeight[0]);
       int maximumWeight = Integer.parseInt(countAndWeight[1]);

       int[] dp = new int[maximumWeight+1];


       for(int i=0; i<count; i++) {
        String[] input = reader.readLine().split(" ");
        int weight = Integer.parseInt(input[0]);
        int value = Integer.parseInt(input[1]);
        //j는 현재 가능한 배낭의 용량 
        for(int j=maximumWeight; j>=weight; j--) {
            // 물건을 취한값과 취하지 않은 값 중 큰 것
            dp[j] = Math.max(dp[j-weight]+value, dp[j]);
           }
       }

       System.out.println(dp[maximumWeight]);
    }
}