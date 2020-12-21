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

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1080, 720, Color.BLACK);
        //TODO make the canvas resizable
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        root.getChildren().add(canvas);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                //TODO exit the Scene back to Main MENU
                System.out.println("Enter Pressed");
            }
        });

        BouncingBall[] balls = new BouncingBall[40];

        for (int i = 0; i < balls.length; i++){
            BouncingBall ball = new BouncingBall((int) canvas.getWidth()/2, (int) canvas.getHeight()/2, 10);
            balls[i] = ball;
        }

        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {

            //TODO implement Collision with other balls

            //move the ball
            gc.clearRect(0, 0, scene.getWidth(), scene.getHeight());

            for (BouncingBall ball : balls) {
                gc.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);

                ball.setNewPos();

                int x = ball.getX();
                int y = ball.getY();
                int r = ball.getRadius();

                

                //If the ball reaches the left or right border make the step negative
                if (x <= 0 || x+r*2 >= scene.getWidth()) {
                    ball.setVelX(-1);
                }
                //If the ball reaches the bottom or top border make the step negative
                if (y <= 0 || y+r*2 >= scene.getHeight()) {
                    ball.setVelY(-1);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}