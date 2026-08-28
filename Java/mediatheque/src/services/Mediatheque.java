package services;

import configs.Config;
import enums.Genre;
import models.*;

import java.util.*;

public class Mediatheque {
    private final String nom;

    private final Map<Integer, Media> catalogue = new HashMap<>();
    private final Map<Integer, Membre> membres = new HashMap<>();
    private final List<Emprunt> emprunts = new ArrayList<>();

    public Mediatheque(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom de la médiathèque ne peut pas être nul ou vide."
            );
        }
        this.nom = nom;
    }

    // Getters

    public String getNom() {
        return nom;
    }

    // CREATE

    public boolean ajouterMedia(Media media) {
        if (media == null) return false;
        if (catalogue.containsKey(media.getId())) {
            return false;
        }
        catalogue.put(media.getId(), media);
        return true;
    }

    public void ajouterMedias(Media... medias) {
        if (medias == null) return;
        for (Media media : medias) {
            ajouterMedia(media);
        }
    }

    public boolean inscrireMembre(Membre membre) {
        if (membre == null) return false;
        // Vérification de l'existence de l'email
        for (Membre membreExistant : membres.values()) {
            if (membreExistant.getEmail().equals(membre.getEmail())) {
                return false;
            }
        }
        membres.put(membre.getId(), membre);
        return true;
    }

    // READ

    public Media rechercherMediaParId(int id){
        return catalogue.get(id);
    }

    public List<Media> listerTous(){
        return new ArrayList<>(catalogue.values());
    }

    public List<Media> rechercher(String motCle){
        if(motCle == null || motCle.isBlank()) return listerTous();
        List<Media> mediasTrouves = new ArrayList<>();
        for( Media media : catalogue.values()){
            if(media.getTitre().toLowerCase().contains(motCle.toLowerCase())){
                mediasTrouves.add(media);
            }
        }
        return mediasTrouves;
    }

    public List<Media> rechercher(Genre genre){
        if(genre == null) return listerTous();
        List<Media> mediasTrouves = new ArrayList<>();
        for( Media media : catalogue.values()){
            if(media.getGenre() == genre){
                mediasTrouves.add(media);
            }
        }
        return mediasTrouves;
    }

    public List<Media> listerDisponibles(){
        List<Media> mediasTrouves = new ArrayList<>();
        for( Media media : listerTous()){
            if(media.isDisponible()) mediasTrouves.add(media);
        }
        return mediasTrouves;
    }

    public List<Media> listerParType(Class<?> type){
        if(type == null) return listerTous();
        List<Media> mediasTrouves = new ArrayList<>();
        for( Media media : catalogue.values()){
            if(type.isInstance(media)){
                mediasTrouves.add(media);
            }
        }
        return mediasTrouves;
    }

    // UPDATE

    public boolean modifierMedia(
            int id,
            String nouveauTitre,
            int nouvelleAnnee,
            Genre nouveauGenre
    ){
        Media media = rechercherMediaParId(id);
        if(media == null) return false;
        media.setTitre(nouveauTitre);
        media.setAnneeSortie(nouvelleAnnee);
        media.setGenre(nouveauGenre);
        return true;
    }

    // DELETE

    public boolean supprimerMedia(int id){
        Media media = rechercherMediaParId(id);
        if(media == null || !media.isDisponible()) return false;
        catalogue.remove(id);
        return true;
    }

    // Emprunter

    public boolean emprunter(int idMedia, int idMembre){
        Media media = rechercherMediaParId(idMedia);
        Membre membre = membres.get(idMembre);

        if(media == null || membre == null) return false;
        if(!media.isDisponible()) return false;

        // Les emprunts en cours pour ce membre
        int nbEmprunts = 0;
        for( Emprunt emprunt : emprunts){
            if(emprunt.getMembre().equals(membre) && emprunt.estEnCours()) nbEmprunts++;
        }

        if(nbEmprunts >= Config.MAX_EMPRUNTS_PAR_MEMBRE) return false;

        Emprunt emprunt = new Emprunt(media,membre);
        emprunts.add(emprunt);
        return true;

        /*
        * L'énoncé dit qu'il faut appeler media.marquerEmprunter()
        * Cette méthode est définie en package-private 'models'.
        * J'ai donc rajouté media.marquerEmprunt() dans le constructeur d'Emprunt.
        * */
    }

    // Rendre

    public double rendre(int idMedia){
        Media media = rechercherMediaParId(idMedia);
        if(media == null || media.isDisponible()) return -1;
        for( Emprunt emprunt : emprunts){
            if(emprunt.getMedia().equals(media) && emprunt.estEnCours()){
                return emprunt.cloturer();
            }
        }
        return -1;
    }

    // Consulter et statistiques

    public List<Emprunt> empruntsEnCours(){
        List<Emprunt> emprunts = new ArrayList<>();
        for( Emprunt emprunt : this.emprunts){
            if(emprunt.estEnCours()){
                emprunts.add(emprunt);
            }
        }
        return emprunts;
    }

    public List<Emprunt> empruntsEnCours(Membre membre){
        if(membre == null) return empruntsEnCours();
        List<Emprunt> emprunts = new ArrayList<>();
        for( Emprunt emprunt : this.emprunts){
            if(emprunt.getMembre().equals(membre) && emprunt.estEnCours()){
                emprunts.add(emprunt);
            }
        }
        return emprunts;
    }

    public List<Emprunt> listerRetards(){
        List<Emprunt> emprunts = new ArrayList<>();
        for( Emprunt emprunt : this.emprunts){
            if(emprunt.estEnRetard()) emprunts.add(emprunt);
        }
        return emprunts;
    }

    public void afficherStatistiques(){
        long disponibles = catalogue.values().stream().filter(Media::isDisponible).count();
        int livres = 0, dvds = 0, jeux = 0;
        Map<Genre, Integer> genres = new HashMap<>();

        for (Media media : catalogue.values()) {
            if (media instanceof Livre) {
                livres++;
            } else if (media instanceof Dvd) {
                dvds++;
            } else if (media instanceof JeuVideo) {
                jeux++;
            }
            Genre genre = media.getGenre();
            /*
            * Ici, on récupère la value de la key genre
            * Si on ne l'a pas, on l'initialise à 0
            * On incrémente ensuite de 1.
            */
            genres.put(genre, genres.getOrDefault(genre, 0) + 1);
        }
        // Récupération du genre le plus représenté (avec un stream)
        Map.Entry<Genre, Integer> maxGenre = genres.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        // Cas du null à prendre en compte
        String nomMaxGenre = maxGenre != null ? maxGenre.getKey().toString() : "";
        int nombreMaxGenre = maxGenre != null ? maxGenre.getValue() : 0;
        // Affichage du texte formaté
        System.out.printf(
                "=== Statistiques - %s ===%n" +
                "Médias au catalogue : %d (%d livres, %d DVD, %d jeux vidéo)%n" +
                "Disponibles : %d%n" +
                "Empruntés : %d%n" +
                "Membres inscrits : %d%n" +
                "Genre le plus représenté : %s (%d médias)%n",
                nom,
                catalogue.size(), livres, dvds, jeux,
                disponibles,
                catalogue.size() - disponibles,
                membres.size(),
                nomMaxGenre, nombreMaxGenre
        );
    }

    // Overrides

    @Override
    public String toString() {
        return "Mediatheque{" +
                "nom='" + nom + '\'' +
                ", catalogue=" + catalogue +
                ", membres=" + membres +
                ", emprunts=" + emprunts +
                '}';
    }
}
