package kurs.wycieczkajavafx.modules;

public abstract class Pracownik extends Osoba {
	private double pensjaMinimalna = 2500;
	private double przepracowaneGodziny;
	private RodzajUmowy rodzajUmowy;

	public Pracownik(String imie, String nazwisko, String numerTelefonu) {
		super(imie, nazwisko, numerTelefonu);
	}

	public Pracownik(String imie, String nazwisko, String numerTelefonu, RodzajUmowy rodzajUmowy) {
		super(imie, nazwisko, numerTelefonu);
		this.rodzajUmowy = rodzajUmowy;
	}

	public abstract double pensja();

	public double getPensjaMinimalna() {
		return pensjaMinimalna;
	}

	public void setPensjaMinimalna(double pensjaMinimalna) {
		this.pensjaMinimalna = pensjaMinimalna;
	}

	public double getPrzepracowaneGodziny() {
		return przepracowaneGodziny;
	}

	public void setPrzepracowaneGodziny(double przepracowaneGodziny) {
		this.przepracowaneGodziny = przepracowaneGodziny;
	}

	public RodzajUmowy getRodzajUmowy() {
		return rodzajUmowy;
	}
}
