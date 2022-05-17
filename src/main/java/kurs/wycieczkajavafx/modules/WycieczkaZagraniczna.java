package kurs.wycieczkajavafx.modules;

import java.io.IOException;

public class WycieczkaZagraniczna extends Wycieczka {
	private final String kraj;
	private final SrodekTransportu srodekTrasportu; //todo enum -> zrobione

	public WycieczkaZagraniczna(double cena, MotywWycieczki motyw, double ocena, String kraj,
			SrodekTransportu srodekTrasportu) throws IOException {
		super(cena, motyw, ocena);
		this.kraj = kraj;
		this.srodekTrasportu = srodekTrasportu;
	}

	public String getKraj() {
		return kraj;
	}

	public SrodekTransportu getSrodekTrasportu() {
		return srodekTrasportu;
	}
}
