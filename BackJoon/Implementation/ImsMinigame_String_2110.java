package BackJoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class ImsMinigame_String_2110 {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int length = Integer.parseInt(input[0]);
        String game = input[1];
        
        int needPlayer = 2;

        if (game.equals("Y")) {
            needPlayer = 2;
        } else if (game.equals("F")) {
            needPlayer = 3;
        } else if (game.equals("O")) {
            needPlayer = 4;
        }

        HashSet<String> alreadyPlayed = new HashSet<>();
        
        int readyPlayer = 1;
        int answer = 0;
        for(int i=0; i<length; i++) {
            String player = reader.readLine();
            if(alreadyPlayed.contains(player))
                continue;
            alreadyPlayed.add(player);
            readyPlayer++;

            if(readyPlayer == needPlayer) {
                answer++;
                readyPlayer = 1;
            }
        }

        System.out.println(answer);
    }
}