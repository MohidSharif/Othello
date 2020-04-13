package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//GUI that contains all the types of players you can play against
public class PlayerOptionGUI extends VBox {
	
	/*
	 * This constructor, initializes a player option gui, which 
	 * the user can click to change the player types in the game
	 * */
	public PlayerOptionGUI(Othello othello, Label opponent, Label current, TimerModel timer1, TimerModel timer2, OthelloGrid grid) {
		
		//specifications
		this.setPrefWidth(100);
		this.setSpacing(100);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-base: rgb(50, 65, 120);");
		
		//initialize buttons
		Button hvhButton = new Button("VS HUMAN");
		Button hvgButton = new Button("VS GREEDY");
		Button hvrButton = new Button("VS RANDOM");
		Button hvsButton = new Button("VS SMART");
		
		//connect button to event handler 
		hvhButton.setOnAction(new GametypeButtonEventHandler(othello, opponent, current, timer1, timer2, grid));
		hvgButton.setOnAction(new GametypeButtonEventHandler(othello, opponent, current, timer1, timer2, grid));
		hvrButton.setOnAction(new GametypeButtonEventHandler(othello, opponent, current, timer1, timer2, grid));
		hvsButton.setOnAction(new GametypeButtonEventHandler(othello, opponent, current, timer1, timer2, grid));
		
		//add buttons to the VBox 
		this.getChildren().addAll(hvhButton, hvgButton, hvrButton, hvsButton);
		

	}
	


}
