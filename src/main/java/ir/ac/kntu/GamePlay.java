package ir.ac.kntu;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

public class GamePlay {

    static Timeline moveBall;
    static boolean flag = false;

    public static void spawnBall() {
        Ball ball = Ball.getInstance();
        int spawnX = new Random().nextInt(300) + 300;
        int spawnY = new Random().nextInt(200) + 150;
        ball.getBall().setCenterX(spawnX);
        ball.getBall().setCenterY(spawnY);
        double launchAngle;
        do {
            launchAngle = Math.toRadians(new Random().nextInt(360));
        } while ((launchAngle > Math.PI / 3 && launchAngle < Math.PI * 2 / 3) || (launchAngle > Math.PI * 4 / 3 && launchAngle < Math.PI * 5 / 3));

        ball.setSpeedX(ball.getSpeedAmount() * (Math.cos(launchAngle) * Math.cos(launchAngle)));
        ball.setSpeedY(ball.getSpeedAmount() * (Math.sin(launchAngle) * Math.sin(launchAngle)));
        Main.root.getChildren().add(ball.getBall());
    }

    public static void ballMovement() {

        KeyFrame move = new KeyFrame(
                Duration.seconds(0),
                event -> {
                    ballMovingXAxis();
                    ballMovingYAxis();
                }
        );

        moveBall = new Timeline(move, new KeyFrame(Duration.millis(20)));
        moveBall.setCycleCount(Timeline.INDEFINITE);
        moveBall.play();
    }

    public static void ballMovingXAxis() {
        Ball ball = Ball.getInstance();

        if ((ball.getBall().getCenterX() + 12 < 865 && ball.getBall().getCenterX() - 12 > 35) || flag) {
            if (flag && ((ball.getBall().getCenterX() + 12 >= 900) || (ball.getBall().getCenterX() - 12 >= 0))) {
                flag = false;
                Main.root.getChildren().remove(ball.getBall());
                moveBall.stop();
                startGame();
                return;
            }
            ball.getBall().setCenterX(ball.getBall().getCenterX() + ball.getSpeedX());
        } else {
            if (ball.getBall().getCenterX() + 12 > 865) {
                if ((Main.player2Rectangle.getY() > (ball.getBall().getCenterY() + 12) ||
                        Main.player2Rectangle.getY() + Main.player2Rectangle.getHeight() < (ball.getBall().getCenterY() - 12))) {
                    Main.player2Health--;
                    Main.player2HealthBar.setText(String.valueOf(Main.player2Health));
                    flag = true;
                }
            } else if (ball.getBall().getCenterX() - 12 < 35) {
                if ((Main.player1Rectangle.getY() > (ball.getBall().getCenterY() + 12) ||
                        Main.player1Rectangle.getY() + Main.player1Rectangle.getHeight() < (ball.getBall().getCenterY() - 12))) {
                    Main.player1Health--;
                    Main.player1HealthBar.setText(String.valueOf(Main.player1Health));
                    flag = true;
                }
            }
            ball.setSpeedX(-ball.getSpeedX());
            ball.getBall().setCenterX(ball.getBall().getCenterX() + ball.getSpeedX());
        }
    }

    public static void ballMovingYAxis() {
        Ball ball = Ball.getInstance();

        if (ball.getBall().getCenterY() + 12 < 500 && ball.getBall().getCenterY() - 12 > 0) {
            ball.getBall().setCenterY(ball.getBall().getCenterY() + ball.getSpeedY());
        } else {
            ball.setSpeedY(-ball.getSpeedY());
            ball.getBall().setCenterY(ball.getBall().getCenterY() + ball.getSpeedY());
        }
    }


    public static void startGame() {
        if (Main.player1Health > 0 && Main.player2Health > 0) {
            spawnBall();
            Ball.getInstance().randomBall();
            ballMovement();
        } else {   //End Game
            Text victoryMassage = new Text();
            victoryMassage.setVisible(false);
            victoryMassage.setX(260);
            victoryMassage.setY(200);
            victoryMassage.setFont(Font.font("LiquidCrystal", FontWeight.BOLD, FontPosture.REGULAR, 50));
            victoryMassage.setFill(Color.ORANGERED);
            Main.root.getChildren().add(victoryMassage);
            if (Main.player2Health == 0) {
                victoryMassage.setText("Player 1 Victory");
            } else if (Main.player1Health == 0) {
                victoryMassage.setText("Player 2 Victory");
            }

            Timeline victoryMassageTimeLine = new Timeline(new KeyFrame(Duration.seconds(0), event -> victoryMassage.setVisible(true)),
                    new KeyFrame(Duration.seconds(3), event -> {
                        victoryMassage.setVisible(false);
                        resetGame();
                    }));
            victoryMassageTimeLine.setCycleCount(1);
            victoryMassageTimeLine.play();

        }
    }

    public static void resetGame() {
        Main.player1ColorChooser.setVisible(true);
        Main.player2ColorChooser.setVisible(true);
        Main.exitButton.setVisible(true);
        Main.playButton.setVisible(true);
        Main.player1Helper.setVisible(false);
        Main.player2Helper.setVisible(false);

        Main.player1Rectangle.setFill(Color.GREEN);
        Main.player1Rectangle.setHeight(90);
        Main.player1ColorChooser.setSelected(false);

        Main.player2Rectangle.setFill(Color.GREEN);
        Main.player2Rectangle.setHeight(90);
        Main.player2ColorChooser.setSelected(false);

    }
}
