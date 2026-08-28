using Domain.Interfaces;

namespace Domain.Entities;

public class Livre : IProduit
{
    public string Nom { get;  set; }
    public double Prix { get;  set; }

    public override string ToString() => Nom;

}
