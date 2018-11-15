package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeProgram extends JPanel implements KeyListener, MouseListener {

	JFrame frame;

	//declare an array to store the maze
	int x = 100, y = 100;

	boolean[][] board;
	ArrayList<String[]> array = new ArrayList<>();
	public MazeProgram() {

		setBoard();
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,800);
		frame.setVisible(true);
		frame.addKeyListener(this);
		//this.addMouseListener(this);

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		// this will set the background color
		g.setColor(Color.BLACK);

		g.fillRect(0,0,1000,800);

		// drawBoard here!
		g.setColor(Color.WHITE);
		g.drawRect(x, y,100,100);
		// x & y would be used to located your
		// playable character
		// values would be set below

		// other commands that might come in handy
//		g.setFont("Times New Roman", Font.PLAIN, 18);

		// you can also use Font.BOLD, Font.ITALIC, Font.BOLD|Font.Italic
//		g.drawOval(x, y, 10, 10);
//		g.fillRect(x, y, 100, 100);
//		g.fillOval(x, y, 10, 10);

	}

	public void setBoard() {

		//choose your maze design

		//pre-fill maze array here

//		File name = new File("maze0.txt");
//		int r = 0;
//		try {
//			BufferedReader input = new BufferedReader(new FileReader(name));
//			String text;
//			while((text = input.readLine()) != null) {
//				array.add(text.split(""));
				// your code goes in here to chop up the maze design
				// fill maze array with actual maze stored in text file
//			}
//		}
//		catch (IOException io) {
//			System.err.println("File error");
//		}
//		setWalls();

	}

//	public void setWalls() {

		// when you're ready for the 3D part
//		int[] c1X = {150, 550, 450, 250};
//		int[] c1Y = {50, 50, 100, 100};
//		Ceiling ceiling1 = new Polygon(c1X, c1Y, 4);
		// needs to be set as a global!

//	}

	@Override public void keyPressed(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}

	public static void main(String args[]) {new MazeProgram();}

}