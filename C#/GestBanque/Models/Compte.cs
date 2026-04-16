using Models.Exceptions;

namespace Models
{
    public abstract class Compte
    {
        public static double operator +(double value, Compte compte)
        {
            return (value < 0 ? 0 : value) + (compte.Solde < 0 ? 0 : compte.Solde);
        }

        public string Numero { get; set; }
        public double Solde { get; private set; }
        public Personne Titulaire { get; set; }

        public void Depot(double montant)
        {
            if (montant <= 0)
                throw new ArgumentOutOfRangeException(nameof (montant), "Le montant doit être supérieur à 0.");

            Solde += montant;
        }

        public virtual void Retrait(double montant)
        {
            Retrait(montant, 0D);
        }

        protected void Retrait(double montant, double ligneDeCredit)
        {
            if (ligneDeCredit < 0)
                throw new ArgumentOutOfRangeException(nameof(ligneDeCredit), "La ligne de crédit ne peut pas être négative.");

            if (montant <= 0)
                throw new ArgumentOutOfRangeException(nameof(montant), "Le montant doit être supérieur à 0.");

            if (Solde - montant < -ligneDeCredit)
                throw new SoldeInsuffisantException("Solde insuffisant pour effectuer le retrait.");

            Solde -= montant;
        }

        protected abstract double CalculInteret();

        public void AppliquerInteret()
        {
            Solde += CalculInteret();
        }
    }
}