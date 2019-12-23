package view;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class Sound ~ Class that controls the played sounds in the system
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 *
 */
public class Sound {
	private static boolean backgroundMuted = false;
	private static boolean eatingMuted = false;

	public static boolean isBackgroundMuted() {
		return backgroundMuted;
	}

	public static void setBackgroundMuted(boolean backgroundMuted) {
		Sound.backgroundMuted = backgroundMuted;
	}

	public static boolean isEatingMuted() {
		return eatingMuted;
	}

	public static void setEatingMuted(boolean eatingMuted) {
		Sound.eatingMuted = eatingMuted;
	}

	/**
	 * This method gets the path of the sound in the system, and controls its volume
	 * 
	 * @param soundFilePath
	 * @param volume
	 */
	protected static void playSound(URL soundFilePath, double volume) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String soundFile = soundFilePath.toString();
					Media media = new Media(soundFile);
					MediaPlayer mediaPlayer = new MediaPlayer(media);
					mediaPlayer.setVolume(volume);
					mediaPlayer.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * This method plays the background music
	 */
	protected static void playBackgroundMusic() {
		if (!backgroundMuted)
			playSound(Sound.class.getResource("/resources/background-music.mp3"), 1);
	}

	/**
	 * This method plays the eating sound
	 */
	public static void playEatingSound() {
		if (!eatingMuted)
			playSound(Sound.class.getResource("/resources/eating-sound.mp3"), 80);
	}

}
