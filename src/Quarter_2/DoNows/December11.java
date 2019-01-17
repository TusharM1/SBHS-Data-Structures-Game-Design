package Quarter_2.DoNows;

import java.util.ArrayList;
import java.util.Collections;

public class December11 {

    static ArrayList<Integer> random;
    public static void main(String[] args) {
        random = new ArrayList<>();
        for (int i = 0, r; i < 1000; i++) {
            r = (int) (Math.random() * 100 + 1);
            if (!contains(r))
                random.add(r);
        }
        Collections.sort(random);
        System.out.println(random);
    }

    private static boolean contains(int r) {
        for (Integer i : random)
            if (r == i)
                return true;
        return false;
    }

}
