package br.imd.ufrn.ms3.function;

public record NotaResultado(
        Double valor,
        Double frequencia,
        String situacao,
        boolean aprovado,
        String mensagem) {
}
