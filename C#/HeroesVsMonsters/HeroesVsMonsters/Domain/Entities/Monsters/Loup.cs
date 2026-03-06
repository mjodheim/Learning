namespace Domain.Entities.Monsters;

public class Loup : Monster
{
    public override int Force => BaseForce;
    public override int Endurance => BaseEndurance;

    public Loup() : base(hasGold: false, canBeSkinned: true){ }
}