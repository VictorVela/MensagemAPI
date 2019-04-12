package br.com.uniopet.mensagemapi.mensagemapi.controller;

import br.com.uniopet.mensagemapi.mensagemapi.model.Mensagem;
import br.com.uniopet.mensagemapi.mensagemapi.repository.MensagemRepository;
import br.com.uniopet.mensagemapi.mensagemapi.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagem")
public class MensagensController {

    @Autowired
    MensagemService mensagemService;

    @GetMapping
    public List<Mensagem> findAll(){return mensagemService.findAll();}

    @GetMapping("/{id}")
    public Mensagem findById(@PathVariable Long id){return mensagemService.findById(id);}

    @PostMapping
    public String save(@RequestBody Mensagem mensagem) {return  mensagemService.saveOrUpdate(mensagem);}
}
