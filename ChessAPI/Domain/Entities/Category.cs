namespace Domain.Entities;

public class Category
{
    public int Id { get; set; }
    public Category Name { get; set; }
    public int MinAge { get; set; } = 0;
    public int MaxAge { get; set; }
}