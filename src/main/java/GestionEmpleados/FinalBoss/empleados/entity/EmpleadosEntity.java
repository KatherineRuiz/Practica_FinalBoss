package GestionEmpleados.FinalBoss.empleados.entity;

import GestionEmpleados.FinalBoss.cargos.entity.CargosEntity;
import GestionEmpleados.FinalBoss.departamentos.entity.DepartamentosEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="TBEMPLEADOS")
public class EmpleadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPLEADO")
    private Long id;
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    @Column(name = "CORREO", nullable = false, unique = true)
    private String correo;
    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    private DepartamentosEntity departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CARGO", nullable = false)
    private CargosEntity cargo;
}
