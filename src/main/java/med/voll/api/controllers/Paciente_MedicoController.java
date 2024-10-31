package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.repositorys.MedicoRepository;
import med.voll.api.repositorys.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("pacienteMedico")
public class Paciente_MedicoController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPacienteMedico (@Valid @RequestBody DadosCadastroPacienteMedico dados) {
        var paciente = pacienteRepository.findPacienteById(dados.idPaciente());
        var medico = medicoRepository.findMedicoById(dados.idMedico());

        if (paciente == null || medico == null) {
            return ResponseEntity.badRequest().build();
        }else {
            paciente.getMedicos().add(medico);
            medico.getPacientes().add(paciente);

            pacienteRepository.save(paciente);
            medicoRepository.save(medico);
        }

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @GetMapping("/pacientes/{idPaciente}/medicos")
    public ResponseEntity pacienteMedicos(@PathVariable long idPaciente) {
        var paciente = pacienteRepository.getReferenceById(idPaciente);

        var medicos = paciente.getMedicos().stream().map(DadosMedicoPacienteMedicos::new);

        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/medicos/{idMedico}/pacientes")
    public ResponseEntity medicoPacientes(@PathVariable long idMedico) {
        var medico = medicoRepository.getReferenceById(idMedico);

        var pacientes = medico.getPacientes().stream().map(DadosPacienteMedicoPacientes::new);

        return ResponseEntity.ok(pacientes);
    }
}

