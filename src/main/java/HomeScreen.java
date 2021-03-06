import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HomeScreen extends Application {

    //Settings can change the value of balls, which changes the amount of balls in the game
    protected static int balls = 20;
    protected static int size = 10;
    //Settings can change the value of speed, which changes the speed of the balls in the game
    protected static int speed = 5;
    //Settings can change the value of hits, which changes the infection rate
    protected static int hits = 1;
    //Settings can change the value of heal, which changes the time the balls need to be healty again
    protected static int heal = 40;
    //Settings can change the value of security_doors, which sets security doors on/off
    protected static boolean security_doors = false;
    //Settings can change the value of the background, which changes the background of the game
    protected static String background = "amsterdam";
    protected static final int WINDOW_WIDTH = 1080;
    protected static final int WINDOW_HEIGHT = 720;
    static Stage classStage = new Stage();


    /*
    This start methode contains some Settings and initializes the HomeScreen
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        classStage = primaryStage;
        // FXMLLoader loads the JavaFX fxml file
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        classStage.setTitle("Bouncing Balls");
        classStage.setResizable(false);
        // Sets a new scene with the loaded SceneBuilder file. This has a fixed size
        classStage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        classStage.show();
    }

    public static void setSecurity_doors(String x) {
        security_doors = x.equals("yes");
    }

    public static void setHeal(int x) {
        heal = x;
    }

    public static void setBalls(int x) {
        balls = x;
    }

    public static void setSpeed(String x) {
        if (x.equals("Langsam")) {
            speed = 4;
        } else if (x.equals("Mittel")) {
            speed = 5;
        } else {
            speed = 6;
        }
    }

    public static String getSpeed() {
        if (speed == 4) {
            return "Langsam";
        } else if (speed == 5) {
            return "Mittel";
        } else {
            return "Schnell";
        }
    }
    public static int getRegeneration(){
        if(heal == 40){
           return 10;
        }else if(heal == 80){
            return 20;
        }else{
            return 30;
        }
    }
    public static void setRegeneration(int x){
        if(x == 10){
            heal = 40;
        }else if(x == 20){
            heal = 80;
        }else{
            heal = 120;
        }
    }

    public static void setBackground(String x) {
        background = x;
    }

    /*
    The clickedStart Method is an onAction event in Scene Builder
    The HomeScreen.java class was set as the HomeScreen class in Scene Builder
    The sourcecode of the Start Class was started here
     */
    public void clickedStart() {
        Start balls = new Start();
        BouncingBall.resetList();
        // Tries to start the simulation and if an error occurs, it prints out the error
        try {
                balls.start(Start.classStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // Hides the HomeScreen
        HomeScreen.classStage.hide();
    }

    /*
    The clickedSettings Method is an onAction event in Scene Builder
    The sourcecode of the Settings Class was started here
     */
    public void clickedSettings() {
        // Loads the scene from the settings
        Stage settingScreen = new Stage();
        // Loads the interface of the settings
        HBox interfaceFromSetting = Setting.getHBox();
        // Creates a new scene with the interface of the settings with size 800x600
        Scene secondScene = new Scene(interfaceFromSetting, 800, 600);
        // Sets the settings scene and shows the settings screen
        settingScreen.setScene(secondScene);
        settingScreen.show();


    }
}
