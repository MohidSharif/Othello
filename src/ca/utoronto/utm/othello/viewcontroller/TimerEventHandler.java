package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class TimerEventHandler implements EventHandler<ActionEvent>{
	
	private int min;
	private int sec;
	private String name;
	private Label label;
	private Timeline timer;
	
	/*
	 * This constructor initializes a timer event handler which will
	 * let the user keep track of the amount of time they have for the
	 * game and each turn
	 * */
	public TimerEventHandler(String name, Label label, int min, int sec, Timeline timer)
	{
		this.name = name;
		this.label = label;
		this.min = min;
		this.sec = sec;
		this.timer = timer;
	}
	
	/*
	 * This handler counts down every second to update the timer
	 * so the player can see how much time they hav left
	 * */
	public void handle(ActionEvent event) 
	{
		//change time by 1 second until it hits 0
		if (this.sec == 0 && this.min == 0)
		{
			this.timer.stop();
		}
		
		if (this.sec == -1)
		{
			this.min--;
			this.sec = 59;
		}
		if (this.sec < 10)
		{
			this.label.setText(this.name + ": " + this.min + ": 0" + this.sec);
			this.sec--;
		}
		
		else if (this.sec > 10 || this.min > 0)
		{
			this.label.setText(this.name + ": " + this.min + ": " + this.sec);
			this.sec--;
		}
		
		
		
	}
	

}
