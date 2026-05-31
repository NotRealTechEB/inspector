package cl.dgac.inspector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dgac.inspector.dtos.DtoModel;
import cl.dgac.inspector.exepciones.ErrorEnRecursos;
import cl.dgac.inspector.mapper.Mapper;
import cl.dgac.inspector.model.Modelo;
import cl.dgac.inspector.repository.Repositorio;

@Service
public class Servicios {
    
    @Autowired
    private Repositorio  repo;

    public List<DtoModel> filtarpornombre(String name){
        List<Modelo> lista = repo.findByNombre(name);
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("inspectaor inexistente con ese nombre");
        }
        return Mapper.lsiatasDto(lista);
    
    }
    public List<DtoModel> filtarPorApellido(String apellido){
        List<Modelo> lista = repo.findByApellido(apellido);
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("inspectaor inexistente con ese nombre");
        }
        return Mapper.lsiatasDto(lista);
    }

    public List<DtoModel> listarInspector(){
        List<Modelo> lista =repo.findAll();
        if(lista.isEmpty()){
            throw new ErrorEnRecursos("no existe inspectores");
        }
        return Mapper.lsiatasDto(lista);
    }

    public List<DtoModel> filtarnombreYapelldio(String name, String apellido){
        List<Modelo>lista = repo.findByNombreAndApellido(name, apellido);
        if (lista.isEmpty()){
            throw new ErrorEnRecursos("inspector inexistente con esa combinacion de nombre y apellido");
        }
        return Mapper.lsiatasDto(lista);
    }

    public DtoModel filtaRut (String rut){
        Modelo modelo = repo.findByRut(rut).orElseThrow(
            ()-> new ErrorEnRecursos("el rut "+ rut +" no esta afiliado a ningun inspector"));
        return Mapper.modelToDto(modelo);
    }

    public DtoModel save(Modelo entity){
        repo.save(entity);
        return Mapper.modelToDto(entity) ;
    }

    public String delete (String rut ){
        DtoModel model = filtaRut(rut);
        repo.deleteById(model.id());
        return "El inspector "+ model.nombre() + "fue eliminado";
    }

    public DtoModel validarId(Long id){
        Modelo model =repo.findById(id).orElseThrow( ()->new ErrorEnRecursos("Empresa con el id " + id + " no encontrada"));
        return Mapper.modelToDto(model) ;
    }
}
