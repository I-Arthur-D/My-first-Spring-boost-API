package med.voll.api.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controllers.DadosCadastroPacienteMedico;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;

import java.util.HashSet;
import java.util.Set;

@Table (name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidades especialidades;

    @Embedded
    private Endereco endereco;
    private boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "paciente_medico",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_paciente")
    )
    private Set<Paciente> pacientes = new HashSet<>();

    public Medico(CadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidades = dados.especialidades();
        this.endereco = new Endereco(dados.endereco());
    }

    public Medico(@Valid DadosCadastroPacienteMedico dados) {
       this.id = dados.idMedico();
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
        if (dados.especialidades() != null) {
            this.especialidades = dados.especialidades();
        }
    }

    public void desativarMedico(long id) {
        this.ativo = false;
    }
}
