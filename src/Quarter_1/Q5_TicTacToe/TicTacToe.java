package Quarter_1.Q5_TicTacToe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TicTacToe {

    public TicTacToe() {
        File name = new File("src/Quarter_1/Q5_TicTacToe/fileName.txt");
        try {
            BufferedReader input = new BufferedReader(new FileReader(name));
            String line1, line2, line3;
            String[][] board = new String[3][3];
            while((line1 = input.readLine()) != null) {
                line2 = input.readLine();
                line3 = input.readLine();

                board[0] = line1.split(" ");
                board[1] = line2.split(" ");
                board[2] = line3.split(" ");

                System.out.println(findMatch(board));
            }
        }
        catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    String findMatch(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("-"))
                return board[0][i];
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("-"))
                return board[i][0];
        }
        if (((board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) ||
                (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])))
                && (!board[1][1].equals("-")))
            return board[1][1];
        return "-";
    }

    public static void main(String[] args) {
        new TicTacToe();
    }

}
