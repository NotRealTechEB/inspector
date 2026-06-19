package cl.dgac.inspector.dtos;

import java.time.LocalDateTime;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoExepcion {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String mensaje;
    private Map<String, String> detalles;
    private String ruta;
    public DtoExepcion(LocalDateTime timestamp, int status, String error,String mensaje, Map<String, String> detalles, String ruta) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.ruta = ruta;
    }

}
