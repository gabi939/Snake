package View;

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
/**
 * This method gets the path of the sound in the system, and controls its volume
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
				}
				catch (Exception e) { 
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	/**
	 * This method plays the login sound
	 */
	protected static void playLoginSound() {
		//TODO
		playSound(Sound.class.getResource("/resources/sound-login.mp3"), 1);
	}
	
	
	/**
	 * This method plays the logout sound
	 */
	protected static void playLogoutSound() {
		//TODO
		playSound(Sound.class.getResource("/resources/sound-logout.mp3"), 1);
	}
	
}
