package Quarter_1.Q7_IndexCards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IndexCards {

    public IndexCards() {
        File name = new File("src/Quarter_1/Q7_IndexCards/fileName.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String line;
            int card, target, count = 1;
            while((line = input.readLine()) != null) {
                String[] numbers = line.split(" ");
                card = Integer.parseInt(numbers[0]);
                target = Integer.parseInt(numbers[1]);

                int closest = 0;
                int[] arr = {card, card / 10 + card % 10, card / 1000 + card % 1000, card / 100 + card % 100,
                                card / 1000 + (card / 100) % 10 + card % 100, card / 100 + (card / 10) % 10 + card % 10,
                                card / 1000 + (card / 10) % 100 + card % 10, card / 1000 + (card / 100) % 10 + (card / 10) % 100 + card % 1000};
                for (int currentNumber: arr)
                    if (currentNumber <= target && currentNumber > closest)
                        closest = currentNumber;
                System.out.printf("Output #%d: %d\n", count, closest);
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }

    }

    public static void main(String[] args) {
        new IndexCards();
    }

}
