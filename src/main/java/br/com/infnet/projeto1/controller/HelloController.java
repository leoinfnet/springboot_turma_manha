package br.com.infnet.projeto1.controller;

import br.com.infnet.projeto1.model.Mensagem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public ResponseEntity<Mensagem> hello(){
        Map<String, String> mensagem = Map.of("Mensagem", "Bem vindo");
        Mensagem mensagem1 = new Mensagem();
        HttpStatus status = HttpStatus.OK;
        System.out.println("Executando o método hello");
        try{
            int i = 7 / 0;
            mensagem1.setCodigo("Sucesso!");
            mensagem1.setDataHora(LocalDateTime.now());
        }catch (ArithmeticException ex){
            mensagem1.setCodigo("Erro!");
            mensagem1.setDataHora(LocalDateTime.now());
            //status = HttpStatus.INTERNAL_SERVER_ERROR;
            status = HttpStatus.CONFLICT;
        }
        ResponseEntity<Mensagem> response = ResponseEntity.status(status).body(mensagem1);
        return response;
    }
    @DeleteMapping
    public void helloDelete(){
        System.out.println("Chamando o metodo delete");
    }

}
