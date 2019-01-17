package Quarter_1.Q4_Diamond;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Diamond {

	public Diamond() {
		File name = new File("src/Quarter_1/Q4_Diamond/fileName.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while((text = input.readLine()) != null) {
				String[] numList = text.split(" ");
				int[] nums = new int[numList.length];
				for (int i = 0; i < numList.length; i++)
					nums[i] = Integer.parseInt(numList[i]);
				int rows = nums[0], columns = nums[1], sideLength = nums[2], startingRow = nums[3], startingColumn = nums[4];
				int size = 1, change = 2;
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						if (i < startingRow || i > startingRow + (2 * sideLength) - 1)
							System.out.print("o");
						else if (startingColumn - ((size - 1) / 2) <= j && startingColumn + ((size + 1) / 2) > j)
							System.out.print("x");
						else
							System.out.print("o");
					}
					if (i == startingRow + sideLength - 1)
						change *= -1;
					if (i >= startingRow && i < startingRow + (2 * sideLength))
						size += change;
					System.out.println();
				}
			}
		}
		catch (IOException io) {
			System.err.println("File does not exist");
		}
	}

	public static void main(String[] args) {
		new Diamond();
	}

}