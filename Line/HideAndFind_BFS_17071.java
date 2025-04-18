// https://www.acmicpc.net/problem/17071

package Line;

import java.util.*;
import java.io.*;
public class HideAndFind_BFS_17071 {

    static boolean isOverRange(int location) {
        if(location >= 0 && location <= 500000)
            return false;
        else return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine();
        int subin = Integer.parseInt(input[0]);
        int brother = Integer.parseInt(input[1]);

        HashSet<Integer> alreadyReached = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();

        
        queue.offer(subin);

        int time = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int location = queue.poll();

                if (location == brother) {
                    System.out.println(time);
                    break;
                }
                int forwardWalk = location + 1;
                int backwardWalk = location - 1;
                int warp = location * 2;
                if (!isOverRange(forwardWalk))
                    queue.offer(forwardWalk);

                if (location > 0)
                    queue.offer(backwardWalk);

                if (!isOverRange(warp))
                    queue.offer(warp);

            }
            time++;
            brother+=time;

        }

    }
}
