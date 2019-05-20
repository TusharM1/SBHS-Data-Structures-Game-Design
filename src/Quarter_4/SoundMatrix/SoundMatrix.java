package Quarter_4.SoundMatrix;

import javax.swing.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundMatrix {

	public static void main(String[] args) throws MalformedURLException {
		URL note = new URL("file:src/Quarter_4/SoundMatrix/MusicBoxNotesC3.wav");
		AudioClip clip = JApplet.newAudioClip(note);
		new Thread(() -> {
			clip.play();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("ERROR 1");
			}
		}).start();
	}

}
