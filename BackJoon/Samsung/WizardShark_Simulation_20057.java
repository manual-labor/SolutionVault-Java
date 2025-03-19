// https://www.acmicpc.net/problem/20057
/*
 
문제

마법사 상어가 토네이도를 배웠고, 오늘은 토네이도를 크기가 N×N인 격자로 나누어진 모래밭에서 연습하려고 한다. 위치 (r, c)는 격자의 r행 c열을 의미하고, A[r][c]는 (r, c)에 있는 모래의 양을 의미한다.

토네이도를 시전하면 격자의 가운데 칸부터 토네이도의 이동이 시작된다. 토네이도는 한 번에 한 칸 이동한다. 다음은 N = 7인 경우 토네이도의 이동이다.

토네이도가 한 칸 이동할 때마다 모래는 다음과 같이 일정한 비율로 흩날리게 된다.


토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동한다. 비율이 적혀있는 칸으로 이동하는 모래의 양은 y에 있는 모래의 해당 비율만큼이고, 계산에서 소수점 아래는 버린다. α로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남은 모래의 양과 같다. 모래가 이미 있는 칸으로 모래가 이동하면, 모래의 양은 더해진다. 위의 그림은 토네이도가 왼쪽으로 이동할 때이고, 다른 방향으로 이동하는 경우는 위의 그림을 해당 방향으로 회전하면 된다.

토네이도는 (1, 1)까지 이동한 뒤 소멸한다. 모래가 격자의 밖으로 이동할 수도 있다. 토네이도가 소멸되었을 때, 격자의 밖으로 나간 모래의 양을 구해보자.
입력

첫째 줄에 격자의 크기 N이 주어진다. 둘째 줄부터 N개의 줄에는 격자의 각 칸에 있는 모래가 주어진다. r번째 줄에서 c번째 주어지는 정수는 A[r][c] 이다.
출력

격자의 밖으로 나간 모래의 양을 출력한다.

 */

package BackJoon.Samsung;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WizardShark_Simulation_20057 {
    static int board[][];
    static int boardSize;
    static int answer = 0;

    // 가로
    static int[] moveX = { -1, 0, 1, 0 };
    static int[] spreadX = { -2, -1, -1, 0, 0, 0, 0, 1, 1 };

    // 세로
    static int[] moveY = { 0, 1, 0, -1 };
    static int[] spreadY = { 0, 1, -1, 1, -1, 2, -2, 1, -1 };

    static int[] ratio = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };

    static void move(int x, int y, int moveIndex) {
        int totalSpreaded = 0;
        int outOfAreaSand = 0;

        int nextX = x + moveX[moveIndex];
        int nextY = y + moveY[moveIndex];


        if(nextX < 0 || nextX >= boardSize || nextY < 0 || nextY >= boardSize) {
            return;
        }
        
        int sand = board[nextY][nextX];
        
        if(sand == 0) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            int spreadRotationX = spreadX[i];
            int spreadRotationY = spreadY[i];

            switch (moveIndex) {
                // 아래쪽
                case 1:
                    spreadRotationX = spreadY[i] * -1;
                    spreadRotationY = spreadX[i] * -1;
                    break;

                // 오른쪽
                case 2:
                    spreadRotationX = spreadX[i] * -1;
                    spreadRotationY = spreadY[i] * -1;
                    break;

                // 위쪽
                case 3:
                    spreadRotationX = spreadY[i];
                    spreadRotationY = spreadX[i];
                    break;
            }

            int spreadedX = nextX + spreadRotationX;
            int spreadedY = nextY + spreadRotationY;

            int spreadedSand = sand * ratio[i] / 100;
            if (spreadedX >= 0 && spreadedX < boardSize && spreadedY >= 0 && spreadedY < boardSize) {
                board[spreadedY][spreadedX] += spreadedSand;
            } else {
                outOfAreaSand += spreadedSand;
            }

            totalSpreaded += spreadedSand;
        }

        int alphaX = nextX + moveX[moveIndex];
        int alphaY = nextY + moveY[moveIndex];


        if (alphaX >= 0 && alphaX < boardSize && alphaY >= 0 && alphaY < boardSize) {
            board[alphaY][alphaX] += sand - totalSpreaded;
        }
        else
            outOfAreaSand += sand - totalSpreaded;



        board[nextY][nextX] = 0;
        answer += outOfAreaSand;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boardSize = Integer.parseInt(reader.readLine());
        board = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        int middleIndex = boardSize / 2;

        int count = 0;
        int x = middleIndex, y = middleIndex;
        int moveAmount = 1;
        int currentDirection = 0;
        int boardVolume = boardSize * boardSize;
        while (count < boardVolume) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < moveAmount && count < boardVolume; j++) {
                    move(x, y, currentDirection);
                    x += moveX[currentDirection];
                    y += moveY[currentDirection];
                    count++;
                }
                currentDirection = (currentDirection + 1) % 4;

                
            }
            moveAmount++;
        }

        System.out.println(answer);
    }
}
