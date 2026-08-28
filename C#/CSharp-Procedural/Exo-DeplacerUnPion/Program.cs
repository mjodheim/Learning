int[] tab;
int taille;

// Récupération de la taille du tableau
Console.Write("Entrez la taille du tableau : ");
while (!int.TryParse(Console.ReadLine(), out taille) && taille <= 1)
{
    Console.WriteLine("Merci d'entrer un nombre entier positif > 1 !");
}
tab = new int[taille];

// On place le pion '0'
tab[0] = 0;

// On rempli le tableau de 1
for (int i = 1; i < taille; i++)
{
    tab[i] = 1;
}

// On vide l'interface et on masque le curseur
Console.Clear();
Console.CursorVisible = false;

Console.WriteLine($"Déplacement du pion '0' : gauche (g) - droite (d) - quitter (q)");

showTab(tab);

bool @continue = true;

while (@continue)
{
    // On intercepte la valeur pour ne pas l'afficher dans la console
    var keyInfo = Console.ReadKey(true);

    if (keyInfo.Key == ConsoleKey.D || keyInfo.Key == ConsoleKey.G)
    {
        move(keyInfo.Key, tab);
    }
    if (keyInfo.Key == ConsoleKey.Q)
    {
        Console.WriteLine("Merci d'avoir joué !");
        @continue = false;
    }
}



// Méthode de mouvement
static void move(ConsoleKey key, int[] tab)
{
    // Contrôle des bornes !
    if ((key == ConsoleKey.G && tab[0] != 0) || (key == ConsoleKey.D && tab[tab.Length - 1] != 0))
    {
        // Récupération de l'emplacement du '0'
        int index = 0;
        for (int i = 0; i < tab.Length; i++)
        {
            if (tab[i] == 0)
            {
                index = i;
            }
        }
        // Déplacement du '0'
        switch (key)
        {
            case ConsoleKey.G:
                tab[index - 1] = 0;
                tab[index] = 1;
                break;
            case ConsoleKey.D:
                tab[index + 1] = 0;
                tab[index] = 1;
                break;
            default:
                break;
        }
        showTab(tab);
    }
}

// Méthode pour afficher un tableau
static void showTab(int[] tab)
{
    Console.SetCursorPosition(0, 1);
    string output = "|";
    foreach (var item in tab)
    {
        output += $" {item} |";
    }
    Console.WriteLine(output);
}