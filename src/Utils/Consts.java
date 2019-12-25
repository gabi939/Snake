package Utils;

import javafx.scene.paint.Color;

public class Consts {
	
	/**
	 * Board height 
	 */
	public static final int HEIGHT = 580; //540;
	
	/**
	 * Board width 
	 */
	public static final int WIDTH = 1100; //860;
	
	/**
	 * Objects size on board
	 */
	public static final int SIZE = 20;
	

	/**
	 * Color of obstacles
	 */
	//TODO
	public static final Color OBSTACLE_COLOR = Color.BROWN;
	
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
	 * Pear image
	 */
	public final static String PEAR_IMG = "resources/Pear-icon.png";
	
	/**
	 * Apple image
	 */
	public final static String APPLE_IMG = "resources/Apple-icon.png";
	
	/**
	 * Banana image
	 */
	public final static String BANANA_IMG = "resources/Banana-icon.png";
	
	/**
	 * Mouse image
	 */
	public final static String MOUSE_IMG = "resources/Mouse-icon.png";
	
	/**
	 * Wall image
	 */
	public final static String WALL_IMG = "resources/Wall-icon.png";
	
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
	
	
	/**
	 * Background music
	 */
	public final static String BACKGROUND_MUSIC = "resources/background-music.mp3";
	
	/**
	 * Eating sound
	 */
	public final static String EATING_SOUND = "resources/eating-sound.mp3";
	
	/**
	 * Default color for board background
	 */
	public final static Color DEFUALT_BG_COLOR = Color.BLACK;
	
	/**
	 * Default color for snake body
	 */
	public final static Color DEFUALT_SNAKE_COLOR = Color.BLUE;
	
	/**
	 * Default speed for snake
	 */
	public final static double DEFUALT_SNAKE_SPEED = 3;
	
	/**
	 * Default speed for mouse
	 */
	public final static double DEFUALT_MOUSE_SPEED = 6;
	
	/**
	 * How many obstacles from beginning
	 */
	public final static int OBSTACLES_START_NUMBER = 3;

	/**
	 * Number of GameObjects to store in X-axis
	 */
	//private static final int BWIDTH = MainView.Consts.WIDTH / Consts.SIZE;
	/**
	 * Number of GameObjects to store in Y-axis
	 */
	//private static final int BHEIGHT = MainView.Consts.HEIGHT / Consts.SIZE;

}
