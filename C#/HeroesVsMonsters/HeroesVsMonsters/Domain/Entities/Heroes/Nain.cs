namespace Domain.Entities.Heroes;

public class Nain : Hero
{
    public override int Force => BaseForce;
    public override int Endurance => BaseEndurance + 2;

    public Nain() : base() { }
}