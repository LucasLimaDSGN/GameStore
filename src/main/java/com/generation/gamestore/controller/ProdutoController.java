package com.generation.gamestore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.CategoriaRepository;
import com.generation.gamestore.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
		private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getByID(@PathVariable Long id){
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)). orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/jogo/{jogo}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String jogo){
		return ResponseEntity.ok(produtoRepository.findAllByJogoContainingIgnoreCase(jogo));
		
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		if(categoriaRepository.existsById(produto.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
		if (produtoRepository.existsById(produto.getId())){
			
			if (categoriaRepository.existsById(produto.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produto));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produtos = produtoRepository.findById(id);
		
		if(produtos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
	}
}