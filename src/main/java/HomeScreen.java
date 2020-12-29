import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    static Stage classStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        classStage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);

        Button start = new Button("Start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start balls = new Start();
                try {
                    balls.start(Start.classStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage1 = (Stage) start.getScene().getWindow();
                stage1.close();
            }
        });
        root.getChildren().add(start);


        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();
    }

}
