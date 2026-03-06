namespace Domain.Entities.Heroes;

public class Humain : Hero
{
    public override int Force => BaseForce + 1;
    public override int Endurance => BaseEndurance + 1;

    public Humain() : base() { }
    
}