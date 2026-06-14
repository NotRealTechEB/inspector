package cl.dgac.inspector.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoModel(
    @Schema(description = "nombre del insopector")
    @NotBlank(message = "el inspector deve tener nombre")
    @Size(min = 3 ,max = 25,message = "el nombre deve tener entre 3 a 25 caracteres")
    String nombre,
    @Schema(description = "apellido del insopector")
    @NotBlank(message = "el inspector deve tener apellido")
    @Size(min = 3 ,max = 25,message = "el apellido deve tener entre 3 a 25 caracteres")
    String apellido,
    @Schema(description = "segundo nombre del insopector, puede ser nulo")
    @Size(min = 3 ,max = 25,message = "el segundo nombre deve tener entre 3 a 25 caracteres")
    String snombre,
    @Schema(description = "segundo apellido del insopector, puede ser nulo")
    @Size(min = 3 ,max = 25,message = "el apellido materno deve  tener entre 3 a 25 caracteres")
    String sapellido,
    @Schema(description = "rut del inspector  ejemplo 12345678-9")
    @NotBlank(message = "el inspector deve tener rut")
    @Size(min = 10 ,max = 15,message = "el rut debee  tener entre 10 a 15 caracteres ")
    String rut,
    Long id
) {}
