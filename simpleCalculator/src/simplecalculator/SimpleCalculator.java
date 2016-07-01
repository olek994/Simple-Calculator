/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Aleksander
 */
public class SimpleCalculator extends Application {
    
    private float firstNumber;
    private float secondNumber;
    private String operation;
    private Button[] numberButtons;
    private Button sumButton;
    private Button differenceButton;
    private Button productButton;
    private Button quotientButton;
    private Button cosButton;
    private Button sinButton;
    private Button logButton;
    private Button powButton;
    
    private Button equalsButton;
    private Button pointButton;
    private Button clearButton;
    private static TextField mainInput;
    @Override
    public void start(Stage primaryStage) {
        
        //InputField
        mainInput = new TextField();
        mainInput.setEditable(false);
        mainInput.setPadding(new Insets(10,10,10,10));
        mainInput.setMaxSize(350, 60);
        mainInput.setAlignment(Pos.CENTER_RIGHT);
        mainInput.setId("maininput");
        mainInput.setTranslateY(10);
        
        //Buttons
        
        createButtons();
        
        //numbersPane
        GridPane numberPane = setNumberPane();
        numberPane.alignmentProperty().set(Pos.CENTER);
        numberPane.setTranslateX(20);
        
        //VBox operations
        VBox VPane = new VBox();
        VPane.getChildren().addAll(quotientButton,productButton,differenceButton,sumButton);
        
        //HBox operations
        HBox HPane = new HBox();
        HPane.getChildren().addAll(sinButton,cosButton,logButton,powButton);
        HPane.setAlignment(Pos.CENTER);
        
        //HBox
        HBox hBoxPane = new HBox(2);
        hBoxPane.getChildren().addAll(numberPane,VPane);
        hBoxPane.setAlignment(Pos.CENTER);
        hBoxPane.setSpacing(70);
        
        //mainPane
        VBox  mainPane = new VBox(3);
        mainPane.alignmentProperty().set(Pos.CENTER);
        mainPane.getChildren().addAll(mainInput,HPane,hBoxPane,equalsButton);
        mainPane.setSpacing(20);
        VBox.setMargin(mainInput, new Insets(20,10,0,10));
        VBox.setMargin(equalsButton, new Insets(0,0,20,0));
       
        //Scene
        Scene scene = new Scene(mainPane, 400, 500);
        scene.getStylesheets().add("simplecalculator/Style.css");
        //Stage
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void createButtons()
    {
        numberButtons = new Button[10];
        for(Integer i = 0;i<10;i++)
        {
            numberButtons[i] = new NumberButton(i.toString());
            numberButtons[i].setMinSize(60, 60);
            
            
        }
        equalsButton = new Button("=");
        equalsButton.setMinSize(350, 40);
        equalsButton.setId("equalsbutton");
        equalsButton.setOnAction(e -> {
            try{
            mainInput.setText(equalsResult(mainInput.getText()).toString());
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        pointButton = new Button(".");
        pointButton.setMinSize(60, 60);
        pointButton.setOnAction(e -> {
            if(!mainInput.getText().contains("."))
            {
                mainInput.setText(mainInput.getText() + ".");
            }
                
        });
        
        clearButton = new Button("C");
        clearButton.setMinSize(60,60);
        clearButton.setOnAction(e -> mainInput.setText(""));
        
        sumButton = new Button("+");
        sumButton.setMinSize(60, 70);
        VBox.setMargin(sumButton, new Insets(0,0,5,0));
        sumButton.setOnAction(e -> {
            try{
            addNumber();
            operation = "+";
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        
        differenceButton = new Button("-");
        differenceButton.setMinSize(60,60);
        VBox.setMargin(differenceButton, new Insets(0,0,5,0));
        differenceButton.setOnAction(e -> {
            try{
            addNumber();
            operation = "-";
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        productButton = new Button("*");
        productButton.setMinSize(60, 60);
        VBox.setMargin(productButton, new Insets(0,0,5,0));
        productButton.setOnAction(e -> {
            try{
            addNumber();
            operation = "*";
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        quotientButton = new Button("/");
        quotientButton.setMinSize(60, 60);
        VBox.setMargin(quotientButton, new Insets(0,0,5,0));
        quotientButton.setOnAction(e -> {
            try{
            addNumber();
            operation = "/";
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        sinButton = new Button("sin");
        sinButton.setMinSize(85, 50);
        HBox.setMargin(sinButton, new Insets(5,5,5,0));
        sinButton.setOnAction(e -> {
            try{
            Float result = (float) Math.sin(Float.parseFloat(mainInput.getText()));
            mainInput.setText(result.toString());
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
        cosButton = new Button("cos");
        cosButton.setMinSize(85, 50);
        HBox.setMargin(cosButton, new Insets(5,5,5,0));
        cosButton.setOnAction(e -> {
             try{
                Float result = (float) Math.cos(Float.parseFloat(mainInput.getText()));
                mainInput.setText(result.toString());
             }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }

        });
        
        logButton = new Button("log10");
        logButton.setMinSize(85, 50);
        HBox.setMargin(logButton, new Insets(5,5,5,0));
        logButton.setOnAction(e -> {
            try{
                Float result = (float) Math.log10(Float.parseFloat(mainInput.getText()));
                mainInput.setText(result.toString());
             }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
            
        });
        
        powButton = new Button("pow");
        powButton.setMinSize(85, 50);
        HBox.setMargin(powButton, new Insets(5,5,5,0));
        powButton.setOnAction(e -> {
            try{
            addNumber();
            operation = "pow";
            }
            catch(NumberFormatException ex)
            {
                mainInput.setText("Error");
            }
        });
        
    }
    
    private GridPane setNumberPane()
    {
        GridPane numbers = new GridPane();
        numbers.add(numberButtons[1], 0, 0);
        numbers.add(numberButtons[2], 1, 0);
        numbers.add(numberButtons[3], 2, 0);
        numbers.add(numberButtons[4], 0, 1);
        numbers.add(numberButtons[5], 1, 1);
        numbers.add(numberButtons[6], 2, 1);
        numbers.add(numberButtons[7], 0, 2);
        numbers.add(numberButtons[8], 1, 2);
        numbers.add(numberButtons[9], 2, 2);
        numbers.add(numberButtons[0], 1, 3);
        numbers.add(pointButton, 0, 3);
        numbers.add(clearButton, 2, 3);
        for(int i = 0;i<10;i++)
        {
            GridPane.setMargin(numberButtons[i], new Insets(0,5,10,5));
        }
        GridPane.setMargin(pointButton, new Insets(0,5,10,5));
        GridPane.setMargin(clearButton, new Insets(0,5,10,5));
        
        
        return numbers;
    }
    
    private Float equalsResult(String secondNumber)
    {
        this.secondNumber = Float.parseFloat(secondNumber);
        float result = 0;
        try{
            switch(operation)
            {
                case "+":
                        result = (this.firstNumber + this.secondNumber);
                        this.firstNumber = 0;
                        this.secondNumber = 0;
                        this.operation = "";
                        break;
                case "-":
                        result = (this.firstNumber - this.secondNumber);
                        this.firstNumber = 0;
                        this.secondNumber = 0;
                        this.operation = "";
                        break;
                case "*":
                        result = (this.firstNumber * this.secondNumber);
                        this.firstNumber = 0;
                        this.secondNumber = 0;
                        this.operation = "";
                        break;
                case "/":
                        result = (this.firstNumber / this.secondNumber);
                        this.firstNumber = 0;
                        this.secondNumber = 0;
                        this.operation = "";
                        break;
                case "pow":
                    result = (float) Math.pow(this.firstNumber, this.secondNumber);
                        this.firstNumber = 0;
                        this.secondNumber = 0;
                        this.operation = "";
                        break;
            }
        }
        catch(ArithmeticException ex)
        {
            mainInput.setText("Error");
            result = -1;
        }
        return result;
        
    }
    
    private void addNumber()
    {
        firstNumber = Float.parseFloat(mainInput.getText());
        mainInput.setText("");
    }
    
    public static void addToInput(String character)
    {
        if(mainInput.getText().contains("Error"))
            mainInput.setText("");
        
        mainInput.setText(mainInput.getText() + character);
    }
    
}
