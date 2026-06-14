package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendamentoDojoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendamentoDojoApplication.class, args);
    }

}
