namespace Models;

public class Epargne : Compte
{
    public DateTime DernierRetrait { get; private set; }

    public override void Retrait(double montant)
    {
        double oldSolde = Solde;
        base.Retrait(montant);

        if(Solde != oldSolde)
        {
            DernierRetrait = DateTime.Now;
        }
    }

    protected override double CalculInteret() => Solde * 0.045;
}
