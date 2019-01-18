package Quarter_1.Q1_ThreeSets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ThreeSets {

	public ThreeSets() {
		File name = new File("src/Quarter_1/Q1_ThreeSets/fileName.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String input1, input2, input3;
			int setCount = 1;
			while((input1 = input.readLine()) != null) {
				input2 = input.readLine();
				input3 = input.readLine();

				ArrayList<String> set1 = new ArrayList<>(Arrays.asList(input1.split(" ")));
				ArrayList<String> set2 = new ArrayList<>(Arrays.asList(input2.split(" ")));
				ArrayList<String> set3 = new ArrayList<>(Arrays.asList(input3.split(" ")));

				ArrayList<Integer> returnArray = new ArrayList<>();
				for (String s : set1)
					if (set2.contains(s) && set3.contains(s))
						returnArray.add(Integer.parseInt(s));
				Collections.sort(returnArray);
				String format = returnArray.toString();
				format = format.replace('[', '{');
				format = format.replace(']', '}');

				System.out.println("MySet " + setCount + " intersection = " + format);
				setCount++;
			}
		}
		catch (IOException io) {
			System.err.println("File does not exist");
		}
	}

	public static void main(String[] args) {
		new ThreeSets();
	}

}