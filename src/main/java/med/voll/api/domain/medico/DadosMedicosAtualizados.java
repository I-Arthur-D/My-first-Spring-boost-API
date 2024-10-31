package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosMedicosAtualizados(Long id , String nome, String email, String telefone, Especialidades especialidades, Endereco endereco) {
    public DadosMedicosAtualizados(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getEspecialidades(), medico.getEndereco());
    }
}
