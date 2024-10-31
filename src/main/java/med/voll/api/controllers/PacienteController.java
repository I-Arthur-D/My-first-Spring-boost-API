package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import med.voll.api.repositorys.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid CadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosPacientesAtualizados(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPaciente>> listarPacientes (@PageableDefault Pageable pageable) {
        var page =  repository.findAllByAtivoTrue(pageable).map(ListagemPaciente :: new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
        return ResponseEntity.ok(new DadosPacientesAtualizados(paciente));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente (@PathVariable long id) {
        var paciente = repository.getReferenceById(id);
        paciente.desativarPaciente(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente (@PathVariable long id) {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosPacientesAtualizados(paciente));
    }

}
