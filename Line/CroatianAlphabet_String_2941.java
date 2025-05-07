// 

package Line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CroatianAlphabet_String_2941 {
 
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        
        String result = input.replaceAll("c=|c-|dz=|d-|lj|nj|s=|z=","a");

        System.out.println(result.length());
       
    }
}
