package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.Player;
import ca.utoronto.utm.othello.model.PlayerBetter;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GametypeButtonEventHandler implements EventHandler<ActionEvent> {
	private Othello othello;
	private Label opp;
	private Label currentPlayer = new Label();
	private TimerModel timer1;
	private TimerModel timer2;
	private OthelloGrid grid;
	
	/*
	 * This is a constructor for an event handler for the game type
	 * this initialized the game type and other components
	 * */
	GametypeButtonEventHandler(Othello othello, Label opponent, Label currentPlayer, TimerModel timer1, TimerModel timer2, OthelloGrid grid){
		this.othello = othello;
		this.opp = opponent;
		this.currentPlayer = currentPlayer;
		this.timer1 = timer1;
		this.timer2 = timer2;
		this.grid = grid;
	}
	
	/*
	 * This handler updates the game type labels which indicate who
	 * the 2 players are, also updates other attributes as necessary
	 * */
	@Override
	public void handle(ActionEvent event) {
		
		Button button = (Button) event.getSource();
		
		//change current players text on screen to show who the user is against
		if (button.getText() == "VS HUMAN") {
			this.opp.setText("Player 2: Human");
		}
		else if (button.getText() == "VS GREEDY") {
			this.opp.setText("Player 2: Greedy");

			//pause both timers, when both players are not human
			this.timer1.pauseTimer();
			this.timer2.pauseTimer();
		}
		else if (button.getText() == "VS RANDOM") {
			this.opp.setText("Player 2: Random");

			//pause both timers, when both players are not human
			this.timer1.pauseTimer();
			this.timer2.pauseTimer();
		}
		else if (button.getText() == "VS SMART") {
			this.opp.setText("Player 2: Smart");

			//pause both timers, when both players are not human
			this.timer1.pauseTimer();
			this.timer2.pauseTimer();
		}
		
		//makes a move for the opponent type gets chosen if it is player 2 turn
		//also changes gui for whos turn is next
		if (this.opp.getText().equals("Player 2: Random") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerRandom(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
			Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
			
			currentPlayer.setGraphic(new ImageView(blackPiece));
			
			for(int row=0;row<Othello.DIMENSION;row++) {
				for(int col=0;col<Othello.DIMENSION;col++) {
					Othello boardCopy = othello.copy();
					grid.buttonArray[row][col].setStyle("-fx-base: rgb(70, 70, 74);");
					if(boardCopy.move(row, col))grid.buttonArray[row][col].setStyle("-fx-base: rgb(47, 66, 49);");
				}
			}
					
		}
		else if (this.opp.getText().equals("Player 2: Greedy") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerGreedy(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
			Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
			
			currentPlayer.setGraphic(new ImageView(blackPiece));
			
			for(int row=0;row<Othello.DIMENSION;row++) {
				for(int col=0;col<Othello.DIMENSION;col++) {
					Othello boardCopy = othello.copy();
					grid.buttonArray[row][col].setStyle("-fx-base: rgb(70, 70, 74);");
					if(boardCopy.move(row, col))grid.buttonArray[row][col].setStyle("-fx-base: rgb(47, 66, 49);");
				}
			}
			
		}
		else if (this.opp.getText().equals("Player 2: Smart") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerBetter(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
			Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
			
			currentPlayer.setGraphic(new ImageView(blackPiece));
			
			for(int row=0;row<Othello.DIMENSION;row++) {
				for(int col=0;col<Othello.DIMENSION;col++) {
					Othello boardCopy = othello.copy();
					grid.buttonArray[row][col].setStyle("-fx-base: rgb(70, 70, 74);");
					if(boardCopy.move(row, col))grid.buttonArray[row][col].setStyle("-fx-base: rgb(47, 66, 49);");
				}
			}
			
		}
		//for this one, we just resume timers, no moves are made
		else if (this.opp.getText().equals("Player 2: Human") && othello.getWhosTurn() == OthelloBoard.P2) {
			
			if (othello.getWhosTurn() == OthelloBoard.P2)	
			{
					this.timer2.startTimer();
				
			}
			
			else if (othello.getWhosTurn() == OthelloBoard.P1)	
			{
					this.timer1.startTimer();
			}
			
		}

	}

}
