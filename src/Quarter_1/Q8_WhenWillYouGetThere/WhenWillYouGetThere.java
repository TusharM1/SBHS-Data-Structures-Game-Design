package Quarter_1.Q8_WhenWillYouGetThere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WhenWillYouGetThere {

    public WhenWillYouGetThere() {
        File name = new File("src/Quarter_1/Q8_WhenWillYouGetThere/fileName.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name));
            String input;
            int count = 1;
            while((input = bufferedReader.readLine()) != null) {
                String[] numList = input.split(" ");
                int[] nums = new int[numList.length];
                for (int i = 0; i < numList.length; i++)
                    nums[i] = Integer.parseInt(numList[i]);
                GregorianCalendar date = new GregorianCalendar(2018, 9, 18, 10, 26, 0);
                System.out.printf("Trip %d: \n", count);
                System.out.printf("\tDeparture Date and Time: %d:%d %s on %d/%d/%d\n",
                        date.get(Calendar.HOUR), date.get(Calendar.MINUTE), date.get(Calendar.AM_PM) == 0 ? "AM" : "PM",
                        date.get(Calendar.MONTH), date.get(Calendar.DATE), date.get(Calendar.YEAR));
                date.add((Calendar.DATE), nums[0]);
                date.add((Calendar.HOUR), nums[1]);
                date.add((Calendar.MINUTE), nums[2]);
                System.out.printf("\tArrival Date and Time: %d:%d %s on %d/%d/%d\n\n",
                        date.get(Calendar.HOUR), date.get(Calendar.MINUTE), date.get(Calendar.AM_PM) == 0 ? "AM" : "PM",
                        date.get(Calendar.MONTH), date.get(Calendar.DATE), date.get(Calendar.YEAR));
                count++;
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main(String[] args) {
        new WhenWillYouGetThere();
    }

}
