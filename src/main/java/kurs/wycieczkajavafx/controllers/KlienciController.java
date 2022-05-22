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

public class KlienciController {



    private MainController mainController;
    private KlientZarejestrowany naznaczony = null;


    @FXML
    public ListView<KlientZarejestrowany> listKlienci = new ListView<>();
    public ObservableList<KlientZarejestrowany> items = FXCollections.observableArrayList();

    @FXML
    public ListView<Wycieczka> wycieczkiKlienta = new ListView<>();
    public ObservableList<Wycieczka> itemsW = FXCollections.observableArrayList();

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
    public TextField najwiecejWydal;

    @FXML
    public void dodajKlienta(ActionEvent actionEvent) throws IOException {
        KlientZarejestrowany nowy = new KlientZarejestrowany(dodajImie.getText(), dodajNazwisko.getText(), dodajTelefon.getText());
        String[] maile = dodajMail.getText().split("\n");
        for (String s : maile) {
            nowy.getEmail().add(s);
        }
        System.out.println(nowy.getEmail());

        items.add(nowy);
        listKlienci.refresh();
        dodajImie.clear();
        dodajNazwisko.clear();
        dodajTelefon.clear();
        dodajMail.clear();

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
        Ekstensja.getEkstensja(KlientZarejestrowany.class).remove(naznaczony);
        items.remove(naznaczony);
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
        if (Ekstensja.getEkstensja(KlientZarejestrowany.class) == null) {

        } else {
            items.addAll(Ekstensja.getEkstensja(KlientZarejestrowany.class));
            listKlienci.setItems(items);
        }

        KlientZarejestrowany najwiece = listKlienci.getItems().get(0);
        for(KlientZarejestrowany k : listKlienci.getItems()){
            if(sumaKosztow(najwiece) < sumaKosztow(k)){
                najwiece = k;
            }
        }

        najwiecejWydal.setText(najwiece.toString());


    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void zapiszEdycje(ActionEvent actionEvent) throws IOException {
        naznaczony.setImie(edytujImie.getText());
        naznaczony.setNazwisko(edytujNazwisko.getText());
        naznaczony.setNumerTelefonu(edytujTelefon.getText());
        String[] maile = edytujMail.getText().split("\n");
        naznaczony.getEmail().clear();
        for (String s : maile) {
            naznaczony.getEmail().add(s);
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
        Ekstensja.save(oos);
        oos.close();
    }

    public void zaznaczonyKlient(MouseEvent mouseEvent) {
        MultipleSelectionModel<KlientZarejestrowany> selected = listKlienci.getSelectionModel();
        naznaczony = selected.getSelectedItem();
        klientID.setText(String.valueOf(naznaczony.getId()));
        edytujImie.setText(naznaczony.getImie());
        edytujNazwisko.setText(naznaczony.getNazwisko());
        edytujTelefon.setText(naznaczony.getNumerTelefonu());
        StringBuilder sb = new StringBuilder();
        for (String s : naznaczony.getEmail()) {
            sb.append(s + "\n");
        }
        edytujMail.setText(sb.toString());

        itemsW.clear();
        itemsW.addAll(naznaczony.getWycieczki());
        wycieczkiKlienta.setItems(itemsW);

    }

    private double sumaKosztow(KlientZarejestrowany klient) {
        double result = 0;
        for (Wycieczka w : klient.getWycieczki()){
            result += w.getCena();
        }
        return result;
    }
}
