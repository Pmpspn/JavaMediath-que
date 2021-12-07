package bases;

import classes.Livre;
import classes.Video;

import java.sql.*;

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
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here : " + e);
        }
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
            query = "INSERT INTO `mediatheque`.`video` (`duree`, `id_article`, `id_realisateur`) VALUES (?,?,'1');";
            command = connection.prepareStatement(query);
            command.setInt(1, v.getDuree());
            command.setInt(2, id);
            command.executeUpdate();
        } catch (SQLException e) {
            System.out.println("here : " + e);
        }
    }
}
