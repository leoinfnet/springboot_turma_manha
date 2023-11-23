package br.com.infnet.projeto1.util;

import br.com.infnet.projeto1.controller.NomeController;
import br.com.infnet.projeto1.model.Pessoa;
import br.com.infnet.projeto1.model.PessoasPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NomesUtil {
    Logger LOGGER = LoggerFactory.getLogger(NomesUtil.class);
    List<String> nomes = getNomes();
    private List<String> getNomes(){
        ArrayList<String> nomes = new ArrayList<>();
        nomes.addAll(List.of("Leonardo", "Joao", "Andre", "Pedro" ,"Ze", "Joaquim"));
        return nomes;

    }
    public void insertAll(List<Pessoa> pessoas){
        pessoas.forEach(pessoa -> nomes.add(pessoa.getNome()));

    }
    public void deleteAll(List<String> nomesToRemove){
        nomesToRemove.stream().forEach(nome -> {
            this.nomes.remove(nome);
        });
        LOGGER.info("DELETE -> nomes " + nomes);
    }

}
