package MiniApplications;

import java.util.Scanner;

public class MoyenneDesScores {
    public static void run(Scanner sc){
        int nbJoueurs;
        float moyenne = 0;
        float[] tabScores = new float [10];
        String saisie;
        boolean saisieValide;

        // Récupération du nombre de joueurs
        do{
            IO.print("Nombre de joueurs (max 10): ");
            nbJoueurs = sc.nextInt();
        } while (nbJoueurs < 1 || nbJoueurs > 10);

        // Récupération des moyennes de chaque joueur
        for (int i=0; i< nbJoueurs; i++){
            do {
                saisieValide = false;
                System.out.printf("Score du joueur %d\n", i+1);
                try {
                    saisie = sc.next();
                    tabScores[i] = Float.parseFloat(saisie.trim().replace(',','.')); // pour clavier belge
                    moyenne += tabScores[i];
                    saisieValide = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Erreur d'encodage.");
                }
            } while (!saisieValide);
        }

        // Calcul et affichage de la moyenne des joueurs
        moyenne /= tabScores.length;
        IO.print("Moyenne des scores: " + moyenne);
    }
}
