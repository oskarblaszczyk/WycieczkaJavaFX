package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import kurs.wycieczkajavafx.modules.*;

import java.io.*;
import java.util.Arrays;

public class WycieczkiController {

    public TextField dodajKrajMiasto;
    public TextField dodajCena;
    public ComboBox dodajMotyw;
    public Slider dodajOcene;
    public ComboBox rodzajWycieczki;
    public Label krajMiasto;
    public Label edytujEtykietaKrajMiasto;
    public ComboBox edytujRodzaj;
    public Button dodajWycieczke;
    public ComboBox dodajPrzewodnik;
    public ComboBox edytujPrzewodnik;
    private MainController mainController;
    private WycieczkaKrajowa krajowa = null;
    private WycieczkaZagraniczna zagraniczna = null;
    private Wycieczka zaznaczona = null;


    @FXML
    public ListView<Wycieczka> listWycieczki = new ListView<>();
    public ObservableList<Wycieczka> items = FXCollections.observableArrayList();

    @FXML
    public ListView<KlientZarejestrowany> klienciWycieczek = new ListView<>();
    public ObservableList<KlientZarejestrowany> itemsW = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
            Ekstensja.load(ois);
        } catch (Exception e) {
            System.out.println("Brak ekstensji");
        }
        try {
            items.addAll(Ekstensja.getEkstensja(WycieczkaKrajowa.class));
            items.addAll(Ekstensja.getEkstensja(WycieczkaZagraniczna.class));
        } catch (NullPointerException e) {
            // e.printStackTrace();
        }
        listWycieczki.setItems(items);

        ObservableList<MotywWycieczki> motywy = FXCollections.observableArrayList(MotywWycieczki.values());
        dodajMotyw.setItems(motywy);
        ObservableList<String> rodzaje = FXCollections.observableArrayList(Arrays.asList("Krajowa", "Zagraniczna"));
        rodzajWycieczki.setItems(rodzaje);

        try {
            ObservableList<Pracownik> przewodnik = FXCollections.observableArrayList(Ekstensja.getEkstensja(Przewodnik.class));
            dodajPrzewodnik.setItems(przewodnik);
        }catch (NullPointerException e){
            dodajPrzewodnik.setPromptText("Brak przewodnikow");
        }

    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.initialize();
    }

    public void zaznaczonaWycieczka(MouseEvent mouseEvent) {
        MultipleSelectionModel<Wycieczka> selected = listWycieczki.getSelectionModel();
        zaznaczona = selected.getSelectedItem();


//        krajowa = selected.getSelectedItem();
//        zagraniczna = selected.getSelectedItem();


//        klientID.setText(String.valueOf(naznaczony.getId()));
//        edytujImie.setText(naznaczony.getImie());
//        edytujNazwisko.setText(naznaczony.getNazwisko());
//        edytujTelefon.setText(naznaczony.getNumerTelefonu());
//        StringBuilder sb = new StringBuilder();
//        for (String s : naznaczony.getEmail()) {
//            sb.append(s + "\n");
//        }
//        edytujMail.setText(sb.toString());
//
        itemsW.clear();
        try {
            itemsW.addAll(krajowa.getKlienci());
            itemsW.addAll(zagraniczna.getKlienci());
            klienciWycieczek.setItems(itemsW);
        } catch (NullPointerException e) {

        }

        ObservableList<String> rodzaje = FXCollections.observableArrayList(Arrays.asList("Krajowa", "Zagraniczna"));
        edytujRodzaj.setItems(rodzaje);
        if (selected.getSelectedItem().getClass() == WycieczkaKrajowa.class) {
            edytujRodzaj.getSelectionModel().selectFirst();
        } else {
            edytujRodzaj.getSelectionModel().selectLast();
        }


        try {
            ObservableList<Pracownik> przewodnik = FXCollections.observableArrayList(Ekstensja.getEkstensja(Przewodnik.class));
            edytujPrzewodnik.setItems(przewodnik);
        }catch (NullPointerException e){
            edytujPrzewodnik.setPromptText("Brak przewodnikow");
        }
        try {
            edytujPrzewodnik.getSelectionModel().select(selected.getSelectedItem().getPrzewodnik());
        }catch (NullPointerException e){
            edytujPrzewodnik.setPromptText("Nie wybrano przewodnika");
        }
        System.out.println(selected.getSelectedItem().getPrzewodnik());
        System.out.println(selected.getSelectedItem().getClass());
        System.out.println(WycieczkaKrajowa.class);

    }

    public void usunWycieczke(ActionEvent actionEvent) throws IOException {
        try {
            Ekstensja.getEkstensja(WycieczkaKrajowa.class).remove(zaznaczona);
            Ekstensja.getEkstensja(WycieczkaZagraniczna.class).remove(zaznaczona);
        } catch (NullPointerException e) {
            //todo
        }
        items.remove(zaznaczona);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
        Ekstensja.save(oos);
        oos.close();
    }

    public void dodajWycieczke(ActionEvent actionEvent) {

    }

    public void zapiszEdycje(ActionEvent actionEvent) {
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void rodzajWycieczki(ActionEvent actionEvent) {
        if (rodzajWycieczki.getValue().toString().equals("Krajowa")) {
            krajMiasto.setText("Miasto");
        } else {
            krajMiasto.setText("Kraj");
        }
    }

    public void edytujRodzaj(ActionEvent actionEvent) {
        if (edytujRodzaj.getValue().toString().equals("Krajowa")) {
            edytujEtykietaKrajMiasto.setText("Miasto");
        } else {
            edytujEtykietaKrajMiasto.setText("Kraj");
        }

    }
}
