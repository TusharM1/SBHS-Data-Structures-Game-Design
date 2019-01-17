package Quarter_1.Q2_LucasNumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class LucasNumbers {

	public LucasNumbers() {
		File name = new File("src/Quarter_1/Q2_LucasNumbers/fileName.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String num;
			while((num = input.readLine()) != null) {
				int count = Integer.parseInt(num) + 1;
				BigInteger a = BigInteger.valueOf(2), b = BigInteger.valueOf(1), c = BigInteger.valueOf(3);
				for (int i = 0; i < count; i++) {
					if (i == 0) {
						c = a;
						continue;
					}
					if (i == 1) {
						c = b;
						continue;
					}
					c = a.add(b);
					a = b;
					b = c;
				}
				System.out.println(c);
			}
		}
		catch (IOException io) {
			System.err.println("File does not exist");
		}
	}

	public static void main(String[] args) {
		new LucasNumbers();
	}

}