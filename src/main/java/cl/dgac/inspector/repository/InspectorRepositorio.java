package cl.dgac.inspector.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import cl.dgac.inspector.model.InspectorModelo;

public interface InspectorRepositorio extends JpaRepository<InspectorModelo,Long> {
    @Query("SELECT i FROM InspectorModelo i WHERE i.nombre ILIKE %:nombre%")
    List<InspectorModelo>  findByNombre(@Param("nombre")  String nombre);
    Optional<InspectorModelo> findByRut(String rut);
    @Query("SELECT i FROM InspectorModelo i WHERE i.apellido ILIKE %:apellido%")
    List<InspectorModelo> findByApellido(@Param( "apellido") String apellido);
    @Query("SELECT i FROM InspectorModelo i WHERE i.nombre ILIKE %:nombre% And i.apellido ILIKE %:apellido%")
    List<InspectorModelo> findByNombreAndApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);
}
