//https://school.programmers.co.kr/learn/courses/30/lessons/86971

package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class ElectricityGrid_BFS {
    static ArrayList<ArrayList<Integer>> node;

    static int getNodeCountByBFS(int number, int omit) {
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(number);
        visited.add(number);
        
        int count = 1;
        while(!queue.isEmpty()) {
            int currentNode = queue.poll();
            for(int next: node.get(currentNode)) {
                if(!visited.contains(next) && next != omit){
                    queue.offer(next);
                    visited.add(next);
                    count++;
                }
            }
        }

        return count;
    }

    public int solution(int n, int[][] wires) {
        node = new ArrayList<ArrayList<Integer>>();

        
         for(int i=0; i<=n; i++) {
            node.add(new ArrayList<Integer>());
        }
        
        for(int wire[] : wires) {
            node.get(wire[0]).add(wire[1]);
            node.get(wire[1]).add(wire[0]);
        }
        
        int minCount = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++) {
            int count = getNodeCountByBFS(wires[i][0], wires[i][1]);
            minCount = Math.min(Math.abs(count - (n - count)),minCount);
        }
        
        
        
        return minCount;
    }
}
