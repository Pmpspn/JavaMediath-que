package main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import bases.*;
import classes.*;
import utils.AddressGenerator;
import utils.PasswordGenerator;
import vues.Accueil;

public class Main {

	public static ArrayList<Client> clients;
	public static ArrayList<Auteur> auteurs;
	public static ArrayList<Realisateur> realisateurs;
	public static ArrayList<Livre> livres;
	public static ArrayList<Video> videos;
	public static void main(String[] args) {
		videos = new ArrayList<Video>();
		clients = new ArrayList<Client>();
		auteurs = new ArrayList<Auteur>();
		realisateurs = PersonneAdo.fetchAllRealisateur();
		livres = new ArrayList<Livre>();
		clients = PersonneAdo.fetchAllClients();
		Auteur au = new Auteur("Michel", "Pedro");
		auteurs.add(au);
		Realisateur r = new Realisateur("Stallone","Sylvester") ;
		realisateurs.add(r);
		Livre l = new Livre("EFRT45H","Le sorceleur Tome 1",13.99,"ISEE",421);
		l.setAuteur(au);
		au.AjouterLivre(l);
		livres.add(l);
		Video v  = new Video("EFTG45","L'empire contre-attaque",13.99,125);
		v.setRealisateur(r);
		r.AjouterVideo(v);
		videos.add(v);
		Accueil a = new Accueil();

	}

	public static void ExoObjet() {
		/*
		 * Article a = new Article(); a.setDesignation("PC hp"); a.setPrix(599.99);
		 * a.setReference("A545E");
		 */

		// System.out.println(a.toString());

		Video d = new Video("les dents de la mer", "JL45M", 15.99, 120);
		System.out.println(d.toString());

		Livre l = new Livre("le petit prince", "ak47", 1.00, "11111111", 127);
		System.out.println(l.toString());
	}

	public static void Exercice1() {
		int cptOut = 0;
		int cptGmail = 0;
		int cptGroupe = 0;
		String[] list = AddressGenerator.GenerateAddress(10);
		for (int i = 0; i < list.length; i++) {
			String[] slp = list[i].split("@");
			if (slp[1].equals("outlook.fr")) {
				cptOut++;
			} else if (slp[1].equals("gmail.com")) {
				cptGmail++;
			} else {
				cptGroupe++;
			}
		}

		System.out.println("Part de march� Outlook :" + cptOut * 10 + "%");
		System.out.println("Part de march� Gmail :" + cptGmail * 10 + "%");
		System.out.println("Part de march� GroupeMontRoland :" + cptGroupe * 10 + "%");
	}

	public static void Exercice2() {
		int nb1 = new Random().nextInt(1000);
		int nb2 = new Random().nextInt(1000);
		int nb3 = new Random().nextInt(1000);
		int cpt = 0;
		while (nb1 % 2 == 1) {
			nb1 = new Random().nextInt(1000);
			cpt++;
		}
		while (nb2 % 2 == 1) {
			nb2 = new Random().nextInt(1000);
			cpt++;
		}
		while (nb3 % 2 == 0) {
			nb3 = new Random().nextInt(1000);
			cpt++;
		}
		System.out.println("Nombres g�n�r�s: " + nb1 + " " + nb2 + " " + nb3);
		System.out.println("Nombre d'essais : " + cpt);

	}

	public static void Exercice3() {
		int find = new Random().nextInt(1000);
		int guess = 0;
		int cpt = 0;
		LocalDateTime start = LocalDateTime.now();
		System.out.println("Saisissez un nombre entre 0 et 1000 :");
		while (guess != find) {
			cpt++;

			guess = Integer.parseInt(new Scanner(System.in).nextLine());
			if (guess > find) {
				System.out.println("Vous �tes au dessus du nombre cherch�");
			}
			if (guess < find) {
				System.out.println("Vous �tes en dessous du nombre cherch�");
			}
		}
		LocalDateTime end = LocalDateTime.now();
		System.out.println("Bien jou� ! Le chiffre �tait : " + find);
		System.out.println(
				"Vous l'avez trouv� en " + cpt + " fois et en " + (start.until(end, ChronoUnit.SECONDS) + " secondes"));
	}
}
