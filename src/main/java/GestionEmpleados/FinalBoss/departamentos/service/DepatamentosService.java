package GestionEmpleados.FinalBoss.departamentos.service;

import GestionEmpleados.FinalBoss.departamentos.dto.DepartamentosDTO;
import GestionEmpleados.FinalBoss.departamentos.entity.DepartamentosEntity;
import GestionEmpleados.FinalBoss.departamentos.repository.DepartamentosRepository;
import GestionEmpleados.FinalBoss.empleados.repository.EmpleadosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepatamentosService {

    private final DepartamentosRepository repo;
    private final EmpleadosRepository repoEmpleados;

    private DepartamentosEntity convertirAEntity(DepartamentosDTO dto){
        DepartamentosEntity entity = new DepartamentosEntity();
        entity.setNombre(dto.getNombre());
        entity.setUbicacion(dto.getUbicacion());
        return entity;
    }

    private DepartamentosDTO convertirADTO(DepartamentosEntity entity){
        DepartamentosDTO dto = new DepartamentosDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setUbicacion(entity.getUbicacion());
        return dto;
    }

    public DepartamentosDTO nuevoDepartamento(@Valid DepartamentosDTO dto){
        DepartamentosEntity entity = convertirAEntity(dto);
        //Guardar el registo en la base de datos
        DepartamentosEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    public List<DepartamentosDTO> obtenerTodos(){
        //Obtener todos los registros
        List<DepartamentosEntity> entidades = repo.findAll();
        //Convertir a dto
        List<DepartamentosDTO> dtos = new ArrayList<>();
        for (DepartamentosEntity entity : entidades){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

    public DepartamentosDTO obtenerPorId(Long id){
        Optional<DepartamentosEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()){
            return convertirADTO(entidadOpcional.get());
        }
        return null;
    }

    public boolean eliminarRegistro(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        if (repoEmpleados.existsByDepartamento_Id(id)){
            throw new RuntimeException("No se puede eliminar el departamento porque tiene empleados asignados.");
        }
        repo.deleteById(id);
        return true;
    }

    public DepartamentosDTO actualizarRegistro(@Valid DepartamentosDTO dto, Long id){

        Optional<DepartamentosEntity> registroExistente = repo.findById(id);
        if (registroExistente.isPresent()){
            DepartamentosEntity entity = registroExistente.get();
            entity.setNombre(dto.getNombre());
            entity.setUbicacion(dto.getUbicacion());
            DepartamentosEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }

        return null;
    }
}
