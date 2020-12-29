import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Arrays;


//scene muss in 800x600 erstellt werden.
public class Setting extends Application {
    private static Stage guiStage;

    public static Stage getStage() {
        Stage guiStage = new Stage();
        return guiStage;
    }

    public static void cancelLogin() {
        guiStage.close();
    }
    public static HBox getVBox(){

        //Boxen und kombinationen, Grundstruktur bzw. Grundordnung der Scene
        HBox mainBox = new HBox();
        VBox leftMainBox = new VBox();
        VBox rightMainBox = new VBox();
        HBox leftMainBoxUpperHalf = new HBox();
        HBox leftMainBoxLowerHalf = new HBox();
        VBox rightMainBoxVertical = new VBox();
        HBox ball = new HBox();
        HBox tempo = new HBox();
        HBox infiz = new HBox();
        HBox regeneration = new HBox();

        //Boxen zusammenfügen
        mainBox.getChildren().add(leftMainBox);
        mainBox.getChildren().add(rightMainBox);
        leftMainBox.getChildren().add(leftMainBoxUpperHalf);
        leftMainBox.getChildren().add(leftMainBoxLowerHalf);
        leftMainBoxUpperHalf.getChildren().add(rightMainBoxVertical);

        //Positionen, damit der text an der richtigen Stelle angezeigt wird. muss für jede Box extra gesetzt werden.
        for(HBox x: Arrays.asList(leftMainBoxLowerHalf,leftMainBoxUpperHalf,ball,tempo,infiz,regeneration)){
            x.setAlignment(Pos.CENTER);
        }
        rightMainBoxVertical.setAlignment(Pos.TOP_CENTER);
            /*
        leftMainBoxUpperHalf.setAlignment(Pos.CENTER);

             */

        /*
        ball.setAlignment(Pos.TOP_CENTER);
        tempo.setAlignment(Pos.TOP_CENTER);
        infiz.setAlignment(Pos.TOP_CENTER);
        regeneration.setAlignment(Pos.TOP_CENTER);
        leftMainBoxLowerHalf.setAlignment(Pos.CENTER);

         */

        //Label - der ganze Label Text
        Label settings = new Label("Settings");
        Label numberOfBalls = new Label("Anzahl der Baelle");
        Label geschwindigkeit = new Label("Geschwindigkeit");
        Label infiziert = new Label("Chance zum infizieren");
        Label heilung = new Label("Heilung");

        //Font size - der ganze Label Text wird größer und fett geschrieben, für die lesbarkeit
        settings.setStyle("-fx-font-size: 20;"+"-fx-font-weight: bold");
        for(Label x: Arrays.asList(numberOfBalls,geschwindigkeit,infiziert,heilung)){
            x.setStyle("-fx-font-weight: bold");
        }
        /*
        numberOfBalls.setStyle("-fx-font-weight: bold");
        geschwindigkeit.setStyle("-fx-font-weight: bold");
        infiziert.setStyle("-fx-font-weight: bold");
        heilung.setStyle("-fx-font-weight: bold");

         */

        //In der rechten oberen hälfte werden alle Labels und radio buttons eingefügt
        for(Region x: Arrays.asList(settings,numberOfBalls,ball,geschwindigkeit,tempo,infiziert,infiz,heilung,regeneration)){
            rightMainBoxVertical.getChildren().add(x);
        }
        /*
        rightMainBoxVertical.getChildren().add(settings);
        rightMainBoxVertical.getChildren().add(numberOfBalls);
        rightMainBoxVertical.getChildren().add(ball);
        rightMainBoxVertical.getChildren().add(geschwindigkeit);
        rightMainBoxVertical.getChildren().add(tempo);
        rightMainBoxVertical.getChildren().add(infiziert);
        rightMainBoxVertical.getChildren().add(infiz);
        rightMainBoxVertical.getChildren().add(heilung);
        rightMainBoxVertical.getChildren().add(regeneration);


         */

        //--------------------Radio Button werden erstellt----------------------------------------------------------------------
        //radio Buttons für Bälle

        RadioButton einHundert = new RadioButton("10");
        RadioButton zweiHundert = new RadioButton("20");
        RadioButton dreiHundert = new RadioButton("30");
        RadioButton vierHundert = new RadioButton("40");

        //radio Buttons für Geschwindigkeit
        RadioButton langsam = new RadioButton("Langsam");
        RadioButton mittel = new RadioButton("Mittel");
        RadioButton schnell = new RadioButton("Schnell");

        //Radio buttons für infi
        RadioButton treffer1 = new RadioButton("1 Treffer");
        RadioButton treffer2 = new RadioButton("2 Treffer");
        RadioButton treffer4 = new RadioButton("4 Treffer");

        //Radio buttons für genesung
        RadioButton h30 = new RadioButton("30");
        RadioButton h60 = new RadioButton("60");
        RadioButton h120 = new RadioButton("120");
        //---------------------------------------------------------------------------------------------------------------------

        //-------------------Radio buttons werden eingefügt-----------------------------------------------------------------------------

        //einfügen bälle
        for(RadioButton x : Arrays.asList(einHundert,zweiHundert,dreiHundert,vierHundert)){
            ball.getChildren().add(x);
        }
        /*
        ball.getChildren().add(einHundert);
        ball.getChildren().add(zweiHundert);
        ball.getChildren().add(dreiHundert);
        ball.getChildren().add(vierHundert);
         */
        //Einfügen der geschwindigkeit
        for(RadioButton x : Arrays.asList(langsam,mittel,schnell)){
            tempo.getChildren().add(x);
        }
        /*
        tempo.getChildren().add(langsam);
        tempo.getChildren().add(mittel);
        tempo.getChildren().add(schnell);

         */

        //Einfügen der infizierten
        for(RadioButton x : Arrays.asList(treffer1,treffer2,treffer4)){
            infiz.getChildren().add(x);
        }
        /*
        infiz.getChildren().add(treffer1);
        infiz.getChildren().add(treffer2);
        infiz.getChildren().add(treffer4);

         */

        //Einfügen der Heilung
        for(RadioButton x : Arrays.asList(h30,h60,h120)){
            regeneration.getChildren().add(x);
        }
        /*
        regeneration.getChildren().add(h30);
        regeneration.getChildren().add(h60);
        regeneration.getChildren().add(h120);
         */

        //Gruppen für radio buttons
        ToggleGroup baelle = new ToggleGroup();
        ToggleGroup geschwind = new ToggleGroup();
        ToggleGroup infi = new ToggleGroup();
        ToggleGroup genesung = new ToggleGroup();

        //Radio button groups - bälle
        for(RadioButton x : Arrays.asList(einHundert,zweiHundert,dreiHundert,vierHundert)){
            x.setToggleGroup(baelle);
        }
        /*
        einHundert.setToggleGroup(bälle);
        zweiHundert.setToggleGroup(bälle);
        dreiHundert.setToggleGroup(bälle);
        vierHundert.setToggleGroup(bälle);

         */

        //Radio button groups - geschwind
        for(RadioButton x : Arrays.asList(langsam,mittel,schnell)){
            x.setToggleGroup(geschwind);
        }
        /*
        langsam.setToggleGroup(geschwind);
        mittel.setToggleGroup(geschwind);
        schnell.setToggleGroup(geschwind);

         */

        //Radio button groups - infi
        for(RadioButton x : Arrays.asList(treffer1,treffer2,treffer4)){
            x.setToggleGroup(infi);
        }
        /*
        treffer1.setToggleGroup(infi);
        treffer2.setToggleGroup(infi);
        treffer4.setToggleGroup(infi);
         */

        //radio button groups - genesung
        for(RadioButton x : Arrays.asList(h30,h60,h120)){
            x.setToggleGroup(genesung);
        }
        /*
        h30.setToggleGroup(genesung);
        h60.setToggleGroup(genesung);
        h120.setToggleGroup(genesung);

         */

        //--------------------------------------------------------------------------------------------------------------

        //---------------------Fixierte höhe der einzelnen segmente-------------------------------
        //Height and width
        rightMainBoxVertical.setMinWidth(300);
        rightMainBoxVertical.setMinHeight(100);
        leftMainBox.setMinWidth(400);
        rightMainBox.setMinWidth(400);
        leftMainBoxUpperHalf.setMinHeight(400);
        leftMainBoxLowerHalf.setMinHeight(180);
        //---------------------------------------------------

        //Spacing - abstände zwischen den wörtern und mehr Abstand zu den andern Zeilen

        for(HBox x : Arrays.asList(ball,tempo,infiz,regeneration)){
            x.setSpacing(10);
            x.setPadding(new Insets(5,5,15,5));
        }

         /*
        ball.setSpacing(10);
        tempo.setSpacing(10);
        infiz.setSpacing(10);
        regeneration.setSpacing(10);

          */
        settings.setPadding(new Insets(35,5,15,5));
        /*
        ball.setPadding(new Insets(5,5,15,5));
        tempo.setPadding(new Insets(5,5,15,5));
        infiz.setPadding(new Insets(5,5,15,5));
        regeneration.setPadding(new Insets(5,5,10,5));

         */

        //Border & Farbe
        rightMainBoxVertical.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-color: black;");
        rightMainBox.setStyle(
                "-fx-border-style: solid inside;" +
                        "-fx-border-width: 0 0 0 5;" +

                        "-fx-border-color: blue;");



        //-----------------------------------------------------------------------------
        // Zweiter Teil - linke untere Hälfte

        Button speichern = new Button("Speichern & Exit");
        leftMainBoxLowerHalf.getChildren().add(speichern);
        speichern.setMinSize(200,80);

        //------------------------------------------------------------------------------
        //Dritter Teil - rechte Hälfte

        //Labels
        Label szenarien = new Label("Szenarien");

        //adding labels
        rightMainBox.getChildren().add(szenarien);

        //Styling
        szenarien.setStyle("-fx-font-size: 20;"+"-fx-font-weight: bold");
        rightMainBox.setAlignment(Pos.TOP_CENTER);

        //Abstände
        szenarien.setPadding(new Insets(35,5,15,5));

        //boxen
        HBox firstHBox = new HBox();
        HBox secondHBox = new HBox();
        HBox thirdHBox = new HBox();

        //einfügen
        for(HBox x : Arrays.asList(firstHBox,secondHBox,thirdHBox)){
            rightMainBox.getChildren().add(x);
        }
        /*
        rightMainBox.getChildren().add(firstHBox);
        rightMainBox.getChildren().add(secondHBox);
        rightMainBox.getChildren().add(thirdHBox);

         */

        // Button erstellen & hinzufügen
        Button wien = new Button("wien");
        Button haus = new Button("haus");
        Button venedig = new Button("venedig");
        Button amsterdam = new Button("amsterdam");
        Button newyork = new Button("newyork");
        Button platzhalter = new Button("platzhalter");

        firstHBox.getChildren().add(wien);
        firstHBox.getChildren().add(haus);
        secondHBox.getChildren().add(venedig);
        secondHBox.getChildren().add(amsterdam);
        thirdHBox.getChildren().add(newyork);
        thirdHBox.getChildren().add(platzhalter);

        //Button größe umstellen
        for(Button x : Arrays.asList(wien,haus,venedig,amsterdam,newyork,platzhalter)){
            x.setMinSize(150,150);
        }
        /*
        wien.setMinSize(150,150);
        haus.setMinSize(150,150);
        venedig.setMinSize(150,150);
        amsterdam.setMinSize(150,150);
        newyork.setMinSize(150,150);
        platzhalter.setMinSize(150,150);

         */

        //zentrieren & Styling
        for(HBox x : Arrays.asList(firstHBox,secondHBox,thirdHBox)){
            x.setAlignment(Pos.TOP_CENTER);
            x.setPadding(new Insets(5,5,10,5));
            x.setSpacing(10);
        }
        /*
        firstHBox.setAlignment(Pos.TOP_CENTER);
        secondHBox.setAlignment(Pos.TOP_CENTER);
        thirdHBox.setAlignment(Pos.TOP_CENTER);

        //Styling
        firstHBox.setPadding(new Insets(5,5,10,5));
        secondHBox.setPadding(new Insets(5,5,10,5));
        thirdHBox.setPadding(new Insets(5,5,10,5));
        firstHBox.setSpacing(10);
        secondHBox.setSpacing(10);
        thirdHBox.setSpacing(10);

         */

        //------------------------------------------------------------------------------------------
        //---------------------------Funktionen-----------------------------------------------------
        //------------------------------------------------------------------------------------------

        //----------Radiobutton preselection--------------------------------------------------------
        for(ToggleButton x : Arrays.asList(einHundert,zweiHundert,dreiHundert,vierHundert)){
            if(HomeScreen.balls == Integer.parseInt(x.getText())){
                x.setSelected(true);
            }
        }

        for(ToggleButton x : Arrays.asList(langsam,mittel,schnell)){
            if(HomeScreen.getSpeed().equals(x.getText())){
                x.setSelected(true);
            }
        }

        for(ToggleButton x : Arrays.asList(treffer1,treffer2,treffer4)){
            String string = x.getText();
            string = string.substring(0,1);
            int hits = Integer.parseInt(string);
            if(HomeScreen.hits == hits){
                x.setSelected(true);
            }
        }

        for(ToggleButton x : Arrays.asList(h30,h60,h120)){
            if(HomeScreen.heal == Integer.parseInt(x.getText())){
                x.setSelected(true);
            }
        }
/*
        if(HomeScreen.balls == 10){
            einHundert.setSelected(true);
        }else if(HomeScreen.balls == 20){
            zweiHundert.setSelected(true);
        }else if(HomeScreen.balls == 30){
            dreiHundert.setSelected(true);
        }else if(HomeScreen.balls == 40){
            vierHundert.setSelected(true);
        }
/*
        if(Main.list.get("geschwind").equals("Langsam")){
            langsam.setSelected(true);
        }else if(Main.list.get("geschwind").equals("Mittel")){
            mittel.setSelected(true);
        }else if(Main.list.get("geschwind").equals("Schnell")){
            schnell.setSelected(true);
        }

        if(Main.list.get("infi").equals("1 Treffer")){
            treffer1.setSelected(true);
        }else if(Main.list.get("infi").equals("2 Treffer")){
            treffer2.setSelected(true);
        }else if(Main.list.get("infi").equals("4 Treffer")){
            treffer4.setSelected(true);
        }

        if(Main.list.get("genesung").equals("30")){
            h30.setSelected(true);
        }else if(Main.list.get("genesung").equals("60")){
            h60.setSelected(true);
        }else if(Main.list.get("genesung").equals("120")){
            h120.setSelected(true);
        }
        */
//------------------------------------------------------------------------------------------------------------

        for(Button x : Arrays.asList(wien,amsterdam,haus,venedig,newyork,platzhalter)){
            x.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;"+ "-fx-background-image: url('picture/amsterdam.jpg');" + "-fx-text-fill: transparent;" );
        }

        for(Button x : Arrays.asList(wien,amsterdam,haus,venedig,newyork,platzhalter)) {
            if (HomeScreen.background.equals(x.getText())) {
                x.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;" + "-fx-background-image: url('picture/amsterdam.jpg');" + "-fx-text-fill: transparent;");
            }
        }
        /*
        if(Main.list.get("background").equals("wien")){
            wien.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }else if(Main.list.get("background").equals("amsterdam")){
            amsterdam.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }else if(Main.list.get("background").equals("haus")){
            haus.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }else if(Main.list.get("background").equals("venedig")){
            venedig.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }else if(Main.list.get("background").equals("newyork")){
            newyork.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }else if(Main.list.get("background").equals("platzhalter")){
            platzhalter.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('resources/amsterdam.jpg')");
        }
*/


//-------------Button click functions------------------------------------------------------------------------
        speichern.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) speichern.getScene().getWindow();
                stage.close();

