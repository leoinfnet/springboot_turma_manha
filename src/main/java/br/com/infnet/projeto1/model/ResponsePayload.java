package br.com.infnet.projeto1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponsePayload {
    List<String> nomes;
    LocalDateTime dataHora;
}
