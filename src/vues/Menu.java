package vues;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar implements ActionListener {
	JMenu menu1, menu2;
	JMenuItem client, auteur, realisateur, livre, video;
	JFrame fenetre;

	public Menu(JFrame mafenetre) {
		super();
		this.fenetre = mafenetre;
		menu1 = new JMenu("Personne");
		menu2 = new JMenu("Article");
		// submenu = new JMenu("Sub Menu");
		client = new JMenuItem("Client");
		auteur = new JMenuItem("Auteur");
		realisateur = new JMenuItem("Réalisateur");
		livre = new JMenuItem("Livre");
		video = new JMenuItem("Vidéo");

		client.addActionListener(this);
		auteur.addActionListener(this);
		realisateur.addActionListener(this);
		livre.addActionListener(this);
		video.addActionListener(this);

		menu1.add(client);
		menu1.add(auteur);
		menu1.add(realisateur);
		menu2.add(livre);
		menu2.add(video);

		this.add(menu1);
		this.add(menu2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == client) {
			fenetre.getContentPane().removeAll();
			fenetre.getContentPane().add(new ClientPanel(fenetre), BorderLayout.CENTER);
			fenetre.revalidate();
			fenetre.repaint();
			
		}
		if (e.getSource() == auteur) {
			fenetre.getContentPane().removeAll();
			fenetre.getContentPane().add(new AuteurPanel(fenetre), BorderLayout.CENTER);
			fenetre.revalidate();
			fenetre.repaint();


		}
		if (e.getSource() == realisateur) {
			fenetre.getContentPane().removeAll();
			fenetre.getContentPane().add(new RealisateurPanel(fenetre), BorderLayout.CENTER);
			fenetre.revalidate();
			fenetre.repaint();

		}
		if (e.getSource() == livre) {
			fenetre.getContentPane().removeAll();
			fenetre.getContentPane().add(new LivrePanel(fenetre), BorderLayout.CENTER);
			fenetre.revalidate();
			fenetre.repaint();

		}
		if (e.getSource() == video) {
			fenetre.getContentPane().removeAll();
			fenetre.getContentPane().add(new VideoPanel(fenetre), BorderLayout.CENTER);
			fenetre.revalidate();
			fenetre.repaint();

		}

	}
}
