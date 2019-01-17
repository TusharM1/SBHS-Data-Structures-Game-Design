package Quarter_2.Queue_Problems.Word_Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class WordReader {

    public static void main(String[] args) {

        LinkedList<Word> linkedList = new LinkedList<>();
        PriorityQueue<Word> priorityQueue = new PriorityQueue<>();

        File name = new File("src/Quarter_2/Queue_Problems/Word_Reader/SampleText.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String text;
            while ((text = input.readLine()) != null) {
                String[] words = text.split(" ");
                for (String s: words) {
                    Word word = new Word(s);
                    linkedList.add(word);
                    priorityQueue.add(word);
                }
            }
            for (int i = 0; i < linkedList.size();)
                System.out.printf("%-16s %s\n", linkedList.pop(), priorityQueue.poll());
        } catch (IOException io) {
            System.err.println("File does not exist");
        }

    }

}

class Word implements Comparable {

    String word;

    public Word(String s) {
        word = s;
    }

    @Override
    public int compareTo(Object o) {
        return -word.compareTo(o.toString());
    }

    @Override
    public String toString() {
        return word;
    }
}
