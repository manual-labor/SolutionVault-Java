package Programmers.BFS;

import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public int solution(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();

        int[][] distance = new int[maps.length][maps[0].length];
        boolean[][] isVisited = new boolean[maps.length][maps[0].length];

        queue.offer(new Point(0, 0));
        isVisited[0][0] = true;
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = currentPoint.x + dx[i];
                int nextY = currentPoint.y + dy[i];

                if (nextX >= 0 && nextX < maps[0].length &&
                        nextY >= 0 && nextY < maps.length &&
                        !isVisited[nextY][nextX] &&
                        maps[nextY][nextX] == 1) {

                    isVisited[nextY][nextX] = true;
                    distance[nextY][nextX] = distance[currentPoint.y][currentPoint.x] + 1;

                    if (nextX == maps[0].length - 1 && nextY == maps.length - 1) {
                        return distance[nextY][nextX];
                    }
                    queue.offer(new Point(nextX, nextY));
                }
            }
        }
        return -1;
    }
}