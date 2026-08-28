using EFCore.Domain.Entities;
using Microsoft.EntityFrameworkCore;

namespace EFCore.Context;

public class MyDbContext : DbContext
{
    public DbSet<Auto> Autos { get; set; }
    public DbSet<Owner> Owners { get; set; }
    public DbSet<Garage> Garages { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseSqlServer("{ConnectionString}",
            sqlOptions =>
            {
                sqlOptions.EnableRetryOnFailure(10, TimeSpan.FromSeconds(30), null);
            });
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Auto>()
            .HasOne(auto => auto.Owner)
            .WithMany(owner => owner.Autos)
            .HasForeignKey(auto => auto.OwnerId)
            .OnDelete(DeleteBehavior.Restrict);
        modelBuilder.Entity<Auto>()
            .HasOne(auto => auto.Garage)
            .WithMany(garage => garage.Autos)
            .HasForeignKey(auto => auto.GarageId)
            .OnDelete(DeleteBehavior.Restrict);
    }
}