package kurs.wycieczkajavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
	public static void main(String[] args) throws IOException {

		launch(args);



//		 wczytanie ekstensji
//		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BazaDanych"))) {
//			Ekstensja.load(ois);
//		} catch (Exception e) {
//			System.out.println("Brak ekstensji");
//		}
//
//		try {
//			for (Wycieczka w : Ekstensja.getEkstensja(WycieczkaKrajowa.class)) {
//				System.out.println(w);
//			}
//		} catch (NullPointerException e) {
//			System.out.println("nie ma klientow");
//		}
//	//
//		KlientZarejestrowany klient1 = new KlientZarejestrowany("Jan", "Kowalski", "0048123456789");
//		KlientZarejestrowany klient2 = new KlientZarejestrowany("Basia", "Mazurek", "0048126789");
////				Przewodnik przewodnik1 = new Przewodnik("Adam", "Nowak", "0048987654321");
//		//		PracownikObslugiKlienta pracownikObslugi1 = new PracownikObslugiKlienta("Anna", "Jankowska", "0048456123987");
//		//
//				Wycieczka w1 = new WycieczkaKrajowa(2410, MotywWycieczki.AKTYWNOSC, 3.5, "Krakow");
//				Wycieczka w2 = new WycieczkaKrajowa(3900, MotywWycieczki.ZWIEDZANIE, 2.8, "Warszawa");
//		//        KartaInformacyjna karta1 = new KartaInformacyjna(w1);
//		//
//		//        Obsluga obsluga1 = new Obsluga(LocalDate.now(), pracownikObslugi1, klient1, w1);
//		//        Obsluga obsluga2 = new Obsluga(LocalDate.now(), pracownikObslugi1, klient2, w2);
//		//
//		//        System.out.println(klient1.kupioneWycieczki());
//		//        System.out.println(Klient.getKlienci());
//		//        System.out.println(KlientZarejestrowany.wydalNajwiecej(KlientZarejestrowany.getKlienciZarejestrowani()));
//
//		// zapis za pomoca serializacji
////
//		klient1.getWycieczki().add(w1);
//		klient1.getWycieczki().add(w2);
//		klient2.getWycieczki().add(w1);
//
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BazaDanych"));
//		Ekstensja.save(oos);
//		oos.close();
//
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/kurs/fxml/MainScreen.fxml"));
		StackPane stackPane = fxmlLoader.load();
		Scene scene = new Scene(stackPane, 800, 600);
		stage.setTitle("Wycieczki");
		stage.setScene(scene);
		stage.show();
	}
}
