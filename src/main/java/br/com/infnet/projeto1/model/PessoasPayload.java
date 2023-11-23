package br.com.infnet.projeto1.model;

import lombok.Data;

import java.util.List;

@Data
public class PessoasPayload {
    private List<Pessoa> pessoas;
}
