package com.example.currencyconverter.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class ConverterController {
    private Map<String, Map<String, Double>> exchangesRate = new HashMap<>();

    public ConverterController(){
        exchangesRate.put("COP", new HashMap<>());
        exchangesRate.put("EUR", new HashMap<>());
        exchangesRate.put("GBP", new HashMap<>());
        exchangesRate.put("JPY", new HashMap<>());
        exchangesRate.put("KRW", new HashMap<>());
        exchangesRate.put("USD", new HashMap<>());

        ///////////////////////////////////////////////////////////
        exchangesRate.get("COP").put("EUR", 4561.99);
        exchangesRate.get("COP").put("GBP", 5286.47);
        exchangesRate.get("COP").put("JPY", 29.07);
        exchangesRate.get("COP").put("KRW", 3.21);
        exchangesRate.get("COP").put("USD", 4329.73);

        exchangesRate.get("EUR").put("GBP", 1.16);
        exchangesRate.get("EUR").put("JPY", 0.0064);
        exchangesRate.get("EUR").put("KRW", 0.00070);
        exchangesRate.get("EUR").put("USD", 0.95);

        exchangesRate.get("GBP").put("COP", 5282.69);
        exchangesRate.get("GBP").put("EUR", 1.16);
        exchangesRate.get("GBP").put("JPY", 181.79);
        exchangesRate.get("GBP").put("KRW", 1650.0);
        exchangesRate.get("GBP").put("USD", 1.22);

        exchangesRate.get("JPY").put("COP", 29.05);
        exchangesRate.get("JPY").put("EUR", 0.0064);
        exchangesRate.get("JPY").put("GBP", 0.0055);
        exchangesRate.get("JPY").put("KRW", 9.07);
        exchangesRate.get("JPY").put("USD", 0.0067);

        exchangesRate.get("KRW").put("COP", 3.20);
        exchangesRate.get("KRW").put("EUR", 0.0007);
        exchangesRate.get("KRW").put("GBP", 0.00061);
        exchangesRate.get("KRW").put("JPY", 0.11);
        exchangesRate.get("KRW").put("USD", 0.00074);

        exchangesRate.get("USD").put("COP", 4317.0);
        exchangesRate.get("USD").put("EUR", 0.95);
        exchangesRate.get("USD").put("GBP", 0.82);
        exchangesRate.get("USD").put("JPY", 148.61);
        exchangesRate.get("USD").put("KRW", 1349.09);
        ////////////////////////////////////////////////////////////
        exchangesRate.get("EUR").put("COP", (1.0/4564.97));
        exchangesRate.get("GBP").put("COP", (1.0/5286.47));
        exchangesRate.get("JPY").put("COP", (1.0/29.07));
        exchangesRate.get("KRW").put("COP", (1.0/3.21));
        exchangesRate.get("USD").put("COP", (1.0/4329.73));

        exchangesRate.get("COP").put("EUR", (1.0/0.22));
        exchangesRate.get("GBP").put("EUR", (1.0/1.16));
        exchangesRate.get("JPY").put("EUR", (1.0/0.0064));
        exchangesRate.get("KRW").put("EUR", (1.0/0.00070));
        exchangesRate.get("USD").put("EUR", (1.0/0.95));

        exchangesRate.get("COP").put("GBP", (1.0/0.00019));
        exchangesRate.get("EUR").put("GBP", (1.0/0.86));
        exchangesRate.get("JPY").put("GBP", (1.0/0.0055));
        exchangesRate.get("KRW").put("GBP", (1.0/0.00061));
        exchangesRate.get("USD").put("GBP", (1.0/0.82));

        exchangesRate.get("COP").put("JPY", (1.0/0.35));
        exchangesRate.get("EUR").put("JPY", (1.0/157.08));
        exchangesRate.get("GBP").put("JPY", (1.0/181.87));
        exchangesRate.get("KRW").put("JPY", (1.0/0.11));
        exchangesRate.get("USD").put("JPY", (1.0/148.61));

        exchangesRate.get("COP").put("KRW", (1.0/0.31));
        exchangesRate.get("EUR").put("KRW", (1.0/1425.73));
        exchangesRate.get("GBP").put("KRW", (1.0/1651.07));
        exchangesRate.get("JPY").put("KRW", (1.0/9.08));
        exchangesRate.get("USD").put("KRW", (1.0/1348.90));

        exchangesRate.get("COP").put("USD", (1.0/0.00023));
        exchangesRate.get("EUR").put("USD", (1.0/1.06));
        exchangesRate.get("GBP").put("USD", (1.0/1.22));
        exchangesRate.get("JPY").put("USD", (1.0/0.0067));
        exchangesRate.get("KRW").put("USD", (1.0/000.74));
        //////////////////////////////////////////////////////////////
    }

    public double convert(double amount, String currencyOrigin, String currencyDestination){
        if(exchangesRate.containsKey(currencyOrigin) && exchangesRate.get(currencyOrigin).containsKey(currencyDestination)){
            double exchange = exchangesRate.get(currencyOrigin).get(currencyDestination);
            return amount / exchange;
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Choose a currencie");
            alert.setHeaderText("Information message");
            alert.setContentText("You must select the currencies");
            alert.showAndWait();
            ControllerPrincipalView controllerPrincipalView = new ControllerPrincipalView();
            controllerPrincipalView.resetButton();
            return 0.0;

        }
    }

}
