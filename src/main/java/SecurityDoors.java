import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
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
    public static int doorTime = 5;               // all doors -> close/open animation time
    public static final int cycleCount = 1;       // cycle of all doors; should be 1 -> else: animation problems
    public static int lockdownTime = 10;          // time of lockdown (includes close animation time of doors)
    public static int doorCorners = 15;           // doorCorners > 0 -> for round corners
    public static Color color = Color.STEELBLUE;  // color of doors
    public static final int doorsWidth = 40;      //
    public static final int doorsHeight = 400;    // shouldn't be changed because of class: Polyline
                                                  // else: merging of doors or on wrong positions

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

//------------------------------ Mattias ----------------------------------------------------------------------
        ObservableList items = FXCollections.observableArrayList();
        items.add("Gesund: " + 2);
        items.add("Infiziert: " + BouncingBall.test);
        ListView list = new ListView();
        list.getItems().addAll(items);
        root.getChildren().add(list);
        list.setPrefHeight(50);
        list.setPrefWidth(180);
        list.setStyle("-fx-background-color: -fx-background ;" + "-fx-background-insets: 0;");

        final Timeline updateList = new Timeline(
                new KeyFrame(
                        Duration.millis( 1 ),
                        event -> {
                            items.clear();
                            items.add("Gesund: " + BouncingBall.tryit);
                            items.add("Infiziert: " + BouncingBall.test);
                            list.getItems().clear();
                            list.getItems().addAll(items);
                        }
                )

        );
        updateList.setCycleCount( Animation.INDEFINITE );
        updateList.play();

          //this code is needed to set the backgroundpicture MFG Mattias
           root.setStyle("-fx-background-image: url('/"+HomeScreen.background+".jpg');");
//---------------------------Mattas-------------------------------------------------------------------------------------


        Scene scene = new Scene(root, HomeScreen.WINDOW_WIDTH, HomeScreen.WINDOW_HEIGHT, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        //----------------------------------Security-Doors------------------------------------------
        Rectangle door1 = new Rectangle(doorsWidth, doorsHeight);   // door1 LeftUp
        Rectangle door2 = new Rectangle(doorsWidth, doorsHeight);   // door2 RightUp
        Rectangle door3 = new Rectangle(doorsWidth, doorsHeight);   // door3 LeftDown
        Rectangle door4 = new Rectangle(doorsWidth, doorsHeight);   // door4 RightDown

        door1.setFill(color);
        door2.setFill(color);
        door3.setFill(color);
        door4.setFill(color);

        door1.setArcWidth(doorCorners);
        door1.setArcHeight(doorCorners);
        door2.setArcWidth(doorCorners);
        door2.setArcHeight(doorCorners);
        door3.setArcWidth(doorCorners);
        door3.setArcHeight(doorCorners);
        door4.setArcWidth(doorCorners);
        door4.setArcHeight(doorCorners);


        //door?_path  &  door?_pathBack (class: Polyline) -> actual path of doors
        //door?_pathActivate  &  door?_pathBackActivate (class: PathTransition) -> for methods
        Polyline door1_path = new Polyline();
        door1_path.getPoints().addAll(360.0, -223.0,
                360.0, 153.0);
        PathTransition door1_pathActivate = new PathTransition();
        door1_pathActivate.setNode(door1);
        door1_pathActivate.setDuration(Duration.seconds(doorTime));
        door1_pathActivate.setPath(door1_path);
        door1_pathActivate.setCycleCount(cycleCount);
        door1_pathActivate.play();

        Polyline door2_path = new Polyline();
        door2_path.getPoints().addAll(720.0, -223.0,
                720.0, 153.0);
        PathTransition door2_pathActivate = new PathTransition();
        door2_pathActivate.setNode(door2);
        door2_pathActivate.setDuration(Duration.seconds(doorTime));
        door2_pathActivate.setPath(door2_path);
        door2_pathActivate.setCycleCount(cycleCount);
        door2_pathActivate.play();

        Polyline door3_path = new Polyline();
        door3_path.getPoints().addAll(360.0, 930.0,
                360.0, 554.0);
        PathTransition door3_pathActivate = new PathTransition();
        door3_pathActivate.setNode(door3);
        door3_pathActivate.setDuration(Duration.seconds(doorTime));
        door3_pathActivate.setPath(door3_path);
        door3_pathActivate.setCycleCount(cycleCount);
        door3_pathActivate.play();

        Polyline door4_path = new Polyline();
        door4_path.getPoints().addAll(720.0, 930.0,
                720.0, 554.0);
        PathTransition door4_pathActivate = new PathTransition();
        door4_pathActivate.setNode(door4);
        door4_pathActivate.setDuration(Duration.seconds(doorTime));
        door4_pathActivate.setPath(door4_path);
        door4_pathActivate.setCycleCount(cycleCount);
        door4_pathActivate.play();


        //----------------------Lockdown-Time-And-Open-Doors--------------------------------------------
        Timeline time = new Timeline(new KeyFrame(Duration.seconds(lockdownTime), event -> {

            Polyline door1_pathBack = new Polyline();
            door1_pathBack.getPoints().addAll(360.0, 153.0,
                    360.0, -223.0);
            PathTransition door1_pathBackActivate = new PathTransition();
            door1_pathBackActivate.setNode(door1);
            door1_pathBackActivate.setPath(door1_pathBack);
            door1_pathBackActivate.setDuration(Duration.seconds(doorTime));
            door1_pathBackActivate.setCycleCount(cycleCount);
            door1_pathBackActivate.play();

            Polyline door2_pathBack = new Polyline();
            door2_pathBack.getPoints().addAll(720.0, 153.0,
                    720.0, -223.0);
            PathTransition door2_pathBackActivate = new PathTransition();
            door2_pathBackActivate.setNode(door2);
            door2_pathBackActivate.setPath(door2_pathBack);
            door2_pathBackActivate.setDuration(Duration.seconds(doorTime));
            door2_pathBackActivate.setCycleCount(cycleCount);
            door2_pathBackActivate.play();

            Polyline door3_pathBack = new Polyline();
            door3_pathBack.getPoints().addAll(360.0, 554.0,
                    360.0, 930.0);
            PathTransition door3_pathBackActivate = new PathTransition();
            door3_pathBackActivate.setNode(door3);
            door3_pathBackActivate.setPath(door3_pathBack);
            door3_pathBackActivate.setDuration(Duration.seconds(doorTime));
            door3_pathBackActivate.setCycleCount(cycleCount);
            door3_pathBackActivate.play();

            Polyline door4_pathBack = new Polyline();
            door4_pathBack.getPoints().addAll(720.0, 554.0,
                    720.0, 930.0);
            PathTransition door4_pathBackActivate = new PathTransition();
            door4_pathBackActivate.setNode(door4);
            door4_pathBackActivate.setPath(door4_pathBack);
            door4_pathBackActivate.setDuration(Duration.seconds(doorTime));
            door4_pathBackActivate.setCycleCount(cycleCount);
            door4_pathBackActivate.play();
        }));
        time.play();

        root.getChildren().addAll(canvas, door1, door2, door3, door4);

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
                //System.out.println(ball.getHeal());
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
