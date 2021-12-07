package classes;

public abstract class Article {
	// attributs
	protected int id;
	protected String reference;
	protected String designation;
	protected Double prix;

	// constructeur par défaut
	public Article() {
	}

	// constructeur d'initialisation
	public Article(String uneRef, String uneDesi, Double unPrix) {
		this.reference = uneRef;
		this.designation = uneDesi;
		this.prix = unPrix;
	}

	public Article(int unId, String uneRef, String uneDesi, Double unPrix) {
		this.id=unId;
		this.reference = uneRef;
		this.designation = uneDesi;
		this.prix = unPrix;
	}

	// accesseurs
	// getters
	public String getReference() {
		return this.reference;
	}

	public String getDesignation() {
		return this.designation;
	}

	public Double getPrix() {
		return this.prix;
	}

	// setters
	public void setReference(String uneRef) {
		this.reference = uneRef;
	}

	public void setDesignation(String uneDesi) {
		this.designation = uneDesi;
	}

	public void setPrix(Double unPrix) {
		this.prix = unPrix;
	}

	// surcharge
	@Override
	public String toString() {
		return "Désignation : " + this.designation + "\nPrix : " + this.prix + " €";
	}

}
