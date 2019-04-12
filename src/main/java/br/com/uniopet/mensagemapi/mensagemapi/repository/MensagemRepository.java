package br.com.uniopet.mensagemapi.mensagemapi.repository;

import br.com.uniopet.mensagemapi.mensagemapi.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
