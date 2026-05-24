package cl.dgac.inspector.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.dgac.inspector.model.Modelo;

public interface Repositorio extends JpaRepository<Modelo,Long> {
    List<Modelo>  findByNombre(String nombre);
    Optional<Modelo> findByRut(String Rut);
    List<Modelo> findByApellido(String apellido);
    List<Modelo> findByNombreAndApelldo(String nombre,String apellido);
}
