// https://www.acmicpc.net/problem/2110

package BackJoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SetupWifi_BinarySearch_2110 {
 
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = reader.readLine().split(" ");
        int houseCount = Integer.parseInt(input[0]);
        int wifiCount = Integer.parseInt(input[1]);

        int house[] = new int[houseCount];

        for(int i=0; i<houseCount; i++) {
            house[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(house);

        int left=0, right=house[houseCount-1] - left;
        int mid = (left+right)/2;
        int answer = 0;

        
        while(left<=right) {
            int setUpCount = 1;

            mid = (left+right)/2;
            int leftWifi = 0;
            for(int i=1; i<houseCount; i++) {
                if ((house[i] - house[leftWifi]) >= mid) {
                    leftWifi = i;
                    setUpCount++;
                }
            }

            if(setUpCount < wifiCount) {
                right = mid-1;
            } else {
                answer = mid;
                left = mid+1;
            }
        }

        System.out.println(answer);
        
    }
}
