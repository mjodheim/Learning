namespace Domain;

public class De
{
    private int Minimum { get; }
    private int Maximum { get; }

    private readonly Random _random = new Random();

    protected De() { }
    
    public De(int minimum, int maximum)
    {
        Minimum = minimum;
        Maximum = maximum;
    }

    public int Lance() => _random.Next(Minimum, Maximum + 1);
}