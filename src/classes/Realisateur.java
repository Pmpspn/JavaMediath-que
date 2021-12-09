package classes;

import java.util.ArrayList;

public class Realisateur extends Personne {

	private ArrayList<Video> videos;
	private int idRealisateur;

	public Realisateur(String nom, String prenom) {
		super(nom, prenom);
		this.videos = new ArrayList<Video>();
	}

	public Realisateur(int unId,String nom, String prenom, int id_real) {
		super(unId,nom, prenom);
		this.idRealisateur=id_real;
		this.videos = new ArrayList<Video>();
	}

	public Realisateur(){}

	public int getIdRealisateur() {
		return idRealisateur;
	}

}
