package BackJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMap_Sort_20920 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        int wordsLength = Integer.parseInt(input[0]);
        int memoryLength = Integer.parseInt(input[1]);

        HashMap<String, Integer> words = new HashMap<>();

        for(int i=0; i<wordsLength; i++) {
            String word = reader.readLine();
            if(word.length() >= memoryLength)
                words.put(word, words.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> wordsList = new ArrayList<>(words.entrySet());

        wordsList.sort((o1, o2)-> {
            if(o2.getValue() == o1.getValue()) {
                if(o2.getKey().length() == o1.getKey().length()) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getKey().length() - o1.getKey().length();
            }
            return o2.getValue() - o1.getValue();
        });


        for(int i=0; i<wordsList.size(); i++) {
            writer.append(wordsList.get(i).getKey()+"\n");
        }

        writer.flush();
    }
}