                //Daten werden in die Hashmap gespeichert
                //benötigt man damit man nur den Wert des radiobuttons zu speichern
                RadioButton selectedRadioButtonBälle = (RadioButton) baelle.getSelectedToggle();
                RadioButton selectedRadioButtonGeschwind = (RadioButton) geschwind.getSelectedToggle();
                RadioButton selectedRadioButtonInfi = (RadioButton) infi.getSelectedToggle();
                RadioButton selectedRadioButtonGenesung = (RadioButton) genesung.getSelectedToggle();



                HomeScreen.setBalls(Integer.parseInt(selectedRadioButtonBälle.getText()));
                HomeScreen.setSpeed(selectedRadioButtonGeschwind.getText());
                HomeScreen.setHits(Integer.parseInt(selectedRadioButtonInfi.getText().substring(0,1)));
                HomeScreen.setHeal(Integer.parseInt(selectedRadioButtonGenesung.getText()));

                /*MainApp.saveData("bälle",a);
                MainApp.saveData("geschwind",b);
                MainApp.saveData("infi",c);
                MainApp.saveData("genesung",d);*/

            }
        });

        for(Button x : Arrays.asList(wien,amsterdam,haus,venedig,newyork,platzhalter)){
            x.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for(Button x : Arrays.asList(wien,amsterdam,haus,venedig,newyork,platzhalter)){
                        x.setStyle("-fx-border-color: black; " + "-fx-border-width: 2;"+ "-fx-background-image: url('picture/amsterdam.jpg');" + "-fx-text-fill: transparent;");
                    }
                    x.setStyle("-fx-border-color: red; " + "-fx-border-width: 2;"+ "-fx-background-image: url('picture/amsterdam.jpg');" + "-fx-text-fill: transparent;");
                    HomeScreen.setBackground(""+x.getText());
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
