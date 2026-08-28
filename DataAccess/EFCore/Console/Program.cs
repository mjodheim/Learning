using EFCore.Domain.Entities;

// Création d'un garage avec 3 voitures
Garage garage = new Garage();
Owner toto = new Owner
{
    Name = "Toto",
    Email = "toto@gmail.com"
};

garage.Name = "Garage";
garage.Address = "adresse bidon";

