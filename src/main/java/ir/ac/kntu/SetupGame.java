package ir.ac.kntu;

import javafx.application.Platform;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SetupGame {
    public static void setupGameDetails() {
        Main.player1Rectangle.setArcHeight(15);
        Main.player1Rectangle.setArcWidth(15);
        Main.player1Rectangle.setFill(Color.GREEN);

        Main.player2Rectangle.setArcHeight(15);
        Main.player2Rectangle.setArcWidth(15);
        Main.player2Rectangle.setFill(Color.GREEN);

        Main.onePlayerButton.setPrefWidth(100);
        Main.onePlayerButton.setPrefHeight(30);
        Main.onePlayerButton.setTranslateX(400);
        Main.onePlayerButton.setLayoutY(150);
        Main.onePlayerButton.setStyle("-fx-background-color: khaki");
        Main.onePlayerButton.setDefaultButton(false);

        Main.twoPlayerButton.setPrefWidth(100);
        Main.twoPlayerButton.setPrefHeight(30);
        Main.twoPlayerButton.setTranslateX(400);
        Main.twoPlayerButton.setLayoutY(200);
        Main.twoPlayerButton.setStyle("-fx-background-color: khaki");
        Main.twoPlayerButton.setDefaultButton(false);

        Main.exitButton.setPrefWidth(100);
        Main.exitButton.setPrefHeight(30);
        Main.exitButton.setTranslateX(400);
        Main.exitButton.setLayoutY(250);
        Main.exitButton.setStyle("-fx-background-color: khaki");
        Main.exitButton.setDefaultButton(false);

        Main.player1ColorChooser.setTextFill(Color.WHITE);
        Main.player1ColorChooser.setTranslateX(150);
        Main.player1ColorChooser.setTranslateY(200);
        Main.player1ColorChooser.setStyle("-fx-background-color: green");
        Main.player1ColorChooser.setPrefHeight(50);
        Main.player1ColorChooser.setPrefWidth(100);

        Main.player2ColorChooser.setTextFill(Color.WHITE);
        Main.player2ColorChooser.setTranslateX(650);
        Main.player2ColorChooser.setTranslateY(200);
        Main.player2ColorChooser.setStyle("-fx-background-color: green");
        Main.player2ColorChooser.setPrefHeight(50);
        Main.player2ColorChooser.setPrefWidth(100);

        Main.player1HealthBar.setFill(Color.SALMON);
        Main.player1HealthBar.setX(350);
        Main.player1HealthBar.setY(50);
        Main.player1HealthBar.setFont(Font.font("LiquidCrystal", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Main.player2HealthBar.setFill(Color.SALMON);
        Main.player2HealthBar.setX(510);
        Main.player2HealthBar.setY(50);
        Main.player2HealthBar.setFont(Font.font("LiquidCrystal", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Main.player1Helper.setFill(Color.DARKBLUE);
        Main.player1Helper.setX(10);
        Main.player1Helper.setY(480);
        Main.player1Helper.setVisible(false);

        Main.player2Helper.setFill(Color.DARKBLUE);
        Main.player2Helper.setX(770);
        Main.player2Helper.setY(480);
        Main.player2Helper.setVisible(false);
    }

    public static void changeRectangle(Rectangle rectangle, ToggleButton colorChooser) {
        if (rectangle.getFill().equals(Color.GREEN)) {
            colorChooser.setSelected(true);
            rectangle.setFill(Color.RED);
            rectangle.setHeight(60);
        } else {
            colorChooser.setSelected(false);
            rectangle.setFill(Color.GREEN);
            rectangle.setHeight(90);
        }
    }

    public static void eventHandling() {
        Main.twoPlayerButton.onMouseClickedProperty().setValue(mouseEvent -> {
            Main.player1Health = 5;
            Main.player2Health = 5;
            Main.player1HealthBar.setText(String.valueOf(Main.player1Health));
            Main.player2HealthBar.setText(String.valueOf(Main.player2Health));
            Main.player1ColorChooser.setVisible(false);
            Main.player2ColorChooser.setVisible(false);
            Main.exitButton.setVisible(false);
            Main.onePlayerButton.setVisible(false);
            Main.twoPlayerButton.setVisible(false);
            Main.player1Helper.setVisible(true);
            Main.player2Helper.setVisible(true);
            GamePlay.startGame(0);
        });

        Main.onePlayerButton.onMouseClickedProperty().setValue(mouseEvent -> {
            Main.player1Health = 5;
            Main.player2Health = 5;
            Main.player1HealthBar.setText(String.valueOf(Main.player1Health));
            Main.player2HealthBar.setText(String.valueOf(Main.player2Health));
            Main.player1ColorChooser.setVisible(false);
            Main.player2ColorChooser.setVisible(false);
            Main.exitButton.setVisible(false);
            Main.onePlayerButton.setVisible(false);
            Main.twoPlayerButton.setVisible(false);
            Main.player1Helper.setVisible(true);
            Main.player2Helper.setVisible(true);
            GamePlay.startGame(1);
        });

        Main.exitButton.onMouseClickedProperty().setValue(event -> Platform.exit());

        Main.player1ColorChooser.setOnMouseClicked(mouseEvent -> {
            if (Main.player1ColorChooser.isSelected()) {
                Main.player1ColorChooser.setStyle("-fx-background-color: red");
                Main.player1ColorChooser.setText("Fast");
                Main.player1Rectangle.setFill(Color.RED);
                Main.player1Rectangle.setHeight(60);
            } else {
                Main.player1ColorChooser.setStyle("-fx-background-color: green");
                Main.player1ColorChooser.setText("Long");
                Main.player1Rectangle.setFill(Color.GREEN);
                Main.player1Rectangle.setHeight(90);
            }
        });

        Main.player2ColorChooser.setOnMouseClicked(mouseEvent -> {
            if (Main.player2ColorChooser.isSelected()) {
                Main.player2ColorChooser.setStyle("-fx-background-color: red");
                Main.player2ColorChooser.setText("Fast");
                Main.player2Rectangle.setFill(Color.RED);
                Main.player2Rectangle.setHeight(60);
            } else {
                Main.player2ColorChooser.setStyle("-fx-background-color: green");
                Main.player2ColorChooser.setText("Long");
                Main.player2Rectangle.setFill(Color.GREEN);
                Main.player2Rectangle.setHeight(90);
            }
        });

        Main.scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                if (Main.player2Rectangle.getY() + Main.player2Rectangle.getHeight() <= Main.scene.getHeight()) {
                    Main.player2Rectangle.setY(Main.player2Rectangle.getY() + 14);
                }
            } else if (keyEvent.getCode() == KeyCode.UP) {
                if (Main.player2Rectangle.getY() >= 0) {
                    Main.player2Rectangle.setY(Main.player2Rectangle.getY() - 14);
                }
            } else if (keyEvent.getCode() == KeyCode.NUMPAD7) {
                changeRectangle(Main.player2Rectangle, Main.player2ColorChooser);
            }
        });

        Main.scene.addEventFilter(KeyEvent.ANY, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.S) {
                if (Main.player1Rectangle.getY() + Main.player1Rectangle.getHeight() <= Main.scene.getHeight()) {
                    Main.player1Rectangle.setY(Main.player1Rectangle.getY() + 14);
                }
            } else if (keyEvent.getCode() == KeyCode.W) {
                if (Main.player1Rectangle.getY() >= 0) {
                    Main.player1Rectangle.setY(Main.player1Rectangle.getY() - 14);
                }
            } else if (keyEvent.getCode() == KeyCode.Q) {
                changeRectangle(Main.player1Rectangle, Main.player1ColorChooser);
            }
        });
    }
}
