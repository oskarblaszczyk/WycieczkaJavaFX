package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import kurs.wycieczkajavafx.modules.*;

import java.io.*;
import java.time.LocalDate;

public class SprzedajController {
    public ComboBox<PracownikObslugiKlienta> sprzedawca;
    public ComboBox<Wycieczka> wycieczka;
    public ComboBox<KlientZarejestrowany> klient;
    private MainController mainController;

    private ObservableList<PracownikObslugiKlienta> listaSprzedawcow = null;
    private ObservableList<Wycieczka> listaWKraj = null;
    //private ObservableList<WycieczkaZagraniczna> listaWZag = null;
    private ObservableList<KlientZarejestrowany> listaKlient = null;


    @FXML
    public void initialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
            Ekstensja.load(ois);
        } catch (Exception e) {
            System.out.println("Brak ekstensji");
        }
        try {
            listaSprzedawcow = FXCollections.observableArrayList(Ekstensja.getEkstensja(PracownikObslugiKlienta.class));
        } catch (NullPointerException e) {
            //todo
        }
        try {
            listaWKraj = FXCollections.observableArrayList(Ekstensja.getEkstensja(WycieczkaKrajowa.class));
            listaWKraj = FXCollections.observableArrayList(Ekstensja.getEkstensja(WycieczkaZagraniczna.class));

        } catch (NullPointerException e) {
            //todo
        }
//        try {
//            listaWZag = FXCollections.observableArrayList(Ekstensja.getEkstensja(WycieczkaZagraniczna.class));
//        } catch (NullPointerException e) {
//            //todo
//        }
        try {
            listaKlient = FXCollections.observableArrayList(Ekstensja.getEkstensja(KlientZarejestrowany.class));
        } catch (NullPointerException e) {
            //todo
        }

        sprzedawca.setItems(listaSprzedawcow);
        wycieczka.setItems(listaWKraj);
        // wycieczka.setItems(listaWZag);
        klient.setItems(listaKlient);

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.initialize();
    }

    public void spzedajButton(ActionEvent actionEvent) throws IOException {
        boolean pustePole = false;
        String komunikat = "";
        ObslugaKlienta o = null;
        try{
            o = new ObslugaKlienta(LocalDate.now(), sprzedawca.getValue(), klient.getValue() );
            sprzedawca.getValue().getObslugaKlientow().add(o);
        } catch (IllegalArgumentException e){
            pustePole = true;
            komunikat += "Sprzedawca\n";
        }

        try {
            klient.getValue().getWycieczki().add(wycieczka.getValue());
            System.out.println("sprzedano");
        } catch (NullPointerException e) {
            pustePole = true;
            komunikat += "Klient\nWycieczka\n";
        }

        if(pustePole){
            showError("Uzupełnij wszystkie pola!", komunikat);
        }


        // ObslugaKlienta obsluga = new ObslugaKlienta(LocalDate.now(), sprzedawca.getSelectionModel().getSelectedItem(), klient.getValue().);


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
        Ekstensja.save(oos);
        oos.close();


    }

    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }

}
