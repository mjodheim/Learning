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

// Console.Clear();

// int [] tab ;
// int taille;

// // Récupération de la taille du tableau
// while (true)
// {
//     System.Console.Write("Entrez la taille du tableau : ");
//     if (int.TryParse(Console.ReadLine(), out taille) && taille > 1) // Il faut au minimum 2 places pour pouvoir bouger !
//     {
//         break;
//     }
//     System.Console.WriteLine("Merci d'entrer un nombre entier positif > 1 !");
// }
// tab = new int[taille];

// // On place le pion '0'
// tab[0] = 0;

// // On rempli le tableau de 1
// for (int i = 1; i < taille; i++)
// {
//     tab[i] = 1;
// }

// // On vide l'interface et on masque le curseur
// Console.Clear();
// Console.CursorVisible = false;

// System.Console.WriteLine($"Déplacement du pion '0' : gauche (g) - droite (d) - quitter (q)");

// showTab(tab);

// bool @continue = true;

// while (@continue)
// {
//     // On intercepte la valeur pour ne pas l'afficher dans la console
//     var keyInfo = Console.ReadKey(true);

//     if (keyInfo.Key == ConsoleKey.D || keyInfo.Key == ConsoleKey.G)
//     {
//         move(keyInfo.Key,tab);
//     }
//     if (keyInfo.Key == ConsoleKey.Q)
//     {
//         System.Console.WriteLine("Merci d'avoir joué !");
//         @continue = false;
//     }
// }



// // Méthode de mouvement
// static void move(ConsoleKey key, int[] tab)
// {
//     // Contrôle des bornes !
//     if ((key == ConsoleKey.G && tab[0] != 0) || (key == ConsoleKey.D && tab[tab.Length-1] != 0))
//     {
//         // Récupération de l'emplacement du '0'
//         int index = 0;
//         for (int i = 0; i < tab.Length; i++)
//         {
//             if (tab[i] == 0)
//             {
//                 index = i;
//             }
//         }
//         // Déplacement du '0'
//         switch (key)
//         {
//             case ConsoleKey.G :
//                 tab[index-1] = 0;
//                 tab[index] = 1;
//                 break;
//             case ConsoleKey.D :
//                 tab[index+1] = 0;
//                 tab[index] = 1;
//                 break;
//             default:
//                 break;
//         }
//         showTab(tab);
//     }
// }

// // Méthode pour afficher un tableau
// static void showTab (int[] tab)
// {
//     Console.SetCursorPosition(0,1);
//     string output = "|";
//     foreach (var item in tab)
//     {
//         output += $" {item} |";
//     }
//     System.Console.WriteLine(output);
// }

#endregion
#region Carte altitude

// Console.Clear();

//     decimal[,] carte;
//     int x, y;
//     Random random = new Random();
//     decimal maxValue = 0, averageValue = 0;

//     do
//     {
//         System.Console.Write("Donnez la valeur de x : ");
//     } while (!int.TryParse(Console.ReadLine(),out x));

//     do
//     {
//         System.Console.Write("Donnez la valeur de y : ");
//     } while (!int.TryParse(Console.ReadLine(),out y));

//     carte = new decimal[x,y];

//     // On rempli la carte avec des nombres aléatoires et on cherche la valeur la plus haute
//     for (int i = 0; i < y; i++)
//     {
//         for (int j = 0; j < x; j++)
//         {
//             carte[j,i] = (decimal)random.Next(25000)/3;
//             if (carte[j,i] > maxValue)
//             {
//                 maxValue = carte[j,i];
//             }
//             averageValue += carte[j,i];
//         }
//     }

//     averageValue /= (x*y);

//     System.Console.WriteLine($"Après génération des altitudes, la valeur maximale et de {maxValue} mètres et la moyenne est de {averageValue} mètres.");

#endregion
#region Notes élèves
// Console.Clear();

// int[][] notesTab;
// int nbClasses, nbStudents, maxNoteByClass = 0, maxGlobalNote = 0, nbGlobalStudents = 0;
// decimal averageNote = 0;
// Random random = new Random();

// do
// {
//     System.Console.Write("Combien de classes y a-t-il ? ");
// } while (!int.TryParse(Console.ReadLine(),out nbClasses));

// notesTab = new int[nbClasses][];

// for (int i = 0; i < nbClasses; i++)
// {
//     do
//     {
//         System.Console.Write($"Nombre d'élèves dans la classe {i+1} ? ");
//     } while (!int.TryParse(Console.ReadLine(),out nbStudents));

//     notesTab[i] = new int[nbStudents];
//     nbGlobalStudents += nbStudents;

//     // On rempli avec des notes aléatoires
//     for (int j = 0; j < nbStudents; j++)
//     {
//         notesTab[i][j] = random.Next(21);
//         averageNote += notesTab[i][j];
//         if (notesTab[i][j] > maxNoteByClass)
//         {
//             maxNoteByClass = notesTab[i][j];
//         }
//     }
//     if (maxNoteByClass > maxGlobalNote)
//     {
//         maxGlobalNote = maxNoteByClass;
//     }
//     System.Console.WriteLine($"Note maximale pour la classe {i+1} : {maxNoteByClass}");
// }

// if (maxGlobalNote != 0)
// {
//     averageNote /= nbGlobalStudents;
// }

