package med.voll.api.controllers;

import med.voll.api.domain.paciente.Paciente;

public record DadosPacienteMedicoPacientes(long id, String nome) {
    public DadosPacienteMedicoPacientes(Paciente paciente) {
        this(paciente.getId(), paciente.getNome());
    }
}
