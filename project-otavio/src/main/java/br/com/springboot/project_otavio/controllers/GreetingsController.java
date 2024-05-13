package br.com.springboot.project_otavio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.project_otavio.model.Usuario;
import br.com.springboot.project_otavio.repository.UsuarioRepository;


//toda e qualquer requisição que voce fizer, seja com a URL ou com um celular diferente 
//ou com um computador diferente será interceptada e rodará com esse arquivo controller!!


//IC, CD, CDI = INJEÇÃO DE DEPENDÊNCIA 
//pois está injetando todas os recursos dentro do nosso controller isto é poder fazer
//buscar todos, buscar paginado etc .. rodar sql no banco deleta em lote ....
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
	//NOVO END POINT NO LINK: http://localhost:8000/mostrarnome/otavio (RETORNAR UM OLA)
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "teste curso API e Spring boot " + name + "!";
    }
    
    //NOVO END POINT NO LINK: http://localhost:8000/mostrarnome/otavio/olamundo/otavio (RETORNA MENSAGEM)
    @RequestMapping (value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario); //gravar no banco de dados
    	
    	return "Ola mundo " + nome;
    }
    
    //MÉTODO DE BUSCAR NO BANCO E RETORNAR UM JSON NO http://localhost:8000/listatodos (RETORNA JSON)
    
    @GetMapping(value = "listatodos") // NOSSO PRIMEIRO MÉTODO DE API
    @ResponseBody //retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuarios() {
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); //esse findall busca no banco de dados e retorna
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //RETORNARÁ UM JSON
    // ELE CONSULTARÁ O BANCO DE DADOS E RETORNARÁ UMA LISTA EM JSON
    	
    }
    
    //NOVO MÉTODO DE SALVAR
    @PostMapping(value = "salvar") //mapeia a url
    @ResponseBody //descrição da resposta
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){ //recebe os dados para salvar 
    // isso aqui acima recebe a classe usuario e dá o propio nome usuario (ELE RECEBE OS DADOS PASSADOS E INJETA)
    // AUTOMATICAMENTE NA CLASSE USUARIO
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
  //NOVO MÉTODO DE DELETAR
    @DeleteMapping(value = "delete") //mapeamento da url
    @ResponseBody //descrição da resposta
    public ResponseEntity<String> delete (@RequestParam Long iduser){  
    
    	usuarioRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User Deletado Com Sucesso", HttpStatus.OK);
    }
    
    //NOVO MÉTODO DE BUSCAR
    @GetMapping(value = "buscaruserid") //URL MAPEADA
    @ResponseBody //RESPOSTA PARA QUEM FEZ A REQUISIÇÃO
    
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser){//RETORNAR UM USUARIO
    //ESSE ACIMA RECEBE OS DADOS PARA CONSULTAR
    	
    Usuario usuario = usuarioRepository.findById(iduser).get(); //PESQUISAR NO BANCO DE DADOS
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK); // RETORNO PARA A TELA
    }
    
  //NOVO MÉTODO DE BUSCAR
    @PutMapping(value = "atualizar") //URL MAPEADA
    @ResponseBody //RESPOSTA PARA QUEM FEZ A REQUISIÇÃO
    
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){//RETORNAR UM USUARIO
    //ESSE ACIMA RECEBE OS DADOS PARA CONSULTAR
    	
    Usuario user = usuarioRepository.saveAndFlush(usuario); //PESQUISAR NO BANCO DE DADOS
    
    //criando um para nao deixar o usuario deixar compos sem nada
    if (usuario.getId() == 0) {
		return new ResponseEntity<String>("Id não informado para atualização. "
				+ "Informe o Id para continuar...", HttpStatus.OK);
	}
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK); // RETORNO PARA A TELA
    }
    
    @GetMapping(value = "buscarPorNome") //URL MAPEADA
    @ResponseBody //RESPOSTA PARA QUEM FEZ A REQUISIÇÃO
    
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name){//RETORNAR UM USUARIO
    //ESSE ACIMA RECEBE OS DADOS PARA CONSULTAR
    	
    List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); // RETORNO PARA A TELA
    }
    
}
