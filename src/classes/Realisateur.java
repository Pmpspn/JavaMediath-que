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

	public ArrayList<Video> getVideos() {
		return videos;
	}

	public void setVideos(ArrayList<Video> videos) {
		this.videos = videos;
	}

	public int getIdRealisateur() {
		return idRealisateur;
	}

	public void setIdRealisateur(int idRealisateur) {
		this.idRealisateur = idRealisateur;
	}

	public void AjouterVideo(Video v) {
		this.videos.add(v);
		v.setRealisateur(this);
	}

}
