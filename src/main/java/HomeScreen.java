import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomeScreen extends Application {
    //Settings can change the value of balls variable, which changes the amount of balls in the game
    public static int balls = 20;
    public static int size = 10;
    //Settings can change the value of speed variable, which changes the speed of the balls in the game
    public static int speed = 6;
    //Settings can change the value of hits variable, which changes the infection rate
    public static int hits = 1;
    //Settings can change the value of heal variable, which changes the time the balls need to be healty again
    public static int heal = 30;
    //Settings can change the value of security_doors, which sets security doors on/off
    public static boolean security_doors = true; //TODO in die settings geben
    //Settings can change the value of the background variable, which changes the background of the game
    public static String background = "amsterdam";
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    static Stage classStage = new Stage();

    public static void setSecurity_doors(String x){
        if (x.equals("yes")) {
            security_doors = true;
        }else{
            security_doors = false;
        }
    }

    public static void setHeal(int x){
        heal = x;
    }

    public static void setBalls(int x){
        balls = x;
    }
    public static void setSpeed(String x){
        if(x.equals("Langsam")){
            speed = 3;
        }else if(x.equals("Mittel")){
            speed = 6;
        }else{
            speed = 9;
        }
    }
    public static String getSpeed(){
        if(speed == 3){
            return "Langsam";
        }else if(speed == 6){
            return "Mittel";
        }else{
            return "Schnell";
        }
    }
    public static void setHits(int x){
        hits = x;
    }
    public static void setBackground(String x){
        background = x;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        classStage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
        Button start = new Button("Start");
        Button setting = new Button("Settings");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Start balls = new Start();
                SecurityDoors doors = new SecurityDoors();
                //infected list will be set to default value
                BouncingBall.resetList();
                try {
                    if(security_doors){
                        doors.start(SecurityDoors.classStage);
                    } else {
                        balls.start(Start.classStage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Stage stage1 = (Stage) start.getScene().getWindow();
                stage1.close();
            }
        });
        setting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //create a new stage
                Stage settingScreen = Setting.getStage();
                //load HBOX from Settings
                HBox interfaceFromSetting = Setting.getHBox();
                //create a scene
                Scene secondScene = new Scene(interfaceFromSetting,800,600);
                settingScreen.setScene(secondScene);
                settingScreen.setResizable(false);
                //open new window with settings
                settingScreen.show();
            }
        });

        root.getChildren().add(start);
        setting.setTranslateX(50);
        root.getChildren().add(setting);

        stage.setTitle("Bouncing Balls!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
