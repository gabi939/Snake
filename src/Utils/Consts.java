package Utils;

import javafx.scene.paint.Color;

/**
 * Class Consts ~ represents the consts in the game
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 */
public class Consts {
	// ------------------------------- Board ------------------------------
	/**
	 * Board height
	 */
	public static final int HEIGHT = 580;

	/**
	 * Board width
	 */
	public static final int WIDTH = 1100;

	/**
	 * Objects size on board
	 */
	public static final int SIZE = 20;

	/**
	 * Default color for board background
	 */
	public final static Color DEFUALT_BG_COLOR = Color.web("#013220");

	// ------------------------------- Snake ------------------------------

	/**
	 * Snake's starting size
	 */
	public static final int SNAKE_SIZE = 6;

	/**
	 * David's head for snake
	 */
	public final static String DAVID_HEAD = "resources/head-David.png";

	/**
	 * Gabi's head for snake
	 */
	public final static String GABI_HEAD = "resources/head-Gabi.png";

	/**
	 * Nareed's head for snake
	 */
	public final static String NAREED_HEAD = "resources/head-Nareed.png";

	/**
	 * Shany's head for snake
	 */
	public final static String SHANY_HEAD = "resources/head-Shany.png";

	/**
	 * Default head for snake
	 */
	public final static String DEFUALT_SNAKE_HEAD = "resources/head-snake.png";
	/**
	 * Tsvika's head for snake
	 */
	public final static String TSVIKA_HEAD = "resources/head-Tsvika.png";

	/**
	 * Sloth's head for snake
	 */
	public final static String SLOTH_HEAD = "resources/head-Sloth.png";

	/**
	 * Default color for snake body
	 */
	public final static Color DEFUALT_SNAKE_COLOR = Color.BLUE;

	/**
	 * Default speed for snake
	 */
	public final static double DEFUALT_SNAKE_SPEED = 3;

	// ------------------------------- Pear ------------------------------

	/**
	 * Pear image
	 */
	public final static String PEAR_IMG = "resources/Pear-icon.png";

	/**
	 * Score added after eating pear
	 */
	public static final int PEAR_SCORE = 30;

	// ------------------------------- Apple ------------------------------

	/**
	 * Apple image
	 */
	public final static String APPLE_IMG = "resources/Apple-icon.png";

	/**
	 * Apple respawn time
	 */
	public static final int APPLE_RESPAWN = 5000;

	/**
	 * Score added after eating apple
	 */
	public static final int APPLE_SCORE = 10;

	// ------------------------------- Banana ------------------------------

	/**
	 * Banana image
	 */
	public final static String BANANA_IMG = "resources/Banana-icon.png";

	/**
	 * Banana respawn time
	 */
	public static final int BANANA_RESPAWN = 10000;

	/**
	 * Score added after eating banana
	 */
	public static final int BANANA_SCORE = 20;

	// ------------------------------- Mouse ------------------------------

	/**
	 * Mouse image
	 */
	public final static String MOUSE_IMG = "resources/Mouse-icon.png";

	/**
	 * Default speed for mouse
	 */
	public final static double DEFUALT_MOUSE_SPEED = 6;

	/**
	 * Respawn time of the mouse
	 */
	public static final int MOUSE_RESPAWN = 60000;

	/**
	 * Score added after eating a mouse
	 */
	public static final int MOUSE_SCORE = 30;

	/**
	 * 
	 */
	public static final int ADD_LIFE = 1;

	// ------------------------------- Questions ------------------------------

	/**
	 * Easy Question image
	 */
	public final static String EASY_Q_IMG = "resources/Question-easy-icon.png";

	/**
	 * Medium Question image
	 */
	public final static String MEDIUM_Q_IMG = "resources/Question-medium-icon.png";

	/**
	 * Hard Question image
	 */
	public final static String HARD_Q_IMG = "resources/Question-hard-icon.png";

	// ------------------------------- Sound & Music ------------------------------

	/**
	 * Background music SID
	 */
	public final static String MUSIC_SLOTH_SID = "/resources/wav_back_music1.wav";
	/**
	 * Background music GOT
	 */
	public final static String MUSIC_SLOTH_GOT = "/resources/wav_back_music2.wav";

	/**
	 * Eating sound
	 */
	public final static String EATING_SOUND = "/resources/eating-sound.mp3";

	/**
	 * Obstacle hit sound
	 */
	public final static String HIT_SOUND = "/resources/hit-sound.mp3";

	// ------------------------------- Utils ------------------------------

	/**
	 * Maximum size for history list
	 */
	public final static int MAX_HISTORY_SIZE = 15;

	/**
	 * Snake logo image
	 */
	public final static String SNAKE_LOGO = "resources/logo-snake-img.png";

	/**
	 * hidden bonus
	 */
	public static final int ADD_HIDDEN_BONUS = 100;
}
