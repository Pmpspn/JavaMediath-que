package vues;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import bases.ArticleAdo;
import classes.Livre;
import classes.Realisateur;
import classes.Client;
import classes.Auteur;
import main.Main;
import models.LivreTableModel;
import utils.PasswordGenerator;

public class LivrePanel extends JPanel implements ActionListener, MouseListener {

    JButton annuler;
    JButton valider;
    JButton supprimer;
    JTextField reference;
    JTextField designation;
    JTextField prix;
    JTextField isbn;
    JTextField pages;
    JComboBox<Auteur> auteurs;
    JFrame fenetre;

    LivreTableModel model;
    JTable tab;

    Livre update;

    public LivrePanel(JFrame mafenetre) {
        super();
        fenetre = mafenetre;
        //this.setLayout(new GridLayout(0, 2, 0, 0));
        this.add(FormulairePanel());
        this.add(ListerPanel());
    }

    public JPanel FormulairePanel() {

        JPanel me = new JPanel();
        me.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        JLabel referenceJ = new JLabel("Référence :");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(referenceJ, c);
        this.reference = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(reference, c);

        JLabel designationJ = new JLabel("Désignation :");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(designationJ, c);
        designation = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(designation, c);

        JLabel prixJ = new JLabel("Prix :");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(prixJ, c);
        prix = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(prix, c);

        JLabel isbnJ = new JLabel("ISBN :");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(isbnJ, c);
        isbn = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(isbn, c);

        JLabel pagesJ = new JLabel("Nombre de pages :");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(pagesJ, c);
        pages = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(pages, c);

        JLabel auteurJ = new JLabel("Auteur :");
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(auteurJ, c);
        auteurs =new JComboBox<Auteur>();
        for(Auteur a : Main.auteurs)
        {
            auteurs.addItem(a);
        }
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(auteurs, c);


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
        c.gridy = 6;
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
        model = new LivreTableModel();
        tab.setModel(model);
        tab.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(tab);
        me.add(scrollPane, c);
        supprimer = new JButton("Supprimer");
        supprimer.addActionListener(this);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10, 500, 10, 100);
        me.add(supprimer, c);
        return me;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            if (update == null) {
                Livre l = new Livre(this.reference.getText(),this.designation.getText(),Double.parseDouble(prix.getText()),this.isbn.getText(),Integer.parseInt(this.pages.getText()));
                Auteur a  = (Auteur)auteurs.getSelectedItem();
                l.setAuteur(a);
                ArticleAdo.createLivre(l);
                Main.livres.add(l);
            } else {
                update.setDesignation(this.designation.getText());
                update.setReference(this.reference.getText());
                update.setPrix(Double.parseDouble(prix.getText()));
                update.setIsbn(this.isbn.getText());
                update.setNbPages(Integer.parseInt(this.pages.getText()));
            }

            model = new LivreTableModel();
            tab.setModel(model);
            fenetre.revalidate();
            fenetre.repaint();
        }
        if (e.getSource() == annuler) {
            this.reference.setText("");
            this.designation.setText("");
            this.prix.setText("");
            this.isbn.setText("");
            this.pages.setText("");
            update = null;
        }
        if (e.getSource() == supprimer) {
            String text = "Voulez-vous vraiment supprimer les sélections ?";
            String title = "Confirmation";
            int optionType = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, text, title, optionType);
            if (result == JOptionPane.OK_OPTION) {
                int index = 0;
                boolean ok =true;
                for (int i = 0; i < Main.livres.size(); i++) {
                    if (Boolean.parseBoolean(model.getValueAt(index, 6).toString()) == true) {
                        Main.livres .remove(i);
                        //i--;
                    }
                    index++;
                }
                model = new LivreTableModel();
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
            update = (Livre) Main.livres.get(target.getSelectedRow());
            reference.setText(update.getReference());
            designation.setText(update.getDesignation());
            prix.setText(update.getPrix()+"");
            isbn.setText(update.getIsbn());
            pages.setText(update.getNbPages()+"");

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
