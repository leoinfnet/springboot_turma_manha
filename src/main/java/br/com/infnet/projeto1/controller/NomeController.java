package br.com.infnet.projeto1.controller;

import br.com.infnet.projeto1.model.Mensagem;
import br.com.infnet.projeto1.model.PessoasPayload;
import br.com.infnet.projeto1.model.ResponsePayload;
import br.com.infnet.projeto1.util.NomesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nomes")
public class NomeController {
    @Autowired
    NomesUtil nomesUtil;
    Logger LOGGER = LoggerFactory.getLogger(NomeController.class);
    List<String> nomes = getNomes();
    private List<String> getNomes(){
        ArrayList<String> nomes = new ArrayList<>();
        nomes.addAll(List.of("Leonardo", "Joao", "Andre", "Pedro" ,"Ze", "Joaquim"));
        return nomes;

    }
    @PostMapping("/insert")
    public void insertName(@RequestBody PessoasPayload payload) throws JsonProcessingException {
        LOGGER.info("POST -> Insert Name: " + payload);
        nomesUtil.insertAll(payload.getPessoas());
        payload.getPessoas().forEach(pessoa -> nomes.add(pessoa.getNome()));

    }
    @GetMapping("/delete")
    public void deleteNames(@RequestParam List<String> nomesToRemove) throws JsonProcessingException {
        nomesUtil.deleteAll(nomesToRemove);
        LOGGER.info("DELETE -> nomes " + nomes);
    }
    @GetMapping("/search")
    public ResponseEntity searchNomes(@RequestParam Map<String,String> allParams){
        String query = allParams.get("query");
        if(query != null){
            nomes = nomes.stream().filter(nome -> nome.contains(query)).toList();
        }
        String size = allParams.get("size");
        if(size != null){
            try{
                nomes = nomes.subList(0, Integer.parseInt(size));
            }catch (IndexOutOfBoundsException ex){
                Mensagem valorInvalido = new Mensagem("Valor invalido para a nomes", LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(valorInvalido);
            }
        }
        String sort = allParams.get("sort");
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                LOGGER.info("ordenando ascendente");
                nomes = nomes.stream().sorted().toList();

            }else if(sort.equalsIgnoreCase("desc")) {
                LOGGER.info("ordenando decrescente");
                nomes = nomes.stream().sorted(Comparator.reverseOrder()).toList();
            }else {
                LOGGER.info("Sem ordenacao");
            }
        }else{
            LOGGER.info("Sem ordenacao");
        }
        ResponsePayload payload = new ResponsePayload(nomes, LocalDateTime.now());
        return ResponseEntity.ok(payload);
    }

}
