package GestionEmpleados.FinalBoss.empleados.controller;

import GestionEmpleados.FinalBoss.empleados.dto.EmpleadosDTO;
import GestionEmpleados.FinalBoss.empleados.service.EmpleadosService;
import GestionEmpleados.FinalBoss.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/empleados")
@CrossOrigin
public class EmpleadosController {

    private final EmpleadosService service;

    public EmpleadosController(EmpleadosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmpleadosDTO>> nuevoEmpleado(@Valid @RequestBody EmpleadosDTO json){
        try{
            EmpleadosDTO dto = service.nuevoEmpleado(json);
            ApiResponse<EmpleadosDTO> response = new ApiResponse<>(true, "Se registro el nuevo empleado", dto);
            log.info("Nuevo empleado registrado" + dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            log.error("Error al realizar la inserción");
            e.printStackTrace();
            ApiResponse<EmpleadosDTO> respuestaError = new ApiResponse<>(false, "El proceso de insersión no se pudo completar", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmpleadosDTO>>> obtenerDatos(){
        try {
            List<EmpleadosDTO> lista = service.obtenerTodos();
            ApiResponse<List<EmpleadosDTO>> respuestaExitosa = new ApiResponse<>(true, "Datos consultados", lista);
            log.info("Datos de empleados consultados");
            return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
        }catch (Exception e){
            log.error("Error al consultar los datos de empleados");
            e.printStackTrace();
            ApiResponse<List<EmpleadosDTO>> respuestaError = new ApiResponse<>(false, "Error al consultar datos de empleados");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadosDTO>> obtenerPorId(@PathVariable Long id){
        try {
            EmpleadosDTO dto = service.obtenerPorId(id);
            ApiResponse<EmpleadosDTO> respuestaExitosa = new ApiResponse<>(true, "Datos del empleado con ID: "+id+", consultados", dto);
            log.info("Datos del empleado con ID: "+id+ ", consultados");
            return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
        }catch (Exception e){
            log.error("Error al consultar los datos del empleado con ID: "+id);
            e.printStackTrace();
            ApiResponse<EmpleadosDTO> respuestaError = new ApiResponse<>(false, "Error al consultar los datos del empleado con ID: "+id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarRegistro (@PathVariable Long id){
        try{
            boolean respuesta = service.eliminarRegistro(id);
            if (respuesta){
                log.info("El empleado con ID: "+id + ", ha sido eliminado");
                ApiResponse<Void> respuestExitosa = new ApiResponse<>(true, "El empleado con ID: "+id + ", ha sido eliminado");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestExitosa);
            }
            log.info("El empleado con ID: "+id + ", no se encontró");
            ApiResponse<Void> noEncontrado = new ApiResponse<>(false, "El empleado con ID: "+ id + ", no se encontró");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (RuntimeException e){
            log.warn("Error al eliminar el empleado con ID: " +id + ". " + e.getMessage());
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
        }catch (Exception e){
            log.error("Error al eliminar empleado con ID: "+id);
            e.printStackTrace();
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, "Error al eliminar el empleado con ID: "+id+ ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmpleadosDTO>> actualizarRegistro(@PathVariable Long id, @Valid @RequestBody EmpleadosDTO dto){
        try{
            EmpleadosDTO data = service.actualizarRegistro(dto, id);
            if (data != null){
                log.info("El empleado con ID: " + id + ", fue actualizado");
                ApiResponse<EmpleadosDTO> respuestExito = new ApiResponse<>(true, "Datos del empleado con ID: " + id + ", actualizados", data);
                return ResponseEntity.status(HttpStatus.OK).body(respuestExito);
            }
            log.info("No se encontró el empleado con ID: " + id);
            ApiResponse<EmpleadosDTO> respuestaFallida = new ApiResponse<>(false, "Proceso no completado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }catch (Exception e){
            log.error("Error al actualizar empleado con ID: "+id);
            e.printStackTrace();
            ApiResponse<EmpleadosDTO> respuestaError = new ApiResponse<>(false, "Error al actualizar los datos del empleado con ID: "+id+ ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

}
