package cl.dgac.inspector.Mapper;

import java.util.ArrayList;
import java.util.List;

import cl.dgac.inspector.dtos.DtoModel;
import cl.dgac.inspector.model.Modelo;

public class Mapper {

    public static Modelo addModelo(DtoModel entity){
        Modelo modelo = new Modelo();
        modelo.setApellido(entity.apellido());
        modelo.setNombre(entity.nombre());
        modelo.setSapellido(entity.sapellido());
        modelo.setSnombre(entity.sapellido());
        modelo.setRut(entity.rut());
        modelo.setId(null);
        return modelo;
    }
    
    public static Modelo update(Long id, DtoModel entity){
        Modelo modelo = addModelo(entity);
        modelo.setId(id);
        return modelo;
    }
    public static DtoModel modelToDto(Modelo entity){
        DtoModel dto = new DtoModel(entity.getNombre(),
            entity.getApellido(), entity.getSnombre(),
            entity.getSapellido(), entity.getRut(), entity.getId());
        return dto;
    }
    public static List<DtoModel> lsiatasDto (List<Modelo> lista){
        List<DtoModel> dtos = new ArrayList<>();
        for (Modelo mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }
}
