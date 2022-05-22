package kurs.wycieczkajavafx.modules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Wycieczka extends Ekstensja {
	private int id;
	private double cena;
	private final MotywWycieczki motyw; //todo enum - zrobione
	private double ocena; // 0 - 5
	private Przewodnik przewodnik;
	private KartaInformacyjna kartaInformacyjna;
	private List<KlientZarejestrowany> klienci = new ArrayList<>();

	public Wycieczka(double cena, MotywWycieczki motyw, double ocena) throws IOException {

		this.cena = cena;
		this.motyw = motyw;
		this.ocena = ocena;
		id = odczytajId();
		zapiszId(id + 1);
	}

	//todo ID zapis/odczyt plik- zrobione
	private void zapiszId(int id) throws IOException {
		FileWriter fw = new FileWriter("wycieczkaid");
		fw.write(String.valueOf(id));
		fw.close();
	}

	private int odczytajId() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("wycieczkaId"));
		return Integer.parseInt(br.readLine());
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public int getId() {
		return id;
	}

	public MotywWycieczki getMotyw() {
		return motyw;
	}

	public Przewodnik getPrzewodnik() {
		return przewodnik;
	}

	public void setPrzewodnik(Przewodnik przewodnik) {
		this.przewodnik = przewodnik;
	}

	public KartaInformacyjna getKartaInformacyjna() {
		return kartaInformacyjna;
	}

	public void setKartaInformacyjna(KartaInformacyjna kartaInformacyjna) {
		this.kartaInformacyjna = kartaInformacyjna;
	}

	public List<KlientZarejestrowany> getKlienci() {
		return klienci;
	}

	@Override
	public String toString() {
		return "Wycieczka{" + "id=" + id + ", cena=" + cena + ", motyw='" + motyw + '\'' + ", ocena=" + ocena
				+ ", przewodnik=" + przewodnik + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Wycieczka wycieczka = (Wycieczka) o;
		return id == wycieczka.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}
