package med.voll.api.domain.medico;

public record ListagemMedico(Long id,String nome, String email, Especialidades especialidades) {

    public ListagemMedico(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getEspecialidades());
    }
}
