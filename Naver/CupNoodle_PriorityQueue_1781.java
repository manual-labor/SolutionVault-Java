package Naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Problem implements Comparable<Problem> {
    int number;
    int deadLine;
    int cupNoodle;

    public Problem (int number, int deadLine, int cupNoodle) {
        this.number = number;
        this.deadLine = deadLine;
        this.cupNoodle = cupNoodle;
    }

    @Override
    public int compareTo(Problem other) {
        return this.deadLine - other.deadLine;
    }
}

public class CupNoodle_PriorityQueue_1781 {

    public static void main(String[] args) throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int questionCount = Integer.parseInt(reader.readLine());
        

        PriorityQueue<Problem> queue = new PriorityQueue<>();
        PriorityQueue<Integer> cupCoutHeap = new PriorityQueue<>();
        for(int i=1; i<=questionCount; i++) {
            String[] input = reader.readLine().split(" ");
            queue.offer(new Problem(i,Integer.parseInt(input[0]),Integer.parseInt(input[1])));
        }

        int answer = 0;
        while(!queue.isEmpty()) {
           Problem currentProblem = queue.poll();
           System.out.println(currentProblem.cupNoodle);
           cupCoutHeap.offer(currentProblem.cupNoodle);

           if(currentProblem.deadLine < cupCoutHeap.size()) {
               cupCoutHeap.poll();
           }
        }

        while(!cupCoutHeap.isEmpty())
            answer += cupCoutHeap.poll();
        
        System.out.println(answer);

    }
}
