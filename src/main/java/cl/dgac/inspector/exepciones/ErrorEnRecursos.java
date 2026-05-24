package cl.dgac.inspector.exepciones;

public class ErrorEnRecursos extends RuntimeException {
    public ErrorEnRecursos (String mensaje){
        super(mensaje);
    }
}
