package BackJoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class LongestIncreaseSequence_DP_11053 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Integer> treeSet = new TreeSet<>();

        int length = Integer.parseInt(reader.readLine());
        
        int[] array = new int[length];

        // i번째 항을 마지막으로 하는 길이
        int[] dp = new int[length];

        dp[0] = 1;

        String[] input = reader.readLine().split(" ");
        for(int i=0; i<length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }

        System.out.println(treeSet);
        for(int i=1; i<length; i++) {
            for(int j=0; j<)
        }
    }
}
