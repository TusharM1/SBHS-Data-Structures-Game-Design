package Quarter_2.Stack_Problems.Reverse_String;

import java.util.Stack;

public class ReverseString {

    public static void main(String[] args) {

        String test = "Hello";

        Stack<Character> lifo = new Stack<>();

        while (test.length() > 0) {
            lifo.push(test.charAt(0));
            test = test.substring(1);
        }

        while (!lifo.isEmpty())
            System.out.print(lifo.pop());
        System.out.println();

    }
}
