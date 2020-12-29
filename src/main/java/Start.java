import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
        Scene scene = new Scene(root, HomeScreen.WINDOW_WIDTH, HomeScreen.WINDOW_HEIGHT, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        root.getChildren().add(canvas);

        //On ESC pressed go back to HomeScreen
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                //TODO exit the Scene back to Main MENU
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

        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {

            gc.clearRect(0, 0, scene.getWidth(), scene.getHeight());

            for (BouncingBall ball : balls) {
                gc.fillOval(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);

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

                for(BouncingBall b : balls2){
                    if(x+r*2>=b.getX() && x-r*2<=b.getX() && y+r*2>=b.getY() && y-r*2<=b.getY()){

                        ball.setVelX(-ball.getVelX());

                    }
                }
            }

            for (BouncingBall ball: balls){
                //TODO Collision Security Doors Nam Loc
            }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}