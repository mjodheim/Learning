public class Personne
{
    public string Nom { get; }
    public string Prenom { get; }
    public int Resultat { get; private set; }

    public Personne(string nom, string prenom)
    {
        Nom = nom;
        Prenom = prenom;
    }

    public Personne(string prenom)
    {
        Prenom = prenom;
    }

    public Personne() { }

    public void AjouterPoints()
    {
        Resultat ++;
    }
}