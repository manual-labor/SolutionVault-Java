package BackJoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Set_String_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int length = Integer.parseInt(reader.readLine());

        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < length; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            String command = input.nextToken();

            
            int value = 0;


            switch (command) {
                case "add":
                    value = Integer.parseInt(input.nextToken());
                    hashSet.add(value);
                    break;
                case "remove":
                    value = Integer.parseInt(input.nextToken());
                    hashSet.remove(value);
                    break;
                case "check":
                    value = Integer.parseInt(input.nextToken());
                    writer.write(hashSet.contains(value) ? "1\n" : "0\n");
                    break;
                case "toggle":
                    value = Integer.parseInt(input.nextToken());
                    if (hashSet.contains(value)) {
                        hashSet.remove(value);
                    } else {
                        hashSet.add(value);
                    }
                    break;
                case "all":
                    for (int j = 1; j <= 20; j++) {
                        hashSet.add(j);
                    }
                    break;
                case "empty":
                    hashSet.clear();
                    break;
            }


        }

        writer.flush();
        writer.close();

    }
}
