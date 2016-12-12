/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author jason
 */
public class AppCtr extends Application {

    @Override
    public void start(Stage stage) throws IOException {

//        Locale locale = Locale.getDefault();
//        locale = new Locale("fr", "CA");

        BorderPane homeBorderPane = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Scene mainScene = new Scene(homeBorderPane, 600, 450);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                System.exit(0);
            }
        });

        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
