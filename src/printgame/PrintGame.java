/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printgame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author lordstorm
 */
public class PrintGame extends Application 
{
    long startTime = System.currentTimeMillis();

    long stopTime = System.currentTimeMillis();
    boolean continue1 = false;
    String words = "Historically, the fundamental role of pharmacists as a healthcare practitioner was to check and distribute drugs to doctors for medication that had been prescribed to patients. In more modern times, pharmacists advise patients and health care providers on the selection, dosages, interactions, and side effects of medications, and act as a learned intermediary between a prescriber and a patient. Pharmacists monitor the health and progress of patients to ensure the safe and effective use of medication. Pharmacists may practice compounding; however, many medicines are now produced by pharmaceutical companies in a standard dosage and drug delivery form. In some jurisdictions, pharmacists have prescriptive authority to either independently prescribe under their own authority or in collaboration with a primary care physician through an agreed upon protocol.";
    String words1 = "";
    int index = 0;
    int mistakes = 0;
    int correct = 0;
    boolean[] truefalse;
    @Override
    public void start(Stage primaryStage) 
    {
        StringBuilder sb = new StringBuilder(words);

        int i = 0;
        while ((i = sb.indexOf(" ", i + 70)) != -1) 
        {
            sb.replace(i, i + 1, "\n");
        }
        words1 = sb.toString();
        truefalse = new boolean[words1.length()];
        Button btn = new Button();
        btn.setText("Start/stop");
       
        Button btn1 = new Button();
        btn1.setText("Reset");
        
        TextArea text = new TextArea(words1);
        text.setEditable(false);
        text.setMaxWidth(500);
        text.setMinHeight(250);
        TextArea text1 = new TextArea();
        text1.setEditable(false);
        text1.setMaxWidth(500);
        text1.setMinHeight(250);
        Label correct_label = new Label();
        Label mistake_label = new Label();
        Label time = new Label();
        Label letters = new Label();
        letters.setFont(new Font(20));
        HBox buttons = new HBox();
        buttons.getChildren().add(btn);
        buttons.getChildren().add(btn1);
        buttons.getChildren().add(correct_label);
        buttons.getChildren().add(mistake_label);
        buttons.getChildren().add(time);
        buttons.getChildren().add(letters);
        VBox root = new VBox();
        root.getChildren().add(buttons);
        //Canvas can = new Canvas(500,250);
        root.getChildren().add(text);
        root.getChildren().add(text1);
        
        
        
        new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                if(continue1)
                {
                stopTime = System.currentTimeMillis();
                correct_label.setText("Correct: " + correct);
                mistake_label.setText("Mistakes: " + mistakes);
                time.setText("Elapsed time is " + (stopTime - startTime)/1000 + " seconds.");
                letters.setText("|" + Character.toString(words1.charAt(index))+Character.toString(words1.charAt(index+1))+Character.toString(words1.charAt(index+2))+"|");
                
                
                
                }
                
            }
        }.start();
        
        Scene scene = new Scene(root, 500, 700);
        scene.setOnKeyReleased
        (
            new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent e)
                {
                   if(continue1)
                   {
                       if(e.getText().toLowerCase().equals(Character.toString(words1.charAt(index)).toLowerCase()))
                       {
                           text1.appendText(e.getText());
                           truefalse[index] = true;
                           index++;
                           correct++;
                           
                       }
                       else
                       {
                           text1.appendText(e.getText());
                           truefalse[index] = true;
                           index++;
                           mistakes++;
                       }
                   }
                   e.consume();
                }
            }
        );
        btn1.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
               index = 0;
               mistakes = 0;
               correct = 0;
               truefalse = new boolean[words1.length()];
               startTime = System.currentTimeMillis();
               stopTime = System.currentTimeMillis();
               text1.clear();
               
            }
        });
         btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            
            @Override
            public void handle(ActionEvent event) 
            {
               continue1 = !continue1;
            }
        });
        
        primaryStage.setTitle("Printing program");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
