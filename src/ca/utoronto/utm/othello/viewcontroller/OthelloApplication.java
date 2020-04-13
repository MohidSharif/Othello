package ca.utoronto.utm.othello.viewcontroller;

import java.util.Stack;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.control.Label;
import ca.utoronto.utm.util.Observer;
import ca.utoronto.utm.util.Observable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
//import guifx.TimerHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OthelloApplication extends Application {
	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	private Othello othello;

	/*
	 * This function initializes the gui in the game
	 * everything that is displayed on the screen is initialized here
	 * */
	void startGame(Stage stage) {
		// Create and hook up the Model, View and the controller

		// score and game progress textfields
		Label p1_score = new Label();
		Label p2_score = new Label();
		Label progress = new Label();
		TextField hint = new TextField();

		//initialize timer to 5 minutes
		Label timer_label_1 = new Label("Black Time: 5: 00");
		Label timer_label_2 = new Label("White Time: 5: 00");
		
		//**************TIMER***************************
		TimerModel timer1 = new TimerModel("Black Time", timer_label_1, 5, 0);
		timer1.startTimer();
		TimerModel timer2 = new TimerModel("White Time", timer_label_2, 5, 0);
		//**********************************************
		
		//Current player labels
		Label user = new Label("Player 1: Human");
		Label opponent = new Label("Player 2: Human");
		Image whitePiece = new Image(getClass().getResourceAsStream("whitepiece.png"));
		Label current = new Label("Turn to play");
		current.setGraphic(new ImageView(whitePiece));

		//score and game progress text
		p1_score.setText("BLACK: 2");
		p2_score.setText("WHITE: 2");
		progress.setText("Game in progress...");
		
		hint.setEditable(false);
		
		//make rand, greedy and restart buttons
		Button hint_rand = new Button("Hint Random");
		Button hint_greedy = new Button("Hint Greedy");
		Button restartGameBtn = new Button("Restart Game");
		Button undo = new Button("Undo");

		
		restartGameBtn.setStyle("-fx-base: rgb(86, 1, 0);");
		undo.setStyle("-fx-base: rgb(136, 138, 51);");
		restartGameBtn.setMaxSize(150, 50);
		undo.setMaxSize(150, 50);
		
		Stack moveStack = new Stack();
		
		//restart the game
	    restartGameBtn.setOnAction(e -> {
	        restartGame(stage);
	     });

		// MODEL
		Othello othello = new Othello();
		Stack<Othello> game = new Stack<Othello>();
		game.push(othello.copy());
		
		// VIEW
		// VIEW->CONTROLLER hookup
		
		OthelloGrid grid = new OthelloGrid(othello, p1_score, p2_score, progress, opponent, current, game, timer1, timer2);
		OthelloGUI gui = new OthelloGUI();
		PlayerOptionGUI POGUI = new PlayerOptionGUI(othello, opponent, current, timer1, timer2, grid);
		
		gui.setOrientation(Orientation.VERTICAL);
		gui.setStyle("-fx-base: rgb(70, 70, 74);");

		hint_rand.setOnAction(new HintEventHandler(othello, grid, hint));
		hint_greedy.setOnAction(new HintEventHandler(othello, grid, hint));
		hint_rand.setMaxSize(150, 50);
		hint_greedy.setMaxSize(150, 50);
		

		
		undo.setOnAction(new UndoEventHandler(othello, grid, game, current, progress));
		//add all components to the GUI
		gui.getChildren().addAll(user, opponent, POGUI);

		gui.getChildren().add(grid);
		gui.getChildren().add(p1_score);
		gui.getChildren().add(p2_score);
		gui.getChildren().add(progress);
		gui.getChildren().add(undo);
		gui.getChildren().add(restartGameBtn);
		gui.getChildren().add(hint_rand);
		gui.getChildren().add(hint_greedy);
		gui.getChildren().add(hint);
		gui.getChildren().add(current);
		gui.getChildren().add(timer_label_1);
		gui.getChildren().add(timer_label_2);
		
		// MODEL->VIEW hookup
		othello.attach(grid);
		
		// SCENE
		Scene scene = new Scene(gui);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();

	}
	
	/*
	 * This function restarts the game
	 * */
	public void restartGame(Stage stage) {
		startGame(stage);
	}

	/*
	 * This function starts the game
	 * */
	@Override
	public void start(Stage stage) throws Exception {
		startGame(stage);
		
	}

	public static void main(String[] args) {
		// OthelloApplication view = new OthelloApplication();
		launch(args);
	}

	
}
