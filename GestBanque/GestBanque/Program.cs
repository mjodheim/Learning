using Models;
using Models.OperatorsExo;

Celsius celsius = new Celsius() { Temperature = 30 };
Fahrenheit fahrenheit = celsius;

celsius = (Celsius)fahrenheit;



Banque banque = new Banque() { Nom = "Techno Banking" };

Personne john = new Personne()
{
    Nom = "Doe",
    Prenom = "John",
    DateNaiss = new DateTime(1970, 1, 1)
};

Courant courant = new Courant()
{
    Numero = "00001",
    LigneDeCredit = 100,
    Titulaire = john
};

Epargne epargne = new Epargne()
{
    Numero = "00002",
    Titulaire = john
};

banque.Ajouter(courant);
banque.Ajouter(epargne);

banque["00001"].Depot(-20);
Console.WriteLine($"Solde après un dépot de -20 : {banque["00001"].Solde}");

banque["00001"].Depot(200);
Console.WriteLine($"Solde après un dépot de 200 : {banque["00001"].Solde}");

banque["00001"].Retrait(-20);
Console.WriteLine($"Solde après un retrait de -20 : {banque["00001"].Solde}");

banque["00001"].Retrait(100);
Console.WriteLine($"Solde après un retrait de 100 : {banque["00001"].Solde}");

banque["00001"].Retrait(200);
Console.WriteLine($"Solde après un retrait de 200 : {banque["00001"].Solde}");

banque["00001"].Retrait(100);
Console.WriteLine($"Solde après un retrait de 100 : {banque["00001"].Solde}");

banque["00002"].Depot(2000);
Console.WriteLine($"Solde après un dépot de 2000 : {banque["00002"].Solde}");

Console.WriteLine($"Avoir des comptes de {john.Prenom} {john.Nom} : {banque.AvoirDesComptes(john)}");