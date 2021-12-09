package main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import bases.*;
import classes.*;
import jdk.internal.org.objectweb.asm.tree.InsnList;
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
        livres = new ArrayList<Livre>();
        realisateurs = PersonneAdo.fetchAllRealisateur();
        clients = PersonneAdo.fetchAllClients();
        livres = ArticleAdo.fetchLivre();
        videos = ArticleAdo.fetchVideo();
        Accueil a = new Accueil();
    }
}
