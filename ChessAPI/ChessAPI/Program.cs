using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using BLL.Services;
using DAL.Repositories;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddOpenApi();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection")!;

#region Repositories

builder.Services.AddScoped<IPlayerRepository>(sp => new PlayerRepository(connectionString));
builder.Services.AddScoped<IMatchRepository>(sp => new MatchRepository(connectionString));
builder.Services.AddScoped<ITournamentRepository>(sp => new TournamentRepository(connectionString));
builder.Services.AddScoped<ICategoryRepository>(sp => new CategoryRepository(connectionString));
builder.Services.AddScoped<IInscriptionRepository>(sp => new InscriptionRepository(connectionString));
#endregion

#region Services
builder.Services.AddScoped<IPlayerService, PlayerService>();

#endregion

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
app.Run();