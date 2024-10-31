package med.voll.api.repositorys;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
    Paciente findPacienteById(Long id);
}
