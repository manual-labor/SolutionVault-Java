// https://www.acmicpc.net/problem/14719

package BackJoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rain_Array_14719 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] length = reader.readLine().split(" ");

        int height = Integer.parseInt(length[0]);
        int width = Integer.parseInt(length[1]);

        int[] wall = new int[width];

        String[] input = reader.readLine().split(" ");

        for (int i = 0; i < width; i++) {
            wall[i] = Integer.parseInt(input[i]);
        }

        int totalRain = 0;

        for (int i=1; i<width-1; i++) {
            int leftMax = 0, rightMax = 0;
            for(int left=0; left< i; left++) {
                leftMax= Math.max(leftMax, wall[left]);
            }
            for(int right=width-1; right > i; right-- ) {
                rightMax = Math.max(rightMax, wall[right]);
            }

            int canRain = Math.min(leftMax , rightMax);

            if(canRain <= wall[i]) {
                continue;
            }
            
            totalRain += canRain - wall[i];

        }
        

        System.out.println(totalRain);

    }
}
