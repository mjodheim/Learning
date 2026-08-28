namespace ADO.Domain.Entities;

public class Product
{
    public int Product_id { get; set; }
    public string Product_name { get; private set; }
    public double Product_price { get; private set; }

    public Product(string name, double price)
    {
        Product_name = name;
        Product_price = price;
    }
}
