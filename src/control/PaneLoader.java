/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author jason
 */
public class PaneLoader {

    // use a stackpane as a container to hold the new loaded pane
    public StackPane parent;

    public PaneLoader() {
    }

    public PaneLoader(StackPane parent) {
        this.parent = parent;
    }
    

    public StackPane getParent() {
        return parent;
    }

    public void setParent(StackPane parent) {
        this.parent = parent;
    }
    
    /**
     * create FXMLLoader, setResources, load a anchorPane from FXML file, set it as the only pane in a stackPane container
     * @param locale
     */
    public void loadPane(Locale locale) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("bundles.converter", locale));

            AnchorPane pane = fxmlLoader.load(this.getClass().getResource("CurrencyConverter.fxml").openStream());
            parent.getChildren().setAll(pane);
            System.out.println("loading pane...");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    


}
