using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Mvc;

namespace MyAPI.Middlewares;

public class GlobalExceptionHandler : IExceptionHandler
{
    private readonly ILogger<GlobalExceptionHandler> _logger;

    public GlobalExceptionHandler(ILogger<GlobalExceptionHandler> logger)
    {
        _logger = logger;
    }

    public async ValueTask<bool> TryHandleAsync(
        HttpContext httpContext,
        Exception exception,
        CancellationToken cancellationToken)
    {
        _logger.LogError(exception, "Une exception non gérée est survenue.");

        var problemDetails = new ProblemDetails();

        switch (exception)
        {
            case ArgumentException:
                problemDetails.Status = StatusCodes.Status400BadRequest;
                problemDetails.Title = exception.Message;
                break;

            case KeyNotFoundException:
                problemDetails.Status = StatusCodes.Status404NotFound;
                problemDetails.Title = exception.Message;
                break;

            default:
                problemDetails.Status = StatusCodes.Status500InternalServerError;
                problemDetails.Title = "Une erreur interne est survenue";
                break;
        }

        httpContext.Response.StatusCode = problemDetails.Status.Value;
        httpContext.Response.ContentType = "application/problem+json";

        await httpContext.Response.WriteAsJsonAsync(problemDetails, cancellationToken);

        return true;
    }
}
