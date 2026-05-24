package cl.dgac.inspector.dtos;

import java.time.LocalDateTime;

public record DtoExepcion(
    LocalDateTime fecha,
    int codigoHttp,
    String error,
    String mensaje,
    String ruta
){
}
