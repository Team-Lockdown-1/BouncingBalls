/*

 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;


//scene resolution has to be 800x600
public class Setting{

    //getHBox() is the main interface for the Settings screen
    public static HBox getHBox() {

        //Basic structure for the scene.
        HBox mainBox = new HBox();
        VBox leftMainBox = new VBox();
        VBox rightMainBox = new VBox();
        HBox leftMainBoxUpperHalf = new HBox();
        HBox leftMainBoxLowerHalf = new HBox();
        VBox mainBoxVertical = new VBox();
        HBox HBoxBall = new HBox();
        HBox HBoxSpeed = new HBox();
        HBox HBoxRegeneration = new HBox();
        HBox HBoxSecurityDoors = new HBox();

        /**
         * Für die Praesentation
         *
         *         HBox noInfection = new HBox();
         *         Label uberschrift = new Label("Zum testen");
         *         RadioButton test1 = new RadioButton("10");
         *         RadioButton test2 = new RadioButton("20");
         *         ToggleGroup zusammen = new ToggleGroup();
         *
         */



        //combine the previous created HBox & VBox
        mainBox.getChildren().addAll(Arrays.asList(leftMainBox,rightMainBox));
        leftMainBox.getChildren().addAll(Arrays.asList(leftMainBoxUpperHalf,leftMainBoxLowerHalf));
        leftMainBoxUpperHalf.getChildren().add(mainBoxVertical);

        /**
         * Hier HBox noInfection einfuegen
         */
        //set the text which is written in the boxes to the position center
        for (HBox box : Arrays.asList(leftMainBoxLowerHalf, leftMainBoxUpperHalf, HBoxBall,
                HBoxSpeed, HBoxRegeneration, HBoxSecurityDoors)) {
            box.setAlignment(Pos.CENTER);
        }
        mainBoxVertical.setAlignment(Pos.TOP_CENTER);

        //create Lables for the left side of the Setting screen
        Label headingSettings = new Label("Settings");
        Label labelNumberOfBalls = new Label("Anzahl der Baelle");
        Label labelGeschwindigkeit = new Label("Geschwindigkeit");
        Label labelHealing = new Label("Heilung");
        Label labelSecurityDoors = new Label("Security Doors");

        /**
         * Hier Label uberschrift einfuegen
         */
        //Font size - der ganze Label Text wird größer und fett geschrieben, für die lesbarkeit
        headingSettings.setStyle("-fx-font-size: 20;" + "-fx-font-weight: bold");
        for (Label x : Arrays.asList(labelNumberOfBalls, labelGeschwindigkeit, labelHealing, labelSecurityDoors)) {
            x.setStyle("-fx-font-weight: bold");
        }

        /**
         * Hier Label uberschrift und noInfection einfuegen
         */
        //add radio buttons to the rightMianBoxVertical
        mainBoxVertical.getChildren().addAll(Arrays.asList(headingSettings, labelNumberOfBalls, HBoxBall,
                labelGeschwindigkeit, HBoxSpeed, labelHealing, HBoxRegeneration, labelSecurityDoors, HBoxSecurityDoors));

        //--------------------Radio Buttons----------------------------------------------------------------------
        //radio buttons for balls
        RadioButton radiobuttonTen = new RadioButton("10");
        RadioButton radiobuttonTwenty = new RadioButton("20");
        RadioButton radiobuttonThirty = new RadioButton("30");
        RadioButton radiobuttonFourty = new RadioButton("40");

        //create ToggleGroups for radio buttons. allows only to select 1 radio button per group
        ToggleGroup togglegroupBalls = new ToggleGroup();
        ToggleGroup togglegroupSpeed = new ToggleGroup();
        ToggleGroup togglegroupHealing = new ToggleGroup();
        ToggleGroup togglegroupSecurityGroup = new ToggleGroup();

        //radio buttons for geschwindigkeit
        RadioButton radiobuttonSlow = new RadioButton("Langsam");
        RadioButton radiobuttonMedium = new RadioButton("Mittel");
        RadioButton radiobuttonFast = new RadioButton("Schnell");

        //Radio buttons for genesung
        RadioButton radiobuttonHealing10 = new RadioButton("10");
        RadioButton radiobuttonHealing20 = new RadioButton("20");
        RadioButton radiobuttonHealing30 = new RadioButton("30");

        //Radio button for security doors
        RadioButton yes = new RadioButton("yes");
        RadioButton no = new RadioButton("no");

        //add radio button for balls
        for (RadioButton button : Arrays.asList(radiobuttonTen, radiobuttonTwenty, radiobuttonThirty, radiobuttonFourty)) {
            HBoxBall.getChildren().add(button);
            button.setToggleGroup(togglegroupBalls);
        }

        //add radio button for geschwindigkeit
        for (RadioButton button : Arrays.asList(radiobuttonSlow, radiobuttonMedium, radiobuttonFast)) {
            HBoxSpeed.getChildren().add(button);
            button.setToggleGroup(togglegroupSpeed);
        }

        //add radio button for healing
        for (RadioButton button : Arrays.asList(radiobuttonHealing10, radiobuttonHealing20, radiobuttonHealing30)) {
            HBoxRegeneration.getChildren().add(button);
            button.setToggleGroup(togglegroupHealing);
        }

        //add radio button for Security doors
        for (RadioButton button : Arrays.asList(yes, no)) {
            HBoxSecurityDoors.getChildren().add(button);
            button.setToggleGroup(togglegroupSecurityGroup);
        }

        //min/max Height and width for each segment.
        mainBoxVertical.setMinWidth(300);
        mainBoxVertical.setMinHeight(100);
        leftMainBox.setMinWidth(400);
        rightMainBox.setMinWidth(400);
        leftMainBoxUpperHalf.setMinHeight(400);
        leftMainBoxLowerHalf.setMinHeight(180);


        /**
         * Hier noInfection einfuegen
         */
        //change spacing between words and lines.
        for (HBox hbox : Arrays.asList(HBoxBall, HBoxSpeed, HBoxRegeneration, HBoxSecurityDoors)) {
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(5, 5, 15, 5));
        }
        headingSettings.setPadding(new Insets(35, 5, 15, 5));


        //change Border color
        mainBoxVertical.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-color: black;");
        rightMainBox.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 0 0 0 5;" +
                        "-fx-border-color: blue;");


        //create and add the save button
        Button buttonSave = new Button("Save & Exit");
        leftMainBoxLowerHalf.getChildren().add(buttonSave);
        buttonSave.setMinSize(200, 80);


        //--------------------------right half of the scene------------------------------------

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
        rightMainBox.getChildren().addAll(Arrays.asList(firstHBoxForScenarios, secondHBoxForScenarios, thirdHBoxForScenarios));

        //create buttons and add it to the right box
        Button wien = new Button("wien");
        Button haus = new Button("haus");
        Button venedig = new Button("venedig");
        Button amsterdam = new Button("amsterdam");
        Button newyork = new Button("newyork");
        Button platzhalter = new Button("platzhalter");

        firstHBoxForScenarios.getChildren().addAll(Arrays.asList(wien,haus));
        secondHBoxForScenarios.getChildren().addAll(Arrays.asList(venedig,amsterdam));
        thirdHBoxForScenarios.getChildren().addAll(Arrays.asList(newyork,platzhalter));

        //change button height and width
        for (Button x : Arrays.asList(wien, haus, venedig, amsterdam, newyork, platzhalter)) {
            x.setMinSize(150, 150);
        }

        //spacing and styling
        for (HBox hbox : Arrays.asList(firstHBoxForScenarios, secondHBoxForScenarios, thirdHBoxForScenarios)) {
            hbox.setAlignment(Pos.TOP_CENTER);
            hbox.setPadding(new Insets(5, 5, 10, 5));
            hbox.setSpacing(10);
        }
        //------------------------------------------------------------------------------------------
        //---------------------------functions-----------------------------------------------------
        //------------------------------------------------------------------------------------------

        //Radiobutton preselection for balls
        for (ToggleButton button : Arrays.asList(radiobuttonTen, radiobuttonTwenty, radiobuttonThirty, radiobuttonFourty)) {
            if (HomeScreen.balls == Integer.parseInt(button.getText())) {
                button.setSelected(true);
            }
        }
        //Radiobutton preselection for geschwindigkeit
        for (ToggleButton button : Arrays.asList(radiobuttonSlow, radiobuttonMedium, radiobuttonFast)) {
            if (HomeScreen.getSpeed().equals(button.getText())) {
                button.setSelected(true);
            }
        }

        //Radiobutton preselection for healing
        for (ToggleButton button : Arrays.asList(radiobuttonHealing10, radiobuttonHealing20, radiobuttonHealing30)) {
            if (HomeScreen.getRegeneration() == Integer.parseInt(button.getText())) {
                button.setSelected(true);
            }
        }
        //Radiobutton preselection for security door
        if (HomeScreen.security_doors) {
            yes.setSelected(true);
        } else {
            no.setSelected(true);
        }

