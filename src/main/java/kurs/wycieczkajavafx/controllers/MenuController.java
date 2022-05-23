package kurs.wycieczkajavafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainController;

    @FXML
    public void openWycieczki(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/kurs/fxml/WycieczkiScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WycieczkiController wycieczkiController = loader.getController();
        wycieczkiController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void openSprzedaj(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/kurs/fxml/SprzedajScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SprzedajController sprzedajController = loader.getController();
        sprzedajController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void openPracownicy(ActionEvent actionEvent) {
    }

    @FXML
    public void openKlienci(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/kurs/fxml/KlienciScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KlienciController klienciController = loader.getController();
        klienciController.setMainController(mainController);
        mainController.setScreen(pane);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
