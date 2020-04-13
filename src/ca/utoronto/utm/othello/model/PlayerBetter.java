package ca.utoronto.utm.othello.model;

public class PlayerBetter extends Player {

	public PlayerBetter(Othello othello, char player) {
		super(othello, player);
	}

	@Override
	public Move getMove() {

		Othello othelloCopy = othello.copy();
		// the coordinates of the corners
		int[][] corners = { { 0, 0 }, // top left corner
				{ 0, Othello.DIMENSION - 1 }, // top right corner
				{ Othello.DIMENSION - 1, 0 }, // bottom left corner
				{ Othello.DIMENSION - 1, Othello.DIMENSION - 1 } // bottom right corner
		};
		
		//check corner piece first
		for (int[] coordinate : corners) {
			if (othelloCopy.move(coordinate[0], coordinate[1])) {
				return new Move(coordinate[0], coordinate[1]);
			}
		}
		
		Move bestMove = new Move(0, 0);
		int bestMoveCount = othelloCopy.getCount(this.player);
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				othelloCopy = othello.copy();
				if (othelloCopy.move(row, col) && othelloCopy.getCenterPieceCount(this.player) > bestMoveCount) {
					bestMoveCount = othelloCopy.getCount(this.player);
					bestMove = new Move(row, col);
				}
			}
		}
		
		//this is initiaetd if there was no way to improve center piece count
		if (bestMove.getRow() == 0 && bestMove.getCol() == 0) { 
			PlayerGreedy greedyStrat = new PlayerGreedy(othelloCopy, player);
			bestMove = greedyStrat.getMove();
		}
		return bestMove;
	}
	


}
