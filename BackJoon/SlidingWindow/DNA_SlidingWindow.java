package BackJoon.SlidingWindow;
//https://www.acmicpc.net/problem/12891


import java.util.*;
import java.io.*;

public class DNA_SlidingWindow {
    static final Character[] type = { 'A', 'C', 'G', 'T' };
    static int[] requiredTypeLength = new int[4];

    static boolean canMakePassword(HashMap<Character, Integer> count) {
        for (int i = 0; i < type.length; i++) {
            if (count.getOrDefault(type[i], 0) < requiredTypeLength[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] lengthInfo = reader.readLine().split(" ");
        int dnaLength = Integer.parseInt(lengthInfo[0]);
        int passwordLength = Integer.parseInt(lengthInfo[1]);
        
        String dna = reader.readLine();
        String[] requiredLength = reader.readLine().split(" ");

        HashMap<Character, Integer> dnaSum = new HashMap<>();

        for (int i = 0; i < type.length; i++) {
            requiredTypeLength[i] = Integer.parseInt(requiredLength[i]);
        }

        for (int i = 0; i < passwordLength; i++) {
            dnaSum.put(dna.charAt(i), dnaSum.getOrDefault(dna.charAt(i), 0) + 1);
        }

        int result = canMakePassword(dnaSum) ? 1 : 0;

        for (int i = passwordLength; i < dnaLength; i++) {
            char firstSliding = dna.charAt(i - passwordLength);
            int firstSlidingCount = dnaSum.getOrDefault(firstSliding, 0);
            dnaSum.put(firstSliding, firstSlidingCount > 0 ? firstSlidingCount -1 : 0);
            char lastSliding = dna.charAt(i);
            int lastSlidingCount = dnaSum.getOrDefault(lastSliding, 0);
            dnaSum.put(lastSliding, lastSlidingCount + 1);

            if(canMakePassword(dnaSum)) {
                result++;
            }
        }

        System.out.println(result);
    }
}
