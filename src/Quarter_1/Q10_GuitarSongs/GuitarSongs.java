package Quarter_1.Q10_GuitarSongs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GuitarSongs {

    public GuitarSongs() {
        File name = new File("src/Quarter_1/Q10_GuitarSongs/fileName.txt");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name));
            String input;
            String[][] notes = {{"E",   "A",    "D",    "G",    "B",    "E"},
                                {"F",   "A#",   "D#",   "G#",   "C",    "F"},
                                {"F#",  "B",    "E",    "A",    "C#",   "F#"},
                                {"G",   "C",    "F",    "A#",   "D",    "G"},
                                {"G#",  "C#",   "F#",   "B",    "D#",   "G#"}};
            char[][][] fingerPositions = new char[0][][];
            if ((input = bufferedReader.readLine()) != null) {
                String[] temp = input.split(",");
                fingerPositions = new char[temp.length][5][6];
                for (int i = 0; i < fingerPositions[0][0].length; i++) {
                    for (int j = 0; j < fingerPositions.length; j++)
                        fingerPositions[j][i] = temp[j].toCharArray();
                    if ((input = bufferedReader.readLine()) == null)
                        break;
                    temp = input.split(",");
                }
            }

            char[][] output = new char[notes.length * notes[0].length][fingerPositions.length];
            for (int i = 0; i < fingerPositions.length; i++) {
                for (int j = 0, index = 0; j < fingerPositions[0][0].length; j++) {
                    for (int k = 0; k < fingerPositions[0].length; k++) {
                        char c = fingerPositions[i][k][j];
                        if (c == '*' || c == 'o')
                            output[output.length - index - 1][i] = 'O';
                        else
                            output[output.length - index - 1][i] = '-';
                        index++;
                    }
                }
            }

            System.out.print("Measure \t");
            for (int i = 1; i <= fingerPositions.length; i++)
                System.out.print(i + "\t");
            System.out.println();
            for (int i = notes[0].length, index = 0; i > 0; i--) {
                for (int j = notes.length; j > 0; j--) {
                    if (index == 9) {
                        for (int k = 0; k < fingerPositions.length; k++)
                            if (output[index][k] == '-')
                                output[index][k] = output[index + 1][k];
                    }
                    if (index == 10) {
                        index++;
                        continue;
                    }
                    System.out.print("\t" + notes[j - 1][i - 1] + "\t\t");
                    for (int k = 0; k < fingerPositions.length; k++)
                        System.out.print(output[index][k] + "\t");
                    index++;
                    System.out.println();
                }
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main(String[] args) {
        new GuitarSongs();
    }
    
}
