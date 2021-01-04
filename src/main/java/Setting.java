import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;


//scene resolution has to be 800x600
public class Setting extends Application {
    private static Stage guiStage;

//hands over the  guiStage to the HomeScreen class
    public static Stage getStage() {
        Stage guiStage = new Stage();
        return guiStage;
    }

    //getHBox() is the main interface for the Settings screen
    public static HBox getHBox() {

        //Basic structure for the scene.
        HBox mainBox = new HBox();
        VBox leftMainBox = new VBox();
        VBox rightMainBox = new VBox();
        HBox leftMainBoxUpperHalf = new HBox();
        HBox leftMainBoxLowerHalf = new HBox();
        VBox rightMainBoxVertical = new VBox();
        HBox HBoxBall = new HBox();
        HBox HBoxSpeed = new HBox();
        HBox HBoxInfected = new HBox();
        HBox HBoxRegeneration = new HBox();
        HBox HBoxSecurityDoors = new HBox();

        //combine the previous created HBox & VBox
        mainBox.getChildren().add(leftMainBox);
        mainBox.getChildren().add(rightMainBox);
        leftMainBox.getChildren().add(leftMainBoxUpperHalf);
        leftMainBox.getChildren().add(leftMainBoxLowerHalf);
        leftMainBoxUpperHalf.getChildren().add(rightMainBoxVertical);

        //set the text which is written in the boxes to the position center
        for (HBox x : Arrays.asList(leftMainBoxLowerHalf, leftMainBoxUpperHalf, HBoxBall, HBoxSpeed, HBoxInfected, HBoxRegeneration, HBoxSecurityDoors)) {
            x.setAlignment(Pos.CENTER);
        }
        rightMainBoxVertical.setAlignment(Pos.TOP_CENTER);


        //create Lables for the left side of the Setting screen
        Label headingSettings = new Label("Settings");
        Label labelNumberOfBalls = new Label("Anzahl der Baelle");
        Label labelGeschwindigkeit = new Label("Geschwindigkeit");
        Label labelInfected = new Label("Chance zum infizieren");
        Label labelHealing = new Label("Heilung");
        Label labelSecurityDoors = new Label("Security Doors");

        //Font size - der ganze Label Text wird größer und fett geschrieben, für die lesbarkeit
        headingSettings.setStyle("-fx-font-size: 20;" + "-fx-font-weight: bold");
        for (Label x : Arrays.asList(labelNumberOfBalls, labelGeschwindigkeit, labelInfected, labelHealing, labelSecurityDoors)) {
            x.setStyle("-fx-font-weight: bold");
        }


        //add radio buttons to the rightMianBoxVertical
        for (Region x : Arrays.asList(headingSettings, labelNumberOfBalls, HBoxBall, labelGeschwindigkeit, HBoxSpeed, labelInfected, HBoxInfected, labelHealing, HBoxRegeneration, labelSecurityDoors, HBoxSecurityDoors)) {
            rightMainBoxVertical.getChildren().add(x);
        }


        //--------------------Radio Button werden erstellt----------------------------------------------------------------------
        //radio buttons for balls
        RadioButton radiobuttonTen = new RadioButton("10");
        RadioButton radiobuttonTwenty = new RadioButton("20");
        RadioButton radiobuttonThirty = new RadioButton("30");
        RadioButton radiobuttonFourty = new RadioButton("40");

        //radio buttons for geschwindigkeit
        RadioButton radiobuttonSlow = new RadioButton("Langsam");
        RadioButton radiobuttonMedium = new RadioButton("Mittel");
        RadioButton radiobuttonFast = new RadioButton("Schnell");

        //Radio buttons for infi
        RadioButton radiobuttonHit1 = new RadioButton("1 Treffer");
        RadioButton radiobuttonHit2 = new RadioButton("2 Treffer");
        RadioButton radiobuttonHit3 = new RadioButton("4 Treffer");

        //Radio buttons for genesung
        RadioButton radiobuttonHealing30 = new RadioButton("30");
        RadioButton radiobuttonHealing60 = new RadioButton("60");
        RadioButton radiobuttonHealing120 = new RadioButton("120");

        //Radio button for security doors
        RadioButton yes = new RadioButton("yes");
        RadioButton no = new RadioButton("no");
        //---------------------------------------------------------------------------------------------------------------------

        //-------------------Radio buttons werden eingefügt-----------------------------------------------------------------------------

        //add radio button for balls
        for (RadioButton x : Arrays.asList(radiobuttonTen, radiobuttonTwenty, radiobuttonThirty, radiobuttonFourty)) {
            HBoxBall.getChildren().add(x);
        }

        //add radio button for geschwindigkeit
        for (RadioButton x : Arrays.asList(radiobuttonSlow, radiobuttonMedium, radiobuttonFast)) {
            HBoxSpeed.getChildren().add(x);
        }

        //add radio button for infi
        for (RadioButton x : Arrays.asList(radiobuttonHit1, radiobuttonHit2, radiobuttonHit3)) {
            HBoxInfected.getChildren().add(x);
        }

        //add radio button for healing
        for (RadioButton x : Arrays.asList(radiobuttonHealing30, radiobuttonHealing60, radiobuttonHealing120)) {
            HBoxRegeneration.getChildren().add(x);
        }
        //add radio button for Security doors
        for (RadioButton x : Arrays.asList(yes,no)) {
            HBoxSecurityDoors.getChildren().add(x);
        }

        //create ToggleGroups for radio buttons. allows only to select 1 radio button per group
        ToggleGroup togglegroupBalls = new ToggleGroup();
        ToggleGroup togglegroupSpeed = new ToggleGroup();
        ToggleGroup togglegroupInfected = new ToggleGroup();
        ToggleGroup togglegroupHealin = new ToggleGroup();
        ToggleGroup togglegroupSecurityGroup = new ToggleGroup();

        //add ball to the radiobutton group
        for (RadioButton x : Arrays.asList(radiobuttonTen, radiobuttonTwenty, radiobuttonThirty, radiobuttonFourty)) {
            x.setToggleGroup(togglegroupBalls);
        }

        //add geschwindigkeit to the radiobutton group
        for (RadioButton x : Arrays.asList(radiobuttonSlow, radiobuttonMedium, radiobuttonFast)) {
            x.setToggleGroup(togglegroupSpeed);
        }

        //add infiziert to the radio button group
        for (RadioButton x : Arrays.asList(radiobuttonHit1, radiobuttonHit2, radiobuttonHit3)) {
            x.setToggleGroup(togglegroupInfected);
        }

        //add genesung to the radio button group
        for (RadioButton x : Arrays.asList(radiobuttonHealing30, radiobuttonHealing60, radiobuttonHealing120)) {
            x.setToggleGroup(togglegroupHealin);
        }

        //add security doors to the radio button group
        for (RadioButton x : Arrays.asList(yes,no)) {
            x.setToggleGroup(togglegroupSecurityGroup);
        }
        //--------------------------------------------------------------------------------------------------------------

        //---------------------Fixierte höhe der einzelnen segmente-------------------------------
        //min/max Height and width for each segment.
        rightMainBoxVertical.setMinWidth(300);
        rightMainBoxVertical.setMinHeight(100);
        leftMainBox.setMinWidth(400);
        rightMainBox.setMinWidth(400);
        leftMainBoxUpperHalf.setMinHeight(400);
        leftMainBoxLowerHalf.setMinHeight(180);
        //---------------------------------------------------


        //change spacing between words and lines.
        for (HBox x : Arrays.asList(HBoxBall, HBoxSpeed, HBoxInfected, HBoxRegeneration, HBoxSecurityDoors)) {
            x.setSpacing(10);
            x.setPadding(new Insets(5, 5, 15, 5));
        }

        headingSettings.setPadding(new Insets(35, 5, 15, 5));


        //change Border color
        rightMainBoxVertical.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-color: black;");
        rightMainBox.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 0 0 0 5;" +
                        "-fx-border-color: blue;");


