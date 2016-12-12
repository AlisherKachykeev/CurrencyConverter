/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.google.gson.JsonSyntaxException;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.json.JSONException;
import yahoo.api.Converter;

/**
 * FXML Controller class
 *
 * @author jason
 */
public class CurrencyConverterController implements Initializable {

    @FXML
    Label lbl_title, lbl_from, lbl_to;
    @FXML
    TextField tf_from, tf_to;
    @FXML
    ComboBox cb_from, cb_to;

    StringConverter<Currency> currencyConverter;
    Map<String, Locale> mapCurrencyLocale;
    BigDecimal rate100 = BigDecimal.ZERO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Locale locale = rb.getLocale();
        initCurrencyComboBox(locale);
        setupStringConverter(locale);
        setupOnActionEventHandler(cb_from, lbl_from);
        setupOnActionEventHandler(cb_to, lbl_to);

        setupOnChangeEventHandler();

    }

    public void calculateTo() {
        try {
            BigDecimal bd_result = new BigDecimal(0);
            BigDecimal bd_from = new BigDecimal(tf_from.getText());
            BigDecimal bd_to = bd_from.multiply(rate100).multiply(new BigDecimal(0.01));
            bd_result = bd_to.setScale(2, RoundingMode.HALF_UP);
            tf_to.setText(bd_result.toPlainString());
        } catch (Exception ex) {
            tf_to.setText("---");
            ex.printStackTrace();
        }
    }

    public void calculateFrom() {
        try {
            BigDecimal bd_result = new BigDecimal(0);
            BigDecimal bd_to = new BigDecimal(tf_to.getText());
            BigDecimal bd_from = bd_to.multiply(new BigDecimal(100).divide(rate100, 6, RoundingMode.HALF_UP));
            bd_result = bd_from.setScale(2, RoundingMode.HALF_UP);
            tf_from.setText(bd_result.toPlainString());
        } catch (Exception ex) {
            tf_from.setText("---");
            ex.printStackTrace();
        }
    }

    public void setupOnActionEventHandler(ComboBox cb, Label symbol) {
        EventHandler onActionEventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                rate100 = BigDecimal.ZERO;
                Currency tempCurrency = (Currency) cb.getValue();
                Locale tempLocale = mapCurrencyLocale.get(tempCurrency.getCurrencyCode());
                if (tempLocale == null) {
                    tempLocale = Locale.CANADA;
                }
                symbol.setText(tempCurrency.getSymbol(tempLocale));

                // get exchange rate from yahoo
                try {
                    rate100 = Converter.convert("100", ((Currency) cb_from.getValue()).getCurrencyCode(), ((Currency) cb_to.getValue()).getCurrencyCode()).getVal();

                } catch (Exception ex) {
                    System.out.println(" error : Converter.convert");
                    ex.printStackTrace();
                }
                tf_from.setText("100");

                tf_to.setText(rate100.toPlainString());
            }
        };

        cb.setOnAction(onActionEventHandler);

    }

    public void setupOnChangeEventHandler() {

        tf_from.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (tf_from.isFocused()) {
                    calculateTo();
//                    try {
//                        tf_to.setText(Double.toString(Double.parseDouble(tf_from.getText()) * rate100.doubleValue() * 0.01));
//
//                    } catch (Exception ex) {
//                        tf_to.setText("---");
//                    }
                }

            }

        });

        tf_to.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (tf_to.isFocused()) {
                    calculateFrom();
//                    try {
//                        tf_from.setText(Double.toString(Double.parseDouble(tf_to.getText()) / rate100.doubleValue() * 100));
//
//                    } catch (Exception ex) {
//                        tf_from.setText("---");
//                    }
                }

            }

        });
    }

    public void initCurrencyComboBox(Locale locale) {
        // 32 currencys
        Locale[] localeList = {Locale.FRANCE, Locale.JAPAN, Locale.KOREA, Locale.PRC, Locale.TAIWAN, Locale.UK, Locale.US, Locale.CANADA};
        mapCurrencyLocale = new HashMap<>();

        cb_from.getItems().clear();
        cb_to.getItems().clear();

        Currency tempCurrency;
        for (Locale tempLocale : localeList) {
            tempCurrency = Currency.getInstance(tempLocale);
            cb_from.getItems().add(tempCurrency);
            cb_to.getItems().add(tempCurrency);
            mapCurrencyLocale.put(tempCurrency.getCurrencyCode(), tempLocale);
        }

        //
        cb_from.getSelectionModel().select(Currency.getInstance("USD"));
        cb_to.getSelectionModel().select(Currency.getInstance("CAD"));

        // get exchange rate from yahoo
        try {
            rate100 = Converter.convert("100", ((Currency) cb_from.getValue()).getCurrencyCode(), ((Currency) cb_to.getValue()).getCurrencyCode()).getVal();

        } catch (Exception ex) {
            System.out.println(" error : Converter.convert");
            ex.printStackTrace();
        }
        tf_from.setText("100");

        tf_to.setText(rate100.toPlainString());

    }

    public void setupStringConverter(Locale locale) {
        currencyConverter = new StringConverter<Currency>() {
            @Override
            public String toString(Currency object) {
                return object.getDisplayName(locale) + "(" + object.getSymbol(locale) + ")";
            }

            @Override
            public Currency fromString(String string) {
                return Currency.getInstance(string);
            }
        };

        cb_from.setConverter(currencyConverter);
        cb_to.setConverter(currencyConverter);
    }

}
