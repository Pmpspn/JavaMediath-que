package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends Personne {

	private int idClient;
	private String mail;
	private String mdp;
	private ArrayList<Emprunter> articles;

	public Client() {
	}

	public Client(String nom, String prenom, String mail, String mdp) {
		super(nom, prenom);
		this.mail = mail;
		this.mdp = mdp;
		this.articles = new ArrayList<Emprunter>();
	}
	public Client(int unId,String nom, String prenom, int idClient,String mail, String mdp) {
		super(unId,nom, prenom);
		this.mail = mail;
		this.idClient=idClient;
		this.mdp = mdp;
		this.articles = new ArrayList<Emprunter>();
	}

	public void setArticles(ArrayList<Emprunter> articles) {
		this.articles = articles;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
}
