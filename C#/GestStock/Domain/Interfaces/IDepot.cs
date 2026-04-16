namespace Domain.Interfaces;

public interface IDepot<T>
{
    void Ajouter(T element);

    void Retirer(int index);
    
    T Recuperer(int index);
    
    int Compter();
}
