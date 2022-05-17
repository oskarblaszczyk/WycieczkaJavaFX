package kurs.wycieczkajavafx.modules;

import java.util.ArrayList;
import java.util.List;

public class KartaInformacyjna extends Ekstensja {
	private List<String> ograniczenia = new ArrayList<>();
	private List<String> uwagi = new ArrayList<>();
	private final Wycieczka wycieczka;

	// todo kompozycja - zrobiona
	public KartaInformacyjna(String ograniczenie, String uwaga, Wycieczka wycieczka) {
		if (wycieczka == null) {
			throw new IllegalArgumentException("argument nie moze byc nullem");
		}
		ograniczenia.add(ograniczenie);
		uwagi.add(uwaga);
		this.wycieczka = wycieczka;
		wycieczka.setKartaInformacyjna(this);
	}

	public List<String> getOgraniczenia() {
		return ograniczenia;
	}

	public List<String> getUwagi() {
		return uwagi;
	}

	public Wycieczka getWycieczka() {
		return wycieczka;
	}

	@Override
	public String toString() {
		return "KartaInformacyjna{" + "ograniczenia=" + ograniczenia + ", uwagi=" + uwagi + ", wycieczka=" + wycieczka
				+ '}';
	}
}
