namespace Domain.Entities.Monsters;

public class Orque : Monster
{
    public override int Force => BaseForce + 1;
    public override int Endurance => BaseEndurance;
    
    public  Orque() : base(hasGold: true, canBeSkinned: false) {}
}