using System.ComponentModel.DataAnnotations;

namespace JsonPlaceholderCRUD.Models;

public class Post
{
    public int Id { get; set; }

    [Required(ErrorMessage ="Ce champs est requis")][MinLength(10,ErrorMessage ="Le titre doit comporter au minimum 10 caractères")]
    public string Title { get; set; } = string.Empty;

    [Required(ErrorMessage ="Ce champs est requis")][MinLength(20, ErrorMessage ="Le contenu doit contenir au minimum 15 caractères")]
    public string Body { get; set; } = string.Empty;

    [Required(ErrorMessage ="Ce champs est requis")]
    public int UserId { get; set; }
}
