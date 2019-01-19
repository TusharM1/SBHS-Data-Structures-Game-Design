package Quarter_2.Map_Problems.Bowler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Bowler {

    public static void main(String[] args) {

        TreeMap<Integer, PriorityQueue<BowlerPlayer>> treeMap = new TreeMap<>();
        File name = new File("src/Quarter_2/BowlingData.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text;
            while ((text = input.readLine()) != null) {
                String firstName = text.substring(0, text.indexOf(" "));
                text = text.substring(text.indexOf(" ") + 1);

                String lastName = text.substring(0, text.indexOf(" "));
                text = text.substring(text.indexOf(" ") + 1);

                int score = Integer.parseInt(text);

                BowlerPlayer player = new BowlerPlayer(firstName, lastName, score);
                if (!treeMap.containsKey(score))
                    treeMap.put(score, new PriorityQueue<>());
                treeMap.get(score).add(player);
            }

            for (Integer key: treeMap.keySet()) {
                System.out.println(key);
                while (treeMap.get(key).size() > 0)
                    System.out.println("\t" + treeMap.get(key).poll());
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }

    }

}

class BowlerPlayer implements Comparable<BowlerPlayer> {

    public String firstName, lastName;
    public int score;

    public BowlerPlayer(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    @Override
    public int compareTo(BowlerPlayer o) {
        int result = this.lastName.compareTo(o.getLastName());
        if (result != 0)
            return result;
        return this.firstName.compareTo(o.getFirstName());
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }
}
