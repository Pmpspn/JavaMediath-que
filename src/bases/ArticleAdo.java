package bases;

import classes.Livre;
import classes.Video;

import java.sql.*;
import java.util.ArrayList;

public class ArticleAdo {

    public static void createLivre(Livre l) {
        Connection connection = Ado.init();
        try {
            String query = "INSERT INTO `mediatheque`.`article` (`reference`, `designation`,`prix`) VALUES (?,?,?);";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, l.getReference());
            command.setString(2, l.getDesignation());
            command.setDouble(3, l.getPrix());
            command.executeUpdate();
            query = "SELECT LAST_INSERT_ID();";
            Statement command2 = connection.createStatement();
            boolean result = command2.execute(query);
            int id = 0;
            if (result) {
                ResultSet res = command2.getResultSet();
                res.next();
                id = res.getInt("LAST_INSERT_ID()");
            }
            query = "INSERT INTO `mediatheque`.`livre` (`isbn`, `nb_pages`,`id_article`, `id_auteur`) VALUES (?,?,?,1);";
            command = connection.prepareStatement(query);
            command.setString(1, l.getIsbn());
            command.setInt(2, l.getNbPages());
            command.setInt(3, id);
            command.setInt(4, l.getAuteur().getIdAuteur());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here : " + e);
        }
    }

    public static ArrayList<Livre> fetchLivre() {
        ArrayList<Livre> livres = new ArrayList<Livre>();
        Connection connection = Ado.init();
        try {
            String query = "SELECT * from livre l INNER JOIN article a ON l.id_article=a.id_article;";
            Statement command = connection.createStatement();
            boolean result = command.execute(query);
            if (result) {
                ResultSet rs = command.getResultSet();
                while (rs.next()) {
                    Livre l = new Livre(rs.getInt("id_article"), rs.getString("reference"), rs.getString("designation"), rs.getDouble("prix"), rs.getString("isbn"), rs.getInt("nb_pages"), rs.getInt("id_livre"), rs.getInt("id_auteur"));
                    livres.add(l);
                }
            }

        } catch (SQLException e) {
            System.out.println("here " + e);
        }
        return livres;
    }

    public static void deleteLivre(Livre l) {
        Connection connection = Ado.init();
        try {
            String query = "DELETE FROM livre WHERE id_livre=?";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, l.getIdLivre());
            command.executeUpdate();
            query = "DELETE FROM article WHERE id_article=?";
            command = connection.prepareStatement(query);
            command.setInt(1, l.getIdArticle());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here " + e);
        }
    }

    public static void updateLivre(Livre l) {
        Connection connection = Ado.init();
        try {
            String query = "UPDATE `mediatheque`.`livre` SET `isbn`=?, `nb_pages`=?, `id_article`=?, `id_auteur`=? WHERE  `id_livre`=?;";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, l.getIsbn());
            command.setInt(2, l.getNbPages());
            command.setInt(3, l.getIdArticle());
            command.setInt(4, l.getAuteur().getIdAuteur());
            command.setInt(5, l.getIdLivre());
            command.executeUpdate();
            query = "UPDATE `mediatheque`.`article` SET `reference`=?, `designation`=?, `prix`=? WHERE  `id_article`=?;";
            command = connection.prepareStatement(query);
            command.setString(1, l.getReference());
            command.setString(2, l.getDesignation());
            command.setDouble(3, l.getPrix());
            command.setInt(4, l.getIdArticle());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une erreur est survenue " + e);
        }
    }

    public static void createVideo(Video v) {
        Connection connection = Ado.init();
        try {
            String query = "INSERT INTO `mediatheque`.`article` (`reference`, `designation`,`prix`) VALUES (?,?,?);";
            PreparedStatement command = connection.prepareStatement(query);
            command.setString(1, v.getReference());
            command.setString(2, v.getDesignation());
            command.setDouble(3, v.getPrix());
            command.executeUpdate();
            query = "SELECT LAST_INSERT_ID();";
            Statement command2 = connection.createStatement();
            boolean result = command2.execute(query);
            int id = 0;
            if (result) {
                ResultSet res = command2.getResultSet();
                res.next();
                id = res.getInt("LAST_INSERT_ID()");
            }
            query = "INSERT INTO `mediatheque`.`video` (`duree`, `id_article`, `id_realisateur`) VALUES (?,?,4);";
            command = connection.prepareStatement(query);
            command.setInt(1, v.getDuree());
            command.setInt(2, id);
            //command.setInt(3, v.getRealisateur().getIdRealisateur());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here : " + e);
        }
    }

    public static ArrayList<Video> fetchVideo() {
        ArrayList<Video> video = new ArrayList<Video>();
        Connection connection = Ado.init();
        try {
            String query = "SELECT * from video v INNER JOIN article a ON v.id_article=a.id_article;";
            Statement command = connection.createStatement();
            boolean result = command.execute(query);
            if (result) {
                ResultSet rs = command.getResultSet();
                while (rs.next()) {
                    Video v = new Video(rs.getInt("id_article"), rs.getString("reference"), rs.getString("designation"), rs.getDouble("prix"), rs.getInt("duree"), rs.getInt("id_video"), rs.getInt("id_realisateur"));
                    video.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("here " + e);
        }
        return video;
    }

    public static void deleteVideo(Video v) {
        Connection connection = Ado.init();
        try {
            String query = "DELETE FROM video WHERE id_video=?";
            PreparedStatement command = connection.prepareStatement(query);
            command.setInt(1, v.getIdVideo());
            command.executeUpdate();
            query = "DELETE FROM article WHERE id_article=?";
            command = connection.prepareStatement(query);
            command.setInt(1, v.getIdArticle());
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here " + e);
        }
    }

}