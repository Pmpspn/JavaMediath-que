package bases;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import bases.Ado;
import classes.*;
import classes.Personne;
import utils.PasswordGenerator;

public abstract class PersonneAdo {

    public static void createClient(Client c) {
        Connection connection = Ado.init();
        try {
            String query = "INSERT INTO `mediatheque`.`personne` (`nom`, `prenom`) VALUES (?,?);";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, c.getNom());
            command.setString(2, c.getPrenom());
            String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                            StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            String hashed = HashPassword.hash(password);
            command.executeUpdate();
            query = "SELECT LAST_INSERT_ID();";
            Statement command2 = connection.createStatement();
            boolean resultat = command2.execute(query);
            int id = 0;
            if (resultat) {
                ResultSet rs = command2.getResultSet();
                rs.next();
                id = rs.getInt("LAST_INSERT_ID()");
                query = "INSERT INTO `mediatheque`.`emprunteur` (`id_personne`,`mail`,`mdp`) VALUES (?,?,?);";
                command = connection.prepareStatement(query);
                command.setInt(1, id);
                command.setString(2, c.getMail());
                command.setString(3, hashed);
                command.executeUpdate();
            }


        } catch (SQLException e) {
            System.out.println("Une erreur est survenue : " + e);
        }


    }

    public static ArrayList<Client> fetchAllClients() {
        Connection connection = Ado.init();
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            String query = "SELECT DISTINCT * FROM personne INNER JOIN emprunteur ON personne.id_personne = emprunteur.id_personne;";
            Statement command = connection.createStatement();
            boolean result = command.execute(query);
            if (result) {
                ResultSet rs = command.getResultSet();
                while (rs.next()) {
                    Client c = new Client(rs.getInt("id_personne"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_client"), rs.getString("mail"), rs.getString("mdp"));
                    clients.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
        return clients;


    }

    public static void updateClient(Client c) {
        Connection connection = Ado.init();
        try {
            String query = "UPDATE `mediatheque`.`personne` SET `nom`=?, `prenom`=? WHERE  `id_personne`=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, c.getNom());
            command.setString(2, c.getPrenom());
            command.setInt(3, c.getId());
            command.executeUpdate();
            query = "UPDATE `mediatheque`.`emprunteur` SET `mail`=?, `mdp`=? WHERE  `id_client`=?;";
            command = connection.prepareStatement(query);
            command.setString(1, c.getMail());
            command.setString(2, c.getMdp());
            command.setInt(3, c.getIdClient());
            command.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Une erreur est survenue " + e);
        }
    }

    public static void deleteClient(Client c) {
        try {
            Connection connection = Ado.init();
            String query = "DELETE FROM `mediatheque`.`emprunteur` WHERE  `id_client`=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, c.getIdClient());
            command.executeUpdate();
            query = "DELETE FROM `mediatheque`.`personne` WHERE  `id_personne`=?;";
            command = connection.prepareStatement(query);
            command.setInt(1, c.getId());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
    }

    public static void createRealisateur(Realisateur r) {
        Connection connection = Ado.init();
        try {
            String query = "INSERT INTO `mediatheque`.`personne` (`nom`, `prenom`) VALUES (?,?);";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, r.getNom());
            command.setString(2, r.getPrenom());
            command.executeUpdate();
            query = "SELECT LAST_INSERT_ID();";
            Statement command2 = connection.createStatement();
            Boolean resultat = command2.execute(query);
            int id = 0;
            if (resultat) {
                ResultSet rs = command2.getResultSet();
                rs.next();
                id = rs.getInt("LAST_INSERT_ID()");
                query = "INSERT INTO `mediatheque`.`realisteur` (`id_personne`) VALUES (?);";
                command = connection.prepareStatement(query);
                command.setInt(1, id);
                command.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue : " + e);
        }

    }

    public static ArrayList<Realisateur> fetchAllRealisateur() {
        Connection connection = Ado.init();
        ArrayList<Realisateur> realisateurs = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT * FROM personne INNER JOIN realisteur ON personne.id_personne = realisteur.id_personne;";
            Statement command = connection.createStatement();
            Boolean result = command.execute(query);
            if (result) {
                ResultSet rs = command.getResultSet();
                while (rs.next()) {
                    Realisateur r = new Realisateur(rs.getInt("id_personne"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_real"));
                    realisateurs.add(r);
                }
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
        return realisateurs;
    }

    public static void updateRealisateur(Realisateur r) {
        Connection connection = Ado.init();
        try {
            String query = "UPDATE `mediatheque`.`personne` SET `nom`=?, `prenom`=? WHERE  `id_personne`=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, r.getNom());
            command.setString(2, r.getPrenom());
            command.setInt(3, r.getId());
            command.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Une erreur est survenue " + e);
        }
    }

    public static void deleteRealisateur(Realisateur r) {
        try {
            Connection connection = Ado.init();
            String query = "DELETE FROM `mediatheque`.`realisteur` WHERE  `id_real`=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, r.getIdRealisateur());
            command.executeUpdate();
            query = "DELETE FROM `mediatheque`.`personne` WHERE  `id_personne`=?;";
            command = connection.prepareStatement(query);
            command.setInt(1, r.getId());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
    }

    public static Auteur getAuteur(int idAuteur) {
        Connection connection = Ado.init();
        Auteur a = new Auteur();
        try {
            String query = "SELECT * FROM auteur a INNER JOIN personne p ON a.id_personne = p.id_personne WHERE a.id_auteur=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, idAuteur);
            boolean result = command.execute();
            if (result) {
                ResultSet rs = command.getResultSet();
                rs.next();
                a = new Auteur(rs.getInt("id_personne"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_auteur"));
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
        return a;
    }

    public static Realisateur getRealisateur(int idRealisateur) {
        Connection connection = Ado.init();
        Realisateur r = new Realisateur();
        try {
            String query = "SELECT * FROM realisateur r INNER JOIN personne p ON r.id_personne = p.id_personne WHERE r.id_auteur=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, idRealisateur);
            boolean result = command.execute();
            if (result) {
                ResultSet rs = command.getResultSet();
                rs.next();
                r = new Realisateur(rs.getInt("id_personne"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_auteur"));
            }
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
        return r;
    }

}

