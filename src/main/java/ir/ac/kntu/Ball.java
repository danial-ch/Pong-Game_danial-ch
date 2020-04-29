package ir.ac.kntu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball{

    private static Ball single_instance = null;

    private Circle ball = new Circle();
    private int speedAmount;
    private double speedX;
    private double speedY;

    public static Ball getInstance() {
        if (single_instance == null)
            single_instance = new Ball();

        return single_instance;
    }

    private Ball() {
        ball.setRadius(12);
        randomBall();
    }

    public void randomBall(){
        boolean isBlue = new Random().nextBoolean();
        if(isBlue){
            speedAmount=10;
            this.ball.setFill(Color.BLUE);
        }
        else{
            speedAmount=7;
            this.ball.setFill(Color.RED);
        }
    }

    public Circle getBall() {
        return this.ball;
    }

    public int getSpeedAmount() {
        return speedAmount;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
