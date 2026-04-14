package org.ingresosYgastos.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ingresosYgastos.dto.EditarMovimientoRequestDTO;
import org.ingresosYgastos.dto.RegistrarRequestDTO;
import org.ingresosYgastos.dto.ResumenResponseDTO;
import org.ingresosYgastos.entity.Movimientos;
import org.ingresosYgastos.entity.TipoMovimiento;
import org.ingresosYgastos.repository.MovimientosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MovimientosService {

    private final MovimientosRepository movimientosRepository;

    @Transactional
    public void registrar(RegistrarRequestDTO request){


        LocalDateTime fecha = LocalDateTime.now();

        Movimientos movimientos = new Movimientos.MovimientoBuilder()
                .monto(request.monto())
                .tipo(request.tipoMovimiento())
                .detalle(request.detalle())
                .registroBuild();

        movimientosRepository.save(movimientos);

    }


    public ResumenResponseDTO ontenerResumen(LocalDate fechaInicio, LocalDate fechaFin){

        LocalDateTime inicio = fechaInicio.atStartOfDay();
        LocalDateTime fin = fechaFin.atTime(23, 59, 59);


        List<Movimientos> movimientos = movimientosRepository.findByFechaRegistroBetween(inicio, fin);

        if (movimientos.isEmpty()) {
            throw new IllegalArgumentException("No hay movimientos en ese rango de fechas");
        }

        BigDecimal totalIngresos = movimientos.stream()
                .filter(m -> m.getTipoMovimiento() == TipoMovimiento.INGRESO)
                .map(Movimientos::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastos = movimientos.stream()
                .filter(m -> m.getTipoMovimiento() == TipoMovimiento.GASTO)
                .map(Movimientos::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResumenResponseDTO(fechaInicio, totalIngresos, totalGastos, totalIngresos.subtract(totalGastos));

    }



//    public void editar(Long id, EditarMovimientoRequestDTO request) {
//
//        Movimientos movimiento = movimientosRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("No existe un movimiento con id: " + id));
//
//        if (request.monto() != null)   movimiento.setMonto(request.monto());
//        if (request.tipo() != null)    movimiento.setTipoMovimiento(request.tipo());
//        if (request.detalle() != null) movimiento.setDetalle(request.detalle());
//
//        movimientosRepository.save(movimiento);
//    }

    public void eliminar(Long id) {

        if (!movimientosRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un movimiento con id: " + id);
        }

        movimientosRepository.deleteById(id);
    }
}
