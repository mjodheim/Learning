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
            {
                Console.WriteLine("Montant invalide");
                return; //Erreur!!!
            }

            Solde += montant;
        }

        public virtual void Retrait(double montant)
        {
            Retrait(montant, 0D);
        }

        protected void Retrait(double montant, double ligneDeCredit)
        {
            if(ligneDeCredit < 0)
            {
                Console.WriteLine("Ligne de crédit invalide");
                return; //Erreur!!!
            }

            if (montant <= 0)
            {
                Console.WriteLine("Montant invalide");
                return; //Erreur!!!
            }

            if (Solde - montant < -ligneDeCredit)
            {
                Console.WriteLine("Solde insuffisant");
                return; //Erreur!!!
            }

            Solde -= montant;
        }

        protected abstract double CalculInteret();

        public void AppliquerInteret()
        {
            Solde += CalculInteret();
        }
    }
}