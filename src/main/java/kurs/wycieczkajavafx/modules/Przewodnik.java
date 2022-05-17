package kurs.wycieczkajavafx.modules;

import java.util.ArrayList;
import java.util.List;

public class Przewodnik extends Pracownik {
	private List<String> jezyki = new ArrayList<>();
	private List<Wycieczka> wycieczki = new ArrayList<>();

	public Przewodnik(String imie, String nazwisko, String numerTelefonu) {
		super(imie, nazwisko, numerTelefonu);
	}

	public List<Wycieczka> getWycieczki() {
		return wycieczki;
	}

	public List<String> getJezyki() {
		return jezyki;
	}

	@Override
	public double pensja() {
		return getPensjaMinimalna() + getRodzajUmowy().dodatekPensji; //todo pensja zrobione
	}
}
