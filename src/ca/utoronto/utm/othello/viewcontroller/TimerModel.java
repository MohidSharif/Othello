package ca.utoronto.utm.othello.viewcontroller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerModel {
	
	Timeline timer = new Timeline();
	
	/*
	 * This constructor initializes a timer model which will run and stop our timer
	 * */
	public TimerModel (String name, Label label, int min, int sec)
	{
		TimerEventHandler timerevent = new TimerEventHandler(name, label, min, sec, timer);
		timer.setCycleCount(Timeline.INDEFINITE);
		KeyFrame perSecondKeyFrame = new KeyFrame(Duration.seconds(1), timerevent);
		timer.getKeyFrames().add(perSecondKeyFrame);
	}
	
	/*
	 * This function starts the timer
	 * */
	public void startTimer() 
	{
		timer.play();
	}
	
	/*
	 * This functions stops the timer
	 * */
	public void pauseTimer()
	{
		timer.stop();
	}
	
	
	

}
