namespace Domain.Entities;

public abstract class Monster : Character
{
    public int MonsterGold { get; protected set; }
    public int MonsterLeather { get; protected set; }

    protected Monster() { }
    
    protected Monster(bool hasGold, bool canBeSkinned) : base()
    {
        MonsterGold = hasGold ? De6.Lance() : 0;
        MonsterLeather = canBeSkinned ? De4.Lance() : 0;
    }
}