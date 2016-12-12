/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author jason
 */
public class MainWindowController implements Initializable {

    @FXML
    ChoiceBox cb_language;
    @FXML
    StackPane sp_center;

    Locale locale = Locale.getDefault();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initLanguageComboBox();
         new PaneLoader(sp_center).loadPane(locale);

    }
    
    
    
    // setup language select ComboBox
    public void initLanguageComboBox() {
        cb_language.getItems().add(Locale.CANADA);
        cb_language.getItems().add(Locale.CANADA_FRENCH);
        cb_language.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale object) {
                return object.getDisplayLanguage();
            }

            @Override
            public Locale fromString(String string) {
                return new Locale(string, "CA");
            }

        });
        cb_language.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                locale = (Locale) cb_language.getValue();
                System.out.println(" selected language : " + locale);

                new PaneLoader(sp_center).loadPane(locale);
            }
        });

    }

}
