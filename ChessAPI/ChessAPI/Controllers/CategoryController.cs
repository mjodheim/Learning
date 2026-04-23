using BLL.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace ChessAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class CategoryController : ControllerBase
{
    private readonly ICategoryService _categoryService;

    public CategoryController(ICategoryService categoryService)
    {
        _categoryService = categoryService;
    }

    [HttpGet]
    public async Task<IActionResult> GetCategories()
    {
        var categories = await _categoryService.GetCategories();
        return Ok(categories);
    }

    [HttpGet("tournament/{tournamentId}")]
    public async Task<IActionResult> GetCategoriesByTournament(int tournamentId)
    {
        var categories = await _categoryService.GetCategoriesByTournament(tournamentId);
        return Ok(categories);
    }
}
