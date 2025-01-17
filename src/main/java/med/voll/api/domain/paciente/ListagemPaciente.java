package med.voll.api.domain.paciente;

public record ListagemPaciente(Long id ,String nome, String email, String cpf) {

    public ListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
