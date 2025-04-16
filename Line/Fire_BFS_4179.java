// https://www.acmicpc.net/problem/4179

package Line;

import java.util.*;
import java.io.*;

class Point implements Comparable<Point> {
    int objectName;
    int time;
    int x;
    int y;

    public Point(int objectName, int time, int x, int y) {
        this.objectName = objectName;
        this.time = time;
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Point other) {
        if(other.time == this.time)
            return this.objectName - other.objectName;
        return this.time - other.time;
    }
}

public class Fire_BFS_4179 {
    public static void main(String[] args) throws IOException {
        final int WALL = 0;
        final int VOID = 1;
        final int FIRE = 2;
        final int HUMAN = 3;

        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };

        int[][] board;

        PriorityQueue<Point> canMoveObjectQueue = new PriorityQueue<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int length = Integer.parseInt(input[0]);
        int width = Integer.parseInt(input[1]);

        board = new int[length][width];

        int answer=0;

        for (int i = 0; i < length; i++) {
            String lineInput = reader.readLine();
            for (int j = 0; j < width; j++) {
                int objectName = 0;
                switch (lineInput.charAt(j)) {
                    case '#':
                        objectName = WALL;
                        break;
                    case 'J':
                        objectName = HUMAN;
                        break;
                    case 'F':
                        objectName = FIRE;
                        break;
                    case '.':
                        objectName = VOID;
                        break;
                }

                board[i][j] = objectName;

                if (objectName == FIRE || objectName == HUMAN) {
                    canMoveObjectQueue.offer(new Point(objectName, 1, j, i));
                }

                if(objectName == HUMAN) {
                    if(j==0|| j==width-1 || i==length-1|| i==0) {
                        answer = 1;
                    }
                }
            }
        }

        if(answer!=0) {
            System.out.println(1);
            return;
        }

        int time=1;
        while (!canMoveObjectQueue.isEmpty()) {
            int queueSize = canMoveObjectQueue.size();
            for (int count = 0; count < queueSize; count++) {
                Point currentPoint = canMoveObjectQueue.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = currentPoint.x + dx[i];
                    int nextY = currentPoint.y + dy[i];

                    if (nextX >= 0 && nextX < width && nextY >= 0 && nextY < length && board[nextY][nextX] != WALL) {
                        if (currentPoint.objectName == FIRE && board[nextY][nextX] != FIRE) {
                            board[nextY][nextX] = FIRE;
                            canMoveObjectQueue.offer(new Point(currentPoint.objectName, currentPoint.time+1, nextX, nextY));
                        }
                        
                        if (currentPoint.objectName == HUMAN && board[nextY][nextX] == VOID) {
                            if (nextX == 0 || nextX == width - 1 || nextY == 0 || nextY == length - 1) {
                                time++;
                                System.out.println(currentPoint.time+1);
                                return;
                            }
                            board[nextY][nextX] = HUMAN;
                            canMoveObjectQueue.offer(new Point(currentPoint.objectName,currentPoint.time+1, nextX, nextY));
                        }

                    }
                }
            }
            time++;
        }

        System.out.println("IMPOSSIBLE");

    }
}
