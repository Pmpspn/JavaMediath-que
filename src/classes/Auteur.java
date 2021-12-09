package classes;

import java.util.ArrayList;

public class Auteur extends Personne {
	private int idAuteur;
	private ArrayList<Livre> livres;

	public Auteur(String nom, String prenom) {
		super(nom, prenom);
		this.livres = new ArrayList<Livre>();
	}
	public Auteur(int unId,String nom, String prenom,int idAuteur) {
		super(unId,nom, prenom);
		this.idAuteur=idAuteur;
		this.livres = new ArrayList<Livre>();
	}

	public Auteur(){}

	public int getIdAuteur() {
		return idAuteur;
	}
}
