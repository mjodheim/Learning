namespace Domain.Entities;

public abstract class Hero : Character
{
    public int HeroGold { get; private set; }
    public int HeroLeather { get; private set; }
    
    protected Hero() : base() { }

    public void AddLoot(int gold, int leather)
    {
        HeroGold += gold;
        HeroLeather += leather;
    }
    
}