package BackJoon.DP;
// https://www.acmicpc.net/problem/11659

/*
 
문제

수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
입력

첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
출력

총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.

5 3
5 4 3 2 1
1 3
2 4
5 5

12
9
1



 */



import java.util.*;
import java.io.*;

public class PrefixSum {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[] length = reader.readLine().split(" ");

    int N = Integer.parseInt(length[0]);
    int M = Integer.parseInt(length[1]);
    int[] array = new int[N];
    int[] sumArray = new int[N + 1];
    sumArray[0] = 0;

    String[] line = reader.readLine().split(" ");
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(line[i]);
        sumArray[i+1] = array[i] + sumArray[i];
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      String[] resultRange = reader.readLine().split(" ");
      //2 ~ 4를 구하려면 2에서 4 누적합에 2보다 1 낮은 인덱스를 빼면 됨
      sb.append((sumArray[Integer.parseInt(resultRange[1])] - sumArray[Integer.parseInt(resultRange[0])-1])+"\n");
    }

    System.out.println(sb);
  }

}
