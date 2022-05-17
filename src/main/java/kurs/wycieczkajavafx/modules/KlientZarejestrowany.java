package kurs.wycieczkajavafx.modules;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KlientZarejestrowany extends Klient {
	private final int id;
	private List<ObslugaKlienta> obslugaKlientow = new ArrayList<>();
	private List<Wycieczka> wycieczki = new ArrayList<>();

	public KlientZarejestrowany(String imie, String nazwisko, String numerTelefonu) throws IOException {
		super(imie, nazwisko, numerTelefonu);
		id = odczytajId();
		zapiszId(id + 1);
	}

	// metoda zapisujaca int do pliku oraz metoda odczytujaca z pliku
	private void zapiszId(int id) throws IOException {
		FileWriter fw = new FileWriter("klientId");
		fw.write(String.valueOf(id));
		fw.close();
	}

	private int odczytajId() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("klientid"));
		return Integer.parseInt(br.readLine());
	}

	public int getId() {
		return id;
	}

	public List<Wycieczka> getWycieczki() {
		return wycieczki;
	}

	public List<ObslugaKlienta> getObslugaKlientow() {
		return obslugaKlientow;
	}
}
