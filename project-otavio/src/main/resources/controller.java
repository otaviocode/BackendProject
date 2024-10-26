@Controller
public class UserController {

    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "cadastro"; // Retorna o template "cadastro.html"
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<String> salvarUsuario(@RequestBody User user) {
        // Lógica para salvar o usuário
        return ResponseEntity.ok("Usuário salvo com sucesso!");
    }
}
