package br.imd.ufrn.ms3.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ValidarNotaFunctionConfig {

    // Função pura e sem estado: mesma entrada sempre produz a mesma saída, sem
    // acessar banco ou qualquer outro serviço — é o que torna isso um bom
    // candidato a FaaS (Spring Cloud Function), diferente do MS1 (tem estado
    // no Postgres) e do MS2 (depende da OpenAI).
    @Bean
    public Function<NotaInput, NotaResultado> validarNota(RegrasAvaliacaoProperties regras) {
        return input -> {
            if (input.valor() == null || input.valor() < 0 || input.valor() > 10) {
                return new NotaResultado(input.valor(), input.frequencia(), "NOTA_INVALIDA", false,
                        "Nota deve estar entre 0 e 10");
            }

            double frequencia = input.frequencia() != null ? input.frequencia() : 100.0;

            if (frequencia < regras.getFrequenciaMinima()) {
                return new NotaResultado(input.valor(), frequencia, "REPROVADO_POR_FALTA", false,
                        "Frequência (%.1f%%) abaixo do mínimo de %.1f%%".formatted(frequencia, regras.getFrequenciaMinima()));
            }

            if (input.valor() >= regras.getMediaAprovacao()) {
                return new NotaResultado(input.valor(), frequencia, "APROVADO", true, "Aprovado direto");
            }

            if (input.valor() >= regras.getMediaRecuperacao()) {
                return new NotaResultado(input.valor(), frequencia, "RECUPERACAO", false,
                        "Nota entre %.1f e %.1f: deve fazer recuperação".formatted(regras.getMediaRecuperacao(), regras.getMediaAprovacao()));
            }

            return new NotaResultado(input.valor(), frequencia, "REPROVADO", false,
                    "Nota abaixo de %.1f: reprovado por nota".formatted(regras.getMediaRecuperacao()));
        };
    }
}
