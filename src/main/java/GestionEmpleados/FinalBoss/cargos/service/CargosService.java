package GestionEmpleados.FinalBoss.cargos.service;

import GestionEmpleados.FinalBoss.cargos.dto.CargosDTO;
import GestionEmpleados.FinalBoss.cargos.entity.CargosEntity;
import GestionEmpleados.FinalBoss.cargos.repository.CargosRepository;
import GestionEmpleados.FinalBoss.departamentos.dto.DepartamentosDTO;
import GestionEmpleados.FinalBoss.departamentos.entity.DepartamentosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CargosService {

    private final CargosRepository repo;

    private CargosDTO convertirADTO(CargosEntity entity){
        CargosDTO dto = new CargosDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setSalarioBase(entity.getSalarioBase());
        return dto;
    }

    public List<CargosDTO> obtenerTodos(){
        //Obtener todos los registros
        List<CargosEntity> entidades = repo.findAll();
        //Convertir a dto
        List<CargosDTO> dtos = new ArrayList<>();
        for (CargosEntity entity : entidades){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

}
