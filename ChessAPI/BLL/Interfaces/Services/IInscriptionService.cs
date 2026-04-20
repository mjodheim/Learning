namespace BLL.Interfaces.Services;

public interface IInscriptionService
{
    Task Register(int tournamentId, int playerId);
    Task Unregister(int tournamentId, int playerId);
}