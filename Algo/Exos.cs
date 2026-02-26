#region Tri instantané d'un tableau d'entiers que l'utilisateur doit remplir

// Console.Clear();

// int?[] tab;
// int taille;

// do
// {
//     Console.WriteLine("Entrez la taille du tableau :");
//     taille = (int.TryParse(Console.ReadLine(), out int result)) ? result : 0;
//     if(taille <= 0)
//     {
//         Console.WriteLine("Veuillez entrer un nombre entier positif");
//     }
// } while (taille <= 0);

// tab = new int?[taille];

// for(int i = 0; i < taille; i++)
// {
//     do
//     {
//         System.Console.WriteLine("Entrez un nombre entier");
//         tab[i] = (int.TryParse(Console.ReadLine(), out int number)) ? number : null;
//     } while (tab[i] == null);

//     // Tri instantané : on boucle sur le tableau jusqu'à la position actuelle
//     // et on switch de place si l'élément actuel est plus petit que celui présent
//     // dans le tableau en position j
//     for(int j = 0; j < i; j++)
//     {
//         int? temp;
//         if(tab[i] < tab[j])
//         {
//             temp = tab[i];
//             tab[i] = tab[j];
//             tab[j] = temp;
//         }
//     }
// }

// // On affiche évidemment le tableau trié à la fin
// System.Console.WriteLine("Voici le tableau rempli et trié :");
// foreach(int? number in tab)
// {
//     System.Console.Write(number + " | ");
// }

#endregion
#region Version 2 du tri instantané

// Console.Clear();

//     int [] tab;
//     int taille;

//     while (true)
//     {
//         Console.Write("Entrez la taille du tableau : ");
//         if (int.TryParse(Console.ReadLine(), out taille) && taille > 0)
//         {
//             break;
//         }
//         Console.WriteLine("Erreur : Veuillez entrer un nombre entier positif non nul !");
//     }

//     tab = new int[taille];

//     for (int i = 0; i < taille; i++)
//     {
//         int value;
        
//         while (true)
//         {
//             Console.Write($"Entrez le nombre {i + 1} : ");
//             if (int.TryParse(Console.ReadLine(), out value))
//             {
//                 break;
//             }
//             Console.WriteLine("Erreur : Veuillez entrer un entier valide !");
//         }

//         int j = i;

//         // Tant qu'on n'est pas au début du tableau 
//         // ET que l'élément de gauche est plus grand que notre valeur saisie
//         while (j > 0 && tab[j - 1] > value)
//         {
//             // On décale l'élément de gauche vers la droite
//             tab[j] = tab[j - 1];
//             j--; // On recule d'une case pour continuer la vérification
//         }

//         // On insère la valeur à sa place définitive (le "trou" créé)
//         tab[j] = value;
//     }

//     System.Console.WriteLine("\nVoilà le tableau trié :");

//     foreach (var item in tab)
//     {
//         System.Console.Write($"{item} | ");
//     }

#endregion
#region Déplacer un pion 
    
    Console.Clear();

    int [] tab ;
    int taille;

    // Récupération de la taille du tableau
    while (true)
    {
        System.Console.Write("Entrez la taille du tableau : ");
        if (int.TryParse(Console.ReadLine(), out taille) && taille > 1) // Il faut au minimum 2 places pour pouvoir bouger !
        {
            break;
        }
        System.Console.WriteLine("Merci d'entrer un nombre entier positif > 1 !");
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

    System.Console.WriteLine($"Déplacement du pion '0' : gauche (g) - droite (d) - quitter (q)");

    showTab(tab);

    bool @continue = true;

    while (@continue)
    {
        // On intercepte la valeur pour ne pas l'afficher dans la console
        var keyInfo = Console.ReadKey(true);

        if (keyInfo.Key == ConsoleKey.D || keyInfo.Key == ConsoleKey.G)
        {
            move(keyInfo.Key,tab);
        }
        if (keyInfo.Key == ConsoleKey.Q)
        {
            System.Console.WriteLine("Merci d'avoir joué !");
            @continue = false;
        }
    }



    // Méthode de mouvement
    static void move(ConsoleKey key, int[] tab)
    {
        // Contrôle des bornes !
        if ((key == ConsoleKey.G && tab[0] != 0) || (key == ConsoleKey.D && tab[tab.Length-1] != 0))
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
                case ConsoleKey.G :
                    tab[index-1] = 0;
                    tab[index] = 1;
                    break;
                case ConsoleKey.D :
                    tab[index+1] = 0;
                    tab[index] = 1;
                    break;
                default:
                    break;
            }
            showTab(tab);
        }
    }

    // Méthode pour afficher un tableau
    static void showTab (int[] tab)
    {
        Console.SetCursorPosition(0,1);
        string output = "|";
        foreach (var item in tab)
        {
            output += $" {item} |";
        }
        System.Console.WriteLine(output);
    }

#endregion