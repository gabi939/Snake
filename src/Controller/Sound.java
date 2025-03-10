package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Utils.Consts;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class Sound ~ Class that controls the played sounds in the system
 * 
 * @author Shany Klein
 * @author Nareed Hashem
 *
 */
public class Sound {
	private static boolean soundFX = true;
	public static Clip clip;

	/**
	 * this method returns the boolean value of soundfx which means weather the
	 * soundfx is turned on or off
	 * 
	 * @return boolean
	 */
	public static boolean isSoundFX() {
		return soundFX;
	}

	/**
	 * @param soundFX
	 */
	public static void setSoundFX(boolean soundFX) {
		Sound.soundFX = soundFX;
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
	public static void playMusic() {
		try {
			String musicFile = "";
			if (Sysdata.getPlayer() != null) {
				if (Sysdata.getPlayer().getName().toLowerCase().contains("sloth")) {
					String a = Consts.MUSIC_SLOTH_SID;
					String b = Consts.MUSIC_SLOTH_GOT;
					musicFile = new Random().nextBoolean() ? a : b;
				} else if (Sysdata.getPlayer().getName().toLowerCase().contains("tsvika")) {
					String a = Consts.TSVIKA_MUSIC_1;
					String b = Consts.TSVIKA_MUSIC_2;
					musicFile = new Random().nextBoolean() ? a : b;
				}else
					musicFile = Consts.DEFAULT_MUSIC;
			}

			InputStream audioFile = Sound.class.getResourceAsStream(musicFile);
			AudioInputStream sound = AudioSystem.getAudioInputStream(audioFile);

			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// toggleMusic(true);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method stops the background music
	 */
	public static void stopMusic() {
		if (clip != null)
			clip.stop();
	}

	/**
	 * This method plays the eating sound
	 */
	public static void playEatingSound() {
		if (soundFX)
			playSound(Sound.class.getResource(Consts.EATING_SOUND), 80);
	}

	/**
	 * This method plays the hitting sound
	 */
	public static void playHitingSound() {
		if (soundFX)
			playSound(Sound.class.getResource(Consts.HIT_SOUND), 80);
	}

	/**
	 * this method starts or stops the background music by the boolean it takes
	 * 
	 * @param bool
	 */
	public static void toggleMusic(boolean bool) {
		if (bool)
			playMusic();
	}

	/**
	 * this method starts or stops the eating sound by the boolean it takes
	 * 
	 * @param bool
	 */
	public static void toggleSoundFX(boolean bool) {
		setSoundFX(bool);

	}
}
