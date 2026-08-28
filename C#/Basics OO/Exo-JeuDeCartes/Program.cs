using System.Threading;

Console.WriteLine("===== Jeu de cartes =====");

#region Création des Paquets et des joueurs
Paquet PaquetJoueur = new Paquet();
Paquet PaquetOrdinateur = new Paquet();

PaquetJoueur.Creer();
PaquetJoueur.Melanger();

PaquetOrdinateur.Creer();
PaquetOrdinateur.Melanger();

Console.Write("Indiquez votre prénom : ");
string prenom = Console.ReadLine();
Personne Joueur = new Personne(prenom == "" ? "Kévin" : prenom);

Personne Ordinateur = new Personne("Jarvis");

Carte? carteJoueur = new Carte();
Carte? carteOrdinateur = new Carte();
#endregion

Console.Clear();
Console.WriteLine($"===== Bienvenue {Joueur.Prenom}, vous jouez contre {Ordinateur.Prenom} =====");

TirageDesCartes();

string messageVictoire = Victoire(Joueur,Ordinateur);

Console.Clear();
Console.WriteLine($"""
        ===== La partie est terminée =====
        +++++   Tableau des scores   +++++

        {Joueur.Prenom} : {Joueur.Resultat}
        {Ordinateur.Prenom} : {Ordinateur.Resultat}
        {messageVictoire}
        """);

Thread.Sleep(10000);

#region Functions
string Victoire(Personne joueur, Personne ordinateur)
{
    return joueur.Resultat.CompareTo(ordinateur.Resultat) switch
    {
        -1 => $"{ordinateur.Prenom} vous a battu. LOOSER !!!",
        1 => $"Bien joué {joueur.Prenom}, vous avez battu {ordinateur.Prenom} !",
        _ => "Incroyable, c'est une égalité parfaite !"
    };
}
void TirageDesCartes()
{
    do
    {
        Console.WriteLine("Appuyez sur une touche pour tirer votre carte");
        Console.ReadKey();

        carteJoueur = PaquetJoueur.Tirer();
        carteOrdinateur = PaquetOrdinateur.Tirer();

        Console.WriteLine($"""
        Vous : {carteJoueur?.valeur} de {carteJoueur?.couleur}
        {Ordinateur.Prenom} : {carteOrdinateur?.valeur} de {carteOrdinateur?.couleur}
        """);

        // Gestion des résultats

        if (carteJoueur?.valeur < carteOrdinateur?.valeur)
        {
            Console.WriteLine($"{Ordinateur.Prenom} l'emporte.");
            Ordinateur.AjouterPoints();
        }
        if (carteJoueur?.valeur > carteOrdinateur?.valeur)
        {
            Console.WriteLine("Vous l'emportez.");
            Joueur.AjouterPoints();
        }
        if (carteJoueur?.valeur == carteOrdinateur?.valeur)
        {
            Console.WriteLine("Égalité !");
        }
    }
    while (carteJoueur is not null);
}
#endregion