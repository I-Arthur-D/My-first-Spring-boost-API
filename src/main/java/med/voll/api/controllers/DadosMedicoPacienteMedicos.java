package med.voll.api.controllers;

import med.voll.api.domain.medico.Medico;

public record DadosMedicoPacienteMedicos(long id, String nome) {
    public DadosMedicoPacienteMedicos(Medico medico) {
        this(medico.getId(), medico.getNome());
    }
}
