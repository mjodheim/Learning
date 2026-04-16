using Microsoft.AspNetCore.Mvc;
using MyAPI.BLL.Interfaces;
using MyAPI.DTO.Product;
using MyAPI.Mappers;

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
    public async Task<ActionResult<List<ProductResponse>>> GetAll(int page = 1, int pageSize = 10)
    {
        if (page < 1) page = 1;
        if (pageSize > 10) pageSize = 10;
        
        var products = await _productService.GetAllProductsAsync(page, pageSize);
        return Ok(products.Select(ProductMapper.ToResponse).ToList());
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<ProductResponse>> GetById(int id)
    {
        var product = await _productService.GetProductByIdAsync(id);
        return Ok(ProductMapper.ToResponse(product));
    }

    [HttpPost]
    public async Task<ActionResult<ProductResponse>> Create(ProductCreateRequest request)
    {
        var product = ProductMapper.ToEntity(request);
        int newId = await _productService.CreateProductAsync(product);
        product.Id = newId;
        return CreatedAtAction(nameof(GetById), new { id = newId }, ProductMapper.ToResponse(product));
    }

    [HttpPut("{id}")]
    public async Task<ActionResult> Update(int id, ProductUpdateRequest request)
    {
        var product = ProductMapper.ToEntity(request, id);
        await _productService.UpdateProductAsync(product);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> Delete(int id)
    {
        await _productService.DeleteProductAsync(id);
        return NoContent();
    }
}
