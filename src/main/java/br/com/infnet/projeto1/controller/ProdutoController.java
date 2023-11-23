package br.com.infnet.projeto1.controller;

import br.com.infnet.projeto1.model.Produto;
import br.com.infnet.projeto1.util.ProdutoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoUtil produtoUtil;
    Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);
    @GetMapping
    public List<Produto> getAll(){
        LOGGER.info("Get ALL");
        List<Produto> produtos = produtoUtil.getAll();
        return produtos;
    }
    @GetMapping("/{id}")
    public Produto getById(@PathVariable  int id){
        LOGGER.info("Get by id: " + id);
        return produtoUtil.getById(id);

    }
    @PutMapping("/{id}")
    public void update(@PathVariable  int id, @RequestBody Produto produto){
        produtoUtil.update(id,produto);
        LOGGER.info("UPDATE: " + id  + "--" + produto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        produtoUtil.delete(id);
        LOGGER.info("Delete: " + id);
    }

    @PostMapping
    public void create(@RequestBody Produto produto){
        LOGGER.info("CREATE: " + produto);
        produtoUtil.save(produto);
    }

}
