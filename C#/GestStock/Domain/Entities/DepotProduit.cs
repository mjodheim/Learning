using Domain.Interfaces;

namespace Domain.Entities;

public class DepotProduit<T> : Depot<T> where T : IProduit
{
    public double PrixTotal()
    {
        double total = 0;

        for (int i = 0; i < Compter(); i++)
        {
            total += Recuperer(i).Prix;
        }

        return total;
    }
}
