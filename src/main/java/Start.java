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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Start extends Application {

    private final int securityDoorWidth = 40;
    private final double securityDoorHeight = 358.5;

    static Stage classStage = new Stage();

    private List<BouncingBall> balls = new ArrayList<>();

  /* public static void main(String[] args) {
        launch(args);
    }

   */

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

          //this code is needed to set the backgroundpicture
           root.setStyle("-fx-background-image: url('/"+HomeScreen.background+".jpg');");

        Scene scene = new Scene(root, HomeScreen.WINDOW_WIDTH, HomeScreen.WINDOW_HEIGHT, Color.BLACK);
        Canvas canvas = new Canvas(scene.getWidth(), scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);
        root.getChildren().addAll(canvas);

        // Generate The Doors
        List<Rectangle> doors = new ArrayList<>();
        Rectangle door1 = new Rectangle(HomeScreen.WINDOW_WIDTH/3, -securityDoorHeight, securityDoorWidth, securityDoorHeight);
        doors.add(door1);
        Rectangle door2 = new Rectangle(HomeScreen.WINDOW_WIDTH/3*2, -securityDoorHeight, securityDoorWidth, securityDoorHeight);
        doors.add(door2);
        Rectangle door3 = new Rectangle(HomeScreen.WINDOW_WIDTH/3, canvas.getHeight(), securityDoorWidth, securityDoorHeight+20);
        doors.add(door3);
        Rectangle door4 = new Rectangle(HomeScreen.WINDOW_WIDTH/3*2, canvas.getHeight(), securityDoorWidth, securityDoorHeight+20);
        doors.add(door4);
        AtomicBoolean timer = new AtomicBoolean(false); // threadsave -> no deadlock
        AtomicBoolean timer2 = new AtomicBoolean(false);
        AtomicReference<Float> step = new AtomicReference<>((float) securityDoorHeight/250);
        AtomicReference<Float> stop = new AtomicReference<>((float) securityDoorHeight);

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
                }else {
                    gc.setFill(Color.BLACK);
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

            //Draw Security Doors
            if (HomeScreen.security_doors){
                gc.setFill(Color.STEELBLUE);
                for(Rectangle door : doors){
                    gc.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
                }
                if(BouncingBall.infectedBallsInList >= HomeScreen.balls/2 && !timer.get() && !timer2.get() && doors.get(0).getY() < -securityDoorHeight+100) {
                    System.out.println("Set Timer");
                    timer.set(true);
                } else if(BouncingBall.healthyBallsInList >= BouncingBall.infectedBallsInList && !timer.get() && !timer2.get() && doors.get(0).getY() > -securityDoorHeight+100) {
                    System.out.println("Set Timer2");
                    timer2.set(true);
                }
                if(timer.get()){
                    if( stop.get() <= 0 ){
                        timer.set(false);
                        stop.set((float) securityDoorHeight);
                    }
                    for(Rectangle door : doors){
                        if (door.getY()<=0){
                            door.setY(door.getY() + step.get());
                        }else {
                            door.setY(door.getY() - step.get());
                        }
                    }
                    stop.set(stop.get()-step.get());
                    //System.out.println(stop.get());
                } else if (timer2.get()){
                    if( stop.get() <= 0 ){
                        timer2.set(false);
                        stop.set((float) securityDoorHeight);
                    }
                    for(Rectangle door : doors){
                        if (door.getY()<=canvas.getHeight()/4){
                            door.setY(door.getY() - step.get());
                        }else {
                            door.setY(door.getY() + step.get());
                        }
                    }
                    stop.set(stop.get()-step.get());
                }
            }

            //Collisions with other balls
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

                            /* old collision vektor calculating
                            System.out.println("Ball Start x: " + velX_ball+ " | Start y: " + velY_ball);
                            System.out.println("Other Start x: " + velX_other + " | Start y: " + velY_other);

                            double contactAngle = -Math.atan2(velY_other-velY_ball, velX_other-velX_ball);

                            double velX_ball_rotated = velX_ball * Math.cos(contactAngle) - velY_ball * Math.sin(contactAngle);
                            double velY_ball_rotated = velX_ball * Math.sin(contactAngle) + velY_ball * Math.cos(contactAngle);

                            double velX_other_rotated = velX_other * Math.cos(contactAngle) - velY_other * Math.sin(contactAngle);
                            double velY_other_rotated = velX_other * Math.sin(contactAngle) + velY_other * Math.cos(contactAngle);

                            double velX_ball_afterCol = velX_ball_rotated * (1-1)/(1+1) + velX_other_rotated * 2 * 1 / (1+1);
                            double velY_ball_afterCol = velY_ball_rotated;

                            double velX_other_afterCol = velX_other_rotated * (1-1)/(1+1) + velX_ball_rotated * 2 * 1 / (1+1);
                            double velY_other_afterCol = velY_other_rotated;

                            double velX_ball_final = velX_ball_afterCol * Math.cos(-contactAngle) - velY_ball_afterCol * Math.sin(-contactAngle);
                            double velY_ball_final = velX_ball_afterCol * Math.sin(-contactAngle) + velY_ball_afterCol * Math.cos(-contactAngle);

                            double velX_other_final = velX_other_afterCol * Math.cos(-contactAngle) - velY_other_afterCol * Math.sin(-contactAngle);
                            double velY_other_final = velX_other_afterCol * Math.sin(-contactAngle) + velY_other_afterCol * Math.cos(-contactAngle);

                            System.out.println("Ball Final x: " + velX_ball_final + " | Final y: " + velY_ball_final);
                            System.out.println("Other Final x: " + velX_other_final + " | Final y: " + velY_other_final);

                            ball.setVelX((float)velX_ball_final);
                            ball.setVelY((float)velY_ball_final);
                            ball.setCollided(true);

                            b.setVelX((float)velX_other_final);
                            b.setVelX((float)velY_other_final);
                            b.setCollided(true);
                            */

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

            /*
            for (BouncingBall ball:balls){

                if (!ball.isCollided()) {

                    List<BouncingBall> balls2 = new ArrayList<>(balls);
                    balls2.remove(ball);
                    float x = ball.getX();
                    float y = ball.getY();
                    int r = ball.getRadius();

                    for (BouncingBall b : balls2) {
                        float x2 = b.getX();
                        float y2 = b.getY();

                        if (Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2)) <= r) {

                            double angle_ball = Math.atan(ball.getVelY() / ball.getVelX());
                            double angle_b = Math.atan(b.getVelY() / b.getVelX());
                            double angle_contact = Math.acos((x*x2 + y*y2) / ( Math.sqrt(x*x + y*y) * Math.sqrt(x2*x2 + y2*y2) ));
                            double zähler = Math.cos(angle_b - angle_contact) * Math.sin(angle_contact) + Math.sin(angle_ball - angle_contact) * Math.sin(angle_contact + (Math.PI / 2));
                            double nenner = Math.cos(angle_b - angle_contact) * Math.cos(angle_contact) + Math.sin(angle_ball - angle_contact) * Math.cos(angle_contact + (Math.PI / 2));
                            double new_angle_ball = Math.atan(zähler / nenner);
                            ball.setSpeed(new_angle_ball);
                            zähler = Math.cos(angle_ball - angle_contact) * Math.sin(angle_contact) + Math.sin(angle_b - angle_contact) * Math.sin(angle_contact + (Math.PI / 2));
                            nenner = Math.cos(angle_ball - angle_contact) * Math.cos(angle_contact) + Math.sin(angle_b - angle_contact) * Math.cos(angle_contact + (Math.PI / 2));
                            new_angle_ball = Math.atan(zähler / nenner);
                            b.setSpeed(new_angle_ball);
                            ball.setCollided(true);
                            b.setCollided(true);

                            double beta_ball = Math.atan((y - y2) / (x - x2));
                            double alpha_ball = Math.atan(ball.getVelX() / ball.getVelY());
                            double new_angle_ball = Math.PI - alpha_ball - beta_ball;
                            ball.setSpeedAfterCollision(new_angle_ball);
                            ball.setCollided(true);

                            double beta_b = Math.atan((y2-y)/(x2-x));
                            double alpha_b = Math.atan(b.getVelX()/b.getVelY());
                            double new_angle_b = Math.PI - alpha_b - beta_b;
                            b.setSpeedAfterCollision(new_angle_b);
                            b.setCollided(true);

                        }

                    }

                }

            }*/

            //Collision with doors
            for(BouncingBall ball : balls){
                float x = ball.getX();
                float y = ball.getY();
                int r = ball.getRadius();
                //Collision with doors
                for (Rectangle door : doors){
                    if ( x+r*2 > door.getX() && x < door.getX()+door.getWidth() && y < door.getY()+securityDoorHeight && y > door.getY() ){
                        //System.out.println("Door col");
                        ball.setVelX(-ball.getVelX());
                    }
                }
            }


            for (BouncingBall ball:balls){
                ball.setCollided(false);
                if(ball.isInfected()){
                    ball.genesung();
                }
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

            if (event.getCode() == KeyCode.ESCAPE) {
                timeline.stop();
                //close the scene
                Stage thisStage = (Stage) list.getScene().getWindow();
                thisStage.close();
                //open new scene
               // HomeScreen home = new HomeScreen();
                try {
                    //home.start(Start.classStage);
                    HomeScreen.classStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // ENTER -> all balls infected
            if(event.getCode() == KeyCode.ENTER){
                balls.stream().forEach( b -> b.setInfected(true));
            }

        });

    }
}