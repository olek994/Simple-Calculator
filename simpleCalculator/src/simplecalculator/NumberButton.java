
package simplecalculator;

import javafx.scene.control.Button;


public class NumberButton extends Button {
    private Integer number;
    
    public NumberButton(String text)
    {
        this.number = Integer.parseInt(text);
        this.setText(number.toString());
        this.setOnAction(e -> {
            
            SimpleCalculator.addToInput(number.toString());
        });
    }
    
    
    
}
