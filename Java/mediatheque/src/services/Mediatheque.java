package services;

import enums.Genre;
import models.Emprunt;
import models.Media;
import models.Membre;

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
