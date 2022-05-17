package kurs.wycieczkajavafx.modules;

public enum RodzajUmowy {
	UMOWA_O_PRACE(2500), UMOWA_ZLECENIE(1800), B2B(3100);

	double dodatekPensji;

	RodzajUmowy(double dodatekPensji) {
		this.dodatekPensji = dodatekPensji;
	}

}
