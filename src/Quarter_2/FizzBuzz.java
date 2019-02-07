package Quarter_2;

public class FizzBuzz {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            String result = "";
            if (i % 5 == 0)
                result += "Fizz";
            if (i % 3 == 0)
                result += "Buzz";
            if (result.equals(""))
                result = String.valueOf(i);
            System.out.println(result);

        }

    }

}
