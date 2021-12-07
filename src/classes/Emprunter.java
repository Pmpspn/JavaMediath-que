package classes;

import java.time.LocalDate;

public class Emprunter {

	private LocalDate dte;
	private Article a;

	public Emprunter() {
	}

	public Emprunter(LocalDate dte, Article a) {
		this.dte = dte;
		this.a = a;
	}

	public LocalDate getDte() {
		return dte;
	}

	public void setDte(LocalDate dte) {
		this.dte = dte;
	}

	public Article getA() {
		return a;
	}

	public void setA(Article a) {
		this.a = a;
	}

}
