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

import cl.dgac.inspector.dtos.DtoExepcion;
import jakarta.servlet.http.HttpServletRequest;

public class ExepcionesGlobales {
@ExceptionHandler(ErrorEnRecursos.class)
    public ResponseEntity<DtoExepcion> ErrorEnRecursos(ErrorEnRecursos ex,HttpServletRequest request){
        DtoExepcion error = new DtoExepcion(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "recurso no encontrado",
            ex.getMessage(),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);}
@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> exepcionesValicadionDto(MethodArgumentNotValidException ex){
        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String campo=((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo,mensaje);
        });
        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);}
@ExceptionHandler(Exception.class)
    public ResponseEntity<DtoExepcion> interlServErerror(Exception ex, HttpServletRequest request){
        DtoExepcion error = new DtoExepcion(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error interno en el servidor",
            "ocurrio un   error inesperado" +ex.getMessage(),
            request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DtoExepcion> manejarDuplicados(DataIntegrityViolationException ex, HttpServletRequest request){
        DtoExepcion error = new DtoExepcion(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            "Error duplicado",
            "El rut ingresado a se encuentra registrado ",
            request.getRequestURI()
        );
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
