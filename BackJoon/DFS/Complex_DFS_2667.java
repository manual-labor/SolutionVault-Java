/*
https://www.youtube.com/watch?v=3_eVkTkBbJE

 시간복잡도

 DFS : O(V+E)

 V, E : N^2, 4N^2

https://www.acmicpc.net/problem/2667



문제

<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

입력

첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
출력

첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.


입력

7
0110100
0110101
1110101
0000111
0100000
0111110
0111000

출력
3
7
8
9


 * 
 */


package BackJoon.DFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Complex_DFS_2667 {

    
    static int[] dy = {-1, 0 , 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] board;
    static boolean[][] check;
    static int N;
    static int houseCount = 0;

    static void dfs(int y, int x) {
        houseCount++;
        for(int i=0; i<4; i++) {
            int nextY = y+dy[i];
            int nextX = x+dx[i];

            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !check[nextY][nextX] && board[nextY][nextX] == 1) {
                check[nextY][nextX] = true;
                dfs(nextY,nextX);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        

        int complexCount = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(bufferedReader.readLine());

        board = new int[N][N];
        check = new boolean[N][N];
    

        for(int i=0; i<N; i++) {
            String[] s = bufferedReader.readLine().split("");
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(s[j]);
            }
        }

        
        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(check[i][j] == false && board[i][j] == 1) {
                    check[i][j] = true;
                    houseCount = 0;
                    complexCount++;
                    dfs(i,j);
                    list.add(houseCount);
                }
            }
        }


        System.out.println(complexCount);

        Collections.sort(list);
        for(int house : list)
        System.out.println(house);


    }
}