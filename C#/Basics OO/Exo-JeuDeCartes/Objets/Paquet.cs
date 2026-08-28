public class Paquet
{
    // On crée le paquet de cartes
    List<Carte> Cartes = new List<Carte>();

    public void Creer()
    {
        foreach (Couleur couleur in Enum.GetValues(typeof(Couleur)))
        {
            foreach (Valeur valeur in Enum.GetValues(typeof(Valeur)))
            {
                Cartes.Add(new Carte {
                    valeur = valeur,
                    couleur = couleur
                });
            }
        }
    }

    public void Melanger()
    {
        for (int i = 0; i < Cartes.Count; i++)
        {
            // Il faut fixer la position à échanger
            Random random = new Random();
            int j = random.Next(Cartes.Count);

            Carte temp = Cartes[i];
            Cartes[i] = Cartes[j];
            Cartes[j] = temp;
        }
    }

    public Carte? Tirer()
    {
        if (Cartes.Count == 0)
            return null;
        Carte? carte = Cartes[0];
        Cartes.RemoveAt(0);
        return carte;
    }
}