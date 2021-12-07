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
import classes.Video;
import classes.Realisateur;
import classes.Client;
import classes.Auteur;
import main.Main;
import models.LivreTableModel;
import models.VideoTableModel;
import utils.PasswordGenerator;

public class VideoPanel extends JPanel implements ActionListener, MouseListener {

    JButton annuler;
    JButton valider;
    JButton supprimer;
    JTextField reference;
    JTextField designation;
    JTextField prix;
    JTextField duree;
    JComboBox<Realisateur> realisateurs;
    JFrame fenetre;

    VideoTableModel model;
    JTable tab;

    Video update;

    public VideoPanel(JFrame mafenetre) {
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

        JLabel pagesJ = new JLabel("Durée : ");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(pagesJ, c);
        duree = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(duree, c);

        JLabel auteurJ = new JLabel("Réalisateur :");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(auteurJ, c);
        realisateurs =new JComboBox<Realisateur>();
        for(Realisateur r : Main.realisateurs)
        {
            realisateurs.addItem(r);
        }
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(realisateurs, c);


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
        c.gridy = 5;
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
        model = new VideoTableModel();
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
                Video v = new Video(this.reference.getText(),this.designation.getText(),Double.parseDouble(prix.getText()),Integer.parseInt(duree.getText()));
                Realisateur r  = (Realisateur) realisateurs.getSelectedItem();
                v.setRealisateur(r);
                ArticleAdo.createVideo(v);
                Main.videos.add(v);
            } else {
                update.setDesignation(this.designation.getText());
                update.setReference(this.reference.getText());
                update.setPrix(Double.parseDouble(prix.getText()));
                update.setDuree(Integer.parseInt(duree.getText()));
                update.setRealisateur((Realisateur) realisateurs.getSelectedItem());
            }

            model = new VideoTableModel();
            tab.setModel(model);
            fenetre.revalidate();
            fenetre.repaint();
        }
        if (e.getSource() == annuler) {
            this.reference.setText("");
            this.designation.setText("");
            this.prix.setText("");
            this.duree.setText("");
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
                for (int i = 0; i < Main.videos.size(); i++) {
                    if (Boolean.parseBoolean(model.getValueAt(index, 5).toString()) == true) {
                        Main.videos.remove(i);
                        //i--;
                    }
                    index++;
                }
                model = new VideoTableModel();
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
            update = (Video) Main.videos.get(target.getSelectedRow());
            reference.setText(update.getReference());
            designation.setText(update.getDesignation());
            prix.setText(update.getPrix()+"");
            duree.setText(update.getDuree()+"");
            realisateurs.setSelectedItem(update.getRealisateur());

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
