package ca.utoronto.utm.othello.viewcontroller;

import java.util.Stack;

import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;


public class UndoEventHandler implements EventHandler<ActionEvent>, Observer{
	
	private Othello othello;
	private OthelloGrid grid;
	private Stack<Othello> game;
	private Label current;
	private Label progress;
	
	/*
	 * This constructor makes an undo event handler which will be used to undo 
	 * moves and settings in the game
	 * */
	public UndoEventHandler(Othello othello, OthelloGrid grid, Stack<Othello> game, Label current, Label progress) {
		this.othello = othello;
		this.grid = grid;
		this.game = game;
		this.current = current;
		this.progress = progress;
	}
	
	/*
	 * This handler goes back 1 turn when the user presses the undo button
	 * this must change the board plus the scores and all other settings
	 * of the game that need to be changed
	 * */	
	public void handle(ActionEvent event) {
		this.progress.setText("Game in progress...");
		Image whitePiece = new Image(getClass().getResourceAsStream("whitepiece.png"));
		Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
//		game.push(othello.copy());
		if (game.size() >= 2) {
			game.pop();
			othello.setGame(game.peek());

			if (othello.getWhosTurn() == OthelloBoard.P1) {
				current.setGraphic(new ImageView(whitePiece));
			}
			else current.setGraphic(new ImageView(blackPiece));
				
			for (int i=0; i < Othello.DIMENSION; i++) {
				for (int j=0; j < Othello.DIMENSION; j++) {
					//the text for the button in the (i,j) position in the grid
					char buttonValue = this.othello.getToken(i,j);
					grid.buttonArray[i][j].setGraphic(null);
					Othello boardCopy = othello.copy();
					grid.buttonArray[i][j].setStyle("-fx-base: rgb(70, 70, 74);");
					if(boardCopy.move(i, j))grid.buttonArray[i][j].setStyle("-fx-base: rgb(47, 66, 49);");
					if (buttonValue == 'X') { 
						//sets the buttontext to buttonvalue
						//WHITE PIECE is represented as an X in the Model
						grid.buttonArray[i][j].setGraphic(new ImageView(whitePiece));
					}
					else if (buttonValue == 'O') { 
						//sets the buttontext to buttonvalue
						//Black Piece is represented as an O 
						grid.buttonArray[i][j].setGraphic(new ImageView(blackPiece));
					}
				}
			}
		}
	}
	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		
	}

}
