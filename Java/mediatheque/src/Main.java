import configs.Config;
import enums.*;
import models.*;
import services.Mediatheque;
import utils.ConsoleUtils;

void main() {
    Mediatheque mediatheque = new Mediatheque(Config.NOM_MEDIATHEQUE);
    chargerDonneesDemo(mediatheque);
    int choix = 0;

    do {
        afficherMenu();
        choix = ConsoleUtils.lireEntier("Votre choix : ");

        switch (choix) {
            case 1 -> ajouterMedia(mediatheque);
            case 2 -> listerCatalogue(mediatheque);
            case 3 -> rechercherMedia(mediatheque);
            case 4 -> modifierMedia(mediatheque);
            case 5 -> supprimerMedia(mediatheque);
            case 6 -> inscrireMembre(mediatheque);
            case 7 -> emprunter(mediatheque);
            case 8 -> rendre(mediatheque);
            case 9 -> afficherEmpruntsEnCours(mediatheque);
            case 10 -> afficherStatistiques(mediatheque);
            case 100 -> autoControle(mediatheque); // menu caché
            case 0 -> System.out.println("Merci, à bientôt !");
            default -> System.out.println("Choix invalide.");
        }
    } while (choix != 0);
}

private static void afficherMenu() {
    ConsoleUtils.afficherTitre("Médiathèque " + Config.NOM_MEDIATHEQUE);
    System.out.println("""
            1. Ajouter un média
            2. Lister le catalogue
            3. Rechercher un média
            4. Modifier un média
            5. Supprimer un média
            ----------------------------------------
            6. Inscrire un membre
            7. Emprunter
            8. Rendre
            9. Emprunts en cours
            ----------------------------------------
            10. Statistiques
            0. Quitter
            ========================================
            """);
}

private static void ajouterMedia(Mediatheque mediatheque) {

    System.out.println("""
            Quel type de média voulez-vous ajouter ?
            1. Livre
            2. DVD
            3. Jeu vidéo
            """);

    int type = ConsoleUtils.lireEntier("Votre choix : ");
    String titre = ConsoleUtils.lireTexte("Titre : ");
    int anneeSortie = ConsoleUtils.lireEntier("Année de sortie : ");
    Genre genre = ConsoleUtils.lireEnum("Genres disponibles :", Genre.class);

    Media media;

    switch (type) {

        case 1 -> {
            String auteur = ConsoleUtils.lireTexte("Auteur : ");
            int nbPages = ConsoleUtils.lireEntier("Nombre de pages : ");
            String isbn = ConsoleUtils.lireTexte("ISBN : ");

            media = new Livre(titre, anneeSortie, genre, auteur, nbPages, isbn);
        }

        case 2 -> {
            String realisateur = ConsoleUtils.lireTexte("Réalisateur : ");
            int duree = ConsoleUtils.lireEntier("Durée en minutes : ");

            media = new Dvd(titre, anneeSortie, genre, duree, realisateur);
        }

        case 3 -> {
            Plateforme plateforme = ConsoleUtils.lireEnum("Plateformes disponibles :", Plateforme.class);
            Pegi pegi = ConsoleUtils.lireEnum("PEGI disponibles :", Pegi.class);

            media = new JeuVideo(titre, anneeSortie, genre, plateforme,pegi);
        }

        default -> {
            System.out.println("Type de média invalide.");
            return;
        }
    }

    if (mediatheque.ajouterMedia(media)) {
        System.out.println("Média ajouté avec succès.");
    } else {
        System.out.println("Impossible d'ajouter le média.");
    }
}

private static void listerCatalogue(Mediatheque mediatheque) {
    ConsoleUtils.afficherTitre("catalogue");
    ConsoleUtils.afficherListe(mediatheque.listerTous());
}

private static void rechercherMedia(Mediatheque mediatheque) {
    System.out.println("""
            Rechercher par :
            1. Mot-clé
            2. Genre
            """);

    int choix = ConsoleUtils.lireEntier("Votre choix : ");

    List<Media> resultats;

    switch (choix) {

        case 1 -> {
            String motCle = ConsoleUtils.lireTexte("Mot-clé : ");
            resultats = mediatheque.rechercher(motCle);
        }

        case 2 -> {
            Genre genre = ConsoleUtils.lireEnum("Choisissez un genre :", Genre.class);
            resultats = mediatheque.rechercher(genre);
        }

        default -> {
            System.out.println("Choix invalide.");
            return;
        }
    }

    ConsoleUtils.afficherListe(resultats);
}

