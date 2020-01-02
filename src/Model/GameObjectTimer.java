package Model;

import java.util.Timer;
import java.util.TimerTask;

import Controller.ManageGame;
import Utils.E_GameObject;
import Utils.E_TimerStatus;

/***
 * 
 * This class creates timers for the relevant objects in the game.
 * 
 * Use Case: After eating an object the timer starts running, after the timer is
 * finished, the fruit respawns.
 * 
 * Case death before respawn -> the timer is canceled
 * 
 * Case eating question -> the timer is paused
 * 
 * Case game paused -> the timer is paused
 * 
 * @author gabi9
 *
 */
public class GameObjectTimer {

	private E_TimerStatus Status;
	private Timer timer;
	private int startTime; // the time the timer started
	private int time;
	private int timeLeft; // case paused, holds the left for completion
	private E_GameObject objectType;

	/**
	 * Constructed of GameObjectTimer.
	 * 
	 * @param objectType According to this parameter, the object knows configures it
	 *                   self to the relevant fruit/mouse
	 */
	public GameObjectTimer(E_GameObject objectType) {
		super();
		this.objectType = objectType;
	}

	/**
	 * start the counting
	 */
	public void start() {
		ManageGame control = ManageGame.getInstance();
		timer = new Timer();
		startTime = (int) System.currentTimeMillis() / 1000;
		TimerTask task = null;

		// Decision according to object type
		if (objectType == E_GameObject.Apple) {
			task = new TimerTask() {

				@Override
				public void run() {
					control.addApple();

				}
			};
			time = Apple.RESPAWN;

		} else if (objectType == E_GameObject.Banana) {
			task = new TimerTask() {

				@Override
				public void run() {
					control.addBanana();

				}
			};
			time = Banana.RESPAWN;

		} else if (objectType == E_GameObject.Mouse) {

			task = new TimerTask() {

				@Override
				public void run() {
					control.addMouse();
				}
			};
			time = Mouse.RESPAWN;
		}

		timer.schedule(task, time);
		Status = E_TimerStatus.RUNNING;

	}

	/**
	 * pauses the timer. keeps the time left in timeLeft
	 */
	public void pause() {
		int cancelTime = (int) System.currentTimeMillis() / 1000;
		int timePast = cancelTime - startTime;
		timeLeft = time - timePast;

		Status = E_TimerStatus.PAUSED;

		if (timer != null)
			timer.cancel();
	}

	/**
	 * resume paused timer. plays timeLeft.
	 */
	public void resume() {
		timer = new Timer();
		ManageGame control = ManageGame.getInstance();
		Status = E_TimerStatus.RUNNING;

		TimerTask task = null;

		// Decision according to object type
		if (objectType == E_GameObject.Apple)
			task = new TimerTask() {

				@Override
				public void run() {
					control.addApple();
				}
			};

		else if (objectType == E_GameObject.Banana)
			task = new TimerTask() {

				@Override
				public void run() {
					control.addBanana();

				}
			};

		else if (objectType == E_GameObject.Mouse)
			task = new TimerTask() {

				@Override
				public void run() {
					control.addMouse();
				}
			};

		timer.schedule(task, timeLeft);
	}

	/**
	 * @return gets status of the timer
	 */
	public E_TimerStatus getStatus() {
		return Status;
	}

	/**
	 * cancels the timer tasks
	 */
	public void cancel() {
		Status = Utils.E_TimerStatus.STOPPED;
		if (timer != null)
			timer.cancel();
	}

}
