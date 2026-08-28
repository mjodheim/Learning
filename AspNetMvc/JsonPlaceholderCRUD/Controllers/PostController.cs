using JsonPlaceholderCRUD.Models;
using Microsoft.AspNetCore.Mvc;

namespace JsonPlaceholderCRUD.Controllers
{
    public class PostController : Controller
    {
        private readonly HttpClient _httpClient;
        private static List<Post>? _allPosts = new();

        public PostController()
        {
            _httpClient = new HttpClient
            {
                BaseAddress = new Uri("https://jsonplaceholder.typicode.com/")
            };
        }

        #region GET

        public async Task<IActionResult> Index()
        {
            _allPosts = await _httpClient.GetFromJsonAsync<List<Post>>("posts");
            return View(_allPosts ?? new List<Post>());
        }

        public async Task<IActionResult> Details (int id)
        {
            Post? post = await _httpClient.GetFromJsonAsync<Post>($"posts/{id}");
            if(post is null)
                return NotFound();
            return View(post);
        }

        public IActionResult Create ()
        {
            TempData["TitrePage"] = "Créer un nouveau post";

            return View(new Post());
        }

        public async Task<IActionResult> Delete(int id)
        {
            Post? post = await _httpClient.GetFromJsonAsync<Post>($"posts/{id}");
            if(post is null)
                return NotFound();
            TempData["TitrePage"] = "Supprimer un post";
            return View(post);
        }

        public async Task<IActionResult> Edit(int id)
        {
            Post? post = await _httpClient.GetFromJsonAsync<Post>($"posts/{id}");
            if(post is null)
                return NotFound();
            ViewData["TitrePage"] = "Éditer un post";
            return View(post);
        }

        #endregion

        #region POST

        [HttpPost]
        public async Task<IActionResult> Create(Post post)
        {
            // Si la validation formulaire n'est pas passée on renvoie le formulaire actuel
            if (!ModelState.IsValid)
            {
                return View(post);
            }

            HttpResponseMessage response = await _httpClient.PostAsJsonAsync("posts", post);

            // Si le post a été correctement créé
            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = $"Le post {post.Title} a bien été créé.";
                return RedirectToAction("Index");
            }

            return View(post);
        }

        [HttpPost]
        public async Task<IActionResult> Delete (Post post)
        {

            HttpResponseMessage response = await _httpClient.DeleteAsync($"posts/{post.Id}");

            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = $"Le post avec l'id {post.Id} a bien été supprimé.";
                return RedirectToAction("Index");
            }

            return View(post);
        }

        [HttpPost]
        public async Task<IActionResult> Edit (Post post)
        {
            if (!ModelState.IsValid)
            {
                return View(post);
            }

            HttpResponseMessage response = await _httpClient.PatchAsJsonAsync($"posts/{post.Id}",post);

            if (response.IsSuccessStatusCode)
            {
                TempData["SuccessMessage"] = $"Le post avec l'id {post.Id} a bien été édité.";
                return RedirectToAction("Index");
            }

            return View(post);
        }

        #endregion

    }
}