private static void modifierMedia(Mediatheque mediatheque) {
    int id = ConsoleUtils.lireEntier("ID du média à modifier : ");

    Media media = mediatheque.rechercherMediaParId(id);

    if (media == null) {
        System.out.println("Média introuvable.");
        return;
    }

    System.out.println("Média actuel :");
    System.out.println(media);

    String titre = ConsoleUtils.lireTexte("Nouveau titre : ");

    int annee = ConsoleUtils.lireEntier("Nouvelle année : ");

    Genre genre = ConsoleUtils.lireEnum("Nouveau genre :", Genre.class);

    if (mediatheque.modifierMedia(id, titre, annee, genre))
        System.out.println("Média modifié.");
    else
        System.out.println("Modification impossible.");
}

private static void supprimerMedia(Mediatheque mediatheque) {
    int id = ConsoleUtils.lireEntier("ID du média à supprimer : ");

    Media media = mediatheque.rechercherMediaParId(id);

    if (media == null) {
        System.out.println("Média introuvable.");
        return;
    }

    System.out.println(media);

    boolean confirmation = ConsoleUtils.lireOuiNon("Confirmer la suppression ?");

    if (!confirmation) {
        System.out.println("Suppression annulée.");
        return;
    }

    if (mediatheque.supprimerMedia(id))
        System.out.println("Média supprimé.");
    else
        System.out.println("Suppression impossible. Le média est peut-être emprunté.");
}

private static void inscrireMembre(Mediatheque mediatheque) {
    String nom = ConsoleUtils.lireTexte("Nom : ");
    String prenom = ConsoleUtils.lireTexte("Prénom : ");
    String email = ConsoleUtils.lireTexte("Email : ");

    Membre membre = new Membre(nom, prenom, email, LocalDate.now());

    if (mediatheque.inscrireMembre(membre))
        System.out.printf("Membre inscrit avec succès. ID : %d%n",membre.getId());
    else
        System.out.println("Impossible d'inscrire ce membre. Email déjà utilisé.");
}

private static void emprunter(Mediatheque mediatheque) {
    int idMedia = ConsoleUtils.lireEntier("ID du média : ");
    int idMembre = ConsoleUtils.lireEntier("ID du membre : ");

    if (mediatheque.emprunter(idMedia, idMembre)) {
        Media media = mediatheque.rechercherMediaParId(idMedia);
        System.out.printf("Emprunt enregistré : \"%s\".%n",media.getTitre());
    }
    else
        System.out.println("Emprunt impossible : média ou membre inexistant, " +
            "média indisponible ou limite d'emprunts atteinte.");
}

private static void rendre(Mediatheque mediatheque) {
    int idMedia = ConsoleUtils.lireEntier("ID du média : ");
    double retard = mediatheque.rendre(idMedia);

    if (retard < 0) {
        System.out.println("Impossible d'enregistrer le retour.");
        return;
    }

    System.out.printf("Retour enregistré. Retard : %.0f jour(s).%n", retard);
}

private static void afficherEmpruntsEnCours(Mediatheque mediatheque) {
    ConsoleUtils.afficherTitre("Emprunts en cours");
    ConsoleUtils.afficherListe(mediatheque.empruntsEnCours());
}

private static void afficherStatistiques(Mediatheque mediatheque) {
    mediatheque.afficherStatistiques();
}

private static void chargerDonneesDemo(Mediatheque mediatheque) {
    Livre livre1 = new Livre(
            "1984",
            1949,
            Genre.SCIENCE_FICTION,
            "George Orwell",
            328,
            "978-0451524935"
    );

    Livre livre2 = new Livre(
            "Le Hobbit",
            1937,
            Genre.AVENTURE,
            "J.R.R. Tolkien",
            310,
            "978-0261102217"
    );

    Dvd dvd1 = new Dvd(
            "Blade Runner",
            1982,
            Genre.SCIENCE_FICTION,
            117,
            "Ridley Scott"
    );

    JeuVideo jeu1 = new JeuVideo(
            "The Witcher 3",
            2015,
            Genre.AVENTURE,
            Plateforme.PC,
            Pegi.PEGI_18
    );

    mediatheque.ajouterMedias(
            livre1,
            livre2,
            dvd1,
            jeu1
    );

    Membre membre1 = new Membre(
            "Dupont",
            "Alice",
            "alice@exemple.be",
            LocalDate.now()
    );

    Membre membre2 = new Membre(
            "Martin",
            "Lucas",
            "lucas@exemple.be",
            LocalDate.now()
    );

    mediatheque.inscrireMembre(membre1);
    mediatheque.inscrireMembre(membre2);
}

private static void autoControle(Mediatheque mediatheque) {
    System.out.println("=== AUTO-CONTRÔLE ===");
    int tailleInitiale = mediatheque.listerTous().size();
    mediatheque.listerTous().clear();
    System.out.println("Catalogue protégé : " + (mediatheque.listerTous().size() == tailleInitiale));

    Media media = mediatheque.listerTous().getFirst();
    Set<Media> set = new HashSet<>();
    set.add(media);
    set.add(media);

    System.out.println("equals/hashCode correct : " + (set.size() == 1));
}