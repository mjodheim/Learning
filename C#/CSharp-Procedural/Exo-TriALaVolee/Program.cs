int[] tab;
int taille;

Console.Write("Entrez la taille du tableau : ");
while (!int.TryParse(Console.ReadLine(), out taille) && taille <= 0)
{
    Console.WriteLine("Erreur : Veuillez entrer un nombre entier positif non nul !");
}

tab = new int[taille];

for (int i = 0; i < taille; i++)
{
    int value;

    Console.Write($"Entrez le nombre {i + 1} : ");
    while (!int.TryParse(Console.ReadLine(), out value))
    {
        Console.WriteLine("Erreur : Veuillez entrer un entier valide !");
    }

    int j = i;

    // Tant qu'on n'est pas au début du tableau 
    // ET que l'élément de gauche est plus grand que notre valeur saisie
    while (j > 0 && tab[j - 1] > value)
    {
        // On décale l'élément de gauche vers la droite
        tab[j] = tab[j - 1];
        j--; // On recule d'une case pour continuer la vérification
    }

    // On insère la valeur à sa place définitive (le "trou" créé)
    tab[j] = value;
}

System.Console.WriteLine("\nVoilà le tableau trié :");

foreach (var item in tab)
{
    System.Console.Write($"{item} | ");
}