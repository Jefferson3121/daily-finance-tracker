package org.ingresosYgastos.repository;


import org.ingresosYgastos.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public  interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

    Movimientos findByFechaRegistro(LocalDate fechaRegistro);

    List<Movimientos> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);


}
