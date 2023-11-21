package br.com.infnet.projeto1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    private String codigo;
    private LocalDateTime dataHora;
}
