package classes;

import bases.PersonneAdo;

public class Video extends Article {
	private int duree;
	private Realisateur realisateur;

	public Video(String uneRef, String uneDesi, Double unPrix, int uneDuree) {
		super(uneRef, uneDesi, unPrix);
		this.duree = uneDuree;
	}

	public void setIdVideo(int id) {
		this.id = id;
	}

	public Video(int unId, String uneRef, String uneDesi, Double unPrix, int uneDuree, int idVideo, int idRealisateur) {
		super(unId,uneRef, uneDesi, unPrix);
		this.duree = uneDuree;
		this.setIdVideo(idVideo);
		this.realisateur= PersonneAdo.getRealisateur(idRealisateur);
	}

	public int getIdVideo() {
		return id;
	}
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Realisateur getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}

	@Override
	public String toString() {
		return super.toString() + "Dur�e : " + this.duree;
	}

}
