/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Renato
 */
public class Nobel extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent home = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage.setTitle("Nobel Prize Database Finder (NPDF)");
        Scene homescene = new Scene(home);
        stage.setScene(homescene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
