using Domain.Interfaces;

namespace Domain.Entities;

public class Depot<T> : IDepot<T>
{
    private List<T> _elements = new List<T>();

    public void Ajouter(T element)
    {
        _elements.Add(element);
    }

    public void Retirer(int index)
    {
        if (index < 0 || index > _elements.Count)
            throw new IndexOutOfRangeException("L'index n'est pas valide.");
        
        _elements.RemoveAt(index);
    }

    public T Recuperer(int index)
    {
        if (index < 0 || index > _elements.Count)
            throw new IndexOutOfRangeException("L'index n'est pas valide.");

        return _elements[index];
    }

    public int Compter()
    {
        return _elements.Count;
    }

    public void Afficher()
    {
        Console.WriteLine($"Voici les éléments demandés :");
        foreach (T element in _elements)
        {
            Console.WriteLine($"- {element}");
        }
    }
}