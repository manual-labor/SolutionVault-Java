package BackJoon;

import java.io.*;
import java.util.*;


public class BiggerNumber_2075 {
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        int boardLength = Integer.parseInt(reader.readLine());

        for(int i=0; i<boardLength; i++) {
           String[] inputLine =  reader.readLine().split(" ");
           for(int j=0; j<boardLength; j++) {
            priorityQueue.offer(Integer.parseInt(inputLine[j]));
           }
        }

        int answer = 0;
        for(int i=0; i<boardLength; i++)
           answer = priorityQueue.poll();

        System.out.println(answer);
    }
}
