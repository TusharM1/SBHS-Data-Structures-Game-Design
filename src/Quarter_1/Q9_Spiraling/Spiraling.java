package Quarter_1.Q9_Spiraling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Spiraling {

    public Spiraling() {
        File name = new File("src/Quarter_1/Q9_Spiraling/fileName.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name));
            String input;
            int sideLength;
            char matrix[][];
            while ((input = bufferedReader.readLine()) != null) {
                sideLength = Integer.parseInt(input);
                matrix = new char[sideLength][sideLength];
                matrix[0][0] = '*';
                if (sideLength > 1) {
                    for (int i = 0; i < matrix.length; i++)
                        for (int j = 0; j < matrix[0].length; j++)
                            matrix[i][j] = '-';
                    for (int layer = 0, width = sideLength; layer < (sideLength / 2) + sideLength % 2; layer += 2, width -= 4) {
                        for (int i = 0; i < width; i++)
                            matrix[layer][i + (sideLength - width) / 2] = matrix[sideLength - layer - 1][i + (sideLength - width) / 2] =
                                    matrix[i + (sideLength - width) / 2][layer] = matrix[i + (sideLength - width) / 2][sideLength - layer - 1] = '*';
                        if (layer != 0)
                            matrix[layer][((sideLength - width) / 2) - 1] = '*';
                        matrix[((sideLength - width) / 2) + 1][layer] = '-';
                    }
                }
                for (int i = 0; i < sideLength; i++) {
                    for (int j = 0; j < sideLength; j++)
                        System.out.print(matrix[i][j] + " ");
                    System.out.println();
                }
                System.out.println();
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main(String[] args) {
        new Spiraling();
    }
    
}
