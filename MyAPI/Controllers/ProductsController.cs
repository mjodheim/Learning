using Microsoft.AspNetCore.Mvc;
using MyAPI.BLL.Interfaces;
using MyAPI.Domain.Entities;

namespace MyAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class ProductsController : ControllerBase
{
    private readonly IProductService _productService;

    public ProductsController(IProductService productService)
    {
        _productService = productService;
    }

    [HttpGet]
    public async Task<ActionResult<List<Product>>> GetAll()
    {
        return Ok(await _productService.GetAllProductsAsync());
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<Product>> GetById(int id)
    {
        var product = await _productService.GetProductByIdAsync(id);

        if (product == null)
            return NotFound("Produit non trouvé");

        return Ok(product);
    }

    [HttpPost]
    public async Task<ActionResult> Create(Product product)
    {
        await _productService.CreateProductAsync(product);
        return CreatedAtAction(nameof(GetById), new { id = product.Id }, product);
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> Update(int id, Product product)
    {
        var existing = await _productService.GetProductByIdAsync(id);

        if (existing == null)
            return NotFound("Produit non trouvé");

        product.Id = id;
        await _productService.UpdateProductAsync(product);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> Delete(int id)
    {
        var existing = await _productService.GetProductByIdAsync(id);

        if (existing == null)
            return NotFound("Produit non trouvé");

        await _productService.DeleteProductAsync(existing);
        return NoContent();
    }
}
