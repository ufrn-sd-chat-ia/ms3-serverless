package br.imd.ufrn.ms3.function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

// @RefreshScope: permite mudar boletim.avaliacao.* no chat-configs e refletir
// aqui via POST /actuator/refresh, sem reiniciar o serviço.
@RefreshScope
@ConfigurationProperties(prefix = "boletim.avaliacao")
public class RegrasAvaliacaoProperties {

    private double frequenciaMinima = 75.0;
    private double mediaAprovacao = 7.0;
    private double mediaRecuperacao = 5.0;

    public double getFrequenciaMinima() {
        return frequenciaMinima;
    }

    public void setFrequenciaMinima(double frequenciaMinima) {
        this.frequenciaMinima = frequenciaMinima;
    }

    public double getMediaAprovacao() {
        return mediaAprovacao;
    }

    public void setMediaAprovacao(double mediaAprovacao) {
        this.mediaAprovacao = mediaAprovacao;
    }

    public double getMediaRecuperacao() {
        return mediaRecuperacao;
    }

    public void setMediaRecuperacao(double mediaRecuperacao) {
        this.mediaRecuperacao = mediaRecuperacao;
    }
}
