package cl.dgac.inspector.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoModel(
    @NotBlank(message = "el inspector deve tener nombre")
    @Size(min = 3 ,max = 25,message = "el nombre deve tener entre 3 a 25 caracteres")
    String nombre,
    @NotBlank(message = "el inspector deve tener apellido")
    @Size(min = 3 ,max = 25,message = "el apellido deve tener entre 3 a 25 caracteres")
    String apellido,
    @Size(min = 3 ,max = 25,message = "el segundo nombre deve tener entre 3 a 25 caracteres")
    String snombre,
    @Size(min = 3 ,max = 25,message = "el apellido materno deve  tener entre 3 a 25 caracteres")
    String sapellido,
    @NotBlank(message = "el inspector deve tener rut")
    String rut,
    Long id
) {}
