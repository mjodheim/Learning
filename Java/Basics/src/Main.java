import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // On ne crée qu'un seul scanner qu'on passe potentiellement aux autres sous-programmes
        // Ne pas oublier de le fermer à la fin
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.print("""
            === Choix de l'exercice à lancer ===
            1. Table de multiplication
            2. Distributeur de boissons
            3. Système de connexion
            4. Nombre de lignes affichées en *
            5. Plus ou moins ?
            6. Calcul de puissance (sur des entiers)
            7. Tableau de 10 nombres en puissance 2
            8. Moyenne des scores (max 10 joueurs)
            0. Quitter
            """);
            choix = scanner.nextInt();

            switch (choix) {
                case 1: TableMultiplication.run(scanner);
                    break;
                case 2: DistributeurDeBoissons.run(scanner);
                    break;
                case 3: SystemeDeConnexion.run(scanner);
                    break;
                case 4: NombreDeLignes.run(scanner);
                    break;
                case 5: PlusOuMoins.run(scanner);
                    break;
                case 6: Exposant.run(scanner);
                    break;
                case 7: TableauDixEntiers.run(scanner);
                    break;
                case 8: MoyenneDesScores.run(scanner);
                    break;
                case 0: System.out.println("Merci, à bientôt !");
            }
        } while (choix != 0);

        // On ferme le scanner
        scanner.close();
    }
}