package Naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubTotal_TwoPointer_2230 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = reader.readLine().split(" ");
        int length = Integer.parseInt(input[0]);
        int sumAboveLimit = Integer.parseInt(input[1]);
        
        String[] inputArray = reader.readLine().split(" ");
        int[] array = new int[length];
            
        for(int i=0; i<length; i++) {
           array[i] = Integer.parseInt(inputArray[i]);
        }
        int  left = 0, right=0, sum=0;
        int minimumLength = Integer.MAX_VALUE;
        
        while(left<=right && right<=length) {
            if(sum < sumAboveLimit) {
                sum+=array[right];
                right++;
            } else if (sum >= sumAboveLimit) {
                minimumLength = Math.min(minimumLength, right - left);
                sum-=array[left];
                left++;
            }
        } 
    

        if(Integer.MAX_VALUE == minimumLength) System.out.println(0);
        else System.out.println(minimumLength);
    }
}
