using Domain.Entities;
using Domain.Entities.Heroes;

namespace Domain.Factories;

public static class HeroFactory
{
    private static readonly Random RandomNumber = new ();
    
    // Création d'un type de héros basé sur l'Enum
    public static Hero CreateHero(HeroType heroType)
    {
        return heroType switch
        {
            HeroType.Humain => new Humain(),
            HeroType.Nain => new Nain(),
            _ => throw new ArgumentOutOfRangeException(nameof(heroType), "Ce type de héro n'existe pas.")
        };
    }
    
    // Création d'un héros aléatoire selon la volonté du joueur
    public static Hero CreateRandomHero()
    {
        // Récupérer le nombre de types de monstres dans l'Enum HeroType.cs
        var heroTypes = Enum.GetValues<HeroType>();
        var index = RandomNumber.Next(heroTypes.Length);
        return CreateHero(heroTypes[index]);
    }
}