package cl.dgac.inspector.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.dgac.inspector.dtos.DtoModel;
import cl.dgac.inspector.exepciones.ErrorEnRecursos;
import cl.dgac.inspector.mapper.Mapper;
import cl.dgac.inspector.service.Servicios;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/v1.5/Inspector")
public class Controller {
    private final Servicios servicio;
    public Controller(Servicios servicio){
        this.servicio= servicio;
    }

    @GetMapping("/listar")   
    public ResponseEntity<List<DtoModel>> listar(){
        List<DtoModel> lista = servicio.listarInspector();
        return new ResponseEntity<List<DtoModel>>(lista,HttpStatus.OK);
    } 
    @GetMapping("/bucador")
    public ResponseEntity<List<DtoModel>> filtradoNombreApellido(@RequestParam(name = "nombre",required = false )String nombre,
    @RequestParam(name= "apellido",required = false) String apellido) {
        if ((nombre == null || nombre.isBlank()) && (apellido == null ||apellido.isBlank()) ){
            throw new ErrorEnRecursos("debe ingresar almenos un nombre o apellido");
        }else if (nombre == null || nombre.isBlank()){
            return new ResponseEntity<List<DtoModel>>(servicio.filtarPorApellido(apellido),HttpStatus.OK);
        }else if (apellido == null ||apellido.isBlank()){
            return new ResponseEntity<List<DtoModel>>(servicio.filtarpornombre(nombre),HttpStatus.OK);
        }else 
            return new ResponseEntity<List<DtoModel>>(servicio.filtarnombreYapelldio(nombre, apellido), HttpStatus.OK);
    }
    @GetMapping("/bucarRut")
    public ResponseEntity<DtoModel> filtradoPorRut(@RequestParam(name="rut") String rut){
        return new ResponseEntity<DtoModel>(servicio.filtaRut(rut),HttpStatus.OK);
    }
    @PostMapping("/nuevoInspector")
    public ResponseEntity<DtoModel> addinspector(@Valid@RequestBody DtoModel entity) {
        return new ResponseEntity<DtoModel>(servicio.save(Mapper.addModelo(entity)),HttpStatus.OK);
    }
    @PutMapping("/editar")
    public ResponseEntity<DtoModel> editarInspector(@RequestParam(name= "rut") String rut,@Valid @RequestBody DtoModel entity) {
        Long id = servicio.filtaRut(rut).id();
        return new ResponseEntity<DtoModel>(Mapper.modelToDto(Mapper.update(id, entity)),HttpStatus.OK);
    }
    
    

}
