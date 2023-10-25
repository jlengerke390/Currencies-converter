package com.example.currencyconverter.controller;

import com.example.currencyconverter.formatterUtil.FormatterUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.DoubleStringConverter;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ControllerPrincipalView implements Initializable {

    @FXML
    private TextField inputField;
    @FXML
    private Label resultText = new Label();
    @FXML
    private Label resultValue = new Label();

    @FXML
    private ComboBox<String> currencyBoxFrom = new ComboBox<>();

    @FXML
    private ComboBox<String> currencyBoxTo = new ComboBox<>();

    private final String[] currencies = {"Select one", "COP", "EUR", "GBP", "JPY", "KRW","USD"};
    private String selectedFrom;
    private String selectedTo;
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    private final ConverterController converterController = new ConverterController();
    private FormatterUtil formatterUtil;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currencyBoxFrom.getItems().addAll(currencies);
        currencyBoxTo.getItems().addAll(currencies);

        currencyBoxFrom.getSelectionModel().selectFirst();
        currencyBoxTo.getSelectionModel().selectFirst();

        currencyBoxFrom.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Deshabilitar la misma divisa en el ComboBox de destino
                currencyBoxTo.setDisable(false); // Habilitar el ComboBox de destino
                if (currencyBoxTo.getValue() != null && currencyBoxTo.getValue().equals(newValue)) {
                    currencyBoxTo.setValue(null); // Deseleccionar la divisa en el ComboBox de destino
                }
            }
        });

        // Escuchar cambios en el ComboBox de destino
        currencyBoxTo.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Deshabilitar la misma divisa en el ComboBox de origen
                currencyBoxFrom.setDisable(false); // Habilitar el ComboBox de origen
                if (currencyBoxFrom.getValue() != null && currencyBoxFrom.getValue().equals(newValue) && !currencyBoxFrom.getValue().contentEquals("Select one")) {
                    currencyBoxFrom.setValue(null); // Deseleccionar la divisa en el ComboBox de origen
                }
            }
        });


        //This validation allows us to avoid non-numeric characters.
        TextFormatter<Double> textFormatter = new TextFormatter<>(
                new DoubleStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("-?\\d*(\\.\\d*)?")) {
                        return change;
                    }
                    return null;
                }
        );
        inputField.setTextFormatter(textFormatter);
    }
    public void convertButton(ActionEvent event) {
            try {

                double value = Double.parseDouble(inputField.getText());
                String numeroFormateado = formatterUtil.formatNumber(value);

                double result = converterController.convert(value, selectedFrom, selectedTo);
                numeroFormateado = formatterUtil.formatNumber(result);

                String resulPrint =inputField.getText() +" "+ selectedFrom +" = " + numeroFormateado +" "+ selectedTo;
                resultValue.setText(resulPrint);

            }catch (Exception e){
                alert.setTitle("Empty space");
                alert.setHeaderText("Information message");
                alert.setContentText("Any value must be entered");
                alert.showAndWait();
            }
    }
    public String getCurrencyFrom(ActionEvent event){
         this.selectedFrom = currencyBoxFrom.getValue();
        System.out.println(selectedFrom);
        return selectedFrom;
    }
    public String getCurrencyTo(ActionEvent event){
        this.selectedTo = currencyBoxTo.getSelectionModel().getSelectedItem();
        System.out.println(selectedTo);
        return selectedTo;
    }
    public void resetButton() {
        inputField.clear();
        resultValue.setText("");
        currencyBoxFrom.setValue("Select one");
        currencyBoxTo.setValue("Select one");
    }
}