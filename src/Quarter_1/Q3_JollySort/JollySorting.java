package Quarter_1.Q3_JollySort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class JollySorting {

	public JollySorting() {

		File file = new File("src/Quarter_1/Q3_JollySort/fileName.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			String line;
			while ((line = input.readLine()) != null) {
				String[] numList = line.split(" ");
				ArrayList<Integer> num = new ArrayList<>();
				for (String s : numList)
					num.add(Integer.parseInt(s));
				Collections.sort(num);
				if (num.size() > 2)
					for (int i = 2; i < num.size(); i+=2) {
						int current = num.get(i - 1), next = num.get(i);
						num.set(i, current);
						num.set(i - 1, next);
					}
				System.out.println(num);
			}
		}
		catch (Exception e) {
			System.err.println("File does not exist.");
		}

	}

	public static void main(String[] args) { new JollySorting(); }

}