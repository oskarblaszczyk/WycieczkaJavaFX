package kurs.wycieczkajavafx.modules;

import java.io.IOException;

public class WycieczkaKrajowa extends Wycieczka {
	private final String miasto;

	public WycieczkaKrajowa(double cena, MotywWycieczki motyw, double ocena, String miasto) throws IOException {
		super(cena, motyw, ocena);
		this.miasto = miasto;
	}

	public String getMiasto() {
		return miasto;
	}
}
