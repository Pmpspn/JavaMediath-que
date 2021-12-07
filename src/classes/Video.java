package classes;

public class Video extends Article {
	private int duree;
	private Realisateur realisateur;

	public Video(String uneRef, String uneDesi, Double unPrix, int uneDuree) {
		super(uneRef, uneDesi, unPrix);
		this.duree = uneDuree;
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
		return super.toString() + "Durée : " + this.duree;
	}

}
