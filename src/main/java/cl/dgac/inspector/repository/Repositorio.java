package cl.dgac.inspector.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import cl.dgac.inspector.model.Modelo;

@Repository
public interface Repositorio extends JpaRepository<Modelo,Long> {
    @Query("SELECT i FROM Modelo i WHERE i.nombre ILIKE %:nombre%")
    List<Modelo>  findByNombre(@Param("nombre")  String nombre);
    Optional<Modelo> findByRut(String rut);
    @Query("SELECT i FROM Modelo i WHERE i.apellido ILIKE %:apellido%")
    List<Modelo> findByApellido(@Param( "apellido") String apellido);
    @Query("SELECT i FROM Modelo i WHERE i.nombre ILIKE %:nombre% And i.apellido ILIKE %:apellido%")
    List<Modelo> findByNombreAndApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);
}
