package cl.dgac.inspector.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.dgac.inspector.model.Modelo;

@Repository
public interface Repositorio extends JpaRepository<Modelo,Long> {
    List<Modelo>  findByNombre(String nombre);
    Optional<Modelo> findByRut(String rut);
    List<Modelo> findByApellido(String apellido);
    List<Modelo> findByNombreAndApellido(String nombre, String apellido);
}
