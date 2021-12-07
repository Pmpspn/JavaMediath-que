package vues;

import javax.swing.JFrame;

public class Accueil extends JFrame {

	public Accueil() {
		super("Médiathèque");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setJMenuBar(new Menu(this));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
