package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
 * @author yourname
 */
public class Main extends Application {
    //required fields and methods

    @Override
    public void start(Stage primaryStage) {
        //TODO: Assume that your program starts from this method


        ///////////////Sample
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.WHEAT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sample");
        primaryStage.setResizable(false);
        primaryStage.show();

        Text text = new Text("Hello Universe!");
        Rectangle rectangle = new Rectangle(90, 60, Color.DARKORANGE);
        Circle circle = new Circle(100, 250, 40, Color.rgb(230, 128, 100));
        Polygon triangle = new Polygon(200.1, 100.0, 120, 130, 140, 150);
        Line line = new Line();
        Line line1 = new Line();
        Label label = new Label("Label");
        Box box = new Box(100, 200, 30);
        box.setTranslateX(400);
        box.setTranslateY(400);
        box.setMaterial(new PhongMaterial(Color.PURPLE));
        label.setTranslateX(300);
        label.setTranslateY(500);
        label.setStyle("-fx-background-color: crimson;" +
                "-fx-background-radius:10%;-fx-font-size: 24px;" +
                "-fx-border-radius: 10%;-fx-border-color: blue;" +
                "-fx-border-style: dotted;" +
                "-fx-border-width: 4px");
        label.setPadding(new Insets(4, 2, 4, 2));
        Button button = new Button("Click me!");
        button.setTranslateX(200);
        button.setTranslateY(200);
        button.setFocusTraversable(false);
        // can be written with lambda
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    circle.setRadius(circle.getRadius() - 2);
                    circle.setOpacity(circle.getOpacity() - 0.05);
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    triangle.setScaleX(triangle.getScaleX() + 0.2);
                    triangle.setScaleY(triangle.getScaleY() + 0.15);
                }
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setY(rectangle.getY() + 5);
            }
        });
        text.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                text.setRotate(text.getRotate() + 10);
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    line.setStrokeWidth(line.getStrokeWidth() + 1);
                    line.setStroke(Color.rgb(Math.abs(new Random().nextInt() % 256)
                            , Math.abs(new Random().nextInt() % 256),
                            Math.abs(new Random().nextInt() % 256)));
                }
            }
        });

        line.setStrokeWidth(5);
        line.setStartX(100);
        line.setStartY(700);
        line.setEndX(400);
        line.setEndY(600);
        line1.setStrokeWidth(8);
        line1.setStartX(10);
        line1.setStartY(400);
        line1.setEndX(200);
        line1.setEndY(450);
        line1.setFill(Color.rgb(255, 0, 255, 0.6));
        line1.setStrokeLineCap(StrokeLineCap.ROUND);
        line1.setTranslateX(300);
        text.setX(400);
        text.setY(700);
        text.setFont(new Font("Times New Roman", 20));
        triangle.setFill(Color.CRIMSON);
        rectangle.setX(600);
        rectangle.setY(200);
        rectangle.setRotate(25);
        scene.setCamera(new PerspectiveCamera());// required for 3D
        new AnimationTimer() {
            private int value = -100;

            @Override
            public void handle(long l) {
                if (box.getTranslateZ() > 10000 || box.getTranslateZ() < -1000) {
                    value *= -1;
                    box.setMaterial(new PhongMaterial(Color.RED));
                    scene.setFill(Color.ORANGE);
                }
                scene.setFill(Color.WHEAT);
                box.setMaterial(new PhongMaterial(Color.PURPLE));
                box.setTranslateZ(box.getTranslateZ() + value);
                box.setRotationAxis(new Point3D(2, 2, 1));
                box.setRotate(box.getRotate() + 1);
            }
        }.start();


        root.getChildren().add(text);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(line);
        root.getChildren().add(line1);
        root.getChildren().add(button);
        root.getChildren().add(label);
        root.getChildren().add(box);

        ///////////////////////////////////////
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
