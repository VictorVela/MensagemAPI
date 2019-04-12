package br.com.uniopet.mensagemapi.mensagemapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "MENSAGEM")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter @Setter
    private String mensagem;

    @Getter @Setter
    private Long idUsuario;

    @NonNull
    @Getter @Setter
    @Transient
    private Usuario usuario;
}
