using System.Globalization;
using ADO.Domain;
using ADO.Domain.Entities;
using Microsoft.Data.SqlClient;

var connectionString =
    @"Data Source=GOSVDI910\TFTIC;
    Initial Catalog=dbslide;
    Integrated Security=True;
    Encrypt=True;
    TrustServerCertificate=True";

using var connection = new SqlConnection(connectionString);
connection.Open();

void ExoStudents()
{
    using (var command = connection.CreateCommand())
    {
        command.CommandText = "SELECT COUNT(*) FROM student";

        var count = (int)command.ExecuteScalar();

        Console.WriteLine($"Nombre d'étudiants : {count}");
    }
}

// Affiche l'ensemble des informations de chaque professeur
void ExoProfessors()
{
    using (var command = connection.CreateCommand())
    {
        command.CommandText = "SELECT * FROM professor";

        List<Professor> professors = new();

        using (var reader = command.ExecuteReader())
        {
            while (reader.Read()) professors.Add(reader.ToProfessor());
        }

        foreach (var prof in professors)
        {
            var props = prof.GetType().GetProperties();

            foreach (var prop in props) Console.Write($"{prop.GetValue(prof)} | ");

            Console.WriteLine();
        }
    }
}

// Insérer une ligne dans la nouvelle table product
void ExoDML()
{
    Console.WriteLine("Entrez le nom de votre produit à ajouter");
    var name = Console.ReadLine()!;

    double price;
    Console.WriteLine("Entrez son prix");
    while (!double.TryParse(Console.ReadLine(), new CultureInfo("en-US"), out price))
        Console.WriteLine("Merci d'entrer une valeur correcte");

    Product product = new(name, price);

    using (var cmd = connection.CreateCommand())
    {
        cmd.CommandText = "INSERT INTO product (Product_name, Product_price) VALUES (@name,@price)";
        cmd.Parameters.AddWithValue("@name", product.Product_name);
        cmd.Parameters.AddWithValue("@price", product.Product_price);

        try
        {
            var nbLines = cmd.ExecuteNonQuery();

            Console.WriteLine($"Nombre de lignes ajoutée(s) : {nbLines}");
        }
        catch
        {
            Console.WriteLine("Erreur d'insertion");
        }
    }
}

ExoDML();