        //-----------------------------------------------------------------------------
        //create and add the save button
        Button buttonSave = new Button("Save & Exit");
        leftMainBoxLowerHalf.getChildren().add(buttonSave);
        buttonSave.setMinSize(200, 80);

        //------------------------------------------------------------------------------
        //Dritter Teil - rechte Hälfte

        //label for szenarien
        Label scenarios = new Label("Szenarien");

        //adding labels
        rightMainBox.getChildren().add(scenarios);

        //Styling the label and set the position of rightMainBox to center
        scenarios.setStyle("-fx-font-size: 20;" + "-fx-font-weight: bold");
        rightMainBox.setAlignment(Pos.TOP_CENTER);

        //spacing
        scenarios.setPadding(new Insets(35, 5, 15, 5));

        //create 1 Box for each row of scenarios
        HBox firstHBoxForScenarios = new HBox();
        HBox secondHBoxForScenarios = new HBox();
        HBox thirdHBoxForScenarios = new HBox();

        //add the previous created box
        for (HBox x : Arrays.asList(firstHBoxForScenarios, secondHBoxForScenarios, thirdHBoxForScenarios)) {
            rightMainBox.getChildren().add(x);
        }

        //create buttons and add it to the right box
        Button wien = new Button("wien");
        Button haus = new Button("haus");
        Button venedig = new Button("venedig");
        Button amsterdam = new Button("amsterdam");
        Button newyork = new Button("newyork");
        Button platzhalter = new Button("platzhalter");

