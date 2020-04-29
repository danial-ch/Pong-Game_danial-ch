package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

/**
 * @author danial chekani
 */
public class Main extends Application {

    static Group root = new Group();
    static Scene scene = new Scene(root, 900, 500, Color.YELLOWGREEN);
    static Rectangle player1Rectangle = new Rectangle(20,100,15,90);
    static Rectangle player2Rectangle = new Rectangle(865,250,15,90);
    static Button playButton = new Button("Play");
    static Button exitButton = new Button("Exit");
    static ToggleButton player1ColorChooser = new ToggleButton("Long");
    static ToggleButton player2ColorChooser = new ToggleButton("Long");
    static int player1Health = 5;
    static int player2Health = 5;
    static Text player1HealthBar = new Text(String.valueOf(player1Health));
    static Text player2HealthBar = new Text(String.valueOf(player2Health));
    static Text player1Helper = new Text("Press Q to change wall");
    static Text player2Helper = new Text("Press 7 to change wall");

    public void start(Stage primaryStage) {

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pong");
        primaryStage.setResizable(false);
        primaryStage.show();
        SetupGame.setupGameDetails();
        SetupGame.eventHandling();

        root.getChildren().add(playButton);
        root.getChildren().add(exitButton);
        root.getChildren().add(player1ColorChooser);
        root.getChildren().add(player2ColorChooser);
        root.getChildren().add(player1Helper);
        root.getChildren().add(player2Helper);
        root.getChildren().add(player1Rectangle);
        root.getChildren().add(player2Rectangle);
        root.getChildren().add(player1HealthBar);
        root.getChildren().add(player2HealthBar);
    }


    @Override
    public void init() {
        System.out.println("Initializing...");
    }

    @Override
    public void stop() {
        System.out.println("Closing....");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
