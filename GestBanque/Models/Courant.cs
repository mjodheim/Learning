namespace Models;

public class Courant : Compte
{
    public double LigneDeCredit
    {
        get { return field; }
        set 
        {
            if (value < 0)
                return; //Erreur!!!

            field = value; 
        }
    }

    public override void Retrait(double montant)
    {
        Retrait(montant, LigneDeCredit);
    }

    protected override double CalculInteret() => Solde > 0 ? Solde * 0.03 : Solde * 0.0975;
}
