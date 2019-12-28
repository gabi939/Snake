package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Utils.Consts;

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
	private static boolean eatingSound = true;
	static Clip clip;

	public static boolean isEatingSound() {
		return eatingSound;
	}

	public static void setEatingSound(boolean eatingSound) {
		Sound.eatingSound = eatingSound;
	}

	/**
	 * This method gets the path of the sound in the system, and controls its volume
	 * 
	 * @param soundFilePath
	 * @param volume
	 */
	public static void playSound(URL soundFilePath, double volume) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String soundFile = soundFilePath.toString();
					Media media = new Media(soundFile);
					MediaPlayer mp = new MediaPlayer(media);
					mp.setVolume(volume);
					mp.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * This method plays the background music
	 */
	public static void setMusic() {
		try {
			File audioFile = new File(Consts.BACKGROUND_MUSIC);
			AudioInputStream sound = AudioSystem.getAudioInputStream(audioFile);
			clip = AudioSystem.getClip();
			clip.open(sound);
			toggleMusic(true);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method plays the eating sound
	 */
	public static void playEatingSound() {
		if (eatingSound)
			playSound(Sound.class.getResource("/resources/eating-sound.mp3"), 80);
	}

	/*
	 * this method starts or stops the background music by the boolean it takes
	 */
	public static void toggleMusic(boolean bool) {
		if (bool) {
			clip.setFramePosition(0);
			clip.start();
		} else {
			clip.stop();
		}
	}

	/*
	 * this method starts or stops the eating sound by the boolean it takes
	 */
	public static void toggleEatingSound(boolean bool) {
		setEatingSound(bool);
	}
}
