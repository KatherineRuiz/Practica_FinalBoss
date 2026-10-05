package GestionEmpleados.FinalBoss.empleados.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpleadosDTO {

    private Long id;
    @NotBlank(message = "ERR09: El nombre del empleado es obligatorio.")
    @Size(max = 100, min = 5, message = "ERR010: El nombre del empleado no debe exceder los 100 caracteres.")
    private String nombre;
    @NotBlank(message = "ERR11: El correo del empleado es obligatorio.")
    @Size(max = 120, min = 5, message = "ERR12: El correo del empleado no debe exceder los 120 caracteres.")
    private String correo;
    @NotBlank(message = "ERR13: El teléfono del empleado es obligatorio.")
    @Size(max = 20, min = 5, message = "ERR14: El teléfono del empleado no debe exceder los 20 caracteres.")
    private String telefono;
    @NotNull(message = "ERR015: El departamento del empleado es obligatorio")
    @Positive(message = "ERR016: El valor del departamento del empleado debe ser un número positivo")
    private Long idDepartamento;
    @NotNull(message = "ERR017: El cargo del empleado es obligatorio")
    @Positive(message = "ERR018: El valor del cargo del empleado debe ser un número positivo")
    private Long idCargo;

    //Campos adicionales
    private String nombreDepartamento;
    private String nombreCargo;
}
