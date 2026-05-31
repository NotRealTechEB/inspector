package cl.dgac.inspector.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import cl.dgac.inspector.model.Modelo;

@Repository
public interface Repositorio extends JpaRepository<Modelo,Long> {
    @Query("SELECT i FROM Modelo i WHERE i.nombre ILIKE %:nombre%")
    List<Modelo>  findByNombre(@RequestParam(name = "nombre")  String nombre);
    Optional<Modelo> findByRut(String rut);
    @Query("SELECT i FROM Modelo i WHERE i.apellido ILIKE %:apellido%")
    List<Modelo> findByApellido(@RequestParam(name = "apellido") String apellido);
    @Query("SELECT i FROM Modelo i WHERE i.nombre ILIKE %:nombre% And i.apellido")
    List<Modelo> findByNombreAndApellido(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "apellido") String apellido);
}
