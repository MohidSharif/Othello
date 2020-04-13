package ca.utoronto.utm.othello.viewcontroller;
import javafx.scene.control.Button;

public class CoordinateButton extends Button {
	
	private int row;
	private int col;

	/*
	 * This is a constructor to create a coordinate button
	 * a coordinate button has 2 attributes
	 * row, column
	 * */
	public CoordinateButton(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/*
	 * This function returns the row number of the coordinate button
	 * */
	public int getRow() {
		return this.row;
	}
	
	/*
	 * This function returns the column number of the coordinate button
	 * */
	public int getCol() {
		return this.col;
	}
	
}
