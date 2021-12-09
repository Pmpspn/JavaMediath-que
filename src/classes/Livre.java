package classes;

import bases.PersonneAdo;

public class Livre extends Article {

	private int id;
	private String isbn;
	private int nbPages;
	private Auteur auteur;

	public Livre(String uneRef, String uneDesi, Double unPrix, String unIsbn, int unNbPages) {
		super(uneRef, uneDesi, unPrix);
		this.isbn = unIsbn;
		this.nbPages = unNbPages;
	}

	public Livre(int unId, String uneRef, String uneDesi, Double unPrix,int id_livre, String unIsbn, int unNbPages) {
		super(unId, uneRef, uneDesi, unPrix);
		this.isbn = unIsbn;
		this.nbPages = unNbPages;
		this.id=id_livre;
	}

	public void setIdLivre(int id) {
		this.id = id;
	}

	public Livre(int unId, String uneRef, String uneDesi, Double unPrix, String unIsbn, int unNbPages, int idLivre, int idAuteur) {
		super(unId,uneRef, uneDesi, unPrix);
		this.isbn = unIsbn;
		this.nbPages = unNbPages;
		this.setIdLivre(idLivre);
		this.auteur= PersonneAdo.getAuteur(idAuteur);
	}

    public int getIdLivre() {
		return id;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	@Override
	public String toString() {
		return super.toString() + "Nombre de pages : " + this.nbPages;
	}
}
