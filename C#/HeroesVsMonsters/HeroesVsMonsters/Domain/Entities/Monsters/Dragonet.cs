namespace Domain.Entities.Monsters;

public class Dragonet : Monster
{
    public override int Force => BaseForce;
    public override int Endurance => BaseEndurance + 1;
    
    public  Dragonet() : base(hasGold: true, canBeSkinned: true) {}
}