// System.Console.WriteLine($"""
//     Note maximale globale : {maxGlobalNote}
//     Note moyenne globale : {averageNote}
// """);
#endregion
#region Application bancaire

Console.Clear();
    
int limiteQuotidienne = 1500, 
    montantDecouvert = 200, 
    montantDecouvertMax = 1250, 
    retraitDuJour = 0, 
    choix = 0, 
    montant;
decimal soldeCompte = 917;
DateTime dateDernierRetrait = DateTime.Today;

while (choix != 5)
{
    Console.Clear();
    Console.WriteLine("""
        ==================== APPLICATION BANCAIRE ====================
        1. Ajouter | 2. Retirer | 3. Découvert | 4. Solde | 5. Quitter
        """);
    
    if (!int.TryParse(Console.ReadLine(), out choix)) continue;

    switch (choix)
    {
        case 1: AjouterArgent(); break;
        case 2: RetirerArgent(); break;
        case 3: ChangerMontantDecouvert(); break;
        case 4: ConsulterCredit(); break;
        case 5: Console.WriteLine("Merci de votre visite, passez une belle journée !"); break;
    }
}

void AjouterArgent()
{
    do
    {
        Console.Clear();
        System.Console.WriteLine("Combien voulez-vous ajouter à votre compte ?");
    } while (!int.TryParse(Console.ReadLine(),out montant));
    if (montant <= 0)
    {
        System.Console.WriteLine("""
        Pour les retraits, merci de sélectionner le choix adéquat dans le menu principal.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        AfficherMenu();
    }
    soldeCompte += montant;
    System.Console.WriteLine($"""
    Votre solde a été crédité d'un montant de {montant} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
}

void RetirerArgent()
{
    do
    {
        Console.Clear();
        System.Console.WriteLine("""
        Combien voulez-vous retirer de votre compte ?
        1. 20€          4. 200€
        2. 50€          5. Autre montant
        3. 100€         6. Revenir au menu principal
        """);
    } while (!int.TryParse(Console.ReadLine(),out choix));
    switch (choix)
    {
        case 1:
            montant = 20;
            break;
        case 2:
            montant = 50;
            break;
        case 3:
            montant = 100;
            break;
        case 4:
            montant = 200;
            break;
        case 5:
            do
            {
                System.Console.WriteLine("Choisissez le montant désiré (un nombre entier)");
            } while (!int.TryParse(Console.ReadLine(),out montant));
            if (montant <= 0)
            {
                System.Console.WriteLine("""
                Bien tenté petit malin, mais pour les crédits de compte ce n'est pas le bon endroit !
                Appuyez sur une touche pour revenir au menu principal.
                """);
                Console.ReadKey();
                return;
            }
            break;
        case 6:
            return;
        default:
            System.Console.WriteLine("""
            Choix incorrect.
            Appuyez sur une touche pour revenir au menu principal.
            """);
            Console.ReadKey();
            return;
    }
    if(!ProcederRetrait()) return;
    System.Console.WriteLine($"""
    Voici votre argent.
    Votre solde a été débité d'un montant de {montant} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
}

bool ProcederRetrait()
{
    if (montant > soldeCompte + montantDecouvert)
    {
        System.Console.WriteLine("""
        Solde insuffisant.
        Appuyez sur une touche pour modifier le montant.
        """);
        Console.ReadKey();
        return false;
    }
    if (VerifierLimiteQuotidienne(montant))
    {
        soldeCompte -= montant;
        retraitDuJour += montant;
        dateDernierRetrait = DateTime.Today;
        return true;
    } else
    {
        System.Console.WriteLine($"""
        Vous avez atteint la limite quotidienne de retrait de {limiteQuotidienne} euros : {retraitDuJour} retirés aujourd'hui.
        Appuyez sur une touche pour modifier le montant.
        """);
        Console.ReadKey();
        return false;
    }
}

void ChangerMontantDecouvert()
{
    do
    {
        Console.Clear();
        System.Console.Write("Indiquez le montant du découvert souhaité : ");
    } while (!int.TryParse(Console.ReadLine(),out montant));
    if (montant < 0)
    {
        System.Console.WriteLine("""
        Désolé, mais le montant doit être une valeure positive.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
    if (montant > montantDecouvertMax)
    {
        System.Console.WriteLine($"""
        Vous ne pouvez pas dépasser votre limite de découvert maximale de {montantDecouvertMax} euros.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
    montantDecouvert = montant;
    System.Console.WriteLine($"""
    Vous avez modifié votre découvert à {montantDecouvert} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
    return;
}

void ConsulterCredit()
{
    Console.Clear();
    if (soldeCompte < 0)
    {
        System.Console.WriteLine($"""
        Votre compte est en négatif : {soldeCompte} euros.
        Veuillez régulariser la situation rapidement afin d'éviter des frais supplémentaires.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    } else
    {
        System.Console.WriteLine($"""
        Votre compte présente un solde de {soldeCompte} euros.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
}

bool VerifierLimiteQuotidienne(int montant)
{
    if (dateDernierRetrait != DateTime.Today)
    {
        retraitDuJour = 0;
    }
    return retraitDuJour + montant <= limiteQuotidienne;
}

#endregion

