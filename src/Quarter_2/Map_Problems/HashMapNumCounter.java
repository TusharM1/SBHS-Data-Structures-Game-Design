package Quarter_2.Map_Problems;

import java.util.HashMap;

public class HashMapNumCounter {

	public static void main(String[] args) {

		HashMap<Integer, Integer> numMap = new HashMap<>();
		for (int x = 0; x < 10; x++) {
			int num = (int) (Math.random() * 6) + 1;
			if (!numMap.containsKey(num))
				numMap.put(num, 0);
			numMap.put(num, numMap.get(num) + 1);
		}
		System.out.println(numMap);

	}

}