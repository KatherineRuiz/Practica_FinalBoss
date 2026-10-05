package GestionEmpleados.FinalBoss.departamentos.repository;

import GestionEmpleados.FinalBoss.departamentos.entity.DepartamentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentosRepository extends JpaRepository<DepartamentosEntity, Long> {
}
