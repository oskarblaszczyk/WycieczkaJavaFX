package kurs.wycieczkajavafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import kurs.wycieczkajavafx.modules.Ekstensja;
import kurs.wycieczkajavafx.modules.KlientZarejestrowany;
import kurs.wycieczkajavafx.modules.Wycieczka;

import java.io.*;
import java.util.Arrays;

public class KlienciController {


    private MainController mainController;

    @FXML
    ListView<KlientZarejestrowany> listKlienci = new ListView<>();
    ObservableList<KlientZarejestrowany> items = FXCollections.observableArrayList();

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
        KlientZarejestrowany nowy = new KlientZarejestrowany(dodajImie.getText(), dodajNazwisko.getText(), dodajTelefon.getText());
        String[] maile = dodajMail.getText().split("\\n");
        nowy.getEmail().addAll(Arrays.stream(maile).toList());
        items.add(nowy);
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
        MultipleSelectionModel<KlientZarejestrowany> klienci = listKlienci.getSelectionModel();
        items.removeAll(klienci.getSelectedItems());
        Ekstensja.getEkstensja(KlientZarejestrowany.class).removeAll(klienci.getSelectedItems());
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
        if (Ekstensja.getEkstensja(KlientZarejestrowany.class) == null){

        }else{
            items.addAll(Ekstensja.getEkstensja(KlientZarejestrowany.class));
            listKlienci.setItems(items);
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void zapiszEdycje(ActionEvent actionEvent) {
    }

    public void selectedKlient(MouseEvent mouseEvent) {
        MultipleSelectionModel<KlientZarejestrowany> wycieczki = listKlienci.getSelectionModel();

        try {
            itemsW.addAll(wycieczki.getSelectedItem().getWycieczki());
            wycieczkiKlienta.setItems(itemsW);
        }catch (NullPointerException e){
            System.out.println("brak wycieczek do wyswietlenia");
        }
        }
    }
