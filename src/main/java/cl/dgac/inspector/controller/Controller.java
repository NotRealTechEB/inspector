package cl.dgac.inspector.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cl.dgac.inspector.dtos.DtoModel;
import cl.dgac.inspector.exepciones.ErrorEnRecursos;
import cl.dgac.inspector.mapper.Mapper;
import cl.dgac.inspector.service.InspectorServicios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1.5/Inspector")
@Tag(name= "Inspector", description = "Esta api esta encargada de comunicarse con la BD para hacer el crud basico y filtrados como rut nombre etc")
public class Controller {
    private final InspectorServicios servicio;
    
    public Controller(InspectorServicios servicio){
        this.servicio= servicio;
    }

    @GetMapping("/listar")   
    @Operation(summary = "obtener todos los inspectores", description = "lista todos los inspectores")
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun inspector")})
    public ResponseEntity<List<DtoModel>> listar(){
        List<DtoModel> lista = servicio.listarInspector();
        return new ResponseEntity<List<DtoModel>>(lista,HttpStatus.OK);
    } 
    @GetMapping("/bucador")
    @Operation(summary = "obtener lista de inspectores", description = "ingresar un nombre o apellido u ambos traera similares o tirar una execion si no existen  ")
        @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun inspector con ese nombre o apeliido o combinacion de estos")})
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
    @Operation(summary = "obtener un inspector", description = "obtene el isnpector que tenga ese rut asigando")
    @Parameter(description = "necesita un rut que este en la bd", required = true )
    @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "ningun inspector tiene ese rut")})
    public ResponseEntity<DtoModel> filtradoPorRut(@RequestParam(name="rut", required = true) String rut){
        return new ResponseEntity<DtoModel>(servicio.filtaRut(rut),HttpStatus.OK);
    }
    @PostMapping("/nuevoInspector")
    @Operation(summary = "Crea un inspector", description = "crea un ispector enviando el json correcto")
        @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
    @ApiResponse(responseCode = "404", description = "lista vacia es decir ningun inspector")})
    public ResponseEntity<DtoModel> addinspector(@Valid
        @RequestBody (
        description = "Datos de la solicitud a crear",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoModel.class),
            examples = @ExampleObject(
            name = "Ejemplo de Inspector",
            summary = "Datos completos de un inspector",
            value = """
            {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Perez",
                "snombre": "Alberto",
                "sapellido": "Gomez",
                "rut": "12.345.678-9"
            }
            """)
    )) @org.springframework.web.bind.annotation.RequestBody DtoModel entity) {
        return new ResponseEntity<DtoModel>(servicio.save(Mapper.addModelo(entity)),HttpStatus.OK);
    }
    @PutMapping("/editar")
    @Operation(summary = "obtener un inspector", description = "ingresas el rut para tener el isnpector luego lo cambioas mendiante el json correcto")
    public ResponseEntity<DtoModel> editarInspector(@RequestParam(name= "rut") String rut,@Valid  
    @RequestBody (
        description = "Datos de la solicitud a crear",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = DtoModel.class),
            examples = @ExampleObject(
            name = "Ejemplo de Inspector",
            summary = "Datos completos de un inspector",
            value = """
            {
                "id": 1,
                "nombre": "Juan",
                "apellido": "Perez",
                "snombre": "Alberto",
                "sapellido": "Gomez",
                "rut": "12.345.678-9"
            }
            """)
    )) @org.springframework.web.bind.annotation.RequestBody
    DtoModel entity) {
        Long id = servicio.filtaRut(rut).id();
        return new ResponseEntity<DtoModel>(Mapper.modelToDto(Mapper.update(id, entity)),HttpStatus.OK);
    }
    
    

}
