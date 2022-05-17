package kurs.wycieczkajavafx.modules;

import java.time.LocalDate;

public class ObslugaKlienta extends Ekstensja {
	private final LocalDate data;
	private final PracownikObslugiKlienta pracownikObslugiKlienta;
	private final KlientZarejestrowany klient;

	public ObslugaKlienta(LocalDate data, PracownikObslugiKlienta pracownikObslugiKlienta,
			KlientZarejestrowany klient) {
		if (pracownikObslugiKlienta == null || klient == null) {
			throw new IllegalArgumentException("Nie moze byc nullem");
		}
		this.data = data;
		this.pracownikObslugiKlienta = pracownikObslugiKlienta;
		this.klient = klient;
		pracownikObslugiKlienta.getObslugaKlientow().add(this);
		klient.getObslugaKlientow().add(this);
	}

	public LocalDate getData() {
		return data;
	}

	public PracownikObslugiKlienta getPracownikObslugiKlienta() {
		return pracownikObslugiKlienta;
	}

	public KlientZarejestrowany getKlient() {
		return klient;
	}

}
