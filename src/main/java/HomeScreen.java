import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreen extends Application {
    public static int balls = 40;
    public static int size = 10;
    public static int speed = 5;
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1080, 720, Color.BLACK);

        Button start = new Button("Start");

        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();
    }

}
