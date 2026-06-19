package cl.dgac.inspector.exepciones;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.dgac.inspector.dtos.DtoExepcion;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExepcionesGlobales {
    private ResponseEntity<DtoExepcion> buildResponse(
        HttpStatus status,
        String mnesaje,
        String ruta,
        Map<String, String> detalles){
        DtoExepcion dto = new DtoExepcion(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            mnesaje,
            detalles,
            ruta
        );
        return  ResponseEntity.status(status).body(dto);
    }
    @ExceptionHandler(ErrorEnRecursos.class)
    public ResponseEntity<DtoExepcion> ErrorEnRecursos(ErrorEnRecursos ex,HttpServletRequest request){
    return buildResponse(
        HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI(),null);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoExepcion> exepcionesValicadionDto(MethodArgumentNotValidException ex,HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String campo=((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo,mensaje);
        });
        return buildResponse(HttpStatus.BAD_REQUEST, 
            "elementos en el json tienen problemas",
            request.getRequestURI(),
            errores);
        }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoExepcion> interlServErerror(Exception ex, HttpServletRequest request){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,"ocurrio un   error inesperado " + ex.getMessage(),request.getRequestURI(), null);
        }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DtoExepcion> manejarDuplicados(DataIntegrityViolationException ex, HttpServletRequest request){
        return buildResponse(HttpStatus.CONFLICT,"Error duplicado",request.getRequestURI(),null);
    }
}
