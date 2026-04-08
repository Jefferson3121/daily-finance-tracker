package org.ingresosYgastos;

import org.ingresosYgastos.entity.IngresosGastos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IngresosGastosRepository extends JpaRepository<IngresosGastos, Long> {

    IngresosGastos findByFechaRegistro(LocalDateTime fechaRegistro);
}
