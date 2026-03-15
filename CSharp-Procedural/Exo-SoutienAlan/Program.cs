// C# Procédural - soutien pour Alan

#region Addition de deux nombres entiers avec utilisation d'une fonction Addition() et une fonction de vérification de nombre entier
int nb1, nb2, result;

Console.WriteLine("===== Addition de deux nombres entiers =====");
Console.WriteLine("Entrez le premier nombre : ");

// Tant que l'utilisateur n'aura pas entré un nombre entier, on le lui redemande
nb1 = VerifierSiEntier(Console.ReadLine());

// Même chose pour le nombre 2
Console.WriteLine("Entrez le deuxième nombre : ");
nb2 = VerifierSiEntier(Console.ReadLine());

// On appelle la fonction qui va nous retourner le résultat qu'on stock dans une variable
result = Addition(nb1, nb2);

Console.WriteLine($"Le résultat de {nb1} + {nb2} vaut {result}.");
Console.ReadKey(); // juste pour attendre que l'utilisateur appuie sur une touche avant de fermer la console...
#endregion


#region Functions
int Addition(int nb1, int nb2)
{
    int result = nb1 + nb2;
    return result;
}
int VerifierSiEntier(string output)
{
    int nombreEntier;
    while (!int.TryParse(output, out nombreEntier))
    {
        Console.WriteLine("Merci de me respecter, entrez un nombre entier : ");
        output = Console.ReadLine();
    }
    return nombreEntier;
}
#endregion