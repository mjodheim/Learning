using Domain.Entities;
using Domain.Entities.Monsters;

namespace Domain.Factories;

public static class MonsterFactory
{
    private static readonly Random RandomNumber = new ();
    
    // Création d'un type de monstre basé sur l'Enum, utilisé uniquement dans la factory
    private static Monster CreateMonster(MonsterType monsterType)
    {
        return monsterType switch
        {
            MonsterType.Loup => new Loup(),
            MonsterType.Orque => new Orque(),
            MonsterType.Dragonet => new Dragonet(),
            _ => throw new ArgumentOutOfRangeException(nameof(monsterType), "Ce type de monstre n'existe pas.")
        };
    }
    
    // Création effective d'un monstre aléatoire
    public static Monster CreateRandomMonster()
    {
        // Récupérer le nombre de types de monstres dans l'Enum MonsterType.cs
        var monsterTypes = Enum.GetValues<MonsterType>();
        var index = RandomNumber.Next(monsterTypes.Length);
        return CreateMonster(monsterTypes[index]);
    }
}