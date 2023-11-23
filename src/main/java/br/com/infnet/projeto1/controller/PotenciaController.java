package br.com.infnet.projeto1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/potencia")
public class PotenciaController {
    Logger LOGGER = LoggerFactory.getLogger(PotenciaController.class);
    @GetMapping("/quadradoOptional")
    public double quadrado(@RequestParam(required = false) Optional<Double> numero){
        return Math.pow(numero.orElseGet(() ->  0.0),2);
    }
    @GetMapping("/quadradoRequired")
    public double quadradoRequired(@RequestParam(required = true) double numero){
        return Math.pow(numero,2);
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

}
