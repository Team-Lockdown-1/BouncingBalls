import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SecurityDoors extends Application {

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

        //----------------------------------Security-Doors------------------------------------------
        Rectangle r1 = new Rectangle(360,0,40,355);   // r1 LeftUp
        Rectangle r2 = new Rectangle(720,0,40,355);   // r2 RightUp
        Rectangle r3 = new Rectangle(360,0,40,345); // r3 LeftDown
        Rectangle r4 = new Rectangle(720,462,40,345); // r4 RightDown

        r1.setFill(Color.STEELBLUE);
        r2.setFill(Color.STEELBLUE);
        r3.setFill(Color.STEELBLUE);
        r4.setFill(Color.STEELBLUE);
        r1.setArcWidth(15);                  //setArcWidth and setArcHeight -> for round corners
        r1.setArcHeight(15);
        r2.setArcWidth(15);
        r2.setArcHeight(15);
        r3.setArcWidth(15);
        r3.setArcHeight(15);
        r4.setArcWidth(15);
        r4.setArcHeight(15);


        Polyline path_r1 = new Polyline();
        path_r1.getPoints().addAll(360.0, -200.0,
                360.0, 180.0);
        PathTransition p1 = new PathTransition();
        p1.setNode(r1);
        p1.setDuration(Duration.seconds(20));
        p1.setPath(path_r1);
        p1.setCycleCount(1);
        p1.play();

        Polyline path_r2 = new Polyline();
        path_r2.getPoints().addAll(720.0, -200.0,
                720.0, 180.0);
        PathTransition p2 = new PathTransition();
        p2.setNode(r2);
        p2.setDuration(Duration.seconds(10));
        p2.setPath(path_r2);
        p2.setCycleCount(1);
        p2.play();

        Polyline path_r3 = new Polyline();
        path_r3.getPoints().addAll(360.0, 900.0,
                360.0, 530.0);
        PathTransition p3 = new PathTransition();
        p3.setNode(r3);
        p3.setDuration(Duration.seconds(20));
        p3.setPath(path_r3);
        p3.setCycleCount(1);
        p3.play();

        Polyline path_r4 = new Polyline();
        path_r4.getPoints().addAll(720.0, 900.0,
                720.0, 530.0);
        PathTransition p4 = new PathTransition();
        p4.setNode(r4);
        p4.setDuration(Duration.seconds(10));
        p4.setPath(path_r4);
        p4.setCycleCount(1);
        p4.play();

        //----------------------------------Security-Doors------------------------------------------

        root.getChildren().addAll(canvas, r1, r2, r3, r4);
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
