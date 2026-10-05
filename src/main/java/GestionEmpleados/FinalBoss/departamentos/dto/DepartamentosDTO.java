package GestionEmpleados.FinalBoss.departamentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartamentosDTO {

    private Long id;
    @NotBlank(message = "ERR01: El nombre del depatamento es obligatorio.")
    @Size(max = 100, min = 5, message = "ERR02: El nombre del departamento no debe exceder los 100 caracteres.")
    private String nombre;
    @NotBlank(message = "ERR03: La ubicación del departamento es obligatoria")
    @Size(max = 150, min=5, message = "ERR04: La ubicacion no debe exceder los 150 caracteres.")
    private String ubicacion;

}
