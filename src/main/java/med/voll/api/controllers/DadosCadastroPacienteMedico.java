package med.voll.api.controllers;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPacienteMedico(
        @NotNull
        Long idPaciente,
        @NotNull
        Long idMedico
    ){
}
