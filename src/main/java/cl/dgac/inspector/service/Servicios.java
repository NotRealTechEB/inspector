package cl.dgac.inspector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dgac.inspector.exepciones.ErrorEnRecursos;
import cl.dgac.inspector.model.Modelo;
import cl.dgac.inspector.repository.Repositorio;

@Service
public class Servicios {
    
    @Autowired
    private Repositorio  repo;

    public List<Modelo> filtarpornombre(String name){
        List<Modelo> lista = repo.findByNombre(name);
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("inspectaor inexistente con ese nombre");
        }
        return lista;
    
    }
    public List<Modelo> filtarPorApellido(String apellido){
        List<Modelo> lista = repo.findByApellido(apellido);
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("inspectaor inexistente con ese nombre");
        }
        return lista;
    }

    public List<Modelo> listarInspector(){
        List<Modelo> lista =repo.findAll();
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("no existe inspectores");
        }
        return lista;
    }

    public List<Modelo> filtarnombreYapelldio(String name, String apellido){
        List<Modelo>lista = repo.findByNombreAndApelldo(name, apellido);
        if (lista.isEmpty()){
            throw new ErrorEnRecursos("inspector inexistente con esa combinacion de nombre y apellido");
        }
        return lista;
    }

    public Modelo filtaRut (String rut){
        Modelo modelo = repo.findByRut(rut).orElseThrow(
            ()-> new ErrorEnRecursos("el rut "+ rut +" no esta afiliado a ningun inspector"));
        return modelo;
    }

    public Modelo save(Modelo entity){
        repo.save(entity);
        return entity;
    }

    public void delete (Long id){
        repo.deleteById(id);
    }

    public Modelo validarId(Long id){
        Modelo model =repo.findById(id).orElseThrow( ()->new ErrorEnRecursos("Empresa con el id " + id + " no encontrada"));
        return model ;
    }
}