        firstHBoxForScenarios.getChildren().add(wien);
        firstHBoxForScenarios.getChildren().add(haus);
        secondHBoxForScenarios.getChildren().add(venedig);
        secondHBoxForScenarios.getChildren().add(amsterdam);
        thirdHBoxForScenarios.getChildren().add(newyork);
        thirdHBoxForScenarios.getChildren().add(platzhalter);

        //change button height and width
        for (Button x : Arrays.asList(wien, haus, venedig, amsterdam, newyork, platzhalter)) {
            x.setMinSize(150, 150);
        }

        //spacing and styling
        for (HBox x : Arrays.asList(firstHBoxForScenarios, secondHBoxForScenarios, thirdHBoxForScenarios)) {
            x.setAlignment(Pos.TOP_CENTER);
            x.setPadding(new Insets(5, 5, 10, 5));
            x.setSpacing(10);
        }
        //------------------------------------------------------------------------------------------
        //---------------------------Funktionen-----------------------------------------------------
        //------------------------------------------------------------------------------------------

        //Radiobutton preselection for balls
        for (ToggleButton x : Arrays.asList(radiobuttonTen, radiobuttonTwenty, radiobuttonThirty, radiobuttonFourty)) {
            if (HomeScreen.balls == Integer.parseInt(x.getText())) {
                x.setSelected(true);
            }
        }
        //Radiobutton preselection for geschwindigkeit
        for (ToggleButton x : Arrays.asList(radiobuttonSlow, radiobuttonMedium, radiobuttonFast)) {
            if (HomeScreen.getSpeed().equals(x.getText())) {
                x.setSelected(true);
            }
        }
        //Radiobutton preselection infiziert
        for (ToggleButton x : Arrays.asList(radiobuttonHit1, radiobuttonHit2, radiobuttonHit3)) {
            String string = x.getText();
            string = string.substring(0, 1);
            int hits = Integer.parseInt(string);
            if (HomeScreen.hits == hits) {
                x.setSelected(true);
            }
        }
        //Radiobutton preselection for healing
        for (ToggleButton x : Arrays.asList(radiobuttonHealing30, radiobuttonHealing60, radiobuttonHealing120)) {
            if (HomeScreen.heal == Integer.parseInt(x.getText())) {
                x.setSelected(true);
            }
        }
        //Radiobutton preselection for security door
        if (HomeScreen.security_doors){
            yes.setSelected(true);
        }else{
            no.setSelected(true);
        }

//------------------------------------------------------------------------------------------------------------
        //load a picture as button background for the scenario buttons
        for (Button x : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            x.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;" + "-fx-background-image: url('/"+ x.getText() +".jpg');" + "-fx-text-fill: transparent;" + "-fx-background-size: 150px 150px;");
        }
        //Scenario button preselection
        for (Button x : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            if (HomeScreen.background.equals(x.getText())) {
                x.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;" + "-fx-background-image: url('/"+ x.getText() +".jpg');" + "-fx-text-fill: transparent;"+ "-fx-background-size: 150px 150px;");
            }
        }

//-------------Button click functions------------------------------------------------------------------------
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) buttonSave.getScene().getWindow();
                stage.close();

                //Daten werden in die Hashmap gespeichert
                //benötigt man damit man nur den Wert des radiobuttons zu speichern
                RadioButton selectedRadioButtonBaelle = (RadioButton) togglegroupBalls.getSelectedToggle();
                RadioButton selectedRadioButtonGeschwind = (RadioButton) togglegroupSpeed.getSelectedToggle();
                RadioButton selectedRadioButtonInfi = (RadioButton) togglegroupInfected.getSelectedToggle();
                RadioButton selectedRadioButtonGenesung = (RadioButton) togglegroupHealin.getSelectedToggle();
                RadioButton selectedRadioButtonSecurityDoors = (RadioButton) togglegroupSecurityGroup.getSelectedToggle();

                HomeScreen.setBalls(Integer.parseInt(selectedRadioButtonBaelle.getText()));
                HomeScreen.setSpeed(selectedRadioButtonGeschwind.getText());
                HomeScreen.setHits(Integer.parseInt(selectedRadioButtonInfi.getText().substring(0, 1)));
                HomeScreen.setHeal(Integer.parseInt(selectedRadioButtonGenesung.getText()));
                HomeScreen.setSecurity_doors(selectedRadioButtonSecurityDoors.getText());

            }
        });

        for (Button x : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            x.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Button x : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
                        x.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;" + "-fx-background-image: url('/"+ x.getText() +".jpg');" + "-fx-text-fill: transparent;"+ "-fx-background-size: 150px 150px;");
                    }
                    x.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;" + "-fx-background-image: url('/"+ x.getText() +".jpg');" + "-fx-text-fill: transparent;"+ "-fx-background-size: 150px 150px;");
                    HomeScreen.setBackground("" + x.getText());
                }
            });
        }

        //return
        return mainBox;
    }

    @Override
    public void start(Stage primaryStage) {
        guiStage = primaryStage;
    }
}
