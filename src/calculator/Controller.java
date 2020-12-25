package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML private Label result;
    @FXML private Label example;
    @FXML private static int point = 0;
    @FXML private static int oper = 0;

    @FXML private int operationIndex;

    @FXML private String value;
    @FXML private String operation;
    @FXML private String lastSymbol = "";
    @FXML private static int size;
    @FXML private double firstNumber;
    @FXML private double secondNumber;

    @FXML
    private void numberPressed(ActionEvent event) {

        if((result.getText()).equals("ERROR")) {
            example.setText("");
            result.setText("");
        }
        else if (lastSymbol.equals("=")) {
            example.setText("");
            result.setText("");
            lastSymbol = "";
        }

        size = result.getText().length();
        value = ((Button)event.getSource()).getText();

        if(size >= 17){
            result.setText(result.getText());
        }
        else {

            if(value.equals("0") && result.getText().equals("0")){
                result.setText(result.getText());
            }
            else if (!value.equals("0") && result.getText().equals(("0"))) {
                result.setText(value);
                lastSymbol = value;
            }
            else if (oper == 1 && size > 2 && lastSymbol.equals("0")){
                String sub = (result.getText()).substring(size - 2, size - 1);
                if(value.equals("0") && (sub.equals("+") || sub.equals("-") || sub.equals("x") || sub.equals("/"))) {
                    result.setText(result.getText());
                }
                else if (!value.equals("0") && (sub.equals("+") || sub.equals("-") || sub.equals("x") || sub.equals("/"))){
                    result.setText((result.getText()).substring(0, size - 1) + value);
                    lastSymbol = value;
                }
                else {
                    result.setText(result.getText() + value);
                    lastSymbol = value;
                }
            }
            else {
                result.setText(result.getText() + value);
                lastSymbol = value;
            }
        }
    }

    @FXML
    private void pointPressed(ActionEvent event) {

        size = result.getText().length();
        value = ((Button)event.getSource()).getText();

        if(size >= 17){
            result.setText(result.getText());
        }
        else if (!lastSymbol.equals("=")){
            if(!lastSymbol.equals("+") && !lastSymbol.equals("-") && !lastSymbol.equals("x") && !lastSymbol.equals("/")) {
                if(!result.getText().equals("ERROR")){
                    if(result.getText().length() == 0) {
                        result.setText(result.getText());
                    }
                    else if(point == 0 && oper == 0) {
                        point = 1;
                        result.setText(result.getText() + value);
                        lastSymbol = value;
                    }
                    else if((point == 0 && oper == 1) || (point == 1 && oper == 1)){
                        point = 2;
                        result.setText(result.getText() + value);
                        lastSymbol = value;
                    }
                }
            }
        }
    }

    @FXML
    private void operationPressed(ActionEvent event){

        size = result.getText().length();
        value = ((Button)event.getSource()).getText();

        if(size >= 17){
            result.setText(result.getText());
        }
        else {
            if (result.getText().equals("ERROR")){
                result.setText("");
                example.setText("");
                lastSymbol = "";
            }

            if(!lastSymbol.equals(".")){
                if(oper == 0){
                    if(result.getText().length() == 0 && !value.equals("-")) {
                        result.setText(result.getText());
                    }

                    else if(result.getText().length() == 0 && value.equals("-")){
                        result.setText(result.getText() + value);
                        lastSymbol = value;
                    }

                    else if(result.getText().length() != 0 && result.getText().equals("-")){
                        result.setText(result.getText());
                    }

                    else {
                        result.setText(result.getText() + value);
                        operation = value;
                        operationIndex = result.getText().length() - 1;
                        oper = 1;
                        lastSymbol = value;
                    }
                }
            }
        }

    }

    @FXML
    private void clearPressed(ActionEvent event){

        value = ((Button)event.getSource()).getText();

        if(value.equals("C")){
            result.setText("");
            example.setText("");
            point = 0;
            oper = 0;
            lastSymbol = "";
        }
        else if(value.equals("DEL")){

            if(result.getText().equals("ERROR") || lastSymbol.equals("=")){
                result.setText("");
                example.setText("");
                lastSymbol = "";
            }

            String del = result.getText();
            size = del.length();

            if(size != 0){
                char symbol = del.charAt(size - 1);
                if(symbol == '.'){
                    point -= 1;
                }

                if((symbol == '+' || symbol == '-' || symbol == 'x' || symbol == '/') && oper!=0){
                    oper -= 1;
                }

                result.setText(del.substring(0, size - 1));

                if(size >= 2) {
                    lastSymbol = del.substring(size - 2, size - 1);
                }
                else {
                    lastSymbol = "";
                }
            }
        }
    }

    @FXML
    private void plusMinusPressed(ActionEvent event) {
        size = result.getText().length();
        if(oper == 0 && result.getText().equals("")){
            result.setText("-");
        }
        else if (oper == 0 && size != 0 && !result.getText().substring(0, 1).equals("-")) {
            result.setText("-" + result.getText());
        }
        else if (oper == 0 && size != 0 && result.getText().substring(0, 1).equals("-")) {
            result.setText(result.getText().substring(1,size));
        }
    }

    @FXML
    private void equalsPressed(ActionEvent event){

        String t = result.getText();
        size = t.length();
        if(size != 0){
            char last = t.charAt(size - 1);

            if(oper != 0 && (last == '+' || last == '-' || last == 'x' || last == '/' || last =='.')) {
                result.setText(result.getText());
            }
            else if (oper != 0) {
                firstNumber = Double.parseDouble(t.substring(0, operationIndex));
                secondNumber = Double.parseDouble((t.substring(operationIndex + 1, size)));

                String rez = Calculation.calculate(firstNumber, secondNumber, operation);

                value = ((Button)event.getSource()).getText();
                example.setText(result.getText() + value);
                result.setText(rez);

                point = 0;
                oper = 0;
                lastSymbol = "=";
            }
            else if(lastSymbol.equals("=") && !result.getText().equals("ERROR")) {
                firstNumber = Double.parseDouble(result.getText());

                String rez = Calculation.calculate(firstNumber, secondNumber, operation);

                value = ((Button)event.getSource()).getText();
                example.setText(result.getText() + operation + secondNumber + value);
                result.setText(rez);

                point = 0;
                oper = 0;
                lastSymbol = "=";
            }
        }
    }
}
