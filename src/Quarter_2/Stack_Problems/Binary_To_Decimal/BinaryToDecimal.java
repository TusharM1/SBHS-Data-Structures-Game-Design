package Quarter_2.Stack_Problems.Binary_To_Decimal;

import java.util.Stack;

public class BinaryToDecimal {

    public static void main(String[] args) {

        int test = 100;

        Stack<Integer> lifo = new Stack<>();

        while (test > 0) {
            if (test % 2 == 0)
                lifo.push(0);
            else
                lifo.push(1);
            test /= 2;
        }

        while (!lifo.isEmpty())
            System.out.print(lifo.pop());
        System.out.println();
        
    }

}
