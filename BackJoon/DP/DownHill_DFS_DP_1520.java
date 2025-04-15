// https://www.acmicpc.net/problem/1520

package BackJoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DownHill_DFS_DP_1520 {
    static int[] dy = { 0, 1, 0 };
    static int[] dx = { 1, 0, -1 };
    static int[][] board;
    static int[][] dp;
    static int height, width;

    static void dfs(int x, int y) {
        if (dp[x][y] != -1) {
        } else {
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(nextX >= 0 && nextX < width && nextY >= 0 && nextY < height) {
                    dp[x][y] += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] length = reader.readLine().split(" ");

        height = Integer.parseInt(length[0]);
        width = Integer.parseInt(length[1]);

        board = new int[height][width];
        dp = new int[height + 1][width + 1];

        for (int i = 0; i < height; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < width; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

    }
}
