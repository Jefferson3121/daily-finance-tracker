package org.ingresosYgastos.repository;


import org.ingresosYgastos.entity.IngresosGastos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;



public  interface IngresosGastosRepository extends JpaRepository<IngresosGastos, Long> {

    IngresosGastos findByFechaRegistro(LocalDate fechaRegistro);
}
