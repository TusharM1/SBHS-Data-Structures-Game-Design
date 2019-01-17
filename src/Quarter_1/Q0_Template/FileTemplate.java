package Quarter_1.Q0_Template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTemplate {

	public static void main(String[] args) {
		File name = new File("fileName.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while ((text = input.readLine()) != null)
				System.out.println(text);
		}
		catch (IOException io) {
			System.err.println("File does not exist");
		}
	}

}
