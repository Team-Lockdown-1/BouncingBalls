import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Start extends Application {

    static Stage classStage = new Stage();

    private List<BouncingBall> balls = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) throws Exception {
        classStage = stage;
        //Make the Scene and the Canvas
        Pane root = new Pane();
        /*
         * //this code is needed to set the backgroundpicture MFG Mattias
         *  root.setStyle("-fx-background-image: url('/"+HomeScreen.background+".jpg');");
         */
        Scene scene = new Scene(root, HomeScreen.WINDOW_WIDTH, HomeScreen.WINDOW_HEIGHT, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        root.getChildren().addAll(canvas);

        //On ESC pressed go back to HomeScreen
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("Enter Pressed");
                HomeScreen home = new HomeScreen();
                try {
                    home.start(Start.classStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Generate Random Balls
        //TODO make no Balls spawn on top of eachother
        for (int i = 0; i < HomeScreen.balls; i++){
            float x = (float) (Math.random() * (HomeScreen.WINDOW_WIDTH - HomeScreen.size*2 - 20)) + HomeScreen.size + 10;
            float y = (float) (Math.random() * (HomeScreen.WINDOW_HEIGHT - HomeScreen.size*2 - 20)) + HomeScreen.size + 10;
            float angle = (float) (Math.random() * 360);
            BouncingBall ball = new BouncingBall(x, y, HomeScreen.speed, angle, HomeScreen.size);
            balls.add(ball);
        }
        balls.get(0).setInfected(true);
        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {

            gc.clearRect(0, 0, scene.getWidth(), scene.getHeight());

            for (BouncingBall ball : balls) {
                if (ball.isInfected()){
                    gc.setFill(Color.GREEN);
                    gc.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);
                    gc.setFill(Color.BLACK);
                }else {
                    gc.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);
                }


                ball.setNewPos();

                //If the ball reaches the left or right border make the step negative
                if (ball.getX() <= 1 || ball.getX()+ball.getRadius()*2 >= canvas.getWidth()) {
                    ball.setVelX(-ball.getVelX());
                }
                //If the ball reaches the bottom or top border make the step negative
                if (ball.getY() <= 1 || ball.getY()+ball.getRadius()*2 >= canvas.getHeight()) {
                    ball.setVelY(-ball.getVelY());
                }
            }

            for (BouncingBall ball : balls) {
                List<BouncingBall> balls2 = new ArrayList<>(balls);
                balls2.remove(ball);
                float x = ball.getX();
                float y = ball.getY();
                int r = ball.getRadius();

                if(!ball.isCollided()) {
                    for (BouncingBall b : balls2) {
                        if (x + r * 2 >= b.getX() && x - r * 2 <= b.getX() && y + r * 2 >= b.getY() && y - r * 2 <= b.getY()) {
                            if (Math.abs(y - b.getY()) > Math.abs(x - b.getX())) {
                                ball.setVelX(-ball.getVelX());
                            } else {
                                ball.setVelY(-ball.getVelY());
                            }
                            ball.setCollided(true);
                            b.setCollided(true);
                            if(ball.isInfected() || b.isInfected()){
                                ball.onHit();
                                b.onHit();
                            }
                        }
                    }
                }
            }

            for (BouncingBall ball:balls){
                ball.setCollided(false);
                if(ball.isInfected()){
                    ball.genesung();
                }
                System.out.println(ball.getHeal());
                if(ball.getHeal()<=0){
                    ball.setInfected(false);
                    ball.setHeal(HomeScreen.heal);
                }
            }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}