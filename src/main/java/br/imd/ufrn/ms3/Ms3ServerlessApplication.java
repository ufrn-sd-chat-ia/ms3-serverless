package br.imd.ufrn.ms3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import br.imd.ufrn.ms3.function.RegrasAvaliacaoProperties;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(RegrasAvaliacaoProperties.class)
public class Ms3ServerlessApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ms3ServerlessApplication.class, args);
    }
}
