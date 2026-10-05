package GestionEmpleados.FinalBoss.empleados.service;

import GestionEmpleados.FinalBoss.cargos.entity.CargosEntity;
import GestionEmpleados.FinalBoss.cargos.repository.CargosRepository;
import GestionEmpleados.FinalBoss.departamentos.entity.DepartamentosEntity;
import GestionEmpleados.FinalBoss.departamentos.repository.DepartamentosRepository;
import GestionEmpleados.FinalBoss.empleados.dto.EmpleadosDTO;
import GestionEmpleados.FinalBoss.empleados.entity.EmpleadosEntity;
import GestionEmpleados.FinalBoss.empleados.repository.EmpleadosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadosService {

    private final EmpleadosRepository repo;
    private final DepartamentosRepository repoDepartamentos;
    private final CargosRepository repoCargos;

    private EmpleadosEntity convertirAEntity(EmpleadosDTO dto){
        EmpleadosEntity entity = new EmpleadosEntity();
        entity.setNombre(dto.getNombre());
        entity.setCorreo(dto.getCorreo());
        entity.setTelefono(dto.getTelefono());
        if (dto.getIdDepartamento() != null){
            DepartamentosEntity objDepartamento = repoDepartamentos.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado."));
            entity.setDepartamento(objDepartamento);
        }
        if (dto.getIdCargo() != null){
            CargosEntity objCargo = repoCargos.findById(dto.getIdCargo()).orElseThrow(() -> new RuntimeException("Cargo no encontrado."));
            entity.setCargo(objCargo);
        }
        return entity;
    }

    private EmpleadosDTO convertirADTO(EmpleadosEntity entity){
        EmpleadosDTO dto = new EmpleadosDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setCorreo(entity.getCorreo());
        dto.setTelefono(entity.getTelefono());
        dto.setIdDepartamento(entity.getDepartamento().getId());
        dto.setIdCargo(entity.getCargo().getId());
        return dto;
    }

    public EmpleadosDTO nuevoEmpleado(@Valid EmpleadosDTO dto){
        EmpleadosEntity entity = convertirAEntity(dto);
        //Guardar el registo en la base de datos
        EmpleadosEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    public List<EmpleadosDTO> obtenerTodos(){
        //Obtener todos los registros
        List<EmpleadosEntity> entidades = repo.findAll();
        //Convertir a dto
        List<EmpleadosDTO> dtos = new ArrayList<>();
        for (EmpleadosEntity entity : entidades){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

    public EmpleadosDTO obtenerPorId(Long id){
        Optional<EmpleadosEntity> entidadOpcional = repo.findById(id);
        if (entidadOpcional.isPresent()){
            return convertirADTO(entidadOpcional.get());
        }
        return null;
    }

    public boolean eliminarRegistro(Long id){
        if (!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public EmpleadosDTO actualizarRegistro(@Valid EmpleadosDTO dto, Long id){

        Optional<EmpleadosEntity> registroExistente = repo.findById(id);
        if (registroExistente.isPresent()){
            EmpleadosEntity entity = registroExistente.get();
            entity.setNombre(dto.getNombre());
            entity.setCorreo(dto.getCorreo());
            entity.setTelefono(dto.getTelefono());
            if (dto.getIdDepartamento() != null){
                DepartamentosEntity objDepartamento = repoDepartamentos.findById(dto.getIdDepartamento()).orElseThrow(() -> new RuntimeException("Departamento no encontrado."));
                entity.setDepartamento(objDepartamento);
            }
            if (dto.getIdCargo() != null){
                CargosEntity objCargo = repoCargos.findById(dto.getIdCargo()).orElseThrow(() -> new RuntimeException("Cargo no encontrado."));
                entity.setCargo(objCargo);
            }
            EmpleadosEntity entitySave = repo.save(entity);
            return convertirADTO(entitySave);
        }
        return null;
    }
}
