// https://www.acmicpc.net/problem/2578

/*
 
빙고 게임은 다음과 같은 방식으로 이루어진다.

25개의 칸으로 이루어진 빙고판에 1부터 25까지 자연수를 한 칸에 하나씩 쓴다

다음은 사회자가 부르는 수를 차례로 지워나간다. 예를 들어 5, 10, 7이 불렸다면 이 세 수를 지운 뒤 빙고판의 모습은 다음과 같다.

차례로 수를 지워가다가 같은 가로줄, 세로줄 또는 대각선 위에 있는 5개의 모든 수가 지워지는 경우 그 줄에 선을 긋는다.

이러한 선이 세 개 이상 그어지는 순간 "빙고"라고 외치는데, 가장 먼저 외치는 사람이 게임의 승자가 된다.

철수는 친구들과 빙고 게임을 하고 있다. 철수가 빙고판에 쓴 수들과 사회자가 부르는 수의 순서가 주어질 때, 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지를 출력하는 프로그램을 작성하시오.

 */

package LG_CNS;

import java.util.*;
import java.io.*;

public class Bingo_Implementation {
    static boolean[][] answerBoard = new boolean[5][5];
    static boolean[] widthBingo = new boolean[5], lengthBingo = new boolean[5];
    static boolean leftDiagonalBingo, rightDiagonalBingo;
    static int bingoCount = 0;
    static int[][] leftDiagonalPoint = { { 0, 0 }, { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, 4 } };
    static int[][] rightDiagonalPoint = { { 0, 4 }, { 1, 3 }, { 2, 2 }, { 3, 1 }, { 4, 0 } };

    static void bingo(boolean[][] board) {

        // width
        for (int i = 0; i < 5; i++) {
            if (!widthBingo[i]) {
                boolean isBingo = true;
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == false) {
                        isBingo = false;
                        break;
                    }
                }

                if (isBingo) {
                    bingoCount++;
                    widthBingo[i] = true;
                }
            }
        }

        // length
        for (int j = 0; j < 5; j++) {
            if (!lengthBingo[j]) {
                boolean isBingo = true;
                for (int i = 0; i < 5; i++) {
                    if (board[i][j] == false) {
                        isBingo = false;
                        break;
                    }
                }

                if (isBingo) {
                    bingoCount++;
                    lengthBingo[j] = true;
                }
            }
        }
        if (!leftDiagonalBingo) {
            boolean isBingo = true;
            for (int i = 0; i < 5; i++) {
                if(board[leftDiagonalPoint[i][0]][leftDiagonalPoint[i][1]] == false) {
                    isBingo = false;
                    break;
                }
            }

            if(isBingo) {
                leftDiagonalBingo=true;
                bingoCount++;
            }
        }

        if (!rightDiagonalBingo) {
            boolean isBingo = true;
            for (int i = 0; i < 5; i++) {
                if(board[rightDiagonalPoint[i][0]][rightDiagonalPoint[i][1]] == false) {
                    isBingo = false;
                    break;
                }
            }

            if(isBingo) {
                rightDiagonalBingo=true;
                bingoCount++;
            }
        }

    };


    static void bingoSearch(int[][] board, int data) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(answerBoard[i][j]) {
                    continue;
                }
                if(board[i][j] == data) {
                    answerBoard[i][j] = true;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        int[][] board = new int[5][5];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String[] input = reader.readLine().split(" ");

            for (int j = 0; j < 5; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }


        int answer = 0;
        for (int i = 0; i < 5; i++) {
            String[] input = reader.readLine().split(" ");

            for (int j = 0; j < 5; j++) {
                bingoSearch(board, Integer.parseInt(input[j]));
                if(bingoCount<3) {
                    answer++;
                    bingo(answerBoard);
                }
            }
        }

        System.out.println(answer);

    }
}
