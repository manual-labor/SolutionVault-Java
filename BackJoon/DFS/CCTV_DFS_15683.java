// https://www.acmicpc.net/problem/15683

/*

문제

스타트링크의 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다. 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 각 CCTV가 감시할 수 있는 방법은 다음과 같다.
				
1번 	2번 	3번 	4번 	5번

1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.

CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.

CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.

0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0

지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다. 위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '#'로 나타내면 아래와 같다.

0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 # 6 0
0 0 0 0 0 0

	

0 0 0 0 0 0
0 0 0 0 0 0
# # 1 0 6 0
0 0 0 0 0 0

	

0 0 # 0 0 0
0 0 # 0 0 0
0 0 1 0 6 0
0 0 0 0 0 0

	

0 0 0 0 0 0
0 0 0 0 0 0
0 0 1 0 6 0
0 0 # 0 0 0

→ 	← 	↑ 	↓

CCTV는 벽을 통과할 수 없기 때문에, 1번이 → 방향을 감시하고 있을 때는 6의 오른쪽에 있는 칸을 감시할 수 없다.

0 0 0 0 0 0
0 2 0 0 0 0
0 0 0 0 6 0
0 6 0 0 2 0
0 0 0 0 0 0
0 0 0 0 0 5

위의 예시에서 감시할 수 있는 방향을 알아보면 아래와 같다.

0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5

	

0 0 0 0 0 #
# 2 # # # #
0 0 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5

	

0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 # # 2 #
0 0 0 0 0 #
# # # # # 5

	

0 # 0 0 0 #
0 2 0 0 0 #
0 # 0 0 6 #
0 6 0 0 2 #
0 0 0 0 # #
# # # # # 5

왼쪽 상단 2: ↔, 오른쪽 하단 2: ↔ 	왼쪽 상단 2: ↔, 오른쪽 하단 2: ↕ 	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↔ 	왼쪽 상단 2: ↕, 오른쪽 하단 2: ↕

CCTV는 CCTV를 통과할 수 있다. 아래 예시를 보자.

0 0 2 0 3
0 6 0 0 0
0 0 6 6 0
0 0 0 0 0

위와 같은 경우에 2의 방향이 ↕ 3의 방향이 ←와 ↓인 경우 감시받는 영역은 다음과 같다.

# # 2 # 3
0 6 # 0 #
0 0 6 6 #
0 0 0 0 #

사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
입력

첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다. 

CCTV의 최대 개수는 8개를 넘지 않는다.
출력

첫째 줄에 사각 지대의 최소 크기를 출력한다.
 
 
 */
package BackJoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class CCTV {
    int x;
    int y;
    int number;

    CCTV(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}

public class CCTV_DFS_15683 {
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int[][] board;
    static int height, width;
    static int voidCount = 0;
    static int answer;
    static int[][] cctvX = {
            {},
            { 1 },
            { 1, -1 },
            { 1, 0 },
            { -1, 0, 1 },
            { -1, 0, 0, 1 },
    };
    static int[][] cctvY = {
            {},
            { 0 },
            { 0, 0 },
            { 0, -1 },
            { 0, -1, 0 },
            { 0, -1, 1, 0 },
    };

    static int[] permutation;

    static void permutation(int index) {
        if (index == cctvList.size()) {
            activeCCTV(permutation);
        } else {
            for (int i = 0; i < 4; i++) {
                permutation[index] = i;
                permutation(index + 1);
            }
        }
    }

    static void activeCCTV(int[] direction) {
        int voidRemain = voidCount;
        int[][] copiedBoard = new int[height][width];

        for(int i=0; i<board.length; i++) {
            System.arraycopy(board[i], 0, copiedBoard[i], 0, board[i].length);
        }

        
        for (int i = 0; i < direction.length; i++) {
            CCTV currentCCTV = cctvList.get(i);


            for (int j = 0; j < cctvX[currentCCTV.number].length; j++) {

                int destinationX = cctvX[currentCCTV.number][j];
                int destinationY = cctvY[currentCCTV.number][j];

                switch (permutation[i]) {
                    case 1:
                        destinationX = cctvY[currentCCTV.number][j];
                        destinationY = cctvX[currentCCTV.number][j] * -1;
                        break;
                    case 2:
                        destinationX = cctvX[currentCCTV.number][j] * -1;
                        destinationY = cctvY[currentCCTV.number][j] * -1;
                        break;
                    case 3:
                        destinationX = cctvY[currentCCTV.number][j] * -1;
                        destinationY = cctvX[currentCCTV.number][j];
                        break;
                }
                int nextX = currentCCTV.x;
                int nextY = currentCCTV.y;

                while (true) {
                    nextX += destinationX;
                    nextY += destinationY;

                    if (nextX < 0 || nextX >= width || nextY < 0 || nextY >= height)
                        break;

                    if (copiedBoard[nextY][nextX] == 0) {
                        copiedBoard[nextY][nextX] = 7;
                        voidRemain--;
                    } else if (copiedBoard[nextY][nextX] == 6) {
                        break;
                    }
                }
            }
        }

       
        answer = Math.min(voidRemain, answer);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        height = Integer.parseInt(input[0]);
        width = Integer.parseInt(input[1]);

        board = new int[height][width];

        for (int i = 0; i < height; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                board[i][j] = Integer.parseInt(row[j]);
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctvList.add(new CCTV(j, i, board[i][j]));
                } else if (board[i][j] == 0) {
                    voidCount++;
                }
            }
        }

        answer = voidCount;
        permutation = new int[cctvList.size()];

        // 조합 뽑음
        permutation(0);

        System.out.println(answer);

    }
}
