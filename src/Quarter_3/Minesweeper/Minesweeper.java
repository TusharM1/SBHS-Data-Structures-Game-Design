package Quarter_3.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Minesweeper extends JPanel {

	int rows, columns;

	int bombNumber = 10;

	JPanel panel;

	JToggleButton[][] buttonGrid;
	int[][] grid;

	HashSet<ArrayList<Integer>> bombs;

	ImageIcon[] imageList;

	JFrame frame;
	String themeName;

	public Minesweeper() {
		// 9x9:10, 16x16:40, 16x30:99
		rows = 9;
		columns = 9;
		themeName = "original";

		frame = new JFrame("Minesweeper");
		frame.add(this);

		createBoard();

		frame.add(panel, BorderLayout.CENTER);

		JPanel options = new JPanel();
		JMenuBar menuBar = new JMenuBar();

		JMenu difficulty = new JMenu("Difficulty");

		JMenuItem beginner = new JMenuItem("Beginner");
		beginner.addActionListener(e -> {
			rows = columns = 9;
			bombNumber = 10;
			createBoard();
		});
		JMenuItem intermediate = new JMenuItem("Intermediate");
		intermediate.addActionListener(e -> {
			rows = columns = 16;
			bombNumber = 40;
			createBoard();
		});
		JMenuItem expert = new JMenuItem("Expert");
		expert.addActionListener(e -> {
			rows = 16;
			columns = 30;
			bombNumber = 40;
			createBoard();
		});

		difficulty.add(beginner);
		difficulty.add(intermediate);
		difficulty.add(expert);

		menuBar.add(difficulty);

		JMenu theme = new JMenu("Theme");
		JMenuItem original = new JMenuItem("Original");
		original.addActionListener(e -> {
			themeName = "original";
			ImageIcon bomb = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/bomb.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			ImageIcon facingDown = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/facingDown.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			ImageIcon flagged = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/flagged.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			for (int i = 0; i < columns; i++)
				for (int j = 0; j < rows; j++)
					if (buttonGrid[j][i].getIcon().equals(imageList[9]))
						buttonGrid[j][i].setIcon(bomb);
					else if (buttonGrid[j][i].getIcon().equals(imageList[10]))
						buttonGrid[j][i].setIcon(facingDown);
					else if (buttonGrid[j][i].getIcon().equals(imageList[11]))
						buttonGrid[j][i].setIcon(flagged);
			imageList[9] = bomb;
			imageList[10] = facingDown;
			imageList[11] = flagged;
		});

		JMenuItem modified = new JMenuItem("Modified");
		modified.addActionListener(e -> {
			themeName = "modified";
			ImageIcon bomb = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/bomb.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			ImageIcon facingDown = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/facingDown.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			ImageIcon flagged = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/flagged.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
			for (int i = 0; i < columns; i++)
				for (int j = 0; j < rows; j++)
					if (buttonGrid[j][i].getIcon().equals(imageList[9]))
						buttonGrid[j][i].setIcon(bomb);
					else if (buttonGrid[j][i].getIcon().equals(imageList[10]))
						buttonGrid[j][i].setIcon(facingDown);
					else if (buttonGrid[j][i].getIcon().equals(imageList[11]))
						buttonGrid[j][i].setIcon(flagged);
			imageList[9] = bomb;
			imageList[10] = facingDown;
			imageList[11] = flagged;
		});

		theme.add(original);
		theme.add(modified);

		menuBar.add(theme);

		JMenu instructions = new JMenu("Instructions");
		instructions.add(new JMenuItem("You are presented with a board of squares. \nSome squares contain mines (bombs), others don't. If you click on a square containing a bomb, you lose. \nIf you manage to click all the squares (without clicking on any bombs) you win."));

		menuBar.add(instructions);

		options.add(menuBar);

		frame.add(options, BorderLayout.NORTH);

		frame.setVisible(true);
	}

	int spacesLeft;

	void createBoard() {
		frame.setSize(columns * 50, rows * 50 + 100);

		buttonGrid = new JToggleButton[rows][columns];
		grid = new int[rows][columns];

		if (panel != null)
			frame.remove(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(rows, columns));

		frame.add(panel);

		imageList = new ImageIcon[12];
		for (int i = 0; i < 9; i++)
			imageList[i] = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + i + ".png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
		imageList[9] = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/bomb.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
		imageList[10] = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/facingDown.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));
		imageList[11] = new ImageIcon(new ImageIcon("src/Quarter_3/Minesweeper/Images/" + themeName + "/flagged.png").getImage().getScaledInstance(frame.getWidth() / (columns - 100), frame.getHeight() / rows, Image.SCALE_SMOOTH));

		buttonGrid = new JToggleButton[rows][columns];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++) {
				buttonGrid[i][j] = new JToggleButton();
				buttonGrid[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JToggleButton clickedButton = ((JToggleButton) e.getSource());
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								if (clickedButton.equals(buttonGrid[i][j])) {
									if (e.getButton() == MouseEvent.BUTTON1)
										onLeftClick(i, j);
									else if (e.getButton() == MouseEvent.BUTTON3)
										onRightClick(i, j);
									break;
								}
							}
						}
					}
				});
				ImageIcon mine = imageList[10];
				buttonGrid[i][j].setIcon(mine);
				panel.add(buttonGrid[i][j]);
			}
		bombs = null;
	}

	void initializeBoard(ArrayList<Integer> clickLocation) {
		// Add the click location to the spawning of bombs, then remove it later
		bombs = new HashSet<>();
		spacesLeft = rows * columns;
		do {
			int row = (int) (Math.random() * rows), column = (int) (Math.random() * columns);
			if (Math.abs(clickLocation.get(0) - row) > 1 && Math.abs(clickLocation.get(1) - column) > 1)
				bombs.add(new ArrayList<>(Arrays.asList(row, column)));
		} while (bombs.size() < bombNumber);
		for (ArrayList<Integer> bomb : bombs)
			grid[bomb.get(0)][bomb.get(1)] = 9;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j] != 9) {
					int localBombCount = 0;
					for (int k = Math.max(i - 1, 0); k <= Math.min(i + 1, rows - 1); k++)
						for (int l = Math.max(j - 1, 0); l <= Math.min(j + 1, columns - 1); l++)
							if ((i != k || j != l) && grid[k][l] == 9)
								localBombCount++;
					grid[i][j] = localBombCount;
				}
				if (grid[i][j] == 0)
					System.out.print("- ");
				else if (grid[i][j] == 9)
					System.out.print("X ");
				else
					System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void onLeftClick(int row, int column) {
		// If the button was already clicked, do nothing
		if (buttonGrid[row][column].getIcon().equals(imageList[10])) {
			// Create an arraylist for the row and column clicked
			ArrayList<Integer> clickLocation = new ArrayList<>(Arrays.asList(row, column));
			// initialize board if bombs havent been placed already
			if (bombs == null)
				initializeBoard(clickLocation);
			// if bombs were already placed, check if the user clicked a bomb
			else {
				// checks every bomb in hashset to see if the one the user clicked is a bomb
				for (ArrayList<Integer> bomb : bombs)
					// if it is a bomb, then end the game and return
					if (clickLocation.equals(bomb)) {
						endGame(false);
						return;
					}
				// if it reaches here, the user clicked a valid location
			}
			// Expand Location
			expandLocation(row, column);
//			clickButton(row, column);

			System.out.println(spacesLeft + " " + bombNumber);
		}
	}

	private void setImages() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				if (buttonGrid[i][j].isSelected())
					buttonGrid[i][j].setIcon(imageList[grid[i][j]]);
	}

	public void onRightClick(int row, int column) {
		if (bombs != null)
			if (buttonGrid[row][column].getIcon().equals(imageList[11]))
				buttonGrid[row][column].setIcon(imageList[10]);
			else if (buttonGrid[row][column].getIcon().equals(imageList[10]))
				buttonGrid[row][column].setIcon(imageList[11]);
		if (spacesLeft == bombNumber)
			endGame(true);
	}

	private void expandLocation(int row, int column) {
		clickButton(row, column);
		if (grid[row][column] == 0)
			for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, rows - 1); i++)
				for (int j = Math.max(column - 1, 0); j <= Math.min(column + 1, columns - 1); j++)
					if (!buttonGrid[i][j].isSelected()) {
						if (grid[i][j] == 0)
							expandLocation(i, j);
						else if (grid[i][j] < 9)
							clickButton(i, j);
					}
	}

	private void clickButton(int row, int column) {
		buttonGrid[row][column].setSelected(true);
		buttonGrid[row][column].setIcon(imageList[grid[row][column]]);
		spacesLeft--;
	}

	private void endGame(boolean success) {
		if (success) {
			System.out.println("Sucess");
		}
		else {
			for (ArrayList<Integer> bomb : bombs)
				clickButton(bomb.get(0), bomb.get(1));
		}
	}

	public static void main(String[] args) {
		new Minesweeper();
	}

}
