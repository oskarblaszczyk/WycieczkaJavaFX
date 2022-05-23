package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import kurs.wycieczkajavafx.modules.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class SprzedajController {
    public ComboBox sprzedawca;
    public ComboBox wycieczka;
    public ComboBox klient;
    private MainController mainController;
    @FXML
    public void initialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
            Ekstensja.load(ois);
        } catch (Exception e) {
            System.out.println("Brak ekstensji");
        }
        try {
            ObservableList<PracownikObslugiKlienta> listaSprzedawcow = FXCollections.observableArrayList(Ekstensja.getEkstensja(PracownikObslugiKlienta.class));
            sprzedawca.setItems(listaSprzedawcow);
            ObservableList<WycieczkaKrajowa> listaWKraj = FXCollections.observableArrayList(Ekstensja.getEkstensja(WycieczkaKrajowa.class));
            wycieczka.setItems(listaWKraj);
            ObservableList<WycieczkaZagraniczna> listaWZag = FXCollections.observableArrayList(Ekstensja.getEkstensja(WycieczkaZagraniczna.class));
            wycieczka.setItems(listaWZag);
            ObservableList<KlientZarejestrowany> listaKlient = FXCollections.observableArrayList(Ekstensja.getEkstensja(KlientZarejestrowany.class));
            klient.setItems(listaKlient);
        }catch (NullPointerException e){

        }

    }
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.initialize();
    }

    public void spzedajButton(ActionEvent actionEvent) {
        if(sprzedawca.getValue() == null){
            //todo
        }
        if (wycieczka.getValue() == null){
            //todo
        }
        if(klient.getValue() == null){

        }

       // ObslugaKlienta obsluga = new ObslugaKlienta(LocalDate.now(), sprzedawca.getSelectionModel().getSelectedItem(), klient.getValue().);

    }
}
