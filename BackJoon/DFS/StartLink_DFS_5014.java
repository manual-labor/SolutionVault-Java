package BackJoon.DFS;

import java.io.IOException;
import java.util.*;

public class StartLink_DFS_5014 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int totalFloor = scanner.nextInt();
        int startFloor = scanner.nextInt();
        int targetFloor = scanner.nextInt();
        int upButton = scanner.nextInt();
        int downButton = scanner.nextInt();

        LinkedList<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> alreadyReached = new HashMap<>();

        queue.offer(startFloor);
        alreadyReached.put(startFloor, 0);

        while (!queue.isEmpty()) {
            int currentFloor = queue.poll();

            if (currentFloor == targetFloor) {
                System.out.println(alreadyReached.get(currentFloor));
                return;
            } 
            if ((currentFloor + upButton) >= 1 && (currentFloor + upButton) <= totalFloor && !alreadyReached.containsKey(currentFloor + upButton)) {
                queue.offer(currentFloor + upButton);
                alreadyReached.put(currentFloor + upButton, alreadyReached.get(currentFloor) + 1);
            }
            if ((currentFloor - downButton) >= 1 && (currentFloor - upButton) <= totalFloor && !alreadyReached.containsKey(currentFloor - downButton)) {
                queue.offer(currentFloor - downButton);
                alreadyReached.put(currentFloor - downButton, alreadyReached.get(currentFloor) + 1);
            }
        }
            System.out.println("use the stairs");
    }
}
