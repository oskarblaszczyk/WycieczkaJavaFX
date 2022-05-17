package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import kurs.wycieczkajavafx.modules.Ekstensja;
import kurs.wycieczkajavafx.modules.KlientZarejestrowany;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class KlienciController {

    private MainController mainController;

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.initialize();
    }

    @FXML
    ListView<KlientZarejestrowany> listKlienci = new ListView<>();

    @FXML
    public void initialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
            Ekstensja.load(ois);
        } catch (Exception e) {
            System.out.println("Brak ekstensji");
        }
        ObservableList<KlientZarejestrowany> items = FXCollections.observableArrayList();
        items.addAll(Ekstensja.getEkstensja(KlientZarejestrowany.class));
        System.out.println(Ekstensja.getEkstensja(KlientZarejestrowany.class));
        listKlienci.setItems(items);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
