package br.com.infnet.projeto1.controller;

import br.com.infnet.projeto1.model.Mensagem;
import br.com.infnet.projeto1.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/potencia")
public class PotenciaController {
    Logger LOGGER = LoggerFactory.getLogger(PotenciaController.class);
    @GetMapping("/quadrado")
    public double quadrado(@RequestParam(required = false) Optional<Double> numero){
        return Math.pow(numero.orElseGet(() ->  0.0),2);
    }
    @GetMapping("/n")
    public double potencia(@RequestParam(required = true) double numero,
                           @RequestParam(required = false, defaultValue = "2") int potencia){
        return Math.pow(numero,potencia);
    }
    @GetMapping("/lista")
    public List<Double> listaAPotencia(@RequestParam  List<Double> valores,
                                       @RequestParam(required = false ,defaultValue = "2") int potencia){
        System.out.println(valores);
        List<Double> potencias = valores.stream()
                .map(valor -> Math.pow(valor, potencia))
                .toList();
        return potencias;
    }
    @GetMapping("/variosParams")
    public ResponseEntity variosParametros(@RequestParam Map<String,String> allParams){
        List<String> lista = List.of("Leonardo", "Joao", "Andre", "Pedro" ,"Ze", "Joaquim");
        String size = allParams.get("size");
        if(size != null){
            try{
                lista = lista.subList(0, Integer.parseInt(size));
            }catch (IndexOutOfBoundsException ex){
                Mensagem valorInvalido = new Mensagem("Valor invalido para a lista", LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(valorInvalido);
            }
        }
        String sort = allParams.get("sort");
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                LOGGER.info("ordenando ascendente");
                lista = lista.stream().sorted().toList();

            }else if(sort.equalsIgnoreCase("desc")) {
                LOGGER.info("ordenando decrescente");
                lista = lista.stream().sorted(Comparator.reverseOrder()).toList();
            }else {
                LOGGER.info("Sem ordenacao");
            }
        }else{
            LOGGER.info("Sem ordenacao");
        }


        Payload payload = new Payload(lista, LocalDateTime.now());
        return ResponseEntity.ok(payload);

    }
}
