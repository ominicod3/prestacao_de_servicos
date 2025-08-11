package com.prestacao_de_servicos.prestacao.model.servico;

import com.prestacao_de_servicos.prestacao.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    private Double preco;

    private String imagemUrl; // link da imagem no S3, Cloudinary, etc.

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;
}