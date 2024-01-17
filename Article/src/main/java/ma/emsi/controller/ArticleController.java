package ma.emsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.model.Article;
import ma.emsi.service.ArticleService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@PostMapping(value = "/article", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Void> add(@RequestBody Article p) {
		articleService.create(p);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/articles", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public List<Article> findAll() {
		return articleService.findAll();
	}

	@GetMapping(value = "/article/id/{id}", produces = { "application/json", "application/xml" })
	public Article findarticle(@PathVariable int id) throws Exception {
		return articleService.findById(id);
	}

}
