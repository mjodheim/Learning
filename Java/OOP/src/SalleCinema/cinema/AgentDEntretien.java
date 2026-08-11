package SalleCinema.cinema;

public class AgentDEntretien {
    private final String nom;

    public AgentDEntretien(String nom) {
        this.nom = nom;
    }

    public void nettoyer(Salle salle) {
        // codeNettoyage est en default : accessible ici, car AgentDEntretien
        // est dans le même package que Salle.
        System.out.printf(
            "%s ouvre le boîtier de la salle %s avec le code %d et lance le nettoyage.%n",
            nom, salle.getNom(), salle.codeNettoyage
        );
    }
}
