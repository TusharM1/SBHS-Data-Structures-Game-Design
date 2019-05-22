package Quarter_4.SoundMatrix;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SoundTemplate extends JFrame implements Runnable {

	boolean notStopped = true;

	JPanel panel;
	JPanel topMenu;
	ArrayList<JToggleButton[]> buttonGrid;
	AudioClip[] soundClip;

	String[] noteNames;

	int matrixLength;

	public SoundTemplate() {
		panel = new JPanel();

		noteNames = new String[] {"A", "ASharp", "B", "C", "CSharp", "D", "DSharp", "E", "F", "FSharp", "G", "GSharp"};

		matrixLength = noteNames.length;

		buttonGrid = new ArrayList<>();
		for (int i = 0; i < matrixLength; i++) {
			JToggleButton[] buttons = new JToggleButton[20];
			for (int j = 0; j < buttons.length; j++)
				buttons[j] = new JToggleButton();
			buttonGrid.add(buttons);
		}

		soundClip = new AudioClip[matrixLength];

		try {
			URL note;
			for (int i = 3; i < noteNames.length + 3; i++) {
				note = new File("src/Quarter_4/SoundMatrix/MusicBoxNotes/" + noteNames[i % noteNames.length] + (3 + ((i - 3) / noteNames.length)) + ".wav").toURL();
				soundClip[(i - 3)] = JApplet.newAudioClip(note);
			}
		} catch (MalformedURLException ignored) {}

		topMenu = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		JMenu songs = new JMenu("Song List");
		JMenuItem song1 = new JMenuItem("Song 1");
		JMenuItem song2 = new JMenuItem("Song 2");
		JMenuItem song3 = new JMenuItem("Song 3");
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		JButton clear = new JButton("Clear");
		JButton random = new JButton("Random");
		JButton add = new JButton("+");
		JButton remove = new JButton("-");
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		menuBar.add(songs);
		menuBar.add(clear);
		menuBar.add(random);
		menuBar.add(add);
		menuBar.add(remove);
		menuBar.add(save);
		menuBar.add(load);
		topMenu.add(menuBar);

		add.addActionListener(e -> {
			for (int i = 0; i < buttonGrid.size(); i++) {
				JToggleButton[] newButtons = new JToggleButton[buttonGrid.get(i).length + 1];
				System.arraycopy(buttonGrid.get(i), 0, newButtons, 0, buttonGrid.get(i).length);
				newButtons[buttonGrid.get(i).length] = new JToggleButton(buttonGrid.get(i)[0].getText());
				buttonGrid.set(i, newButtons);
				panel.add(buttonGrid.get(i)[buttonGrid.get(i).length - 1]);
			}
			panel.setLayout(new GridLayout(buttonGrid.size(), buttonGrid.get(0).length, 0, 0));
			panel.updateUI();
		});

		save.addActionListener(e -> {
			StringBuilder fileData = new StringBuilder();
			for (int i = 0; i < buttonGrid.get(0).length; i++) {
				for (JToggleButton[] jToggleButtons : buttonGrid)
					fileData.append(jToggleButtons[i].isSelected() ? 1 : 0);
				fileData.append("|");
			}
			fileData.append("\n");
			try {
				FileOutputStream fos = new FileOutputStream("song.txt");
				fos.write(fileData.toString().getBytes());
				fos.flush();
				fos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		load.addActionListener(e -> {
			try {
				FileReader fileReader = new FileReader("song.txt");
				int col = 0, row = 0;
				while(col < buttonGrid.get(0).length) {
					int c = fileReader.read();
					buttonGrid.get(row)[col].setSelected(c == '1');
					row = (row + 1) % buttonGrid.size();
					if (c == '|') {
						col++;
						row = 0;
					}
				}

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		clear.addActionListener(e -> {
			for (int i = 0; i < buttonGrid.get(0).length; i++) {
				for (JToggleButton[] jToggleButtons : buttonGrid) {
					jToggleButtons[i].setSelected(false);
				}
			}
		});

		random.addActionListener(e -> {
			for (int i = 0; i < buttonGrid.get(0).length; i++) {
				for (JToggleButton[] jToggleButtons : buttonGrid) {
					if (Math.random() < .25)
						jToggleButtons[i].setSelected(true);
				}
			}
		});

		this.add(topMenu, BorderLayout.NORTH);

		panel.setLayout(new GridLayout(buttonGrid.size(), buttonGrid.get(0).length, 0, 0));
		for (int i = matrixLength - 1; i >= 0; i--) {
			for (int j = 0; j < buttonGrid.get(0).length; j++) {
				buttonGrid.get(i)[j] = new JToggleButton();
				buttonGrid.get(i)[j].setText(noteNames[((i) + 3) % noteNames.length].replace("Sharp", "#"));
				panel.add(buttonGrid.get(i)[j]);
			}
		}

		this.add(panel, BorderLayout.CENTER);
//		this.add(frame);
		setSize(50 * buttonGrid.get(0).length, 50 * buttonGrid.size());

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread timing = new Thread(this);
		timing.start();
	}

	public void run() {
//		do {
//			try {
//				for (int i = 0; i < buttonGrid.get(0).length; i++) {
//					for (int j = 0; j < buttonGrid.size(); j++) {
//						if (buttonGrid.get(j)[i].isSelected()) {
//							soundClip[j % matrixLength].play();
//							System.out.println(j);
//						}
//					}
//					Thread.sleep(1000);
//				}
//				System.out.println();
//			} catch (Exception ignored) {}
//		} while (notStopped);
	}

	public static void main(String[] args) {
		new SoundTemplate();
	}
}