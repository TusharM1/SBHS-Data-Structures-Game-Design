package Quarter_1.Q6_Calender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calender {

    public Calender() {
        File name = new File("src/Quarter_1/Q6_Calender/fileName.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String line;
            boolean isLeapYear;
            int dayOfWeek, month, numberOfDays;
            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 30};
            String[] daysInWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            while((line = input.readLine()) != null) {
                String[] numbers = line.split(" ");
                isLeapYear = Boolean.parseBoolean(numbers[0]);
                dayOfWeek = Integer.parseInt(numbers[1]);
                month = Integer.parseInt(numbers[2]);
                numberOfDays = Integer.parseInt(numbers[3]);

                System.out.printf("%d %d %d %d ", Integer.parseInt(numbers[0]), dayOfWeek, month, numberOfDays);
                int result = 0;
                for (int i = 0; i < month - 1; i++) {
                    result += daysInMonth[i];
                    if (i == 1 && isLeapYear)
                        result++;
                }
                result += (dayOfWeek - 1) % 7 + numberOfDays;
                result %= 7;
                System.out.println(daysInWeek[result - 1]);
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main(String[] args) {
        new Calender();
    }

}
