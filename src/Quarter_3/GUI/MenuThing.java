package Quarter_3.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuThing extends JPanel implements ActionListener {
	
	JFrame frame;
	JPanel topPanel;
	JMenuBar menuBar;
	JTextArea textArea;

	String currentFont;
	Color currentFontColor;
	int currentFontSize;

	Color currentBackgroundColor;

	public MenuThing() {
		frame = new JFrame();
		frame.add(this);

		currentFont = "Times New Roman";
		currentFontColor = Color.BLACK;
		currentFontSize = 14;

		currentBackgroundColor = Color.WHITE;

		topPanel = new JPanel();

		Button[] direction = new Button[4];
		String[] buttonNames = {"N", "S", "E", "W"};
		for (int i = 0; i < direction.length; i++) {
			direction[i] = new Button();
			direction[i].setLabel(buttonNames[i]);
			direction[i].addActionListener(e -> {
				switch (((Button)(e.getSource())).getLabel()) {
					case "N":
						menuBar.setLayout(new FlowLayout());
						topPanel.setLayout(new FlowLayout());
						frame.add(topPanel, BorderLayout.NORTH);
						break;
					case "S":
						menuBar.setLayout(new FlowLayout());
						topPanel.setLayout(new FlowLayout());
						frame.add(topPanel, BorderLayout.SOUTH);
						break;
					case "E":
						menuBar.setLayout(new GridLayout(5,1));
						topPanel.setLayout(new GridLayout(6, 1));
						frame.add(topPanel, BorderLayout.EAST);
						break;
					case "W":
						menuBar.setLayout(new GridLayout(5,1));
						topPanel.setLayout(new GridLayout(6, 1));
						frame.add(topPanel, BorderLayout.WEST);
						break;
				}
				frame.revalidate();
			});
			topPanel.add(direction[i]);
		}

		menuBar = new JMenuBar();

		JMenu menu;

		menu = new JMenu("Font");
		String[] fonts = {"Times New Roman", "Arial", "Cambria"};
		for (int i = 0; i < 3; i++) {
			JMenuItem menuItem = new JMenuItem(fonts[i]);
			final int fontIndex = i;
			JMenu finalMenu = menu;
			menuItem.addActionListener(e -> {
				System.out.println(textArea.getText());
				currentFont = fonts[fontIndex];
				Font font = new Font(currentFont, Font.PLAIN, currentFontSize);
				textArea.setFont(font);
				for (int j = 0; j < menuBar.getSubElements().length; j++) {
					if (menuBar.getSubElements()[j] == finalMenu)
						continue;
					for (MenuElement thisMenu : menuBar.getSubElements()[j].getSubElements()) {
//						((JMenu) thisMenu).setFont(font);
						for (MenuElement thisItem : thisMenu.getSubElements())
							((JMenuItem) thisItem).setFont(font);
					}
				}
			});
			menu.add(menuItem);
		}
		menuBar.add(menu);

		menu = new JMenu("Font Size");
		int[] fontSizes = {12, 24, 36};
		for (int i = 0; i < 3; i++) {
			JMenuItem menuItem = new JMenuItem(String.valueOf(fontSizes[i]));
			final int fontIndex = i;
			JMenu finalMenu = menu;
			menuItem.addActionListener(e -> {
				currentFontSize = fontSizes[fontIndex];
				Font font = new Font(currentFont, Font.PLAIN, currentFontSize);
				textArea.setFont(new Font(currentFont, Font.PLAIN, currentFontSize));
				textArea.setFont(font);
				for (int j = 0; j < menuBar.getSubElements().length; j++) {
					if (menuBar.getSubElements()[j] == finalMenu)
						continue;
					for (MenuElement thisMenu : menuBar.getSubElements()[j].getSubElements()) {
						for (MenuElement thisItem : thisMenu.getSubElements())
							((JMenuItem) thisItem).setFont(font);
					}
				}
			});
			menu.add(menuItem);
		}
		menuBar.add(menu);

		menu = new JMenu("Text Color");
		String[] fontColors = {"Black", "Red", "Random"};
		for (int i = 0; i < 3; i++) {
			JMenuItem menuItem = new JMenuItem(fontColors[i]);
			final int fontIndex = i;
			JMenu finalMenu = menu;
			menuItem.addActionListener(e -> {
				switch (fontColors[fontIndex]) {
					case "Black": currentFontColor = Color.BLACK; break;
					case "Red": currentFontColor = Color.RED; break;
					case "Random": currentFontColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)); break;
				}
				for (int j = 0; j < menuBar.getSubElements().length; j++) {
					if (menuBar.getSubElements()[j] == finalMenu)
						continue;
					for (MenuElement thisMenu : menuBar.getSubElements()[j].getSubElements()) {
						for (MenuElement thisItem : thisMenu.getSubElements())
							((JMenuItem) thisItem).setForeground(currentFontColor);
					}
				}
				textArea.setForeground(currentFontColor);
			});
			menu.add(menuItem);
		}
		menuBar.add(menu);

		menu = new JMenu("Background Color");
		String[] backgroundColors = {"White", "Orange", "Random"};
		for (int i = 0; i < 3; i++) {
			JMenuItem menuItem = new JMenuItem(backgroundColors[i]);
			final int fontIndex = i;
			JMenu finalMenu = menu;
			menuItem.addActionListener(e -> {
				switch (backgroundColors[fontIndex]) {
					case "White": currentBackgroundColor = Color.WHITE; break;
					case "Orange": currentBackgroundColor = Color.ORANGE; break;
					case "Random": currentBackgroundColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)); break;
				}
				for (int j = 0; j < menuBar.getSubElements().length; j++) {
					if (menuBar.getSubElements()[j] == finalMenu)
						continue;
					for (MenuElement thisMenu : menuBar.getSubElements()[j].getSubElements()) {
						for (MenuElement thisItem : thisMenu.getSubElements())
							((JMenuItem) thisItem).setBackground(currentFontColor);
					}
				}
				textArea.setBackground(currentBackgroundColor);
			});
			menu.add(menuItem);
		}
		menuBar.add(menu);

		menu = new JMenu("Outline Color");
		String[] outlineColor = {"Black", "Red", "Random"};
		for (int i = 0; i < 3; i++) {
			JMenuItem menuItem = new JMenuItem(backgroundColors[i]);
			final int fontIndex = i;
			JMenu finalMenu = menu;
			menuItem.addActionListener(e -> {
				switch (outlineColor[fontIndex]) {
					case "Black": currentFontColor = Color.WHITE; break;
					case "Red": currentFontColor = Color.ORANGE; break;
					case "Random": currentFontColor = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)); break;
				}
				for (int j = 0; j < menuBar.getSubElements().length; j++) {
					if (menuBar.getSubElements()[j] == finalMenu)
						continue;
					for (MenuElement thisMenu : menuBar.getSubElements()[j].getSubElements()) {
						for (MenuElement thisItem : thisMenu.getSubElements())
							((JMenuItem) thisItem).setBackground(currentFontColor);
					}
				}
				textArea.setBackground(currentFontColor);
			});
			menu.add(menuItem);
		}
		menuBar.add(menu);

		topPanel.add(menuBar);

		JButton reset = new JButton("Reset");
		reset.addActionListener(e -> {
			currentFont = fonts[0];
			currentFontColor = Color.BLACK;
			currentFontSize = fontSizes[0];
		});
		topPanel.add(reset);

		frame.add(topPanel, BorderLayout.NORTH);

		textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);

		frame.add(textArea, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);
		frame.setVisible(true);

		currentFont = fonts[0];
		currentFontColor = Color.BLACK;
		currentFontSize = fontSizes[0];
	}

	private void setWidgetsFont(String currentFont, int currentFontSize, Color currentFontColor) {
		for (MenuElement menus: menuBar.getSubElements())
			for (MenuElement items : menus.getSubElements())
				((MenuItem) items).setFont(new Font(currentFont, Font.PLAIN, currentFontSize));
				
	}

	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {
		new MenuThing();
	}

}