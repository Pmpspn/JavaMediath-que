package vues;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bases.PersonneAdo;
import classes.Client;
import main.Main;
import models.ClientTableModel;
import utils.PasswordGenerator;

public class ClientPanel extends JPanel implements ActionListener, MouseListener {

	JButton annuler;
	JButton valider;
	JButton supprimer;
	JTextField nomT;
	JTextField prenomT;
	JTextField mailT;
	JFrame fenetre;

	ClientTableModel model;
	JTable tab;

	Client update;

	public ClientPanel(JFrame mafenetre) {
		super();
		fenetre = mafenetre;
		//this.setLayout(new GridLayout(0, 2, 100, 100));
		this.add(FormulairePanel());
		this.add(ListerPanel());
	}

	public JPanel FormulairePanel() {
		JPanel me = new JPanel();
		me.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;

		JLabel nom = new JLabel("Nom :");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 100, 10, 0);
		me.add(nom, c);
		nomT = new JTextField();
		c.gridx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		me.add(nomT, c);

		JLabel prenom = new JLabel("Prénom :");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10, 100, 10, 0);
		me.add(prenom, c);
		prenomT = new JTextField();
		c.gridx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		me.add(prenomT, c);

		JLabel mail = new JLabel("Mail :");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 100, 10, 0);
		me.add(mail, c);
		mailT = new JTextField();
		c.gridx = 1;
		c.insets = new Insets(10, 10, 10, 10);
		me.add(mailT, c);

		JPanel btn = new JPanel();
		btn.setLayout(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		annuler = new JButton("Annuler");
		annuler.addActionListener(this);
		btn.add(annuler, c);
		c.gridx = 1;
		c.gridy = 0;
		valider = new JButton("Valider");
		valider.addActionListener(this);
		btn.add(valider, c);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 200, 10, 0);
		me.add(btn, c);
		return me;

	}

	public JPanel ListerPanel() {
		JPanel me = new JPanel();
		me.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.insets = new Insets(10, 100, 10, 100);
		c.gridy = 0;
		tab = new JTable();
		tab.setAutoCreateRowSorter(true);
		model = new ClientTableModel();
		tab.setModel(model);
		tab.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(tab);
		me.add(scrollPane, c);
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(this);
		c.gridy = 1;
		c.weightx = 0.5;
		c.insets = new Insets(10, 700, 10, 100);
		me.add(supprimer, c);
		return me;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valider) {
			if (update == null) {

				Client c = new Client(nomT.getText(), prenomT.getText(), mailT.getText(),
						PasswordGenerator.generateStrongPassword());
				Main.clients.add(c);
				PersonneAdo.createClient(c);


			} else {
				update.setNom(nomT.getText());
				update.setPrenom(prenomT.getText());
				update.setMail(mailT.getText());
				PersonneAdo.updateClient(update);

			}

			model = new ClientTableModel();
			tab.setModel(model);
			fenetre.revalidate();
			fenetre.repaint();
		}
		if (e.getSource() == annuler) {
			nomT.setText("");
			prenomT.setText("");
			mailT.setText("");
			update = null;
		}
		if (e.getSource() == supprimer) {
			String text = "Voulez-vous vraiment supprimer les sélections ?";
			String title = "Confirmation";
			int optionType = JOptionPane.YES_NO_OPTION;
			int result = JOptionPane.showConfirmDialog(null, text, title, optionType);
			if (result == JOptionPane.OK_OPTION) {
				int index = 0;
				for (int i = 0; i < Main.clients.size(); i++) {
					if (Boolean.parseBoolean(model.getValueAt(index, 3).toString()) == true) {
						Client c = Main.clients.get(i);
						PersonneAdo.deleteClient(c);
						Main.clients.remove(i);

						i--;
					}
					index++;
				}
				model = new ClientTableModel();
				tab.setModel(model);
				fenetre.revalidate();
				fenetre.repaint();
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // to detect doble click events
			JTable target = (JTable) e.getSource();
			update = (Client) Main.clients.get(target.getSelectedRow());
			nomT.setText(update.getNom());
			prenomT.setText(update.getPrenom());
			mailT.setText(update.getMail());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
