package cl.dgac.inspector.mapper;

import java.util.ArrayList;
import java.util.List;

import cl.dgac.inspector.dtos.DtoModel;
import cl.dgac.inspector.model.InspectorModelo;

public class Mapper {

    public static InspectorModelo addModelo(DtoModel entity){
        InspectorModelo modelo = new InspectorModelo();
        modelo.setApellido(entity.apellido());
        modelo.setNombre(entity.nombre());
        modelo.setSapellido(entity.sapellido());
        modelo.setSnombre(entity.sapellido());
        modelo.setRut(entity.rut());
        modelo.setId(null);
        return modelo;
    }
    
    public static InspectorModelo update(Long id, DtoModel entity){
        InspectorModelo modelo = addModelo(entity);
        modelo.setId(id);
        return modelo;
    }
    public static DtoModel modelToDto(InspectorModelo entity){
        DtoModel dto = new DtoModel(entity.getNombre(),
            entity.getApellido(), entity.getSnombre(),
            entity.getSapellido(), entity.getRut(), entity.getId());
        return dto;
    }
    public static List<DtoModel> lsiatasDto (List<InspectorModelo> lista){
        List<DtoModel> dtos = new ArrayList<>();
        for (InspectorModelo mode : lista){
            dtos.add(modelToDto(mode));
        }
        return dtos;
    }
}
