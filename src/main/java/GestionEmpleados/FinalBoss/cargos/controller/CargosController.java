package GestionEmpleados.FinalBoss.cargos.controller;

import GestionEmpleados.FinalBoss.cargos.dto.CargosDTO;
import GestionEmpleados.FinalBoss.cargos.service.CargosService;
import GestionEmpleados.FinalBoss.departamentos.dto.DepartamentosDTO;
import GestionEmpleados.FinalBoss.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cargos")
@CrossOrigin
public class CargosController {

    private final CargosService service;

    public CargosController(CargosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CargosDTO>>> obtenerDatos(){
        try {
            List<CargosDTO> lista = service.obtenerTodos();
            ApiResponse<List<CargosDTO>> respuestaExitosa = new ApiResponse<>(true, "Datos consultados", lista);
            log.info("Datos de cargos consultados");
            return ResponseEntity.status(HttpStatus.OK).body(respuestaExitosa);
        }catch (Exception e){
            log.error("Error al consultar los datos de los cargos");
            e.printStackTrace();
            ApiResponse<List<CargosDTO>> respuestaError = new ApiResponse<>(false, "Error al consultar datos de los cargos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuestaError);
        }
    }

}
