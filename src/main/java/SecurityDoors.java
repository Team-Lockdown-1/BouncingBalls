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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SecurityDoors extends Application {
    public static int doorTime = 5;                       // all doors -> close/open animation time
    public static final int cycleCount = 1;               // cycle of all doors; don't change -> else: animation problems
    public static int lockdownTime = HomeScreen.heal;     // time of lockdown (includes close animation time of doors)
    public static int doorCorners = 15;                   // doorCorners > 0 -> for round corners
    public static Color color = Color.STEELBLUE;          // color of doors
    public static double doorsWidth = 40.0;               // width of doors
    public static final double doorsHeight = 400.0;       // if changed -> merging of doors or on wrong positions
    public static double xPosLeftDoors = 360.0;           // starting X-position of left doors
    public static double xPosRightDoors = 720.0;          // starting X-position of right doors
    public static final double yPosUpperDoors = -223.0;   // starting Y-position of upper doors
    public static final double yPosLowerDoors = 930.0;    // starting Y-position of lower doors
    public static final double yDestUpperDoors = 153.0;   // destination Y-position of upper doors
    public static final double yDestLowerDoors = 554.0;   // destination Y-position of lower doors

    // waiting Y-position of doors | if variable is not used -> doors will spawn in the scene before animation starts
    public static final double positionY = -500.0;

    // infected balls > healthy ball -> delay time: when doors should react and start the closing animation; still buggy
    public static final int delay = 1000;
    public static final int period = 10;                  // don't change -> else: animation and starting problems

    public static boolean test = true;
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

        //Observerable list, is needed to display in realtime
        ObservableList items = FXCollections.observableArrayList();
        //healthy balls
        items.add("Gesund: " + BouncingBall.healthyBallsInList);
        //infected balls
        items.add("Infiziert: " + BouncingBall.infectedBallsInList);
        //creating a listview
        ListView list = new ListView();
        //put the observerable list into the listview, to display it on the screen
        list.getItems().addAll(items);
        root.getChildren().add(list);
        //set height & Width for the list
        list.setPrefHeight(50);
        list.setPrefWidth(180);
        list.setStyle("-fx-background-color: -fx-background ;" + "-fx-background-insets: 0;");

          //this code is needed to set the backgroundpicture
           root.setStyle("-fx-background-image: url('/"+HomeScreen.background+".jpg');");
//----------------------------------------------------------------------------------------------------------------


        Scene scene = new Scene(root, HomeScreen.WINDOW_WIDTH, HomeScreen.WINDOW_HEIGHT, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);


        //----------------------------------Security-Doors---------------------------------------------------------------
        Rectangle door1 = new Rectangle(xPosLeftDoors, positionY, doorsWidth, doorsHeight);     // door1 LeftUp
        Rectangle door2 = new Rectangle(xPosRightDoors, positionY, doorsWidth, doorsHeight);    // door2 RightUp
        Rectangle door3 = new Rectangle(xPosLeftDoors, positionY, doorsWidth, doorsHeight);     // door3 LeftDown
        Rectangle door4 = new Rectangle(xPosRightDoors, positionY, doorsWidth, doorsHeight);    // door4 RightDown

        for (Rectangle allDoors: Arrays.asList(door1,door2,door3,door4)){
            allDoors.setFill(color);
            allDoors.setArcHeight(doorCorners);
            allDoors.setArcWidth(doorCorners);
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                if(BouncingBall.healthyBallsInList < HomeScreen.balls/2){
                    if(!test){
                        timer.cancel();
                    }
                    test = false;

                    //door?_path (class: Polyline) -> actual path of doors
                    //door?_pathActivate (class: PathTransition) -> for methods and to show animation
                    Polyline door1_path = new Polyline();                       // to create the path ->
                    door1_path.getPoints().addAll(                              // from start to destination
                            xPosLeftDoors, yPosUpperDoors,                      // start position of door1
                            xPosLeftDoors, yDestUpperDoors);                    // destination position of door1
                    PathTransition door1_pathActivate = new PathTransition();
                    door1_pathActivate.setNode(door1);                          // which door is selected
                    door1_pathActivate.setDuration(Duration.seconds(doorTime)); // close animation time of door
                    door1_pathActivate.setPath(door1_path);                     // path of door
                    door1_pathActivate.setCycleCount(cycleCount);               // cycle of door
                    door1_pathActivate.play();                                  // show animation

                    Polyline door2_path = new Polyline();
                    door2_path.getPoints().addAll(
                            xPosRightDoors, yPosUpperDoors,                      // start position of door2
                            xPosRightDoors, yDestUpperDoors);                    // destination position of door2
                    PathTransition door2_pathActivate = new PathTransition();
                    door2_pathActivate.setNode(door2);
                    door2_pathActivate.setDuration(Duration.seconds(doorTime));
                    door2_pathActivate.setPath(door2_path);
                    door2_pathActivate.setCycleCount(cycleCount);
                    door2_pathActivate.play();

                    Polyline door3_path = new Polyline();
                    door3_path.getPoints().addAll(
                            xPosLeftDoors, yPosLowerDoors,                      // start position of door3
                            xPosLeftDoors, yDestLowerDoors);                    // destination position of door3
                    PathTransition door3_pathActivate = new PathTransition();
                    door3_pathActivate.setNode(door3);
                    door3_pathActivate.setDuration(Duration.seconds(doorTime));
                    door3_pathActivate.setPath(door3_path);
                    door3_pathActivate.setCycleCount(cycleCount);
                    door3_pathActivate.play();

                    Polyline door4_path = new Polyline();
                    door4_path.getPoints().addAll(
                            xPosRightDoors, yPosLowerDoors,                      // start position of door4
                            xPosRightDoors, yDestLowerDoors);                    // destination position of door4
                    PathTransition door4_pathActivate = new PathTransition();
                    door4_pathActivate.setNode(door4);
                    door4_pathActivate.setDuration(Duration.seconds(doorTime));
                    door4_pathActivate.setPath(door4_path);
                    door4_pathActivate.setCycleCount(cycleCount);
                    door4_pathActivate.play();


                    //----------------------Lockdown-Time-And-Open-Doors-------------------------------------------------

                    //door?_pathBack (class: Polyline) -> actual path of doors
                    //door?_pathBackActivate (class: PathTransition) -> for methods and to show animation
                    Timeline timelineLockdown = new Timeline();
                    timelineLockdown.getKeyFrames().add(new KeyFrame(Duration.seconds(lockdownTime), event -> {

                        Polyline door1_pathBack = new Polyline();                       // to create the path back ->
                        door1_pathBack.getPoints().addAll(                              // from destination to start
                                xPosLeftDoors, yDestUpperDoors,                         // destination position of door1
                                xPosLeftDoors, yPosUpperDoors);                         // start position of door1
                        PathTransition door1_pathBackActivate = new PathTransition();
                        door1_pathBackActivate.setNode(door1);                          // which door is selected
                        door1_pathBackActivate.setDuration(Duration.seconds(doorTime)); // open animation time of door
                        door1_pathBackActivate.setPath(door1_pathBack);                 // path back of door
                        door1_pathBackActivate.setCycleCount(cycleCount);               // cycle of door
                        door1_pathBackActivate.play();                                  // show animation

                        Polyline door2_pathBack = new Polyline();
                        door2_pathBack.getPoints().addAll(
                                xPosRightDoors, yDestUpperDoors,                        // destination position of door2
                                xPosRightDoors, yPosUpperDoors);                        // start position of door2
                        PathTransition door2_pathBackActivate = new PathTransition();
                        door2_pathBackActivate.setNode(door2);
                        door2_pathBackActivate.setDuration(Duration.seconds(doorTime));
                        door2_pathBackActivate.setPath(door2_pathBack);
                        door2_pathBackActivate.setCycleCount(cycleCount);
                        door2_pathBackActivate.play();

                        Polyline door3_pathBack = new Polyline();
                        door3_pathBack.getPoints().addAll(
                                xPosLeftDoors, yDestLowerDoors,                        // destination position of door3
                                xPosLeftDoors, yPosLowerDoors);                        // start position of door3
                        PathTransition door3_pathBackActivate = new PathTransition();
                        door3_pathBackActivate.setNode(door3);
                        door3_pathBackActivate.setDuration(Duration.seconds(doorTime));
                        door3_pathBackActivate.setPath(door3_pathBack);
                        door3_pathBackActivate.setCycleCount(cycleCount);
                        door3_pathBackActivate.play();

                        Polyline door4_pathBack = new Polyline();
                        door4_pathBack.getPoints().addAll(
                                xPosRightDoors, yDestLowerDoors,                        // destination position of door4
                                xPosRightDoors, yPosLowerDoors);                        // start position of door4
                        PathTransition door4_pathBackActivate = new PathTransition();
                        door4_pathBackActivate.setNode(door4);
                        door4_pathBackActivate.setDuration(Duration.seconds(doorTime));
                        door4_pathBackActivate.setPath(door4_pathBack);
                        door4_pathBackActivate.setCycleCount(cycleCount);
                        door4_pathBackActivate.play();
                    }));
                    timelineLockdown.play();
                }
            }
        }, delay, period);

        root.getChildren().addAll(canvas, door1, door2, door3, door4);

        //Generate Random Balls
        List<Integer> allX = new ArrayList<>();
        List<Integer> allY = new ArrayList<>();
        for (int i = 0; i < HomeScreen.balls; i++){
            float x = (int) (Math.random() * (HomeScreen.WINDOW_WIDTH - HomeScreen.size*2 - 20)) + HomeScreen.size + 10;
            float y = (int) (Math.random() * (HomeScreen.WINDOW_HEIGHT - HomeScreen.size*2 - 20)) + HomeScreen.size + 10;
            if(!allX.contains(x) && !allY.contains(y)){
                float angle = (float) (Math.random() * 360);
                BouncingBall ball = new BouncingBall(x, y, HomeScreen.speed, angle, HomeScreen.size);
                balls.add(ball);
                allX.addAll(IntStream.rangeClosed((int)x-HomeScreen.size*2, (int)x+HomeScreen.size*2).boxed().collect(Collectors.toList()));
                allY.addAll(IntStream.rangeClosed((int)y-HomeScreen.size*2, (int)y+HomeScreen.size*2).boxed().collect(Collectors.toList()));
            }else{
                i--;
            }
        }
        //all balls infected
        //balls.stream().forEach( b -> b.setInfected(true));
        balls.get(0).setInfected(true);
        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), t -> {
            //clear observerable list
            items.clear();
            //load in new number of healthy balls
            items.add("Gesund: " + BouncingBall.healthyBallsInList);
            //load in new number of infected balls
            items.add("Infiziert: " + BouncingBall.infectedBallsInList);
            //clear list
            list.getItems().clear();
            //load everything in the observerable list
            list.getItems().addAll(items);

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

                /*
                //Collision with door1
                if (ball.getX() <= door1.getX() && ball.getX() >= door1.getX()+door1.getWidth() && ball.getY() <= door1.getY()+door1.getHeight()){
                    ball.setVelX(-ball.getVelX());
                }

                System.out.println("Door1: x: " + door1.getX() + " y: " + door1.getY());
                */
            }

            for(BouncingBall ball : balls){
                if(!ball.isCollided()) {
                    List<BouncingBall> balls2 = new ArrayList<>(balls);
                    balls2.remove(ball);
                    float x = ball.getX();
                    float y = ball.getY();
                    int r = ball.getRadius();
                    float velX_ball = ball.getVelX();
                    float velY_ball = ball.getVelY();

                    for (BouncingBall b : balls2) {
                        float x2 = b.getX();
                        float y2 = b.getY();
                        float velX_other = b.getVelX();
                        float velY_other = b.getVelY();

                        if (Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2)) <= r*2) {

                            ball.setVelX(velX_other);
                            ball.setVelY(velY_other);
                            ball.setCollided(true);
                            b.setVelX(velX_ball);
                            b.setVelY(velY_ball);
                            b.setCollided(true);

                            if(ball.isInfected() || b.isInfected()){
                                ball.setInfected(true);
                                ball.onHit();
                                b.setInfected(true);
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
                //this method updates the infected list
                if(ball.isInfected() && ball.hasNotBeenInfectedOnce){
                    BouncingBall.infectedCounter(ball);
                }
                //this method updates the healthy list
                if(!ball.isInfected() && !ball.hasNotBeenInfectedOnce && ball.hasBeenHealed){
                    BouncingBall.regenerationCounter(ball);
                }

            }


        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        //On ESC pressed go back to HomeScreen
        list.setOnKeyPressed(event -> {
            timeline.stop();
            //close the scene
            Stage thisStage = (Stage) list.getScene().getWindow();
            thisStage.close();
            if (event.getCode() == KeyCode.ESCAPE) {
                //open new scene
                HomeScreen home = new HomeScreen();
                try {
                    home.start(Start.classStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
