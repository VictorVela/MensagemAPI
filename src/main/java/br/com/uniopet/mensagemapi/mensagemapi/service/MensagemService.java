package br.com.uniopet.mensagemapi.mensagemapi.service;

import br.com.uniopet.mensagemapi.mensagemapi.model.Mensagem;
import br.com.uniopet.mensagemapi.mensagemapi.model.Usuario;
import br.com.uniopet.mensagemapi.mensagemapi.repository.MensagemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MensagemService {

    @Autowired
    MensagemRepository mensagemRepository;

    RestTemplate restTemplate = new RestTemplate();

    public List<Mensagem> findAll() {return mensagemRepository.findAll();}

    public Mensagem findById(Long id){
        Mensagem mensagem = mensagemRepository.findById(id).get();

        Usuario usuario = restTemplate.getForObject("http://localhost:9998/usuarios/" + mensagem.getIdUsuario(), Usuario.class);
        mensagem.setUsuario(usuario);

        return mensagem;
    }

    public String saveOrUpdate(Mensagem mensagem){
        if (mensagem.getId() != null){
            mensagemRepository.save(mensagem);
            return "Mensagem atualizada";
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> bodyParamMap = new HashMap<String, String>();

        bodyParamMap.put("nomeUsuario", mensagem.getUsuario().getNomeUsuario());

        String reqBodyData = null;
        try {
            reqBodyData = new ObjectMapper().writeValueAsString(bodyParamMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> requestEnty = new HttpEntity<>(reqBodyData, headers);
        ResponseEntity<Object> result = restTemplate.postForEntity("http://localhost:9998/usuarios/", requestEnty, Object.class);

        mensagem.setIdUsuario(Long.parseLong(result.getBody().toString()));

        mensagemRepository.save(mensagem);

        return "Mensagem salva";
    }

    public String deleteById(Mensagem mensagem) {
        mensagemRepository.deleteById(mensagem.getId());

        if (!mensagemRepository.existsById(mensagem.getId())) {
            return "Deletado com sucesso";
        }

        return "Erro!!!!";
    }
}
