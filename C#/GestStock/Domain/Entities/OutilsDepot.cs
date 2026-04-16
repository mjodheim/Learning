using Domain.Interfaces;

namespace Domain.Entities;

public static class OutilsDepot
{
    public static void Echanger<T>(ref T a, ref T b)
    {
        T temp = a;
        a = b;
        b = temp;
    }

    public static int Trouver<T>(List<T> liste, T valeur)
    {
        for (int i = 0; i < liste.Count; i++)
        {
            if (liste[i]!.Equals(valeur))
                return i;
        }

        return -1;
    }

    public static List<T> Filtrer<T>(List<T> liste, ICondition<T> condition)
    {
        List<T> FilteredList = new List<T>();

        foreach (T element in liste)
        {
            if (condition.Verifier(element))
                FilteredList.Add(element);
        }

        return FilteredList;
    }


}
