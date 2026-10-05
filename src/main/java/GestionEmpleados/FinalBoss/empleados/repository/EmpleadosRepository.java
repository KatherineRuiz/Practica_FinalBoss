package GestionEmpleados.FinalBoss.empleados.repository;

import GestionEmpleados.FinalBoss.empleados.entity.EmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity, Long>{

    //Verifica si hay empleados con ese departamento
    boolean existsByDepartamento_Id(Long id);
}
