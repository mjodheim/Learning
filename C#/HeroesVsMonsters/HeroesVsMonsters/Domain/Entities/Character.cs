namespace Domain.Entities;

public abstract class Character
{
    // Propriétés de base, sans modificateur
    protected int BaseForce { get; }
    protected int BaseEndurance { get; }
    
    // Propriétés finales, propres à chaque type de personnage, après modification des bases communes
    public abstract int Force { get; }
    public abstract int Endurance { get; }
    
    // Vie de chaque personnage, totale et actuelle
    public int BaseHp { get; private set; }
    public int CurrentHp { get; private set; }

    // Etat vital d'un personnage avec initialisation à true
    public bool IsAlive => CurrentHp > 0;
    
    // Position du personnage sur le gameboard
    public int X { get; private set; }
    public int Y { get; private set; }

    // Création d'un nouveau dé à 6 faces
    // Inutile de le créer à chaque fois → static
    protected static readonly De De6 = new De(1, 6);
    protected static readonly De De4 = new De(1, 4);
    
    // Ctor
    protected Character()
    {
        BaseForce = RollStat();
        BaseEndurance = RollStat();
        
        BaseHp = Endurance + CaracModifier(Endurance);
        CurrentHp =  BaseHp;
    }
    
    // Création de la position
    public void SetPosition(int x, int y)
    {
        X = x;
        Y = y;
    }
    
    // Génération aléatoire des propriétés Force et Endurance
    private static int RollStat()
    {
        // lancement des 4 dés à 6 faces
        var rolls = new int[]
        {
            De6.Lance(),
            De6.Lance(),
            De6.Lance(),
            De6.Lance(),
        };
        
        // on récupère les 3 meilleurs lancés des 4 lancés (LinQ !)
        return rolls.OrderByDescending(r => r).Take(3).Sum();
    }

    // Modificateur de caractéristiques interne utile pour la génération des propriétés de base et pour Frapper
    private static int CaracModifier(int baseEndurance)
    {
        return baseEndurance switch
        {
            < 5 => -1,
            < 10 => 0,
            < 15 => 1,
            _ => 2
        };
    }
    
    // Action d'attaque
    public void Frapper(Character cible)
    {
        var degat = De4.Lance() + CaracModifier(Force);
        cible.CurrentHp -= degat;
    }
}