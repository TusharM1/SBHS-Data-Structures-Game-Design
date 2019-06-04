package Quarter_4.SoundMatrix;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class SoundMatrix extends JPanel {

    JFrame jFrame;

    JPanel songMenu, buttons;

    ArrayList<String> noteNames;
    ArrayList<AudioClip> notes;
    ArrayList<ArrayList<String>> buttonNames;
    ArrayList<ArrayList<JToggleButton>> buttonMatrix;

    int numberOfNotes, numberOfColumns, startingNote, startingOctave;

    Thread playMusic;
    boolean isPlaying;
    int currentColumn;
    int direction = 1;

    int delay = 1000;

    public SoundMatrix() {
        jFrame = new JFrame();
        jFrame.add(this);

        this.setLayout(new BorderLayout(0, 0));

        noteNames = new ArrayList<>(Arrays.asList("A", "ASharp", "B", "C", "CSharp", "D", "DSharp", "E", "F", "FSharp", "G", "GSharp"));
        notes = new ArrayList<>();
        numberOfNotes = 24;
        numberOfColumns = 12;
        startingNote = noteNames.indexOf("C");
        startingOctave = 3;
        isPlaying = true;
        currentColumn = 0;

        try {
            URL note;
            for (int i = startingNote; i < numberOfNotes + startingNote; i++) {
                note = new File("src/Quarter_4/SoundMatrix/MusicBoxNotes/" + noteNames.get(i % noteNames.size()) + (startingOctave + ((i - startingNote) / noteNames.size())) + ".wav").toURL();
                notes.add( JApplet.newAudioClip(note));
            }
        } catch (MalformedURLException ignored) {}

        // CREATE MENUS
        songMenu = new JPanel();
        JMenuBar menuBar = new JMenuBar();

        JMenu songs = new JMenu("Song List");
        JMenuItem song1 = new JMenuItem("Twinkle Twinkle Litte Star");
        JMenuItem song2 = new JMenuItem("Mary Had a Little Lamb");
        JMenuItem song3 = new JMenuItem("London Bridge is Falling Down");
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);

        JButton clear = new JButton("Clear");
        JButton random = new JButton("Random");
        JButton add = new JButton("+");
        JButton remove = new JButton("-");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton play = new JButton("Play");
        JButton pause = new JButton("Pause");
        JButton restart = new JButton("Restart");
        JButton reverse = new JButton("Reverse");

        JTextField bpm = new JTextField("BPM");

        menuBar.add(songs);
        menuBar.add(clear);
        menuBar.add(random);
        menuBar.add(add);
        menuBar.add(remove);
        menuBar.add(save);
        menuBar.add(load);
        menuBar.add(play);
        menuBar.add(pause);
        menuBar.add(restart);
        menuBar.add(reverse);

        menuBar.add(bpm);

        songMenu.add(menuBar);

        song1.addActionListener(e -> {
            loadSong("Songs/twinkle_twinkle_little_star.txt");
        });

        song2.addActionListener(e -> {
            loadSong("Songs/mary_had_a_little_lamb.txt");
        });

        song3.addActionListener(e -> {
            loadSong("Songs/london_bridge_is_falling_down.txt");
        });

        // CLEAR ALL SONGS
        clear.addActionListener(e -> {
            for (ArrayList<JToggleButton> arrayList : buttonMatrix)
                for (JToggleButton button : arrayList)
                    button.setSelected(false);
        });

        // RANDOMIZE ALL SONGS
        random.addActionListener(e -> {
            for (ArrayList<JToggleButton> arrayList : buttonMatrix)
                for (JToggleButton button : arrayList) {
                    button.setSelected(false);
                    if (Math.random() <= .25)
                        button.setSelected(true);
                }
        });

        // ADD SONGS
        add.addActionListener(e -> {
            numberOfColumns++;
            buttons.setLayout(new GridLayout(numberOfNotes, numberOfColumns, 0, 0));
            for (int i = 0; i < numberOfNotes; i++) {
                String noteName = buttonNames.get(i).get(0);
                buttonNames.get(i).add(noteName);
                buttonMatrix.get(i).add(new JToggleButton(noteName.replace("Sharp", "") + (noteName.contains("Sharp") ? "#" : "")));
//                buttonMatrix.get(i).add(new JToggleButton("*" + i));
                buttons.add(buttonMatrix.get(i).get(buttonMatrix.get(i).size() - 1), (i + 1) * numberOfColumns - 1);
            }
            buttons.updateUI();
        });

        // REMOVE SONGS
        remove.addActionListener(e -> {
            if (numberOfColumns == 0)
                return;
            if (numberOfColumns >= 1) {
                numberOfColumns--;
                buttons.setLayout(new GridLayout(numberOfNotes, numberOfColumns, 0, 0));
                for (int i = 0; i < numberOfNotes; i++) {
                    if (numberOfColumns > 1)
                        buttonNames.get(i).remove(buttonNames.get(i).get(0));
                    buttonMatrix.get(i).remove(numberOfColumns - (numberOfColumns > 1 ? 1 : 0));
                    buttons.remove(numberOfColumns == 0 ? i * numberOfColumns : (i + 1) * numberOfColumns);
                }
            }
            buttons.updateUI();
        });

        // SAVE SONG TO FILE
        save.addActionListener(e -> {
            try {
                ArrayList<String> strings = new ArrayList<>();
                for (ArrayList<JToggleButton> arrayList : buttonMatrix) {
                    StringBuilder string = new StringBuilder();
                    for (JToggleButton jToggleButton : arrayList)
                        string.append(jToggleButton.isSelected() ? 1 : 0);
                    strings.add(string.toString().replaceAll("[\\[\\],]", ""));
                }
                Files.write(Paths.get("src/Quarter_4/SoundMatrix/Songs/saved.txt"), strings, Charset.defaultCharset());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // READ SONG FROM FILE
        load.addActionListener(e -> {
            loadSong("Songs/saved.txt");
        });

        play.addActionListener(e -> {
            isPlaying = true;
        });

        pause.addActionListener(e -> {
            isPlaying = false;
        });

        restart.addActionListener(e -> {
            currentColumn = 0;
            isPlaying = true;
        });

        bpm.addActionListener(e -> {
            delay = 60000 / Integer.parseInt(bpm.getText());
        });

        reverse.addActionListener(e -> {
            direction *= -1;
        });

        this.add(songMenu, BorderLayout.NORTH);

        // CREATE BUTTONS
        buttons = new JPanel(new GridLayout(numberOfNotes, numberOfColumns, 0, 0));
        buttonNames = new ArrayList<>();
        buttonMatrix = new ArrayList<>();
        for (int i = 0; i < numberOfNotes; i++) {
            buttonNames.add(new ArrayList<>());
            buttonMatrix.add(new ArrayList<>());
            int inverseIndex = numberOfNotes - i - 1;
            for (int j = 0; j < numberOfColumns; j++) {
                String noteName = noteNames.get((inverseIndex + startingNote) % noteNames.size()) +
                                    (startingOctave + (inverseIndex + (noteNames.size() - (noteNames.indexOf("C") != startingNote ? noteNames.indexOf("C") -
                                            startingNote : noteNames.size()))) / noteNames.size() - (noteNames.indexOf("C") < startingNote ? 1 : 0));
                buttonNames.get(i).add(noteName);
                buttonMatrix.get(i).add(new JToggleButton(noteName.replace("Sharp", "") + (noteName.contains("Sharp") ? "#" : "")));
//                buttonMatrix.get(i).add(new JToggleButton(String.valueOf(i * numberOfColumns + j)));
                buttons.add(buttonMatrix.get(i).get(buttonMatrix.get(i).size() - 1));
            }
        }

        this.add(buttons, BorderLayout.CENTER);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(50 * numberOfColumns + 350, 50 * numberOfNotes + 100);
        jFrame.setVisible(true);

        playMusic = new Thread() {
            @Override
            public void start() {
                try {
                    while (true) {
                        if (isPlaying) {
                            currentColumn = (currentColumn + numberOfColumns) % numberOfColumns;
                            for (int j = 0; j < buttonMatrix.size(); j++) {
                                if (buttonMatrix.get(j).get(currentColumn % buttonMatrix.get(j).size()).isSelected()) {
                                    notes.get(numberOfNotes - j - 1).stop();
                                    notes.get(numberOfNotes - j - 1).play();
                                }
                            }
                            currentColumn += direction;
                            Thread.sleep(delay);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        playMusic.start();
    }

    private void loadSong(String song) {
        currentColumn = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/Quarter_4/SoundMatrix/" + song), Charset.defaultCharset())) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null && !line.equals("\n")) {
                String[] strings = line.split("");
                for (int i = buttonMatrix.get(lineNumber).size(); i < strings.length; i++) {
                    String noteName = buttonNames.get(lineNumber).get(0);
                    buttonNames.get(lineNumber).add(noteName);
                    buttonMatrix.get(lineNumber).add(new JToggleButton(noteName.replace("Sharp", "") + (noteName.contains("Sharp") ? "#" : "")));
                    buttons.add(buttonMatrix.get(lineNumber).get(buttonMatrix.get(lineNumber).size() - 1), (lineNumber + 1) * buttonMatrix.get(lineNumber).size() - 1);
                    numberOfColumns = buttonMatrix.get(lineNumber).size();
                }
                for (int i = buttonMatrix.get(lineNumber).size() - 1; i >= strings.length; i--) {
                    buttonNames.get(lineNumber).remove(buttonNames.get(lineNumber).get(0));
                    buttonMatrix.get(lineNumber).remove(buttonMatrix.get(lineNumber).remove(i));
                    buttons.remove((lineNumber + 1) * strings.length - 1);
                    numberOfColumns = buttonMatrix.get(lineNumber).size();
                }
                for (int i = 0; i < strings.length; i++)
                    buttonMatrix.get(lineNumber).get(i).setSelected(strings[i].equals("1"));
                numberOfColumns = buttonMatrix.get(lineNumber).size();
                lineNumber++;
            }
            buttons.updateUI();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) { new SoundMatrix(); }

}
