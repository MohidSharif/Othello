package ca.utoronto.utm.othello.viewcontroller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Stack;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observer;
import ca.utoronto.utm.util.Observable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class OthelloGrid extends GridPane implements Observer{
	
	CoordinateButton[][] buttonArray;
	private Othello othello;
	private Stack<Othello> game;
	
	/*
	 * This constructor initializes an othello grid which displays all
	 * coordinate buttons in a grid like manor, each button is then put
	 * on this grid and assigned a handler
	 * */
	public OthelloGrid(Othello othello, Label p1_score, Label p2_score, Label progress, Label opponent, Label current, Stack<Othello> game, TimerModel timer1, TimerModel timer2) {

		this.setHgap(0); //horizontal gap in pixels 
		this.setVgap(0); //vertical gap in pixels
		this.setPadding(new Insets(10)); //margin 
		this.othello = othello; 
		this.buttonArray = new CoordinateButton[Othello.DIMENSION][Othello.DIMENSION];
		int n = 0;
		this.game = game;
		for (int i=0; i < Othello.DIMENSION; i++) {
			for (int j=0; j < Othello.DIMENSION; j++) {
				//create new coordinate button 
				//coordinate button keeps track of the coordinates of the button
				//in the grid
				CoordinateButton button = new CoordinateButton(i,j);
				
				this.buttonArray[i][j] = button;
				//makes the move in the button handler
				button.setOnAction(new CoordinateButtonEventHandler(othello, this, p1_score, p2_score, progress, opponent, current, timer1, timer2));
				
				//button size
				button.setMinHeight(80);
				button.setMinWidth(80);
				
				
				//add the button to the gridpanel
				this.add(button,j,i);
				n = j;
			}
		}
		Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
		Image whitePiece = new Image(getClass().getResourceAsStream("whitepiece.png"));

		//add the first four othello pieces
		buttonArray[3][3].setGraphic(new ImageView(whitePiece));
		buttonArray[3][4].setGraphic(new ImageView(blackPiece));
		buttonArray[4][3].setGraphic(new ImageView(blackPiece));
		buttonArray[4][4].setGraphic(new ImageView(whitePiece));
		
		//all four possible first moves
		buttonArray[2][4].setStyle("-fx-base: rgb(47, 66, 49);");
		buttonArray[3][5].setStyle("-fx-base: rgb(47, 66, 49);");
		buttonArray[5][3].setStyle("-fx-base: rgb(47, 66, 49);");
		buttonArray[4][2].setStyle("-fx-base: rgb(47, 66, 49);");
		
		
	}
	
	/*
	 * This observable obserces the changes on the grid and makes changes 
	 * to the coordinate buttons as required
	 * */
	@Override
	public void update(Observable o) {			//SET ANIMATION	
		// TODO Auto-generated method stub
		game.push(othello.copy());
		for (int i=0; i < Othello.DIMENSION; i++) {
			for (int j=0; j < Othello.DIMENSION; j++) {
				//the text for the button in the (i,j) position in the grid
				char buttonValue = this.othello.getToken(i,j);
				if (buttonValue == 'X') { 
					//sets the buttontext to buttonvalue
					//WHITE PIECE is represented as an X in the Model
					Image whitePiece = new Image(getClass().getResourceAsStream("whitepiece.png"));
					buttonArray[i][j].setGraphic(new ImageView(whitePiece));
				}
				else if (buttonValue == 'O') { 
					//sets the buttontext to buttonvalue
					//Black Piece is represented as an O 
					Image blackPiece = new Image(getClass().getResourceAsStream("blackpiece.png"));
					buttonArray[i][j].setGraphic(new ImageView(blackPiece));
				}
			}
		}
	}
}
