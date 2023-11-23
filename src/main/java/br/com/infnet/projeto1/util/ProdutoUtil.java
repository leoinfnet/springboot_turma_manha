package br.com.infnet.projeto1.util;

import br.com.infnet.projeto1.model.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProdutoUtil {
    Map<Integer,Produto> produtos = getProdutos();
    private int lastId = 3;
    private Map<Integer, Produto> getProdutos(){
        Map<Integer, Produto> produtos = new HashMap<>();
        Produto playtstation = new Produto(1,"Playstation 5", new BigDecimal("7000"));
        Produto xbox = new Produto(2,"Xbox ONE", new BigDecimal("3250.99"));
        produtos.put(1,playtstation);
        produtos.put(2, xbox);
        return produtos;
    }

    public List<Produto> getAll() {
        return produtos.values().stream().toList();
    }

    public void save(Produto produto) {
        produto.setId(lastId);
        produtos.put(lastId++, produto);

    }
    public void delete(int id) {
        produtos.remove(id);
    }

    public Produto getById(int id) {
        return produtos.get(id);
    }

    public void update(int id, Produto produto) {
        produto.setId(id);
        produtos.replace(id, produto);
    }
}
