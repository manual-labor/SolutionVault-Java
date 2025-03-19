package KAKAO;
import java.util.*;

public class DollClaw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vectorLength = scanner.nextInt();
        int vector[][] = new int[vectorLength][vectorLength];
        for (int i = 0; i < vectorLength; i++)
            for (int j = 0; j < vectorLength; j++)
                vector[i][j] = scanner.nextInt();

        int clawLength = scanner.nextInt();
        int[] claw = new int[clawLength];
        for (int i = 0; i < clawLength; i++)
            claw[i] = scanner.nextInt();

        Stack<Integer> clawedDolls = new Stack<>();

        int answer = 0;
        for (int clawIndex = 0; clawIndex < clawLength; clawIndex++)
            for (int widthIndex = 0; widthIndex < vectorLength; widthIndex++) {
                if (vector[widthIndex][claw[clawIndex] - 1] != 0) {
                    if (!clawedDolls.isEmpty() && clawedDolls.peek() == vector[widthIndex][claw[clawIndex] - 1]) {
                        clawedDolls.pop();
                        answer += 2;
                    } else {
                        clawedDolls.push(vector[widthIndex][claw[clawIndex] - 1]);
                    }

                    vector[widthIndex][claw[clawIndex] - 1] = 0;
                    break;
                }
            }

        System.out.println(answer);
        return;
    }
}