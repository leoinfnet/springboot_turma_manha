package br.com.infnet.projeto1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private int id;
    private String nome;
    private BigDecimal valor;
}
