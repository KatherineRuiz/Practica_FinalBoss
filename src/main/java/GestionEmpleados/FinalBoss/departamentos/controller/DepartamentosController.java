package GestionEmpleados.FinalBoss.departamentos.controller;

import GestionEmpleados.FinalBoss.departamentos.dto.DepartamentosDTO;
import GestionEmpleados.FinalBoss.departamentos.service.DepatamentosService;
import GestionEmpleados.FinalBoss.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin
public class DepartamentosController {

    private final DepatamentosService service;

    public DepartamentosController(DepatamentosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartamentosDTO>> nuevoDepartamento(@Valid @RequestBody DepartamentosDTO json){
        try{
            DepartamentosDTO dto = service.nuevoDepartamento(json);
            ApiResponse<DepartamentosDTO> response = new ApiResponse<>(true, "Se registro el nuevo departamento", dto);
            log.info("Nuevo departamento registrado" + dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            log.error("Error al realizar la inserción");
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false, "El proceso de insersión no se pudo completar", json);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartamentosDTO>>> obtenerDatos(){
        try {
            List<DepartamentosDTO> lista = service.obtenerTodos();
            ApiResponse<List<DepartamentosDTO>> respuestaExitosa = new ApiResponse<>(true, "Datos consultados", lista);
            log.info("Datos de departamentos consultados");
            return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
        }catch (Exception e){
            log.error("Error al consultar los datos de departamentos");
            e.printStackTrace();
            ApiResponse<List<DepartamentosDTO>> respuestaError = new ApiResponse<>(false, "Error al consultar datos de departamentos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentosDTO>> obtenerPorId(@PathVariable Long id){
        try {
            DepartamentosDTO dto = service.obtenerPorId(id);
            ApiResponse<DepartamentosDTO> respuestaExitosa = new ApiResponse<>(true, "Datos del departamento con ID: "+id+", consultados", dto);
            log.info("Datos del departamento con ID: "+id+ ", consultados");
            return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
        }catch (Exception e){
            log.error("Error al consultar los datos del departamento con ID: "+id);
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false, "Error al consultar los datos del departamento con ID: "+id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarRegistro (@PathVariable Long id){
        try{
            boolean respuesta = service.eliminarRegistro(id);
            if (respuesta){
                log.info("El departamento con ID: "+id + ", ha sido eliminado");
                ApiResponse<Void> respuestExitosa = new ApiResponse<>(true, "El departamento con ID: "+id + ", ha sido eliminado");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuestExitosa);
            }
            log.info("El departamento con ID: "+id + ", no se encontró");
            ApiResponse<Void> noEncontrado = new ApiResponse<>(false, "El departamento con ID: "+ id + ", no se encontró");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noEncontrado);
        }catch (RuntimeException e){
            log.warn("Error al eliminar el departamento con ID: " +id + ". " + e.getMessage());
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
        }catch (Exception e){
            log.error("Error al eliminar departamento con ID: "+id);
            e.printStackTrace();
            ApiResponse<Void> respuestaError = new ApiResponse<>(false, "Error al eliminar el departamento con ID: "+id+ ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartamentosDTO>> actualizarRegistro(@PathVariable Long id, @Valid @RequestBody DepartamentosDTO dto){
        try{
            DepartamentosDTO data = service.actualizarRegistro(dto, id);
            if (data != null){
                log.info("El departamento con ID: " + id + ", fue actualizado");
                ApiResponse<DepartamentosDTO> respuestExito = new ApiResponse<>(true, "Datos del departamento con ID: " + id + ", actualizados", data);
                return ResponseEntity.status(HttpStatus.OK).body(respuestExito);
            }
            log.info("No se encontró el departamento con ID: " + id);
            ApiResponse<DepartamentosDTO> respuestaFallida = new ApiResponse<>(false, "Proceso no completado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaFallida);
        }catch (Exception e){
            log.error("Error al actualizar departamento con ID: "+id);
            e.printStackTrace();
            ApiResponse<DepartamentosDTO> respuestaError = new ApiResponse<>(false, "Error al actualizar los datos del departamento con ID: "+id+ ". " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }
}
