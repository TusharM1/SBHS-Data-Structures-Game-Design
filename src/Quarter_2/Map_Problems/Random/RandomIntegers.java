package Quarter_2.Map_Problems.Random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RandomIntegers {

    public static void main(String[] args) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        File name = new File("src/Quarter_2/random_integers.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text;
            while ((text = input.readLine()) != null) {
                int number = Integer.parseInt(text);
                if (hashMap.containsKey(number))
                    hashMap.put(number, hashMap.get(number) + 1);
                else
                    hashMap.put(number, 1);
            }
            System.out.println(hashMap);
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

}
