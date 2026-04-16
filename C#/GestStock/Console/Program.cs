using Domain.Entities;

DepotProduit<Livre> listeLivres = new();
DepotProduit<Appareil> listeAppareils = new();

// Ajout de livres
listeLivres.Ajouter(new Livre { Nom = "Pro C# 10 with .NET 6", Prix = 50.99 });
listeLivres.Ajouter(new Livre { Nom = "Angular – The Complete Guide", Prix = 49.99 });
listeLivres.Ajouter(new Livre { Nom = "Azure DevOps Explained", Prix = 42.99 });

// Ajout d'appareils
listeAppareils.Ajouter(new Appareil { Nom = "Logitech MX Keys S", Prix = 127.55 });
listeAppareils.Ajouter(new Appareil { Nom = "Redragon M811 Pro", Prix = 42.45 });
listeAppareils.Ajouter(new Appareil { Nom = "Dell Pro 27 Plus P2725QE (4K USB-C)", Prix = 453.95 });

listeLivres.Afficher();
Console.WriteLine($"Prix total du dépot des livres : {listeLivres.PrixTotal()}");

listeAppareils.Afficher();
Console.WriteLine($"Prix total du dépot des livres : {listeAppareils.PrixTotal()}");

// Echanges
int a = 5, b = 10;
OutilsDepot.Echanger(ref a, ref b);
Console.WriteLine($"a = {a}, b = {b}");

// Trouver
List<int> liste = new List<int> { 1, 2, 3, 4 };
Console.WriteLine(OutilsDepot.Trouver(liste, 3));

// Filtrer
