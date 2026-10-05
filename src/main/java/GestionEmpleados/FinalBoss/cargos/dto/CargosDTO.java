package GestionEmpleados.FinalBoss.cargos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CargosDTO {

    private Long id;
    @NotBlank(message = "ERR05: El nombre del cargo es obligatorio.")
    @Size(max = 100, min = 5, message = "ERR06: El nombre del cargo no debe exceder los 100 caracteres.")
    private String nombre;
    @NotNull(message = "ERR07: El salario base es obligatorio")
    @Positive(message = "ERR08: El valor del salario base debe ser positivo.")
    private double salarioBase;
}
