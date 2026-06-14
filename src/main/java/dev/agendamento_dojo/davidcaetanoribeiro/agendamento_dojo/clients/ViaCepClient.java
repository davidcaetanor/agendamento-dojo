package dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.clients;

import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.dtos.input.EnderecoViaCepDto;
import dev.agendamento_dojo.davidcaetanoribeiro.agendamento_dojo.entities.vo.EnderecoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws", name = "viacepClient")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    EnderecoViaCepDto buscarEnderecoCep(@PathVariable String cep);
}
