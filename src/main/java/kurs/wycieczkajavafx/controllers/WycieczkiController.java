package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import kurs.wycieczkajavafx.modules.Ekstensja;
import kurs.wycieczkajavafx.modules.Wycieczka;
import kurs.wycieczkajavafx.modules.WycieczkaKrajowa;
import kurs.wycieczkajavafx.modules.WycieczkaZagraniczna;

import java.io.*;

public class WycieczkiController {

    private MainController mainController;

    @FXML
    ListView<Wycieczka> listWycieczki = new ListView<>();
    ObservableList<Wycieczka> items = FXCollections.observableArrayList();

    @FXML
    public ListView<Wycieczka> wycieczkiKlienta = new ListView<>();
    ObservableList<Wycieczka> itemsW = FXCollections.observableArrayList();

    @FXML
    public TextField dodajImie;

    @FXML
    public TextField dodajNazwisko;

    @FXML
    public TextField dodajTelefon;

    @FXML
    public TextArea dodajMail;

    @FXML
    public TextField edytujImie;

    @FXML
    public TextField edytujNazwisko;

    @FXML
    public TextField edytujTelefon;

    @FXML
    public TextArea edytujMail;

    @FXML
    public void dodajKlienta(ActionEvent actionEvent) throws IOException {
        // Wycieczka nowy = new WycieczkaKrajowa();
        //String[] maile = dodajMail.getText().split("\\n");
        // nowy.getEmail().addAll(Arrays.stream(maile).toList());
        // items.add(nowy);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
        Ekstensja.save(oos);
        oos.close();
    }

    @FXML
    public TextField klientID;


    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.initialize();
    }

    @FXML
    public void deleteKlient(ActionEvent actionEvent) throws IOException {
        MultipleSelectionModel<Wycieczka> wycieczki = listWycieczki.getSelectionModel();
        items.removeAll(wycieczki.getSelectedItems());
        Ekstensja.getEkstensja(WycieczkaKrajowa.class).removeAll(wycieczki.getSelectedItems());
        //Ekstensja.getEkstensja(WycieczkaZagraniczna.class).removeAll(wycieczki.getSelectedItems());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
        Ekstensja.save(oos);
        oos.close();
    }

    @FXML
    public void initialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
            Ekstensja.load(ois);
        } catch (Exception e) {
            System.out.println("Brak ekstensji");
        }
        if (Ekstensja.getEkstensja(WycieczkaKrajowa.class) == null) {
            System.out.println("null");
        } else {
            System.out.println("test");
            System.out.println(Ekstensja.getEkstensja(Wycieczka.class));
            items.addAll(Ekstensja.getEkstensja(WycieczkaKrajowa.class));
            listWycieczki.setItems(items);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void zapiszEdycje(ActionEvent actionEvent) {
    }

    public void selectedKlient(MouseEvent mouseEvent) {
//        MultipleSelectionModel<Wycieczka> wycieczki = listKlienci.getSelectionModel();
//
//        try {
//            itemsW.addAll(wycieczki.getSelectedItem().getWycieczki());
//            wycieczkiKlienta.setItems(itemsW);
//        }catch (NullPointerException e){
//            System.out.println("brak wycieczek do wyswietlenia");
//        }
    }
}