package ir.ac.kntu;

import javafx.animation.AnimationTimer;

public class AI {

    public static void AIStart(){

        AnimationTimer AIMovementTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Main.player2Rectangle.setY(Ball.getInstance().getBall().getCenterY()-(Main.player2Rectangle.getHeight()/2));
            }
        };
        AIMovementTimer.start();
    }

}
