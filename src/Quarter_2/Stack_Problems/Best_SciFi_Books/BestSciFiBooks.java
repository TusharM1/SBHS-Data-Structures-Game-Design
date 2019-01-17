package Quarter_2.Stack_Problems.Best_SciFi_Books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BestSciFiBooks {

    public static void main(String[] args) {

        File name = new File("src/Quarter_2/Stack_Problems/Best_SciFi_Books/100BestSciFi21stCentury.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String inputs;
            int count = 1;
            Stack<Book> lifo = new Stack<>();
            String author = "", bookTitle = "";
            double rating = 0;
            int numberOfRatings = 0, score = 0, numberOfVotes = 0;
            while((inputs = input.readLine()) != null) {
                if (count % 7 == 3)
                    bookTitle = inputs.substring(1);
                if (count % 7 == 4)
                    author = inputs.substring(3);
                if (count % 7 == 5) {
                    rating = Double.parseDouble((inputs.substring(0, 4)).replace(",", ""));
                    numberOfRatings = Integer.parseInt(inputs.substring(18, inputs.indexOf("ratings") - 1).replace(",", ""));
                }
                if (count % 7 == 6) {
                    score = Integer.parseInt(inputs.substring(7, inputs.indexOf(", and ")).replace(",", ""));
                    numberOfVotes = Integer.parseInt(inputs.substring(inputs.indexOf(", and ") + 6, inputs.indexOf(" people voted")).replace(",", ""));
                }
                if (count % 7 == 0)
                    lifo.push(new Book(author, bookTitle, rating, numberOfRatings, score, numberOfVotes));
                count++;
            }
            while (!lifo.isEmpty()) {
                Book book = lifo.pop();
                String firstName = book.getAuthor().substring(0, book.getAuthor().indexOf(" ")), lastName = book.getAuthor().substring(book.getAuthor().lastIndexOf(" ") + 1);
                if (firstName.charAt(0) == 'E' || firstName.charAt(0) == 'H' || firstName.charAt(0) == 'P' || firstName.charAt(0) == 'S' ||
                        lastName.charAt(0) == 'E' || lastName.charAt(0) == 'H' || lastName.charAt(0) == 'P' || lastName.charAt(0) == 'S')
                    System.out.printf("%s %s - %s; %f\n", lastName, firstName, book.getBookTitle(), book.getRating());
            }
            System.out.println();
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }

    }

}

class Book {

    private String author, bookTitle;
    private double rating;
    private int numberOfRatings, score, numberOfVotes;

    public Book (String author, String bookTitle, double rating, int numberOfRatings, int score, int numberOfVotes) {
        this.author = author;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.score = score;
        this.numberOfVotes = numberOfVotes;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public double getRating() {
        return rating;
    }

}