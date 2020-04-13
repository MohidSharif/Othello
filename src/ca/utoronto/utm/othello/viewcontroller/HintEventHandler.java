package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;
import java.util.Random;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HintEventHandler implements EventHandler<ActionEvent>, Observer{

	private Othello othello;
	private OthelloGrid grid;
	private TextField hint = new TextField();
	
	/*
	 * This is a constructor to initialize the hint gui on
	 * the scene
	 * */
	public HintEventHandler(Othello othello, OthelloGrid grid, TextField hint_tf) {
		this.othello = othello;
		this.grid = grid;
		this.hint = hint_tf;
	}
	
	/*
	 * This handler produces a hint for the player, depending
	 * on which button they clicked (random or greedy), also highlights
	 * the coordinate button on that coordinate for the greedy hint
	 * */
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
		//check which hint type was chosen
		if (((Button) (event.getSource())).getText() == "Hint Random") {
			//hind random, just display a random hint on textbox
			Random rand = new Random();
			ArrayList<Move> moves = new ArrayList<Move>();
			for(int row=0;row<Othello.DIMENSION;row++) {
				for(int col=0;col<Othello.DIMENSION;col++) {
					Othello othelloCopy = othello.copy();
					if(othelloCopy.move(row, col))moves.add(new Move(row,col));
				}
			}
			//disable button when board is empty
			if (moves.isEmpty()) {
				((Button) (event.getSource())).setDisable(true);				
			}
			else { 
				((Button) (event.getSource())).setDisable(false);
				this.hint.setText(moves.get(rand.nextInt(moves.size())).toString());
			}
		}
		else if (((Button) (event.getSource())).getText() == "Hint Greedy") {
			//hint greedy, display a greedy hint on textbox, and highlight that coordinate
			Othello othelloCopy = othello.copy();
			Move bestMove=new Move(0,0);
			int bestMoveCount=othelloCopy.getCount(othello.getWhosTurn());;
			for(int row=0;row<Othello.DIMENSION;row++) {
				for(int col=0;col<Othello.DIMENSION;col++) {
					othelloCopy = othello.copy();
					if(othelloCopy.move(row, col) && othelloCopy.getCount(othello.getWhosTurn())>bestMoveCount) {
						bestMoveCount = othelloCopy.getCount(othello.getWhosTurn());
						bestMove = new Move(row,col);
					}
				}
			}
			grid.buttonArray[bestMove.getRow()][bestMove.getCol()].setStyle("-fx-base: rgb(86, 1, 0);");
			//disable button when board is empty
			if (othello.getWhosTurn() == OthelloBoard.EMPTY) {
				((Button) (event.getSource())).setDisable(true);
			}
			else{
				((Button) (event.getSource())).setDisable(false);
				this.hint.setText(bestMove.toString());
			}
		}
		
		
	}
	

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		
	}
}
