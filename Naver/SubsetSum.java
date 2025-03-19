package Naver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SubsetSum {

    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
       String[] input = reader.readLine().split(" ");
       int length = Integer.parseInt(input[0]);
       int commandLength = Integer.parseInt(input[1]);

       int[] dp = new int[length+1];

       String[] array =  reader.readLine().split(" ");
       
       for(int i=1; i<=length; i++) {
        dp[i] = dp[i-1] + Integer.parseInt(array[i-1]);
       }
       for(int i=0; i<commandLength; i++) {
       String[] command = reader.readLine().split(" ");
        int startIndex = Integer.parseInt(command[0]);
        int endIndex = Integer.parseInt(command[1]);
        writer.append((dp[endIndex] - dp[startIndex-1])+"\n");
       }

       writer.flush();
    }
}
