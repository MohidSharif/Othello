package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerBetter;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoordinateButtonEventHandler implements EventHandler<ActionEvent>{

	private Othello othello;
	private OthelloGrid grid;
	private Label p1_score = new Label();
	private Label p2_score = new Label();
	private Label playerWon = new Label();
	private Label opponent = new Label();
	private Label currentPlayer = new Label();
	private TimerModel timer1;
	private TimerModel timer2;
	
	/*
	 * This is a constructor for an event handler
	 * this constructor initializes all its attributes
	 * to the arguments given
	 * */
	public CoordinateButtonEventHandler(Othello othello, OthelloGrid grid, Label p1, Label p2, Label progress, Label opponent, Label current, TimerModel timer1, TimerModel timer2) {
		this.othello = othello;
		this.grid = grid;
		this.p1_score = p1;
		this.p2_score = p2;
		this.playerWon = progress;
		this.opponent = opponent;
		this.currentPlayer = current;
		this.timer1 = timer1;
		this.timer2 = timer2;
	}
	
	/*
	 * This handler makes changes according to which coordinate button was pressed on
	 * the board, updates score, game status and other settings of the game
	 * */
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
		CoordinateButton curr = (CoordinateButton) event.getSource();
		
		//was the move successful?
		boolean n = this.othello.move(curr.getRow(), curr.getCol());
		
		//change timers depending on who makes a move
		if (othello.getWhosTurn() == OthelloBoard.P2 && this.opponent.getText().equals("Player 2: Human"))	
		{
			if (n)			//IF PLAYER 1 MADE A MOVE
			{
				this.timer2.startTimer();
				this.timer1.pauseTimer();
			}
			
		}
		
		else if (othello.getWhosTurn() == OthelloBoard.P1)	
		{
			if (n)		//IF PLAYER 2 HAS MADE A MOVE
			{
				this.timer2.pauseTimer();
				this.timer1.startTimer();
			}
			
		}
		//move other players if the game is not human vs human
		if (n && this.opponent.getText().equals("Player 2: Random") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerRandom(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
		}
		else if (n && this.opponent.getText().equals("Player 2: Greedy") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerGreedy(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
		}
		else if (n && this.opponent.getText().equals("Player 2: Smart") && othello.getWhosTurn() == OthelloBoard.P2) {
			Move m = new PlayerBetter(othello, OthelloBoard.P2).getMove();
			this.othello.move(m.getRow(), m.getCol());
		}
		
		//change scores for players
		p1_score.setText("Black: " + othello.getCount(OthelloBoard.P1));
		p2_score.setText("White: " + othello.getCount(OthelloBoard.P2));
		
		Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
		Image whitePiece = new Image(getClass().getResourceAsStream("whitepiece.png"));
		
		//change graphics telling the user whos turn is next
		if (othello.getWhosTurn() == OthelloBoard.P1) {
			currentPlayer.setGraphic(new ImageView(whitePiece));
		}
		else if (othello.getWhosTurn() == OthelloBoard.P2) {
			currentPlayer.setGraphic(new ImageView(blackPiece));
		}
		
		//clearing all the buttons from any colour.
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				grid.buttonArray[row][col].setStyle(null);
			}
		}
		//highlighting the buttons where a player can play.
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				Othello boardCopy = othello.copy();
				grid.buttonArray[row][col].setStyle("-fx-base: rgb(70, 70, 74);");
				if(boardCopy.move(row, col))grid.buttonArray[row][col].setStyle("-fx-base: rgb(47, 66, 49);");
			}
		}
		//check if game is over and give message accordingly
		if (othello.isGameOver())
		{
			char whoWon = othello.getWinner();
			
			if (whoWon == OthelloBoard.P1)
			{
				playerWon.setText("Black Won!");
			}
			else if (whoWon == OthelloBoard.P2)
			{
				playerWon.setText("White Won!");
			}
			else
				playerWon.setText("It was a TIE!");
		}
	}
	
}
