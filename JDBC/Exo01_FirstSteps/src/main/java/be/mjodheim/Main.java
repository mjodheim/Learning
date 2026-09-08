package be.mjodheim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    static void main() {
        String connectionString = "jdbc:postgresql://localhost:5432/dbslide";

        // Connexion à dbslide avec l'user postgres et le mdp root
        try (Connection connection = DriverManager.getConnection(connectionString, "postgres", "root")) {
            System.out.println("Connected to the database"); // Connexion ok !

            // Récupérer la liste des étudiants de la table student
            PreparedStatement preparedStatement = connection.prepareStatement(
                "select first_name, last_name from student"
            );

            // On récupère le résultat de la requête dans rs
            ResultSet rs = preparedStatement.executeQuery();

            // Chaque rs.next() avance d'une ligne dans le résultat de la requête
            while (rs.next()) {
                // Pour chaque ligne, on affiche la valeur de chaque colonne
                System.out.println(
                        rs.getString("first_name") + " " + rs.getString("last_name")
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
