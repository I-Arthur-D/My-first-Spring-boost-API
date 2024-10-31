package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controllers.DadosCadastroPacienteMedico;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Medico;

import java.util.HashSet;
import java.util.Set;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    @ManyToMany(mappedBy = "pacientes")
    private Set<Medico> medicos = new HashSet<>();

    public Paciente(@Valid DadosCadastroPacienteMedico dados) {
        this.id = dados.idPaciente();
    }

    public Paciente(@Valid CadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }



    public void atualizarPaciente(@Valid DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void desativarPaciente(Long id) {
        this.ativo = false;
    }
}
