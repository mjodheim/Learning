namespace Domain.Entities;

public class Category
{
    public int Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public int MinAge { get; set; } = 0;
    public int MaxAge { get; set; }
}