//------------------------------------------------------------------------------------------------------------

        //load a picture as button background for the scenario buttons
        for (Button button : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            button.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;" + "-fx-background-image: url('/" + button.getText() + ".jpg');" + "-fx-text-fill: transparent;" + "-fx-background-size: 150px 150px;");
        }
        //Scenario button preselection
        for (Button button : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            if (HomeScreen.background.equals(button.getText())) {
                button.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;" + "-fx-background-image: url('/" + button.getText() + ".jpg');" + "-fx-text-fill: transparent;" + "-fx-background-size: 150px 150px;");
            }
        }

        //save button on click event
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) buttonSave.getScene().getWindow();
                stage.close();

                //get button of the selected Togglegroup
                RadioButton selectedRadioButtonBaelle = (RadioButton) togglegroupBalls.getSelectedToggle();
                RadioButton selectedRadioButtonGeschwind = (RadioButton) togglegroupSpeed.getSelectedToggle();
                RadioButton selectedRadioButtonGenesung = (RadioButton) togglegroupHealing.getSelectedToggle();
                RadioButton selectedRadioButtonSecurityDoors = (RadioButton) togglegroupSecurityGroup.getSelectedToggle();

                //change variables in HomeScreen
                HomeScreen.setBalls(Integer.parseInt(selectedRadioButtonBaelle.getText()));
                HomeScreen.setSpeed(selectedRadioButtonGeschwind.getText());
                HomeScreen.setSecurity_doors(selectedRadioButtonSecurityDoors.getText());
                HomeScreen.setRegeneration(Integer.parseInt(selectedRadioButtonGenesung.getText()));
            }
        });

        //scene button on click event
        for (Button button : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //change the style of every other button to black border, if one is clicked
                    for (Button button : Arrays.asList(wien, amsterdam, haus, venedig, newyork, platzhalter)) {
                        button.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;" + "-fx-background-image: url('/" + button.getText() + ".jpg');" + "-fx-text-fill: transparent;" + "-fx-background-size: 150px 150px;");
                    }
                    //change the border color of the selected button to red
                    button.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;" + "-fx-background-image: url('/" + button.getText() + ".jpg');" + "-fx-text-fill: transparent;" + "-fx-background-size: 150px 150px;");
                    //change the variable in HomeScreen. is needed to load the background image.
                    HomeScreen.setBackground("" + button.getText());
                }
            });
        }

        //return
        return mainBox;
    }
}
