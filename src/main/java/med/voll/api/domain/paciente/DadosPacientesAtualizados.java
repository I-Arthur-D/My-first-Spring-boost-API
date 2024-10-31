package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosPacientesAtualizados(Long id, String nome, String email, String cpf, Endereco endereco) {
    public DadosPacientesAtualizados(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
